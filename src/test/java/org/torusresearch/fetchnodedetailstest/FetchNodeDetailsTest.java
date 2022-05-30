package org.torusresearch.fetchnodedetailstest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.torusresearch.fetchnodedetails.FetchNodeDetails;
import org.torusresearch.fetchnodedetails.types.EthereumNetwork;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetailstest.config.Config;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FetchNodeDetailsTest {
    FetchNodeDetails fetchNodeDetails;

    @DisplayName("Gets the Node details for Mainnet")
    @Test
    public void shouldGetNodeDetailsMainnet() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        fetchNodeDetails = new FetchNodeDetails();
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.MAINNET, nodeDetails);
    }

    @DisplayName("Gets the Node details for Ropsten")
    @Test
    public void shouldGetNodeDetailsRopsten() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        fetchNodeDetails = new FetchNodeDetails(EthereumNetwork.ROPSTEN, FetchNodeDetails.PROXY_ADDRESS_ROPSTEN);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.ROPSTEN, nodeDetails);
    }

    @DisplayName("Gets the Node details for Polygon")
    @Test
    public void shouldGetNodeDetailsPolygon() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        fetchNodeDetails = new FetchNodeDetails(EthereumNetwork.POLYGON, FetchNodeDetails.PROXY_ADDRESS_POLYGON);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.POLYGON, nodeDetails);
    }

}