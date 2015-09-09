package com.bpms.action;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

/**
 * 使用List上传多个文件
 * 
 * @author ljq
 *
 */
@Namespace("/uploadAction")
@Action(value = "uploadAction")
public class UploadAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> fileName; // 文件名称
	private List<File> file; // 上传的文件
	private List<String> fileFileName; // 源文件文件名称
	private List<String> fileContentType; // 文件类型
	private String savePath;

	public String upload() throws Exception {
		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		// 取得需要上传的文件数组
		List<File> files = getFile();
		if (files != null && files.size() > 0) {
			for (int i = 0; i < files.size(); i++) {
				System.out.println("rewrewrewr");
			}
		}

		OutputJson(getMessage(true));
		return null;
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

	/**
	 * 返回上传文件保存的位置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

}