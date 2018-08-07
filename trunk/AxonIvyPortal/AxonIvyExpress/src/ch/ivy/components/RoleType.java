package ch.ivy.components;

/**
 * This enumeration contains role types property
 * 
 * inactive and admin roles are not shown in lists like delegate
 * @author K21649
 *
 */
public enum RoleType {
	NONE,
	ACTIV, 
	INACTIV,
	ADMIN;
	
	/*
	 * Get Enum from String
	 */
	public static RoleType getFromName(String name) {
		RoleType type;
		if (name.equals(ACTIV.name())) {
		  type = ACTIV;		
		}
		else if (name.equals(ADMIN.name())) {
			  type = ADMIN;		
			}
		else if (name.equals(INACTIV.name())) {
		  type = INACTIV;		
		}
		else {
		  type = NONE;					
		}
		return type;
		
	}

}
