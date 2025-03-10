package site.hnfy258.RPCVer3.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * 初始化，主要负责序列化的编码解码， 需要解决netty的粘包问题
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;

    public NettyServerInitializer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast(new LengthFieldPrepender(4));

        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast((new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String s) throws ClassNotFoundException {
                return Class.forName(s);
            }
        })));
        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));
    }

}