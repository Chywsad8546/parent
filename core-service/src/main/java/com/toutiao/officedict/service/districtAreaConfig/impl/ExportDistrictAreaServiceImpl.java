package com.toutiao.officedict.service.districtAreaConfig.impl;

import com.toutiao.officedict.dao.entity.officedict.ExportCircleAndDistrict;
//import com.toutiao.officedict.dao.mapper.officedict.ExportCricleMapper;
import com.toutiao.officedict.service.districtAreaConfig.ExprotDistrictAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by 18710 on 2018/9/25.
 */
@Service
@Transactional
public class ExportDistrictAreaServiceImpl implements ExprotDistrictAreaService {
//    @Autowired
//    private ExportCricleMapper exportCricleMapper;

    @Override
    public ArrayList<ExportCircleAndDistrict> circleAndDistriceList(Integer cityId) {
//        return exportCricleMapper.exportCircleAndDistrictlist(cityId);
        return null;
    }
}
