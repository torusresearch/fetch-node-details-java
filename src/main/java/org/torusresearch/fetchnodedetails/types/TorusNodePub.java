package org.torusresearch.fetchnodedetails.types;

public final class TorusNodePub implements Cloneable {
    private final String X;
    private final String Y;

    public TorusNodePub(String _X, String _Y) {
        this.X = _X;
        this.Y = _Y;
    }

    public String getX() {
        return X;
    }

    public String getY() {
        return Y;
    }
}
