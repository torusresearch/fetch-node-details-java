package org.torusresearch.fetchnodedetails.types;

public enum TorusSapphireNetwork {
    SAPPHIRE_DEVNET("sapphire_devnet"),
    SAPPHIRE_TESTNET("sapphire_devnet"),
    SAPPHIRE_MAINNET("sapphire_mainnet");

    private final String val;

    TorusSapphireNetwork(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
