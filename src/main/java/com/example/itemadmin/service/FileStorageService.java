package com.example.itemadmin.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	@Value("${property.app.upload-path}")
	private String uploadPath;

	
	/**
	 *  동시에 여러개의 파일업로드를 처리할때 
	 * 
	 * @param files
	 * @param itemId
	 * @return
	 */
	public List<String> save(MultipartFile[] files, int itemId) {
		try {
			List<String> uploadedFiles = new ArrayList<String>();
			System.out.println("Resource Path : ["+uploadPath+"]");
			
			String uploadDirPath = uploadPath+itemId+"\\";
			File uploadDir = new File(uploadDirPath);
			Path root = Paths.get(uploadDirPath);
			if (!uploadDir.exists()) {
				Files.createDirectory(root);
			}
			
			int cnt = 0;
			for (MultipartFile file : files) {
				String fileName = 
						new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					    + "_"+
					    file.getOriginalFilename();
				
				System.out.println("[ "+cnt+" ]"+ fileName);
				
				Files.copy(file.getInputStream(), root.resolve(fileName));
				uploadedFiles.add(fileName);
				
				cnt++;
			}

			return uploadedFiles;
			
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
	
	
	/**
	 * 파일 하나의 업로드를 처리할때 
	 * 
	 * @param file
	 * @param itemId
	 * @return
	 */
	public String saveOne(MultipartFile file, int itemId) {
		try {
			System.out.println("Resource Path : [" + uploadPath + "]");
			
			String uploadDirPath = uploadPath+itemId+"\\";
			File uploadDir = new File(uploadDirPath);
			Path root = Paths.get(uploadDirPath);
			if (!uploadDir.exists()) {
				Files.createDirectory(root);
			}
			
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_"
					+ file.getOriginalFilename();

			Files.copy(file.getInputStream(), root.resolve(fileName));

			
			return fileName;

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	
	/**
	 * 파일 가져오기
	 * 
	 * @param filename
	 * @param itemId
	 * @return
	 */
	public Resource load(String filename, int itemId) {
		
		String filePath = uploadPath+itemId+"\\"+filename;
		try {
			Path file = Paths.get(filePath);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	
	/**
	 * 파일 삭제하기
	 * 
	 * @param filename
	 */
	public void deleteFile(String filename) {
		Path file = Paths.get(filename);
		FileSystemUtils.deleteRecursively(file.toFile());
	}

}
