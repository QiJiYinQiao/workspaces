package com.oasys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.StampTurnoverAppAttach;
import com.oasys.service.StampTurnoverAppAttachService;
/**   
 * @Title: StampTurnoverAppAttachServiceImpl
 * @Package com.oasys.serviceImpl
 * @Description: 印章移交附加表Service实现类
 * @author lida  
 * @date 2015/9/21
 * @version V1.0   
 */
@Service
public class StampTurnoverAppAttachServiceImpl implements StampTurnoverAppAttachService {

	@Autowired
	PublicDao<StampTurnoverAppAttach> publicDao;
	
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 新增印章移交附加信息
	 * @param ppeAttach 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/21
	 */
	@Override
	public void saveOrUpdatePpeEntity(StampTurnoverAppAttach ppeAttach) {
		// TODO Auto-generated method stub
		publicDao.saveOrUpdate(ppeAttach);
	}

	/** 
	 * @Title: findPpeAttachList
	 * @Description: 根据appNo加载印章移交附加信息
	 * @param String appNo 申请编号
	 * @author lida
	 * @return StampTurnoverAppAttach
	 * @date 2015/9/23
	 */
	@Override
	public List<StampTurnoverAppAttach> findPpeAttachList(String appNo) {
		String hql = "from StampTurnoverAppAttach where appNo = '"+appNo+"'";
		List<StampTurnoverAppAttach> list = publicDao.find(hql);
		return list;
	}
	
	
	/** 
	 * @Title: delStampTurnoverAttach
	 * @Description: 根据ID删除印章移交附加表信息 返回受影响行数
	 * @param ids 附加表id 支持多个一次删除
	 * @author lida
	 * @return Integer
	 * @date 2015/9/22
	 */
	@Override
	public Integer delStampTurnoverAttach(String ids) {
		// TODO Auto-generated method stub
		return publicDao.executeHql("delete from StampTurnoverAppAttach where id in ("+ids+")");
	}

	
	/** 
	 * @Title: getStampTurnoverAttachByID
	 * @Description: 根据ID加载印章移交记录
	 * @param id id标识
	 * @author lida
	 * @return StampTurnoverAppAttach
	 * @date 2015/9/22
	 */
	@Override
	public StampTurnoverAppAttach getStampTurnoverAttachByID(Integer id) {
		return publicDao.get(StampTurnoverAppAttach.class, id);
	}
	
}
