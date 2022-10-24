package com.netcracker.utils;

import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.netcracker.exceptions.NotAPdfFileException;
import com.netcracker.exceptions.SizeLimitExceededException;

@Component
public class StorageServiceImpl implements IStorageService {

	public static String generateRandomHash(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk" + "lmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String azstore(MultipartFile file) {
		
		System.out.println(file.getSize());
		
		
		if(file.getSize() > 1000000)
			throw new SizeLimitExceededException("file size must not exceed 1 mb");
		
		System.out.println(file.getContentType());
		
		if(!file.getContentType().toString().equals("application/pdf"))
			throw new NotAPdfFileException("file is not a pdf file");
		
		
		
		String constr = "DefaultEndpointsProtocol=https;AccountName=azstorageogm;AccountKey=Ro4m7e18A0GIwNvRPDdF8RpEAZSTUuuiH9oqY7xrUvlbw9AG5V2Dz8UUCSuYcNYVK4ryT6+6eT6w+AStpgvDjg==;EndpointSuffix=core.windows.net";

		BlobContainerClient container = new BlobContainerClientBuilder().connectionString(constr)
				.containerName("greenmartimages").buildClient();
		

		
		String fileNameWithOutExt = file.getOriginalFilename().replaceFirst("[.][^.]+$", "");
		
		System.out.println(fileNameWithOutExt);
		
		String fileName = fileNameWithOutExt+StorageServiceImpl.generateRandomHash(7).toString()+ ".pdf";

		System.out.println(fileName);
		
		BlobClient blob = container.getBlobClient(fileName);
		
		BlobHttpHeaders bobf = new BlobHttpHeaders();
		
		bobf.setContentDisposition("attachment");
		bobf.setContentType("application/pdf");
		

		try {
			//blob.upload(file.getInputStream(), file.getSize(), true);
			
			blob.uploadWithResponse(file.getInputStream(), file.getSize(), null, bobf, null, null, null, null, null);
			return fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
