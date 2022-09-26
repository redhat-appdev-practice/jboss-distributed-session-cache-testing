package com.redhat.consulting.testdistributedcache.models;

import java.io.Serializable;
import java.util.Objects;

public class Login implements Serializable {
	public String uuid;
	public String username;
	public String password;
	public String salt;
	public String md5;
	public String sha1;
	public String sha256;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Login login = (Login) o;
		return Objects.equals(uuid, login.uuid) && Objects.equals(username, login.username) && Objects.equals(password, login.password) && Objects.equals(salt, login.salt) && Objects.equals(md5, login.md5) && Objects.equals(sha1, login.sha1) && Objects.equals(sha256, login.sha256);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(uuid, username, password, salt, md5, sha1, sha256);
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getMd5() {
		return md5;
	}
	
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	public String getSha1() {
		return sha1;
	}
	
	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}
	
	public String getSha256() {
		return sha256;
	}
	
	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}
}
