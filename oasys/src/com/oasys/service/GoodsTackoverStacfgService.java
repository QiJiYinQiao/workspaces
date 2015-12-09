package com.oasys.service;

import java.util.List;

import com.oasys.model.GoodsTackoverStacfg;

public interface GoodsTackoverStacfgService {

	List<GoodsTackoverStacfg> findAllGoodsStacfg();

	GoodsTackoverStacfg getGoodsTackoverStacfgById(Integer id);

}
