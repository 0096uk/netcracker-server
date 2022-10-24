package com.netcracker.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.netcracker.entity.NCFile;

public interface INCFileService {

	List<NCFile> findAllNCFiles();
	
	NCFile saveNCFile(MultipartFile pdfFile);
}
