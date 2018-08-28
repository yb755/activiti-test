package com.user;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.service.GroupService;

public class OwnGroupManager extends GroupEntityManager {

	private final Logger logger = LoggerFactory.getLogger(OwnGroupManager.class);

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
		try {
			logger.debug("执行insertGroup,group={}", new ObjectMapper().writeValueAsString(group));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ActivitiException("My group manager doesn't support inserting a new group");
	}

	@Override
	public void updateGroup(Group updatedGroup) {
		try {
			logger.debug("执行updateGroup,updatedGroup={}", new ObjectMapper().writeValueAsString(updatedGroup));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ActivitiException("My group manager doesn't support updating a new group");
	}

	@Override
	public void deleteGroup(String groupId) {
		logger.debug("执行deleteGroup,groupId={}", groupId);
		throw new ActivitiException("My group manager doesn't support deleting a new group");
	}

	@Override
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		try {
			logger.debug("执行findGroupByQueryCriteria,query={}", new ObjectMapper().writeValueAsString(query));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			logger.debug("执行findGroupCountByQueryCriteria,query={}", new ObjectMapper().writeValueAsString(query));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.findGroupCountByQueryCriteria(query);
	}

	@Override
	public List<Group> findGroupsByUser(String userId) {
		logger.debug("执行findGroupsByUser,userId={}", userId);
		throw new ActivitiException("我的群组管理不支持查询群组");
	}
}
