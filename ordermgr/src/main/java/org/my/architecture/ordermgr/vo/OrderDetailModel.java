package org.my.architecture.ordermgr.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.my.architecture.common.vo.BaseModel;

@Entity
@Table(name = "tbl_orderDetail")
public class OrderDetailModel extends BaseModel{
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
	private Integer orderUuid;
	private Integer goodsUuid;
	private Integer orderNum;
	private Float price;
	private Float money;
	private Float saveMoney;
	
	public void setOrderUuid(Integer obj){
		this.orderUuid = obj;
	}
	public Integer getOrderUuid(){
		return this.orderUuid;
	}
	
	public void setGoodsUuid(Integer obj){
		this.goodsUuid = obj;
	}
	public Integer getGoodsUuid(){
		return this.goodsUuid;
	}
	
	public void setOrderNum(Integer obj){
		this.orderNum = obj;
	}
	public Integer getOrderNum(){
		return this.orderNum;
	}
	
	public void setPrice(Float obj){
		this.price = obj;
	}
	public Float getPrice(){
		return this.price;
	}
	
	public void setMoney(Float obj){
		this.money = obj;
	}
	public Float getMoney(){
		return this.money;
	}
	
	public void setSaveMoney(Float obj){
		this.saveMoney = obj;
	}
	public Float getSaveMoney(){
		return this.saveMoney;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+"[orderUuid=" + this.getOrderUuid() + ",goodsUuid=" + this.getGoodsUuid() + ",orderNum=" + this.getOrderNum() + ",price=" + this.getPrice() + ",money=" + this.getMoney() + ",saveMoney=" + this.getSaveMoney() + ",]";
	}	
}
