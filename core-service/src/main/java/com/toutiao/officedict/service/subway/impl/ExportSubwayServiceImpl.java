package com.toutiao.officedict.service.subway.impl;

import com.toutiao.officedict.dao.entity.officedict.ExportSubway;
import com.toutiao.officedict.dao.mapper.officedict.ExportSubwayMapper;
import com.toutiao.officedict.service.subway.ExportSubwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 18710 on 2018/9/25.
 */
@Service
public class ExportSubwayServiceImpl implements ExportSubwayService {
    @Autowired
    private ExportSubwayMapper exportSubwayMapper;

    @Override
    public ArrayList<ExportSubway> exportSubwayList(Integer cityId) {
        return exportSubwayMapper.selectSubway(cityId);
    }
}
