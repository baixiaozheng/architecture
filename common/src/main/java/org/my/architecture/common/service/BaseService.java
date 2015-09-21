package org.my.architecture.common.service;

import java.util.List;

import org.my.architecture.common.dao.BaseDAOBAK;
import org.my.architecture.common.vo.BaseModel;
import org.my.pageutil.Page;


public class BaseService<M, QM extends BaseModel> implements IBaseService<M,QM> {
	private BaseDAOBAK dao = null;
	public void setDAO(BaseDAOBAK dao){
		this.dao = dao;
	}
	public void create(M m) {
		dao.create(m);
	}

	public void update(M m) {
		dao.update(m);
	}

	public void delete(int uuid) {
		dao.delete(uuid);
	}

	public M getByUuid(int uuid) {
		return (M)dao.getByUuid(uuid);
	}

	public Page<M> getByConditionPage(QM qm) {
		List<M> list = dao.getByConditionPage(qm);
		qm.getPage().setResult(list);
		
		return qm.getPage();
	}
}
