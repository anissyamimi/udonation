package domainLayer;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Donation {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm--dd hh:ss");
	
	public String donationName;
	public String donationDescription;
	public Date donationOpenDate;
	public int donationID =  Integer.parseInt(dateFormat.format(donationOpenDate).substring(0,12));
	public Date donationCloseDate;
	
	public Donation(int donationID, String donationName, String donationDescription, Date donationOpenDate,
			Date donationCloseDate) {
		super();
		this.donationID = donationID;
		this.donationName = donationName;
		this.donationDescription = donationDescription;
		this.donationOpenDate = donationOpenDate;
		this.donationCloseDate = donationCloseDate;
	}
	
	public Donation(String donationName, String donationDescription, Date donationOpenDate,
			Date donationCloseDate) {
		super();
		this.donationName = donationName;
		this.donationDescription = donationDescription;
		this.donationOpenDate = donationOpenDate;
		this.donationCloseDate = donationCloseDate;
	}
	
	public int getDonationID() {
		return donationID;
	}

	public void setDonationID(int donationID) {
		this.donationID = donationID;
	}

	public String getDonationName() {
		return donationName;
	}

	public void setDonationName(String donationName) {
		this.donationName = donationName;
	}

	public String getDonationDescription() {
		return donationDescription;
	}

	public void setDonationDescription(String donationDescription) {
		this.donationDescription = donationDescription;
	}

	public Date getDonationOpenDate() {
		return donationOpenDate;
	}

	public void setDonationOpenDate(Date donationOpenDate) {
		this.donationOpenDate = donationOpenDate;
	}

	public Date getDonationCloseDate() {
		return donationCloseDate;
	}

	public void setDonationCloseDate(Date donationCloseDate) {
		this.donationCloseDate = donationCloseDate;
	}
	
}

