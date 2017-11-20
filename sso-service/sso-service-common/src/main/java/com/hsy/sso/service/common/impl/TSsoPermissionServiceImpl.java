package com.hsy.sso.service.common.impl;

import com.hsy.bean.vo.PermissionBean;
import com.hsy.java.enums.DBEnum;
import com.hsy.java.exception.dao.DBHandleException;
import com.hsy.sso.base.entity.sso.TSsoPermission;
import com.hsy.sso.dao.mybatis.mapper.ITSsoPermissionMapper;
import com.hsy.sso.service.api.ITSsoPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
        List<TSsoPermission> list = itSsoPermissionMapper.getAll(null,null) ;
        if(list.size()>0){
            return generateXIndex(list);
        }else{
            throw new DBHandleException(DBEnum.DB_SELECT_IS_NULL) ;
        }
    }
    @Override
    public List<PermissionBean> getAllPermissionByUserId(Long userId) {
        List<TSsoPermission> list = itSsoPermissionMapper.getAllOfUser(userId) ;
        if(list.size()>0){
            return generateXIndex(list);
        }else{
            throw new DBHandleException(DBEnum.DB_SELECT_IS_NULL) ;
        }
    }

    @Override
    public List<TSsoPermission> getAll(Integer offset, Integer limit) {
        List<TSsoPermission> list = itSsoPermissionMapper.getAll(offset,limit) ;
        if(list.size()>0){
            return list ;
        }else {
            throw new DBHandleException(DBEnum.DB_SELECT_IS_NULL) ;
        }
    }

    @Override
    public boolean insert(String authAddress, String authDescription, Long parentid, Long userId) {
        TSsoPermission permission = new TSsoPermission() ;
        permission.setAuthAddress(authAddress);
        permission.setAuthDescription(authDescription);
        permission.setParentId(parentid);
        permission.setCreater(userId);
        permission.setCreateTime(Calendar.getInstance().getTime());
        if(itSsoPermissionMapper.insert(permission)>=1){
            return true ;
        }
        return false;
    }

    @Override
    public boolean update(Long id,String authAddress, String authDescription, Long parentid, Long userId) {
        TSsoPermission permission = new TSsoPermission() ;
        permission.setId(id);
        permission.setAuthAddress(authAddress);
        permission.setAuthDescription(authDescription);
        permission.setParentId(parentid);
        permission.setUpdater(userId);
        permission.setUpdateTime(Calendar.getInstance().getTime());
        if(itSsoPermissionMapper.update(permission)>=1){
            return true ;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try{
            itSsoPermissionMapper.delete(id) ;
            return true;
        }catch (Exception e){

        }
        return false;
    }
    /**
     * @description <p>生成树</p>
     * @param
     * @return
     * @author heshiyuan
     * @date 2017/11/15 10:14
     */
    private List<PermissionBean> generateXIndex(List<TSsoPermission> list) {
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
            outerPermissionBean.setAuthImg(tSsoPermission.getAuthImg());
            outerPermissionBean.setAuthStyle(tSsoPermission.getAuthStyle());
            outerPermissionBean.setParentid(tSsoPermission.getParentId());
            List<PermissionBean> tempList = new ArrayList<>();
            innerList.forEach(innerSsoPermission -> {
                if(innerSsoPermission.getParentId()==tSsoPermission.getId()){
                    PermissionBean permissionBean = new PermissionBean() ;
                    permissionBean.setId(innerSsoPermission.getId());
                    permissionBean.setAuthAddress(innerSsoPermission.getAuthAddress());
                    permissionBean.setAuthDescription(innerSsoPermission.getAuthDescription());
                    permissionBean.setAuthImg(innerSsoPermission.getAuthImg());
                    permissionBean.setAuthStyle(innerSsoPermission.getAuthStyle());
                    permissionBean.setParentid(innerSsoPermission.getParentId());
                    tempList.add(permissionBean) ;
                }
            });
            outerPermissionBean.setChildren(tempList);
            returnList.add(outerPermissionBean);
        });
        return returnList ;
    }
}
