package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.domain.districtArea.AreaWithDistrictID;
import com.toutiao.officedict.domain.query.AreaQuery;
import com.toutiao.officedict.domain.query.DistrictAreaConfigQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaMapper extends BaseDao {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    /**
     * 商圈列表
     */
    List<Area> selectAreaList(AreaQuery areaQuery);

    /**
     * 根据物业类别和商圈id获取未绑定的商圈
     */
    List<Area> selectUnboundAreaByPurposesAndId(DistrictAreaConfigQuery districtAreaConfigQuery);

    /**
     * 根据商圈名称查询
     * @param areaName
     * @return
     */
    Area selectByAreaName(String areaName);

    List<Area> selectByDistrictID(AreaQuery areaQuery);
    List<AreaWithDistrictID> selectByCityIdAndHouseType(@Param("cityid") Integer cityid, @Param("houseType") Integer housetype);


}