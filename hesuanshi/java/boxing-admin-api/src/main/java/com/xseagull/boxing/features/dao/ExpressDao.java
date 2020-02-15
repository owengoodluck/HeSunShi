package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Express;

public interface ExpressDao {

	List<Express> getExpress();

	String findNameById(@Param("id") long id);

}
