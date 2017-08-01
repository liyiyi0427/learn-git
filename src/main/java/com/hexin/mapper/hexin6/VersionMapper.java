package com.hexin.mapper.hexin6;

import com.hexin.domain.hexin6.Version;
import com.hexin.model.PageVo;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface VersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);

    Version getVersionByPacketType(String packageType);

    /**
     * 获取最新版本
     * 
     * @param packageType
     * 
     * @return
     */
    Version getLastVersion(byte packageType);

    /**
     * @param pageVo
     * @param pageable
     * @return
     * @author panmeng
     */
    List<Version> getAllVersions(@Param("pageVo") PageVo pageVo, Pageable pageable);

}