package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	public String gender;
	public Name name;
	public Location location;
	public String email;
	public Login login;
	public Dob dob;
	public Registered registered;
	public String phone;
	public String cell;
	public Id id;
	public Picture picture;
	public String nat;
	
	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public int counter = 0;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User result = (User) o;
		return Objects.equals(gender, result.gender) && Objects.equals(name, result.name) && Objects.equals(location, result.location) && Objects.equals(email, result.email) && Objects.equals(login, result.login) && Objects.equals(dob, result.dob) && Objects.equals(registered, result.registered) && Objects.equals(phone, result.phone) && Objects.equals(cell, result.cell) && Objects.equals(id, result.id) && Objects.equals(picture, result.picture) && Objects.equals(nat, result.nat);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gender, name, location, email, login, dob, registered, phone, cell, id, picture, nat);
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Name getName() {
		return name;
	}
	
	public void setName(Name name) {
		this.name = name;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Login getLogin() {
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Dob getDob() {
		return dob;
	}
	
	public void setDob(Dob dob) {
		this.dob = dob;
	}
	
	public Registered getRegistered() {
		return registered;
	}
	
	public void setRegistered(Registered registered) {
		this.registered = registered;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getCell() {
		return cell;
	}
	
	public void setCell(String cell) {
		this.cell = cell;
	}
	
	public Id getId() {
		return id;
	}
	
	public void setId(Id id) {
		this.id = id;
	}
	
	public Picture getPicture() {
		return picture;
	}
	
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	public String getNat() {
		return nat;
	}
	
	public void setNat(String nat) {
		this.nat = nat;
	}
}
