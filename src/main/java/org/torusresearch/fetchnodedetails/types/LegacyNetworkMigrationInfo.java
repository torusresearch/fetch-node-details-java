package org.torusresearch.fetchnodedetails.types;

public class LegacyNetworkMigrationInfo {

    private Boolean migrationCompleted = false;
    private String networkIdentifier;
    private Web3AuthNetwork networkMigratedTo;


    public LegacyNetworkMigrationInfo(Boolean migrationCompleted, String networkIdentifier, Web3AuthNetwork networkMigratedTo) {
        this.migrationCompleted = migrationCompleted;
        this.networkIdentifier = networkIdentifier;
        this.networkMigratedTo = networkMigratedTo;
    }

    public Boolean getMigrationCompleted() {
        return migrationCompleted;
    }

    public String getNetworkIdentifier() {
        return networkIdentifier;
    }

    public Web3AuthNetwork getNetworkMigratedTo() {
        return networkMigratedTo;
    }
}
