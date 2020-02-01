package com.gyy.springboot_mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.gyy.springboot_mybatis_plus.enums.SexEnum;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;

/**
 * @author GYY
 * @date 2019/12/16 20:26
 */
@Data
//@TableName(value = "tb_user")
public class User extends Model<User> {
    /**
     * 让id是自增的
     */
//    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *  @TableField(value = "myAge")
     *  添加字段进数据库时指定名字
     */
    private Integer age;

    /**
     * @TableField(exist = false)
     * 表明当前字段在数据库中并不存在，sql语句在添加时不会将其写入sql中
     */
    private String name;

    /**
     * @TableField(select = false)
     * 加上这个注解，查询数据时该字段将会返回null
     */
    private String email;

    @Version
    @TableField(fill = INSERT) //乐观锁字段
    private Integer version;


    @TableLogic //逻辑删除
    private Integer deleted;

    private SexEnum sex;
}
