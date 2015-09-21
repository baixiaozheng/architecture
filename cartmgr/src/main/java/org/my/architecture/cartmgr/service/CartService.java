package org.my.architecture.cartmgr.service;


import java.util.List;

import javax.annotation.Resource;

import org.my.architecture.cartmgr.dao.CartDao;
import org.my.architecture.cartmgr.vo.CartModel;
import org.my.architecture.cartmgr.vo.CartQueryModel;
import org.my.pageutil.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="cartService")
public class CartService implements ICartService{
	@Resource(name="cartDao")
	private CartDao cartDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public CartModel create(CartModel cartModel) {
		return cartDao.create(cartModel);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public CartModel update(CartModel cartModel) {
		return cartDao.update(cartModel);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void delete(int uuid) {
		cartDao.delete(uuid);
		
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public CartModel getByUuid(int uuid) {
		return cartDao.getByUuid(uuid);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page getByConditionPage(CartQueryModel cqm) {
		List<CartModel> list = cartDao.getByConditionPage(cqm);
		cqm.getPage().setResult(list);
		return cqm.getPage();
	}
	
}