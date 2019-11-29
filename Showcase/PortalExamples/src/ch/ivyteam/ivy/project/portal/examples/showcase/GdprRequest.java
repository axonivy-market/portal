package ch.ivyteam.ivy.project.portal.examples.showcase;

import java.util.Date;

import ch.ivyteam.ivy.project.portal.examples.showcase.enums.Completeness;
import ch.ivyteam.ivy.project.portal.examples.showcase.enums.ProcessState;

public class GdprRequest {
	private ProcessState processState;
	private String id;
	private Long caseId;
	private String applicationUserName;
	private Date creationDate;
	private java.util.List<GdprFile> files;
	private String reviewedBy;
	private Date dateOfReview;	
	private String surname;
	private String name;
	private String address;
	private Integer zip;
	private String city;
	private Integer phoneNumber;
	private String emailAddress;
	private String requestReason;
	private String identificationNumer;
	private Completeness completeness;
	
	public ProcessState getProcessState() {
		return processState;
	}

	public void setProcessState(ProcessState processState) {
		this.processState = processState;
	}
	
	public Completeness getCompleteness() {
		return completeness;
	}
	public void setCompleteness(Completeness completeness) {
		this.completeness = completeness;
	}
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}
	
	public String getRequestReason() {
		return requestReason;
	}

	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getIdentificationNumer() {
		return identificationNumer;
	}

	public void setIdentificationNumer(String identificationNumer) {
		this.identificationNumer = identificationNumer;
	}


	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public Date getDateOfReview() {
		return dateOfReview;
	}

	public void setDateOfReview(Date dateOfReview) {
		this.dateOfReview = dateOfReview;
	}

	public java.util.List<GdprFile> getFiles() {
		return files;
	}

	public void setFiles(java.util.List<GdprFile> files) {
		this.files = files;
	}

	public String getApplicationUserName() {
		return applicationUserName;
	}

	public void setApplicationUserName(String applicationUserName) {
		this.applicationUserName = applicationUserName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
