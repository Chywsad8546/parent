package com.toutiao.officedict.service.districtAreaConfig;

import com.toutiao.officedict.dao.entity.officedict.ExportCircleAndDistrict;

import java.util.ArrayList;

/**
 * Created by 18710 on 2018/9/25.
 */
public interface ExprotDistrictAreaService {
      ArrayList<ExportCircleAndDistrict> circleAndDistriceList(Integer cityId);
}
