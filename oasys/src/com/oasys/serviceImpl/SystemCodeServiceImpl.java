package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.SystemCode;
import com.oasys.service.SystemCodeService;
import com.oasys.util.Constants;
import com.oasys.viewModel.TreeModel;
@Service("systemCodeService")
public class SystemCodeServiceImpl implements SystemCodeService
{
	private PublicDao<SystemCode> publicDao;
	@Autowired
	public void setPublicDao(PublicDao<SystemCode> publicDao )
	{
		this.publicDao = publicDao;
	}
	
	/* (非 Javadoc) 
	* <p>Title: findSystemCodeList</p> 
	* <p>Description: </p> 
	* @param id
	* @return 
	*/
	public List<SystemCode> findSystemCodeList(Integer id)
	{
		String hql="from SystemCode t where t.status='A'";
		if(null==id||"".equals(id)){
			hql+=" and t.parentId is null";
		}else {
			hql+=" and t.parentId="+id;
		}
		return publicDao.find(hql);
	}
	
	/* (非 Javadoc) 
	* <p>Title: findSystemCodeList</p> 
	* <p>Description: </p> 
	* @return 
	*/
	public List<TreeModel> findSystemCodeList()
	{
		String hql="from SystemCode t where t.status='A'";
		List<SystemCode> list = publicDao.find(hql);
		List<TreeModel> tempList=new ArrayList<TreeModel>();
		for (SystemCode s : list)
		{
			TreeModel treeModel=new TreeModel();
			treeModel.setId(s.getCodeId().toString());
			treeModel.setPid(s.getParentId()==null?"":s.getParentId().toString());
			treeModel.setName(s.getDictName());
			treeModel.setIconCls(s.getIconCls());
			treeModel.setState(s.getState());
			treeModel.setState("open");
			treeModel.setPermissionId(s.getPermissionId());
			tempList.add(treeModel);
		}
		return tempList;
	}
	
	
	/* (非 Javadoc) 
	* <p>Title: persistenceSystemCodeDig</p> 
	* <p>Description: </p> 
	* @param systemCode
	* @return 
	*/
	public boolean persistenceSystemCodeDig(SystemCode systemCode,String permissionName,Integer codePid)
	{
		Integer userid = Constants.getCurrendUser().getUserId();
		Integer pid = systemCode.getParentId();
		Integer codeId = systemCode.getCodeId();
		if (null==codeId||"".equals(codeId))
		{
			systemCode.setCreated(new Date());
			systemCode.setLastmod(new Date());
			systemCode.setCreater(userid);
			systemCode.setModifyer(userid);
			systemCode.setStatus(Constants.PERSISTENCE_STATUS);
			systemCode.setDictType("D");
			if (null==pid||"".equals(pid))
			{
				systemCode.setState(Constants.TREE_STATUS_OPEN);
				//systemCode.setParentId(codePid);
			}else {
				SystemCode pCode = publicDao.get(SystemCode.class, pid);
				if (!Constants.TREE_STATUS_CLOSED.equals(pCode.getState()))
				{
					pCode.setState(Constants.TREE_STATUS_CLOSED);
					publicDao.update(pCode);
				}
				systemCode.setState(Constants.TREE_STATUS_OPEN);
			}
			List<SystemCode> list = isExtPermissionId(systemCode.getPermissionId());
			if (list.size()!=0)
			{
				if (pid==null||"".equals(pid))
				{
					SystemCode sysc = list.get(0);
					systemCode.setParentId(sysc.getCodeId());
				}
			}else {
				SystemCode ss=new SystemCode();
				ss.setCreated(new Date());
				ss.setLastmod(new Date());
				ss.setCreater(userid);
				ss.setModifyer(userid);
				ss.setStatus(Constants.PERSISTENCE_STATUS);
				ss.setPermissionId(systemCode.getPermissionId());
				String[] temp=permissionName.split(",");
				ss.setDictName(temp[0]);
				ss.setState(Constants.TREE_STATUS_CLOSED);
				ss.setIconCls(temp[1]);
				ss.setDictType("M");
				publicDao.save(ss);
				systemCode.setParentId(ss.getCodeId());
			}
			publicDao.save(systemCode);
		}else {
			systemCode.setLastmod(new Date());
			systemCode.setModifyer(userid);
			publicDao.update(systemCode);
		}
		return true;
	}
	
