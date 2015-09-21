package org.my.architecture.filemgr.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.my.architecture.common.vo.BaseModel;

@Entity
@Table(name = "tbl_file")
public class FileModel extends BaseModel{
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
	private String fileName;
	private String remotePaths;
	
	public void setFileName(String obj){
		this.fileName = obj;
	}
	public String getFileName(){
		return this.fileName;
	}
	
	public void setRemotePaths(String obj){
		this.remotePaths = obj;
	}
	public String getRemotePaths(){
		return this.remotePaths;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+"[fileName=" + this.getFileName() + ",remotePaths=" + this.getRemotePaths() + ",]";
	}	
}
