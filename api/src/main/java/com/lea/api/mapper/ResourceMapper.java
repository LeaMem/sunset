package com.lea.api.mapper;

import com.lea.api.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper {

    List<Resource> listAllByUrl(@Param("url") String url);
}
