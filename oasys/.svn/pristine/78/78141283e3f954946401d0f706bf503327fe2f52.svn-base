package com.oasys.service.workFlow;

import org.activiti.engine.delegate.DelegateTask;

/****
 * 
 * @author lida
 * @Title TaskRoleServiceImpl
 * @date 2015年11月2日 15:33:08
 * @descpration 流程节点与角色接口
 * 
 */
public interface TaskRoleService {

	/***
	 * 根据流程图中task节点ID和申请人ID动态获取角色code 并赋候选组
	 * @param taskDefineID 流程图中task节点ID
	 * @param curUserID 申请人ID
	 * @return 
	 */
	public void setTaskRoleCodeByTask(DelegateTask task);
	
	/***
	 * 
	 * 根据流程图中task节点ID和申请人ID动态获取候选人ID设置候选人
	 * 该方法只有满足定制区域角色时会调用
	 * @param task 流程节点对象
	 * @return 
	 */
	public void setTaskAssisantByTask(DelegateTask task);
	
	/***
	 * 根据申请人调用getOrgCodeFunc方法
	 * @param curUserID 申请人ID
	 * @param taskDefinitionKey 流程节点/角色标识
	 * @return 动态角色code(方法内部调用实现类中getOrgCodeFunc方法 详见getOrgCodeFunc方法)
	 */
	public String getOrgIdRoleByDefKeyByUser(String curUserID,String taskDefinitionKey);
	
	/***
	 * 根据组织机构调用getOrgCodeFunc方法
	 * @param orgID 组织机构ID
	 * @param taskDefinitionKey 流程节点/角色标识
	 * @return 动态角色code(方法内部调用实现类中getOrgCodeFunc方法 详见getOrgCodeFunc方法)
	 */
	public String getOrgIdRoleByDefKeyByOrg(String orgID,String taskDefinitionKey);
	
	
}
