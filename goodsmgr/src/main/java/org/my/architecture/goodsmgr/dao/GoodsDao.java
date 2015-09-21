package org.my.architecture.goodsmgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.common.dao.BaseDao;
import org.my.architecture.goodsmgr.vo.GoodsModel;
import org.my.architecture.goodsmgr.vo.GoodsQueryModel;
import org.springframework.stereotype.Repository;

@Repository(value="goodsDao")
public class GoodsDao extends BaseDao{

	@Override
	protected Class<?> entityClass() {
		return GoodsModel.class;
	}
	public GoodsModel create(GoodsModel goodsModel){
		return super.add(goodsModel);
	}
	
	public GoodsModel update(GoodsModel goodsModel){
		return super.update(goodsModel);
	}
	
	public void delete(int uuid){
		super.remove(get(uuid));
	}
	
	public GoodsModel getByUuid(int uuid){
		return super.get(uuid);
	}
	
	@SuppressWarnings("unchecked")
	public List<GoodsModel> getByConditionPage(GoodsQueryModel gqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_goods where 1=1 " ;
		if(gqm.getUuid() != null && gqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", gqm.getUuid());
		}
		if(gqm.getName()!=null){
			sql+="and name like :name ";
			condition.put("name", gqm.getName());
		}
		return (List<GoodsModel>) super.listEntityWithSql(sql, condition);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Integer> getIdsByConditionPage(GoodsQueryModel gqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select uuid from tbl_goods where 1=1 " ;
		if(gqm.getUuid() != null && gqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", gqm.getUuid());
		}
		if(gqm.getName()!=null){
			sql+="and name like :name ";
			condition.put("name", gqm.getName());
		}
		return (List<Integer>) super.listEntityWithSql(sql, condition);
	}
	
	public List<GoodsModel> getByIds(String ids){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select uuid from tbl_goods where  uuid in (:ids) " ;
		condition.put("ids", ids);
		return (List<GoodsModel>) super.listEntityWithSql(sql, condition);
	}
//	public Page<GoodsModel> getByCondition(GoodsQueryModel qm);
}
