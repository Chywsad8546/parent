package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ExportSubway;

import java.util.ArrayList;

/**
 * Created by 18710 on 2018/9/25.
 */
public interface ExportSubwayMapper extends BaseDao {
    ArrayList<ExportSubway> selectSubway(Integer cityId);
}
