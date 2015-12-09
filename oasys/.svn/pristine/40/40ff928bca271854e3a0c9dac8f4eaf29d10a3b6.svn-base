package com.oasys.dao;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StringType;

public class MySqlDialect  extends MySQL5Dialect{

	public MySqlDialect() {
		super();
		// TODO Auto-generated constructor stub
		this.registerFunction("get_role_child", new SQLFunctionTemplate(new StringType(), "get_role_child(?1)"));
		this.registerFunction("get_role_parent", new SQLFunctionTemplate(new StringType(), "get_role_parent(?1)"));
		this.registerFunction("get_org_child", new SQLFunctionTemplate(new StringType(), "get_org_child(?1)"));
		this.registerFunction("get_org_parent", new SQLFunctionTemplate(new StringType(), "get_org_parent(?1)"));
		this.registerFunction("find_in_set", new SQLFunctionTemplate(new StringType(), "find_in_set(?1,?2)"));
		this.registerFunction("get_dict_code_func", new SQLFunctionTemplate(new StringType(), "get_dict_code_func(?1,?2)"));
	}
}
