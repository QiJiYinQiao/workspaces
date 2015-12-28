package com.oasys.service.workFlow;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;

import com.oasys.model.ActReDeployment;
import com.oasys.model.ActReProcDef;
import com.oasys.viewModel.WorkFlowBean;



public interface WorkFlowService {
	/**
	 * 查询部署流程
	 * 
	 * @param bean
	 *            查询部署流程实例的参数
	 * @return 流程实例列表
	 */
	public abstract List<ActReDeployment> findAllProcessDeployment(
			WorkFlowBean bean);

	/**
	 * 查询部署流程的总个数
	 * 
	 * @param bean
	 *            查询部署流程实例的个数
	 * @return 流程实例的个数
	 */
	public abstract Long findAllProcessDeploymentCount(WorkFlowBean bean);

	/**
	 * 级联删除部署流程
	 * 
	 * @param deploymentId
	 *            部署流程id
	 */
	public abstract void deleteProcessDeployment(String deploymentId);

	/**
	 * 查询流程定义对象
	 * 
	 * @param bean
	 *            查询流程定义参数
	 * @return 流程定义列表
	 */
	public abstract List<ActReProcDef> findAllProcessDefination(
			WorkFlowBean bean);

	/**
	 * 查询流程定义对象个数
	 * 
	 * @param bean
	 *            查询流程定义参数
	 * @return 流程定义对象的个数
	 */
	public abstract Long findAllProcessDefinationCount(WorkFlowBean bean);

	/**
	 * 部署上传的流程zip文件
	 * 
	 * @param file
	 *            上传流程zip文件
	 * @param fileName
	 *            流程名称
	 * @return 部署流程信息
	 * @throws IOException
	 */
	public abstract Deployment saveDeployProcess(byte[] bytes, String fileName)
			throws IOException;

	/**
	 * 获取定义流程的图片信息
	 * 
	 * @param bean
	 *            获取定义流程图片的信息的参数1.部署id,2.图片名称.
	 * 
	 */
	public abstract InputStream findDefinationResourceAsStream(WorkFlowBean bean);

	/**
	 * 
	 * @time:2015年9月22日 下午2:09:30
	 * @Title:getDiagramResourceByTaskId
	 * @Description:TODO（这里描述这个方法的作用）根据任务的id获取当前执行的流程图
	 * @param response
	 * @param resourceName 图片名称
	 * @param taskId 任务id
	 * @throws IOException
	 * @throws:
	 */
	public abstract void getDiagramResourceByTaskId(HttpServletResponse response,String resourceName,String taskId)
			throws IOException;

	/**
	 * 根据根据订单的id获取formkey属性值,formkey的属性值为页面名称
	 * 
	 * @param id
	 * @return
	 */
	public abstract String findTaskFormKeyByTaskId(String taskId);

	/**
	 * 获取运行中的task指定的候选人列表，不包含自身
	 * 
	 * @param taskId
	 *            任务的ID
	 * @return 人员列表
	 */
	public abstract List<String> findRunTaskHandlePersons(String taskId);

	/**
	 * 获取运行中的task指定的所有候选人和受理人ID，包含自身并去重复
	 * 
	 * @param taskId
	 *            任务的ID
	 * @return 人员列表
	 */
	public abstract List<String> findRunTaskAllHandlePersons(String taskId);
	
	/**
	 * @param taskId
	 *            当前任务ID
	 * @param variables
	 *            流程变量
	 * @param activityId
	 *            流程转向执行任务节点ID<br>
	 *            此参数为空，默认为提交操作
	 * @throws Exception
	 */
	public abstract void commitProcess(String taskId, Map<String, Object> variables,String activityId) throws Exception;
	
	
	/** 根据taskID获取流程图中task节点ID */
	public String getTaskDefKeyByTaskID(String taskID);
	
	/** 根据executionID获取taskID*/
	public String getTaskIDByExecutionID(String executionID);
	
	/**
	 * 
	 * @time:2015年10月28日 下午2:21:06
	 * @Title:commitProcessByExecutionId
	 * @Description:TODO（这里描述这个方法的作用）流程转向自由跳转
	 * @param excutionId 执行对象id
	 * @param activityId  流程转向执行任务节点ID<br>
	 * @throws Exception
	 * @throws:
	 */
	public abstract void commitProcessByExecutionId(String excutionId,String activityId) throws Exception;
	
	public void getDiagramResourceByBusinessKey(HttpServletResponse response,String processKey,String resName,String busID) throws IOException;
}
