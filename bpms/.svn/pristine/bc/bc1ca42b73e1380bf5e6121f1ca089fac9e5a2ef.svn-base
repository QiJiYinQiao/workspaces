package com.bpms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.SysParameter;
import com.bpms.service.ParameterService;

/**
 * @Creater chenfl
 * @File_Name ParameterServiceImpl.java
 * @Version v1.0
 * @Creation_Date 2015年5月29日 下午4:03:13
 * @Modifier
 * @Modified_Date
 * @Description 系统参数服务接口实现类
 */
@Service("parameterService")
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private BaseDAO<SysParameter> baseDAO;

	/**
	 * 根据参数编码查询参数值
	 */
	public List<SysParameter> findSysParameterValueByCode(String parameterCode) {
		return baseDAO.find("from SysParameter p where p.parmCode='"
				+ parameterCode + "'");
	}
}
