package com.hexin.model;

import java.util.HashMap;
import java.util.Map;

/**
 * php调用接口传递分页参数
 *
 * @author zzn
 * @create 2017-04-14 18:17
 **/

public class PageVo {
        //当前页
        private String pageNum;

        //每页显示条数
        private String pageSize;

        // 查询参数
        private Map<String, Object> parameters = new HashMap<>(10);

        public String getPageNum() {
            return pageNum;
        }

        public void setPageNum(String pageNum) {
            this.pageNum = pageNum;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public Map<String, Object> getParameters() {
            return parameters;
        }

        public void setParameters(Map<String, Object> parameters) {
            this.parameters = parameters;
        }
}


