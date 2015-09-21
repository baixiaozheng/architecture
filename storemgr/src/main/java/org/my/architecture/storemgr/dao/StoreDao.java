package org.my.architecture.storemgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.common.dao.BaseDao;
import org.my.architecture.storemgr.vo.StoreModel;
import org.my.architecture.storemgr.vo.StoreQueryModel;
import org.springframework.stereotype.Repository;

@Repository(value="storeDao")
public class StoreDao extends BaseDao{
	
	@Override
	protected Class<?> entityClass() {
		return StoreModel.class;
	}
	
	public StoreModel create(StoreModel cartModel){
		return super.add(cartModel);
	}
	
	public StoreModel update(StoreModel cartModel){
		return super.update(cartModel);
	}
	
	public void delete(int uuid){
		super.remove(get(uuid));
	}
	
	public StoreModel getByUuid(int uuid){
		return super.get(uuid);
	}
	
	@SuppressWarnings("unchecked")
	public List<StoreModel> getByConditionPage(StoreQueryModel cqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_store where 1=1 " ;
		if(cqm.getUuid() != null && cqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", cqm.getUuid());
		}
		return (List<StoreModel>) super.listEntityWithSql(sql, condition);
	}
	
	
	public StoreModel getByGoodsUuid(int goodsUuid){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_store where goodsUuid=:goodsUuid " ;
		condition.put("goodsUuid", goodsUuid);
		return super.getWithSql(sql, condition);
	}

}
