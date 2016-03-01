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
		 // ���������ļ�
	    ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
	    RepositoryService repositoryService = processEngine.getRepositoryService();
	    RuntimeService runtimeService = processEngine.getRuntimeService();
	    repositoryService.createDeployment().name("jishuceshi").addClasspathResource("Interview.bpmn").deploy();
	    String processId = runtimeService.startProcessInstanceByKey("Interview").getId();
	 
	    TaskService taskService = processEngine.getTaskService();
	    //�õ����Ե�����
	    System.out.println("\n***************�������̿�ʼ***************");
	 
	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
	    for (Task task : tasks) {
	        System.out.println("������Դ��������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }
	 
	    System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
	    tasks = taskService.createTaskQuery().taskAssignee("����").list();
	    Map<String,Object> result = new HashMap<String,Object>();
	    result.put("result", "pass");
	    for (Task task : tasks) {
	        System.out.println("����������name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId(),result);
	    }
	 
	    System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************�������̽���***************");
	 
	    System.out.println("\n***************һ�����̿�ʼ***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
//	    for (Task task : tasks) {
//	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "����");
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    for (Task task : tasks) {
//	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    System.out.println("***************һ�����̽���***************");
//	 
//	    System.out.println("\n***************�������̿�ʼ***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
//	    for (Task task : tasks) {
//	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "����");
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    for (Task task : tasks) {
//	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    System.out.println("***************�������̽���***************");
//	 
//	    System.out.println("***************HR�����̿�ʼ***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
//	    for (Task task : tasks) {
//	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "����");
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    for (Task task : tasks) {
//	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    System.out.println("***************HR�����̽���***************");
//	 
//	    System.out.println("\n***************¼�����̿�ʼ***************");
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
//	    for (Task task : tasks) {
//	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
//	        taskService.claim(task.getId(), "����");
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    for (Task task : tasks) {
//	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
//	        taskService.complete(task.getId());
//	    }
//	 
//	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
//	    System.out.println("***************¼�����̽���***************");
//	 
//	    HistoryService historyService = processEngine.getHistoryService();
//	    HistoricProcessInstance historicProcessInstance = historyService
//	            .createHistoricProcessInstanceQuery()
//	            .processInstanceId(processId).singleResult();
//	    System.out.println("\n���̽���ʱ�䣺"+historicProcessInstance.getEndTime());
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
//		Task task = processEngine.getTaskService().createTaskQuery().taskAssignee("����").singleResult();
//		System.out.println(task.getAssignee()+"   "+task.getId());
//		task.setAssignee("����");//������
		/*Task task = processEngine.getTaskService().createTaskQuery().taskCandidateUser("����").singleResult();
		List<IdentityLink> list = processEngine.getTaskService().getIdentityLinksForTask(task.getId());
		if(null!=list && list.size()>0) {
			for(IdentityLink il:list) {
				System.out.println(il.getType()+"    "+il.getUserId());
			}
		}*/
		//processEngine.getRuntimeService().getIdentityLinksForProcessInstance(task.getProcessInstanceId());
		//processEngine.getTaskService().setAssignee("4904", null);
		processEngine.getTaskService().claim("4904", "����");
	}
}
