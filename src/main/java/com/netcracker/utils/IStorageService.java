package com.netcracker.utils;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	String azstore(MultipartFile file);
}
