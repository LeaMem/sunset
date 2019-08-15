package com.han.natty.learn.seecho.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class SecondEchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() +
                " -> Server: " + msg.toString());

        var toS = msg.toString();

        String message = "dhm: " + msg.toString() + "\n";
        final ChannelFuture future = ctx.write("dhm: " + msg.toString() + "\n");

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {

//                System.out.println(future.get());

                if(future.isSuccess()){
                    System.out.println("chenggong");
                }else{
                    future.get();
                    System.out.println("shibai");
                }
            }
        });

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server read complete");


        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
