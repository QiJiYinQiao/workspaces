package com.bpms.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Attachment;
import com.bpms.service.AttachmentService;
import com.bpms.util.DocConverter;
import com.bpms.view.model.DataModel;

/**
 * 在线预览
 * 
 * @author panchuanhe 2015/6/30
 */
@Namespace("/openOfficeAction")
@Action(value = "openOfficeAction")
public class OpenOfficeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AttachmentService attachmentService;

	private String attachmentId;

	/**
	 * 在线预览文档
	 */
	public void openOffice() {
		Attachment attachment = attachmentService
				.findAttachmentById(attachmentId);
		// 附件不存在的时候给前台提示信息
		if (null == attachment) {
			OutputJson(new DataModel("提示", "附件已经不存在！", false));
			return;
		}
		try {
			// 如果是图片则直接写到服务器上作为临时文件
			if (isImage(attachment.getFileType())) {
				saveTempImage(attachment);
			}
			// 可以在线预览的文件
			else if (isOfficeFile(attachment.getFileType())) {
				saveDocToSwf(attachment);
			} else {
				OutputJson(new DataModel("提示", "此文档不支持在线预览！", true, getData(
						"2", "")));
			}
		} catch (IOException e) {
			e.printStackTrace();
			OutputJson(new DataModel("提示", "服务器为您努力加载时，遇到小小问题，请您重试！", false));
		}
	}

	/**
	 * 将office文档转化为swf并降临时文件保存到服务器
	 * 
	 * @param attachment
	 *            附件
	 * @throws IOException
	 */
	private void saveDocToSwf(Attachment attachment) throws IOException {
		OutputStream os = null;
		try { // 获取存储的路径
			String contextPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "upload";

			// 源文件的名称
			String fileString = contextPath.replaceAll("\\\\", "/") + "/"
					+ attachment.getAttId() + "." + attachment.getFileType();

			// 将源文件转化为swf文件并保存到服务器
			DocConverter dc = new DocConverter(fileString);

			// 判断要转化的文件是否存在，如果存在则不需要进行转换，否则进行转换
			if (!dc.swfFileExists()) {
				// 如果源文件存在，创建原文文件，并进行转化
				if (!dc.docFileExists()) {
					// 保存源文件到服务器
					os = new FileOutputStream(new File(fileString));
					os.write(attachment.getAttData());
					os.close();
					// 转化
					dc.conver();
				}
			}

			// 获取转化后的swf文件的相对路径
			String swfPath = "upload";
			if (dc.getswfPath().lastIndexOf("/") != -1) {
				swfPath += dc.getswfPath().substring(
						dc.getswfPath().lastIndexOf("/"));
			} else {
				swfPath += dc.getswfPath();
			}

			// 返回信息
			OutputJson(new DataModel("提示", "转化成功！", true, getData("1", swfPath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将图片保存到临时服务器
	 * 
	 * @param attachment
	 *            附件
	 * @throws IOException
	 */
	private void saveTempImage(Attachment attachment) throws IOException {
		OutputStream os = null;
		try {
			// 获取存储的路径
			String contextPath = ServletActionContext.getServletContext()
					.getRealPath("/") + "upload";

			// 源文件的名称
			String fileString = contextPath.replaceAll("\\\\", "/") + "/"
					+ attachment.getAttId() + "." + attachment.getFileType();

			// 创建文件
			File file = new File(fileString);
			if (!file.exists()) {
				// 将原文件临时保存到服务器
				os = new FileOutputStream(file);
				os.write(attachment.getAttData());
				os.close();
			}
			// 获取临时文件的相对路径
			String imagePath = "upload"
					+ fileString.substring(fileString.lastIndexOf("/"));

			// 返回信息
			OutputJson(new DataModel("提示", "转化成功！", true, getData("0",
					imagePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否是图片
	 * 
	 * @param fileType
	 *            文件类型
	 * @return 是图片类型返回true否则返回false
	 */
	private boolean isImage(String fileType) {
		Pattern p = Pattern
				.compile("(JPEG|jpeg|JPG|jpg|GIF|gif|BMP|bmp|PNG|png){1}");
		Matcher m = p.matcher(fileType);
		return m.matches();
	}

	/**
	 * 判断是不是在线预览的支持的文件格式
	 * 
	 * @param fileType
	 *            文件类型
	 * @return 是在线预览的文件格式返回true否则返回false
	 */
	private boolean isOfficeFile(String fileType) {
		Pattern p = Pattern
				.compile("(xlsx|docx|xls|doc|pdf|txt|ppt|pptx|wps){1}");
		Matcher m = p.matcher(fileType);
		return m.matches();
	}

	/**
	 * 组织返回值的信息
	 * 
	 * @param fileType
	 *            0：图片，1：在线预览文档，2：其他 文件类型
	 * @param filePath
	 *            文件地址
	 * @return 返回组织好的信息
	 */
	private Map<String, Object> getData(String fileType, String filePath) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("fileType", fileType);
		data.put("filePath", filePath);
		return data;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

}
