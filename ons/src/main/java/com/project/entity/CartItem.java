package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CART_ITEM1")
public class CartItem {

	@Id
	@Column(name= "CART_ITEM_ID")
	private int id;
	
	@Column(name= "QUANTITY")
	private int quantity;
	
	@Column(name = "UNIT_PRICE")
	private double unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "CART_ID")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
