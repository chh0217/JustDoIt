package ch.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author 云岩
 * @description
 * @date 2020/8/6 8:49 上午
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 1000000; i++) {
//            ByteBuf data = ctx.alloc().buffer(4);
            System.out.println(i);
            ctx.writeAndFlush("我来了");
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}
