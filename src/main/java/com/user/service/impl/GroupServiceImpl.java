package com.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dao.GroupDAO;
import com.user.entity.Group;
import com.user.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDao;

	public List<Group> findList() {
		return groupDao.findList();
	}

}
