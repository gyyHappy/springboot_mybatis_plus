package com.gyy.springboot_mybatis_plus.test;

import com.gyy.springboot_mybatis_plus.entity.User;
import com.gyy.springboot_mybatis_plus.enums.SexEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author GYY
 * @date 2020/1/31 15:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {

    @Test
    public void testAR(){
        User user = new User();
        user.setId(1L);
        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testUpdateAll() {
        User user = new User();
        user.setAge(32);
        user.update(null);
    }

    @Test
    public void testOptimistic(){
        User user = new User();
        user.setId(1L);
        user.setAge(22);
        User user1 = user.selectById();
        user.setVersion(user1.getVersion());
        user.updateById();
    }

    @Test
    public void testDelete(){
        User user = new User();
        user.setId(1L);
        user.deleteById();
    }

    @Test
    public void testInsertEnum(){
        User user = new User();
        user.setAge(11);
        user.setName("爱德华");
        user.setEmail("adh@qq.com");
        user.setSex(SexEnum.Man);
        user.insert();
    }
}
