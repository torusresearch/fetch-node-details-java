package org.torusresearch.fetchnodedetails.types;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class NodeDetails implements Serializable {
    private String currentEpoch;
    private String[] torusNodeEndpoints;
    private String[] torusNodeSSSEndpoints;
    private String[] torusNodeRSSEndpoints;
    private String[] torusNodeTSSEndpoints;
    private BigInteger[] torusIndexes;
    private TorusNodePub[] torusNodePub;
    private Boolean updated = false;

    public NodeDetails() {
    }

    public NodeDetails(String _currentEpoch, String[] _torusNodeEndpoints,
                       String[] _torusNodeSSSEndpoints, String[] _torusNodeRSSEndpoints, String[] _torusNodeTSSEndpoints,
                       BigInteger[] _torusIndexes, TorusNodePub[] _torusNodePub, Boolean _updated) {
        this.currentEpoch = _currentEpoch;
        this.torusNodeEndpoints = _torusNodeEndpoints.clone();
        this.torusNodeSSSEndpoints = _torusNodeSSSEndpoints.clone();
        this.torusNodeRSSEndpoints = _torusNodeRSSEndpoints.clone();
        this.torusNodeTSSEndpoints = _torusNodeTSSEndpoints.clone();
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

    public String[] getTorusNodeSSSEndpoints() {
        return torusNodeSSSEndpoints;
    }

    public void setTorusNodeSSSEndpoints(String[] torusNodeSSSEndpoints) {
        this.torusNodeSSSEndpoints = torusNodeSSSEndpoints;
    }

    public String[] getTorusNodeRSSEndpoints() {
        return torusNodeRSSEndpoints;
    }

    public void setTorusNodeRSSEndpoints(String[] torusNodeRSSEndpoints) {
        this.torusNodeRSSEndpoints = torusNodeRSSEndpoints;
    }

    public String[] getTorusNodeTSSEndpoints() {
        return torusNodeTSSEndpoints;
    }

    public void setTorusNodeTSSEndpoints(String[] torusNodeTSSEndpoints) {
        this.torusNodeTSSEndpoints = torusNodeTSSEndpoints;
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
                ", torusNodeEndpoints=" + Arrays.toString(torusNodeEndpoints) +
                ", torusNodeSSSEndpoints=" + Arrays.toString(torusNodeSSSEndpoints) +
                ", torusNodeRSSEndpoints=" + Arrays.toString(torusNodeRSSEndpoints) +
                ", torusNodeTSSEndpoints=" + Arrays.toString(torusNodeTSSEndpoints) +
                ", torusIndexes=" + Arrays.toString(torusIndexes) +
                ", torusNodePub=" + Arrays.toString(torusNodePub) +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodeDetails)) return false;
        NodeDetails that = (NodeDetails) o;
        return Objects.equals(getCurrentEpoch(), that.getCurrentEpoch()) && Arrays.equals(getTorusNodeEndpoints(), that.getTorusNodeEndpoints())
                && Arrays.equals(getTorusNodeSSSEndpoints(), that.getTorusNodeSSSEndpoints())
                && Arrays.equals(getTorusNodeRSSEndpoints(), that.getTorusNodeRSSEndpoints())
                && Arrays.equals(getTorusNodeTSSEndpoints(), that.getTorusNodeTSSEndpoints())
                && Arrays.equals(getTorusIndexes(), that.getTorusIndexes()) &&
                Arrays.equals(getTorusNodePub(), that.getTorusNodePub()) && Objects.equals(getUpdated(), that.getUpdated());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getCurrentEpoch(), getUpdated());
        result = 31 * result + Arrays.hashCode(getTorusNodeEndpoints());
        result = 31 * result + Arrays.hashCode(getTorusNodeSSSEndpoints());
        result = 31 * result + Arrays.hashCode(getTorusNodeRSSEndpoints());
        result = 31 * result + Arrays.hashCode(getTorusNodeTSSEndpoints());
        result = 31 * result + Arrays.hashCode(getTorusIndexes());
        result = 31 * result + Arrays.hashCode(getTorusNodePub());
        return result;
    }
}
