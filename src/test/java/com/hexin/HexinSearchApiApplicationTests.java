package com.hexin;

import com.alibaba.fastjson.JSONObject;
import com.hexin.mapper.hexin6.UserMapper;
import com.hexin.model.PageVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class HexinSearchApiApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Test
	public void contextLoads() {
	//	List<User> users = userMapper.listAll();
		PageVo pageVo = new PageVo();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userIdNum","247");
		pageVo.setParameters(params);
		JSONObject.toJSON(pageVo);
//		pageVo.setPageNum("1");
//		pageVo.setPageSize("3");
//		params.put("name","aaaa");
//		params.put("pwd","bbbb");
//		pageVo.setParameters(params);
        System.out.println(JSONObject.toJSON(pageVo));
	}

}
