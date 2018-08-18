package com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class QingJiaProcessTest {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	// 发布流程
	// @Test
	public void deploy() {
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("com/diagrams/process-qingjia.bpmn").deploy();
		System.out.println("deploy.getId()=" + deploy.getId() + ",name=" + deploy.getName());
	}

	// 启动流程
	// @Test
	public void startProcess() {
		// 根据流程定义的key（标识）来启动一个实例，activiti找该key下版本最高的流程定义
		// 一般情况下为了方便开发使用该方法启动一个流程实例
		String processDefinitionKey = "process-qingjia";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inputUserName", "yebing");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, params);
		System.out.println("流程梳理所属流程定义id：" + processInstance.getProcessDefinitionId());
		System.out.println("流程实例的id：" + processInstance.getProcessInstanceId());
		System.out.println("流程实例的执行id：" + processInstance.getId());
		System.out.println("流程当前的活动（结点）id：" + processInstance.getActivityId());
		System.out.println("业务标识：" + processInstance.getBusinessKey());
	}

	// 查询我的任务列表
	@Test
	public void queryMyTaskList() {
		List<Task> taskList = taskService.createTaskQuery().taskAssignee("yebing").list();
		System.out.println(taskList.size());
		for (Task task : taskList) {
			System.out.println("====================");
			System.out.println("任务ID:" + task.getId());
			System.out.println("任务名称:" + task.getName());
			System.out.println("任务的创建时间:" + task.getCreateTime());
			System.out.println("任务的办理人:" + task.getAssignee());
			System.out.println("流程实例ID：" + task.getProcessInstanceId());
			System.out.println("执行对象ID:" + task.getExecutionId());
			System.out.println("流程定义ID:" + task.getProcessDefinitionId());
		}
	}

	@Test
	public void completTask() {
		// 任务ID
		String taskId = "20005";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("zhuguan", "xushaomin");
		taskService.complete(taskId, params);
		System.out.println("完成任务：任务ID：" + taskId);
	}

	// 查看实例 状态
	// @Test
	public void viewInstance() {
	}
}
