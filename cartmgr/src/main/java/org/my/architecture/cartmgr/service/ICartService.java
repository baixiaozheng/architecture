package org.my.architecture.cartmgr.service;

import java.util.List;

import org.my.architecture.cartmgr.vo.CartModel;
import org.my.architecture.cartmgr.vo.CartQueryModel;
import org.my.pageutil.Page;



public interface ICartService {
	
	CartModel create(CartModel cartModel);
	
	CartModel update(CartModel cartModel);
	
	void delete(int uuid);
	
	CartModel getByUuid(int uuid);
	
	Page getByConditionPage(CartQueryModel cqm);
}

