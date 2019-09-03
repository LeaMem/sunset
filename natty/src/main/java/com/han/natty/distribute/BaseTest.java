package com.han.natty.distribute;

import com.han.natty.distribute.entity.Student;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;

public class BaseTest {

     RedissonClient redissonClient;

    public BaseTest() {
        this.redissonClient = new RedisClient().getRedissonClient();
    }


    public void delete(String number){
        RLiveObjectService service = redissonClient.getLiveObjectService();
        service.delete(Student.class, number);
    }

    public void register(Student student){
        RLiveObjectService service = redissonClient.getLiveObjectService();
        service.persist(student);
    }


    public Student find(String number){
        RLiveObjectService service = redissonClient.getLiveObjectService();
        Student student = service.get(Student.class, number);
        return student;
    }

    public Student modifyStu(String number){
        RLiveObjectService service = redissonClient.getLiveObjectService();
        Student s = service.get(Student.class, number);
        s.addAge(redissonClient);
        return s;
    }


//    public static void main(String[] args) {
//        new BaseTest().delete("1");
//    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setNumber("1");
        student.setName("ding");
        student.setAge(12);

        BaseTest test = new BaseTest();
        test.register(student);
    }
}
