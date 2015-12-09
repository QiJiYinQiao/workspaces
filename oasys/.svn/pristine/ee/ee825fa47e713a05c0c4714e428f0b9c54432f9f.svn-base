package com.oasys.datasource;

import org.aspectj.lang.JoinPoint;

import com.oasys.util.Constants;

public class DataSourceInterceptor {

	public void setdataSourceOA(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType(Constants.DATASOURCE_OA);
	}
	
	public void setdataSourceQQms(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType(Constants.DATASOURCE_QQMS);
	}
}