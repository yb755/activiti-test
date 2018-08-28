package com.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.service.UserService;

public class OwnUserManager extends UserEntityManager {

	private final Logger logger = LoggerFactory.getLogger(OwnUserManager.class);

	private UserService userService;

	public OwnUserManager(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User createNewUser(String userId) {
		// return super.createNewUser(userId);
		throw new ActivitiException("User manager doesn't support creating a newe user");
	}

	@Override
	public void insertUser(User user) {
		// super.insertUser(user);
		throw new ActivitiException("User manager doesn't support inserting a newe user");
	}

	@Override
	public void updateUser(User updatedUser) {
		// super.updateUser(updatedUser);
		throw new ActivitiException("User manager doesn't support updating a newe user");
	}

	@Override
	public User findUserById(String userId) {
		logger.debug("执行findUserById,userId={}", userId);
		com.user.entity.User user = userService.findByLoginName(userId);
		if (user != null) {
			UserEntity entity = new UserEntity();
			entity.setId(userId);
			entity.setFirstName(user.getNickName());
			return entity;
		}
		return null;
	}

	@Override
	public void deleteUser(String userId) {
		logger.debug("执行deleteUser,userId={}", userId);
		userService.delete(userId);
	}

	@Override
	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		try {
			logger.debug("执行findUserByQueryCriteria,query={},page={}", new ObjectMapper().writeValueAsString(query), new ObjectMapper().writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<com.user.entity.User> userList = userService.findList(query, page);
		List<User> list = new ArrayList<User>();
		for (com.user.entity.User user : userList) {
			UserEntity entity = new UserEntity();
			entity.setId(user.getLoginName());
			entity.setFirstName(user.getNickName());
			entity.setLastName("");
			list.add(entity);
		}
		return list;
	}

	@Override
	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
		try {
			logger.debug("执行findUsersByNativeQuery,parameterMap={},firstResult={},maxResults={}", new ObjectMapper().writeValueAsString(parameterMap), firstResult, maxResults);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<com.user.entity.User> userList = userService.findList();
		List<User> list = new ArrayList<User>();
		for (com.user.entity.User user : userList) {
			UserEntity entity = new UserEntity();
			entity.setId(user.getLoginName());
			entity.setFirstName(user.getNickName());
			entity.setLastName("");
			list.add(entity);
		}
		return list;
	}

	@Override
	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		try {
			logger.debug("执行findUserCountByQueryCriteria,query={}", new ObjectMapper().writeValueAsString(query));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.findUserCountByQueryCriteria(query);
		// return findUserByQueryCriteria(query, null).size();
	}

	@Override
	public Boolean checkPassword(String userId, String password) {
		logger.info("登录用户名={},密码={}", userId, password);
		return userService.checkUserPassword(userId, password);
	}
}
