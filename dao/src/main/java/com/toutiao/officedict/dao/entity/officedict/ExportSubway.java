package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

/**
 * Created by 18710 on 2018/9/25.
 */
@Data
public class ExportSubway {
    private Integer lineId;
    private String subwayName;
    private Integer stationId;
    private String stationName;
}
