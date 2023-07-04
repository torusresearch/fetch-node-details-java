package org.torusresearch.fetchnodedetails;

import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.torusresearch.fetchnodedetails.types.APIUtils;
import org.torusresearch.fetchnodedetails.types.FNDResponse;
import org.torusresearch.fetchnodedetails.types.LegacyNetworkMigrationInfo;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.NodeInfo;
import org.torusresearch.fetchnodedetails.types.TorusNetwork;
import org.torusresearch.fetchnodedetails.types.TorusNodePub;
import org.torusresearch.fetchnodedetails.types.Utils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FetchNodeDetails {

    public static String PROXY_ADDRESS_MAINNET = "0xf20336e16B5182637f09821c27BDe29b0AFcfe80";
    public static String PROXY_ADDRESS_TESTNET = "0xd084604e5FA387FbC2Da8bAab07fDD6aDED4614A";
    public static String PROXY_ADDRESS_CYAN = "0x9f072ba19b3370e512aa1b4bfcdaf97283168005";
    public static String PROXY_ADDRESS_AQUA = "0x29Dea82a0509153b91040ee13cDBba0f03efb625";
    public static String PROXY_ADDRESS_CELESTE = "0x6Bffb4e89453069E7487f0fa5c9f4a2D771cce6c";

    public static NodeDetails NODE_DETAILS_MAINNET = new NodeDetails("19", new String[]{"https://torus-19.torusnode.com/jrpc", "https://torus-node.ens.domains/jrpc", "https://torus-node.matic.network/jrpc", "https://torus.zilliqa.network/jrpc", "https://torus-mainnet.cosmos.network/jrpc", "https://torus2.etherscan.com/jrpc", "https://torus-node-v2.skalelabs.com/jrpc", "https://torus-node.binancex.dev/jrpc", "https://torusnode.ont.io/jrpc"}, new String[]{}, new String[]{}, new String[]{}, new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5"), new BigInteger("6"), new BigInteger("7"), new BigInteger("8"), new BigInteger("9"),}, new TorusNodePub[]{new TorusNodePub("bbe83c64177c3775550e6ba6ac2bc059f6847d644c9e4894e42c60d7974d8c2b", "82b49a7caf70def38cdad2740af45c1e4f969650105c5019a29bb18b21a9acb5"), new TorusNodePub("c208cac4ef9a47d386097a9c915b28e9cb89213abee8d26a17198ee261201b0d", "c7db2fe4631109f40833de9dc78d07e35706549ee48fa557b33e4e75e1047873"), new TorusNodePub("ca1766bb426d4ca5582818a0c5439d560ea64f5baa060793ab29dd3d0ceacfe", "d46c1d08c40e1306e1bca328c2287b8268166b11a1ba4b8442ea2ad0c5e32152"), new TorusNodePub("c3934dd2f6f4b3d2e1e398cc501e143c1e1a381b52feb6d1525af34d16253768", "71f5141a5035799099f5ea3e241e66946bc55dc857ac3bd7d6fcdb8dcd3eeeef"), new TorusNodePub("22e66f1929631d00bf026227581597f085fd94fd952fc0dca9f0833398b5c064", "6088b3912e10a1e9d50355a609c10db7d188f16a2e2fd7357e51bf4f6a74f0a1"), new TorusNodePub("9dc9fa410f3ce9eb70df70cdea00a49f2c4cc7a31c08c0dab5f863ed35ff5139", "627a291cb87a75c61da3f65d6818e1e05e360217179817ed27e8c73bca7ec122"), new TorusNodePub("118b9fc07e97b096d899b9f6658463ce6a8caa64038e37fc969df4e6023dd8c6", "baf9fa4e51770f4796ea165dd03a769b8606681a38954a0a92c4cbffd6609ce9"), new TorusNodePub("8a6d8b925da15a273dec3d8f8395ec35cd6878f274b2b180e4e106999db64043", "96f67f870c157743da0b1eb84d89bf30500d74dc84c11f501ee1cb013acc8c46"), new TorusNodePub("39cecb62e863729f572f7dfc46c24867981bf04bb405fed0df39e33984bfade5", "61c2364434012e68a2be2e9952805037e52629d7762fafc8e10e9fb5bad8f790")}, false);

    public static HashMap<TorusNetwork, String> NETWORK_MAP = new HashMap<TorusNetwork, String>() {
        {
            put(TorusNetwork.MAINNET, "mainnet");
            put(TorusNetwork.TESTNET, "goerli");
            put(TorusNetwork.CYAN, "polygon-mainnet");
            put(TorusNetwork.AQUA, "polygon-mainnet");
            put(TorusNetwork.CELESTE, "polygon-mainnet");
            put(TorusNetwork.SAPPHIRE_DEVNET, "sapphire_devnet");
            put(TorusNetwork.SAPPHIRE_TESTNET, "sapphire_testnet");
            put(TorusNetwork.SAPPHIRE_MAINNET, "sapphire_mainnet");

        }
    };

    public static HashMap<String, LegacyNetworkMigrationInfo> NETWORKS_ROUTE_MAP = new HashMap<String, LegacyNetworkMigrationInfo>() {
        {
            put(TorusNetwork.AQUA.toString(), new LegacyNetworkMigrationInfo(false, "aqua", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.CELESTE.toString(), new LegacyNetworkMigrationInfo(false, "celeste", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.CYAN.toString(), new LegacyNetworkMigrationInfo(false, "cyan", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.MAINNET.toString(), new LegacyNetworkMigrationInfo(false, "mainnet", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.TESTNET.toString(), new LegacyNetworkMigrationInfo(true, "teal", TorusNetwork.SAPPHIRE_TESTNET));
        }
    };

    private List<String> multi_cluster_networks = Arrays.asList("aqua", "celeste", "cyan");

    private String proxyAddress;
    private String providerUrl;
    private TorusLookup torusLookup;
    private final NodeDetails nodeDetails = new NodeDetails();
    private TorusNetwork torusNetwork = TorusNetwork.MAINNET;

    public FetchNodeDetails() {
        this(TorusNetwork.MAINNET);
    }

    public FetchNodeDetails(TorusNetwork network, String proxyAddress) {
        this.proxyAddress = proxyAddress;
        this.torusNetwork = network;
        this.providerUrl = "https://" + NETWORK_MAP.get(network) + ".infura.io/v3/" + "b8cdb0e4cff24599a286bf8e87ff1c96";
        this.setupWeb3();
    }

    public FetchNodeDetails(String providerUrl, String proxyAddress) {
        this.proxyAddress = proxyAddress;
        this.providerUrl = providerUrl;
        this.setupWeb3();
    }

    public FetchNodeDetails(TorusNetwork network) {
        this.torusNetwork = network;
    }

    public CompletableFuture<NodeDetails> getLegacyNodeDetails(String verifier, String verifierId) {
        // For mainnet & ropsten, verifierId combination doesn't change the network details
        if (this.nodeDetails.getUpdated() && (this.proxyAddress.equals(PROXY_ADDRESS_MAINNET) || this.proxyAddress.equals(PROXY_ADDRESS_TESTNET)))
            return CompletableFuture.supplyAsync(() -> this.nodeDetails);
        byte[] hashedVerifierId = Hash.sha3(verifierId.getBytes(StandardCharsets.UTF_8));
        CompletableFuture<NodeDetails> cf = new CompletableFuture<>();
        this.torusLookup.getNodeSet(verifier, hashedVerifierId).sendAsync().whenCompleteAsync((nodeEndPoints, err) -> {
            if (err != null) {
                if (this.proxyAddress.equals(FetchNodeDetails.PROXY_ADDRESS_MAINNET)) {
                    cf.complete(FetchNodeDetails.NODE_DETAILS_MAINNET);
                } else {
                    cf.completeExceptionally(err);
                }
                return;
            }
            try {
                String[] updatedEndpoints = new String[nodeEndPoints.component3().size()];
                TorusNodePub[] updatedNodePub = new TorusNodePub[nodeEndPoints.component3().size()];

                for (int i = 0; i < nodeEndPoints.component3().size(); i++) {
                    NodeInfo endPointElement = new NodeInfo(String.valueOf(nodeEndPoints.component2().get(i)), new BigInteger(String.valueOf(nodeEndPoints.component3().get(i)), 10).toString(16).replace("0x", ""), new BigInteger(String.valueOf(nodeEndPoints.component4().get(i)), 10).toString(16).replace("0x", ""));

                    String endpoint = "https://" + endPointElement.getDeclaredIp().split(":")[0] + "/jrpc";
                    updatedEndpoints[i] = endpoint;
                    updatedNodePub[i] = new TorusNodePub(endPointElement.getPubKx(), endPointElement.getPubKy());
                }
                this.nodeDetails.setCurrentEpoch(nodeEndPoints.component1().toString());
                this.nodeDetails.setTorusNodeEndpoints(updatedEndpoints);
                this.nodeDetails.setTorusNodePub(updatedNodePub);
                this.nodeDetails.setTorusIndexes(nodeEndPoints.component5().toArray(new BigInteger[0]));
                this.nodeDetails.setUpdated(true);
                cf.complete(this.nodeDetails);
            } catch (Exception err2) {
                cf.completeExceptionally(err2);
            }
        });
        return cf;
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
            JSONObject jsonObject = new JSONObject(response.get());
            FNDResponse fndResponse =
                    new GsonBuilder().disableHtmlEscaping().create().fromJson(jsonObject.toString(), FNDResponse.class);
            cf.complete(fndResponse.getNodeDetails());
            return cf;
        } catch (Exception ex) {
            cf.completeExceptionally(new Exception("Failed to fetch node details from server"));
        }
        NodeDetails nodeDetails = Utils.fetchLocalConfig(this.torusNetwork);
        cf.complete(nodeDetails);
        return cf;
    }

    private void setupWeb3() {
        Web3j web3j = Web3j.build(new HttpService(this.providerUrl));
        Credentials credentials = Credentials.create("0x5bbbef76458bf30511c9ee6ed56783644eb339258d02656755c68098c4809130");
        this.torusLookup = new TorusLookup(this.proxyAddress, web3j, credentials, new DefaultGasProvider());
    }
}
