package org.torusresearch.fetchnodedetails.types;

public enum Web3AuthNetwork {
    MAINNET("mainnet"),
    TESTNET("testnet"),
    CYAN("cyan"),
    AQUA("aqua"),
    CELESTE("celeste"),
    SAPPHIRE_DEVNET("sapphire_devnet"),
    SAPPHIRE_MAINNET("sapphire_mainnet");

    private final String val;

    Web3AuthNetwork(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
