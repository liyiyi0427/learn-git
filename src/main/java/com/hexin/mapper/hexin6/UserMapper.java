package com.hexin.mapper.hexin6;

import com.hexin.domain.hexin6.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 查询所有测试分页
     * @return
     * @param parameters
     */
     List<User> listAll(Map<String, Object> parameters);
}