package com.bpms.service;

import com.bpms.model.SysParameter;

/**
 * 系统参数表 Service
 * @author Sun
 *
 */
public interface SysParameterService {
	/**
	 * 通过parmCode 查询系统参数
	 * @param parmCode
	 * @return
	 */
	public SysParameter findSysParameter(String parmCode);
}
