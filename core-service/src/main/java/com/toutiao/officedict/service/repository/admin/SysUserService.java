package com.toutiao.officedict.service.repository.admin;

import com.toutiao.officedict.dao.entity.admin.SysUserEntity;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */
public interface SysUserService {
    int deleteByPrimaryKey(Integer id);
    int insertSelective(SysUserEntity record);
    SysUserEntity selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SysUserEntity record);
    List<SysUserEntity> selectByKey(SysUserEntity record);

    /**
     * 获取全部用户信息
     * @return
     */
    List<SysUserEntity> selectAll();

    SysUserEntity selectByLoginName(String loginName);


}
