package com.hexin.controller;

import com.hexin.model.PageVo;
import com.hexin.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Demo
 *
 * @author zzn
 * @create 2017-04-14 14:22
 **/

@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public Object listAll(@RequestBody PageVo pageVo){
        return  userService.pageUser(pageVo);
    }
}

