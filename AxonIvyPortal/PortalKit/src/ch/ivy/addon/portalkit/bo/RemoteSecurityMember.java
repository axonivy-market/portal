package ch.ivy.addon.portalkit.bo;


/**
 * Bean for remote security member.
 */
public class RemoteSecurityMember {

	private String displayName;
	
	private String memberName;
	
	private boolean isUser;
	
	/**
	 * Constructor
	 *
	 */
	public RemoteSecurityMember(){
		super();
	}
	
	/**
	 * Constructor
	 *
	 * @param _memberName member name
	 */
	public RemoteSecurityMember(String _memberName){
		super();
		this.memberName = _memberName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public long getId() {
		return 0;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public boolean isUser() {
		if((memberName != null && memberName.startsWith("#")) || isUser){
			return true;
		}
		return false;
	}

	/**
	 * Sets the displayName
	 *
	 * @param displayName The displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Sets the memberName
	 *
	 * @param memberName The memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

  public void setUser(boolean isUser) {
    this.isUser = isUser;
  }
}
