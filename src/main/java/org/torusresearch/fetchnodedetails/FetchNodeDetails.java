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

    public static HashMap<TorusNetwork, String> NETWORK_MAP = new HashMap<TorusNetwork, String>() {
        {
            put(TorusNetwork.MAINNET, "mainnet");
            put(TorusNetwork.TESTNET, "goerli");
            put(TorusNetwork.CYAN, "polygon-mainnet");
            put(TorusNetwork.AQUA, "polygon-mainnet");
            put(TorusNetwork.CELESTE, "polygon-mainnet");
            put(TorusNetwork.SAPPHIRE_DEVNET, "sapphire_devnet");
            put(TorusNetwork.SAPPHIRE_MAINNET, "sapphire_mainnet");

        }
    };

    public static HashMap<String, LegacyNetworkMigrationInfo> LEGACY_NETWORKS_ROUTE_MAP = new HashMap<String, LegacyNetworkMigrationInfo>() {
        {
            put(TorusNetwork.AQUA.toString(), new LegacyNetworkMigrationInfo(false, "aqua", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.CELESTE.toString(), new LegacyNetworkMigrationInfo(false, "celeste", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.CYAN.toString(), new LegacyNetworkMigrationInfo(false, "cyan", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.MAINNET.toString(), new LegacyNetworkMigrationInfo(true, "mainnet", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.TESTNET.toString(), new LegacyNetworkMigrationInfo(true, "teal", TorusNetwork.SAPPHIRE_DEVNET));
        }
    };

    private List<String> multi_cluster_networks = Arrays.asList("aqua", "celeste", "cyan");

    private final NodeDetails nodeDetails = new NodeDetails();
    private TorusNetwork torusNetwork = TorusNetwork.MAINNET;

    public FetchNodeDetails() {
        this(TorusNetwork.MAINNET);
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
            cf.complete(fndResponse.getNodeDetails());
            return cf;
        } catch (Exception ex) {
            cf.completeExceptionally(new Exception("Failed to fetch node details from server"));
        }
        NodeDetails nodeDetails = Utils.fetchLocalConfig(this.torusNetwork);
        cf.complete(nodeDetails);
        return cf;
    }
}
