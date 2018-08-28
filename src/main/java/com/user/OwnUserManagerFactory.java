package com.user;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

import com.user.service.UserService;

public class OwnUserManagerFactory implements SessionFactory {

	private UserService userService;

	public OwnUserManagerFactory(UserService userService) {
		this.userService = userService;
	}

	public Class<?> getSessionType() {
		return UserIdentityManager.class;
	}

	public Session openSession() {
		return new OwnUserManager(userService);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
