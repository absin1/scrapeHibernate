package org.scrape.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vaibhav.scrape.utils.ClustersPagination;
import com.vaibhav.scrape.utils.JobListingClusters;
import com.vaibhav.scrape.utils.JobListingClustersDAO;
import com.vaibhav.scrape.utils.JobListings;
import com.vaibhav.scrape.utils.Skills;
import com.vaibhav.scrape.utils.SkillsDAO;

public class Test {

	public static void main(String[] args) {
		//extracted();
		List findAll = new JobListingClustersDAO().findAll();
		for (Object object : findAll) {
			System.err.println(((JobListingClusters) object).getUrl());
		}
	}

	private static void extracted() {
		Set<JobListingClusters> doneClusters = new HashSet<JobListingClusters>();
		for (JobListingClusters o : (List<JobListingClusters>) new JobListingClustersDAO().findAll()) {
			/*Set<ClustersPagination> clustersPaginations = o.getClustersPaginations();
			for (ClustersPagination object : clustersPaginations) {
				if (object.getIsScraped()) {
					for (JobListings jobListings : object.getJobListingses()) {
						
						if (jobListings.getIsDownloaded()) {
							
							 * System.out.println(jobListings.getId() +
							 * " belonging to " + o.getClusterName() +
							 * " has been downloaded");
							 
							doneClusters.add(o);
						}
					}
				}
			}*/

		}
		System.err.println("A total of " + doneClusters.size() + " clusters have been fully dlded");
		for (JobListingClusters doneCluster : doneClusters) {
			System.out.println(doneCluster.getId() + ">>" + doneCluster.getClusterName());
		}
	}

}
