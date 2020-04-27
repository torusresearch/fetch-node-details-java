package org.torusresearch.fetchnodedetailstest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.torusresearch.fetchnodedetails.FetchNodeDetails;
import org.torusresearch.fetchnodedetails.types.EpochInfo;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.NodeInfo;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FetchNodeDetailsTest {
    private static final String EPOCH_18_INFO = "EpochInfo{id='18', n='9', k='5', t='2', nodeList=[0x40e8f0d606281b0a1d9d8ac9030aaae9d51229d1, 0x1c1895be5c0951da5b3663f5820e23ee74d99f87, 0x8dfa7764a9fdffff1305fac1ee2dfc1513602c30, 0x31bdf7cfc8d70bed513e27ba9e96d94635065b03, 0xf3d1ce44e2399c931d705872c83a020693711e0a, 0x014fa226e56f5df33aed56d62882eb1db9052047, 0x45a0ffdd0d2d853e7f25e3c51567e9803dbacaa3, 0xf2fa106065fa4131f71988d2c24caa235c399bc8, 0x73f1fd7b5e0501ad1e79353c73b1692d4b4b8a6c], prevEpoch='17', nextEpoch='19'}";
    private static final String FIRST_NODE_INFO = "NodeInfo{declaredIp='torus-18.torusnode.com:443', position='1', pubKx='ecbe4a29e02bb9c077433191051ba74d2458a7ebce95a3183a4201338376539f', pubKy='114018f97e6576f3fe1c247fc6802de3ff219507589a90544e6921ea9608adcf', tmP2PListenAddress='', p2pListenAddress=''}";
    private static final String NODE_DETAILS = "NodeDetails{currentEpoch='18', nodeListAddress='0x638646503746d5456209e33a2ff5e3226d698bea', torusNodeEndpoints=[https://torus-18.torusnode.com/jrpc, https://torus.ont.io/jrpc, https://torus.binancex.dev/jrpc, https://torus.matic.network/jrpc, https://torusnode.zilliqa.network/jrpc, https://torus.ens.domains/jrpc, https://torus-node.skalelabs.com/jrpc, https://torus.cosmos.network/jrpc, https://torus.etherscan.com/jrpc], torusIndexes=[1, 2, 3, 4, 5, 6, 7, 8, 9], torusNodePub=[TorusNodePub{X='ecbe4a29e02bb9c077433191051ba74d2458a7ebce95a3183a4201338376539f', Y='114018f97e6576f3fe1c247fc6802de3ff219507589a90544e6921ea9608adcf'}, TorusNodePub{X='c1d31f5b0ba633ad5dec80c35df7209ffe282392831f5d3a386d3c5c0c6b4c2b', Y='2490a3e69ac1d5a6c3170b5dc4947f038bf37a738cc279d75c18b6a85c90bc71'}, TorusNodePub{X='a6d83cc23847dec596d19e93801a0d67ee85f4b1e5f44f267a7118a0c01381ef', Y='f4b16a19ba44766d53cfaa8fe5858d87c842c7ec0c7b6ecdf11056e6afbeb726'}, TorusNodePub{X='79242c54fcfda56c914a28ae89b038dcae8ef2e9dd295171d5562f01d396fbb', Y='519c655678bf9f6091aa17d0bc13443b69fe8b10ba9336c7cc966a2164eec9e4'}, TorusNodePub{X='24b3f3582397f4f4274510e94d8e95fa1a17d57fab2e581d4da1dd2ba61c47d2', Y='fa15e5cd538992c74cbca5fc1c654ab4d79fb40e2cf5ec58190c35252d81bb6'}, TorusNodePub{X='a08620e79b5c006f261b5221e0fae75d379fd0fcd16c66ced105b3fe5282ed64', Y='e589aa09a7ac28a74d4f4cf7f52718dd9c3cca0541070eec36c66e62a748b17'}, TorusNodePub{X='17b5b8402eb27b520eaf4697c7ca3e4818bc79c6cedb6c39490da18602cd84a9', Y='f18768b68383b8fa9a91caa55768fd7bdec64c8c01cc2fa88963fc6fe92775a6'}, TorusNodePub{X='47953df2031bf785f95158a1753a65735fecc93a0248620c4afd7b08ddf239e2', Y='8058bf6928e7d49179d08cc354d3d735dd08be2f0aa6023d8a7298bb892cf65'}, TorusNodePub{X='e53c29bd04eb211d911d78ebcd36d3645872d8f8a18af4fb1937fb2a1f049078', Y='91ace383afb055ce304ccc5ee811fd8e7407c93e8e0d63d993fc08affc646045'}], updated=true}";

    FetchNodeDetails fetchNodeDetails;
    BigInteger currentEpoch = new BigInteger("18");

    @BeforeEach
    public void setup() {
        System.out.println("Setup Starting");
        fetchNodeDetails = new FetchNodeDetails();
    }

    @DisplayName("Gets Current Epoch Correctly")
    @Test
    public void shouldGetCurrentEpoch() throws ExecutionException, InterruptedException {
        BigInteger epoch = this.fetchNodeDetails.getCurrentEpoch().get();
        assertEquals(epoch, this.currentEpoch);
    }

    @DisplayName("Gets the Epoch Info")
    @Test
    public void shouldGetEpochInfo() throws ExecutionException, InterruptedException {
        EpochInfo epochInfo = this.fetchNodeDetails.getEpochInfo(this.currentEpoch).get();
        assertEquals(epochInfo.toString(), EPOCH_18_INFO);
    }

    @DisplayName("Gets the First Node Endpoint")
    @Test
    public void shouldGetNodeEndpoint() throws ExecutionException, InterruptedException {
        EpochInfo epochInfo = this.fetchNodeDetails.getEpochInfo(this.currentEpoch).get();
        NodeInfo nodeInfo = this.fetchNodeDetails.getNodeEndpoint(epochInfo.getNodeList()[0]).get();
        assertEquals(nodeInfo.toString(), FIRST_NODE_INFO);
    }

    @DisplayName("Gets the Node details")
    @Test
    public void shouldGetNodeDetails() throws ExecutionException, InterruptedException {
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails().get();
        assertEquals(nodeDetails.toString(), NODE_DETAILS);
    }

}