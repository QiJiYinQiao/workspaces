package demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;


public class ActivitiTest {
	
	private static ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
	
	public static void main(String[] args) throws IOException {
//		String processDefinitionKey = "askForLeave";
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey);
//		System.out.println(processInstance.getId()+":::::"+processInstance.getProcessDefinitionId());
//		String deploymentId = "101";
//		processEngine.getRepositoryService().deleteDeployment("1701",true);
//		findHistoryTask();
//		findTask();
//		deploymentProcess();
//		startProcessInstance();
//		completeTask();
//		setVariables();
		//getVariables();
	}
	
	/**
	 * 部署流程
	 * 
	 * 2016年2月17日
	 * demo
	 * void
	 * Sun
	 */
	public static void deploymentProcess(){
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("diagrams/freeday.bpmn").name("newProcess").deploy();
		System.out.println(deployment.getId());
	}
	
	/**
	 * 启动流程
	 * 
	 * 2016年2月17日
	 * demo
	 * void
	 * Sun
	 */
	public static void startProcessInstance(){
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("askForLeave");
		System.out.println(processInstance.getProcessDefinitionId());
	}
	
	/**
	 * 获取流程图片
	 * @throws IOException 
	 * @date 2016年2月17日
	 * demo
	 * void
	 * @user Sun
	 */
	public static void getProcessImage() throws IOException{
		String deploymentId = "601";
		String resourceName = "";
		RepositoryService repositoryService = processEngine.getRepositoryService();
		List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
		for(String name:list){
			if(name.indexOf(".png")>=0){
				resourceName = name;
			}
		}
		
		File file = new File("D:/"+resourceName);
		InputStream inputStream = processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		FileOutputStream fos = new FileOutputStream(file);
		byte[] b = new byte[1024];
		while(bis.read(b)!=-1){
			fos.write(b);
		}
		fos.close();
		inputStream.close();
	}
	
	/**
	 * 查询正在执行的任务
	 * 
	 * @date 2016年2月17日
	 * demo
	 * void
	 * @user Sun
	 */
	public static void findTask(){
		List<Task> list = processEngine.getTaskService().createTaskQuery().taskName("主管").list();
		for(Task task:list) {
			System.out.println(task.getId());
			System.out.println(task.getName());
			System.out.println(task.getProcessDefinitionId());
		}
	}
	
	/**
	 * 完成任务
	 * 
	 * @date 2016年2月17日
	 * demo
	 * void
	 * @user Sun
	 */
	public static void completeTask(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "true");
		map.put("result","false");
		processEngine.getTaskService().complete("3108",map);
	}
	
	/**
	 * 查询所有历史记录
	 * 
	 * @date 2016年2月17日
	 * demo
	 * void
	 * @user Sun
	 */
	public static void findHistoryTask(){
		List<HistoricTaskInstance> list = processEngine.getHistoryService().createHistoricTaskInstanceQuery().list();
		for(HistoricTaskInstance hti:list){
			System.out.println(hti.getName());
		}
	}
	
	/**
	 * 设置流程变量
	 * 
	 * @date 2016年2月17日
	 * demo
	 * void
	 * @user Sun
	 */
	public static void setVariables(){
		processEngine.getTaskService().setVariable("2704", "请假日期", "2015/6/13");
		processEngine.getTaskService().setVariableLocal("2704", "请假天数", "5");//自动绑定taskId，给TASKID赋值,当前任务完成后会自动从act_run_var表中清除。
		processEngine.getRuntimeService().setVariable("2701", "请假原因", "结婚1");
		processEngine.getRuntimeService().setVariableLocal("2701", "请假人", "张德顺1");//无变化
	}
	
	public static void getVariables(){
		String data = (String) processEngine.getTaskService().getVariable("3008", "请假日期");
		String reason = (String) processEngine.getRuntimeService().getVariable("3003", "请假原因");
		System.out.println(data);
		System.out.println(reason);
	}
}
