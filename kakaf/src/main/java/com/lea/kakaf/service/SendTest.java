package com.lea.kakaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class SendTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String message, String topic){

        ListenableFuture send = kafkaTemplate.send(topic, message);
        send.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(Object result) {
                System.out.println("成功发射: " + result);
            }
        });
    }

}
