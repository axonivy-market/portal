package ch.ivy.addon.portalkit.bo;


/**
 * Bean for remote role.
 */
public class RemoteRole {

	private long id;
	private String displayName;
	private String memberName;
	
	
	/**
	 * Constructor
	 *
	 * @param id role id
	 * @param memberName name of role
	 * @param displayName display name of role
	 */
	public RemoteRole(long id, String memberName, String displayName){
		this.id = id;
		this.displayName = displayName;
		this.memberName = memberName;
	}
	
	
	public long getId() {
		return id;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return memberName;
	}

}
