package com.qqms.service;

import java.util.List;

import com.qqms.model.Permission;
import com.qqms.viewModel.TreeGridModel;
import com.qqms.viewModel.TreeModel;



public interface FunctionService 
{


	List<TreeGridModel> findAllFunctionList(Integer pid );

	boolean delFunction(Integer id );

	boolean persistenceFunction(List<Permission> list );

	List<TreeModel> findAllFunctionList();

	boolean persistenceFunction(Permission permission );
	
}
