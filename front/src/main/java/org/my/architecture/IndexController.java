package org.my.architecture;

import org.my.architecture.cartmgr.service.ICartService;
import org.my.architecture.cartmgr.vo.CartModel;
import org.my.architecture.cartmgr.vo.CartQueryModel;
import org.my.architecture.goodsmgr.service.IGoodsService;
import org.my.architecture.goodsmgr.vo.GoodsModel;
import org.my.architecture.goodsmgr.vo.GoodsQueryModel;
import org.my.architecture.ordermgr.service.IOrderDetailService;
import org.my.architecture.ordermgr.service.IOrderService;
import org.my.architecture.storemgr.service.IStoreService;
import org.my.pageutil.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private IGoodsService igs = null;
	@Autowired
	private ICartService ics = null;
	@Autowired
	private IOrderService ios = null;
	@Autowired
	private IOrderDetailService iods = null;
	@Autowired
	private IStoreService iss = null;
	
	@RequestMapping(value="/toIndex",method=RequestMethod.GET)
	public String toIndex(Model model){
		GoodsQueryModel gqm = new GoodsQueryModel();
		gqm.getPage().setPageShow(100);

		Page<GoodsModel> page = igs.getByConditionPage(gqm);
		
		model.addAttribute("page",page);
		
		
		return "index";
	}
	
	@RequestMapping(value="/toGoodsDesc/{goodsUuid}",method=RequestMethod.GET)
	public String toGoodsDesc(Model model,@PathVariable("goodsUuid")int goodsUuid){
		GoodsModel gm = igs.getByUuid(goodsUuid);
		
		model.addAttribute("m",gm);
		return "goods/desc";
	}
	
	@RequestMapping(value="/addToCart/{goodsUuid}",method=RequestMethod.GET)
	public String addToCart(Model model,@PathVariable("goodsUuid")int goodsUuid,@CookieValue("MyLogin")String myLogin){
		int customerUuid = Integer.parseInt( myLogin.split(",")[0]);
		
		CartModel cm = new CartModel();
		cm.setBuyNum(1);
		cm.setCustomerUuid(customerUuid);
		cm.setGoodsUuid(goodsUuid);
		
		ics.create(cm);
		///////////////////////////
		CartQueryModel cqm = new CartQueryModel();
		cqm.getPage().setPageShow(1000);
		cqm.setCustomerUuid(customerUuid);
		
		
		Page<CartModel>  page = ics.getByConditionPage(cqm);
		
		model.addAttribute("page",page);
		
		return "cart/myCart";
	}
	@RequestMapping(value="/toCart",method=RequestMethod.GET)
	public String toCart(Model model,@CookieValue("MyLogin")String myLogin){
		int customerUuid = Integer.parseInt( myLogin.split(",")[0]);
		
		CartQueryModel cqm = new CartQueryModel();
		cqm.getPage().setPageShow(1000);
		cqm.setCustomerUuid(customerUuid);
		
		Page<CartModel>  page = ics.getByConditionPage(cqm);
		
		model.addAttribute("page",page);
		
		return "cart/myCart";
	}
	@RequestMapping(value="/order",method=RequestMethod.GET)
	public String order(){//@CookieValue("MyLogin")String myLogin){
		//1:查出这个人购物车所有的信息		
		int customerUuid = 1;//Integer.parseInt( myLogin.split(",")[0]);
		
		ios.order(customerUuid);
		
		return "success";
	}
}
