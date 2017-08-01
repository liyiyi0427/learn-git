package com.hexin.manager;

import com.hexin.mapper.hexin6.UserMapper;
import com.hexin.model.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务事物控制层
 *
 * @author zzn
 * @create 2017-04-17 10:02
 **/
@Component
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    /**
     * 查询所有
     * @return
     */
    public Object pageUser(PageVo pageVo) {
        return userMapper.listAll(pageVo.getParameters());
    }
}
