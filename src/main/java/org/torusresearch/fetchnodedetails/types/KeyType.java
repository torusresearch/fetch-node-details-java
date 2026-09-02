package org.torusresearch.fetchnodedetails.types;

public enum KeyType {
    SECP256K1("secp256k1"),
    ED25519("ed25519");

    private final String val;

    KeyType(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
