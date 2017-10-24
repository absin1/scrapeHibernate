package com.vaibhav.scrape.utils;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Skills entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "skills", schema = "public")

public class Skills implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Set<JobListings> jobListingses = new HashSet<JobListings>(0);

	// Constructors

	/** default constructor */
	public Skills() {
	}

	/** minimal constructor */
	public Skills(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Skills(Integer id, String title, Set<JobListings> jobListingses) {
		this.id = id;
		this.title = title;
		this.jobListingses = jobListingses;
	}

	// Property accessors
	@Id

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title")

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "skills_listing", schema = "public", joinColumns = {
			@JoinColumn(name = "skill_id", updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "listing_id", updatable = false) })

	public Set<JobListings> getJobListingses() {
		return this.jobListingses;
	}

	public void setJobListingses(Set<JobListings> jobListingses) {
		this.jobListingses = jobListingses;
	}

}