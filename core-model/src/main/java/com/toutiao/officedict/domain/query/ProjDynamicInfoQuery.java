package com.toutiao.officedict.domain.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 18710 on 2018/4/17.
 */
@Data
public class ProjDynamicInfoQuery {
    Integer id;
    Integer type;
    Date time;
    String title;
    String detail;
    Integer creatorId;
    Date createTime;
    Integer updaterId;
    Date updateTime;
    String linkUrl;
    Integer isDel = 0;
    @NotNull
    Integer newcode;

    Integer cityId;

    /**
     * 当前页码，默认值为 1
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

}
