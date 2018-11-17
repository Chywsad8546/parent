package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.util.Date;

/**
 * @author  18710 on 2018/4/11.
 */
@Data
public class RelationOfMaiTian {
    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 楼盘名id
     */
    private String communityId;

    /**
     * 楼盘名
     */
    private String communityName;

    /**
     * 麦田Id
     */
    private String communityDistrict;

    private String communityBizcircle;

    private String communityAddress;

    private  String company;

    private Integer cityId;

    private Date createTime;

    private Date updateTime;

    /**
     * 当前页码，默认值为 1
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    private Integer newcode;

    private String projname;
}
