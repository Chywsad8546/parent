package com.toutiao.officedict.dao.mapper.mapping;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.mapping.HouseCommunityNotMatch;
import com.toutiao.officedict.domain.mapping.NotMatchModel;
import com.toutiao.officedict.domain.query.mapping.NotMatchQuery;

import java.util.List;

/**
 * @author 武守磊
 */
public interface HouseCommunityNotMatchMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseCommunityNotMatch record);

    int insertSelective(HouseCommunityNotMatch record);

    HouseCommunityNotMatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseCommunityNotMatch record);

    int updateByPrimaryKey(HouseCommunityNotMatch record);

    /**
     * 未匹配楼盘对应关系列表
     * @param notMatchQuery
     * @return
     */
    List<NotMatchModel> listNotMatch(NotMatchQuery notMatchQuery);

    NotMatchModel selectNotMatch(Integer id);

    List<String> getCompanys();


}