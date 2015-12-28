package com.oasys.service;

import java.util.List;

import com.oasys.model.NoticeInfo;
import com.oasys.model.NoticeInfoAttach;
import com.oasys.model.VO.NoticeReceiveModel;
import com.oasys.util.PageUtil;


public interface NoticeInfoService
{
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月9日 下午8:26:04
	 * @Title:findNoticeInfoList
	 * @Description:TODO（这里描述这个方法的作用）查询通知列表（发件箱）
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	List<NoticeInfo> findNoticeInfoSendList(NoticeInfo noticeInfo,PageUtil pageUtil);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 上午10:43:20
	 * @Title:getCountNoticeInfoSendList
	 * @Description:TODO（这里描述这个方法的作用）发件箱通知总条数
	 * @param noticeInfo
	 * @return
	 * @throws:
	 */
	Long getCountNoticeInfoSendList(NoticeInfo noticeInfo);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午4:12:17
	 * @Title:getCountNoticeInfoReceiveList
	 * @Description:TODO（这里描述这个方法的作用）收件箱总条数
	 * @param noticeInfo
	 * @return
	 * @throws:
	 */
	Long getCountNoticeInfoReceiveList(Integer userId);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月18日 下午1:46:28
	 * @Title:findNoticeInfoReceiveList
	 * @Description:TODO（这里描述这个方法的作用）查询通知列表（收件箱）
	 * @param userId 收件人id
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	List<NoticeReceiveModel> findNoticeInfoReceiveList(Integer userId,PageUtil pageUtil);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月9日 下午8:29:35
	 * @Title:persistenceNoticeInfo
	 * @Description:TODO（这里描述这个方法的作用）持久化通知信息（增加或修改）
	 * @param noticeInfo
	 * @return
	 * @throws:
	 */
	boolean persistenceNoticeInfo(NoticeInfo noticeInfo,String userIds);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月9日 下午8:30:28
	 * @Title:delNoticeInfo
	 * @Description:TODO（这里描述这个方法的作用）删除通知信息
	 * @param ids
	 * @return
	 * @throws:
	 */
	boolean delNoticeInfo(String ids);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午4:40:09
	 * @Title:delNoticeInfoAttach
	 * @Description:TODO（这里描述这个方法的作用）删除收件箱信息
	 * @param ids
	 * @return
	 * @throws:
	 */
	boolean delNoticeInfoAttach(String ids);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午4:54:57
	 * @Title:findNoticeInfoAttachByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）根据通知编号查询当前登录用户的收件箱
	 * @param noticeNo 通知编号
	 * @return
	 * @throws:
	 */
	List<NoticeInfoAttach> findNoticeInfoAttachByNoticeNo(String noticeNo);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月10日 下午7:55:50
	 * @Title:receiptByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）根据通知编号进行回执
	 * @param noticeNo
	 * @return
	 * @throws:
	 */
	boolean receiptByNoticeNo(String noticeNo);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月11日 下午4:48:27
	 * @Title:getModelByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）根据通知编号加载通知详情
	 * @param noticeNo
	 * @return
	 * @throws:
	 */
	NoticeReceiveModel getModelByNoticeNo(String noticeNo);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月17日 下午5:48:52
	 * @Title:findReceiversByNoticeNo
	 * @Description:TODO（这里描述这个方法的作用）根据通知编号查询收件人
	 * @param noticeNo
	 * @throws:
	 */
	List<NoticeInfoAttach> findReceiversByNoticeNo(String noticeNo);
}
