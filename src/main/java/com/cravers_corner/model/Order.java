package com.cravers_corner.model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private String status;
    private double totalAmount;
    private String orderNote;
    private Timestamp orderDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<OrderItem> items;
    private String orderContact;
    private String shippingAddress;
    private String customerUsername;
    private String customerfname;
    private String customerlname;
    

	//getter and setter
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getOrderContact() {
		return orderContact;
	}

	public void setOrderContact(String orderContact) {
		this.orderContact = orderContact;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	
	public String getCustomerlname() {
		return customerlname;
	}

	public void setCustomerlname(String customerlname) {
		this.customerlname = customerlname;
	}
	
	public String getCustomerfname() {
		return customerfname;
	}

	public void setCustomerfname(String customerfname) {
		this.customerfname = customerfname;
	}
    
    
}