package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.ConsumablesApp;
import com.oasys.model.GoodsTackoverStacfg;
import com.oasys.service.GoodsTackoverStacfgService;

@Service(value="goodsTackoverStacfgService")
public class GoodsTackoverStacfgServiceImpl implements
		GoodsTackoverStacfgService {
	@Autowired
	public PublicDao<GoodsTackoverStacfg> publicDao;
	@Override
	public List<GoodsTackoverStacfg> findAllGoodsStacfg() {
		// TODO Auto-generated method stub
//		String sql="select * from t_oa_ad_goods_tackover_stacfg ";
		StringBuffer str = new StringBuffer();
		str.append("select ");
		str.append("GTS_ID AS 'ID' ,");
		str.append("GOODS_NAME AS '名称' ,");
		str.append("GOODS_TYPE AS '类型' ,");
		str.append("QTY AS '数量' ,");
		str.append("UNIT AS '单位' ,");
		str.append("BRAND_MODEL AS '规格' ");
		str.append("from t_oa_ad_goods_tackover_stacfg");
		List<Object> objects = publicDao.findBySQL(str.toString());
		List<GoodsTackoverStacfg> arrayList = new ArrayList<GoodsTackoverStacfg>();
		for (int i = 0; i < objects.size(); i++) {
			GoodsTackoverStacfg goodsTackoverStacfg = new GoodsTackoverStacfg();
			Object[] obj = (Object[]) objects.get(i);
			goodsTackoverStacfg.setGtsId((Integer)obj[0]);
			goodsTackoverStacfg.setGoodsName(String.valueOf(obj[1]));
			goodsTackoverStacfg.setGoodsType(String.valueOf(obj[2]));
			goodsTackoverStacfg.setQty((Integer)(obj[3]));
			goodsTackoverStacfg.setUnit(String.valueOf(obj[4])==null?"null":String.valueOf(obj[4]));
			goodsTackoverStacfg.setBrandModel(String.valueOf(obj[5])==null?"null":String.valueOf(obj[5]));
			arrayList.add(goodsTackoverStacfg);
		}
		return arrayList;
	}
	@Override
	public GoodsTackoverStacfg getGoodsTackoverStacfgById(Integer id) {
		// TODO Auto-generated method stub
		return publicDao.get(GoodsTackoverStacfg.class, id);
	}

}
