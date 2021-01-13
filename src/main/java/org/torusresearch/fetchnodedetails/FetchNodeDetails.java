package org.torusresearch.fetchnodedetails;


import java8.util.concurrent.CompletableFuture;
import org.torusresearch.fetchnodedetails.types.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FetchNodeDetails {

    private final String proxyAddress;
    private final NodeDetails nodeDetails = new NodeDetails();
    private final String providerUrl;
    private NodeListProxy proxyContract;

    public FetchNodeDetails() {
        this(EthereumNetwork.MAINNET, "0x638646503746d5456209e33a2ff5e3226d698bea");
    }

    public FetchNodeDetails(EthereumNetwork network, String proxyAddress) {
        this.proxyAddress = proxyAddress;
        this.providerUrl = "https://" + network.toString().toLowerCase() + ".infura.io/v3/" + "b8cdb0e4cff24599a286bf8e87ff1c96";
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

    public CompletableFuture<BigInteger> getCurrentEpoch() {
        return this.proxyContract.currentEpoch().sendAsync();
    }

    public CompletableFuture<EpochInfo> getEpochInfo(BigInteger epoch) {
        return this.proxyContract.getEpochInfo(epoch).sendAsync()
                .thenApply(
                        (Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, List<String>, BigInteger, BigInteger> result) ->
                                new EpochInfo(result.component1().toString(), result.component2().toString(), result.component3().toString(),
                                        result.component4().toString(), result.component5().toArray(new String[0]),
                                        result.component6().toString(), result.component7().toString())
                );
    }

    public CompletableFuture<NodeInfo> getNodeEndpoint(String nodeEthAddress) {
        return this.proxyContract.getNodeDetails(nodeEthAddress).sendAsync().thenApply(
                (Tuple6<String, BigInteger, BigInteger, BigInteger, String, String> result) ->
                        new NodeInfo(result.component1(), result.component2().toString(), result.component3().toString(16),
                                result.component4().toString(16), result.component5(), result.component6())
        );
    }

    public CompletableFuture<NodeDetails> getNodeDetails() {
        if (this.nodeDetails.getUpdated()) return CompletableFuture.supplyAsync(() -> this.nodeDetails);
        return this.getCurrentEpoch().thenCompose((epoch) -> {
            this.nodeDetails.setNodeListAddress(this.proxyAddress);
            this.nodeDetails.setCurrentEpoch(epoch.toString());
            return this.getEpochInfo(epoch);
        }).thenCompose((latestEpochInfo) -> {
            String[] nodeList = latestEpochInfo.getNodeList();
            BigInteger[] _torusIndexes = new BigInteger[nodeList.length];
            List<CompletableFuture<NodeInfo>> futures = new ArrayList<>(nodeList.length);
            for (int i = 0, size = nodeList.length; i < size; i++) {
                _torusIndexes[i] = new BigInteger(Integer.toString(i + 1));
                futures.add(this.getNodeEndpoint(nodeList[i]));
            }
            this.nodeDetails.setTorusIndexes(_torusIndexes);
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            return allFutures.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList())
            );
        }).thenApply((nodeEndPointsList) -> {
            NodeInfo[] nodeEndPoints = nodeEndPointsList.toArray(new NodeInfo[0]);
            String[] updatedEndpoints = new String[nodeEndPoints.length];
            TorusNodePub[] updatedNodePub = new TorusNodePub[nodeEndPoints.length];
            for (int i = 0, size = nodeEndPoints.length; i < size; i++) {
                NodeInfo endPointElement = nodeEndPoints[i];
                String endpoint = "https://" + endPointElement.getDeclaredIp().split(":")[0] + "/jrpc";
                updatedEndpoints[i] = endpoint;
                updatedNodePub[i] = new TorusNodePub(endPointElement.getPubKx(), endPointElement.getPubKy());
            }
            this.nodeDetails.setTorusNodeEndpoints(updatedEndpoints);
            this.nodeDetails.setTorusNodePub(updatedNodePub);
            this.nodeDetails.setUpdated(true);
            return this.nodeDetails;
        });
    }

    private void setupWeb3() {
        Web3j web3j = Web3j.build(new HttpService(this.providerUrl));
        Credentials credentials = Credentials.create("0x5bbbef76458bf30511c9ee6ed56783644eb339258d02656755c68098c4809130");
        this.proxyContract = new NodeListProxy(this.proxyAddress, web3j, credentials, new DefaultGasProvider());
    }
}
