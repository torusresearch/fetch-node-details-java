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
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class TorusUtilsTest {

    static FetchNodeDetails fetchNodeDetails;

    static TorusUtils torusUtils;
    static Algorithm algorithmRs;

    static String TORUS_TEST_VERIFIER = "torus-test-health";
    static String TORUS_TEST_AGGREGATE_VERIFIER = "torus-test-health-aggregate";

    static String TORUS_TEST_EMAIL = "hello@tor.us";

    @BeforeAll
    static void setup() throws ExecutionException, InterruptedException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("Setup Starting");
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.TESTNET);
        TorusCtorOptions opts = new TorusCtorOptions("Custom");
        opts.setNetwork(TorusNetwork.TESTNET.name());
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
        VerifierArgs args = new VerifierArgs("google-lrc", TORUS_TEST_EMAIL, "extendedVerifierId");
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(args.getVerifier(), args.getVerifierId()).get();
        TorusPublicKey publicAddress = torusUtils.getLegacyPublicAddress(nodeDetails.getTorusNodeEndpoints(), nodeDetails.getTorusNodePub(), args).get();
        assertEquals("0x5b56E06009528Bffb1d6336575731ee3B63f6150", publicAddress.getAddress());
    }

    @DisplayName("Key Assign test")
    @Test
    public void shouldKeyAssign() throws ExecutionException, InterruptedException {
        String email = JwtUtils.getRandomEmail();
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails("google-lrc", email).get();
        TorusPublicKey publicAddress = torusUtils.getLegacyPublicAddress(nodeDetails.getTorusNodeEndpoints(), nodeDetails.getTorusNodePub(), new VerifierArgs("google-lrc", email, "extendedVerifierId")).get();
        System.out.println(email + " -> " + publicAddress.getAddress());
        assertNotNull(publicAddress.getAddress());
        assertNotEquals(publicAddress.getAddress(), "");
    }

    /*@DisplayName("Login test")
    @Test
    public void shouldLogin() throws ExecutionException, InterruptedException, TorusException {
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(TORUS_TEST_VERIFIER, TORUS_TEST_EMAIL).get();
        RetrieveSharesResponse retrieveSharesResponse = torusUtils.retrieveShares(nodeDetails.getTorusNodeEndpoints(), nodeDetails.getTorusIndexes(), TORUS_TEST_VERIFIER, new HashMap<String, Object>() {{
            put("verifier_id", TORUS_TEST_EMAIL);
        }}, JwtUtils.generateIdToken(TORUS_TEST_EMAIL, algorithmRs)).get();
        System.out.println(retrieveSharesResponse.getPrivKey());
        BigInteger requiredPrivateKey = new BigInteger("68ee4f97468ef1ae95d18554458d372e31968190ae38e377be59d8b3c9f7a25", 16);
        assert (requiredPrivateKey.equals(retrieveSharesResponse.getPrivKey()));
        assertEquals("0xEfd7eDAebD0D99D1B7C8424b54835457dD005Dc4", retrieveSharesResponse.getEthAddress());
    }

    @DisplayName("Aggregate Login test")
    @Test
    public void shouldAggregateLogin() throws ExecutionException, InterruptedException, TorusException {
        String idToken = JwtUtils.generateIdToken(TORUS_TEST_EMAIL, algorithmRs);
        String hashedIdToken = Hash.sha3String(idToken).substring(2);
        NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails(TORUS_TEST_AGGREGATE_VERIFIER, TORUS_TEST_EMAIL).get();
        RetrieveSharesResponse retrieveSharesResponse = torusUtils.retrieveShares(nodeDetails.getTorusNodeEndpoints(), nodeDetails.getTorusIndexes(), TORUS_TEST_AGGREGATE_VERIFIER, new HashMap<String, Object>() {{
            put("verify_params", new VerifyParams[]{new VerifyParams(idToken, TORUS_TEST_EMAIL)});
            put("sub_verifier_ids", new String[]{TORUS_TEST_VERIFIER});
            put("verifier_id", TORUS_TEST_EMAIL);
        }}, hashedIdToken).get();
        assertEquals("0x5a165d2Ed4976BD104caDE1b2948a93B72FA91D2", retrieveSharesResponse.getEthAddress());
    }*/
}
