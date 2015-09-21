package org.my.architecture.customermgr.service;

import org.my.architecture.common.service.IBaseService;
import org.my.architecture.customermgr.vo.CustomerModel;
import org.my.architecture.customermgr.vo.CustomerQueryModel;

public interface ICustomerService extends IBaseService<CustomerModel, CustomerQueryModel>{
	public CustomerModel getByCustomerId(String customerId);
}
