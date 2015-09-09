package test;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Copyright(C) JiNanShangJie 2015.
 * 
 * JUit4 测试的基类.
 * 
 * @author 刘洪虎 2015/03/10.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/03/10 刘洪虎 创建.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-spring-hibernate.xml","/test-activiti-context.xml" })
/*@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")*/
public class TestBase {
	/** 日志. */
	protected Logger logger;

	/** 构造方法. */
	public TestBase() {
		logger = Logger.getLogger(this.getClass());
	}	
	
	public static void main(String[] args) {
		int m = 2;
		int i = 0 ;
		for(; m >= 0; m--){
			if(m == 0){
				m = 12;
			}
			System.out.println(m);
			i++;
			if(i == 6){
				break;
			}
		}
	}
}
