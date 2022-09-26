package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Objects;

public class Picture implements Serializable {
	public String large;
	public String medium;
	public String thumbnail;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Picture picture = (Picture) o;
		return Objects.equals(large, picture.large) && Objects.equals(medium, picture.medium) && Objects.equals(thumbnail, picture.thumbnail);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(large, medium, thumbnail);
	}
	
	public String getLarge() {
		return large;
	}
	
	public void setLarge(String large) {
		this.large = large;
	}
	
	public String getMedium() {
		return medium;
	}
	
	public void setMedium(String medium) {
		this.medium = medium;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
