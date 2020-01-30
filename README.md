# springboot_mybatis_plus
# Mybatis-Plus + SpringBoot

## 环境搭建

引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.gyy</groupId>
    <artifactId>springboot_mybatis_plus</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot_mybatis_plus</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.0</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.10</version>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.18</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13-beta-3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>



    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

application.yml

```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mp?serverTimezone=UTC
    username: root
    password: 123456

# Logger Config
logging:
  level:
    com.baomidou.mybatisplus.samples.quickstart: debug
```

User.java

```java
@Data
public class User {
    /**
     让id是自增的
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

UserMapper.java

```java
public interface UserMapper extends BaseMapper<User> {
}
```

## 测试

创建好数据库和表，在其中插入数据

SampleTest.java

```java
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
```

## @TableField

| 注解参数                     | 作用                                                         |
| ---------------------------- | ------------------------------------------------------------ |
| @TableField(select = false)  | 加上这个注解的字段，在查询时返回的结果将为null               |
| @TableField(value = "myAge") | 添加字段进数据库时指定名字                                   |
| @TableField(exist = false)   | 表明当前字段在数据库中并不存在，sql语句在添加时不会将其写入sql中 |

## 3种Update方法

1、根据主键更新

```java
 @Test
    public void testUpdate1(){
        User user = new User();
        user.setId(1L);
        user.setName("郭靖");
        user.setAge(32);
        userMapper.updateById(user);
    }
```

2、QueryWrapper

```java
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
```

3、UpdateWrapper

```java
 @Test
    public void testUpdate3(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",11).set("email","666666666666@qq.com")
                .eq("name","Tom");
        userMapper.update(null,wrapper);
    }
```

