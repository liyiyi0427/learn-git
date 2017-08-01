package com.hexin.mapper.hexin6;

import com.hexin.domain.hexin6.Parameter;

import java.util.List;
import java.util.Map;


public interface ParameterMapper {

    int insert(Parameter record);

    Parameter selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);

    List<Parameter> selectAll();

    Map<String, String> findValueForCash();
    
    List<Parameter> findValueForVip(int valueId);
}
