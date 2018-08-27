package com.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.user.BaseDao;
import com.user.entity.User;

@BaseDao
public interface UserDAO {

	@Select("select * from by_user")
	List<User> findList();

	@Select("select * from by_user where login_name=#{loginName}")
	User findByLoginName(String loginName);

	@Delete("delete from by_user where login_name=#{loginName}")
	void delete(String loginName);
}
