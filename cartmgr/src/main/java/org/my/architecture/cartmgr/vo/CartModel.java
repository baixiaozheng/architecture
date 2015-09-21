package org.my.architecture.cartmgr.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.my.architecture.common.vo.BaseModel;

@Entity
@Table(name = "tbl_cart")
public class CartModel extends BaseModel{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false)
	private Integer uuid;
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	private Integer customerUuid;
	private Integer goodsUuid;
	private Integer buyNum;
	
	public void setCustomerUuid(Integer obj){
		this.customerUuid = obj;
	}
	public Integer getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setGoodsUuid(Integer obj){
		this.goodsUuid = obj;
	}
	public Integer getGoodsUuid(){
		return this.goodsUuid;
	}
	
	public void setBuyNum(Integer obj){
		this.buyNum = obj;
	}
	public Integer getBuyNum(){
		return this.buyNum;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+"[customerUuid=" + this.getCustomerUuid() + ",goodsUuid=" + this.getGoodsUuid() + ",buyNum=" + this.getBuyNum() + ",]";
	}	
}
