package com.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dao.UserDAO;
import com.user.entity.User;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	public List<User> findList() {
		return userDao.findList();
	}

	public boolean checkUserPassword(String loginName, String password) {
		User user = userDao.findByLoginName(loginName);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public User findByLoginName(String loginName) {
		User user = userDao.findByLoginName(loginName);
		return user;
	}

	public void delete(String loginName) {
		userDao.delete(loginName);
	}

}
