package org.torusresearch.fetchnodedetailstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.torusresearch.fetchnodedetails.FetchNodeDetails;
import org.torusresearch.fetchnodedetails.types.NodeDetails;
import org.torusresearch.fetchnodedetails.types.Utils;
import org.torusresearch.fetchnodedetails.types.Web3AuthNetwork;
import org.torusresearch.fetchnodedetailstest.config.Config;

import java.util.concurrent.ExecutionException;

class FetchNodeDetailsTest {
    FetchNodeDetails fetchNodeDetails;

    @DisplayName("Gets the Node details for Mainnet")
    @Test
    public void shouldGetNodeDetailsMainnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(Web3AuthNetwork.MAINNET);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.MAINNET, nodeDetails);
    }

    @DisplayName("Gets the Node details for Testnet")
    @Test
    public void shouldGetNodeDetailsTestnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(Web3AuthNetwork.TESTNET);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.TESTNET, nodeDetails);
    }

    @DisplayName("Gets the Node details for Cyan")
    @Test
    public void shouldGetNodeDetailsCyan() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(Web3AuthNetwork.CYAN);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.CYAN, nodeDetails);
    }

    @DisplayName("Gets the Node details for Aqua")
    @Test
    public void shouldGetNodeDetailsAqua() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(Web3AuthNetwork.AQUA);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.AQUA, nodeDetails);
    }

    @DisplayName("Gets the Node details for Celeste")
    @Test
    public void shouldGetNodeDetailsCeleste() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(Web3AuthNetwork.CELESTE);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.CELESTE, nodeDetails);
    }

    @DisplayName("Gets the Node details for SapphireDevnet")
    @Test
    public void shouldGetNodeDetailsSapphireDevnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(Web3AuthNetwork.SAPPHIRE_DEVNET);
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.SAPPHIRE_DEVNET, nodeDetails);
    }

    @DisplayName("Gets the Node details for SapphireMainnet")
    @Test
    public void shouldGetNodeDetailsSapphireMainnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails();
        NodeDetails nodeDetails = this.fetchNodeDetails.getNodeDetails("google", "hello@tor.us").get();
        assertEquals(Config.SAPPHIRE_MAINNET, nodeDetails);
    }

    @DisplayName("Gets the Metadata url for SapphireMainnet")
    @Test
    public void shouldGetMetadataUrlSapphireMainnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails();
        String metadataUrl = this.fetchNodeDetails.getMetadataUrl().get();
        assertEquals(Config.SAPPHIRE_MAINNET.getTorusNodeEndpoints()[0].replace("/sss/jrpc", "/metadata"), metadataUrl);
    }

    @DisplayName("Gets the Metadata url for Legacy Mainnet")
    @Test
    public void shouldGetMetadataUrlLegacyMainnet() throws ExecutionException, InterruptedException {
        fetchNodeDetails = new FetchNodeDetails(Web3AuthNetwork.MAINNET);
        String metadataUrl = this.fetchNodeDetails.getMetadataUrl().get();
        assertEquals(Utils.METADATA_MAP.get(Web3AuthNetwork.MAINNET), metadataUrl);
    }
}