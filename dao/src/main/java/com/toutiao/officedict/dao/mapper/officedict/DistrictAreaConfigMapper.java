package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.DistrictAreaConfig;
import com.toutiao.officedict.domain.query.DistrictAreaConfigQuery;

import java.util.List;

public interface DistrictAreaConfigMapper extends BaseDao {
    int insert(DistrictAreaConfig record);

    int insertSelective(DistrictAreaConfig record);

    /**
     * 根据区县id查询绑定商圈
     * @param districtAreaConfigQuery
     * @return
     */
    List<DistrictAreaConfig> selectConfigListByDisIdHouType(DistrictAreaConfigQuery districtAreaConfigQuery);

    /**
     * 检测绑定关系
     * @param districtAreaConfig
     * @return
     */
    DistrictAreaConfig selectCheckConfig(DistrictAreaConfig districtAreaConfig);

    /**
     * 解除绑定关系
     * @param districtAreaConfig
     * @return
     */
    int delDistrictAreaConfig(DistrictAreaConfig districtAreaConfig);

    /**
     * 根据区县查询
     * @param districtAreaConfigQuery
     * @return
     */
    List<DistrictAreaConfig> selectByDistrictId(DistrictAreaConfigQuery districtAreaConfigQuery);

    /**
     * 根据商圈查询
     * @param districtAreaConfigQuery
     * @return
     */
    List<DistrictAreaConfig> selectByAreaId(DistrictAreaConfigQuery districtAreaConfigQuery);


    List<DistrictAreaConfig> selectAreaByTypeAndIds(DistrictAreaConfig districtAreaConfig);
}