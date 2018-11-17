package com.toutiao.officedict.dao.mapper.admin;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.admin.SysUserEntity;

import java.util.List;

public interface SysUserMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserEntity record);

    int insertSelective(SysUserEntity record);

    SysUserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserEntity record);

    int updateByPrimaryKey(SysUserEntity record);

    List<SysUserEntity> selectByKey(SysUserEntity record);

    /**
     * 获取全部用户信息
     * @return
     */
    List<SysUserEntity> selectAll();

    /**
     * 根据登录名称获取用户数据
     * @param loginName
     * @return
     */
    SysUserEntity selectByLoginName(String loginName);

}