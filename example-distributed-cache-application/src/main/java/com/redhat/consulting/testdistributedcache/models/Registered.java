package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Registered implements Serializable {
	public Date date;
	public int age;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Registered that = (Registered) o;
		return age == that.age && Objects.equals(date, that.date);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, age);
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}
