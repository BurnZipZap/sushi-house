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
@Table(name = "authorities")
@Getter 
@Setter
@NoArgsConstructor
public class Authorities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_users")
	private Users users;
	
	@Column
	private String authority;

	public Authorities(String authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authorities [id=" + id + ", authority=" + authority + "]";
	}
	
}
