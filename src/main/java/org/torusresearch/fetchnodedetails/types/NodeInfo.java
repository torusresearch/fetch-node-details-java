package org.torusresearch.fetchnodedetails.types;

public final class NodeInfo {
    private final String declaredIp;
    private final String position;
    private final String pubKx;
    private final String pubKy;
    private final String tmP2PListenAddress;
    private final String p2pListenAddress;

    public NodeInfo(String _declaredIp, String _position, String _pubKx, String _pubKy, String _tmP2PListenAddress, String _p2pListenAddress) {
        this.declaredIp = _declaredIp;
        this.position = _position;
        this.pubKx = _pubKx;
        this.pubKy = _pubKy;
        this.tmP2PListenAddress = _tmP2PListenAddress;
        this.p2pListenAddress = _p2pListenAddress;
    }

    public String getDeclaredIp() {
        return declaredIp;
    }

    public String getP2pListenAddress() {
        return p2pListenAddress;
    }

    public String getPosition() {
        return position;
    }

    public String getPubKx() {
        return pubKx;
    }

    public String getPubKy() {
        return pubKy;
    }

    public String getTmP2PListenAddress() {
        return tmP2PListenAddress;
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "declaredIp='" + declaredIp + '\'' +
                ", position='" + position + '\'' +
                ", pubKx='" + pubKx + '\'' +
                ", pubKy='" + pubKy + '\'' +
                ", tmP2PListenAddress='" + tmP2PListenAddress + '\'' +
                ", p2pListenAddress='" + p2pListenAddress + '\'' +
                '}';
    }
}
