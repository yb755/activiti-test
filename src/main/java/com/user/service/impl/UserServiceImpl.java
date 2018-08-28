package com.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.user.MD5;
import com.user.dao.UserDAO;
import com.user.entity.User;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDao;

	public List<User> findList() {
		return userDao.findList();
	}

	public boolean checkUserPassword(String loginName, String password) {
		User user = userDao.findByLoginName(loginName);
		logger.debug("传入密码,password={}", MD5.md5(password));
		if (user != null) {
			logger.debug("数据库密码,password={}", user.getPassword());
		}
		if (user != null && user.getPassword().equals(MD5.md5(password))) {
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

	public List<User> findList(UserQueryImpl query, Page page) {
		String id = query.getId();
		List<User> userList = new ArrayList<User>();
		if (!StringUtils.isEmpty(id)) {
			User user = userDao.findByLoginName(id);
			if (user != null) {
				userList.add(user);
			}
		} else {
			userList.addAll(userDao.findList());
		}
		return userList;
	}

}
