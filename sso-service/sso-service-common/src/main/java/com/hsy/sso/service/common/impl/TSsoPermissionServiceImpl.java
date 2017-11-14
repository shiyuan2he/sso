package com.hsy.sso.service.common.impl;

import com.hsy.bean.vo.PermissionBean;
import com.hsy.sso.base.entity.sso.TSsoPermission;
import com.hsy.sso.dao.mybatis.mapper.ITSsoPermissionMapper;
import com.hsy.sso.service.api.ITSsoPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.common.impl
 * @date 2017/11/14 13:18
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Service("ssoPermissionService")
public class TSsoPermissionServiceImpl implements ITSsoPermissionService {

    @Autowired private ITSsoPermissionMapper itSsoPermissionMapper ;
    @Override
    public List<PermissionBean> getAllPermisstion() {
        List<TSsoPermission> list = itSsoPermissionMapper.getAll() ;
        List<TSsoPermission> outerList = list.stream()
                .filter(tSsoPermission -> tSsoPermission.getParentId()==0)
                .collect(Collectors.toList());
        List<TSsoPermission> innerList = list.stream()
                .filter(tSsoPermission -> tSsoPermission.getParentId()!=0)
                .collect(Collectors.toList());
        List<PermissionBean> returnList = new ArrayList<>() ;
        outerList.forEach(tSsoPermission -> {
            PermissionBean outerPermissionBean = new PermissionBean() ;
            outerPermissionBean.setId(tSsoPermission.getId());
            outerPermissionBean.setAuthAddress(tSsoPermission.getAuthAddress());
            outerPermissionBean.setAuthDescription(tSsoPermission.getAuthDescription());
            outerPermissionBean.setParentid(tSsoPermission.getParentId());
            List<PermissionBean> tempList = new ArrayList<>();
            innerList.forEach(innerSsoPermission -> {
                if(innerSsoPermission.getParentId()==tSsoPermission.getId()){
                    PermissionBean permissionBean = new PermissionBean() ;
                    permissionBean.setId(innerSsoPermission.getId());
                    permissionBean.setAuthAddress(innerSsoPermission.getAuthAddress());
                    permissionBean.setAuthDescription(innerSsoPermission.getAuthDescription());
                    permissionBean.setParentid(innerSsoPermission.getParentId());
                    tempList.add(permissionBean) ;
                }
            });
            outerPermissionBean.setChildren(tempList);
            returnList.add(outerPermissionBean);
        });
        return returnList ;
    }

    @Override
    public List<PermissionBean> getAllPermissionByUserId(Long userId) {
        //return itSsoPermissionMapper.getAllOfUser(userId) ;
        return null ;
    }
}
