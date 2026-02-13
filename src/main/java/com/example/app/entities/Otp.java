package com.example.app.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Otp {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int otpid;
	@Column
	int otpvalue;
	@Column
	LocalDateTime createdat;
	@Column
	LocalDateTime expiresAt;


	
	@ManyToOne
    @JoinColumn(name = "uid")
    private User users;

	

	public Otp() {
		super();
	}



	 public Otp(int otpvalue, LocalDateTime createdat, User users) {
	        this.otpvalue = otpvalue;
	        this.createdat = createdat;
	        this.users = users;
	        this.expiresAt = createdat.plusMinutes(5);
	    }



	public Otp(int otpid, int otpvalue, LocalDateTime createdat, LocalDateTime expiresAt, User users) {
		super();
		this.otpid = otpid;
		this.otpvalue = otpvalue;
		this.createdat = createdat;
		this.expiresAt = expiresAt;
		this.users = users;
	}



	public int getOtpid() {
		return otpid;
	}



	public void setOtpid(int otpid) {
		this.otpid = otpid;
	}



	public int getOtpvalue() {
		return otpvalue;
	}



	public void setOtpvalue(int otpvalue) {
		this.otpvalue = otpvalue;
	}



	public LocalDateTime getCreatedat() {
		return createdat;
	}



	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}



	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}



	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}



	public User getUsers() {
		return users;
	}



	public void setUsers(User users) {
		this.users = users;
	}



	@Override
	public String toString() {
		return "Otp [otpid=" + otpid + ", otpvalue=" + otpvalue + ", createdat=" + createdat + ", expiresAt="
				+ expiresAt + ", users=" + users + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(createdat, expiresAt, otpid, otpvalue, users);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Otp other = (Otp) obj;
		return Objects.equals(createdat, other.createdat) && Objects.equals(expiresAt, other.expiresAt)
				&& otpid == other.otpid && otpvalue == other.otpvalue && Objects.equals(users, other.users);
	}



	


	

	
}
