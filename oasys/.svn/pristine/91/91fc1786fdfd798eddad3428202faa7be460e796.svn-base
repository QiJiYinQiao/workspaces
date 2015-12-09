package com.oasys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.service.PPEScrapAppAttachService;
/**   
 * @Title: PPEScrapAppAttachServiceImpl
 * @Package com.oasys.serviceImpl
 * @Description: 固定资产报废附加表Service实现类
 * @author lida  
 * @date 2015/9/21
 * @version V1.0   
 */
@Service
public class PPEScrapAppAttachServiceImpl implements PPEScrapAppAttachService {

	@Autowired
	PublicDao<PpeScrapAppAttach> publicDao;
	
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 新增固定资产报废附加信息
	 * @param ppeAttach 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/21
	 */
	@Override
	public void saveOrUpdatePpeEntity(PpeScrapAppAttach ppeAttach) {
		// TODO Auto-generated method stub
		publicDao.saveOrUpdate(ppeAttach);
	}

	/** 
	 * @Title: findPpeAttachList
	 * @Description: 根据appNo加载固定资产报废附加信息
	 * @param String appNo 申请编号
	 * @author lida
	 * @return PpeScrapAppAttach
	 * @date 2015/9/23
	 */
	@Override
	public List<PpeScrapAppAttach> findPpeAttachList(String appNo) {
		String hql = "from PpeScrapAppAttach where appNo = '"+appNo+"'";
		List<PpeScrapAppAttach> list = publicDao.find(hql);
		return list;
	}
	
	
	/** 
	 * @Title: delPpeScrapAttach
	 * @Description: 根据ID删除固定资产报废附加表信息 返回受影响行数
	 * @param ids 附加表id 支持多个一次删除
	 * @author lida
	 * @return Integer
	 * @date 2015/9/22
	 */
	@Override
	public Integer delPpeScrapAttach(String ids) {
		// TODO Auto-generated method stub
		return publicDao.executeHql("delete from PpeScrapAppAttach where id in ("+ids+")");
	}

	
	/** 
	 * @Title: getPpeScrapAttachByID
	 * @Description: 根据ID加载固定资产报废记录
	 * @param id id标识
	 * @author lida
	 * @return PpeScrapAppAttach
	 * @date 2015/9/22
	 */
	@Override
	public PpeScrapAppAttach getPpeScrapAttachByID(Integer id) {
		return publicDao.get(PpeScrapAppAttach.class, id);
	}
	
}
