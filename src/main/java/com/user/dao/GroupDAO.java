package com.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.user.BaseDao;
import com.user.entity.Group;

@BaseDao
public interface GroupDAO {

	@Select("select * from by_group")
	List<Group> findList();
}
