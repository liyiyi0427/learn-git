package com.hexin;

import com.alibaba.fastjson.JSONObject;
import com.hexin.model.PageVo;
import com.hexin.service.UserAccountSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccountSearchTest {

    @Resource
    private UserAccountSearchService userAccountSearchService;

    @Test
    public void searchAccountFreezeMoneyTest(){
        PageVo pageVo = new PageVo();
        Map<String,Object> map = new HashMap<>();
//        map.put("bid_id","121514570");
//        map.put("asset_id","4236668");
//        {\"parameters\":{\"asset_id\":\"41855158\",\"bid_id\":\"41855124\"}}

//            map.put("asset_id","41855158");
//        map.put("bid_id","41855124");
        map.put("user_id",249);
        map.put("startDate","2017-06-01 00:00:00");
//        map.put("state","4,7,9");

//        map.put("user_id","190855");
        pageVo.setPageSize("10");
        pageVo.setPageNum("0");
//        map.put("io","0");
//        List<Integer> money_op = Lists.newArrayList();
//        money_op.add(0);
//        money_op.add(60);
//        map.put("money_op",JSONObject.toJSON(money_op));
//        map.put("all","1");
        pageVo.setParameters(map);
        Object param = JSONObject.toJSON(pageVo);
        System.out.println(param);
        userAccountSearchService.searchWaitBidGathering(pageVo);

    }
}
