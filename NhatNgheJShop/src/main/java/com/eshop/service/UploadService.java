package com.eshop.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	@Autowired
	ServletContext app;
	
	public List<File> save(MultipartFile[] uploadFiles, String dir){
		List<File> list = new ArrayList<File>();
		for(MultipartFile uploadFile: uploadFiles) {
			File file = this.save(uploadFile, dir);
			if(file != null) {
				list.add(file);
			}
		}
		return list;
	}
	
	public File save(MultipartFile uploadFile, String dir){
		
		try {
			if(!uploadFile.isEmpty()) {
				String realDir = app.getRealPath(dir);
				String uniqueName = this.getUniqueName(uploadFile);
				File newFile = new File(realDir, uniqueName);
				uploadFile.transferTo(newFile);
				return newFile;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	String getUniqueName(MultipartFile uploadFile) {
		String filename = System.currentTimeMillis() + uploadFile.getOriginalFilename();
		String ext = filename.substring(filename.lastIndexOf("."));
		return String.valueOf(filename.hashCode()) + ext;
	}
	
	public void write(Path path, byte[] data) {
		try {
			Files.write(path, data);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public byte[] read(Path path) {
		try {
			return Files.readAllBytes(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
