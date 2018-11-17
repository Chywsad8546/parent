package com.toutiao.officedict.api.chance.request.mapping;

import com.toutiao.officedict.common.util.officedict.CityCodeMap;
import com.toutiao.officedict.common.util.officedict.CityMap;
import lombok.Data;

/**
 * 楼盘未匹配请求
 * @author WuShoulei on 2018/7/16
 */
@Data
public class NotMatchRequest {

    /**
     * 所属城市
     */

    private  Integer id;

    private  String communityName;

    /**
     * 请求城市
     */
    private String city;

    /**
     * 请求城市编号
     */
    private Integer cityId;

    /**
     * 所属经纪公司
     */
    private String company;

    /**
     * 当前页码，默认值为 1
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * 对应关系处理状态
     */
    private Integer status = 0;

/*    public void setCity(String city) {
        this.city = city;
        this.cityId = CityCodeMap.getCityId(city);
    }*/
}
