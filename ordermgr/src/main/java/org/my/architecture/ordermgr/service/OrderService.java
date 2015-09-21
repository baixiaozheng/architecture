package org.my.architecture.ordermgr.service;


import javax.annotation.Resource;

import org.my.architecture.cartmgr.service.ICartService;
import org.my.architecture.cartmgr.vo.CartModel;
import org.my.architecture.cartmgr.vo.CartQueryModel;
import org.my.architecture.common.service.BaseService;
import org.my.architecture.ordermgr.dao.OrderDao;
import org.my.architecture.ordermgr.vo.OrderDetailModel;
import org.my.architecture.ordermgr.vo.OrderModel;
import org.my.architecture.ordermgr.vo.OrderQueryModel;
import org.my.architecture.storemgr.service.IStoreService;
import org.my.architecture.storemgr.vo.StoreModel;
import org.my.pageutil.Page;
import org.my.util.format.DateFormatHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService extends BaseService<OrderModel,OrderQueryModel> implements IOrderService{
	@Resource(name = "orderDao")
	private OrderDao orderDao;
	@Autowired
	private ICartService cartService;
	@Autowired
	private IOrderDetailService orderDetailService;
	@Autowired
	private IStoreService storeService;
	public void order(int customerUuid) {
//		//消息的 生产者
//		for(int i=0;i<100;i++){
//			QueueSender.sendMsg(customerUuid);
//		}
//		
		
			//消息的消费者来完成如下功能
			CartQueryModel cqm = new CartQueryModel();
			cqm.getPage().setPageShow(1000);
			cqm.setCustomerUuid(customerUuid);
			
			Page<CartModel>  page = cartService.getByConditionPage(cqm);
			//2:
			float totalMoney = 0.0f;
			for(CartModel cm : page.getResult()){
				totalMoney += 10;
			}
			
			OrderModel order = new OrderModel();
			order.setCustomerUuid(customerUuid);
			order.setOrderTime(DateFormatHelper.long2str(System.currentTimeMillis()));
			order.setSaveMoney(0.0F);
			order.setTotalMoney(totalMoney);
			order.setState(1);
	
			create(order);
			
			OrderQueryModel oqm = new OrderQueryModel();
			oqm.setOrderTime(order.getOrderTime());
			
			Page<OrderModel>  orderPage = getByConditionPage(oqm);
			order = orderPage.getResult().get(0);
			
			//3:
			for(CartModel cm : page.getResult()){
				OrderDetailModel odm = new OrderDetailModel();
				odm.setGoodsUuid(cm.getGoodsUuid());
				odm.setOrderNum(cm.getBuyNum());
				odm.setPrice(10.0f);
				odm.setMoney(odm.getPrice() * odm.getOrderNum());
				odm.setSaveMoney(0.0f);
				odm.setOrderUuid(order.getUuid());
				
				orderDetailService.create(odm);
				//4:
				StoreModel storeModel = storeService.getByGoodsUuid(cm.getGoodsUuid());
				storeModel.setStoreNum(storeModel.getStoreNum() - odm.getOrderNum());
				storeService.update(storeModel);
				
				//5:
	//			ics.delete(cm.getUuid());
			}	
		
		//long a2 = new Date().getTime();
		//System.out.println("NONO send one msg time==="+(a2-a));
	}
	
}