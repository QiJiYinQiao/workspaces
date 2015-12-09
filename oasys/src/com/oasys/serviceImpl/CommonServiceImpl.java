package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.Parameter;
import com.oasys.service.CommonService;
import com.oasys.util.Collections;
import com.oasys.viewModel.ComboBoxModel;
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private PublicDao publicDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBoxModel> findDicList(String codeMyid) {
		String sql = "SELECT t1.dict_code as code,t1.dict_name as text from qqms.t_sys_dict t1 WHERE t1.`STATUS`='A' AND t1.PARENT_ID in ( SELECT t.code_id from qqms.t_sys_dict t where t.dict_code = '"
				+ codeMyid + "')";
		List<Object> list = publicDao.findBySQL(sql.toString());
		return castToListComBoxModel(list);
	}
	/**
	 * 将返回的结果转换为List<ComboBoxModel>
	 * @Title: castToListComBoxModel 
	 * @Description: TODO
	 * @param @param list
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<ComboBoxModel>
	 * @date 2015年9月18日 下午5:25:36
	 * @throws
	 */
	private List<ComboBoxModel> castToListComBoxModel(List<Object> list) {
		List<ComboBoxModel> comboxList = new ArrayList<ComboBoxModel>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] g = (Object[]) list.get(i);
				comboxList.add(new ComboBoxModel(String.valueOf(g[0]),(String) g[1]));
			}
		}
		return comboxList;
	}
	@Override
	public List<ComboBoxModel> findDicByParentId(String codeMyid,String parentId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT s.dict_code as code,s.dict_name as text FROM qqms.t_sys_dict s WHERE s.PARENT_ID IN ( ");
		sql.append("SELECT CODE_ID FROM qqms.t_sys_dict s1 WHERE s1.PARENT_ID IN ( ");
		sql.append("SELECT CODE_ID FROM qqms.t_sys_dict s2 WHERE s2.DICT_CODE = '"+codeMyid+"' ");
		sql.append(") AND s1.DICT_CODE = '"+parentId+"')");
		List<Object> list = publicDao.findBySQL(sql.toString());
		return castToListComBoxModel(list);
	}
	@Override
	public Parameter findParameterByParmCode(String parmCode) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Parameter p where p.parmCode = '"+parmCode+"'");
		List<Parameter> list = publicDao.find(hql.toString());
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
