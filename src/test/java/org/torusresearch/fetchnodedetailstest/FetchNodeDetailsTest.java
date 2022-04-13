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
    private static final String NODE_DETAILS_MAINNET = "NodeDetails{currentEpoch='19', nodeListAddress='0xf20336e16B5182637f09821c27BDe29b0AFcfe80', torusNodeEndpoints=[https://torus-19.torusnode.com/jrpc, https://torus-node.ens.domains/jrpc, https://torus-node.matic.network/jrpc, https://torus.zilliqa.network/jrpc, https://torus-mainnet.cosmos.network/jrpc, https://torus2.etherscan.com/jrpc, https://torus-node-v2.skalelabs.com/jrpc, https://torus-node.binancex.dev/jrpc, https://torusnode.ont.io/jrpc], torusIndexes=null, torusNodePub=[TorusNodePub{X='bbe83c64177c3775550e6ba6ac2bc059f6847d644c9e4894e42c60d7974d8c2b', Y='82b49a7caf70def38cdad2740af45c1e4f969650105c5019a29bb18b21a9acb5'}, TorusNodePub{X='c208cac4ef9a47d386097a9c915b28e9cb89213abee8d26a17198ee261201b0d', Y='c7db2fe4631109f40833de9dc78d07e35706549ee48fa557b33e4e75e1047873'}, TorusNodePub{X='ca1766bb426d4ca5582818a0c5439d560ea64f5baa060793ab29dd3d0ceacfe', Y='d46c1d08c40e1306e1bca328c2287b8268166b11a1ba4b8442ea2ad0c5e32152'}, TorusNodePub{X='c3934dd2f6f4b3d2e1e398cc501e143c1e1a381b52feb6d1525af34d16253768', Y='71f5141a5035799099f5ea3e241e66946bc55dc857ac3bd7d6fcdb8dcd3eeeef'}, TorusNodePub{X='22e66f1929631d00bf026227581597f085fd94fd952fc0dca9f0833398b5c064', Y='6088b3912e10a1e9d50355a609c10db7d188f16a2e2fd7357e51bf4f6a74f0a1'}, TorusNodePub{X='9dc9fa410f3ce9eb70df70cdea00a49f2c4cc7a31c08c0dab5f863ed35ff5139', Y='627a291cb87a75c61da3f65d6818e1e05e360217179817ed27e8c73bca7ec122'}, TorusNodePub{X='118b9fc07e97b096d899b9f6658463ce6a8caa64038e37fc969df4e6023dd8c6', Y='baf9fa4e51770f4796ea165dd03a769b8606681a38954a0a92c4cbffd6609ce9'}, TorusNodePub{X='8a6d8b925da15a273dec3d8f8395ec35cd6878f274b2b180e4e106999db64043', Y='96f67f870c157743da0b1eb84d89bf30500d74dc84c11f501ee1cb013acc8c46'}, TorusNodePub{X='39cecb62e863729f572f7dfc46c24867981bf04bb405fed0df39e33984bfade5', Y='61c2364434012e68a2be2e9952805037e52629d7762fafc8e10e9fb5bad8f790'}], updated=true}";
    private static final String NODE_DETAILS_ROPSTEN = "NodeDetails{currentEpoch='15', nodeListAddress='0x6258c9d6c12ed3edda59a1a6527e469517744aa7', torusNodeEndpoints=[https://teal-15-1.torusnode.com/jrpc, https://teal-15-3.torusnode.com/jrpc, https://teal-15-4.torusnode.com/jrpc, https://teal-15-5.torusnode.com/jrpc, https://teal-15-2.torusnode.com/jrpc], torusIndexes=null, torusNodePub=[TorusNodePub{X='1363aad8868cacd7f8946c590325cd463106fb3731f08811ab4302d2deae35c3', Y='d77eebe5cdf466b475ec892d5b4cffbe0c1670525debbd97eee6dae2f87a7cbe'}, TorusNodePub{X='7c8cc521c48690f016bea593f67f88ad24f447dd6c31bbab541e59e207bf029d', Y='b359f0a82608db2e06b953b36d0c9a473a00458117ca32a5b0f4563a7d539636'}, TorusNodePub{X='8a86543ca17df5687719e2549caa024cf17fe0361e119e741eaee668f8dd0a6f', Y='9cdb254ff915a76950d6d13d78ef054d5d0dc34e2908c00bb009a6e4da701891'}, TorusNodePub{X='25a98d9ae006aed1d77e81d58be8f67193d13d01a9888e2923841894f4b0bf9c', Y='f63d40df480dacf68922004ed36dbab9e2969181b047730a5ce0797fb6958249'}, TorusNodePub{X='d908f41f8e06324a8a7abcf702adb6a273ce3ae63d86a3d22723e1bbf1438c9a', Y='f977530b3ec0e525438c72d1e768380cbc5fb3b38a760ee925053b2e169428ce'}], updated=true}";
    private static final String NODE_DETAILS_POLYGON = "NodeDetails{currentEpoch='1', nodeListAddress='0x9f072ba19b3370e512aa1b4bfcdaf97283168005', torusNodeEndpoints=[https://node-1.torus-cluster-15.com/jrpc, https://node-2.torus-cluster-15.com/jrpc, https://node-3.torus-cluster-15.com/jrpc, https://node-4.torus-cluster-15.com/jrpc, https://node-5.torus-cluster-15.com/jrpc], torusIndexes=null, torusNodePub=[TorusNodePub{X='1c0a4cafc99bb66b99121a1b07962eb9ad9ebd80435fc6adadc7c509182ee9af', Y='984fc27ed95cf6453d5c99c2a71f15be6888e5711b777e3d38d9fe40f5b6f68f'}, TorusNodePub{X='523a8ef7edd47f7c8641d325f5cb40a08c357b50742b9ccd634299fb1dffe49a', Y='abf9bea7690709fe6a057e129e8eef834a339b2e78d437bf13f4f737313c2bc6'}, TorusNodePub{X='80e15b937048457dd8be2479549a2f0e20018afbf0ae807c73456c6e3cca4484', Y='cd39ebe14b70f66fd222091df022d844b5982022528ce8f5caffb8034716c073'}, TorusNodePub{X='7062c853a921e2ed9d0658b5da41cbd17ed61b8a721145407dca1d91d6d23a1f', Y='ea8c064e165d4c703ce54c998bed387b169f9fe9d757c2c83aa2dbc7374c7909'}, TorusNodePub{X='edd14de3b36ad53c24b5fe761cc912f400172a6db1b57b39beb4913ba839897c', Y='2e9143d436d3b4bb9b9d15644282484d1e950c171c450e08332d6a772a6b095b'}], updated=true}";

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
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("hello@tor.us", "google").get();
        assertEquals(NODE_DETAILS_MAINNET, nodeDetails.toString());
        //System.out.println(nodeDetails);
    }

}