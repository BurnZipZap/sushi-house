package com.sushi.sushihouse.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ord_dish")
@Getter 
@Setter
@NoArgsConstructor
public class OrderDish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column
	private int count;

	@ManyToOne()
	@JoinColumn(name = "id_dish")
	private Dish dish;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "id_ord")
	private Order order;
	
	public OrderDish(int count) {
		super();
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrderDish [id=" + id + ", count=" + count + "]";
	}
}
