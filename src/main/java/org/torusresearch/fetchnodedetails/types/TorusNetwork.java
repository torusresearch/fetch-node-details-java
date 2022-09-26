package org.torusresearch.fetchnodedetails.types;

public enum TorusNetwork {
    MAINNET("mainnet"),
    TESTNET("testnet"),
    CYAN("cyan"),
    AQUA("aqua");


    private final String val;

    TorusNetwork(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
