package com.bpms.service.impl;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.UserAndOrganization;
import com.bpms.model.Users;
import com.bpms.service.UserAndOrganizationService;
import com.bpms.util.Collections;
import com.bpms.util.Constants;

/**
 * 绑定外援用户和要外援组织机构管理
 * @author liuhh
 *
 */
@Service("userAndOrganizationService")
public class UserAndOrganizationServiceImpl implements UserAndOrganizationService {
	
	@Autowired
	private BaseDAO<UserAndOrganization> userAndOrganizationDao;
	
	/** 注入流程运行服务service. */
	@Autowired
	protected RuntimeService runtimeService;

	/** 注入节点任务服务service. */
	@Autowired
	protected TaskService taskService;
	
	@Autowired
	protected IdentityService identityService;
	
	@Autowired
	private BaseDAO<Users> userDao;

	@Override
	public void saveUserAndOrganization(UserAndOrganization userAndOrganization) {
		userAndOrganizationDao.save(userAndOrganization);
	}

	@Override
	public UserAndOrganization isExistUserAndOrganization(UserAndOrganization userAndOrganization) {
		String hql = "from UserAndOrganization uo where uo.organizationId ='"+userAndOrganization.getOrganizationId()+"' and uo.userId ='"+userAndOrganization.getUserId()+"' ";
		List<UserAndOrganization> userAndOrganizations = userAndOrganizationDao.find(hql);
		if(Collections.listIsNotEmpty(userAndOrganizations)){
			return userAndOrganizations.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteUserAndOrganization(Integer organizationId) {
		String hql ="delete from UserAndOrganization uo where uo.organizationId='"+organizationId+"'";
		userAndOrganizationDao.executeHql(hql);
	}
	
	@Override
	public void saveBindingUserAndOrganization(String organizationId, String userIds) throws Exception {
		// 判断组织机构是否为空，如果为空抛出参数异常，组织机构不能为空
		if(StringUtils.isBlank(organizationId)){
			throw new IllegalArgumentException("组织机构不能为空！！");
		}
		
		// 判断用户的ID是否空，如果不为空从新设置候选人
		if(StringUtils.isNotBlank(userIds)){
			String[] userIDs = userIds.split(",");
			if(null != userIDs && userIDs.length >0){
				// 删除原来的组织机构绑定信息
				deleteUserAndOrganization(Integer.parseInt(organizationId));
				
				// 绑定用户组织机构的信息
				for (String userID : userIDs) {
					UserAndOrganization uo = new  UserAndOrganization();
					uo.setUserId(Integer.parseInt(userID));
					uo.setOrganizationId(Integer.parseInt(organizationId));
					saveUserAndOrganization(uo);
				}
				
				// 设置当前组织机构下的人员信息
				List<Task> list = taskService.createTaskQuery().taskCandidateGroup(Constants.LOAN_ROLE_CODE_IPCXIAOERDIAOCHA+"."+organizationId).list();
				// 获取该活动节点任务，查看原有的参与人员并删除，重新设置参与人员
				if(Collections.listIsNotEmpty(list)){
					for(Task task : list){
						List<IdentityLink> identityList = taskService.getIdentityLinksForTask(task.getId());
						// 删除原有的参与者
						if(Collections.listIsNotEmpty(identityList)){
							for (IdentityLink identityLink : identityList) {
								if(identityLink.getType().equals("candidate") && StringUtils.isNotBlank(identityLink.getUserId())){
									taskService.deleteCandidateUser(task.getId(), identityLink.getUserId());
								}
							}
						}
						// 设置新的参与者
						for (String userId: userIDs) {
							taskService.addCandidateUser(task.getId(), userId);
						}
					}
				}
			}
		}else{
			// 用户的信息为空，则取消外援人员绑定信息
			// 删除原来的组织机构绑定信息
			deleteUserAndOrganization(Integer.parseInt(organizationId));
			
			// 设置当前组织机构下的人员信息
			List<Task> list = taskService.createTaskQuery().taskCandidateGroup(Constants.LOAN_ROLE_CODE_IPCXIAOERDIAOCHA+"."+organizationId).list();
			// 获取该活动节点任务，查看原有的参与人员并删除，重新设置参与人员
			if(Collections.listIsNotEmpty(list)){
				for(Task task : list){
					List<IdentityLink> identityList = taskService.getIdentityLinksForTask(task.getId());
					// 删除原有的参与者
					if(Collections.listIsNotEmpty(identityList)){
						for (IdentityLink identityLink : identityList) {
							if(identityLink.getType().equals("candidate") && StringUtils.isNotBlank(identityLink.getUserId())){
								taskService.deleteCandidateUser(task.getId(), identityLink.getUserId());
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<Users> findBindingUsersByOrganizationId(String organizationId) {
		String hql = "select u from Users u,UserAndOrganization uo where u.userId = uo.userId and uo.organizationId = '"+organizationId+"'";
		return userDao.find(hql);
	}

}
