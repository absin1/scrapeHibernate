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
 * JobListings entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "job_listings", schema = "public")

public class JobListings implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private Boolean isDownloaded;
	private Boolean isSkillParsed;
	private Set<Skills> skillses = new HashSet<Skills>(0);
	private Set<ClustersPagination> clustersPaginations = new HashSet<ClustersPagination>(0);

	// Constructors

	/** default constructor */
	public JobListings() {
	}

	/** minimal constructor */
	public JobListings(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JobListings(Integer id, String url, Boolean isDownloaded, Boolean isSkillParsed, Set<Skills> skillses,
			Set<ClustersPagination> clustersPaginations) {
		this.id = id;
		this.url = url;
		this.isDownloaded = isDownloaded;
		this.isSkillParsed = isSkillParsed;
		this.skillses = skillses;
		this.clustersPaginations = clustersPaginations;
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

	@Column(name = "url")

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "is_downloaded")

	public Boolean getIsDownloaded() {
		return this.isDownloaded;
	}

	public void setIsDownloaded(Boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}

	@Column(name = "is_skill_parsed")

	public Boolean getIsSkillParsed() {
		return this.isSkillParsed;
	}

	public void setIsSkillParsed(Boolean isSkillParsed) {
		this.isSkillParsed = isSkillParsed;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobListingses")

	public Set<Skills> getSkillses() {
		return this.skillses;
	}

	public void setSkillses(Set<Skills> skillses) {
		this.skillses = skillses;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "listing_cluster", schema = "public", joinColumns = {
			@JoinColumn(name = "listing_id", updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "pagination_id", updatable = false) })

	public Set<ClustersPagination> getClustersPaginations() {
		return this.clustersPaginations;
	}

	public void setClustersPaginations(Set<ClustersPagination> clustersPaginations) {
		this.clustersPaginations = clustersPaginations;
	}

}