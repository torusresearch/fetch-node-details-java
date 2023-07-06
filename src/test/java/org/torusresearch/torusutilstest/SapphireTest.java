package org.torusresearch.torusutilstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.auth0.jwt.algorithms.Algorithm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.torusresearch.fetchnodedetails.FetchNodeDetails;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.TorusNetwork;
import org.torusresearch.torusutils.TorusUtils;
import org.torusresearch.torusutils.types.ImportedShare;
import org.torusresearch.torusutils.types.RetrieveSharesResponse;
import org.torusresearch.torusutils.types.TorusCtorOptions;
import org.torusresearch.torusutils.types.TorusException;
import org.torusresearch.torusutils.types.TorusPublicKey;
import org.torusresearch.torusutils.types.VerifierArgs;
import org.torusresearch.torusutilstest.utils.JwtUtils;
import org.torusresearch.torusutilstest.utils.PemUtils;
import org.torusresearch.torusutilstest.utils.VerifyParams;
import org.web3j.crypto.Hash;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class SapphireTest {

    static FetchNodeDetails fetchNodeDetails;

    static TorusUtils torusUtils;
    static Algorithm algorithmRs;

    static String TORUS_TEST_VERIFIER = "torus-test-health";
    static String TORUS_TEST_AGGREGATE_VERIFIER = "torus-test-health-aggregate";

    static String TORUS_IMPORT_EMAIL = "importeduser2@tor.us";
    static String TORUS_EXTENDED_VERIFIER_EMAIL = "testextenderverifierid@example.com";
    static String HashEnabledVerifier = "torus-test-verifierid-hash";

    static String TORUS_TEST_EMAIL = "saasas@tr.us";

    @BeforeAll
    static void setup() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("Setup Starting");
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.SAPPHIRE_DEVNET);
        TorusCtorOptions opts = new TorusCtorOptions("Custom");
        opts.setNetwork(TorusNetwork.SAPPHIRE_DEVNET.toString());
        opts.setAllowHost("https://signer.tor.us/api/allow");
        opts.setClientId("BG4pe3aBso5SjVbpotFQGnXVHgxhgOxnqnNBKyjfEJ3izFvIVWUaMIzoCrAfYag8O6t6a6AOvdLcS4JR2sQMjR4");
        torusUtils = new TorusUtils(opts);
        ECPrivateKey privateKey = (ECPrivateKey) PemUtils.readPrivateKeyFromFile("src/test/java/org/torusresearch/torusutilstest/keys/key.pem", "EC");
        ECPublicKey publicKey = (ECPublicKey) KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(privateKey.getParams().getGenerator(), privateKey.getParams()));
        algorithmRs = Algorithm.ECDSA256(publicKey, privateKey);
    }

    @DisplayName("Gets Public Address")
    @Test
    public void shouldGetPublicAddress() throws ExecutionException, InterruptedException {
        VerifierArgs args = new VerifierArgs(TORUS_TEST_VERIFIER, TORUS_TEST_EMAIL, "");
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(args.getVerifier(), args.getVerifierId()).get();
        TorusPublicKey torusPublicKey = torusUtils.getPublicAddress(nodeDetails.getTorusNodeSSSEndpoints(), null, args).get();
        assertEquals("0xac997dE675Fb69FCb0F4115A23c0061A892A2772", torusPublicKey.getFinalPubKeyData().getEvmAddress());
    }

    @DisplayName("Key Assign test")
    @Test
    public void shouldKeyAssign() throws ExecutionException, InterruptedException {
        String email = JwtUtils.getRandomEmail();
        VerifierArgs args = new VerifierArgs(TORUS_TEST_VERIFIER, TORUS_TEST_EMAIL, "");
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(args.getVerifier(), args.getVerifierId()).get();
        TorusPublicKey publicAddress = torusUtils.getPublicAddress(nodeDetails.getTorusNodeSSSEndpoints(), null, args).get();
        System.out.println(email + " -> " + publicAddress.getFinalPubKeyData().getEvmAddress());
        assertNotNull(publicAddress.getFinalPubKeyData().getEvmAddress());
        assertNotEquals(publicAddress.getFinalPubKeyData().getEvmAddress(), "");
    }

    @DisplayName("Login test")
    @Test
    public void shouldLogin() throws ExecutionException, InterruptedException, TorusException {
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(TORUS_TEST_VERIFIER, TORUS_TEST_EMAIL).get();
        RetrieveSharesResponse retrieveSharesResponse = torusUtils.retrieveShares(nodeDetails.getTorusNodeSSSEndpoints(), nodeDetails.getTorusIndexes(), TORUS_TEST_VERIFIER, new HashMap<String, Object>() {{
            put("verifier_id", TORUS_TEST_EMAIL);
        }}, JwtUtils.generateIdToken(TORUS_TEST_EMAIL, algorithmRs), new ImportedShare[]{}).get();
        assert (retrieveSharesResponse.getFinalKeyData().getPrivKey().equals("3c590f140b24051855f945c06629d0b66262675055b4d8a92da7d2ec4d92b08a"));
    }

    @DisplayName("Should be able to login even when node is down")
    @Test
    public void shouldLoginWhenNodeIsDown() throws Exception {
        String token = JwtUtils.generateIdToken(TORUS_TEST_EMAIL, algorithmRs);
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(TORUS_TEST_VERIFIER, TORUS_TEST_EMAIL).get();
        String[] torusNodeEndpoints = nodeDetails.getTorusNodeSSSEndpoints();
        torusNodeEndpoints[1] = "https://example.com";
        RetrieveSharesResponse retrieveSharesResponse = torusUtils.retrieveShares(torusNodeEndpoints, nodeDetails.getTorusIndexes(), TORUS_TEST_VERIFIER, new HashMap<String, Object>() {{
            put("verifier_id", TORUS_TEST_EMAIL);
        }}, token, new ImportedShare[]{}).get();
        assert (retrieveSharesResponse.getFinalKeyData().getPrivKey().equals("3c590f140b24051855f945c06629d0b66262675055b4d8a92da7d2ec4d92b08a"));
    }

    @DisplayName("should fetch public address when verifierID hash enabled")
    @Test
    public void shouldFetchPubAddressWhenVerfierIdHasEnabled() throws Exception {
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(HashEnabledVerifier, TORUS_TEST_EMAIL).get();
        String[] torusNodeEndpoints = nodeDetails.getTorusNodeSSSEndpoints();
        VerifierArgs args = new VerifierArgs(HashEnabledVerifier, TORUS_TEST_EMAIL, "extendedVerifierId");
        TorusPublicKey torusPublicKey = torusUtils.getPublicAddress(torusNodeEndpoints, null, args).get();
        assertEquals("0x4135ad20D2E9ACF37D64E7A6bD8AC34170d51219", torusPublicKey.getFinalPubKeyData().getEvmAddress());
    }

    @DisplayName("Should fetch user type and public address when verifierID hash enabled")
    @Test
    public void testFetchUserTypeAndPublicAddressWhenVerfierIdHasEnabled() throws Exception {
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(HashEnabledVerifier, TORUS_TEST_EMAIL).get();
        String[] torusNodeEndpoints = nodeDetails.getTorusNodeSSSEndpoints();
        VerifierArgs args = new VerifierArgs(HashEnabledVerifier, TORUS_TEST_EMAIL, "");
        TorusPublicKey torusPublicKey = torusUtils.getPublicAddress(torusNodeEndpoints, null, args, true).get();
        assertEquals("0x4135ad20D2E9ACF37D64E7A6bD8AC34170d51219", torusPublicKey.getFinalPubKeyData().getEvmAddress());
    }

    @DisplayName("Aggregate Login test")
    @Test
    public void shouldAggregateLogin() throws ExecutionException, InterruptedException, TorusException {
        String idToken = JwtUtils.generateIdToken(TORUS_TEST_EMAIL, algorithmRs);
        String hashedIdToken = Hash.sha3String(idToken).substring(2);
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(TORUS_TEST_AGGREGATE_VERIFIER, TORUS_TEST_EMAIL).get();
        RetrieveSharesResponse retrieveSharesResponse = torusUtils.retrieveShares(nodeDetails.getTorusNodeSSSEndpoints(), nodeDetails.getTorusIndexes(), TORUS_TEST_AGGREGATE_VERIFIER, new HashMap<String, Object>() {{
            put("verify_params", new VerifyParams[]{new VerifyParams(idToken, TORUS_TEST_EMAIL)});
            put("sub_verifier_ids", new String[]{TORUS_TEST_VERIFIER});
            put("verifier_id", TORUS_TEST_EMAIL);
        }}, hashedIdToken, new ImportedShare[]{}).get();
        assertNotNull(retrieveSharesResponse.getFinalKeyData().getEvmAddress());
        assertNotEquals("", retrieveSharesResponse.getFinalKeyData().getEvmAddress());
    }

    @DisplayName("should fetch public address of a legacy v1 user")
    @Test
    public void testFetchPublicAddressOfLegacyV1User() throws ExecutionException, InterruptedException {
        VerifierArgs verifierDetails = new VerifierArgs("google-lrc", "himanshu@tor.us", ""); // Replace with the actual verifier ID
        TorusCtorOptions opts = new TorusCtorOptions("Custom");
        opts.setNetwork(TorusNetwork.TESTNET.toString());
        opts.setAllowHost("https://signer.tor.us/api/allow");
        opts.setClientId("");
        opts.setEnableOneKey(true);
        torusUtils = new TorusUtils(opts);
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails("google-lrc", "himanshu@tor.us").get();
        TorusPublicKey publicKeyData = torusUtils.getPublicAddress(nodeDetails.getTorusNodeSSSEndpoints(), nodeDetails.getTorusNodePub(), verifierDetails).get();
        assertEquals("0x930abEDDCa6F9807EaE77A3aCc5c78f20B168Fd1", publicKeyData.getFinalPubKeyData().getEvmAddress());
        assertEquals("v1", publicKeyData.getMetadata().getTypeOfUser());
    }

    // TODO Check below test case is failing
    @DisplayName("Should fetch pub address of tss verifier id")
    @Test
    public void shouldFetchPubAddressOfTSSVerifierId() throws Exception {
        String email = TORUS_EXTENDED_VERIFIER_EMAIL;
        int nonce = 0;
        String tssTag = "default";
        String tssVerifierId = email + "\u0015" + tssTag + "\u0016" + nonce;
        VerifierArgs verifierArgs = new VerifierArgs(TORUS_TEST_VERIFIER, email, tssVerifierId);
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(TORUS_TEST_VERIFIER, email).get();
        TorusPublicKey torusPublicKey = torusUtils.getPublicAddress(nodeDetails.getTorusNodeSSSEndpoints(), null, verifierArgs).get();
        assertEquals("0xBd6Bc8aDC5f2A0526078Fd2016C4335f64eD3a30", torusPublicKey.getFinalPubKeyData().getEvmAddress());
        TorusPublicKey torusPublicKey1 = torusUtils.getPublicAddress(nodeDetails.getTorusNodeSSSEndpoints(), null, verifierArgs).get();
        assertEquals(torusPublicKey1.getFinalPubKeyData().getEvmAddress(), torusPublicKey1.getFinalPubKeyData().getEvmAddress());
    }
}
