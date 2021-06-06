package com.example.itemadmin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemId")
	private Integer itemId;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "itemDescription")
	private String itemDescription;
	
	@Column(name = "makerCode")
	private String makerCode;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "saleStatus")
	private Integer saleStatus;
	
	@Column(name = "images")
	private String images;
}
