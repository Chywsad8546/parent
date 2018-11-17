package com.toutiao.officedict.common.authentication;

import lombok.Data;

/**
 * zhangjinglei 2017/9/11 下午2:42
 */
@Data
public class AuthDataFilter {
    private Integer city;
    private Integer district;
    private Integer circle;
    private Integer project;
    private Integer building;
}
