package com.oasys.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.Parameter;
import com.oasys.service.SystemParameterService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ParameterModel;

@Service("systemParameterService")
public class SystemParameterServiceImpl implements SystemParameterService
{
	private PublicDao<Parameter> publicDao;
	@Autowired
	public void setPublicDao(PublicDao<Parameter> publicDao )
	{
		this.publicDao = publicDao;
	}
	
	public  List<ParameterModel> findParameterList(String type,PageUtil pageUtil)
	{
		String hql="from Parameter t where 1=1";
		 List<Parameter> temp = publicDao.find(hql, pageUtil);
		 List<ParameterModel> list2=new ArrayList<ParameterModel>();
		 for (Parameter p : temp)
		{
			ParameterModel pm=new ParameterModel();
			try
			{
				PropertyUtils.copyProperties(pm, p);
				list2.add(pm);
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			} catch (InvocationTargetException e)
			{
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list2;
	}
	public boolean persistenceParameter(Map<String, List<Parameter>> map) 
	{
		this.addParameter(map.get("addList"));
		this.updParameter(map.get("updList"));
		this.delParameter(map.get("delList"));
		return true;
	}
	
	public boolean addParameter(List<Parameter> addlist) 
	{
		
		if (addlist!=null&&addlist.size()!=0) {
			ShiroUser users = Constants.getCurrendUser();
			for (Parameter companyInfo : addlist) {
				companyInfo.setCreateDate(new Date());
				companyInfo.setUpdateDate(new Date());
				companyInfo.setStatus('A');
				if (users!=null)
				{
					companyInfo.setCreator(users.getUserId());
					companyInfo.setUpdater(users.getUserId());
				}
				publicDao.save(companyInfo);
			}
		}
		return true;
	}
	
	public boolean updParameter(List<Parameter>  updlist) 
	{
		if (updlist!=null&&updlist.size()!=0) {
			ShiroUser users = Constants.getCurrendUser();
			for (Parameter companyInfo : updlist) {
				
				companyInfo.setUpdateDate(new Date());
				companyInfo.setUpdater(users.getUserId());
				publicDao.update(companyInfo);
			}
		}
		return true;
	}
	
	public boolean delParameter(List<Parameter>  dellist)
	{
		/*if (dellist!=null&&dellist.size()!=0) {
			for (Parameter companyInfo : dellist) {
				companyInfo.setPrmStatus('I');
				companyInfo.setPrmUpdateDate(new Date());
				publicDao.deleteToUpdate(companyInfo);
			}
		}*/
		if(dellist!=null&&dellist.size()!=0){
			for (Parameter companyInfo : dellist) {
				publicDao.delete(companyInfo);
			}
		}
		return true;
	}
}
