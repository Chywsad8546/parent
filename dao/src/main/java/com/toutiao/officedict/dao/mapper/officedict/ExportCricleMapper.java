package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ExportCircleAndDistrict;

import java.util.ArrayList;

/**
 * Created by 18710 on 2018/9/25.
 */
public interface ExportCricleMapper extends BaseDao
{
    ArrayList<ExportCircleAndDistrict>exportCircleAndDistrictlist(Integer cityId);
}
