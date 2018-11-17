package com.toutiao.officedict.service.global;

import com.toutiao.officedict.dao.entity.officedict.ProjPrice;
import com.toutiao.officedict.domain.query.ProjectPriceQuery;

import java.util.List;

/**
 * @author WuShoulei on 2017/11/24
 */
public interface ProjectPriceService {

    /**
     * 获取价格列表
     * @param priceQuery
     * @return
     */
    List<ProjPrice> listProjectPrice(ProjectPriceQuery priceQuery);

    /**
     * 添加价格信息
     * @param price
     * @return
     */
    int saveProjectPrice(ProjPrice price);

    /**
     * 查询某条价格信息
     * @param priceId
     * @return
     */
    ProjPrice getProjectPrice(Integer priceId);

    /**
     * 更新某条价格信息
     * @param price
     * @return
     */
    int updateProjectPrice(ProjPrice price);

    /**
     * 删除某条价格信息
     * @param priceId
     * @return
     */
    int deleteProjectPrice(Integer priceId);
}
