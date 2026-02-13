package com.example.app.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String userName;
    @Column
    private String fatherName;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private String mobileNumber;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String gender;
    @Column
    private String photo;
    @Column
    private String city;
    @Column
    private String address;
    
    public Register() {
    }

	public Register(String userName, String fatherName, LocalDate dateOfBirth, String mobileNumber, String email,
			String password, String gender, String photo, String city, String address) {
		super();
		this.userName = userName;
		this.fatherName = fatherName;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.photo = photo;
		this.city = city;
		this.address = address;
	}

	public Register(int userId, String userName, String fatherName, LocalDate dateOfBirth, String mobileNumber,
			String email, String password, String gender, String photo, String city, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fatherName = fatherName;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.photo = photo;
		this.city = city;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, dateOfBirth, email, fatherName, gender, mobileNumber, password, photo,
				userId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
				&& Objects.equals(fatherName, other.fatherName) && Objects.equals(gender, other.gender)
				&& Objects.equals(mobileNumber, other.mobileNumber) && Objects.equals(password, other.password)
				&& Objects.equals(photo, other.photo) && userId == other.userId
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "Register [userId=" + userId + ", userName=" + userName + ", fatherName=" + fatherName + ", dateOfBirth="
				+ dateOfBirth + ", mobileNumber=" + mobileNumber + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", photo=" + photo + ", city=" + city + ", address=" + address + "]";
	}

	
	
    
}

