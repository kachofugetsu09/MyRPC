package site.hnfy258.RPCVer3.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import site.hnfy258.RPCVer3.common.RPCRequest;
import site.hnfy258.RPCVer3.common.RPCResponse;

import java.lang.reflect.Method;

public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {
    private ServiceProvider serviceProvider;
    public NettyRPCServerHandler(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest rpcRequest) throws Exception {
        RPCResponse response = getResponse(rpcRequest);
        channelHandlerContext.writeAndFlush(response);
        channelHandlerContext.close();
    }

    private RPCResponse getResponse(RPCRequest rpcRequest) {
        String interfaceName = rpcRequest.getInterfaceName();
        Object service = serviceProvider.getService(interfaceName);
        Method method = null;
        try{
            method = service.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
            Object invoke = method.invoke(service, rpcRequest.getParameters());
            return RPCResponse.success(invoke);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("服务端处理请求失败");
            return RPCResponse.fail();
        }
    }
}
