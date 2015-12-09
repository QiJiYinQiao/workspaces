package com.oasys.serviceImpl;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.DataGrid;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PpeStockInfo;
import com.oasys.model.PpeUsedConfirmApp;
import com.oasys.model.PpeUsedConfirmAppAttach;
import com.oasys.model.Users;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.PpeStockInfoService;
import com.oasys.service.PpeUsedConfirmService;
import com.oasys.service.RoleService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.SystemCodeService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.GridModel;

@Service("ppeUsedConfirmService")
@SuppressWarnings("unchecked")
public class PpeUsedConfirmServiceImpl  extends WorkFlowBaseServiceImpl  implements PpeUsedConfirmService
{

	
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	@Autowired
	private  StatusNameRefService  statusNameRefService;
	@Autowired
	private AuditProcHisService auditProcHisService;
	@Autowired
	private RoleService roleService;
	/**
	 * 固定资产使用
	 */
	@Autowired
	private PublicDao<PpeUsedConfirmApp> ppeUsedDao;
	
	/**
	 * 系统字典
	 */
	@Autowired
	private SystemCodeService systemCodeService;
	/**
	 * 固定资产使用附加表
	 */
	@Autowired
	private PublicDao<PpeUsedConfirmAppAttach> ppeUsedAttDao;
	/**
	 * 固定资产登记表
	 */
	@Autowired
	private PpeStockInfoService ppeStockInfoService;

	/**
	 * 固定资产使用列表
	 */
	@Override
	public GridModel findPpeUsedConfirmList(
			PpeUsedConfirmApp ppeUsedConfirmApp, PageUtil pageUtil) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Integer userId=Constants.getCurrendUser().getUserId();
			
			StringBuffer sql = this.getPpeUsedConSql();
			if(userId!=1){
				sql.append(" AND puc.APPLICANT_NO = "+Constants.getCurrendUser().getUserId());
			}
			
