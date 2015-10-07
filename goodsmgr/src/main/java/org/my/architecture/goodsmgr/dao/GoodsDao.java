package org.my.architecture.goodsmgr.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.common.dao.BaseDao;
import org.my.architecture.goodsmgr.vo.GoodsModel;
import org.my.architecture.goodsmgr.vo.GoodsQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danga.MemCached.MemCachedClient;

@Repository(value="goodsDao")
public class GoodsDao extends BaseDao{
	private final String MEM_PRE = "Goods";
	@Autowired
	private MemCachedClient mcc;
	@Override
	protected Class<?> entityClass() {
		return GoodsModel.class;
	}
	public GoodsModel create(GoodsModel goodsModel){
		return super.add(goodsModel);
	}
	
	public GoodsModel update(GoodsModel goodsModel){
		//查询缓存里面是否有这条数据，有的话，需更新缓存
		Object obj = mcc.get(MEM_PRE+goodsModel.getUuid());
		if(obj!=null){
			mcc.set(MEM_PRE+goodsModel.getUuid(), goodsModel);
		}
		return super.update(goodsModel);
	}
	
	public void delete(int uuid){
		
		super.remove(get(uuid));
		//查询缓存里面是否有这条数据，有的话，需从缓存中删除
		Object obj = mcc.get(MEM_PRE+uuid);
		if(obj!=null){
			mcc.delete(MEM_PRE+uuid);
		}
	}
	
	public GoodsModel getByUuid(int uuid){
		GoodsModel gm = null;
		//1：查缓存，如果有就从缓存取值并返回
		Object obj = mcc.get(MEM_PRE+uuid);
		if(obj!=null){
			gm = (GoodsModel)obj;
			return gm;
		}
		//2：缓存没有，从db中取
		gm = super.get(uuid);
		//3：把这个数据设置到缓存中
		mcc.set(MEM_PRE+uuid,gm);
		
		return gm;
	}
	
	public List<GoodsModel> getByConditionPage(GoodsQueryModel gqm){
		//1:根据条件去查询出所有的 ids
		List<Integer> ids = getIdsByConditionPage(gqm);
		//2：在内存中找 这些id对应的对象
		List<GoodsModel> listGm1 = new ArrayList<GoodsModel>();
//		String noIds = "";
		List<Integer> noIds = new ArrayList<Integer>();
		for(Integer id : ids){
			Object obj = mcc.get(MEM_PRE+id);
			if(obj!=null){
				GoodsModel gm = (GoodsModel)obj;
				listGm1.add(gm);
			}else{
//				noIds += id+",";
				noIds.add(id);
			}
		}
		System.out.println("noIds==="+noIds);
		//3：内存中找不到对应对象的id，从数据库里面获取，并设置到缓存中
//		List<GoodsModel> listGm2 = null; 
//		if(noIds.trim().length() > 0){
//			noIds = noIds.substring(noIds.length() - 1);
//			listGm2 = mapper.getByIds(noIds);
//			listGm1.addAll(listGm2);
		if(noIds.size()>0){
			for(Integer id : noIds){
				GoodsModel gm = getByUuid(id);
				
				mcc.set(MEM_PRE+id,gm);
				
				listGm1.add(gm);
			}
		}
		return listGm1;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Integer> getIdsByConditionPage(GoodsQueryModel gqm){
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
		List<GoodsModel> list = (List<GoodsModel>)super.listEntityWithSql(sql, condition);
		List<Integer> ids = new ArrayList<Integer>();
		for(GoodsModel gm : list){
			ids.add(gm.getUuid());
		}
		return ids;
	}
	
	@SuppressWarnings("unchecked")
	public List<GoodsModel> getByIds(String ids){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select uuid from tbl_goods where  uuid in (:ids) " ;
		condition.put("ids", ids);
		return (List<GoodsModel>) super.listEntityWithSql(sql, condition);
	}
//	public Page<GoodsModel> getByCondition(GoodsQueryModel qm);
}
