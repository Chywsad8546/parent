package com.toutiao.officedict.service.mapping;

import com.toutiao.officedict.domain.mapping.NotMatchModel;
import com.toutiao.officedict.domain.query.mapping.NotMatchQuery;

import java.util.List;

/**
 * 楼盘未匹配服务接口
 * @author WuShoulei on 2018/7/16
 */
public interface NoMatchMappingService {

    /**
     * 楼盘未匹配列表
     * @param notMatchQuery
     * @return
     */
    List<NotMatchModel> listNotMatchMappings(NotMatchQuery notMatchQuery);
    NotMatchModel selectNotMatch(Integer id);

    Integer updateStutas (NotMatchQuery notMatchQuery);

    Integer deleteRela (Integer id);

    List<String> getCompanys();

}
