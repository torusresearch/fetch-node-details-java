package org.torusresearch.fetchnodedetails.types;

public final class NodeInfo {
    private final String declaredIp;
    private final String pubKx;
    private final String pubKy;

    public NodeInfo(String _declaredIp, String _pubKx, String _pubKy) {
        this.declaredIp = _declaredIp;
        this.pubKx = _pubKx;
        this.pubKy = _pubKy;
    }


    public String getDeclaredIp() {
        return declaredIp;
    }

    public String getPubKx() {
        return pubKx;
    }

    public String getPubKy() {
        return pubKy;
    }


    @Override
    public String toString() {
        return "NodeInfo{" +
                ", position='" + declaredIp + '\'' +
                ", pubKx='" + pubKx + '\'' +
                ", pubKy='" + pubKy + '\'' +
                '}';
    }
}
