package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Objects;

public class Name implements Serializable {
	public String title;
	public String first;
	public String last;
	
	public Name() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Name name = (Name) o;
		return Objects.equals(title, name.title) && Objects.equals(first, name.first) && Objects.equals(last, name.last);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(title, first, last);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getFirst() {
		return first;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getLast() {
		return last;
	}
	
	public void setLast(String last) {
		this.last = last;
	}
}
