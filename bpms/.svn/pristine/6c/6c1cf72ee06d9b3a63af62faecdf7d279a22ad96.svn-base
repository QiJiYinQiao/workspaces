package com.bpms.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.activiti.model.ActReDeployment;
import com.bpms.activiti.model.ActReProcDef;
import com.bpms.service.WorkFlowService;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.bpms.view.model.WorkFlowBean;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 业务流程处理的action
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Namespace("/workflow")
@Action(value = "workflowAction")
@Result(name = "procRep", location = "/pages/workflow/ProcessRepository.jsp")
public class WorkFlowAction extends BaseAction implements
		ModelDriven<WorkFlowBean> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 注入service. */
	@Autowired
	private WorkFlowService workflowService;

	/** modelDriven. */
	private WorkFlowBean workFlowBean = new WorkFlowBean();

	@Override
	public WorkFlowBean getModel() {
		return workFlowBean;
	}

	/**
	 * 获取全部的部署的流程
	 * 
	 * @return
	 */
	public String getAllProcessDeployment() {
		Long total = workflowService
				.findAllProcessDeploymentCount(workFlowBean);
		List<ActReDeployment> rows = workflowService
				.findAllProcessDeployment(workFlowBean);
		OutputJson(new GridModel(rows, total));
		return null;
	}

	/**
	 * 删除部署的流程
	 * 
	 * @return
	 */
	public String delProcessDeployment() {
		workflowService.deleteProcessDeployment(workFlowBean.getDeploymentId());
		OutputJson(new DataModel("提示", "删除成功!", true));
		return null;
	}

	/**
	 * 发布流程
	 * 
	 * @throws Exception
	 */
	public String deployProcess() {
		try {
			// 判断文件类型是不是zip格式
			String originalFileName = workFlowBean.getFileFileName().get(0);
			String extension = FilenameUtils.getExtension(originalFileName);
			if ("zip".equals(extension)) {
				this.workflowService.saveDeployProcess(workFlowBean.getFile()
						.get(0), workFlowBean.getFileName());
				OutputJson(new DataModel("提示", "流程发布成功!!", true), "text/html");
			}
			OutputJson(new DataModel("提示", "文件类型不匹配,请你上传zip/bar格式!!", false),
					"text/html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询全部的流程定义实例
	 * 
	 * @return 流程定义列表
	 */
	public String getAllProcessDefinition() {
		List<ActReProcDef> list = this.workflowService
				.findAllProcessDefination(workFlowBean);
		Long total = this.workflowService
				.findAllProcessDefinationCount(workFlowBean);
		OutputJson(new GridModel(list, total));
		return null;
	}

	/**
	 * 获取流程定义的png图片
	 * 
	 * @return
	 */
	public String getDiagramResource() {
		InputStream in = this.workflowService
				.findDefinationResourceAsStream(workFlowBean);
		// 从response对象获取输出流
		try {
			OutputStream out = ServletActionContext.getResponse()
					.getOutputStream();
			// 将输入流中的数据读取出来，写到输出流中
			for (int b = -1; (b = in.read()) != -1;) {
				out.write(b);
			}
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
