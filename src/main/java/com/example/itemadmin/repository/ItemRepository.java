package com.example.itemadmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemadmin.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Optional<Item> findByItemName(String itemName);

}
