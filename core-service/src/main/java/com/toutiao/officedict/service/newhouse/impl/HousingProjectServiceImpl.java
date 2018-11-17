package com.toutiao.officedict.service.newhouse.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.common.constant.RingRoadMap;
import com.toutiao.officedict.common.constant.SalesStatusMap;
import com.toutiao.officedict.dao.entity.officedict.*;
import com.toutiao.officedict.dao.mapper.officedict.*;
import com.toutiao.officedict.domain.newHouse.SalesDynamicInfo;
import com.toutiao.officedict.domain.newHouse.SalesLicenseInfo;
import com.toutiao.officedict.domain.query.HousingProjectQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectService;
import com.toutiao.officedict.vo.ProjInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuShoulei on 2017/11/15
 */
@Service
@Transactional
public class HousingProjectServiceImpl implements HousingProjectService {

    @Autowired
    private ProjectInfoMapper projInfoMapper;

    @Autowired
    private ResidenceBuildFormMapper buildFormMapper;

    @Autowired
    private ResidenceBuildCategoryMapper buildCategoryMapper;

    @Autowired
    private ResidenceCategoryMapper residenceCategoryMapper;

    @Override
    public int createProj(ProjectInfo projInfo) {

        return projInfoMapper.insertSelective(projInfo);
    }

    @Override
    public ProjInfoVO retrieveProj(Integer newcode) {

        ProjectInfo projInfo = projInfoMapper.selectByPrimaryKey(newcode);

        ProjInfoVO projInfoVO = new ProjInfoVO();
        BeanUtils.copyProperties(projInfo, projInfoVO);

        //转换建筑类别
        if (null != projInfo.getBuildCategory()) {
            StringBuilder buildCategoryStr = new StringBuilder();
            for (Integer tempBuildCategoryId : projInfo.getBuildCategory()) {
                ResidenceBuildCategory buildCategory = buildCategoryMapper.selectByPrimaryKey(tempBuildCategoryId);

                buildCategoryStr.append(buildCategory.getBuildCategoryName()).append(",");
            }
            String tempBuildCategoryStr = buildCategoryStr.toString();

            projInfoVO.setBuildCategoryDesc(tempBuildCategoryStr.substring(0, tempBuildCategoryStr.length()-1));
        }

        //转换住宅建筑形式
        if (null != projInfo.getBuildForm()) {
            StringBuilder buildFormStr = new StringBuilder();
            for (Integer tempFormId : projInfo.getBuildForm()) {
                ResidenceBuildForm buildForm = buildFormMapper.selectByPrimaryKey(tempFormId);

                buildFormStr.append(buildForm.getBuildFormName()).append(",");
            }

            String tempBuildFormStr = buildFormStr.toString();
            projInfoVO.setBuildFormDesc(tempBuildFormStr.substring(0, tempBuildFormStr.length()-1));
        }

        //转换住宅类别
        if (null != projInfo.getResidentialCategory()) {
            StringBuilder residentialCategoryStr = new StringBuilder();
            for (Integer tempResidentialCategoryId : projInfo.getResidentialCategory()) {
                ResidenceCategory residenceCategory = residenceCategoryMapper.selectByPrimaryKey(tempResidentialCategoryId);

                residentialCategoryStr.append(residenceCategory.getCategoryName()).append(",");
            }

            String tempResidentialCategoryStr = residentialCategoryStr.toString();
            projInfoVO.setResidentialCategoryDesc(tempResidentialCategoryStr.substring(0, tempResidentialCategoryStr.length()-1));
        }

        //转换销售状态
        if (null != projInfo.getSaling()) {
            projInfoVO.setSalingDesc(SalesStatusMap.getSalesStatusDesc(projInfo.getSaling()));
        }

        //转换环线
        if (null != projInfo.getRingRoad()) {
            projInfoVO.setRingRoadDesc(RingRoadMap.getRingRoadDesc(projInfo.getRingRoad()));
        }


        return projInfoVO;
    }

    @Override
    public int updateProj(ProjectInfo projInfo) {
        projInfo.setUpdateTime(new Date());
        return projInfoMapper.updateByPrimaryKeySelective(projInfo);
    }


    @Override
    public List<ProjectInfo> listHousingProject(HousingProjectQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return projInfoMapper.selectProjInfoList(query);
    }

    @Override
    public int logicDelProj(ProjectInfo projInfo) {
        return projInfoMapper.logicDelProj(projInfo);
    }

    /**
     * 获取某个楼盘的预售证信息列表
     * @param newcode
     * @return
     */
    @Override
    public List<SalesLicenseInfo> listSalesLicenseInfo(Integer newcode) {

        ProjectInfo projectInfo = projInfoMapper.selectByPrimaryKey(newcode);

        return projectInfo.getSalesLicenseInfo();
    }

    /**
     * 获取某个楼盘的楼盘动态信息列表
     * @param newcode
     * @return
     */
    @Override
    public List<SalesDynamicInfo> listSalesDynamicInfo(Integer newcode) {

        ProjectInfo projectInfo = projInfoMapper.selectByPrimaryKey(newcode);
        return projectInfo.getDynamicInfo();
    }

    /**
     * 更新楼盘的别名信息
     * @param newcode
     * @param newNickName
     */
    @Override
    public void updateProjectNickName(Integer newcode, String oldNickName, String newNickName) {

        ProjectInfo projectInfo = projInfoMapper.selectByPrimaryKey(newcode);

        //别名不同于原楼盘名
        if (!projectInfo.getProjname().equals(newNickName)) {
            String nickNameStr = projectInfo.getNickname();

            Set<String> nickNameSet = new HashSet<>();

            if (StringUtils.isNotEmpty(nickNameStr)) {
                String[] nickNames = nickNameStr.split(",");

                for(String nick:nickNames){
                    nickNameSet.add(nick);
                }
                //对应关系更新，去掉旧的别名
                if (StringUtils.isNotEmpty(oldNickName)) {
                    nickNameSet.remove(oldNickName);
                }
            }

            nickNameSet.add(newNickName);

            String newNickNameStr = StringUtils.join(nickNameSet.toArray(), ",");

            ProjectInfo projectInfoN = new ProjectInfo();
            projectInfoN.setNewcode(newcode);
            projectInfoN.setNickname(newNickNameStr);

            //更新楼盘别名
            projInfoMapper.updateByPrimaryKeySelective(projectInfoN);
        }

    }

    @Override
    public Integer updateIsAcitivity(ProjectInfo projectInfo) {


        return projInfoMapper.updateIsActivity(projectInfo);
    }

}