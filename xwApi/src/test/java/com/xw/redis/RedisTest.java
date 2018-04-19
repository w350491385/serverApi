package com.xw.redis;

import com.xw.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdongbin on 2018/4/12.
 */
public class RedisTest extends TestBase {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisSet(){
        redisTemplate.opsForValue().set("name","jaco");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(" \n name is " + name);
    }

    @Test
    public void testRedisList(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
//        for (String str : list) {
            redisTemplate.opsForList().leftPushAll("list", list);
//        }
        List<String> tempList = (List<String>)redisTemplate.opsForList().range("list",0,-1);
        System.out.println(tempList);
    }
}
