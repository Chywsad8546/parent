package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.RelationOfMaiTian;
import com.toutiao.officedict.domain.query.RelationOfMaiTianQuery;

import java.util.List;

/**
 * Created by 18710 on 2018/4/11.
 */
public interface RelationOfMaiTianMapper extends BaseDao {
    /**
     * 麦田列表
     */
    List<RelationOfMaiTian> selectRelationOfMaiTian(RelationOfMaiTianQuery relationOfMaiTianQuery);
    RelationOfMaiTian selectOne(Integer id);
    Integer updateById(RelationOfMaiTianQuery relationOfMaiTianQuery);
    Integer insert(RelationOfMaiTianQuery relationOfMaiTianQuery);
    Integer selectByTouName(String projname);
    Integer deleteById(Integer id);
    Integer selectCount(RelationOfMaiTianQuery relationOfMaiTianQuery);
}
