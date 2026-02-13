package com.example.app.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	
	@Column
	private String email;
	
	 @OneToOne
	    @JoinColumn(name = "register_id")
	    private Register register;
	

	public User() {
		super();
	}

	public User(int id, String username, String password,String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email=email;
	}

	public User(String username, String password,String email) {
		super();
		this.username = username;
		this.password = password;
		this.email=email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	
	public void setEmail(String email) {
		this.email=email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, username);
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
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	public void setRegister(Register savedRegister) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	

}
