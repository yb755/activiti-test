package com.user.service;

import java.util.List;

import com.user.entity.User;

public interface UserService {

	List<User> findList();

	boolean checkUserPassword(String loginName, String password);

	void delete(String loginName);

	User findByLoginName(String loginName);
}
