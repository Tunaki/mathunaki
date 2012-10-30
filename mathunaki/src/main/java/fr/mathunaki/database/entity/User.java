package fr.mathunaki.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import fr.mathunaki.database.service.Status;
import fr.mathunaki.database.validation.PhoneNumber;

@Entity
@Table(name = "MathunakiUser")
public final class User implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "first_name", nullable = false, length = 20)
	@NotNull
	@Size(max = 20)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 20)
	@NotNull
	@Size(max = 20)
	private String lastName;

	@Column(nullable = false)
	@NotNull
	private String address;

	@Column
	@Email
	private String email;

	@Column(name = "phone_number")
	@PhoneNumber
	private String phoneNumber;

	@Column(name = "phone_number_parent")
	@PhoneNumber
	private String phoneNumberParent;

	@Column(name = "phone_number_2")
	@PhoneNumber
	private String phoneNumber2;

	@Column
	private String information;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@NotNull
	private Status status;

	@Column(nullable = false, precision = 4, scale = 2)
	@NotNull
	@Digits(integer = 4, fraction = 2)
	private double price;

	@Override
	public Long getId() {
		return id;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumberParent() {
		return phoneNumberParent;
	}

	public void setPhoneNumberParent(String phoneNumberParent) {
		this.phoneNumberParent = phoneNumberParent;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status state) {
		status = state;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
