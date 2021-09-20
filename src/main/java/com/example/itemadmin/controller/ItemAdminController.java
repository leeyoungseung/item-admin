package com.example.itemadmin.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itemadmin.domain.Item;
import com.example.itemadmin.service.ItemService;

@RestController
@RequestMapping(path = "/api")
public class ItemAdminController {
	
	private static final Logger log = LogManager.getLogger(ItemAdminController.class);
	
	@Autowired
	ItemService itemService;
	
	
	@GetMapping(path = "/items")
	public ResponseEntity<List<Item>> getItemAll() {
		log.info("ItemAdminController getItemAll ");
		return itemService.getItemAll();
	}
	
	
	@GetMapping(path = "/items/{itemId}")
	public ResponseEntity<Item> getItemOne(@PathVariable int itemId) {
		log.info("ItemAdminController getItemOne ID : "+itemId);
		return itemService.getItem(itemId);
	}
	
	
	@PostMapping(path = "/items")
	public ResponseEntity<String> createItemOne(@RequestBody Item item) {
		log.info("ItemAdminController createItemOne Form : "+item);
		return itemService.createItem(item);
	}
	
	
	@PutMapping(path = "/items/{itemId}")
	public ResponseEntity<Item> updateItemOne(@PathVariable int itemId, 
			@RequestBody Item item) {
		log.info("ItemAdminController updateItemOne ID : "+itemId);
		log.info("ItemAdminController updateItemOne Form : "+item);
		
		return itemService.updateItem(itemId, item);
	}
	
	
	@DeleteMapping("/items/{itemId}")
	public ResponseEntity<Map<String, Boolean>> deleteItemOne(@PathVariable int itemId) {
		log.info("ItemAdminController deleteItemOne ID : "+itemId);
		
		return itemService.deleteItem(itemId);
	}

}
