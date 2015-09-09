package com.bpms.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


public class SessionInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -2771516501737587145L;

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		/*SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo == null) {
			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
			return "noSession";
		}*/
		return actionInvocation.invoke();
	}

}
