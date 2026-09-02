package org.torusresearch.fetchnodedetails.types;

public enum BuildEnv {
    PRODUCTION("production"),
    DEVELOPMENT("development"),
    STAGING("staging"),
    TESTING("testing");

    private final String val;

    BuildEnv(String s) {
        this.val = s;
    }

    public String toString() {
        return val;
    }
}
