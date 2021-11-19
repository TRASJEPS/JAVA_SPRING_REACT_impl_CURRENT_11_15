package com.backend.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Please enter your first name!")
	// @Size(min=2, message="Please keep your first name more than two characters") try
	@Size(min=2, max=50, message="Please keep your first name between 2 and 50 characters!")
	private String firstName;

	@NotBlank(message="Please don't forget your last name!")
	@Size(min=2, max=50, message="Please keep your last name less than 50 characters!")
	private String lastName;
	
	@NotBlank(message="Please enter your email address!")
	@Email
	private String email;

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	//
	//@Regex()  // USE LATER TO SET SPECIAL CHARACTERS
	// password validation is confirmed on backend
	// setup min
	@Size(min=6, message="Please make your password longer than six characters!")
	private String password;
	
	// Make sure this is javax.persistence
	@Transient  // THIS MAKES SURE NOT TO GO INTO THE DATABASE
	@Size(min=6, message="Please make your password longer than six characters!")
	private String confirmPassword;
	
	// This comes into play in UserService.java
	//
	// Validation occurs in the UserValidator.java
	// for fancy password goodness.
	//
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	
	@Column(updatable=false)  // ENABLE WITH JAVA.UTIL.date
	@DateTimeFormat(pattern="yyyy,MM,DD HH:mm:ss") // you  can format this
	private Date createdAt;
	// THIS SETS THE CREATED AT TO EXIST AND auto assigns
	@PrePersist
	protected void onCreate()
	{
		this.createdAt = new Date();
	}
	
	// USER WILL NOT SEE THIS
	@DateTimeFormat(pattern="yyyy,MM,DD HH:mm:ss") // you  can format this
	private Date updatedAt;	
	// This only does this ONLY when the object is updated
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();  //MUST BE CALLED UPDATED AT
		
	}
	// Relationships 
	@OneToMany(mappedBy="doctor", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Patient> patients;
	
	
	// Getter and Setters Below
	
	public User() {
		// BEANNNNNN
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
