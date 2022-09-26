package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Objects;

public class Street implements Serializable {
	public int number;
	public String name;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Street street = (Street) o;
		return number == street.number && Objects.equals(name, street.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(number, name);
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
