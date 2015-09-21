package org.my.architecture.storemgr.service;


import java.util.List;

import javax.annotation.Resource;

import org.my.architecture.storemgr.dao.StoreDao;
import org.my.architecture.storemgr.vo.StoreModel;
import org.my.architecture.storemgr.vo.StoreQueryModel;
import org.my.pageutil.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="storeService")
public class StoreService implements IStoreService{
	@Resource(name = "storeDao")
	private StoreDao storeDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public StoreModel getByGoodsUuid(int goodsUuid) {
		return storeDao.getByGoodsUuid(goodsUuid);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void create(StoreModel m) {
		storeDao.create(m);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void update(StoreModel m) {
		storeDao.update(m);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void delete(int uuid) {
		storeDao.delete(uuid);
	}
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public StoreModel getByUuid(int uuid) {
		return storeDao.getByUuid(uuid);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<StoreModel> getByConditionPage(StoreQueryModel cqm) {
		List<StoreModel> list = storeDao.getByConditionPage(cqm);
		cqm.getPage().setResult(list);
		return cqm.getPage();
	}
	
}