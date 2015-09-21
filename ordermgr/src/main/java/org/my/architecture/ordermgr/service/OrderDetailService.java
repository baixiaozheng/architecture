package org.my.architecture.ordermgr.service;


import java.util.List;

import javax.annotation.Resource;

import org.my.architecture.ordermgr.dao.OrderDetailDao;
import org.my.architecture.ordermgr.vo.OrderDetailModel;
import org.my.architecture.ordermgr.vo.OrderDetailQueryModel;
import org.my.pageutil.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "orderDetailService")
public class OrderDetailService implements IOrderDetailService{
	@Resource(name = "orderDetailDao")
	private OrderDetailDao orderDetailDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void create(OrderDetailModel m) {
		orderDetailDao.create(m);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void update(OrderDetailModel m) {
		orderDetailDao.update(m);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void delete(int uuid) {
		orderDetailDao.delete(uuid);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public OrderDetailModel getByUuid(int uuid) {
		return orderDetailDao.getByUuid(uuid);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<OrderDetailModel> getByConditionPage(OrderDetailQueryModel cqm) {
		List<OrderDetailModel> list = orderDetailDao.getByConditionPage(cqm);
		cqm.getPage().setResult(list);
		return cqm.getPage();
	}
	
}