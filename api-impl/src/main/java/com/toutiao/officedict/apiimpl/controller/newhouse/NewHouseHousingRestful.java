package com.toutiao.officedict.apiimpl.controller.newhouse;

import com.toutiao.officedict.api.chance.request.newhouse.NewHouseHousingRequest;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.NewHouseHousing;
import com.toutiao.officedict.domain.query.HousingQuery;
import com.toutiao.officedict.service.newhouse.NewHouseHouingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房源管理-新房
 * @author WuShoulei on 2017/11/20
 */
@RestController
@RequestMapping("/newHouse")
public class NewHouseHousingRestful {

    @Autowired
    private NewHouseHouingService newHouseHouingService;

    /**
     * 房源列表-新房
     * @return
     */
    @RequestMapping("/listHousing")
    @ResponseBody
    public NashResult newHouseHousingList(HousingQuery housingQuery) {

        List<NewHouseHousing> newHouseHousingList = newHouseHouingService.queryNewHouseHousingList(housingQuery);
        return NashResult.build(newHouseHousingList);
    }

    /**
     * 添加房源-新房
     * @param newHouseHousingRequest
     * @return
     */
    @RequestMapping(value = "/saveHousing", method = RequestMethod.POST)
    @ResponseBody
    public NashResult addNewHouseHousing(@Validated({Second.class}) NewHouseHousingRequest newHouseHousingRequest) {

        NewHouseHousing newHouseHousing = new NewHouseHousing();
        BeanUtils.copyProperties(newHouseHousingRequest, newHouseHousing);

        return NashResult.build(newHouseHouingService.addNewHouseHousing(newHouseHousing));
    }

    /**
     * 查询某个新房房源
     * @param houseId
     * @return
     */
    @RequestMapping(value = "/getHousing")
    @ResponseBody
    public NashResult getNewHouseHousing(@RequestParam(value = "id") Integer houseId) {
        NewHouseHousing houseHousing = newHouseHouingService.getNewHouseHousing(houseId);

        return NashResult.build(houseHousing);
    }

    /**
     * 更新房源-新房
     * @param newHouseHousingRequest
     * @return
     */
    @RequestMapping(value = "/updateHousing", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateNewHouseHousing(@Validated(First.class) NewHouseHousingRequest newHouseHousingRequest) {

        NewHouseHousing newHouseHousing = new NewHouseHousing();
        BeanUtils.copyProperties(newHouseHousingRequest, newHouseHousing);

        return NashResult.build(newHouseHouingService.updateNewHouseHousing(newHouseHousing));
    }

    /**
     * 删除房源-新房
     * @param newHouseHousingRequest
     * @return
     */
    @RequestMapping(value = "/deleteHousing", method = RequestMethod.POST)
    @ResponseBody
    public NashResult delNewHouseHousing(@Validated(Third.class) NewHouseHousingRequest newHouseHousingRequest) {

        NewHouseHousing newHouseHousing = new NewHouseHousing();
        BeanUtils.copyProperties(newHouseHousingRequest, newHouseHousing);

        return NashResult.build(newHouseHouingService.logicDelNewHouseHousing(newHouseHousing));
    }
}
