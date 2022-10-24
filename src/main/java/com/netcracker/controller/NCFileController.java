package com.netcracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcracker.entity.NCFile;
import com.netcracker.payload.NCFileDTO;
import com.netcracker.payload.Response;
import com.netcracker.service.INCFileService;

@RequestMapping(produces = "application/json")
@CrossOrigin
@Controller
public class NCFileController {

	@Autowired
	private INCFileService ncFileService;
	
	@GetMapping("")
	public ResponseEntity<?> findAllNCFiles() {
		List<NCFile> NCFileList = ncFileService.findAllNCFiles();
		
		
		for(NCFile ele : NCFileList)
		{
			ele.setNcfileName("https://azstorageogm.blob.core.windows.net/greenmartimages/"+ele.getNcfileName());
		}
		
		System.out.println(NCFileList);
		
		return Response.success(NCFileList);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> saveNCFile(NCFileDTO dto){
		

		NCFile nCFile = ncFileService.saveNCFile(dto.getNCFILE());
		
		if(nCFile == null)
			return Response.error("error");
		
		return Response.success(nCFile);
	
	}
}
