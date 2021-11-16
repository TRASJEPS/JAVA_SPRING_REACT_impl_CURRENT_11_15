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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="medications")
public class Medicine {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String medName;
	
	@NotBlank
	private String unitOfMeasure;
	@NotNull
	private float price;
	@NotNull
	private Long dosage;
	private boolean prescription;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	//Between Medicine and Patient
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="meds",
			joinColumns = @JoinColumn(name="medication_id"),
			inverseJoinColumns = @JoinColumn(name="patient_id")
			)
	private List<Patient> patientsOn;
	
	//Between Medicine and Images
	@OneToMany(mappedBy="medicine", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Image> image;
	
	
	//Getters setters
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Long getDosage() {
		return dosage;
	}
	public void setDosage(Long dosage) {
		this.dosage = dosage;
	}
	
	//CHECK if this should be getPerscription because its a T/F
	public boolean isPrescription() {
		return prescription;
	}
	// use set or IS!>!>!>
	public void setPrescription(boolean prescription) {
		this.prescription = prescription;
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
	
	// FOREIGN KEY!!!  for the => MEDS
	public List<Patient> getPatientsOn() {
		return patientsOn;
	}
	public void setPatientsOn(List<Patient> patientsOn) {
		this.patientsOn = patientsOn;
	}
	public List<Image> getImage() {
		return image;
	}
	public void setImage(List<Image> image) {
		this.image = image;
	}
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	
}
