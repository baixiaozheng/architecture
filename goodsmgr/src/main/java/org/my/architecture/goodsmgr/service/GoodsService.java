package org.my.architecture.goodsmgr.service;


import java.util.List;

import javax.annotation.Resource;

import org.my.architecture.goodsmgr.dao.GoodsDao;
import org.my.architecture.goodsmgr.vo.GoodsModel;
import org.my.architecture.goodsmgr.vo.GoodsQueryModel;
import org.my.pageutil.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="goodsService")
@Transactional
public class GoodsService  implements IGoodsService{
	@Resource(name = "goodsDao")
	private GoodsDao goodsDao;
//	@Override
//	public Page<GoodsModel> getByConditionPage(GoodsQueryModel qm){
//		return dao.getByConditionPage(qm);
//	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void create(GoodsModel m) {
		goodsDao.create(m);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void update(GoodsModel m) {
		goodsDao.update(m);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void delete(int uuid) {
		goodsDao.delete(uuid);
	}
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public GoodsModel getByUuid(int uuid) {
		return goodsDao.getByUuid(uuid);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<GoodsModel> getByConditionPage(GoodsQueryModel gqm) {
		List<GoodsModel> list = goodsDao.getByConditionPage(gqm);
		gqm.getPage().setResult(list);
		return gqm.getPage();
	}
}