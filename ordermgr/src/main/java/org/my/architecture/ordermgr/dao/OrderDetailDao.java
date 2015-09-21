package org.my.architecture.ordermgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.common.dao.BaseDao;
import org.my.architecture.ordermgr.vo.OrderDetailModel;
import org.my.architecture.ordermgr.vo.OrderDetailQueryModel;
import org.springframework.stereotype.Repository;

@Repository(value="orderDetailDao")
public class OrderDetailDao extends BaseDao{

	@Override
	protected Class<?> entityClass() {
		return OrderDetailModel.class;
	}
	public OrderDetailModel create(OrderDetailModel orderDetailModel){
		return super.add(orderDetailModel);
	}
	
	public OrderDetailModel update(OrderDetailModel orderDetailModel){
		return super.update(orderDetailModel);
	}
	
	public void delete(int uuid){
		super.remove(get(uuid));
	}
	
	public OrderDetailModel getByUuid(int uuid){
		return super.get(uuid);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderDetailModel> getByConditionPage(OrderDetailQueryModel cqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_orderDetail where 1=1 " ;
		if(cqm.getUuid() != null && cqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", cqm.getUuid());
		}
		return (List<OrderDetailModel>) super.listEntityWithSql(sql, condition);
	}
}
