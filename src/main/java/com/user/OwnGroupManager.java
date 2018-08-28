package com.user;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;

import com.user.service.GroupService;

public class OwnGroupManager extends GroupEntityManager {

	private GroupService groupService;

	public OwnGroupManager(GroupService groupService) {
		this.groupService = groupService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	public void insertGroup(Group group) {
		throw new ActivitiException("My group manager doesn't support inserting a new group");
	}

	@Override
	public void updateGroup(Group updatedGroup) {
		throw new ActivitiException("My group manager doesn't support updating a new group");
	}

	@Override
	public void deleteGroup(String groupId) {
		throw new ActivitiException("My group manager doesn't support deleting a new group");
	}

	@Override
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		List<com.user.entity.Group> entityList = groupService.findList();

		List<Group> groups = new ArrayList<Group>();
		for (com.user.entity.Group entity : entityList) {
			GroupEntity ge = new GroupEntity();
			ge.setId(String.valueOf(entity.getGroupId()));
			ge.setRevision(1);
			ge.setName(entity.getGroupName());
			ge.setType("security-role");
			groups.add(ge);
		}
		return groups;
	}

	@Override
	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
		return super.findGroupCountByQueryCriteria(query);
	}

	@Override
	public List<Group> findGroupsByUser(String userId) {
		throw new ActivitiException("我的群组管理不支持查询群组");
	}
}
