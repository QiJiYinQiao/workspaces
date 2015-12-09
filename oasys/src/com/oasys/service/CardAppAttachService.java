package com.oasys.service;

import java.math.BigDecimal;
import java.util.List;

import com.oasys.model.CardAppAttach;
import com.oasys.viewModel.CardAppAttachModel;

public interface CardAppAttachService {
	//添加附件
	boolean addCardAppAttach(CardAppAttach cardAppAttach);
	//展示附件信息
	List<CardAppAttachModel> getList(String caID);
	//删除附件信息
	boolean delCardAttach(String cardAttachId);
	//统一提交时将所有名片附件统一设置编号
	boolean modifyCardAttachCaId(String appNo);
	//统计一次名片提交中所有的提交数量
	BigDecimal getCountByCardApp(String appNo);
}
