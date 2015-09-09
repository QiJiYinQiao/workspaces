package test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import test.TestBase;

import com.bpms.model.vo.CustomerRepaymentInfoModel;
import com.bpms.service.LoanContractService;
import com.bpms.service.LoanOrderHisService;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanerService;
import com.bpms.service.SysParameterService;
import com.bpms.util.PageUtil;

public class TestLoanOrderHisService extends TestBase {
	@Autowired
	LoanOrderHisService loanOrderHisService;

	@Autowired
	LoanOrderService loanOrderService;
	
	@Autowired
	LoanerService loanerService;
	@Autowired
	SysParameterService sysParameterService;
	
	@Autowired
	LoanContractService loanContractService;
	
	/** 注入Hibernate会话工厂 */
	@Autowired
	private SessionFactory sessionFactory;


	@Test
	@Transactional(readOnly=false)
	public void findAttachmentByULA() {
		Map map = new HashMap();
		PageUtil pageUtil = new PageUtil(0, 10);
	}
	
	public static void main(String[] args) {
		
	}
}
