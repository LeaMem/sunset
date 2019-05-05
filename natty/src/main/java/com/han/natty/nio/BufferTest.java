package com.han.natty.nio;


import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class BufferTest {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        SocketChannel accept = serverSocketChannel.bind(new InetSocketAddress(8099)).accept();


    }

}

