package org.my.architecture.cartmgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.cartmgr.vo.CartModel;
import org.my.architecture.cartmgr.vo.CartQueryModel;
import org.my.architecture.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository(value="cartDao")
public class CartDao extends BaseDao{

	@Override
	protected Class<?> entityClass() {
		return CartModel.class;
	}
	public CartModel create(CartModel cartModel){
		return super.add(cartModel);
	}
	
	public CartModel update(CartModel cartModel){
		return super.update(cartModel);
	}
	
	public void delete(int uuid){
		super.remove(get(uuid));
	}
	
	public CartModel getByUuid(int uuid){
		return super.get(uuid);
	}
	
	@SuppressWarnings("unchecked")
	public List<CartModel> getByConditionPage(CartQueryModel cqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_cart where 1=1 " ;
		if(cqm.getUuid() != null && cqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", cqm.getUuid());
		}
		if(cqm.getCustomerUuid()!=null && cqm.getCustomerUuid() > 0){
			sql+="and customerUuid=:customerUuid ";
			condition.put("customerUuid", cqm.getCustomerUuid());
		}
		return (List<CartModel>) super.listEntityWithSql(sql, condition);
	}
}
