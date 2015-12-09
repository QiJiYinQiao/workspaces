package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PurchaseAppAttach;
import com.oasys.service.PurchaseAppAttachService;
import com.oasys.util.Collections;
import com.oasys.util.MoneyUtil;
import com.oasys.viewModel.PurchaseAppAttachModel;
/**
 * @ClassName: PurchaseAppAttachServiceImpl 
 * @Description: TODO 物料附加表ServiceImpl
 * @author PANCHUANHE
 * @date 2015年9月16日 下午3:21:17
 */
@Service(value="purchaseAppAttachService")
public class PurchaseAppAttachServiceImpl implements PurchaseAppAttachService {

	@Autowired
	private PublicDao<PurchaseAppAttach> publicDao;
	
	@Override
	public boolean savePurchaseAppAttach(PurchaseAppAttach purchaseAppAttach) {
		try {
			if (!"0".equals(purchaseAppAttach.getUnit())) {//如果单位不为其他单位
				purchaseAppAttach.setUnitOther(null);
			}
			if(purchaseAppAttach.getPsaId()==null){
				publicDao.save(purchaseAppAttach);
			}else{
				publicDao.update(purchaseAppAttach);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delPurchaseAppAttachByPsaId(String ids) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("delete PurchaseAppAttach p where p.psaId in ("+ids+")");
			publicDao.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PurchaseAppAttach findPurchaseAppAttachBypsaId(Integer psaId) {
		return publicDao.get(PurchaseAppAttach.class, psaId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PurchaseAppAttachModel> findPurchaseAppAttachList(String appNo) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("u1.LOGIN_ACT AS '使用人',");
		sql.append("u2.LOGIN_ACT AS '保管人',");
		sql.append("p.ARTICLE_NAME AS '物品',");
		sql.append("p.MODEL as '型号规格',");
		sql.append("p.PRICE as '价格',");
		sql.append("p.QTY as '数量',");
		sql.append("p.TOTAL_AMT as '合计金额',");
		sql.append("p.PURPOSE as '用途',");
		sql.append("p.REMARK as '备注',");
		sql.append("p.PSA_ID as '主键',");
		sql.append("p.APP_NO as '申请编号',");
		sql.append("p.`USER` as '使用人id',");
		sql.append("p.DEPOSITARY as '保管人id',");
		sql.append("p.UNIT as '单位',");
		sql.append("p.UNIT_OTHER as '其他单位',");
		sql.append("qqms.get_dict_code_func('article_name',p.ARTICLE_NAME) as '物品名称' ");
		sql.append("FROM t_oa_ad_purchase_app_attach p ");
		sql.append("LEFT JOIN qqms.t_users u1 ON p.`USER` = u1.USER_ID ");
		sql.append("LEFT JOIN qqms.t_users u2 ON p.DEPOSITARY = u2.USER_ID where 1=1 ");
		sql.append("AND p.APP_NO = '"+appNo+"'");
		sql.append(" ORDER BY p.PSA_ID DESC");
		List<Object> list = publicDao.findBySQL(sql.toString());
		List<PurchaseAppAttachModel> modelList = new ArrayList<PurchaseAppAttachModel>();
		if(Collections.listIsNotEmpty(list)){
			PurchaseAppAttachModel model = new PurchaseAppAttachModel();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				PurchaseAppAttachModel cmodel = (PurchaseAppAttachModel) model.clone();
				cmodel.setUserName(obj[0]==null?"":String.valueOf(obj[0]));
				cmodel.setDepositaryName(obj[1]==null?"":String.valueOf(obj[1]));
				cmodel.setArticleName(obj[2]==null?"":String.valueOf(obj[2]));
				cmodel.setModel(obj[3]==null?"":String.valueOf(obj[3]));
				cmodel.setPrice(obj[4]==null?null:MoneyUtil.numberWithDelimiter(String.valueOf(obj[4])));
				cmodel.setQty(obj[5]==null?null:(Integer)obj[5]);
				cmodel.setTotalAmt(obj[6]==null?null:MoneyUtil.numberWithDelimiter(String.valueOf(obj[6])));
				cmodel.setPurpose(obj[7]==null?"":String.valueOf(obj[7]));
				cmodel.setRemark(obj[8]==null?"":String.valueOf(obj[8]));
				cmodel.setPsaId(obj[9]==null?0:(Integer)obj[9]);
				cmodel.setAppNo(obj[10]==null?"":String.valueOf(obj[10]));
				cmodel.setUser(obj[11]==null?0:(Integer)obj[11]);
				cmodel.setDepositary(obj[12]==null?0:(Integer)obj[12]);
				cmodel.setUnit(obj[13]==null?"":String.valueOf(obj[13]));
				cmodel.setUnitOther(obj[14]==null?"":String.valueOf(obj[14]));
				cmodel.setArticleNameText(obj[15]==null?"":String.valueOf(obj[15]));
				modelList.add(cmodel);
			}
		}
		return modelList;
	}

	@Override
	public boolean delPurchaseAppAttachByAppNo(String appNo) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("delete PurchaseAppAttach p where p.appNo = '"+appNo+"'");
			publicDao.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
