package org.scrape.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtractJD {
	public static void main(String[] args) {
		try {
			(new ExtractJD()).createListingJDs();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String listingHTMLPath = "/home/ab/Documents/job_listings/";
	private static String listingTextPath = "/home/ab/Documents/job_listings_text/";

	private void createListingJDs() throws IOException {
		File listingFolder = new File(listingHTMLPath);
		for (File listingHTMLFile : listingFolder.listFiles()) {
			String JD = "";
			Document document = Jsoup.parse(listingHTMLFile, "UTF-8");
			Elements listings = document.getElementsByClass("listing");
			for (Element listing : listings) {
				JD += listing.text();
			}

			// Now creatng text JDs
			if (JD.length() > 1) {
				File listingTextFile = new File(listingTextPath + listingHTMLFile.getName() + ".txt");
				listingTextFile.createNewFile();
				System.out.println("Creating " + listingTextFile.getName());
				FileWriter fileWriter = new FileWriter(listingTextFile);
				fileWriter.write(JD);
				if (fileWriter != null)
					fileWriter.close();
			}
		}

	}
}
