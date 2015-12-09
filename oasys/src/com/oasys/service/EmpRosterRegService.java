package com.oasys.service;

import java.util.List;

import com.oasys.model.EmpRosterReg;
import com.oasys.model.VO.EmpRosterRegModel;
import com.oasys.util.PageUtil;

/**
 * 
 * @ClassName: EmpRosterRegService
 * @Description: TODO 员工花名册登记
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年11月6日 下午2:03:02
 *
 */
public interface EmpRosterRegService {

	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月6日 下午2:04:19
	 * @Title:saveOrUpdateEmpRosterRegEntity
	 * @Description:TODO（这里描述这个方法的作用）新增或修改操作
	 * @param empRosterReg
	 * @throws:
	 */
	 boolean saveOrUpdateEmpRosterRegEntity(EmpRosterReg empRosterReg);

	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月6日 下午2:04:47
	 * @Title:delEmpRosterReg 删除
	 * @Description:TODO（这里描述这个方法的作用）
	 * @param ids
	 * @return
	 * @throws:
	 */
	Integer delEmpRosterReg(String ids);

	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月6日 下午2:05:01
	 * @Title:getEmpRosterRegByID
	 * @Description:TODO（这里描述这个方法的作用）根据ida查询对象
	 * @param id
	 * @return
	 * @throws:
	 */
	EmpRosterReg getEmpRosterRegByID(Integer id);

	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月6日 下午2:06:23
	 * @Title:findEmpRosterRegList
	 * @Description:TODO（这里描述这个方法的作用）查询列表
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	List<EmpRosterRegModel> findEmpRosterRegList(PageUtil pageUtil,EmpRosterRegModel empRosterReg);

	/**
	 * @Title: findEmpRosterRegCount
	 * @Description: 查询录数
	 * @return EmpRosterReg
	 * @date 2015/10/15
	 */
	Long findEmpRosterRegCount(EmpRosterRegModel empRosterReg);

}
