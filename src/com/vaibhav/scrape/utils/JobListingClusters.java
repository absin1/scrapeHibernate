package com.vaibhav.scrape.utils;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobListingClusters entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "job_listing_clusters", schema = "public")

public class JobListingClusters implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private String clusterType;
	private String clusterName;
	private Boolean isPaginated;
	private Set<ClustersPagination> clustersPaginations = new HashSet<ClustersPagination>(0);

	// Constructors

	/** default constructor */
	public JobListingClusters() {
	}

	/** minimal constructor */
	public JobListingClusters(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JobListingClusters(Integer id, String url, String clusterType, String clusterName, Boolean isPaginated,
			Set<ClustersPagination> clustersPaginations) {
		this.id = id;
		this.url = url;
		this.clusterType = clusterType;
		this.clusterName = clusterName;
		this.isPaginated = isPaginated;
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

	@Column(name = "cluster_type")

	public String getClusterType() {
		return this.clusterType;
	}

	public void setClusterType(String clusterType) {
		this.clusterType = clusterType;
	}

	@Column(name = "cluster_name")

	public String getClusterName() {
		return this.clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@Column(name = "is_paginated")

	public Boolean getIsPaginated() {
		return this.isPaginated;
	}

	public void setIsPaginated(Boolean isPaginated) {
		this.isPaginated = isPaginated;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobListingClusters")

	public Set<ClustersPagination> getClustersPaginations() {
		return this.clustersPaginations;
	}

	public void setClustersPaginations(Set<ClustersPagination> clustersPaginations) {
		this.clustersPaginations = clustersPaginations;
	}

}