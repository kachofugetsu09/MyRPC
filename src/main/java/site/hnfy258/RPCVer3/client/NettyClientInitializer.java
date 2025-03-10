package site.hnfy258.RPCVer3.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast((new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4)));
        channelPipeline.addLast(new LengthFieldPrepender(4));
        channelPipeline.addLast(new ObjectEncoder());
        channelPipeline.addLast((new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String s) throws ClassNotFoundException {
                return Class.forName(s);
            }
        })));

        channelPipeline.addLast(new NettyRPCClientHandler());
    }
}
