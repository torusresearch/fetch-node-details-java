package org.torusresearch.fetchnodedetails;

import com.google.gson.GsonBuilder;

import org.torusresearch.fetchnodedetails.types.APIUtils;
import org.torusresearch.fetchnodedetails.types.BuildEnv;
import org.torusresearch.fetchnodedetails.types.FNDResponse;
import org.torusresearch.fetchnodedetails.types.KeyType;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.SigType;
import org.torusresearch.fetchnodedetails.types.Utils;
import org.torusresearch.fetchnodedetails.types.Web3AuthNetwork;

import java.util.concurrent.CompletableFuture;

public class FetchNodeDetails {

    private final NodeDetails nodeDetails = new NodeDetails();
    private Web3AuthNetwork web3AuthNetwork = Web3AuthNetwork.SAPPHIRE_MAINNET;
    private BuildEnv buildEnv = BuildEnv.PRODUCTION;
    private KeyType keyType = KeyType.SECP256K1;
    private SigType sigType = SigType.ECDSA_SECP256K1;
    private String fndServerEndpoint;

    public FetchNodeDetails() {
        this(Web3AuthNetwork.SAPPHIRE_MAINNET);
    }

    public FetchNodeDetails(Web3AuthNetwork network) {
        this(network, BuildEnv.PRODUCTION);
    }

    public FetchNodeDetails(Web3AuthNetwork network, BuildEnv buildEnv) {
        this(network, buildEnv, KeyType.SECP256K1, SigType.ECDSA_SECP256K1, null);
    }

    public FetchNodeDetails(Web3AuthNetwork network, BuildEnv buildEnv, KeyType keyType, SigType sigType, String fndServerEndpoint) {
        this.web3AuthNetwork = network;
        this.buildEnv = buildEnv;
        this.keyType = keyType;
        this.sigType = sigType;
        if (fndServerEndpoint != null && !fndServerEndpoint.isEmpty()) {
            this.fndServerEndpoint = fndServerEndpoint;
        } else {
            this.fndServerEndpoint = Utils.FND_SERVER_MAP.get(buildEnv) + "/node-details";
        }
    }

    public CompletableFuture<NodeDetails> getNodeDetails(String verifier, String verifierId) {
        if (this.nodeDetails.getUpdated())
            return CompletableFuture.supplyAsync(() -> this.nodeDetails);

        CompletableFuture<NodeDetails> cf = new CompletableFuture<>();
        try {
            String url = this.fndServerEndpoint + "?network=" + this.web3AuthNetwork + "&verifier=" + verifier
                    + "&verifierId=" + verifierId + "&keyType=" + this.keyType + "&sigType=" + this.sigType;
            CompletableFuture<String> response = APIUtils.get(url);
            FNDResponse fndResponse =
                    new GsonBuilder().disableHtmlEscaping().create().fromJson(response.get(), FNDResponse.class);
            this.setNodeDetails(fndResponse.getNodeDetails(), true);
            cf.complete(this.nodeDetails);
            return cf;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NodeDetails nodeDetails = Utils.fetchLocalConfig(this.web3AuthNetwork, this.keyType, this.sigType);
        if (nodeDetails == null) cf.completeExceptionally(new Exception("Failed to fetch node details"));
        else {
            this.setNodeDetails(nodeDetails, false);
            cf.complete(this.nodeDetails);
        }
        return cf;
    }

    public CompletableFuture<String> getMetadataUrl() {
        if (this.web3AuthNetwork.isLegacyNetwork())
            return CompletableFuture.supplyAsync(() -> Utils.LEGACY_METADATA_MAP.get(this.buildEnv));

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
