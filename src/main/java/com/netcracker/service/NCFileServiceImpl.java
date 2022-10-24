package com.netcracker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.netcracker.entity.NCFile;
import com.netcracker.repository.INCFileRepository;
import com.netcracker.utils.IStorageService;


@Service
@Transactional
public class NCFileServiceImpl implements INCFileService{
	
	@Autowired
	private INCFileRepository ncFileRepository;
	
	@Autowired
	private IStorageService storageService;

	@Override
	public List<NCFile> findAllNCFiles() {
		return ncFileRepository.findAll();
	}

	@Override
	public NCFile saveNCFile(MultipartFile pdfFile) {

		String fileName = storageService.azstore(pdfFile);
		
		if(fileName == null)
			return null;
		
		NCFile ncfile = new NCFile();
		
		ncfile.setNcfileName(fileName);
		
		return ncFileRepository.saveAndFlush(ncfile);
	}

}
