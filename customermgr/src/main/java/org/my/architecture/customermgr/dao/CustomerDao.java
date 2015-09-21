package org.my.architecture.customermgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.common.dao.BaseDao;
import org.my.architecture.customermgr.vo.CustomerModel;
import org.my.architecture.customermgr.vo.CustomerQueryModel;
import org.springframework.stereotype.Repository;

@Repository(value="customerDao")
public class CustomerDao extends BaseDao{

	@Override
	protected Class<?> entityClass() {
		return CustomerModel.class;
	}
	public CustomerModel create(CustomerModel customerModel){
		return super.add(customerModel);
	}
	
	public CustomerModel update(CustomerModel customerModel){
		return super.update(customerModel);
	}
	
	public void delete(int uuid){
		super.remove(get(uuid));
	}
	
	public CustomerModel getByUuid(int uuid){
		return super.get(uuid);
	}
	@SuppressWarnings("unchecked")
	public List<CustomerModel> getByConditionPage(CustomerQueryModel cqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_customer where 1=1 " ;
		if(cqm.getUuid() != null && cqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", cqm.getUuid());
		}
		if(cqm.getCustomerId()!=null ){
			sql+="and customerId=:customerId ";
			condition.put("customerId", cqm.getCustomerId());
		}
		if (cqm.getShowName()!=null && cqm.getShowName()!=""){
			sql+=" and showName=:showName";
			condition.put("showName", cqm.getShowName());
		}
		
		return (List<CustomerModel>) super.listEntityWithSql(sql, condition);
	}
	public CustomerModel getByCustomerId(String customerId){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_customer where customerId=:customerId " ;
		condition.put("customerId", customerId);
		return super.getWithSql(sql, condition);
	}
}
