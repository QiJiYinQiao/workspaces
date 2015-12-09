package com.oasys.service;

import java.util.List;

import com.oasys.model.GoodsTackoverReg;
import com.oasys.model.GoodsTackoverStacfg;
import com.oasys.util.PageUtil;

public interface GoodsTackoverRegService {

	void addOrUpdateGoodsTackoverEntity(GoodsTackoverReg goodsTackoverReg);
	
	List<GoodsTackoverReg> findGoodsList(PageUtil pageUtil,
			GoodsTackoverReg goodsTackoverReg);

	Long findGoodsCount(GoodsTackoverReg goodsTackoverReg);

	boolean deleteGoods(String ids);

	String findGoodsStacfgIds(Integer id);

}
