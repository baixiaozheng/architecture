package org.my.architecture.ordermgr.service;


import org.my.architecture.common.service.IBaseService;
import org.my.architecture.ordermgr.vo.OrderModel;
import org.my.architecture.ordermgr.vo.OrderQueryModel;

public interface IOrderService extends IBaseService<OrderModel,OrderQueryModel>{
	public void order(int customerUuid);
}

