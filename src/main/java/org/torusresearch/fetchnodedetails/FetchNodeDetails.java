package org.torusresearch.fetchnodedetails;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import org.torusresearch.fetchnodedetails.types.*;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

public class FetchNodeDetails {

    private final String proxyAddress;
    private final NodeDetails nodeDetails = new NodeDetails();
    private final String providerUrl;
    private TorusLookup torusLookup;

    public FetchNodeDetails() {
        this(EthereumNetwork.MAINNET, "0xf20336e16B5182637f09821c27BDe29b0AFcfe80");
        // this(EthereumNetwork.ROPSTEN, "0x4023d2a0D330bF11426B12C6144Cfb96B7fa6183");
        // this(EthereumNetwork.POLYGON, "0x6258c9d6c12ed3edda59a1a6527e469517744aa7");
    }

    public FetchNodeDetails(EthereumNetwork network, String proxyAddress) {
        this.proxyAddress = proxyAddress;
        this.providerUrl = "https://" + network.name() + ".infura.io/v3/" + "b8cdb0e4cff24599a286bf8e87ff1c96";
        this.setupWeb3();
    }

    public FetchNodeDetails(String proxyAddress, String providerUrl) {
        this.proxyAddress = proxyAddress;
        this.providerUrl = providerUrl;
        this.setupWeb3();
    }

//    public static void main(String[] args) {
//        FetchNodeDetails fetchNodeDetails = new FetchNodeDetails();
//        try {
//            // var epoch = fetchNodeDetails.getCurrentEpoch().get();
//            NodeDetails nodeDetails = fetchNodeDetails.getNodeDetails().get();
//            String[] epoch = nodeDetails.getTorusNodeEndpoints();
//            TorusNodePub[] items = nodeDetails.getTorusNodePub();
//            BigInteger[] indexes = nodeDetails.getTorusIndexes();
//            Arrays.stream(items).forEach(System.out::println);
//            Arrays.stream(epoch).forEach(System.out::println);
//            Arrays.stream(indexes).forEach(System.out::println);
//            System.out.println(nodeDetails);
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        System.out.println("Hello world from fetch node details");
//    }


    public CompletableFuture<NodeDetails> getNodeDetails() throws UnsupportedEncodingException {
        if (this.nodeDetails.getUpdated()) return CompletableFuture.supplyAsync(() -> this.nodeDetails);
        String verifierid = "hello@tor.us";
        String encodedVerifierId = URLEncoder.encode(verifierid, "utf-8");
        String hash = Hash.sha3(encodedVerifierId).substring(2);
        BigInteger bigInt = new BigInteger(hash, 16);

        byte[] hashVerifierId =bigInt.toByteArray();

        return this.torusLookup.getNodeSet("google", hashVerifierId).sendAsync().thenApply((nodeEndPoints) -> {
            String[] updatedEndpoints = new String[nodeEndPoints.component3().size()];
            TorusNodePub[] updatedNodePub = new TorusNodePub[nodeEndPoints.component3().size()];

            for (int i = 0; i < nodeEndPoints.component3().size(); i++) {
                NodeInfo endPointElement = new NodeInfo(String.valueOf(nodeEndPoints.component2().get(i)),
                        new BigInteger(String.valueOf(nodeEndPoints.component3().get(i)),10).toString(16).replace("0x", ""),
                        new BigInteger(String.valueOf(nodeEndPoints.component4().get(i)),10).toString(16).replace("0x", ""));

                String endpoint = "https://" + endPointElement.getDeclaredIp().split(":")[0] + "/jrpc";
                updatedEndpoints[i] = endpoint;
                updatedNodePub[i] = new TorusNodePub(endPointElement.getPubKx(), endPointElement.getPubKy());
            }
            this.nodeDetails.setNodeListAddress(this.proxyAddress);
            this.nodeDetails.setCurrentEpoch(nodeEndPoints.component1().toString());
            this.nodeDetails.setTorusNodeEndpoints(updatedEndpoints);
            this.nodeDetails.setTorusNodePub(updatedNodePub);
            this.nodeDetails.setUpdated(true);
            return this.nodeDetails;
        });
    }

    private void setupWeb3() {
        Web3j web3j = Web3j.build(new HttpService(this.providerUrl));
        Credentials credentials = Credentials.create("0x5bbbef76458bf30511c9ee6ed56783644eb339258d02656755c68098c4809130");
        this.torusLookup = new TorusLookup(this.proxyAddress, web3j, credentials, new DefaultGasProvider());
    }
}
