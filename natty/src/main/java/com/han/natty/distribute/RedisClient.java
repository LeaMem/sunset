package com.han.natty.distribute;

import com.han.natty.distribute.entity.Person;
import com.han.natty.distribute.entity.Student;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

@Data
public class RedisClient {

    private RedissonClient redissonClient;

    public RedisClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.40.14:6379")
                .setDatabase(13);
//        config.setCodec(JsonJacksonCodec.INSTANCE);

        this.redissonClient = Redisson.create(config);
    }

    public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.40.14:6379")
                .setDatabase(13);

        RedissonClient redissonClient = Redisson.create(config);


        Person person = new Person();
        person.setAge(24);
        person.setName("ding");

        RBucket<Person> personRBucket = redissonClient.getBucket("person");

        System.out.println(personRBucket.get() == null);

        personRBucket.set(person, 20L, TimeUnit.SECONDS);


//
//        bucket.set(student);
    }
}
