package com.gyy.springboot_mybatis_plus.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gyy.springboot_mybatis_plus.entity.User;
import com.gyy.springboot_mybatis_plus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GYY
 * @date 2019/12/16 20:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        System.out.println("------selectAll method test--------");
        List<User> userList = userMapper.selectList(null);
        for (User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(10);
        user.setEmail("4444444@qq.com");
        user.setName("吕布");
        //返回插入的结果
        int result = userMapper.insert(user);
        System.out.println(result);

        //自增的id会回填到user对象中
        System.out.println(user.getId());
    }

    @Test
    public void testUpdate1(){
        User user = new User();
        user.setId(1L);
        user.setName("郭靖");
        user.setAge(32);
        userMapper.updateById(user);
    }

    @Test
    public void testUpdate2(){
        User user = new User();
        user.setEmail("22222222@qq.com");
        user.setAge(32);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //第一个是数据库字段名字，后面是值
        queryWrapper.eq("name","Jack");
        userMapper.update(user,queryWrapper);
    }

    @Test
    public void testUpdate3(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",11).set("email","666666666666@qq.com")
                .eq("name","Tom");
        userMapper.update(null,wrapper);
    }
}
