package org.jsp.customerfoodorderapp.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String fooditem;
	@Column(nullable=false)
	private double cost;
	@CreationTimestamp
	private LocalDateTime ordered_time;
	@UpdateTimestamp
	private LocalDateTime deleivery_time;
	@Column(nullable=false)
	private String address;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFooditem() {
		return fooditem;
	}
	public void setFooditem(String fooditem) {
		this.fooditem = fooditem;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public LocalDateTime getOrdered_time() {
		return ordered_time;
	}
	public void setOrdered_time(LocalDateTime ordered_time) {
		this.ordered_time = ordered_time;
	}
	public LocalDateTime getDeleivery_time() {
		return deleivery_time;
	}
	public void setDeleivery_time(LocalDateTime deleivery_time) {
		this.deleivery_time = deleivery_time;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() 
	{
		return "FoodOrder [id=" + id + ", fooditem=" + fooditem + ", cost=" + cost + ", ordered_time=" + ordered_time
				+ ", deleivery_time=" + deleivery_time + ", address=" + address + ", customer=" + customer + "]";
	}
	

}
