package org.my.architecture.customermgr.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.my.architecture.common.vo.BaseModel;

@Entity
@Table(name = "tbl_customer")
public class CustomerModel extends BaseModel{
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
	private String customerId;
	private String pwd;
	private String showName;
	private String trueName;
	private String registerTime;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		return "CustomerModel [uuid=" + getUuid() + ", customerId=" + customerId
				+ ", pwd=" + pwd + ", showName=" + showName + ", trueName="
				+ trueName + ", registerTime=" + registerTime + "]";
	}
	
}
