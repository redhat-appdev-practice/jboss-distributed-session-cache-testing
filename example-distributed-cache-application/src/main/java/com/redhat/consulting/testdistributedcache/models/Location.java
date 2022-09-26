package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {
	public Street street;
	public String city;
	public String state;
	public String country;
	public String postcode;
	public Coordinates coordinates;
	public Timezone timezone;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Location location = (Location) o;
		return Objects.equals(street, location.street) && Objects.equals(city, location.city) && Objects.equals(state, location.state) && Objects.equals(country, location.country) && Objects.equals(postcode, location.postcode) && Objects.equals(coordinates, location.coordinates) && Objects.equals(timezone, location.timezone);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(street, city, state, country, postcode, coordinates, timezone);
	}
	
	public Street getStreet() {
		return street;
	}
	
	public void setStreet(Street street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public Timezone getTimezone() {
		return timezone;
	}
	
	public void setTimezone(Timezone timezone) {
		this.timezone = timezone;
	}
}
