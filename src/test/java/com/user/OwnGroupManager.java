package com.user;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;

public class OwnGroupManager extends GroupEntityManager {

	private KeystoneConnection keystoneConnection;

	public OwnGroupManager(KeystoneConnection keystoneConnection) {
		this.keystoneConnection = keystoneConnection;
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
		// sometimes to implement how to query the Group
		// return super.findGroupByQueryCriteria(query, page);

		List<Group> groups = new ArrayList<Group>();
		GroupEntity ge = new GroupEntity();
		ge.setId("admin");
		ge.setRevision(1);
		ge.setName("Administrators");
		ge.setType("security-role");
		groups.add(ge);
		return groups;
	}

	@Override
	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
		// TODO Auto-generated method stub
		return super.findGroupCountByQueryCriteria(query);
	}

	@Override
	public List<Group> findGroupsByUser(String userId) {
		throw new ActivitiException("My group manager doesn't support finding a group");
	}
}
