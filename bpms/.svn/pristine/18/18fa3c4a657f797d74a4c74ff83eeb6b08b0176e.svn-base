package com.bpms.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Attachment;
import com.bpms.model.AuditinforecordAndAttachment;
import com.bpms.model.vo.AttachmentModel;
import com.bpms.service.AttachmentService;
import com.bpms.util.Constants;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;

/**
 * 文件上传控制器
 */
@Namespace("/attachment")
@Action(value = "attachmentAction")
public class AttachmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 自动注入service. */
	@Autowired
	private AttachmentService attachmentService;

	private String fileName; // 文件名称
	private List<File> file; // 上传的文件
	private List<String> fileFileName; // 源文件文件名称
	private List<String> fileContentType; // 文件类型
	private String attType;// 附件类型
	private String loanOrderId; // 贷款订单ID
	private String auditId; // 稽核信息ID
	private String attId; // 附件id
	private String orderType; // 订单类型
	private String userId;// 用户的id
	private String investOrderId;//投资订单id
	private String orderId;//订单id
	private String isDetail;//是否是附件列表详情 1是


	public String saveAttachment() {
		String msg = "上传失败";
		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 返回已保存附件列表
		List<Map<String, String>> attachmentIdList = new ArrayList<Map<String, String>>();
		// 取得需要上传的文件数组
		List<File> files = getFile();
		if (files != null && files.size() > 0) {
			for (int i = 0; i < files.size(); i++) {
				Attachment attachment = new Attachment();
				Map<String, String> map = new HashMap<String, String>();// 存放附件信息
				byte[] bytes = getFileToByte(files.get(i));
				attachment.setAttData(bytes);
				attachment.setAttName(getFileName().get(i));
				attachment.setAttType(getAttType().get(i));
				attachment.setOrderId(loanOrderId);
				attachment.setCreator(String.valueOf(Constants.getCurrendUser()
						.getUserId()));
				attachment.setOrderType("borrowerOrder");
				attachment.setFileType(FilenameUtils.getExtension(fileFileName
						.get(i)));
				// 保存前查询，如果有同名，文件则更新
				Attachment existatt = attachmentService.findSameByNT(
						attachment, auditId);
				if (null == existatt) {
					attachmentService.persistenceAttachment(attachment);
				} else {
					existatt.setAttData(attachment.getAttData());
					attachmentService.persistenceAttachment(existatt);
				}
				map.put("attId", attachment.getAttId());
				map.put("attName", attachment.getAttName());
				map.put("attType", attachment.getAttType());
				attachmentIdList.add(map);
				if (!"noauditId".equals(auditId)) {
					// 如果不是初始页面附件添加，才保存附件稽核关系
					AuditinforecordAndAttachment aaa = new AuditinforecordAndAttachment();
					aaa.setAttId(attachment.getAttId());
					aaa.setAuditId(auditId);
					attachmentService.persistenceAttachmentAndAuditInfo(aaa);
				}
			}
			msg = "上传成功";
		}
		OutputJson(new DataModel("", msg, true, attachmentIdList), "text/html");
		return null;
	}

	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月28日 下午1:30:57
	 * @Title:saveInvestAttachment
	 * @Description:TODO 投资业务上传附件（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String saveInvestAttachment() {
		String msg = "上传失败";
		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 返回已保存附件列表
		List<Map<String, String>> attachmentIdList = new ArrayList<Map<String, String>>();
		// 取得需要上传的文件数组
		List<File> files = getFile();
		if (files != null && files.size() > 0) {
			for (int i = 0; i < files.size(); i++) {
				Attachment attachment = new Attachment();
				Map<String, String> map = new HashMap<String, String>();// 存放附件信息
				byte[] bytes = getFileToByte(files.get(i));
				attachment.setAttData(bytes);
				attachment.setAttName(getFileName().get(i));
				attachment.setAttType(getAttType().get(i));
				attachment.setOrderId(investOrderId);
				attachment.setCreator(String.valueOf(Constants.getCurrendUser()
						.getUserId()));
				attachment.setOrderType("investorOrder");
				attachment.setFileType(FilenameUtils.getExtension(fileFileName
						.get(i)));
				// 保存前查询，如果有同名，文件则更新
				Attachment existatt = attachmentService.findSameAttachment(attachment);
				if (null == existatt) {
					attachmentService.persistenceAttachment(attachment);
				} else {
					existatt.setAttData(attachment.getAttData());
					attachmentService.persistenceAttachment(existatt);
				}
				map.put("attId", attachment.getAttId());
				map.put("attName", attachment.getAttName());
				map.put("attType", attachment.getAttType());
				attachmentIdList.add(map);
			}
			msg = "上传成功";
		}
		OutputJson(new DataModel("", msg, true, attachmentIdList), "text/html");
		return null;
	}
	/**
	 * 根据用户的id和订单的id获取附件
	 */
	public void findAttachmentListByUserIdAndOrderId() {
		List<AttachmentModel> attachmentList = attachmentService
				.findAttachmentList(userId, loanOrderId);
		OutputJson(new GridModel(attachmentList, (long) attachmentList.size()));
	}

	/**
	 * 根据id删除附件
	 */
	public void delAttachment() {
		boolean b = attachmentService.delAttachAuditRL(attId);
		b = attachmentService.delAttachment(attId);
		String message = "删除失败";
		if (b) {
			message = "删除成功";
		}
		OutputJson(new DataModel("提示", message, b), "text/html");
	}

	/**
	 * 根据登陆用户，订单类型，稽核信息ID查询附件信息
	 */
	public String findAttachmentByULA() {
		List<AttachmentModel> list = attachmentService.findAttachmentByULA(
				String.valueOf(Constants.getCurrendUser().getUserId()),
				orderType, auditId, loanOrderId, isDetail);
		OutputJson(list);
		return null;
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月28日 下午1:51:26
	 * @Title:findAttachmentByOrderTypeAndOrderId
	 * @Description:TODO 查询投资上传附件列表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findAttachmentByOrderTypeAndOrderId() {
		List<AttachmentModel> list = attachmentService.findAttachmentByOrderTypeAndOrderId(
				String.valueOf(Constants.getCurrendUser().getUserId()),
				orderType,investOrderId);
		OutputJson(list);
		return null;
	}

	/**
	 * 下载附件
	 * 
	 * @return
	 */
	public String downloadAttachment() {
		Attachment attachment = attachmentService.findAttachmentById(attId);
		if (null != attachment) {
			try {
				// 设置头文件
				ServletActionContext.getResponse().setHeader(
						"Content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(attachment.getAttName()
										+ "-" + attachment.getAttId() + "."
										+ attachment.getFileType(), "utf-8"));
				// 写入流中
				IOUtils.write(attachment.getAttData(), ServletActionContext
						.getResponse().getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * 将文件转换成二进制数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getFileToByte(File file) {
		byte[] by = new byte[(int) file.length()];
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			byte[] bb = new byte[2048];
			int ch;
			ch = is.read(bb);
			while (ch != -1) {
				bytestream.write(bb, 0, ch);
				ch = is.read(bb);
			}
			by = bytestream.toByteArray();
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return by;
	}
	/**
	 * @Title: findAllAttachmentList 
	 * @Description: TODO 查询莫订单附件列表
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String findAllAttachmentList(){
		GridModel gridModel = new GridModel();
		gridModel.setRows(attachmentService.findAllAttachmentList(userId,orderType,orderId));
		OutputJson(gridModel);
		return null;
	}
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAttId() {
		return attId;
	}

	public void setAttId(String attId) {
		this.attId = attId;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public List<String> getAttType() {
		return Arrays.asList(attType.split(","));
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public List<String> getFileName() {
		return Arrays.asList(fileName.split(","));
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getInvestOrderId() {
		return investOrderId;
	}

	public void setInvestOrderId(String investOrderId) {
		this.investOrderId = investOrderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}
	
}
