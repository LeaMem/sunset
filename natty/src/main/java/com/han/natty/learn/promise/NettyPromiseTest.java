package com.han.natty.learn.promise;

import io.netty.util.concurrent.*;

import java.util.concurrent.TimeUnit;

public class NettyPromiseTest {


    public static void main(String[] args) {

        //创建线程池
        EventExecutor executor = new DefaultEventExecutor();

        //创建 DefaultPromise 实例
        Promise promise = new DefaultPromise(executor);

        //下面给这个 promise 添加两个 listener
        promise.addListener((GenericFutureListener<Future<Integer>>) future -> {
            if (future.isSuccess()) {
                System.out.println("回调事件1的线程池:" + Thread.currentThread().getName());
                System.out.println("任务结束，结果: " + future.get());
            } else {
                System.out.println("任务失败，异常: " + future.cause());
            }
        }).addListener((GenericFutureListener<Future<Integer>>) future -> {
            System.out.println("回调事件2的线程池:" + Thread.currentThread().getName());
            System.out.println("任务结束，balabala");
        });

        //提交任务到线程池，五秒后执行，设置 promise 的结果
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
                    System.out.println("Task run in thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //set result of promise
                promise.setSuccess(123456);
            }
        });

        // block main thread until the listeners done
        try{
            promise.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
