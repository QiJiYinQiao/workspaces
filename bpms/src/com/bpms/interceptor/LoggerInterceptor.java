package com.bpms.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 自定义日志拦截器
 * @author Administrator
 *
 */
public class LoggerInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 792736468659807415L;
	private static final Logger logger = Logger.getLogger(LoggerInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		try {
			return actionInvocation.invoke();
		} catch (Exception e) {
			Object action = actionInvocation.getAction();
			String method = actionInvocation.getProxy().getMethod();
			logger.error("Action:"+action.getClass()+"Method:"+method, e);
		}
		return "strutsException";
	}

}
