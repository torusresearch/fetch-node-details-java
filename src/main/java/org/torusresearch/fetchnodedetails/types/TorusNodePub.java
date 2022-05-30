package org.torusresearch.fetchnodedetails.types;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TorusNodePub)) return false;
        TorusNodePub that = (TorusNodePub) o;
        return Objects.equals(getX(), that.getX()) && Objects.equals(getY(), that.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
