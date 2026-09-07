package org.torusresearch.fetchnodedetails.types;

public class LegacyNetworkMigrationInfo {

    private String networkIdentifier;
    private Web3AuthNetwork networkMigratedTo;

    public LegacyNetworkMigrationInfo(String networkIdentifier, Web3AuthNetwork networkMigratedTo) {
        this.networkIdentifier = networkIdentifier;
        this.networkMigratedTo = networkMigratedTo;
    }

    public String getNetworkIdentifier() {
        return networkIdentifier;
    }

    public Web3AuthNetwork getNetworkMigratedTo() {
        return networkMigratedTo;
    }
}
