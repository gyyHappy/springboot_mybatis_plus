package com.gyy.springboot_mybatis_plus.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.springboot_mybatis_plus.entity.User;
import com.gyy.springboot_mybatis_plus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public void testSelect() {
        System.out.println("------selectAll method test--------");
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(10);
        user.setEmail("4444444@qq.com");
        user.setName("流弊");
        //返回插入的结果
        int result = userMapper.insert(user);
        System.out.println(result);

        //自增的id会回填到user对象中
        System.out.println(user.getId());
    }

    @Test
    public void testUpdate1() {
        User user = new User();
        user.setId(1L);
        user.setName("郭靖");
        user.setAge(32);
        userMapper.updateById(user);
    }

    @Test
    public void testUpdate2() {
        User user = new User();
        user.setEmail("22222222@qq.com");
        user.setAge(32);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //第一个是数据库字段名字，后面是值
        queryWrapper.eq("name", "Jack");
        userMapper.update(user, queryWrapper);
    }

    @Test
    public void testUpdate3() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age", 11).set("email", "666666666666@qq.com")
                .eq("name", "Tom");
        userMapper.update(null, wrapper);
    }

    @Test
    public void testDelete1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //第一个是数据库字段名字，后面是值
        wrapper.eq("name", "Jack");
        userMapper.delete(wrapper);
    }

    @Test
    public void testDelete3() {
        //推荐用法
        User user = new User();
        user.setName("吕布");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int delete = userMapper.delete(wrapper);
        System.out.println(delete);
    }

    @Test
    public void testDeleteBatchIds() {
        //通过id批量删除数据
        int i = userMapper.deleteBatchIds(Arrays.asList(7L, 8L));
        System.out.println(i);
    }

    @Test
    public void testSelectBatchIds() {
        //通过id批量查询数据
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Jack");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过名字模糊查询
        wrapper.like("name","a");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询年龄大于20岁的条数
        wrapper.gt("age",20);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    public void testSelectPage(){
        //第一个参数是当前页，第二个参数是每页条数
        Page<User> page = new Page<>(1,1);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","a");
        Page<User> selectPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数: " + selectPage.getTotal());
        System.out.println("数据总页数: " + selectPage.getPages());
        System.out.println("当前页数:  " + selectPage.getCurrent());

        List<User> records = selectPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","吕布").or().eq("age","30");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


}

