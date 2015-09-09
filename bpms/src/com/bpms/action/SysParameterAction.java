package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.SysParameter;
import com.bpms.service.SysParameterService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 系统参数控制器
 */
@Namespace("/sysParameter")
@Action(value = "sysParameterAction")
public class SysParameterAction extends BaseAction implements ModelDriven<SysParameter>{
	
	private static final long serialVersionUID = 2586159027388906809L;
	/** modelDriven. */
	private SysParameter sysParameter = new SysParameter();
	
	@Autowired
	private SysParameterService sysParameterService;

	@Override
	public SysParameter getModel() {
		return sysParameter;
	}
	
	public void findSysParameter(){
		SysParameter sys = sysParameterService.findSysParameter(sysParameter.getParmCode());
		OutputJson(sys);
	}
}
