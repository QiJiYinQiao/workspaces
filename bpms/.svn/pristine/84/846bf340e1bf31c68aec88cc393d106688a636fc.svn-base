package test.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import test.TestBase;

import com.bpms.service.LoanOrderHisService;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanerService;
import com.bpms.service.SysParameterService;

public class TestLoanOrderHisService extends TestBase {
	@Autowired
	LoanOrderHisService loanOrderHisService;

	@Autowired
	LoanOrderService loanOrderService;
	
	@Autowired
	LoanerService loanerService;
	@Autowired
	SysParameterService sysParameterService;
	
	/** 注入Hibernate会话工厂 */
	@Autowired
	private SessionFactory sessionFactory;


	@Test
	@Transactional(readOnly=false)
	public void findAttachmentByULA() {
		System.out.println(sysParameterService.findSysParameter("loan_mthd"));
	}

}
