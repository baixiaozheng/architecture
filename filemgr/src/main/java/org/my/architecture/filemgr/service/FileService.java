package org.my.architecture.filemgr.service;


import java.util.List;

import javax.annotation.Resource;

import org.my.architecture.filemgr.dao.FileDao;
import org.my.architecture.filemgr.vo.FileModel;
import org.my.architecture.filemgr.vo.FileQueryModel;
import org.my.pageutil.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fileService")
public class FileService implements IFileService{
	@Resource(name = "fileDao")
	private FileDao fileDao;
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public FileModel getByFileName(String fileName) {
		FileQueryModel qm = new FileQueryModel();
		qm.setFileName(fileName);
		
		List<FileModel> list = fileDao.getByConditionPage(qm);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void create(FileModel m) {
		fileDao.create(m);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void update(FileModel m) {
		fileDao.update(m);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public void delete(int uuid) {
		fileDao.delete(uuid);
	}
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public FileModel getByUuid(int uuid) {
		return fileDao.getByUuid(uuid);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<FileModel> getByConditionPage(FileQueryModel fqm) {
		List<FileModel> list = fileDao.getByConditionPage(fqm);
		fqm.getPage().setResult(list);
		return fqm.getPage();
	}
	
}