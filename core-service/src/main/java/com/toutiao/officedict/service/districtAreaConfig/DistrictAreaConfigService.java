package com.toutiao.officedict.service.districtAreaConfig;

import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.dao.entity.officedict.District;
import com.toutiao.officedict.dao.entity.officedict.DistrictAreaConfig;
import com.toutiao.officedict.domain.districtArea.AreaWithDistrictID;
import com.toutiao.officedict.domain.districtArea.CascadeControlDomain;
import com.toutiao.officedict.domain.districtArea.DistrictAreaDomain;
import com.toutiao.officedict.domain.query.DistrictAreaConfigQuery;

import java.util.List;

public interface DistrictAreaConfigService {

    List<AreaWithDistrictID> findArea(Integer Cityid, Integer housetype);

    List<District> findDistrict(Integer cityid,Integer housetype);

    /**
     * 根据区县城市id和区县id查询区县关联商圈信息
     * @param districtAreaConfigQuery
     * @return
     */
    List<DistrictAreaDomain> findDistrictAreaConfig(DistrictAreaConfigQuery districtAreaConfigQuery);

    List<DistrictAreaConfig> getDistrictId(DistrictAreaConfigQuery districtAreaConfig);

    /**
     * 绑定区县商圈
     * @param areaIds
     * @param districtId
     * @param houseType
     * @return
     */
    int bindingDistrictArea(List<Integer> areaIds, Integer districtId, Integer houseType);

    /**
     * 解绑区县商圈
     * @param areaIds
     * @param districtId
     * @param houseType
     * @return
     */
    int unBundlingDistrictArea(List<Integer> areaIds, Integer districtId,Integer houseType);

    /**
     * 根据商圈拼音首字母查询未绑定商圈
     * @param districtAreaConfigQuery
     * @return
     */
    List<Area> unBundlingAreaByPinyin(DistrictAreaConfigQuery districtAreaConfigQuery);

    /**
     * 城市，区域，商圈级联数据
     * @return
     */
    CascadeControlDomain findDistrictCityAreaConfig(DistrictAreaConfigQuery districtAreaConfigQuery);



}
