package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Assets;
import com.bpms.service.AssetsService;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service
public class AssetsServiceImpl implements AssetsService {

	@Autowired
	private BaseDAO<Assets> baseDAO;

	@Override
	public boolean persistenceAssets(Assets assets) {
		if (StringUtils.isBlank(assets.getAssetId())) {
			baseDAO.save(assets);
		} else {
			baseDAO.update(assets);
		}
		return true;
	}

	@Override
	public Assets findAssetsById(String id) {
		/*
		 * List<Assets> list = baseDAO.find("from Assets o where o.assetId='" +
		 * id + "'"); if (Collections.listIsNotEmpty(list)) { return
		 * list.get(0); } else { return null; }
		 */
		return baseDAO.get(Assets.class, id);
	}

	@Override
	public List<Assets> findAssets(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "from Assets t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countAssets(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from Assets t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

}
