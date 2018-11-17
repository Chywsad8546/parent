package com.toutiao.officedict.dao.mapper.admin;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.admin.SysMenuEntity;
import com.toutiao.officedict.dao.entity.admin.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseDao {
    int deleteByPrimaryKey(String code);

    int insert(SysMenuEntity record);

    int insertSelective(SysMenuEntity record);

    SysMenuEntity selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysMenuEntity record);

    int updateByPrimaryKey(SysMenuEntity record);

    List<SysMenuEntity> selectByStatus(@Param("status")Integer status);
    List<SysMenuEntity> selectByRoleID(@Param("role_id")Integer role_id);

}