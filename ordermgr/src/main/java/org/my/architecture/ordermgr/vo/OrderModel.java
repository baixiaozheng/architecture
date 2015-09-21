package org.my.architecture.ordermgr.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.my.architecture.common.vo.BaseModel;

@Entity
@Table(name = "tbl_order")
public class OrderModel extends BaseModel{
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
	private String orderTime;
	private Float totalMoney;
	private Float saveMoney ;
	private Integer state;
	
	public void setCustomerUuid(Integer obj){
		this.customerUuid = obj;
	}
	public Integer getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setOrderTime(String obj){
		this.orderTime = obj;
	}
	public String getOrderTime(){
		return this.orderTime;
	}
	
	public void setTotalMoney(Float obj){
		this.totalMoney = obj;
	}
	public Float getTotalMoney(){
		return this.totalMoney;
	}
	
	public void setSaveMoney (Float obj){
		this.saveMoney  = obj;
	}
	public Float getSaveMoney (){
		return this.saveMoney ;
	}
	
	public void setState(Integer obj){
		this.state = obj;
	}
	public Integer getState(){
		return this.state;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+"[customerUuid=" + this.getCustomerUuid() + ",orderTime=" + this.getOrderTime() + ",totalMoney=" + this.getTotalMoney() + ",saveMoney =" + this.getSaveMoney () + ",state=" + this.getState() + ",]";
	}	
}
