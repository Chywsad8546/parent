package com.toutiao.officedict.domain.mapping;

import com.toutiao.officedict.common.util.officedict.CityMap;
import lombok.Data;

/**
 * @author WuShoulei on 2018/7/17
 */
@Data
public class NotMatchModel {

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
     * 所属城市
     */
    private String city;

    /**
     * 城市Id
     */
    private Integer cityId;

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        this.city = CityMap.getCityCNName(cityId);
    }

    private Integer status;
}
