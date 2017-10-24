package org.scrape.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vaibhav.scrape.utils.ClustersPagination;
import com.vaibhav.scrape.utils.JobListingClusters;
import com.vaibhav.scrape.utils.JobListings;
import com.vaibhav.scrape.utils.JobListingsDAO;

public class CreateClusterFolders {

	public static void main(String[] args) {
		try {
			(new CreateClusterFolders()).sortAndCleanListings();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sortAndCleanListings() throws IOException {
		ArrayList<JobListings> listings = (ArrayList<JobListings>) new JobListingsDAO().findAll();
		for (JobListings jobListings : listings) {
			if (jobListings.getIsDownloaded()) {
				if (jobListings.getId() > 632421) {
					Set<JobListingClusters> uniqueClusters = new HashSet<>();
					Set<ClustersPagination> clustersPaginations = jobListings.getClustersPaginations();
					for (ClustersPagination clustersPagination : clustersPaginations) {
						JobListingClusters jobListingClusters = clustersPagination.getJobListingClusters();
						uniqueClusters.add(jobListingClusters);
					}
					checkMakeClusterDir(uniqueClusters);
					String addListingText = addListingText(jobListings);
					if (addListingText.length() > 1) {
						try {
							makeClusterListingTextFile(addListingText, jobListings, uniqueClusters);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.err.println(addListingText);
					}
				} else {
					makeTestCases(jobListings);
				}
			}
		}
	}

	private void makeTestCases(JobListings jobListings) throws IOException {
		File folder = new File("/home/ab/Documents/Scraper/clusters/test/" + jobListings.getId());
		if (!folder.exists())
			folder.mkdirs();
		File file = new File(
				"/home/ab/Documents/Scraper/clusters/test/" + jobListings.getId() + "/" + jobListings.getId() + ".txt");
		String addListingText = addListingText(jobListings);
		createTextFile(addListingText, file);
	}

	private void makeClusterListingTextFile(String addListingText, JobListings jobListings,
			Set<JobListingClusters> uniqueClusters) throws IOException {
		for (JobListingClusters jobListingClusters : uniqueClusters) {
			File file = new File("/home/ab/Documents/Scraper/clusters/train/" + jobListingClusters.getClusterName()
					+ "/" + jobListings.getId() + ".txt");
			if (!file.exists()) {
				createTextFile(addListingText, file);
			}
		}
	}

	private void createTextFile(String addListingText, File file) throws IOException {
		file.createNewFile();
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(addListingText);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private String addListingText(JobListings jobListings) throws FileNotFoundException {
		String listingText = "";
		File file = new File("/home/ab/Documents/job_listings/listing" + jobListings.getId() + ".html");
		if (file.exists()) {
			String text = new Scanner(file).useDelimiter("\\Z").next();
			Document document = Jsoup.parse(text);
			Elements elementsByClass = document.getElementsByClass("listing");
			for (Element element : elementsByClass) {
				listingText += element.text();
			}
			listingText = clean(listingText);
		} else {
			System.err.println("No listing file exists for ID " + jobListings.getId());
		}
		return listingText;
	}

	private String clean(String listingText) {
		String newtext = listingText.replaceAll("Please refer to the Job description above", "");
		return newtext;
	}

	private void checkMakeClusterDir(Set<JobListingClusters> uniqueClusters) {
		for (JobListingClusters jobListingClusters : uniqueClusters) {
			File clusterFolder = new File(
					"/home/ab/Documents/Scraper/clusters/train/" + jobListingClusters.getClusterName());
			if (!clusterFolder.exists()) {
				clusterFolder.mkdirs();
				// System.out.println("Created Folder >> " +
				// clusterFolder.getAbsolutePath());
			}
		}

	}

}
