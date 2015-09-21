package org.my.architecture.common.vo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.my.pageutil.Page;

public class BaseModel implements java.io.Serializable{
	
	private Page page = new Page();

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}	
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BaseModel other = (BaseModel) obj;
//		if (uuid == null) {
//			if (other.uuid != null)
//				return false;
//		} else if (!uuid.equals(other.uuid))
//			return false;
//		return true;
//	}
}
