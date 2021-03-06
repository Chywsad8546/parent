package com.toutiao.officedict.api.chance.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 18710 on 2018/4/17.
 */
@Data
public class ProjDynamicInfoRequest {
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
    Integer isDel;
    @NotNull
    Integer newcode;
}
