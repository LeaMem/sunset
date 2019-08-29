package com.han.natty.distribute;

import com.han.natty.distribute.entity.Student;
import org.redisson.api.RedissonClient;

public class TestA extends BaseTest{


    public static void main(String[] args) {
        TestA testA = new TestA();
        Student student = testA.find("1");
        System.out.println(student.getAge());

    }


}
