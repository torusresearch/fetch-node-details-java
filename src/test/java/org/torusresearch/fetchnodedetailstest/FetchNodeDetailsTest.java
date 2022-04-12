package org.torusresearch.fetchnodedetailstest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.torusresearch.fetchnodedetails.FetchNodeDetails;
import org.torusresearch.fetchnodedetails.types.NodeDetails;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FetchNodeDetailsTest {
    private static final String NODE_DETAILS = "NodeDetails{currentEpoch='19', nodeListAddress='0xf20336e16B5182637f09821c27BDe29b0AFcfe80', torusNodeEndpoints=[https://torus-19.torusnode.com/jrpc, https://torus-node.ens.domains/jrpc, https://torus-node.matic.network/jrpc, https://torus.zilliqa.network/jrpc, https://torus-mainnet.cosmos.network/jrpc, https://torus2.etherscan.com/jrpc, https://torus-node-v2.skalelabs.com/jrpc, https://torus-node.binancex.dev/jrpc, https://torusnode.ont.io/jrpc], torusIndexes=null, torusNodePub=[TorusNodePub{X='bbe83c64177c3775550e6ba6ac2bc059f6847d644c9e4894e42c60d7974d8c2b', Y='82b49a7caf70def38cdad2740af45c1e4f969650105c5019a29bb18b21a9acb5'}, TorusNodePub{X='c208cac4ef9a47d386097a9c915b28e9cb89213abee8d26a17198ee261201b0d', Y='c7db2fe4631109f40833de9dc78d07e35706549ee48fa557b33e4e75e1047873'}, TorusNodePub{X='ca1766bb426d4ca5582818a0c5439d560ea64f5baa060793ab29dd3d0ceacfe', Y='d46c1d08c40e1306e1bca328c2287b8268166b11a1ba4b8442ea2ad0c5e32152'}, TorusNodePub{X='c3934dd2f6f4b3d2e1e398cc501e143c1e1a381b52feb6d1525af34d16253768', Y='71f5141a5035799099f5ea3e241e66946bc55dc857ac3bd7d6fcdb8dcd3eeeef'}, TorusNodePub{X='22e66f1929631d00bf026227581597f085fd94fd952fc0dca9f0833398b5c064', Y='6088b3912e10a1e9d50355a609c10db7d188f16a2e2fd7357e51bf4f6a74f0a1'}, TorusNodePub{X='9dc9fa410f3ce9eb70df70cdea00a49f2c4cc7a31c08c0dab5f863ed35ff5139', Y='627a291cb87a75c61da3f65d6818e1e05e360217179817ed27e8c73bca7ec122'}, TorusNodePub{X='118b9fc07e97b096d899b9f6658463ce6a8caa64038e37fc969df4e6023dd8c6', Y='baf9fa4e51770f4796ea165dd03a769b8606681a38954a0a92c4cbffd6609ce9'}, TorusNodePub{X='8a6d8b925da15a273dec3d8f8395ec35cd6878f274b2b180e4e106999db64043', Y='96f67f870c157743da0b1eb84d89bf30500d74dc84c11f501ee1cb013acc8c46'}, TorusNodePub{X='39cecb62e863729f572f7dfc46c24867981bf04bb405fed0df39e33984bfade5', Y='61c2364434012e68a2be2e9952805037e52629d7762fafc8e10e9fb5bad8f790'}], updated=true}";

    FetchNodeDetails fetchNodeDetails;
    BigInteger currentEpoch = new BigInteger("19");

    @BeforeEach
    public void setup() {
        System.out.println("Setup Starting");
        fetchNodeDetails = new FetchNodeDetails();
    }

    @DisplayName("Gets the Node details")
    @Test
    public void shouldGetNodeDetails() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails().get();
       // assertEquals(NODE_DETAILS, nodeDetails.toString());
        System.out.println(nodeDetails);
    }

}