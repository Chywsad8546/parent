package com.toutiao.officedict.service.relation;

import com.toutiao.officedict.dao.entity.officedict.RelationOfMaiTian;
import com.toutiao.officedict.domain.query.RelationOfMaiTianQuery;

import java.util.List;

/**
 * @author 武守磊
 * Created by 18710 on 2018/4/11.
 */
public interface RelationOfMaiTianService {
    List<RelationOfMaiTian> selectRelationOfMaiTian(RelationOfMaiTianQuery relationOfMaiTianQuery);
    RelationOfMaiTian selectOneRelationOfMaiTian(Integer id);

    /**
     * 更新麦田-头条房产楼盘对应关系
     * @param relationOfMaiTianQuery
     * @return
     */
    Integer updateById(RelationOfMaiTianQuery relationOfMaiTianQuery);

    /**
     * 保存麦田-头条房产对应关系
     * @param relationOfMaiTianQuery
     * @return
     */
    Integer saveMaitianMapping(RelationOfMaiTianQuery relationOfMaiTianQuery);
    Integer selectByTouName(String projname);

    Integer deleteMaiTian(Integer id);
}
