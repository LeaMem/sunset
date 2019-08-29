package com.han.natty.distribute.entity;

import lombok.Data;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

import java.util.concurrent.TimeUnit;

@Data
@REntity
public class Student {

    @RId
    private String number;

    @RIndex
    private String name;

    @RIndex
    private Integer age;

    public void addAge(RedissonClient redissonClient) {

        RLock lock = redissonClient.getLock(getNumber());


        try {
            lock.lock(40L, TimeUnit.SECONDS);
            int age = getAge();
            age++;
            setAge(age);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}
