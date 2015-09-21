package org.my.architecture.customermgr;

import org.my.architecture.customermgr.service.ICustomerService;
import org.my.architecture.customermgr.vo.CustomerModel;
import org.my.architecture.customermgr.vo.CustomerQueryModel;
import org.my.pageutil.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Client {
	@Autowired
	private ICustomerService s = null;
	
	public ICustomerService getS(){
		return s;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Client t = (Client)ctx.getBean("client");
		
		CustomerModel cm = new CustomerModel();
		cm.setCustomerId("c1");
		cm.setPwd("c1");
		cm.setRegisterTime("111");
		cm.setShowName("c1");
		cm.setTrueName("张三");

		
		CustomerQueryModel cqm = new CustomerQueryModel();
		cqm.getPage().setNowPage(1);
		cqm.getPage().setPageShow(1);
		
		Page<CustomerModel> p = t.getS().getByConditionPage(cqm);
		
		System.out.println("list=="+p);
		
		
		
		Page<CustomerModel> p2 = t.getS().getByConditionPage(cqm);
		
		System.out.println("list2222=="+p);
	}
}
