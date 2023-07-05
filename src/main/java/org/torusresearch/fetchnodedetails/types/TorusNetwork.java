package org.torusresearch.fetchnodedetails.types;

public enum TorusNetwork {
    MAINNET("mainnet"),
    TESTNET("testnet"),
    CYAN("cyan"),
    AQUA("aqua"),
    CELESTE("celeste"),
    SAPPHIRE_DEVNET("sapphire_devnet"),
    SAPPHIRE_TESTNET("sapphire_testnet"),
    SAPPHIRE_MAINNET("sapphire_mainnet");

    private final String val;

    TorusNetwork(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
