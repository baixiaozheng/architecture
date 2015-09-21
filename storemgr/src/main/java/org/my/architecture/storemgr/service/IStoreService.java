package org.my.architecture.storemgr.service;


import org.my.architecture.common.service.IBaseService;
import org.my.architecture.storemgr.vo.StoreModel;
import org.my.architecture.storemgr.vo.StoreQueryModel;

public interface IStoreService extends IBaseService<StoreModel,StoreQueryModel>{
	public StoreModel getByGoodsUuid(int goodsUuid);
}

