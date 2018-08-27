package com.user;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;

public class OwnUserManager extends UserEntityManager {

	private KeystoneConnection keystoneConnection;

	public OwnUserManager(KeystoneConnection keystoneConnection) {
		this.keystoneConnection = keystoneConnection;
	}

	@Override
	public User createNewUser(String userId) {
		return super.createNewUser(userId);
		// throw new ActivitiException("User manager doesn't support creating a newe user");
	}

	@Override
	public void insertUser(User user) {
		super.insertUser(user);
		// throw new ActivitiException("User manager doesn't support inserting a newe user");
	}

	@Override
	public void updateUser(User updatedUser) {
		super.updateUser(updatedUser);
		// throw new ActivitiException("User manager doesn't support updating a newe user");
	}

	@Override
	public User findUserById(String userId) {
		return super.findUserById(userId);
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
		System.out.println("start to findUsersByNativeQuery.....................!!!!!!_----------------------------------------------------");
		// use your own method or third party method...
		return super.findUsersByNativeQuery(parameterMap, firstResult, maxResults);
	}

	@Override
	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		return super.findUserCountByQueryCriteria(query);
		// return findUserByQueryCriteria(query, null).size();
	}

	@Override
	public Boolean checkPassword(String userId, String password) {

		// now return true, means that ignoring the password verification
		return true;
	}
}
