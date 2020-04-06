package com.sushi.sushihouse.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "users")
@Getter 
@Setter
@NoArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private byte enabled;

	@OneToMany(mappedBy = "users")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Authorities> authorities;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "id_geolocation")
	private Geolocation geolocation;

	public Users( String username, String password, byte enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + "]";
	}
	
	public void addAuthorities(Authorities auth) {
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		authorities.add(auth);
		auth.setUsers(this);
	}
}
