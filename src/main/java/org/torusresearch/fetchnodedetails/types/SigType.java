package org.torusresearch.fetchnodedetails.types;

public enum SigType {
    ECDSA_SECP256K1("ecdsa-secp256k1"),
    ED25519("ed25519"),
    BIP340("bip340");

    private final String val;

    SigType(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
