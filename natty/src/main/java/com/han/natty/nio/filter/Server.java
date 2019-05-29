package com.han.natty.nio.filter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        SocketChannel channel = serverSocketChannel.bind(new InetSocketAddress(8088)).accept();


        Socket socket = channel.socket();




    }
}
