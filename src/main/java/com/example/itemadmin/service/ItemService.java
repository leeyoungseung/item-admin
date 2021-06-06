package com.example.itemadmin.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.itemadmin.domain.Item;
import com.example.itemadmin.exception.ResourceNotFoundException;
import com.example.itemadmin.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

	private static final Logger log = LogManager.getLogger(ItemService.class);
	
	private final ItemRepository itemRepository;

	public ResponseEntity<Item> getItem(int itemId) {
		log.info("ItemService getItem ID : "+itemId);
		Optional<Item> item = itemRepository.findById((Integer)itemId);
		
		log.info("ItemService Item : "+item.get());
		return ResponseEntity.of(item);
	}

	
	public ResponseEntity<String> createItem(Item item) {
		log.info("ItemService createItem ID : "+item);
		
		Optional<Item> existItem = itemRepository.findByItemName(item.getItemName());
		if (!existItem.isEmpty()) {
			return ResponseEntity.badRequest()
					.body("Item exist.");
		}
		
		Item createdItem = itemRepository.save(item);
		
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Item applied ["+createdItem.getItemId()+"]");
	}


	public ResponseEntity<Item> updateItem(int itemId, Item item) {
		log.info("ItemService updateItem ID : "+itemId);
		
		Item existItem = itemRepository.findById((Integer)itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Not Found Item : "+itemId));
		
		log.info("ItemService updateItem exist : "+existItem);
		log.info("ItemService updateItem update : "+item);
		
		existItem.setItemDescription(item.getItemDescription());
		existItem.setMakerCode(item.getMakerCode());
		existItem.setPrice(item.getPrice());
		existItem.setSaleStatus(item.getSaleStatus());
		
		return ResponseEntity.ok(itemRepository.save(existItem));
	}


	public ResponseEntity<Map<String, Boolean>> deleteItem(int itemId) {
		log.info("ItemService deleteItem ID : "+itemId);
		Item existItem = itemRepository.findById((Integer)itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Not Found Item : "+itemId));
		
		itemRepository.delete(existItem);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
}
