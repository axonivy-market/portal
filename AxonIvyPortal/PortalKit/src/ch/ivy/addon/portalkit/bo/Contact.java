package ch.ivy.addon.portalkit.bo;

/**
 * Model class for contact detail popover
 * 
 * @author ptanh
 * 
 */
public class Contact {
  private String email;
  private String phone;
  private String mobilePhone;
  
	/**
	 * Default Constructor
	 */
	public Contact() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param email contact email.
	 */
	public Contact(String email) {
		super();
		this.email = email;
	}

	/**
	 * Gets the email
	 *
	 * @return Returns the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email
	 *
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the phone number
	 *
	 * @return Returns the phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	
	/**
	 * set the phone number to contact
	 *
	 * @param phone String Phone number to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the mobile phone number
	 *
	 * @return Returns the mobile phone number
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	/**
	 * set the mobile number to contact
	 * 
	 * @param mobilePhone mobile phone 
	 *
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
