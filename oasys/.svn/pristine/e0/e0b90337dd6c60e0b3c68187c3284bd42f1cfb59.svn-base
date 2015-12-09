package com.oasys.service;

import java.io.File;
import java.util.List;

import com.oasys.model.PpeStockInfo;
import com.oasys.util.PageUtil;


/**
 * 
 * @ClassName: PpeStockInfoService
 * @Description: TODO 固定资产库存信息表
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年12月1日 下午6:43:18
 *
 */
public interface PpeStockInfoService {
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月1日 下午6:48:12
	 * @Title:importExcelToDB
	 * @Description:TODO（这里描述这个方法的作用）把excel里边的数据写入数据库固定资产库存表里
	 * @param file 上传的excel文件
	 * @throws:
	 */
	public List<String> startImportExcelToDB(File file);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月2日 下午2:09:09
	 * @Title:isExistsPpeCode
	 * @Description:TODO（这里描述这个方法的作用） 判断固定资产编码，数据库里是否已存在
	 * @param ppeCode
	 * @return
	 * @throws:
	 */
	public boolean isExistsPpeCode(String ppeCode);
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月2日 下午4:08:57
	 * @Title:findPpeStockInfoList
	 * @Description:TODO（这里描述这个方法的作用）查询固定资产库存信息列表
	 * @param ppeStockInfo
	 * @return
	 * @throws:
	 */
	public List<PpeStockInfo> findPpeStockInfoList(PpeStockInfo ppeStockInfo,PageUtil pageUtil);
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年12月2日 下午4:25:56
	 * @Title:findCountPpeStockInfoList
	 * @Description:TODO（这里描述这个方法的作用）
	 * @param ppeStockInfo
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	public Long getCount(PpeStockInfo ppeStockInfo);
	
	/**
	 * 
	 * @author:lida
	 * @time:2015年12月3日 15:24:49
	 * @Title:getPpeStockInfo
	 * @Description:TODO（这里描述这个方法的作用）
	 * @param ppeStockInfo 根据实体中设置的属性加载记录
	 * @return PpeStockInfo 实体对象
	 * @throws:
	 */
	public PpeStockInfo getPpeStockInfo(PpeStockInfo ppeStockInfo);
	
	/**
	 * 
	 * @author:lida
	 * @time:2015年12月3日 15:25:40
	 * @Title:updPpeStockInfoStatus
	 * @Description:TODO（这里描述这个方法的作用）
	 * @param ppeCode 资产编号
	 * @param ppeStatus 更新状态 (1库存状态 2使用中 3已报废)
	 * @throws:
	 */
	public void updPpeStockInfoStatus(String ppeCode,String ppeStatus);
}
