package org.torusresearch.fetchnodedetailstest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.torusresearch.fetchnodedetails.FetchNodeDetails;
import org.torusresearch.fetchnodedetails.types.TorusNetwork;
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

    @DisplayName("Gets the Node details for Testnet")
    @Test
    public void shouldGetNodeDetailsRopsten() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.TESTNET, FetchNodeDetails.PROXY_ADDRESS_TESTNET);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.TESTNET, nodeDetails);
    }

    @DisplayName("Gets the Node details for Cyan")
    @Test
    public void shouldGetNodeDetailsCyan() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.CYAN, FetchNodeDetails.PROXY_ADDRESS_CYAN);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.CYAN, nodeDetails);
    }

    @DisplayName("Gets the Node details for Aqua")
    @Test
    public void shouldGetNodeDetailsAqua() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.AQUA, FetchNodeDetails.PROXY_ADDRESS_AQUA);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("glipandroid", "hello@tor.us").get();
        assertEquals(Config.AQUA, nodeDetails);
    }

}