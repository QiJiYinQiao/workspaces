package com.bpms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.service.CommonService;
import com.bpms.view.model.ComboBoxModel;

/**
 * 
 * 获取地区和字典表的共同方法的实现类<br>
 * 描述:主要是为前台页面的下拉列表框使用的
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Service("commonServiceImpl")
public class CommonServiceImpl implements CommonService {

	/** 注入baseDAO. */
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDAO baseDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBoxModel> findTextArr(String codeMyid) {
		String sql = "SELECT t1.dict_code as code,t1.dict_name as text from t_sys_dict t1 WHERE t1.PARENT_ID in ( SELECT t.code_id from t_sys_dict t where t.dict_code = '"
				+ codeMyid + "')";
		List<Object> list = baseDao.findBySQL(sql.toString());
		return castToListComBoxModel(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBoxModel> findAreaDic(String parentId) {
		String sql = "SELECT t1.area_id as code,t1.area_name as text from t_area t1 where t1.parent_id = '"
				+ parentId + "'";
		List<Object> list = baseDao.findBySQL(sql.toString());
		return castToListComBoxModel(list);
	}

	/**
	 * 将返回的结果转换为List<ComboBoxModel>
	 * 
	 * @param list
	 *            需要转化的list
	 */
	private List<ComboBoxModel> castToListComBoxModel(List<Object> list) {
		List<ComboBoxModel> comboxList = new ArrayList<ComboBoxModel>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] g = (Object[]) list.get(i);
				comboxList.add(new ComboBoxModel(String.valueOf(g[0]),
						(String) g[1]));
			}
		}
		return comboxList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findAreaDicByCode(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t1.area_id as code,t1.area_name as text from t_area t1 where t1.parent_id = '"+code+"'");
		List<Object> list = baseDao.findBySQL(sql.toString());
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

	@SuppressWarnings("unchecked")
	@Override
	public String findDicText(String codeMyid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t1.CODE_MYID as code,t1.NAME as text from t_sys_dict t1 WHERE t1.PARENT_ID in (");
		sql.append(" SELECT t.CODE_ID from tb_system_code t where t.CODE_MYID = '"+codeMyid+"') and t1.STATUS='A' order by t1.SORT");
		List<Object> list = baseDao.findBySQL(sql.toString());
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

	@Override
	public String findDictName(String dictPcode, String dictCode) {
		String sql = "SELECT scd.DICT_NAME name FROM t_sys_dict scd WHERE scd.PARENT_ID = (SELECT sd.CODE_ID FROM t_sys_dict sd WHERE sd.DICT_CODE = '"+dictPcode+"') AND scd.DICT_CODE = '"+dictCode+"'";
		List<Object> list = baseDao.findBySQL(sql);
		if(list.size()>0){
			return (String) list.get(0);
		}
		return null;
	}
}
