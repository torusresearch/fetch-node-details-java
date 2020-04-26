package org.torusresearch.fetchnodedetails.types;

import java.util.Arrays;

public final class EpochInfo {
    private final String id;
    private final String n;
    private final String k;
    private final String t;
    private final String[] nodeList;
    private final String prevEpoch;
    private final String nextEpoch;

    public EpochInfo(String _id, String _n, String _k, String _t, String[] _nodeList, String _prevEpoch, String _nextEpoch) {
        this.id = _id;
        this.n = _n;
        this.k = _k;
        this.t = _t;
        this.nodeList = _nodeList.clone();
        this.prevEpoch = _prevEpoch;
        this.nextEpoch = _nextEpoch;
    }

    public String getId() {
        return id;
    }

    public String getK() {
        return k;
    }

    public String getN() {
        return n;
    }

    public String getNextEpoch() {
        return nextEpoch;
    }

    public String getPrevEpoch() {
        return prevEpoch;
    }

    public String getT() {
        return t;
    }

    public String[] getNodeList() {
        return nodeList.clone();
    }

    @Override
    public String toString() {
        return "EpochInfo{" +
                "id='" + id + '\'' +
                ", n='" + n + '\'' +
                ", k='" + k + '\'' +
                ", t='" + t + '\'' +
                ", nodeList=" + Arrays.toString(nodeList) +
                ", prevEpoch='" + prevEpoch + '\'' +
                ", nextEpoch='" + nextEpoch + '\'' +
                '}';
    }
}
