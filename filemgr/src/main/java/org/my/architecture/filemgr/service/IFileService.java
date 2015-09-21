package org.my.architecture.filemgr.service;


import org.my.architecture.common.service.IBaseService;
import org.my.architecture.filemgr.vo.FileModel;
import org.my.architecture.filemgr.vo.FileQueryModel;

public interface IFileService extends IBaseService<FileModel,FileQueryModel>{
	public FileModel getByFileName(String fileName);
}