	/**  
	* 函数功能说明
	* Administrator修改者名字
	* 2013-6-20修改日期
	* 修改内容
	* @Title: isExtPermissionId 
	* @Description:  TODO:检查模块是否存在 
	* @param @param permissionId
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public List<SystemCode> isExtPermissionId(Integer permissionId)
	{
		String hql=" from SystemCode t where t.status='A' and t.dictType='M' and t.permissionId="+permissionId;
		return publicDao.find(hql);
	}
	
	/* (非 Javadoc) 
	* <p>Title: findSystemCodeByType</p> 
	* <p>Description: </p> 
	* @param codeMyid
	* @return 
	*/
	public List<SystemCode> findSystemCodeByType(String codeMyid)
	{
		String hql="from SystemCode t where t.status='A' and t.dictCode='"+codeMyid+"'";
		List<SystemCode> list = publicDao.find(hql);
		if (list.size()==1)
		{
			SystemCode ss = list.get(0);
			String hql2="from SystemCode t where t.status='A' and t.parentId="+ss.getCodeId();
			hql2+="order by t.sort";
			return publicDao.find(hql2);
		}
		return null;
	}
	
	/* (非 Javadoc) 
	* <p>Title: delSystemCode</p> 
	* <p>Description: </p> 
	* @param codeId
	* @return 
	*/
	public boolean delSystemCode(Integer codeId)
	{
		String hql=" from SystemCode t where t.status='A' and t.parentId="+codeId;
		List<SystemCode> list = publicDao.find(hql);
		if (list.size()!=0)
		{
			return false;
		}else {
			Integer userid = Constants.getCurrendUser().getUserId();
			SystemCode s = publicDao.get(SystemCode.class,codeId);
			s.setLastmod(new Date());
			s.setModifyer(userid);
			s.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
			publicDao.deleteToUpdate(s);
			return true;
		}
	}
	/**
	 * (非 Javadoc) 
	 * <p>Title: isExistsCode</p> 
	 * <p>Description: dictcode是否已存在确保其唯一性</p> 
	 * @param systemCode
	 * @return 
	 * @see com.oasys.service.SystemCodeService#isExistsCode(com.oasys.model.SystemCode)
	 */
	public boolean isExistsCode(SystemCode systemCode){
		//判断paren_id为1的字典项的字典编码（dict_code）只存在1个
		String dictcode=systemCode.getDictCode();
		Integer codeId = systemCode.getCodeId();
		String hql="from SystemCode t where t.parentId =1 and t.status='A' and t.dictCode='"+dictcode+"'";
		Long count = publicDao.count("select count(*)"+hql);
		boolean flag = false;
		if (null==codeId||"".equals(codeId))
		{
			//新增操作
			if(count>0){
				flag=true;
			}
		}else{
			//修改操作
			hql+=" and t.codeId <>'"+codeId+"'";
			Long count1 = publicDao.count("select count(*)"+hql);
			if(count1>=1){
				flag=true;
			}
		}
		return flag;
	}

	@Override
	public String findSystemName(String parDictCode, String dictCode) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT sd2.DICT_NAME FROM qqms.t_sys_dict sd1 LEFT JOIN qqms.t_sys_dict sd2 ON sd1.CODE_ID = sd2.PARENT_ID WHERE sd1.DICT_CODE = '"+parDictCode+"'  ");
		sql.append(" AND sd1.PARENT_ID = '1' AND sd2.STATUS='A' ");
		sql.append(" AND sd2.DICT_CODE = '"+dictCode+"' ");
		List<String> list = publicDao.findBySQL(sql.toString());
		for (String dictName : list) {
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
}
