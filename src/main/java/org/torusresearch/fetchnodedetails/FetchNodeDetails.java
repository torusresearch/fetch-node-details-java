package org.torusresearch.fetchnodedetails;

import com.google.gson.GsonBuilder;

import org.torusresearch.fetchnodedetails.types.APIUtils;
import org.torusresearch.fetchnodedetails.types.FNDResponse;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.Utils;
import org.torusresearch.fetchnodedetails.types.Web3AuthNetwork;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FetchNodeDetails {

    private List<String> multi_cluster_networks = Arrays.asList("celeste");

    private final NodeDetails nodeDetails = new NodeDetails();
    private Web3AuthNetwork web3AuthNetwork = Web3AuthNetwork.SAPPHIRE_MAINNET;

    public FetchNodeDetails() {
        this(Web3AuthNetwork.SAPPHIRE_MAINNET);
    }

    public FetchNodeDetails(Web3AuthNetwork network) {
        this.web3AuthNetwork = network;
    }

    public CompletableFuture<NodeDetails> getNodeDetails(String verifier, String verifierId) {
        // For mainnet & ropsten, verifierId combination doesn't change the network details
        if (this.nodeDetails.getUpdated() && !(multi_cluster_networks.contains(this.web3AuthNetwork.name())))
            return CompletableFuture.supplyAsync(() -> this.nodeDetails);

        CompletableFuture<NodeDetails> cf = new CompletableFuture<>();
        try {
            String fndServerEndpoint = Utils.FND_SERVER + "/node-details";
            String url = fndServerEndpoint + "?network=" + this.web3AuthNetwork + "&verifier=" + verifier + "&verifierId=" + verifierId;
            CompletableFuture<String> response = APIUtils.get(url);
            FNDResponse fndResponse =
                    new GsonBuilder().disableHtmlEscaping().create().fromJson(response.get(), FNDResponse.class);
            this.setNodeDetails(fndResponse.getNodeDetails(), true);
            cf.complete(this.nodeDetails);
            return cf;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NodeDetails nodeDetails = Utils.fetchLocalConfig(this.web3AuthNetwork);
        if (nodeDetails == null) cf.completeExceptionally(new Exception("Failed to fetch node details"));
        else {
            this.setNodeDetails(nodeDetails, false);
            cf.complete(this.nodeDetails);
        }
        return cf;
    }

    public CompletableFuture<String> getMetadataUrl() {
        List<Web3AuthNetwork> legacyNetworks = Arrays.asList(Web3AuthNetwork.AQUA, Web3AuthNetwork.CYAN, Web3AuthNetwork.CELESTE, Web3AuthNetwork.MAINNET, Web3AuthNetwork.TESTNET);
        if (legacyNetworks.contains(this.web3AuthNetwork))
            return CompletableFuture.supplyAsync(() -> Utils.METADATA_MAP.get(this.web3AuthNetwork));

        return this.getNodeDetails("test-verifier", "test-verifier-id").thenCompose((nodeDetails) -> CompletableFuture.supplyAsync(() -> nodeDetails.getTorusNodeEndpoints()[0].replace("/sss/jrpc", "/metadata")));
    }

    private void setNodeDetails(NodeDetails nodeDetails, boolean updated) {
        this.nodeDetails.setTorusNodeEndpoints(nodeDetails.getTorusNodeEndpoints());
        this.nodeDetails.setTorusNodePub(nodeDetails.getTorusNodePub());
        this.nodeDetails.setCurrentEpoch(nodeDetails.getCurrentEpoch());
        this.nodeDetails.setUpdated(updated);
        this.nodeDetails.setTorusIndexes(nodeDetails.getTorusIndexes());
        this.nodeDetails.setTorusNodeRSSEndpoints(nodeDetails.getTorusNodeRSSEndpoints());
        this.nodeDetails.setTorusNodeSSSEndpoints(nodeDetails.getTorusNodeSSSEndpoints());
        this.nodeDetails.setTorusNodeTSSEndpoints(nodeDetails.getTorusNodeTSSEndpoints());
    }
}
