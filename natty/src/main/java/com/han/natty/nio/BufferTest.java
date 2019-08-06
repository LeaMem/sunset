package com.han.natty.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.IntBuffer;

public class BufferTest {


    public static void main(String[] args) {

        Buffer buffer = IntBuffer.allocate(20);

        buffer.limit(15);

//        for(int i = 0; i < buffer.limit(); i++){
//            buffer.s
//        }

    }
}
