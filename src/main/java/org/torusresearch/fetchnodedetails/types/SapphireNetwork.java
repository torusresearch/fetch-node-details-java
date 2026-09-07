package org.torusresearch.fetchnodedetails.types;

public enum SapphireNetwork {
    SAPPHIRE_DEVNET("sapphire_devnet"),
    SAPPHIRE_MAINNET("sapphire_mainnet");

    private final String val;

    SapphireNetwork(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
