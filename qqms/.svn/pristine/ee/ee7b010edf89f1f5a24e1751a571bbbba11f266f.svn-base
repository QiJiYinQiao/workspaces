
package com.qqms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qqms.dao.PublicDao;
import com.qqms.model.Investor;
import com.qqms.service.BaseService;




@Service("baseService")
/*@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)*/
public class BaseServiceImpl implements BaseService{
	@Autowired
    private PublicDao<Investor> publicDao;
    
	@Override
	public String findAreaDic(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t1.area_id as code,t1.area_name as text from t_area t1 where t1.parent_id = '"+code+"'");
		List<Object> list = publicDao.findBySQL(sql.toString());
		String msg = "[{\"code\":\"\",\"text\":\"\"}]";
		if(list!=null&&list.size()>0){
		    msg = "[";
			for (int i = 0; i <list.size(); i++) {
				Object[] g = (Object[])list.get(i);
				msg += "{\"code\":\""+g[0]+"\",\"text\":\""+g[1]+"\"},";
			}
			msg = msg.substring(0,msg.length()-1);
			msg += "]";
		}
		return msg;
	}
}
