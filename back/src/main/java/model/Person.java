package model;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import config.CustomerDateAndTimeDeserialize;


public class Person {
	private enum Gender {M, F};
	private enum Relation {child, parent, sibling, spouse};
	
	private String firstName;
	private String lastName;
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	private Date birthDate;
	private String city;
	private String profession;
	private String phone;
	private String email;
	private Gender gender;
	private Relation relation;
	private String relative;
	private String family;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		if(gender != null)
			return gender.name();
		else
			return null;
	}
	public void setGender(String gender) {
		this.gender = Gender.valueOf(gender);
	}
	public String getRelation() {
		if(gender != null)
			return relation.name();
		else
			return null;
	}
	public void setRelation(String relation) {
		this.relation = Relation.valueOf(relation);
	}
	public String getRelative() {
		return relative;
	}
	public void setRelative(String relative) {
		this.relative = relative;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	
	
	
}
