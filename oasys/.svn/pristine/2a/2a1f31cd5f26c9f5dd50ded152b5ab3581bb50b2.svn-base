package com.oasys.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oasys.model.Attachment;
import com.oasys.model.VO.AttachmentModel;
import com.oasys.service.AttachmentService;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.SaveUploadFileUtil;
import com.oasys.util.ZipUtils;
import com.oasys.viewModel.DataModel;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *
 */
@Controller
@RequestMapping("/attachmentController")
public class AttachmentController extends BaseController{
	private static Logger logger = Logger.getLogger(AttachmentController.class);
	
	/** 自动注入service. */
	@Autowired
	private AttachmentService attachmentService;
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年10月12日 上午11:21:49
	 * @Title:saveAttachment
	 * @Description:TODO（这里描述这个方法的作用）上传附件
	 * @param httpServletResponse
	 * @param file 取得需要上传的文件数组
	 * @param fileFileName 源文件文件名称
	 * @param appNo 申请编号
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/saveAttachment",method=RequestMethod.POST)
	public String saveAttachment(MultipartHttpServletRequest  request ,HttpServletResponse httpServletResponse,String appNo,String detailId) throws IllegalStateException, IOException {
		String msg = "上传失败";
		List<MultipartFile> files = request.getFiles("file");
		// 取得需要上传的文件数组
		if (Collections.listIsNotEmpty(files)) {
			Attachment attachment = new Attachment();
			for (int i = 0; i < files.size(); i++) {
				String originalFilename = files.get(i).getOriginalFilename();
				// 文件在文件服务器上的相对路径
				//String path = SaveUploadFileUtil.saveFile2FileSystem(originalFilename, files.get(i).getBytes());
				String path = SaveUploadFileUtil.saveFile2FileSystem4App(appNo, originalFilename, files.get(i).getBytes());
				attachment.setAttURL(path);
				attachment.setAttName(originalFilename);
				attachment.setAppRegNo(appNo);
				if(StringUtils.isNotBlank(detailId)){
					attachment.setDetailId(Integer.parseInt(detailId));
				}
				attachment.setCreator(String.valueOf(Constants.getCurrendUser()
						.getUserId()));
				attachment.setFileType(FilenameUtils.getExtension(originalFilename));
				attachment.setAttDesc(originalFilename);
				attachment.setCreateDatetime(new Date());
				// 保存前查询，如果有同名，文件则更新
				Attachment existatt = attachmentService.findSameByNT(
						attachment);
				// 判断文件是否存在，如果存在则进行更新图片的相对地址，同时将原来的图片删除
				if (null == existatt) {
					attachmentService.persistenceAttachment(attachment);
				} else {
					SaveUploadFileUtil.deleteFlieSystemFileByPath(existatt
							.getAttURL());
					existatt.setAttURL(path);
					attachmentService.persistenceAttachment(existatt);
				}
			}
			msg = "上传成功";
		}
		OutputJson(httpServletResponse,new DataModel("", msg, true), "text/html");
		return null;
	}
	
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年10月12日 下午4:24:50
	 * @Title:findAttachmentByULA
	 * @Description:TODO（这里描述这个方法的作用）根据登陆用户，申请编号查询附件信息
	 * @param resp
	 * @param userId
	 * @param appNo
	 * @param isDetail
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/findAttachmentByULA",method=RequestMethod.POST)
	public String findAttachmentByULA(HttpServletResponse resp,String userId,String appNo,String isDetail,String detailId) {
		List<AttachmentModel> list = new ArrayList<AttachmentModel>();
		// 如果用户ID不为空，查看该用户所上传附件
		if (null != userId && StringUtils.isNotBlank(userId)) {
			list = attachmentService.findAttachmentList(userId, appNo,detailId);
		} else {
			list = attachmentService.findAttachmentByULA(
					String.valueOf(Constants.getCurrendUser().getUserId()),
					 appNo, isDetail,detailId);
		}

		// 查询所有附件类型

		Map<String, List<AttachmentModel>> group = new HashMap<String, List<AttachmentModel>>();
		for (AttachmentModel acm : list) {
			String key = "附件";
			if (group.containsKey(key)) {
				group.get(key).add(acm);
			} else {
				List<AttachmentModel> photoTypeList = new ArrayList<AttachmentModel>();
				photoTypeList.add(acm);
				group.put(key, photoTypeList);
			}
		}
		OutputJson(resp,group);
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年10月12日 下午4:10:20
	 * @Title:deleteAttachmentByUrl
	 * @Description:TODO（这里描述这个方法的作用）根据url删除附件
	 * @param resp
	 * @param photoAttids 附件id
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/deleteAttachmentByUrl",method=RequestMethod.POST)
	public void deleteAttachmentByUrl(HttpServletResponse resp,String photoAttids) {
		boolean b = false;
		String message = "删除失败";
		String basicUrl = SaveUploadFileUtil.getFileSystemURL();
		if (StringUtils.isNotBlank(photoAttids)) {
			String[] attIdList = photoAttids.split(",");
			for (int i = 0; i < attIdList.length; i++) {
				Attachment att = attachmentService.findAttachmentById(attIdList[i]);
				SaveUploadFileUtil.deleteFlieSystemFileByPath(att.getAttURL()
						.replace(basicUrl, ""));
				b = attachmentService.delAttachment(attIdList[i]);
			}
			if (b) {
				message = "删除成功";
			}
		}
		OutputJson(resp,new DataModel("提示", message, b), "text/html");
	}
	
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年10月12日 下午4:19:33
	 * @Title:downloadAttachment
	 * @Description:TODO（这里描述这个方法的作用）附件下载
	 * @param httpServletResponse
	 * @param photoAttids
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/downloadAttachment",method=RequestMethod.POST)
	public String downloadAttachment(HttpServletResponse httpServletResponse,String photoAttids) {
		// 输出流
		ZipOutputStream zos = null;
		// 判断前台传过来的订单ID是否为空，不为空的话进行分割
		if (StringUtils.isNotBlank(photoAttids)) {
			String[] attidArray = photoAttids.split(",");// 所需下载文件ID数组
			// 判断如果不为空，进行打包下载
			if (attidArray != null && attidArray.length > 0) {
				try {
					// 设置头文件
					httpServletResponse.setHeader(
							"Content-disposition",
							"attachment;filename="
							+ URLEncoder.encode(new  SimpleDateFormat("yyyyMMddHHssSSS").format(new  Date())+"附件.zip", "utf-8"));
					// 获取输出了流
					zos = new ZipOutputStream(httpServletResponse.getOutputStream());
					for (String attId : attidArray) {
						Attachment attachment = attachmentService.findAttachmentById(attId);
						if (null != attachment) {
							ZipUtils.writeZip(attachment.getAttName(), SaveUploadFileUtil.getBytesFormFileSystemByPath(attachment.getAttURL()), zos);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(zos!=null){
						try {
							zos.flush();
							zos.close();
						} catch (IOException e) {
							zos = null;
							e.printStackTrace();
						} 
					}
				}
			}
		}
		return null;

	}

}


