package com.toutiao.officedict.dao.entity.mapping;

import lombok.Data;

import java.util.Date;

/**
 * @author 武守磊
 */
@Data
public class HouseCommunityNotMatch {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 导入方小区ID
     */
    private String communityId;

    /**
     * 导入方小区名称
     */
    private String communityName;

    /**
     * 所属公司
     */
    private String ofCompany;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人ID
     */
    private String creatorId;

    /**
     * 更新人ID
     */
    private String updaterId;

    /**
     * 城市Id
     */
    private Integer cityId;

    private Integer status;
}