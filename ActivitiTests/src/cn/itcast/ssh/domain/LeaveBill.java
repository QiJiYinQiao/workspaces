package cn.itcast.ssh.domain;

import java.util.Date;

public class LeaveBill {
	private Long id;//主键ID
	private Integer days;// 请假天数
	private String content;// 请假内容
	private Date leaveDate = new Date();// 请假时间
	private String remark;// 备注
	private Employee user;// 请假人

	private Integer state=0;// 请假单状态 0初始录入,1.开始审批,2为审批通过

}
