package org.torusresearch.fetchnodedetails.types;

public class LegacyNetworkMigrationInfo {

    private Boolean migrationCompleted = false;
    private String networkIdentifier;
    private TorusNetwork networkMigratedTo;


    public LegacyNetworkMigrationInfo(Boolean migrationCompleted, String networkIdentifier, TorusNetwork networkMigratedTo) {
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

    public TorusNetwork getNetworkMigratedTo() {
        return networkMigratedTo;
    }
}
