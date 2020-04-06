package com.sushi.sushihouse.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredient")
@Getter 
@Setter
@NoArgsConstructor
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int cost1kg;
	
	@Column
	private String supplier;
	
	@Column(name = "mass_now")
	private int massNow;
	
	@Column(name = "mass_all")
	private int massAll;
	
	@OneToMany(mappedBy = "ingredient")
	private List<DishIngredient> dishIngredient;

	public Ingredient(String name, int cost1kg, String supplier) {
		super();
		this.name = name;
		this.cost1kg = cost1kg;
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", cost1kg=" + cost1kg + ", supplier=" + supplier + "]";
	}
}
