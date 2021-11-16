package com.backend.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=2, message="Must be greater than 3 letters")
	private String fName;
	@NotBlank
	private String lName;
	@Email
	private String email;
	@NotNull
	private Integer age;
	@NotNull
	private Long medicalNumber;
	
	private String notes;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
		
//Relationships
	// Between Patient and User(Doctor)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User doctor;

	//Between Patient and Medication
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="meds",
			joinColumns = @JoinColumn(name="patient_id"),
			inverseJoinColumns = @JoinColumn(name="medication_id")
			)
	private List<Medicine> medsPrescibed;
	
	
	//Getters Setters
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	//Constructor
	public Patient() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Long getMedicalNumber() {
		return medicalNumber;
	}
	public void setMedicalNumber(Long medicalNumber) {
		this.medicalNumber = medicalNumber;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	public User getDoctor() {
		return doctor;
	}
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	
}
