package com.toutiao.officedict.service.area;

import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.dao.entity.officedict.DistrictAreaConfig;
import com.toutiao.officedict.domain.query.AreaQuery;

import java.util.List;

public interface AreaService {

    /**
     * 获取城市列表
     * @param areaQuery
     * @return
     */
    List<Area> getAreaList(AreaQuery areaQuery);

    /**
     * 根据城市id删除城市
     * @param areaId
     * @return
     */
    int delArea(Integer areaId);

    /**
     * 添加城市
     * @param area
     * @return
     */
    int addArea(Area area);

    /**
     * 更新城市
     * @param area
     * @return
     */
    int updateArea(Area area);

    /**
     * 根据城市id查询详情
     * @param areaId
     * @return
     */
    Area findAreaById(Integer areaId);

    List<Area> findAreaByDistrictId(DistrictAreaConfig districtAreaConfig);

    List<Area> areaListByDistrictId(AreaQuery areaQuery);

}
