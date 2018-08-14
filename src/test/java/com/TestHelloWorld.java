package com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestHelloWorld {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	/**部署流程定义*/
	// @Test
	public void deployProcessDefinition() {
		List<Task> taskList = taskService.createTaskQuery().taskAssignee("kermit").list();
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

	public void completeTaskTest() {
		// 任务ID
		String taskId = "10064";
		taskService.complete(taskId);
		System.out.println("完成任务：任务ID：" + taskId);
	}

	@Test
	public void queryTask() {
		//返回存放连线的名称集合  
        List<String> list = new ArrayList<String>();
		Task task = taskService.createTaskQuery().taskId("10064").singleResult();
		System.out.println("====================");
		System.out.println("任务ID:" + task.getId());
		System.out.println("任务名称:" + task.getName());
		System.out.println("任务的创建时间:" + task.getCreateTime());
		System.out.println("任务的办理人:" + task.getAssignee());
		System.out.println("流程实例ID：" + task.getProcessInstanceId());
		System.out.println("执行对象ID:" + task.getExecutionId());
		System.out.println("流程定义ID:" + task.getProcessDefinitionId());

		// 2:获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 3:查询ProcessDefinitionEntity对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);

		// 获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 获取流程实例
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		// 获取当前活动ID
		String activityId = pi.getActivityId();

		// 4：获取当前的活动
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		// 5:获取当前活动完成之后连线的名称
		List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
		if (pvmList != null && pvmList.size() > 0) {
			for (PvmTransition pvm : pvmList) {
				String name = (String) pvm.getProperty("name");
				if (StringUtils.isNotBlank(name)) {
					list.add(name);
					System.out.println(name);
				} else {
					list.add("默认提交");
					System.out.println("默认提交");
				}
			}
		}
	}

	public void test1() {
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("com/diagrams/process.bpmn20.xml").deploy();
		System.out.println("deploy.getId()=" + deploy.getId() + ",name=" + deploy.getName());
	}

	public void test2() {
		// ProcessDefinition define =
		// repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		// System.out.println("define.getKey()=" + define.getKey() + ",deploymentId=" + define.getDeploymentId());
	}

	public void test3() {
		BpmnModel model = repositoryService.getBpmnModel("leave-formkey:1:30004");
		if (model != null) {
			Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
			for (FlowElement e : flowElements) {
				System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
			}
		}
	}
}
