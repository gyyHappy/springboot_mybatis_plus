package com.gyy.springboot_mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author GYY
 * @date 2019/12/16 20:26
 */
@Data
public class User {
    /**
     * 让id是自增的
     */
    @TableId(type = IdType.AUTO)
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

}
