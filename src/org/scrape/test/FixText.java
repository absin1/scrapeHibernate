package org.scrape.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vaibhav.scrape.utils.ClustersPagination;
import com.vaibhav.scrape.utils.JobListings;
import com.vaibhav.scrape.utils.JobListingsDAO;
import com.vaibhav.scrape.utils.Skills;

public class FixText {

	public static void main(String[] args) throws FileNotFoundException {
		HashMap<String, ArrayList<JobListings>> ttt = new HashMap();

		List<JobListings> joblistings = new JobListingsDAO().findAll();
		for (JobListings joblisting : joblistings) {
			if (joblisting.getIsDownloaded()) {
				int listing_id = joblisting.getId();
				try {
					for (ClustersPagination item : joblisting.getClustersPaginations()) {
						System.err.println(item.getJobListingClusters().getClusterName());
						if (ttt.containsKey(item.getJobListingClusters().getClusterName())) {
							ArrayList<JobListings> value = ttt.get(item.getJobListingClusters().getClusterName());
							value.add(joblisting);
							ttt.put(item.getJobListingClusters().getClusterName(), value);
						} else {
							ArrayList<JobListings> value = new ArrayList<JobListings>();
							value.add(joblisting);
							ttt.put(item.getJobListingClusters().getClusterName(), value);
						}

					}

					for (String s : ttt.keySet()) {
						File file = new File("C:\\Users\\Istar\\Documents\\categories\\" + s);
						if (!file.exists()) {
							file.mkdirs();
						}
						for (JobListings jobListings2 : ttt.get(s)) {
							File listingFile = new File(
									"C:\\Users\\Istar\\Documents\\allfiles\\listing" + listing_id + ".html");
							String JD = "";
							JD = (new FixText()).getJD(listingFile);
							File listingFile1 = new File("C:\\Users\\Istar\\Documents\\categories\\" + s + "\\"
									+ jobListings2.getId() + ".txt");
							if (JD.equalsIgnoreCase("")) {
								System.err.println("JD for listing " + listing_id + " not found");
							} else {
								(new FixText()).createListingTextFile(JD, listingFile1);
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}

	}

	private  void createListingTextFile(String jD, File listingFile1) {

		jD = jD.replaceAll("Job Description � Send me Jobs like this", "");
		System.out.println("------------------");
		try {
			FileUtils.writeStringToFile(listingFile1, jD);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private  String getJD(File file) throws FileNotFoundException {
		String JD = "";
		String subSequence = "";
		if (!file.isDirectory()) {
			JD = new Scanner(file).useDelimiter("\\Z").next();
			Document document = Jsoup.parse(JD);
			Elements jdDivs = document.getElementsByClass("listing");
			if (jdDivs.size() > 0)
				for (Element skillDiv : jdDivs) {
					subSequence += skillDiv.text();
				}
			if (subSequence.contains(","))
				subSequence = subSequence.substring(0, subSequence.length() - 1);
		}
		return subSequence;
	}

}
