package com.training.topaz;

import java.util.List;

public class Order {
private int orderid;
private List<Item> listItems;
private double total;
private String date;
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public List<Item> getListItems() {
	return listItems;
}
public void setListItems(List<Item> listItems) {
	this.listItems = listItems;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
@Override
public String toString() {
	return "Order [orderid=" + orderid + ", listItems=" + listItems
			+ ", total=" + total + ", date=" + date + "]";
}
}
