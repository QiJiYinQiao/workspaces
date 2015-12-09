package com.oasys.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.CardApp;
import com.oasys.model.CardAppAttach;
import com.oasys.service.CardAppAttachService;
import com.oasys.service.CardApplyService;
import com.oasys.viewModel.CardAppAttachModel;

@Service("cardAppAttachService")
public class CardAppAttachServiceImpl implements CardAppAttachService{
	@Autowired
	private PublicDao<CardAppAttach> publicDao;
	@Autowired
	private PublicDao<CardApp> publicDaoCardApp;
	@Autowired
	private CardApplyService cardApplyService;
	
	//添加附件
	@Override
	public boolean addCardAppAttach(CardAppAttach cardAppAttach) {
		boolean flag=false;
		try {
			cardAppAttach.setSubTotalAMT(new BigDecimal(cardAppAttach.getAppQty()*Double.parseDouble(cardAppAttach.getPrice()+"")));
			publicDao.saveOrUpdate(cardAppAttach);
			CardApp cardApp = cardApplyService.findCardAppNo(cardAppAttach.getAppNo());
			CardApp cardApp2 = publicDaoCardApp.get(CardApp.class,cardApp.getCaID());
			List<Object[]> listObj = publicDao.findBySQL("select SUM(SUBTOTAL_AMT),SUM(APP_QTY) from t_oa_ad_card_app_attach where APP_NO = '"+cardApp2.getAppNo()+"'");
			for (Object[] item : listObj) {
				cardApp2.setTotalAMT(new BigDecimal(item[0]+""));
				cardApp2.setAppQty(Integer.parseInt(item[1]+""));
			}
			publicDaoCardApp.saveOrUpdate(cardApp2);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//获取添加附件
	@Override
	public List<CardAppAttachModel> getList(String caID) {
		StringBuffer stringBuffer = new StringBuffer("SELECT a.APP_QTY '申请数量',a.PERSONAL_TEL '个人电话',a.OFFICE_TEL '办公电话',a.EMAIL '邮箱',a.BRANCH_ADDR '分公司地址',a.COM_URL '公司网址',a.UNIT '单位',a.REMARK '备注信息',u.USER_NAME as '姓名',o.FULL_NAME as '部门名称',a.POSITION '职位',a.CA_ID,a.APP_NO,u.USER_ID,card.REGISTRANT_NO '登记人编号',o.ORGANIZATION_ID,a.PRICE,a.SUBTOTAL_AMT");
		stringBuffer.append(" FROM t_oa_ad_card_app_attach a ");
		stringBuffer.append(" LEFT JOIN t_oa_ad_card_app card ON a.APP_NO = card.APP_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_users u ON u.USER_ID = a.APPLICANT_NO ");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = a.DEPT_NO");
		stringBuffer.append(" WHERE o.STATUS = 'A' ");
		if(StringUtils.isNotBlank(caID) && !"null".equals(caID)){
			String appNo=publicDao.findBySQL("SELECT APP_NO FROM t_oa_ad_card_app WHERE CA_ID="+caID).get(0)+"";
			stringBuffer.append(" AND a.APP_NO = '"+appNo+"'");
		}
		/*else{
			stringBuffer.append(" AND a.APP_NO IS NULL OR a.APP_NO = 'null'");
		}*/
		List<Object[]> list=publicDao.findBySQL(stringBuffer.toString());
		List<CardAppAttachModel> list2 = new ArrayList<CardAppAttachModel>();
		for (int i = 0; i < list.size(); i++) {
			Object[] objects = list.get(i);
			CardAppAttachModel cardAppAttachModel = new CardAppAttachModel();
			cardAppAttachModel.setAppQty(Integer.parseInt((objects[0]==null||"".equals(objects[0])?"0":objects[0])+""));//申请数量
			cardAppAttachModel.setPersonalTel(objects[1]==null?"":objects[1]+"");//个人电话
			cardAppAttachModel.setOfficeTel(objects[2]==null?"":objects[2]+"");//办公电话
			cardAppAttachModel.setEmail(objects[3]==null?"":objects[3]+"");//邮箱
			cardAppAttachModel.setBranchAddr(objects[4]==null?"":objects[4]+"");//公司地址
			cardAppAttachModel.setComUrl(objects[5]==null?"":objects[5]+"");//公司网址
			cardAppAttachModel.setUnit(objects[6]==null?"":objects[6]+"");//单位
			cardAppAttachModel.setRemark(objects[7]==null?"":objects[7]+"");//备注信息
			cardAppAttachModel.setApplicantNo(objects[8]==null?"":objects[8]+"");//申请人
			cardAppAttachModel.setUserName(objects[8]==null?"":objects[8]+"");
			cardAppAttachModel.setDeptNo(objects[9]==null?"":objects[9]+"");//部门名称
//			cardAppAttachModel.setDeptName(objects[9]==null?"":objects[9]+"");//部门名称
			cardAppAttachModel.setPosition(objects[10]==null?"":objects[10]+"");//职位
//			cardAppAttachModel.setPositionName(objects[10]==null?"":objects[10]+"");
			cardAppAttachModel.setCaId(Integer.parseInt(objects[11]+""));
			cardAppAttachModel.setAppNo(objects[12]==null?"":objects[12]+"");
			cardAppAttachModel.setUserId(Integer.parseInt(objects[13]+""));
			cardAppAttachModel.setRegistrantNO(objects[14]==null?null:Integer.parseInt(objects[14]+""));
			cardAppAttachModel.setOrganizationId(Integer.parseInt(objects[15]+""));
			cardAppAttachModel.setPrice(new BigDecimal(objects[16]+""));
			cardAppAttachModel.setSubTotalAMT(new BigDecimal(objects[17]+""));
			list2.add(cardAppAttachModel);
		}
		return list2;
	}
	//删除附件
	@Override
	public boolean delCardAttach(String cardAttachId) {
		//删除附表数据后重新计算主表的总数量
		String appNo=null;
		if(cardAttachId.contains(",")){
			appNo=publicDao.get(CardAppAttach.class,Integer.parseInt(cardAttachId.split(",")[0])).getAppNo();
		}else{
			appNo=publicDao.get(CardAppAttach.class, Integer.parseInt(cardAttachId)).getAppNo();
		}
		Integer count=publicDao.executeHql("delete CardAppAttach where caId in("+cardAttachId+")");
		if(count>0){
			String amount = publicDao.findBySQL("SELECT SUM(APP_QTY) FROM t_oa_ad_card_app_attach WHERE APP_NO ='"+appNo+"'").get(0)+"";
			publicDao.executeHql("update CardApp set appQty = "+Integer.parseInt(amount)+" where appNo='"+appNo+"'");
			return true;
		}else {
			return false;
		}
	}
	//修改附件
	@Override
	public boolean modifyCardAttachCaId(String appNo) {
		Integer count=publicDao.executeHql("update CardAppAttach set appNo = '"+appNo+"' where appNo=null or appNo = 'null'");
		if(count>0){
			return true;
		}else {
			return false;
		}
	}
	//统计附件
	@Override
	public BigDecimal getCountByCardApp(String appNo) {
		return (BigDecimal) publicDao.findBySQL("SELECT SUM(APP_QTY) FROM t_oa_ad_card_app_attach WHERE APP_NO is "+appNo+" OR APP_NO ='null'").get(0);
	}

}
