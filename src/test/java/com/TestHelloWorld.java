package com;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
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
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("com/diagrams/process.bpmn20.xml").deploy();
		ProcessDefinition define = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		List list = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(define.getKey()).list();
		System.out.println(list.size());
	}
}
