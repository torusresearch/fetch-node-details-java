package org.torusresearch.torusutils.apis;

import org.torusresearch.torusutils.types.GetOrSetNonceResult;
import org.torusresearch.torusutils.types.JRPCResponse;
import org.torusresearch.torusutils.types.VerifierLookupResponse;

import java.util.List;

public class KeyLookupResult {
    private final String keyResult;
    private List<Integer> nodeIndexes;
    private final String errResult;
    private JRPCResponse<VerifierLookupResponse> errorResult;
    private GetOrSetNonceResult nonceResult;

    public KeyLookupResult(String _keyResult, String _errResult, List<Integer> nodeIndexes, GetOrSetNonceResult nonceResult) {
        keyResult = _keyResult;
        errResult = _errResult;
        this.nodeIndexes = nodeIndexes;
        this.nonceResult = nonceResult;
    }

    public KeyLookupResult(String _keyResult, String _errResult) {
        keyResult = _keyResult;
        errResult = _errResult;
    }

    public List<Integer> getNodeIndexes() {
        return nodeIndexes;
    }

    public GetOrSetNonceResult getNonceResult() {
        return nonceResult;
    }

    public String getKeyResult() {
        return keyResult;
    }

    public String getErrResult() {
        return errResult;
    }

}
