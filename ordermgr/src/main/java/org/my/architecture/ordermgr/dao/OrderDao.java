package org.my.architecture.ordermgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.common.dao.BaseDao;
import org.my.architecture.ordermgr.vo.OrderModel;
import org.my.architecture.ordermgr.vo.OrderQueryModel;
import org.springframework.stereotype.Repository;

@Repository(value="orderDao")
public class OrderDao extends BaseDao{

	@Override
	protected Class<?> entityClass() {
		return OrderModel.class;
	}
	
	public OrderModel create(OrderModel orderModel){
		return super.add(orderModel);
	}
	
	public OrderModel update(OrderModel orderModel){
		return super.update(orderModel);
	}
	
	public void delete(int uuid){
		super.remove(get(uuid));
	}
	
	public OrderModel getByUuid(int uuid){
		return super.get(uuid);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderModel> getByConditionPage(OrderQueryModel cqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_order where 1=1 " ;
		if(cqm.getUuid() != null && cqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", cqm.getUuid());
		}
		if(cqm.getCustomerUuid()!=null && cqm.getCustomerUuid() > 0){
			sql+="and customerUuid=:customerUuid ";
			condition.put("customerUuid", cqm.getCustomerUuid());
		}
		if(cqm.getOrderTime()!=null && cqm.getOrderTime()!=""){
			sql+="and orderTime=:orderTime ";
			condition.put("orderTime", cqm.getOrderTime());
		}
		return (List<OrderModel>) super.listEntityWithSql(sql, condition);
	}
	
}
