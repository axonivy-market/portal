package ch.ivy.addon.portalkit.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.ivy.addon.portalkit.bo.Contact;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Object store ICase data, implement the {@link Serializable} interface to for sorting, filter ...
 *
 */
public class CaseVO implements Serializable {

	/** default serialVersionUID */
	private static final long serialVersionUID = 4030426883979789328L; 
	
	/** id of case*/
	private long id;
	/** title case name*/
	private String title;
	/** case status */
	private String status;
	/** case creator */
	private String creator;
	/** case creatorContact in object, stored contact data such as email, phone ... */
	private Contact creatorContact;
	/** case createdAt date*/
	private Date createdAt;
	/** case description */
	private String description;
	/** case process details  */
	private String processCaseDetails;
	
	
	/**
	 * Gets the processCaseDetails
	 *
	 * @return Returns the processCaseDetails
	 */
	public String getProcessCaseDetails() {
		return processCaseDetails;
	}

	/**
	 * Sets the processCaseDetails
	 *
	 * @param processCaseDetails The processCaseDetails to set
	 */
	public void setProcessCaseDetails(String processCaseDetails) {
		this.processCaseDetails = processCaseDetails;
	}

	/**
	 * Get title of the case
	 * 
	 * @return String : Title of the case
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set title of the case
	 * 
	 * @param title : Title of the case
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Get Status of the case
	 * 
	 * @return String : Status of the case
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Set Status of the case
	 * 
	 * @param status : Status of the case
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Get Creator of the case
	 * 
	 * @return String : Creator of the case
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * Set Creator of the case
	 * 
	 * @param creator : Creator of the case
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	/**
	 * Get the created date of the case
	 * 
	 * @return Date : The created date of the case
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * Set the created date of the case
	 * 
	 * @param createdAt : The created date of the case
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * Get the description of the case
	 * 
	 * @return description : Description of the case
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description of the case
	 * 
	 * @param description : Description of the case
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * Get the contact of the creator
	 * 
	 * @return Contact : Contact of the creator
	 */
	public Contact getCreatorContact() {
		return creatorContact;
	}
	
	/**
	 * Set the contact of the creator
	 * 
	 * @param creatorContact {@link Contact}
	 */
	public void setCreatorContact(Contact creatorContact) {
		this.creatorContact = creatorContact;
	}

	/**
	 * Gets the id
	 *
	 * @return Returns the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id
	 *
	 * @param id The id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Converts Create At from date to String
	 * 
	 * @return value as String
	 */
  public String getCreatedAtAsString() {
    DateTimeGlobalSettingService dateTimeService = new DateTimeGlobalSettingService();
    SimpleDateFormat dateFormat =
        new SimpleDateFormat(dateTimeService.getDatePattern(), Ivy.session().getContentLocale());
    return dateFormat.format(this.createdAt);
  }
}
