package com.user.service;

import java.util.List;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;

import com.user.entity.User;

public interface UserService {

	List<User> findList();
	
	List<User> findList(UserQueryImpl query, Page page);

	boolean checkUserPassword(String loginName, String password);

	void delete(String loginName);

	User findByLoginName(String loginName);
}
