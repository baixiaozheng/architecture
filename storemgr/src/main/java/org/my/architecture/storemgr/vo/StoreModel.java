package org.my.architecture.storemgr.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.my.architecture.common.vo.BaseModel;

@Entity
@Table(name = "tbl_store")
public class StoreModel extends BaseModel{
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
	private Integer goodsUuid;
	private Integer storeNum;
	
	public void setGoodsUuid(Integer obj){
		this.goodsUuid = obj;
	}
	public Integer getGoodsUuid(){
		return this.goodsUuid;
	}
	
	public void setStoreNum(Integer obj){
		this.storeNum = obj;
	}
	public Integer getStoreNum(){
		return this.storeNum;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+"[goodsUuid=" + this.getGoodsUuid() + ",storeNum=" + this.getStoreNum() + ",]";
	}	
}
