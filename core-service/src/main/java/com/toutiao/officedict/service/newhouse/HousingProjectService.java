package com.toutiao.officedict.service.newhouse;

import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import com.toutiao.officedict.domain.newHouse.SalesDynamicInfo;
import com.toutiao.officedict.domain.newHouse.SalesLicenseInfo;
import com.toutiao.officedict.domain.query.HousingProjectQuery;
import com.toutiao.officedict.vo.ProjInfoVO;

import java.util.List;

/**
 * 住宅项目（楼盘）服务
 * @author WuShoulei on 2017/11/15
 */
public interface HousingProjectService {

    /**
     * 获取楼盘列表
     * @param query
     * @return
     */
    List<ProjectInfo> listHousingProject(HousingProjectQuery query);

    /**
     * 增加项目
     * @param projInfo
     * @return
     */
    int createProj(ProjectInfo projInfo);

    /**
     * 查询某个楼盘
     * @param newcode
     * @return
     */
    ProjInfoVO retrieveProj(Integer newcode);

    /**
     * 更新某个项目
     * @param projInfo
     * @return
     */
    int updateProj(ProjectInfo projInfo);

    /**
     * 逻辑删除楼盘
     * @param projInfo
     * @return
     */
    int logicDelProj(ProjectInfo projInfo);

    /**
     * 获取某个楼盘的预售证信息列表
     * @param newcode
     * @return
     */
    List<SalesLicenseInfo> listSalesLicenseInfo(Integer newcode);

    /**
     * 获取某个楼盘的楼盘动态信息列表
     * @param newcode
     * @return
     */
    List<SalesDynamicInfo> listSalesDynamicInfo(Integer newcode);

    /**
     * 更新某楼盘的别名信息
     * @param newcode
     * @param newNickName
     */
    void updateProjectNickName(Integer newcode, String oldNickName, String newNickName);

    Integer updateIsAcitivity(ProjectInfo projectInfo);
}
