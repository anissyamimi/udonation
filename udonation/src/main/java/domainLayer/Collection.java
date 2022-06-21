package domainLayer;

import java.sql.Date;   
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class Collection {
	
	private LocalTime t = LocalTime.now();
	private LocalDate d = LocalDate.now();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm--dd hh:ss");
	
	public Date collectionDate = Date.valueOf(d);
	public int collectionID = Integer.parseInt(dateFormat.format(collectionDate).substring(0,12));
	public Time collectionTime = Time.valueOf(t);
	public String collectionDetails;
	public String applicantID;
	public String committeeID;
	
	public Collection(int collectionID, Date collectionDate, Time collectionTime, String collectionDetails,
			String applicantID, String committeeID) {
		super();
		this.collectionID = collectionID;
		this.collectionDate = collectionDate;
		this.collectionTime = collectionTime;
		this.collectionDetails = collectionDetails;
		this.applicantID = applicantID;
		this.committeeID = committeeID;
	}

	public int getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(int collectionID) {
		this.collectionID = collectionID;
	}

	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public Time getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Time collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getCollectionDetails() {
		return collectionDetails;
	}

	public void setCollectionDetails(String collectionDetails) {
		this.collectionDetails = collectionDetails;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public String getCommitteeID() {
		return committeeID;
	}

	public void setCommitteeID(String committeeID) {
		this.committeeID = committeeID;
	}
	
	
}
