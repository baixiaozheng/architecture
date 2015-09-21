package org.my.architecture.goodsmgr.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.my.architecture.common.vo.BaseModel;

@Entity
@Table(name = "tbl_goods")
public class GoodsModel extends BaseModel{
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
	private String name;
	private String imgPath;
	private String description;
	
	public void setName(String obj){
		this.name = obj;
	}
	public String getName(){
		return this.name;
	}
	
	public void setImgPath(String obj){
		this.imgPath = obj;
	}
	public String getImgPath(){
		return this.imgPath;
	}
	
	public void setDescription(String obj){
		this.description = obj;
	}
	public String getDescription(){
		return this.description;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+"[name=" + this.getName() + ",imgPath=" + this.getImgPath() + ",description=" + this.getDescription() + ",]";
	}	
}
