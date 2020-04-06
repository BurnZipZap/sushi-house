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
@Table(name = "dish")
@Getter 
@Setter
@NoArgsConstructor
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int cost;
	
	@Column(name = "сalorie_content")
	private int calorieContent;
	
	@Column
	private String category;
	
	@Column
	private int like;
	
	@Column
	private int dislike;
	
	@Column(name = "image_href")
	private String imageHref;
	
	@OneToMany(mappedBy = "dish")
	private List<DishIngredient> dishIngredient;
	
	@OneToMany(mappedBy = "dish")
	private List<OrderDish> orderDishs;

	public Dish(String name, int cost, int calorieContent, String category, int like, int dislike,
			String imageHref) {
		super();
		this.name = name;
		this.cost = cost;
		this.calorieContent = calorieContent;
		this.category = category;
		this.like = like;
		this.dislike = dislike;
		this.imageHref = imageHref;
	}

	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", cost=" + cost + ", calorieContent=" + calorieContent
				+ ", category=" + category + ", like=" + like + ", dislike=" + dislike + ", imageHref=" + imageHref
				+ "]";
	}
}
