package org.torusresearch.fetchnodedetails;

import com.google.gson.GsonBuilder;

import org.torusresearch.fetchnodedetails.types.APIUtils;
import org.torusresearch.fetchnodedetails.types.FNDResponse;
import org.torusresearch.fetchnodedetails.types.LegacyNetworkMigrationInfo;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.TorusNetwork;
import org.torusresearch.fetchnodedetails.types.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FetchNodeDetails {

    private List<String> multi_cluster_networks = Arrays.asList("celeste");

    private final NodeDetails nodeDetails = new NodeDetails();
    private TorusNetwork torusNetwork = TorusNetwork.SAPPHIRE_MAINNET;

    public FetchNodeDetails() {
        this(TorusNetwork.SAPPHIRE_MAINNET);
    }

    public FetchNodeDetails(TorusNetwork network) {
        this.torusNetwork = network;
    }

    public CompletableFuture<NodeDetails> getNodeDetails(String verifier, String verifierId) {
        // For mainnet & ropsten, verifierId combination doesn't change the network details
        if (this.nodeDetails.getUpdated() && !(multi_cluster_networks.contains(this.torusNetwork.name())))
            return CompletableFuture.supplyAsync(() -> this.nodeDetails);

        CompletableFuture<NodeDetails> cf = new CompletableFuture<>();
        try {
            String fndServerEndpoint = "https://fnd.tor.us/node-details";
            String url = fndServerEndpoint + "?network=" + this.torusNetwork + "&verifier=" + verifier + "&verifierId=" + verifierId;
            CompletableFuture<String> response = APIUtils.get(url);
            FNDResponse fndResponse =
                    new GsonBuilder().disableHtmlEscaping().create().fromJson(response.get(), FNDResponse.class);
            this.setNodeDetails(fndResponse.getNodeDetails(), true);
            cf.complete(this.nodeDetails);
            return cf;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NodeDetails nodeDetails = Utils.fetchLocalConfig(this.torusNetwork);
        if (nodeDetails == null) cf.completeExceptionally(new Exception("Failed to fetch node details"));
        else {
            this.setNodeDetails(nodeDetails, false);
            cf.complete(this.nodeDetails);
        }
        return cf;
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
