package org.torusresearch.fetchnodedetails.types;

public class TorusNodePub {
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

    @Override
    public String toString() {
        return "TorusNodePub{" +
                "X='" + X + '\'' +
                ", Y='" + Y + '\'' +
                '}';
    }
}
