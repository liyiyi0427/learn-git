package com.hexin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexin.annotation.Page;
import com.hexin.domain.hexin6.User;
import com.hexin.manager.UserManager;
import com.hexin.mapper.hexin6.UserMapper;
import com.hexin.model.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务demp
 *
 * @author zzn
 * @create 2017-04-14 14:23
 **/

@Service
public class UserService {

    @Autowired
    private UserManager userManager;
    /**
     * 查询所有
     * @return
     */
    @Page
    public Object pageUser(PageVo pageVo) {
        return userManager.pageUser(pageVo);
    }
}
