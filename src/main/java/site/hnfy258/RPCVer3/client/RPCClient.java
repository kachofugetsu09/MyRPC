package site.hnfy258.RPCVer3.client;


import site.hnfy258.RPCVer3.common.RPCRequest;
import site.hnfy258.RPCVer3.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}