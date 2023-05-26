package org.torusresearch.fetchnodedetailstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.torusresearch.fetchnodedetails.FetchNodeDetails;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.TorusNetwork;
import org.torusresearch.fetchnodedetailstest.config.Config;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

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
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.AQUA, nodeDetails);
    }

    @DisplayName("Gets the Node details for Celeste")
    @Test
    public void shouldGetNodeDetailsCeleste() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.CELESTE, FetchNodeDetails.PROXY_ADDRESS_CELESTE);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.CELESTE, nodeDetails);
    }

    @DisplayName("Gets the Node details for SapphireDevnet")
    @Test
    public void shouldGetNodeDetailsSapphireDevnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.SAPPHIRE_DEVNET, FetchNodeDetails.PROXY_ADDRESS_CELESTE);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.SAPPHIRE_DEVNET, nodeDetails);
    }

    @DisplayName("Gets the Node details for SapphireTestnet")
    @Test
    public void shouldGetNodeDetailsSapphireTestnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.SAPPHIRE_TESTNET, FetchNodeDetails.PROXY_ADDRESS_CELESTE);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.SAPPHIRE_TESTNET, nodeDetails);
    }

    @DisplayName("Gets the Node details for SapphireMainnet")
    @Test
    public void shouldGetNodeDetailsSapphireMainnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(TorusNetwork.SAPPHIRE_MAINNET, FetchNodeDetails.PROXY_ADDRESS_CELESTE);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.SAPPHIRE_MAINNET, nodeDetails);
    }
}