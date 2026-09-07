package org.torusresearch.fetchnodedetails.types;

public enum LegacyNetwork {
    MAINNET("mainnet"),
    TESTNET("testnet"),
    CYAN("cyan"),
    AQUA("aqua"),
    CELESTE("celeste");

    private final String val;

    LegacyNetwork(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
