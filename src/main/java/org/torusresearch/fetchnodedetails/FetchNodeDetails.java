package org.torusresearch.fetchnodedetails;

import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.TorusNetwork;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class FetchNodeDetails {

    public static String PROXY_ADDRESS_MAINNET = "0xf20336e16B5182637f09821c27BDe29b0AFcfe80";
    public static String PROXY_ADDRESS_TESTNET = "0xd084604e5FA387FbC2Da8bAab07fDD6aDED4614A";
    public static String PROXY_ADDRESS_CYAN = "0x9f072ba19b3370e512aa1b4bfcdaf97283168005";
    public static String PROXY_ADDRESS_AQUA = "0x29Dea82a0509153b91040ee13cDBba0f03efb625";
    public static String PROXY_ADDRESS_CELESTE = "0x6Bffb4e89453069E7487f0fa5c9f4a2D771cce6c";

    private String fndServerEndpoint = "https://fnd.tor.us/node-details";


    public static HashMap<TorusNetwork, String> NETWORK_MAP = new HashMap<TorusNetwork, String>() {
        {
            put(TorusNetwork.MAINNET, "mainnet");
            put(TorusNetwork.TESTNET, "goerli");
            put(TorusNetwork.CYAN, "polygon-mainnet");
            put(TorusNetwork.AQUA, "polygon-mainnet");
            put(TorusNetwork.CELESTE, "polygon-mainnet");
        }
    };


    private final String proxyAddress;
    private final NodeDetails nodeDetails = new NodeDetails();
    private final String providerUrl;
    private TorusLookup torusLookup;

    public FetchNodeDetails() {
        this(TorusNetwork.MAINNET, "0xf20336e16B5182637f09821c27BDe29b0AFcfe80");
    }

    public FetchNodeDetails(TorusNetwork network, String proxyAddress) {
        this.proxyAddress = proxyAddress;
        this.providerUrl = "https://" + NETWORK_MAP.get(network) + ".infura.io/v3/" + "b8cdb0e4cff24599a286bf8e87ff1c96";
        this.setupWeb3();
    }

    public FetchNodeDetails(String providerUrl, String proxyAddress) {
        this.proxyAddress = proxyAddress;
        this.providerUrl = providerUrl;
        this.setupWeb3();
    }

    public CompletableFuture<NodeDetails> getNodeDetails(String verifier, String verifierId) {
        // For mainnet & ropsten, verifierId combination doesn't change the network details
        if (this.nodeDetails.getUpdated() && (this.proxyAddress.equals(PROXY_ADDRESS_MAINNET) || this.proxyAddress.equals(PROXY_ADDRESS_TESTNET)))
            return CompletableFuture.supplyAsync(() -> this.nodeDetails);
        byte[] hashedVerifierId = Hash.sha3(verifierId.getBytes(StandardCharsets.UTF_8));
        CompletableFuture<NodeDetails> cf = new CompletableFuture<>();
        this.torusLookup.getNodeSet(verifier, hashedVerifierId).sendAsync().whenCompleteAsync((nodeEndPoints, err) -> {
            if (err != null) {
                if (this.proxyAddress.equals(FetchNodeDetails.PROXY_ADDRESS_MAINNET)) {
                    //cf.complete(FetchNodeDetails.NODE_DETAILS_MAINNET);
                } else {
                    cf.completeExceptionally(err);
                }
                return;
            }
            try {
                cf.complete(this.nodeDetails);
            } catch (Exception err2) {
                cf.completeExceptionally(err2);
            }
        });
        return cf;
    }

    private void setupWeb3() {
        Web3j web3j = Web3j.build(new HttpService(this.providerUrl));
        Credentials credentials = Credentials.create("0x5bbbef76458bf30511c9ee6ed56783644eb339258d02656755c68098c4809130");
        this.torusLookup = new TorusLookup(this.proxyAddress, web3j, credentials, new DefaultGasProvider());
    }
}
