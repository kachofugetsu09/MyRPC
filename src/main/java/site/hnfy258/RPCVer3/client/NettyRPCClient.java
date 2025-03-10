package site.hnfy258.RPCVer3.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import site.hnfy258.RPCVer3.common.RPCRequest;
import site.hnfy258.RPCVer3.common.RPCResponse;

public class NettyRPCClient implements RPCClient{
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private String host;
    private int port;

    public NettyRPCClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    static{
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new NettyClientInitializer());
    }

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
            try{
                ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
                Channel channel = channelFuture.channel();

                channel.writeAndFlush(request);
                channel.closeFuture().sync();

                AttributeKey<RPCResponse> rpcResponse = AttributeKey.valueOf("RPCResponse");
                RPCResponse resepone = channel.attr(rpcResponse).get();
                return resepone;

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
    }
}
