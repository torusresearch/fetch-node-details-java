package org.torusresearch.fetchnodedetails.types;

public enum EthereumNetwork {
    MAINNET("mainnet"),
    ROPSTEN("ropsten"),
    POLYGON("polygon-mainnet");

    private String val;

    EthereumNetwork(String s) {
        this.val = s;
    }

    public String toString() {
          return val;
    }
}
