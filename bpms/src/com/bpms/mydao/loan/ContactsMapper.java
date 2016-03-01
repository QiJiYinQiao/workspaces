package com.bpms.mydao.loan;

import java.util.List;
import java.util.Map;

/**
 * 紧急联系人的查询
 * 
 * @author liuhh
 *
 */
public interface ContactsMapper {

	/**
	 * 紧急联系人的查询
	 * 
	 * @param param
	 *            查询紧急联系人需要的参数
	 * @return 紧急联系人列表
	 */
	List<Map<String, Object>> findContactList(Map<String, Object> param);

	/**
	 * 紧急联系人的个数
	 * 
	 * @param param
	 *            查询紧急联系人需要的参数
	 * @return 紧急联系人列表个数
	 */
	long findContactListCount(Map<String, Object> param);

}
