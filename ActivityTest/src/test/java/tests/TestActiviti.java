package tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class TestActiviti {
	
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
 	@Test
	 public void monthtest() {
		 // 加载配置文件
	    ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
	    RepositoryService repositoryService = processEngine.getRepositoryService();
	    RuntimeService runtimeService = processEngine.getRuntimeService();
	    repositoryService.createDeployment().name("jishuceshi").addClasspathResource("Interview.bpmn").deploy();
	    String processId = runtimeService.startProcessInstanceByKey("Interview").getId();
	 
	    TaskService taskService = processEngine.getTaskService();
	    //得到笔试的流程
	    System.out.println("\n***************笔试流程开始***************");
	 
	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("人力资源部").list();
	    for (Task task : tasks) {
	        System.out.println("人力资源部的任务：name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "张三");
	    }
	 
	    System.out.println("张三的任务数量："+taskService.createTaskQuery().taskAssignee("张三").count());
	    tasks = taskService.createTaskQuery().taskAssignee("张三").list();
	    Map<String,Object> result = new HashMap<String,Object>();
	    result.put("result", "pass");
	    for (Task task : tasks) {
	        System.out.println("张三的任务：name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId(),result);
	    }
	 
	    System.out.println("张三的任务数量："+taskService.createTaskQuery().taskAssignee("张三").count());
	    System.out.println("***************笔试流程结束***************");
	 
	    System.out.println("\n***************一面流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("技术部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************一面流程结束***************");
//	 
//	    System.out.println("\n***************二面流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("技术部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************二面流程结束***************");
//	 
//	    System.out.println("***************HR面流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("人力资源部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************HR面流程结束***************");
//	 
//	    System.out.println("\n***************录用流程开始***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("人力资源部").list();
//	    for (Task task : tasks) {
//	        System.out.println("技术部的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "李四");
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    for (Task task : tasks) {
//	        System.out.println("李四的任务：name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("李四的任务数量："+taskService.createTaskQuery().taskAssignee("李四").count());
//	    System.out.println("***************录用流程结束***************");
//	 
//	    HistoryService historyService = processEngine.getHistoryService();
//	    HistoricProcessInstance historicProcessInstance = historyService
//	            .createHistoricProcessInstanceQuery()
//	            .processInstanceId(processId).singleResult();
//	    System.out.println("\n流程结束时间："+historicProcessInstance.getEndTime());
}
	
	@Test
	public void sequenceFlow(){
		/*InputStream in = this.getClass().getResourceAsStream("sequence.bpmn");
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addInputStream("sequence.bpmn", in).deploy();
		System.out.println(deployment.getName());*/
		//processEngine.getRuntimeService().startProcessInstanceByKey("testlistener");
		/*processEngine.getRuntimeService().startProcessInstanceByKey("myProcess");
		Task task = processEngine.getTaskService().createTaskQuery().processDefinitionName("parallelGateway").singleResult();
		System.out.println(task.getId()+"     "+task.getName());*/
		//processEngine.getTaskService().complete("4202");
//		Task task = processEngine.getTaskService().createTaskQuery().taskAssignee("张三").singleResult();
//		System.out.println(task.getAssignee()+"   "+task.getId());
//		task.setAssignee("李四");//不可行
		/*Task task = processEngine.getTaskService().createTaskQuery().taskCandidateUser("张三").singleResult();
		List<IdentityLink> list = processEngine.getTaskService().getIdentityLinksForTask(task.getId());
		if(null!=list && list.size()>0) {
			for(IdentityLink il:list) {
				System.out.println(il.getType()+"    "+il.getUserId());
			}
		}*/
		//processEngine.getRuntimeService().getIdentityLinksForProcessInstance(task.getProcessInstanceId());
		//processEngine.getTaskService().setAssignee("4904", null);
		processEngine.getTaskService().claim("4904", "田七");
	}
}
