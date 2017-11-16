package com.hsy.sso.service.api;
import com.hsy.sso.base.entity.sso.TSsoRole;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.jdbc
 * @date 20/10/2017 1:18 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoRoleService {
    boolean insert(String roleName,String roleDescription,Long userId);

    boolean delete(Long id) ;

    boolean update(Long id,String roleName,String roleDescription,Long userId) ;

    List<TSsoRole> getAll(Integer offset, Integer limit) ;
}
