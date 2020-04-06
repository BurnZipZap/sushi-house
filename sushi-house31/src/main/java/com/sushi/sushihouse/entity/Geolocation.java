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
@Table(name = "geolocation")
@Getter 
@Setter
@NoArgsConstructor
public class Geolocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column
	private String city;
	
	@Column
	private String street;
	
	@Column
	private int house;
	
	@Column
	private int porch;
	
	@OneToMany(mappedBy = "geolocation")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "geolocation")
	private List<Users> users;

	public Geolocation(String city, String street, int house, int porch) {
		super();
		this.city = city;
		this.street = street;
		this.house = house;
		this.porch = porch;
	}

	@Override
	public String toString() {
		return "Geolocation [id=" + id + ", city=" + city + ", street=" + street + ", house=" + house + ", porch="
				+ porch + "]";
	}
}
