package com.sushi.sushihouse.entity;

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
@Table(name = "dish_ingredient")
@Getter 
@Setter
@NoArgsConstructor
public class DishIngredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column
	private int mass;
	
	@ManyToOne()
	@JoinColumn(name = "id_dish")
	private Dish dish;
	
	@ManyToOne()
	@JoinColumn(name = "id_ingredient")
	private Ingredient ingredient;

	public DishIngredient(int mass) {
		super();
		this.mass = mass;
	}

	@Override
	public String toString() {
		return "DishIngredient [id=" + id + ", mass=" + mass + "]";
	}
}
