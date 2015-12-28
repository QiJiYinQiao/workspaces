package com.oasys.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.VO.MyAppModel;
import com.oasys.service.MyAppService;
import com.oasys.util.Constants;

@Service(value="myAppService")
public class MyAppServiceImpl implements MyAppService{
	@Autowired
	private PublicDao<MyAppModel> publicDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MyAppModel> findMyAppList() {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.WEEK_OF_YEAR, -1);
		Date dateFrom = cl.getTime();
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT tt.*,COUNT(tt.NAME_) FROM ");
		sb.append("( SELECT t1.NAME_,t1.RESOURCE_NAME_,t1.DGRM_RESOURCE_NAME_,IFNULL(t2.BUSINESS_KEY_,'') AS businesskey,t2.start_time_,IFNULL(SUBSTRING_INDEX(t2.business_key_, '.', 1),'') AS businesskeypre FROM ");
		sb.append("( SELECT * FROM act_hi_procinst WHERE id_ IN ( SELECT execution_id_ FROM act_hi_varinst ");
		sb.append(" WHERE name_ = 'curUserId' AND text_ = '"+Constants.getCurrendUser().getUserId()+"' ) ");
/*		sb.append(" AND START_TIME_>='"+sdf.format(dateFrom)+"'");*/
		sb.append(" AND BUSINESS_KEY_ IS NOT NULL) t2 LEFT JOIN oasys.act_re_procdef t1 ON t2.PROC_DEF_ID_ = t1.ID_) tt GROUP BY tt.NAME_ ORDER BY tt.start_time_ DESC");
		List<Object> objList = publicDao.findBySQL(sb.toString());
		List<MyAppModel> modelList = new ArrayList<MyAppModel>();
		for(int i=0;i<objList.size();i++){
			Object[] obj =(Object[])objList.get(i);
			MyAppModel model = new MyAppModel();
			model.setAppName(obj[0]!=null?String.valueOf(obj[0]):"");
/*			model.setResourceName(obj[1]!=null?String.valueOf(obj[1]):"");
			model.setDgrmResourceName(obj[2]!=null?String.valueOf(obj[2]):"");
			model.setBusinesskey(obj[3]!=null?String.valueOf(obj[3]):"");
			model.setStartTime(obj[4]!=null?String.valueOf(obj[4]):"");*/
			model.setBusinesskeyPre(obj[5]!=null?String.valueOf(obj[5]):"");
			model.setCountSort(obj[6]!=null?Integer.valueOf(String.valueOf(obj[6])):0);
			modelList.add(model);
		}
		return modelList;
	}


	@Override
	public List<MyAppModel> findMyAppListSort(String key) {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.WEEK_OF_YEAR, -1);
		Date dateFrom = cl.getTime();
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "SELECT t1.NAME_,t1.RESOURCE_NAME_,t1.DGRM_RESOURCE_NAME_,t2.BUSINESS_KEY_,t2.start_time_,t2.end_time_,SUBSTRING_INDEX(t2.business_key_, '.', 1) AS businesskeypre,SUBSTRING_INDEX(t2.business_key_, '.', -1) AS ywID FROM ( SELECT * FROM `act_hi_procinst` WHERE id_ IN ( SELECT execution_id_ FROM `act_hi_varinst` "
		+" WHERE name_ = 'curUserId' AND text_ = '"+Constants.getCurrendUser().getUserId()+"' "
		+" )  AND START_TIME_>='"+sdf.format(dateFrom)+"') t2 LEFT JOIN oasys.`act_re_procdef` t1 ON t2.PROC_DEF_ID_ = t1.ID_ "
		+" WHERE t1.key_ = '"+key+"'";
		List<Object> objList = publicDao.findBySQL(sql);
		List<MyAppModel> modelList = new ArrayList<MyAppModel>();
		for(int i=0;i<objList.size();i++){
			Object[] obj =(Object[])objList.get(i);
			MyAppModel model = new MyAppModel();
			model.setAppName(obj[0]!=null?String.valueOf(obj[0]):"");
			model.setResourceName(obj[1]!=null?String.valueOf(obj[1]):"");
			model.setDgrmResourceName(obj[2]!=null?String.valueOf(obj[2]):"");
			model.setBusinesskey(obj[3]!=null?String.valueOf(obj[3]):"");
			model.setStartTime(obj[4]!=null?String.valueOf(obj[4]):"");
			model.setEndTime(obj[5]!=null?String.valueOf(obj[5]):"");
			model.setBusinesskeyPre(obj[6]!=null?String.valueOf(obj[6]):"");
			model.setYwid(obj[7]!=null?String.valueOf(obj[7]):"");
			modelList.add(model);
		}
		return modelList;
	}
}
