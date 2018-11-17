package com.toutiao.officedict.common.commonmodel;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * zhangjinglei 2017/9/5 上午10:40
 *
 * 所有数据库表entity的基类
 *
 *      @额外作用：aop对insert和update做了拦截，验证insert和update的时候这几个字段的值有没有，没有就抛异常
 */
@Data
public class BaseEntity implements Serializable {
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.nash_building.create_at
     *
     * @mbggenerated
     */
    private Date createAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.nash_building.modifier
     *
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.nash_building.modify_at
     *
     * @mbggenerated
     */
    private Date modifyAt;

    private String modifierName;
}
