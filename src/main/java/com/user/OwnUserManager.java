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
import org.springframework.beans.factory.annotation.Autowired;

import com.user.service.UserService;

public class OwnUserManager extends UserEntityManager {

	private final Logger logger = LoggerFactory.getLogger(OwnUserManager.class);

	private KeystoneConnection keystoneConnection;

	@Autowired
	private UserService userService;

	public OwnUserManager(KeystoneConnection keystoneConnection) {
		this.keystoneConnection = keystoneConnection;
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
		com.user.entity.User user = userService.findByLoginName(userId);
		if (user != null) {
			UserEntity entity = new UserEntity();
			entity.setId(userId);
			entity.setFirstName(user.getNickName());
			return entity;
		}
		return null;
		// throw new ActivitiException("User manager doesn't support finding an user by id");
	}

	@Override
	public void deleteUser(String userId) {
		throw new ActivitiException("User manager doesn't support deleting a newe user");
	}

	@Override
	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		System.out.println("start to findUserByQueryCriteria.....................!!!!!!_----------------------------------------------------");
		// use your own method or third party method...
		return super.findUserByQueryCriteria(query, page);
	}

	@Override
	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
		logger.info("start to findUsersByNativeQuery.....................!!!!!!_----------------------------------------------------");
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
		return super.findUserCountByQueryCriteria(query);
		// return findUserByQueryCriteria(query, null).size();
	}

	@Override
	public Boolean checkPassword(String userId, String password) {
		logger.info("登录用户名={},密码={}", userId, password);
		return userService.checkUserPassword(userId, password);
	}
}
