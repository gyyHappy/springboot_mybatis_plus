package com.gyy.springboot_mybatis_plus.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author GYY
 * @date 2020/2/1 15:00
 */

public enum  SexEnum implements IEnum<Integer> {

    /**
     * 男
     */
    Man(1,"男"),

    /**
     * 女
     */
    WOMAN(2,"女");

    private int value;

    private String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
