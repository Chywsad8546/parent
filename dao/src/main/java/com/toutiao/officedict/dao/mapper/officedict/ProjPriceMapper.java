package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ProjPrice;
import com.toutiao.officedict.domain.query.ProjectPriceQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjPriceMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjPrice record);

    int insertSelective(ProjPrice record);

    ProjPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjPrice record);

    int updateByPrimaryKey(ProjPrice record);

    /**
     * 获取某楼盘的历史价格信息
     * @param priceQuery
     * @return
     */
    List<ProjPrice> listProjectPrice(ProjectPriceQuery priceQuery);
}