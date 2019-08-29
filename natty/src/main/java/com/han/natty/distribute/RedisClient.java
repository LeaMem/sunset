package com.han.natty.distribute;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

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
}
