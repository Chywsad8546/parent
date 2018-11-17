package com.toutiao.officedict.domain.subway;

import lombok.Data;

/**
 * zhangjinglei 2017/9/12 下午7:42
 */
@Data
public class SubwayStationDistince {
    private int subwayid;
    private int stationid;
    private String subwayname;
    private String stationname;
    private int distance;
}
