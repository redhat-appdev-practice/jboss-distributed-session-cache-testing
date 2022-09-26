package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Objects;

public class Id implements Serializable {
	public String name;
	public String value;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Id id = (Id) o;
		return Objects.equals(name, id.name) && Objects.equals(value, id.value);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, value);
	}
}
