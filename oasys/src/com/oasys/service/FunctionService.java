package com.oasys.service;

import java.util.List;

import com.oasys.model.Permission;
import com.oasys.viewModel.TreeGridModel;
import com.oasys.viewModel.TreeModel;



public interface FunctionService 
{


	List<TreeGridModel> findAllFunctionList(Integer pid );

	boolean delFunction(Integer id );

	boolean persistenceFunction(List<Permission> list );

	List<TreeModel> findAllFunctionList();

	boolean persistenceFunction(Permission permission );
	
}
