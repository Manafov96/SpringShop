package com.SpringShop.entity.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@NotEmpty
	@Length(max = 100)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotEmpty
	@Email
  	@Pattern(regexp = "^(.+)@(.+)$")
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotEmpty
	@Length(min = 8)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	/*
		^             	  # start-of-string
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-z])       # a lower case letter must occur at least once
		(?=.*[A-Z])       # an upper case letter must occur at least once
		(?=.*[@#$%^&+=])  # a special character must occur at least once
		(?=\S+$)          # no whitespace allowed in the entire string
		.{8,}             # anything, at least eight places though
		$                 # end-of-string
	* */
	@Column(name = "password", nullable = false)
	private String password;
		
	@Transient
	private String confirmPassword;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Order> orders = new HashSet<>();
	
	@JoinTable(
		name = "user_role", 
		joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, 
		inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Role> roles = new HashSet<>();
	
	
	public User() {
		
	}
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<Order> getOrders() {
		return orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
		order.setUser(this);
	}
	
	public void removeOrder(Order order) {
		orders.remove(order);
		order.setUser(null);
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		roles.add(role);
		role.getUsers().add(this);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

}