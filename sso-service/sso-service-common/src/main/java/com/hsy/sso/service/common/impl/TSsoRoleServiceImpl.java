package com.hsy.sso.service.common.impl;

import com.hsy.sso.base.entity.sso.TSsoRole;
import com.hsy.sso.dao.mybatis.mapper.ITSsoRoleMapper;
import com.hsy.sso.service.api.ITSsoRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.common.impl
 * @date 2017/11/15 10:09
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Service(value = "ssoRoleService")
public class TSsoRoleServiceImpl implements ITSsoRoleService{

    @Autowired private ITSsoRoleMapper itSsoRoleMapper ;
    @Override
    public boolean insert(String roleName, String roleDescription,Long userId) {
        TSsoRole role = new TSsoRole();
        role.setRoleName(roleName);
        role.setRoleDescription(roleDescription);
        role.setCreater(userId);
        role.setCreateTime(Calendar.getInstance().getTime());
        if(itSsoRoleMapper.insert(role)>=1){
            return true ;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        itSsoRoleMapper.delete(id) ;
        return true;
    }

    @Override
    public boolean update(Long id,String roleName, String roleDescription,Long userId) {
        TSsoRole role = new TSsoRole();
        role.setId(id);
        role.setRoleName(roleName);
        role.setRoleDescription(roleDescription);
        role.setUpdater(userId);
        role.setUpdateTime(Calendar.getInstance().getTime());
        if(itSsoRoleMapper.update(role)>=1){
            return true ;
        }
        return false;
    }

    @Override
    public List<TSsoRole> getAll(Integer offset, Integer limit) {
        return itSsoRoleMapper.getAll(offset,limit);
    }
}
