package org.my.architecture.filemgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my.architecture.common.dao.BaseDao;
import org.my.architecture.filemgr.vo.FileModel;
import org.my.architecture.filemgr.vo.FileQueryModel;
import org.springframework.stereotype.Repository;

@Repository(value="fileDao")
public class FileDao extends BaseDao{

	@Override
	protected Class<?> entityClass() {
		return FileModel.class;
	}
	
	public FileModel create(FileModel fileModel){
		return super.add(fileModel);
	}
	
	public FileModel update(FileModel fileModel){
		return super.update(fileModel);
	}
	
	public void delete(int uuid){
		super.remove(get(uuid));
	}
	
	public FileModel getByUuid(int uuid){
		return super.get(uuid);
	}
	
	@SuppressWarnings("unchecked")
	public List<FileModel> getByConditionPage(FileQueryModel fqm){
		Map<String, Object> condition = new HashMap<String, Object>();
		String sql = "select * from tbl_file where 1=1 " ;
		if(fqm.getUuid() != null && fqm.getUuid()>0){
			sql+="and uuid=:uuid ";
			condition.put("uuid", fqm.getUuid());
		}
		if(fqm.getFileName()!=null ){
			sql+="and fileName=:fileName ";
			condition.put("fileName", fqm.getFileName());
		}
		return (List<FileModel>) super.listEntityWithSql(sql, condition);
	}
}
