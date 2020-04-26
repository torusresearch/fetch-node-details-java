package org.torusresearch.fetchnodedetails.types;

import java.math.BigInteger;
import java.util.Arrays;

public class NodeDetails {
    private String currentEpoch;
    private String nodeListAddress;
    private String[] torusNodeEndpoints;
    private BigInteger[] torusIndexes;
    private TorusNodePub[] torusNodePub;
    private Boolean updated = false;

    public NodeDetails() {
    }

    public NodeDetails(String _currentEpoch, String _nodeListAddress, String[] _torusNodeEndpoints, BigInteger[] _torusIndexes, TorusNodePub[] _torusNodePub, Boolean _updated) {
        this.currentEpoch = _currentEpoch;
        this.nodeListAddress = _nodeListAddress;
        this.torusNodeEndpoints = _torusNodeEndpoints.clone();
        this.torusIndexes = _torusIndexes.clone();
        this.torusNodePub = _torusNodePub.clone();
        this.updated = _updated;
    }

    public BigInteger[] getTorusIndexes() {
        return torusIndexes.clone();
    }

    public void setTorusIndexes(BigInteger[] torusIndexes) {
        this.torusIndexes = torusIndexes.clone();
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public String getCurrentEpoch() {
        return currentEpoch;
    }

    public void setCurrentEpoch(String currentEpoch) {
        this.currentEpoch = currentEpoch;
    }

    public String getNodeListAddress() {
        return nodeListAddress;
    }

    public void setNodeListAddress(String nodeListAddress) {
        this.nodeListAddress = nodeListAddress;
    }

    public String[] getTorusNodeEndpoints() {
        return torusNodeEndpoints.clone();
    }

    public void setTorusNodeEndpoints(String[] torusNodeEndpoints) {
        this.torusNodeEndpoints = torusNodeEndpoints.clone();
    }

    public TorusNodePub[] getTorusNodePub() {
        return torusNodePub.clone();
    }

    public void setTorusNodePub(TorusNodePub[] torusNodePub) {
        this.torusNodePub = torusNodePub.clone();
    }

    @Override
    public String toString() {
        return "NodeDetails{" +
                "currentEpoch='" + currentEpoch + '\'' +
                ", nodeListAddress='" + nodeListAddress + '\'' +
                ", torusNodeEndpoints=" + Arrays.toString(torusNodeEndpoints) +
                ", torusIndexes=" + Arrays.toString(torusIndexes) +
                ", torusNodePub=" + Arrays.toString(torusNodePub) +
                ", updated=" + updated +
                '}';
    }
}
