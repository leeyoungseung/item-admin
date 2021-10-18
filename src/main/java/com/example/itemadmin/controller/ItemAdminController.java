package com.example.itemadmin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.itemadmin.domain.Item;
import com.example.itemadmin.service.FileStorageService;
import com.example.itemadmin.service.ItemService;

@RestController
@RequestMapping(path = "/api")
public class ItemAdminController {

	private static final Logger log = LogManager.getLogger(ItemAdminController.class);

	@Autowired
	ItemService itemService;
	
	@Autowired
	FileStorageService fileStorageService;

	@GetMapping(path = "/items")
	public ResponseEntity<List<Item>> getItemAll() {
		log.info("ItemAdminController getItemAll ");
		return itemService.getItemAll();
	}

	@GetMapping(path = "/items/{itemId}")
	public ResponseEntity<Item> getItemOne(@PathVariable int itemId) {
		log.info("ItemAdminController getItemOne ID : " + itemId);
		return itemService.getItem(itemId);
	}

	@PostMapping(path = "/items")
	public ResponseEntity<String> createItemOne(@RequestBody Item item) {
		log.info("ItemAdminController createItemOne Form : " + item);
		return itemService.createItem(item);
	}

	@PutMapping(path = "/items/{itemId}")
	public ResponseEntity<Item> updateItemOne(@PathVariable int itemId, @RequestBody Item item) {
		log.info("ItemAdminController updateItemOne ID : " + itemId);
		log.info("ItemAdminController updateItemOne Form : " + item);

		return itemService.updateItem(itemId, item);
	}

	@DeleteMapping("/items/{itemId}")
	public ResponseEntity<Map<String, Boolean>> deleteItemOne(@PathVariable int itemId) {
		log.info("ItemAdminController deleteItemOne ID : " + itemId);

		return itemService.deleteItem(itemId);
	}

	
	@PostMapping("items/{itemId}/upload-file")
	public ResponseEntity<String> uploadFileOne(
			@RequestParam(value = "file") MultipartFile file,
			@PathVariable("itemId") int itemId
			) {
		log.info("ItemAdminController uploadFileOne ID : " + itemId + "fileName : "+file.getOriginalFilename());
		String fileName = null;
		try {
			fileName = fileStorageService.saveOne(file, itemId);
			return ResponseEntity.status(HttpStatus.OK).body(fileName);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(fileName);
		}
	}
	
	
	@PostMapping("items/{itemId}/upload-files")
	public ResponseEntity<List<String>> uploadFiles(
			@RequestParam(value = "files") MultipartFile[] files,
			@PathVariable("itemId") int itemId
			) {
		log.info("ItemAdminController uploadFiles ID : " + itemId);
		for (MultipartFile file : files) {
			System.out.println("File Name : "+file.getOriginalFilename());
		}
		List<String> fileNames = null;
		try {
			fileNames = fileStorageService.save(files, itemId);
			return ResponseEntity.status(HttpStatus.OK).body(fileNames);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(fileNames);
		}
	}

	
	@GetMapping("items/{itemId}/{filename:.+}")
	public ResponseEntity<Resource> getFile(
			@PathVariable int itemId,
			@PathVariable String filename ) {
		log.info("ItemAdminController getFile filename : "+filename);
		Resource file = fileStorageService.load(filename, itemId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	
}
