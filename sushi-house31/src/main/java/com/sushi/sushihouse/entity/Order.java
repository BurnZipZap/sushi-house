package com.sushi.sushihouse.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ord")
@Getter 
@Setter
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column()
	private String status;
	
	@Basic
	@Column(name = "dt_order")
	private Timestamp dtOrder;
	
	@Basic
	@Column(name = "dt_delivered")
	private Timestamp dtDelivered;
	
	@Column()
	private int cost;

	@Column()
	private int discount;
	
	@Column()
	private String number;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDish> orderDishs;
	
	@ManyToOne()
	@JoinColumn(name = "id_geolocation")
	private Geolocation geolocation;
	
	@ManyToOne
	@JoinColumn(name = "id_users")
	private Users users;

	public Order( String status, Timestamp dtOrder, Timestamp dtDelivered, int cost,
			int discount, String number) {
		super();
		this.status = status;
		this.dtOrder = dtOrder;
		this.dtDelivered = dtDelivered;
		this.cost = cost;
		this.discount = discount;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ",  status=" + status + ", dtOrder=" + dtOrder
				+ ", dtDelivered=" + dtDelivered + ", cost=" + cost + ", discount=" + discount + ", number=" + number
				+ "]";
	}
	public void addOrderDish(OrderDish orderDish) {
		if(orderDishs == null) {
			orderDishs = new ArrayList<>();
		}
		orderDishs.add(orderDish);
		orderDish.setOrder(this);
	}
	public void addListOrderDish(List<OrderDish> orderDish) {
		for (OrderDish tempOrderDish : orderDish) {
			addOrderDish(tempOrderDish);
		}
	}
}
