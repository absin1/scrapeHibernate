package com.vaibhav.scrape.utils;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ClustersPagination entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "clusters_pagination", schema = "public")

public class ClustersPagination implements java.io.Serializable {

	// Fields

	private Integer id;
	private JobListingClusters jobListingClusters;
	private String url;
	private Boolean isScraped;
	private Set<JobListings> jobListingses = new HashSet<JobListings>(0);

	// Constructors

	/** default constructor */
	public ClustersPagination() {
	}

	/** minimal constructor */
	public ClustersPagination(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ClustersPagination(Integer id, JobListingClusters jobListingClusters, String url, Boolean isScraped,
			Set<JobListings> jobListingses) {
		this.id = id;
		this.jobListingClusters = jobListingClusters;
		this.url = url;
		this.isScraped = isScraped;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cluster_id")

	public JobListingClusters getJobListingClusters() {
		return this.jobListingClusters;
	}

	public void setJobListingClusters(JobListingClusters jobListingClusters) {
		this.jobListingClusters = jobListingClusters;
	}

	@Column(name = "url")

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "is_scraped")

	public Boolean getIsScraped() {
		return this.isScraped;
	}

	public void setIsScraped(Boolean isScraped) {
		this.isScraped = isScraped;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clustersPaginations")

	public Set<JobListings> getJobListingses() {
		return this.jobListingses;
	}

	public void setJobListingses(Set<JobListings> jobListingses) {
		this.jobListingses = jobListingses;
	}

}