			if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppNo())){
				sql.append(" AND puc.APP_NO='"+ppeUsedConfirmApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(ppeUsedConfirmApp.getMyId())){
				sql.append(" AND tr1.MYID='"+ppeUsedConfirmApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppDateS())){
					sql.append( " AND puc.APP_DATE >='" + ppeUsedConfirmApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppDateE())){
					sql.append( " AND puc.APP_DATE <='" + ppeUsedConfirmApp.getAppDateE()+"' ");  //申请结束日期
				}
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getProcStatus())){
					sql.append(" AND puc.PROC_STATUS='"+ppeUsedConfirmApp.getProcStatus()+"' ");
				}
				
			}
			sql.append(" ORDER BY puc.PSA_ID DESC ");
			
			List<Object> list = ppeUsedDao.findBySql(sql.toString(), pageUtil);
			
			List<PpeUsedConfirmApp> ppeList=new ArrayList<PpeUsedConfirmApp>();
			Long count=(long)0;
			
			if(Collections.listIsNotEmpty(list)){
				PpeUsedConfirmApp confirmApp=new PpeUsedConfirmApp();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj=(Object[]) list.get(i);
					PpeUsedConfirmApp app=(PpeUsedConfirmApp) confirmApp.clone();
					app.setPsaId(obj[0]==null?0:(Integer)obj[0]);//申请id
					app.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));//申请编号
					app.setApplicantNo(obj[2]==null?0:(Integer)obj[2]);//申请人id
					app.setApplicantName(obj[3]==null?"":String.valueOf(obj[3]));//申请人名字
					app.setAppDept(obj[4]==null?0:(Integer)obj[4]);//申请人部门id
					app.setAppDeptName(obj[5]==null?"":String.valueOf(obj[5]));//申请人部门名字
					app.setAppDate(obj[6]==null?null:sdf.parse(String.valueOf(obj[6])));//申请编号
					app.setAppStatus(obj[7]==null?null:String.valueOf(obj[7]));//申请状态
					app.setUseNature(obj[8]==null?"":String.valueOf(obj[8]));//使用性质
					app.setUsedQty(obj[9]==null?0:(Integer)obj[9]);//使用数量
					app.setUser(obj[10]==null?0:(Integer)obj[10]);//使用人id
					app.setUserName(obj[11]==null?"":String.valueOf(obj[11]));//使用人名字
					app.setUserDept(obj[12]==null?0:(Integer)obj[12]);//使用人部门id
					app.setUserDeptName(obj[13]==null?"":String.valueOf(obj[13]));//使用人部门名字
					app.setRemark(obj[14]==null?"":String.valueOf(obj[14]));//备注信息
					app.setProcStatus(obj[15]==null?"":String.valueOf(obj[15]));//流程状态
					app.setPpeNo(obj[16]==null?"":String.valueOf(obj[16]));//固定资产编号
					app.setPpeName(obj[17]==null?"":String.valueOf(obj[17]));//固定资产名字
					app.setMyId(obj[18]==null?"":String.valueOf(obj[18]));//借款端/财富端
					app.setPuaPsaId(obj[19]==null?0:(Integer)obj[19]);//固定资产使用附加表id
					app.setMyId(obj[20]==null?"":String.valueOf(obj[20]));//判断借款端财富端
					
					ppeList.add(app);
				}
			}
			
			//总数量
			List list2 = ppeUsedDao.findBySQL(sql.toString());
			if(Collections.listIsNotEmpty(list2)){
				count=(long)list2.size();
			}else{
				
			}
			return new GridModel(ppeList, count);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridModel();
	}

	/**
	 * 固定资产使用列表基本sql语句
	 */
	@Override
	public StringBuffer getPpeUsedConSql() {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT ");
		sql.append(" puc.PSA_ID '申请id',");
		sql.append("puc.APP_NO '申请编号', ");
		sql.append("puc.APPLICANT_NO '申请人id', ");
		sql.append("u1.USER_NAME '申请名字', ");
		sql.append("puc.APP_DEPT '申请人部门id', ");
		sql.append(" tr1.FULL_NAME '申请人部门名字',");
		sql.append(" puc.APP_DATE '申请日期',");
		sql.append("puc.APP_STATUS '申请状态', ");
		sql.append(" puc.USE_NATURE '使用性质',");
		sql.append(" puc.USED_QTY '使用数量',");
		sql.append("puc.`USER` '使用人id', ");
		sql.append(" u2.USER_NAME '使用人名字',");
		sql.append("puc.USER_DEPT '使用人部门id', ");
		sql.append("tr2.FULL_NAME '使用人部门名字', ");
		sql.append("puc.REMARK '主表备注信息', ");
		sql.append("puc.PROC_STATUS '流程状态', ");
		sql.append("pua.PPE_NO '固定资产编号', ");
		sql.append("pua.PPE_NAME '固定资产名称', ");
		sql.append(" tr1.MYID '借款端/财富端',  ");
		sql.append(" pua.PSA_ID '详情表id', ");
		sql.append(" tr1.MYID '区分财富端借款端', ");
		sql.append(" pua.REVERTER '归还人' ");
		sql.append(" FROM t_oa_ad_ppe_used_confirm_app puc ");
		sql.append("LEFT JOIN t_oa_ad_ppe_used_confirm_app_attach pua ON puc.APP_NO = pua.APP_NO ");
		//申请人呢
		sql.append("LEFT JOIN qqms.t_users u1 ON puc.APPLICANT_NO = u1.USER_ID ");
		sql.append("LEFT JOIN qqms.t_organization tr1 ON puc.APP_DEPT = tr1.ORGANIZATION_ID ");
		//使用人
		sql.append("LEFT JOIN qqms.t_users u2 ON puc.`USER` = u2.USER_ID ");
		sql.append("LEFT JOIN qqms.t_organization tr2 ON puc.USER_DEPT = tr2.ORGANIZATION_ID ");
		sql.append(" WHERE 1=1 ");
		
		return sql;
	}

	/**
	 * 保存固定资产使用申请主表信息
	 */
	@Override
	public PpeUsedConfirmApp saveOrUpdPpeUsedConfirm(
			PpeUsedConfirmApp ppeUsedConfirmApp) {
		try {
			if(StringUtils.isBlank(ppeUsedConfirmApp.getAppNo())){
				//新添加，保存信息
				String appNo=UniqueIdUtil.generate("GQ");
				Integer userId=Constants.getCurrendUser().getUserId();
				Integer appDept=userService.findUserById(userId).getOrganizeId();
				ppeUsedConfirmApp.setAppNo(appNo);//申请编号
				ppeUsedConfirmApp.setApplicantNo(userId);//申请人id
				ppeUsedConfirmApp.setAppDept(appDept);//申请人部门id
				ppeUsedConfirmApp.setUsedQty(0);//申请数量
				ppeUsedConfirmApp.setProcStatus("1");//流程状态
				//保存信息
				ppeUsedDao.save(ppeUsedConfirmApp);
			}else{
				PpeUsedConfirmApp confirmApp = this.findPpeUsedConfirmByAppNo(ppeUsedConfirmApp.getAppNo());
				confirmApp.setUserDept(ppeUsedConfirmApp.getUserDept());//使用人部门
				confirmApp.setUser(ppeUsedConfirmApp.getUser());//使用人id
				confirmApp.setUseNature(ppeUsedConfirmApp.getUseNature());//使用性质
				confirmApp.setRemark(ppeUsedConfirmApp.getRemark());//备注信息
				ppeUsedDao.saveOrUpdate(confirmApp);
			}
			return ppeUsedConfirmApp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据申请编号查询固定资产使用申请主表信息
	 */
	@Override
	public PpeUsedConfirmApp findPpeUsedConfirmByAppNo(String appNo) {
		String hql="from PpeUsedConfirmApp where appNo='"+appNo+"'";
		List<PpeUsedConfirmApp> list = ppeUsedDao.find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 删除固定资产使用申请
	 */
	@Override
	public boolean deletePpeusedConfirm(String appNo) {
		
		try {
			//删除附加表中的数据
			String hql="delete from PpeUsedConfirmAppAttach where appNo='"+ appNo+"' ";
			ppeUsedDao.executeHql(hql);
			//删除主表中的数据
			String hql1="delete from PpeUsedConfirmApp where appNo='"+ appNo+"' ";
			ppeUsedAttDao.executeHql(hql1);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
		
	/**
	 * 根据申请编号查询固定资产使用申请
	 */
	@Override
	public PpeUsedConfirmApp findPpeUsedByAppNo(String appNo) {
		String hql="from PpeUsedConfirmApp where appNo='"+appNo+"' ";
		List<PpeUsedConfirmApp> list = ppeUsedDao.find(hql);
		if(list!=null & list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 更新固定资产保管人
	 */
	@Override
	public void updatePpeStock(Integer psaId) {
		PpeUsedConfirmApp ppeUsedConfirmApp = ppeUsedDao.get(PpeUsedConfirmApp.class, psaId);
		//申请编号
		String appNo=ppeUsedConfirmApp.getAppNo();
		//使用人
		Integer userId = ppeUsedConfirmApp.getUser();
		List<PpeUsedConfirmAppAttach> ppeUsedAttList = this.findPpeUsedAttList(appNo);
		for (PpeUsedConfirmAppAttach ppeUsedConfirmAppAttach : ppeUsedAttList) {
			ppeStockInfoService.updatePpeStockCodeCus(ppeUsedConfirmAppAttach.getPpeNo(), userId);
		}
		
	}
	
	/**
	 * 更新申请状态
	 */
	@Override
	public void updateAppStatus(Integer psaId, String appStatus) {
		PpeUsedConfirmApp ppeUsedConfirmApp = ppeUsedDao.get(PpeUsedConfirmApp.class, psaId);
		ppeUsedConfirmApp.setAppStatus(appStatus);
		ppeUsedDao.saveOrUpdate(ppeUsedConfirmApp);
		
	}
	
	/**
	 * 更新流程状态
	 */
	@Override
	public void updateProcStatus(Integer psaId, String procStatus) {
		PpeUsedConfirmApp ppeUsedConfirmApp = ppeUsedDao.get(PpeUsedConfirmApp.class, psaId);
		ppeUsedConfirmApp.setProcStatus(procStatus);
		ppeUsedDao.saveOrUpdate(ppeUsedConfirmApp);
	}
	
	/**
	 * 查看流程图
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param pnrId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月7日 下午5:03:45
	 * @throws
	 */
	@Override
	public void getDiagramResourceByPaId(HttpServletResponse response,
			Integer psaId) {
		// 图片的文件的流
				InputStream in = null;
				try {
					String proDefKey = Constants.PPEUSEDCONFIRM;
					String imgName =Constants.PPEUSEDCONFIRMIMAGE;
					
					String businessKey = proDefKey + "." + psaId;
					// 获取当前执行的流程实例
					ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
					// 获取流程定义的实体对象（对应.bpmn文件中的数据）
					ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(pi.getProcessDefinitionId());
					// 获取当前执行任务流程
					List<Task> tasks = this.taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
					// 获取图片的流程图片名称
					String resourceName = imgName + ".png";
					// 获取图片的文件的流
					in = this.repositoryService.getResourceAsStream(pd.getDeploymentId(),resourceName);
					// 获取图片对象
					BufferedImage bimg;
					bimg = ImageIO.read(in);
					// 得到Graphics2D 对象
					Graphics2D g2d = (Graphics2D) bimg.getGraphics();
					// 设置颜色和画笔粗细
					g2d.setColor(Color.RED);
					g2d.setStroke(new BasicStroke(3));
					// 遍历执行的任务,画图
					for (Task task : tasks) {
						// 根据任务的获取当前执行对象的id,根据执行对象的id获取执行对象的信息
						Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
						// 根据当前的执行对象的id获取正在执行的活动信息
						ActivityImpl activityImpl = pd.findActivity(execution.getActivityId());
						// 绘制矩形
						Rectangle2D rectangle = new Rectangle2D.Float(
								activityImpl.getX(), activityImpl.getY(),
								activityImpl.getWidth(), activityImpl.getHeight());
						g2d.draw(rectangle);
					}
					// 写入response输出流中
					ImageIO.write(bimg, "png", response.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (in != null)
						try {
							in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		
		
	}

	

	//------------------------------------------------附加表信息---------------------------------------------------------
		
	/**
	 * 保存或更新固定资产付交表
	 */
	@Override
	public String saveOrUpdPpeUsedConAtt(
			PpeUsedConfirmAppAttach ppeUsedConfirmAppAttach) {
		try {
			PpeUsedConfirmApp ppeUsedConfirmApp = this.findPpeUsedConfirmByAppNo(ppeUsedConfirmAppAttach.getAppNo());
			
			if(ppeUsedConfirmAppAttach.getPsaId()!=null && ppeUsedConfirmAppAttach.getPsaId()!=0){
				ppeUsedAttDao.saveOrUpdate(ppeUsedConfirmAppAttach);
			}else{
				if(ppeUsedConfirmAppAttach.getUseNature().equals("1")){
					ppeUsedConfirmAppAttach.setReverter(ppeUsedConfirmApp.getUser());
				}
				ppeUsedAttDao.save(ppeUsedConfirmAppAttach);
				//更改主表中的数量字段
				Integer total = this.findPpeUsedConAttTotal(ppeUsedConfirmAppAttach.getAppNo());
				ppeUsedConfirmApp.setUsedQty(total);
				ppeUsedDao.saveOrUpdate(ppeUsedConfirmApp);
			}
			
			return ppeUsedConfirmAppAttach.getAppNo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 申请的固定资产列表
	 */
	@Override
	public GridModel findPpeUsedConAttList(String appNo,PageUtil pageUtil) {
		try {
			if(StringUtils.isBlank(appNo)){
				return new GridModel();
			}
			
			String hql="from PpeUsedConfirmAppAttach where appNo='"+appNo+"' order by psaId desc";
			List<PpeUsedConfirmAppAttach> list = ppeUsedAttDao.find(hql, pageUtil);
			for (PpeUsedConfirmAppAttach ppeUsedConfirmAppAttach : list) {
				if(ppeUsedConfirmAppAttach.getReverter()!=null && ppeUsedConfirmAppAttach.getReverter()!=0){
					Users users = userService.findUserById(ppeUsedConfirmAppAttach.getReverter());
					ppeUsedConfirmAppAttach.setReverterName(users.getName());
				}
				
			}
			
			//个数
			String hql1="select count(*) from PpeUsedConfirmAppAttach where appNo='"+appNo+"' ";
			Long count = ppeUsedAttDao.count(hql1);
			return new GridModel(list,count);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridModel();
	}

	/**
	 * 删除该申请下的固定资产，删除附加表
	 */
	@Override
	public boolean deletePpeUsedconAtt(String ids,String appNo) {
		try {
			String hql=" delete from PpeUsedConfirmAppAttach where psaId in("+ids+")";
			ppeUsedAttDao.executeHql(hql);
			
			//更新主表中数量
			PpeUsedConfirmApp ppeUsedConfirmApp = this.findPpeUsedConfirmByAppNo(appNo);
			Integer total = this.findPpeUsedConAttTotal(appNo);
			ppeUsedConfirmApp.setUsedQty(total);
			ppeUsedDao.saveOrUpdate(ppeUsedConfirmApp);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 查询该申请下的固定资产数量
	 */
	@Override
	public Integer findPpeUsedConAttTotal(String appNo) {
		try {
			String hql="select count(*) from PpeUsedConfirmAppAttach where appNo='"+appNo+"' ";
			Long total = ppeUsedAttDao.count(hql);
			return total.intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 根据固定资产编号查询固定资产
	 */
	@Override
	public String findPpeStockPpeNo(String ppeNo) {
		try {
			PpeStockInfo ppeStockInfo=new PpeStockInfo();
			ppeStockInfo.setPpeCode(ppeNo);
			PpeStockInfo stockInfo = ppeStockInfoService.getPpeStockInfo(ppeStockInfo);
			if(stockInfo!=null){
				return stockInfo.getPpeName();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据申请编号查询固定资产确认附加表
	 */
	@Override
	public List<PpeUsedConfirmAppAttach> findPpeUsedAttList(String appNo) {
		String hql="from PpeUsedConfirmAppAttach where appNo='"+appNo+"'";
		List<PpeUsedConfirmAppAttach> list = ppeUsedAttDao.find(hql);
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	/**
	 * 根据appNo查询附加表中的id集合
	 */
	@Override
	public List<String> findPpeUsedAttPsaIdList(String appNo) {
		List<String> ids=new ArrayList<String>();
		String sql="SELECT PSA_ID  FROM t_oa_ad_ppe_used_confirm_app_attach WHERE APP_NO='"+appNo+"'";
		List list = ppeUsedAttDao.findBySQL(sql);
		if(Collections.listIsNotEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
				ids.add(String.valueOf(list.get(i)));
			}
		}
		
		return ids;
	}

	

	

	

	

	

	


		



	
	
	
	
}
