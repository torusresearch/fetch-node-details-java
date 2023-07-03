package org.torusresearch.fetchnodedetails.types;

public enum TorusNetwork {
    LEGACY_MAINNET("mainnet"),
    LEGACY_TESTNET("testnet"),
    LEGACY_CYAN("cyan"),
    LEGACY_AQUA("aqua"),
    LEGACY_CELESTE("celeste"),
    SAPPHIRE_DEVNET("sapphire_devnet"),
    SAPPHIRE_TESTNET("sapphire_devnet"),
    SAPPHIRE_MAINNET("sapphire_mainnet");

    private final String val;

    TorusNetwork(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
