package com.user;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

import com.user.service.GroupService;

public class OwnGroupMagagerFactory implements SessionFactory {

	private GroupService groupService;

	public OwnGroupMagagerFactory(GroupService groupService) {
		this.groupService = groupService;
	}

	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	public Session openSession() {
		return new OwnGroupManager(groupService);
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

}
