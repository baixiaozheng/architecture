package org.my.architecture.common.service;

import org.my.architecture.common.vo.BaseModel;
import org.my.pageutil.Page;


public interface IBaseService<M,QM extends BaseModel> {
	public void create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public Page<M> getByConditionPage(QM qm);
}
