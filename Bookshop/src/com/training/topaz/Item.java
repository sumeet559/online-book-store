package com.training.topaz;

public class Item {

	@Override
	public String toString() {
		return "Item [title=" + title + ", author=" + author + ", publication="
				+ publication + ", category=" + category + ", description="
				+ description + ", cost=" + cost + ", stock=" + stock
				+ ", itemNo=" + itemNo + "]";
	}
	private String title,author,publication,category,description;
	private double cost;
	private int stock,itemNo;
	public Item(){
		
	}
	public int getItemNo() {
		return itemNo;
	}
	public Item(String title, String author, String publication, String category,
			String description, double cost, int stock) {
		super();
		this.title = title;
		this.author = author;
		this.publication = publication;
		this.category = category;
		this.description = description;
		this.cost = cost;
		this.stock = stock;
	}
		
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublication() {
		return publication;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
