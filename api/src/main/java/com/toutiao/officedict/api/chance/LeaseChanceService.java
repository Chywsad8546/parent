package com.toutiao.officedict.api.chance;

import com.toutiao.officedict.api.chance.request.LeaseChanceRequest;
import com.toutiao.officedict.common.exceptions.NashRequestException;
import com.toutiao.officedict.common.restmodel.NashResult;

/**
 * Created by jyl on 17/9/12.
 */
public interface LeaseChanceService {
    NashResult findLeaseChanceListByRange(Integer page,
                                   Integer size,
                                   Integer cityId,Integer districtId,Integer projectId,
                                   Integer circleId,
                                   Integer productTypeId,
                                   Integer stage,
                                   String name,String phone,Integer status);


}
