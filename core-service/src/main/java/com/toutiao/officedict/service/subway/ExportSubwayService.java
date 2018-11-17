package com.toutiao.officedict.service.subway;

import com.toutiao.officedict.dao.entity.officedict.ExportSubway;

import java.util.ArrayList;

/**
 * Created by 18710 on 2018/9/25.
 */
public interface ExportSubwayService {
    ArrayList<ExportSubway> exportSubwayList(Integer cityId);
}
