package com.bpms.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.MicrocreditOpinion;
import com.bpms.service.MicrocreditOpinionService;

@Service
public class MicrocreditOpinionServiceImpl implements MicrocreditOpinionService {
	
	@Autowired
	BaseDAO<MicrocreditOpinion> baseDAO;
	
	@Override
	public boolean saveMicrocreditOpinion(MicrocreditOpinion mo) {
		if(StringUtils.isBlank(mo.getMcbrId())){
			baseDAO.save(mo);
		}else{
			baseDAO.update(mo);
		}
		return true;
	}

	@Override
	public MicrocreditOpinion findMicrocreditOpinionByOid(String loanOrderId) {
		String hql = " FROM MicrocreditOpinion mo WHERE mo.loanOrderId = '"+loanOrderId+"'";
		List<MicrocreditOpinion> list = baseDAO.find(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
