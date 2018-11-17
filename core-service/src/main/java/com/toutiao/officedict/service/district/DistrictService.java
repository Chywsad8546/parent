package com.toutiao.officedict.service.district;

import com.toutiao.officedict.dao.entity.officedict.Cities;
import com.toutiao.officedict.dao.entity.officedict.District;
import com.toutiao.officedict.domain.district.DistrictDomain;
import com.toutiao.officedict.domain.query.DistrictQuery;

import java.util.List;

/**
 * 区县服务类
 */
public interface DistrictService {

    /**
     * 获取区县列表
     * @param districtQuery
     * @return
     */
    List<DistrictDomain> getDistrictLists(DistrictQuery districtQuery);

    /**
     * 根据区县ID删除区县
     * @param districtId
     * @return
     */
    int delDistrict(Integer districtId);

    /**
     * 添加区县
     * @param district
     * @return
     */
    int addDistrict(District district);

    /**
     * 根据id更新区县
     * @param district
     * @return
     */
    int updateDistrict(District district);

    /**
     *  根据区县id查询区县详情
     * @param districtId
     * @return
     */
    District findDistrictById(Integer districtId);


}
