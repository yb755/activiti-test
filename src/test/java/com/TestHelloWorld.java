package com;

import java.util.Collection;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
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
	@Test
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

	@Test
	public void completeTaskTest() {
		// 任务ID
		String taskId = "10064";
		taskService.setAssignee("kermit","kermit");
		taskService.complete(taskId);
		System.out.println("完成任务：任务ID：" + taskId);
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
