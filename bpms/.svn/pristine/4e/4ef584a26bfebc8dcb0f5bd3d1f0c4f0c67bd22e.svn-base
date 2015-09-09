package com.bpms.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;

import com.bpms.activiti.model.ActReDeployment;
import com.bpms.activiti.model.ActReProcDef;
import com.bpms.view.model.WorkFlowBean;

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
	public abstract Deployment saveDeployProcess(File file, String fileName)
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
	 * 根据任务的id获取当前执行的流程图
	 * 
	 * @param taskId
	 *            任务的id
	 * @throws IOException
	 */
	public abstract void getDiagramResourceByTaskId(String taskId)
			throws IOException;

	/**
	 * 根据根据订单的id获取formkey属性值,formkey的属性值为页面名称
	 * 
	 * @param id
	 * @return
	 */
	public abstract String findTaskFormKeyByTaskId(String taskId);

}
