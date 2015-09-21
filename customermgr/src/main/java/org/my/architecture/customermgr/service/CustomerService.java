package org.my.architecture.customermgr.service;

import java.util.List;

import javax.annotation.Resource;

import org.my.architecture.customermgr.dao.CustomerDao;
import org.my.architecture.customermgr.vo.CustomerModel;
import org.my.architecture.customermgr.vo.CustomerQueryModel;
import org.my.pageutil.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="customerService")
public class CustomerService implements ICustomerService{
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public CustomerModel getByCustomerId(String customerId) {
		return customerDao.getByCustomerId(customerId);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void create(CustomerModel m) {
		customerDao.create(m);
		
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void update(CustomerModel m) {
		customerDao.update(m);
		
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void delete(int uuid) {
		customerDao.delete(uuid);
		
	}
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public CustomerModel getByUuid(int uuid) {
		return customerDao.getByUuid(uuid);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<CustomerModel> getByConditionPage(CustomerQueryModel cqm) {
		List<CustomerModel> list = customerDao.getByConditionPage(cqm);
		cqm.getPage().setResult(list);
		return cqm.getPage();
	}
	
}
