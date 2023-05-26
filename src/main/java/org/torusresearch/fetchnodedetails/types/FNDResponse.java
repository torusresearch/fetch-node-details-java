package org.torusresearch.fetchnodedetails.types;

public class FNDResponse {
    private NodeDetails nodeDetails;
    private boolean success;

    public FNDResponse(NodeDetails nodeDetails, boolean success) {
        this.nodeDetails = nodeDetails;
        this.success = success;
    }

    public NodeDetails getNodeDetails() {
        return nodeDetails;
    }

    public void setNodeDetails(NodeDetails nodeDetails) {
        this.nodeDetails = nodeDetails;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
