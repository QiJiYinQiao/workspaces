package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.ConsumablesApp;
import com.oasys.model.GoodsTackoverReg;
import com.oasys.model.GoodsTackoverStacfg;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.service.GoodsTackoverRegService;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
@Service(value="goodsTackoverRegService")
public class GoodsTackoverRegServiceImpl implements GoodsTackoverRegService {
	@Autowired
	public PublicDao<GoodsTackoverReg> publicDao;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Override
	public void addOrUpdateGoodsTackoverEntity(GoodsTackoverReg goodsTackoverReg) {
		// TODO Auto-generated method stub
		
		publicDao.saveOrUpdate(goodsTackoverReg);
	}
	
	private String getFindHQL(GoodsTackoverReg goodsTackoverReg){
		String hql = "from GoodsTackoverReg where 1=1 and registrantNo = "+Constants.getCurrendUser().getUserId();
		
		if(StringUtils.isNotBlank(goodsTackoverReg.getRegDatetimeBefore())){
			hql += " and regDatetime >='" + goodsTackoverReg.getRegDatetimeBefore()+"'";  
		}
		if(StringUtils.isNotBlank(goodsTackoverReg.getRegDatetimeAfter())){
			hql += " and regDatetime <='" + goodsTackoverReg.getRegDatetimeAfter()+"'";
		}
		hql += " order by regDatetime desc";
		return hql;
	}
	
	@Override
	public List<GoodsTackoverReg> findGoodsList(PageUtil pageUtil,
			GoodsTackoverReg goodsTackoverReg) {
		// TODO Auto-generated method stub
		String findHQL = getFindHQL(goodsTackoverReg);
		List<GoodsTackoverReg> list = publicDao.find(findHQL, pageUtil);
		for (GoodsTackoverReg goodsTackoverReg2 : list) {
			goodsTackoverReg2.setRegisterName(userService.getUserByID(goodsTackoverReg2.getRegistrantNo()).getName());
			goodsTackoverReg2.setTackoverUserName(userService.getUserByID(goodsTackoverReg2.getTackoverUser()).getName());
			goodsTackoverReg2.setTackoverDeptName(organizationService.getOrgNameByID(goodsTackoverReg2.getTackoverDept()));
			goodsTackoverReg2.setTurnoverUserName(userService.getUserByID(goodsTackoverReg2.getTurnoverUser()).getName());
			goodsTackoverReg2.setTurnoverDeptName(organizationService.getOrgNameByID(goodsTackoverReg2.getTurnoverDept()));
		}
		return list;
	}
	@Override
	public Long findGoodsCount(GoodsTackoverReg goodsTackoverReg) {
		// TODO Auto-generated method stub
		String hql=" select count(*) from GoodsTackoverReg";
		Long count = publicDao.count(hql);
		return count;
	}
	@Override
	public boolean deleteGoods(String ids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (StringUtils.isNotBlank(ids)) {
			String[] idsArray = ids.split(",");
			for (int i = 0; i < idsArray.length; i++) {
				String id = String.valueOf((idsArray[i]));
				GoodsTackoverReg goodsTackoverReg = new GoodsTackoverReg();
				goodsTackoverReg.setGtrId(Integer.parseInt(id));
				publicDao.delete(goodsTackoverReg);
			}
			flag = true;
		}
		return flag;
	}
	@Override
	public String findGoodsStacfgIds(Integer id) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		str.append("select ");
		str.append("GTS_ID AS 'ID' ");
		str.append("from t_oa_ad_goodstackover_and_stacfg ");
		str.append(" where GTR_ID="+id);
		List<Integer> objects = publicDao.findBySQL(str.toString());
		String ids = "";
		/*for (int i = 0; i < objects.size(); i++) {
			Object[] obj = (Object[]) objects.get(i);
			ids+=String.valueOf((Integer)obj[0])+",";
		}*/
		for (Integer integer : objects) {
			ids+=integer.toString()+",";
		}
		return ids;
	}

}
