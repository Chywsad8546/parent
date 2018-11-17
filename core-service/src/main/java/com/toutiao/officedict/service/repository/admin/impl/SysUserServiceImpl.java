package com.toutiao.officedict.service.repository.admin.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.admin.SysUserEntity;
import com.toutiao.officedict.dao.mapper.admin.SysUserMapper;
import com.toutiao.officedict.service.repository.admin.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SysUserEntity record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public SysUserEntity selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUserEntity record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<SysUserEntity> selectByKey(SysUserEntity record) {
        PageHelper.startPage(1,10000);
        return userMapper.selectByKey(record);
    }

    @Override
    public List<SysUserEntity> selectAll() {
        List<SysUserEntity> sysUsers = userMapper.selectAll();
        return sysUsers;
    }

    @Override
    public SysUserEntity selectByLoginName(String loginName) {
        SysUserEntity sysUserEntity = userMapper.selectByLoginName(loginName);
        return sysUserEntity;
    }
}
