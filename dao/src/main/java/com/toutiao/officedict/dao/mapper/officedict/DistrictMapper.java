package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.Cities;
import com.toutiao.officedict.dao.entity.officedict.District;
import com.toutiao.officedict.domain.district.DistrictDomain;
import com.toutiao.officedict.domain.query.DistrictQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper extends BaseDao {
    int deleteByPrimaryKey(Integer districtId);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer districtId);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    /**
     * 区县列表
     */
    List<DistrictDomain> selectDistrictList(DistrictQuery districtQuery);

    /*
     * 根据城市id查询商圈列表
     * @param districtQuery
     * @return
     */
    List<District> selectDistrictListByCityId(DistrictQuery districtQuery);


    List<District> selectDistrictListByCityIdAndHouseTye(@Param("cityid")Integer cityid,@Param("housetype")Integer housetype);
    /**
     * 根据名称查询区县
     * @param districtName
     * @return
     */
    District selectByDistrictName(String districtName);

}