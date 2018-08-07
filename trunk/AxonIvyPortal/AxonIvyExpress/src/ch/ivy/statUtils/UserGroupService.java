package ch.ivy.statUtils;


/**
 * 
 * This class is for managing Roles, which are dynamically created/edited (Bewertungsgruppen)
 * <br>
 * <p>
 * Public static methods:
 * <ul>
 * <li>createBewertungsGruppe</li>
 * <li>setUsersForRole</li>
 * <li>deactivateBewertungsGruppe</li>
 * <li>updateBewertungsGruppe</li>
 * <li>findGruppeFilter</li>
 * </ul>
 * </p>
 *
 * @author K21649
 *
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivy.components.RoleProperty;
import ch.ivy.components.RoleType;
import ch.ivy.components.utility.IvySecurityUtility;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;


public class UserGroupService {

	
	final static String ROLE_PREFIX =  "GAWFS_"; //not used for AIFA
	final static String ROLE_ROOT =  "Everybody";

    /**
	 * create roleFor Department
	 * @param name 
	 * @return role
	 * @throws Exception
	 * @throws PersistencyException
	 */
    
    public static IRole createBewertungsGruppe(final String name) throws PersistencyException, Exception {
        IRole result;
        
		result = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<IRole>() {
					public IRole call() throws Exception {			        		    			
				    	IRole gruppenRole = Ivy.session().getSecurityContext().findRole(ROLE_ROOT);
            
				    	List<IRole> bewertungsGruppen = gruppenRole.getChildRoles();
				    	// find last ID
				    	
				    	IRole newRole = null;
				    	if (bewertungsGruppen != null) {
				    		String newRoleName = getNewRoleName(bewertungsGruppen);
				    		newRole = gruppenRole.createChildRole(newRoleName, name, "", true);
				    	}
				    	return newRole;
					}
				 });    	

		return result;
    }
    
    /*
     * find new free roleName for Bewertungsgruppe
     * @param List<IRole>
     * 
     */
    protected static String getNewRoleName(List<IRole> roles) {
    	String newRoleName = null;
    	Integer maxId = 0;
    	
    	// find the last used Id after role prefix
    	
    	for (IRole r : roles) {		
    		String[] s = r.getName().split(ROLE_PREFIX);
    		if (s.length > 1) {
    			Integer num = 0;    			    			
    			try {
    				num = Integer.parseInt(s[1]);
    			}
    			catch (Exception e) {}
  			    if (num > maxId) {
				  maxId = num;
			    }

    		}
    		
    	}
    	
    	maxId++;    // increment for new ID
    	
    	newRoleName = ROLE_PREFIX + maxId.toString(); 
    	
    	return newRoleName;    	

    }
    
	/**
	 * set users for specified role (it means also add/remove users from role)
	 * @param userList 
	 * @param rolename
	 * @return true if role exists
	 * @throws Exception
	 * @throws PersistencyException
	 */
	public static Boolean setUsersForRole(final List<IUser> userList, final String roleName) throws PersistencyException, Exception {
		Boolean result = false;

		result = Ivy.wf().getSecurityContext().executeAsSystemUser(
					new Callable<Boolean>() {
						public Boolean call() throws Exception {
							IRole r = Ivy.wf().getSecurityContext().findRole(roleName);
							
							if (r == null) {
								return false; 
							}
							else {
							   // remove role by user which are not in list
                               for (IUser u : r.getAllUsers()) {
                            	   if (userList != null && userList.contains(u)) {                            		
                            	     userList.remove(u);
                            	   }
                                   else {
                                	  u.removeRole(r);
                               	  
                                   }
                            	   
                               }

                               // add new users
                               if (userList != null) {
                                   for (IUser u : userList) {
                                	   u.addRole(r);
                                   }
                               }
							   return true;
							}
						}
					});

		return result;

	}

    /*
	 * get Roles for Bewertungsgruppen
	 * 	 * @return List<IRole>
	 * @throws Exception
	 * @throws PersistencyException
	 */
    
    public static List<IRole> getBewertungsGruppen() throws PersistencyException, Exception {
        List<IRole> result;
        
		result = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<List<IRole>>() {
					public List<IRole> call() throws Exception {			        		    			
				    	IRole gruppenRole = Ivy.session().getSecurityContext().findRole(ROLE_ROOT);
            
				    	List<IRole> bewertungsGruppen = new ArrayList<IRole>();
				    	List<IRole> roles = gruppenRole.getChildRoles();
				    					    	
				    	for (IRole r : roles) {
				    	   if (!IvySecurityUtility.hasRoleType(r, RoleType.INACTIV) && !r.getName().equals("OTHER")) {
				    		  bewertungsGruppen.add(r);
				    	   }
				    	}
				    	java.util.Collections.sort(bewertungsGruppen,new SortNameRole());
				    	IRole other = Ivy.session().getSecurityContext().findRole("OTHER"); //add role "OTHER" at last position
				    	if (other != null) {
				    		  bewertungsGruppen.add(other);				    		
				    	}
				    	return bewertungsGruppen;
					}
				 });

		return result;

    }

    /*
	 * get Roles for Bewertungsgruppen
	 * 	 * @return List<IRole>
	 * @throws Exception
	 * @throws PersistencyException
	 */
    
    public static List<IRole> getBewertungsGruppenAdmin() throws PersistencyException, Exception {
        List<IRole> result;
        
		result = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<List<IRole>>() {
					public List<IRole> call() throws Exception {			        		    			
				    	IRole gruppenRole = Ivy.session().getSecurityContext().findRole(ROLE_ROOT);
            
				    	List<IRole> bewertungsGruppen = new ArrayList<IRole>();
				    	List<IRole> roles = gruppenRole.getChildRoles();
				    					    	
				    	for (IRole r : roles) {
				    	   if (!IvySecurityUtility.hasRoleType(r, RoleType.INACTIV) && !r.getName().equals("OTHER")) {
				    		  bewertungsGruppen.add(r);
				    	   }
				    	}
				    	java.util.Collections.sort(bewertungsGruppen,new SortNameRole());				    	
				    	return bewertungsGruppen;
					}
				 });    	

		return result;

    }

    /*
	 * get Roles for Bewertungsgruppen with Name
	 * @param String name
	 * @return List<IRole>
	 * @throws Exception
	 * @throws PersistencyException
	 */
    
    public static List<IRole> findGruppeFilter(final String name, final Boolean exactName) throws PersistencyException, Exception {
        List<IRole> result;
        
		result = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<List<IRole>>() {
					public List<IRole> call() throws Exception {
				    	IRole gruppenRole = Ivy.session().getSecurityContext().findRole(ROLE_ROOT);
            
				    	List<IRole> bewertungsGruppen = new ArrayList<IRole>();
				    	
				    	List<IRole> roles = gruppenRole.getChildRoles();
				    	
				    	String nameUpperCase = name.toUpperCase();
				    	
				    	for (IRole r : roles) {

				    	   if (!IvySecurityUtility.hasRoleType(r, RoleType.INACTIV)) {
				    	      if (exactName) {
				    	          if (r.getDisplayName().equals(name)) {
				    	        	 bewertungsGruppen.add(r);
								  }
							  }
							  else {
				    	          if (r.getDisplayName().toUpperCase().indexOf(nameUpperCase) >= 0) {
					    	        	 bewertungsGruppen.add(r);
				    	          }								  
							  }
				    	   }
				    	}
				    	// find last ID
				    	return bewertungsGruppen;
					}
				 });    	

		return result;


    }

    /*
	 * get update Bewertungsgruppen with Name
	 * @param String name
	 * @param IRole name
	 * @return IRole
	 * @throws Exception
	 * @throws PersistencyException
	 */
    
    public static void updateBewertungsGruppe(IRole r, String name) {
       if (r != null && name != null && name.length() > 0) {
    	   r.setDisplayNameTemplate(name);
       }
    }

	/*
	 * Delete Role
	 * @param String - roleName
	 */
	public static Boolean deleteBewertungsGruppe(final String roleName) throws Exception {
//		return Ivy.session().getSecurityContext().executeAsSystemUser(
		return ServerFactory.getServer().getSecurityManager().executeAsSystem( //this is faster because no additional security issue
				new Callable<Boolean>() {
					public Boolean call() throws Exception {
						IRole r = Ivy.wf().getSecurityContext().findRole(roleName);
						if (r != null) {
					        r.delete();
						}
						return true;
					}
				});
	}

	/*
	 * Get Displayname from role
	 * @param String roleName
	 */
    public static String getDisplayNameFromRole(final String roleName) throws PersistencyException, Exception {
        String result;
        
		result = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<String>() {
					public String call() throws Exception {			        		    			
				    	IRole rol = Ivy.session().getSecurityContext().findRole(roleName);
				    	String newResult = "";
				    	if (rol != null) {
				    		newResult = rol.getDisplayName();
				    	}
				    	return newResult;
					}
				 });    	

		return result;
    }
	
	/*
	 * Deactivate Role for Bewertungsgruppe
	 * @param String roleName
	 */
    public static void deactivateBewertungsGruppe(String roleName) {
	  try {
	    IvySecurityUtility.setRoleType(roleName, RoleType.INACTIV);
	  }
	  catch (Exception e){
		  Ivy.log().debug(e);
	  }
    }
	
	/*
	 * Get users from role sorted
	 * @param String roleName
	 */
    public static List<IUser> getUsersFromRoleSorted(final String roleName) throws Exception {
    	List<IUser> users;
		users = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<List<IUser>>() {
					public List<IUser> call() throws Exception {			        		    			

    	List<IUser> users = new ArrayList<IUser>();
    	IRole rol = Ivy.wf().getSecurityContext().findRole(roleName);
    	if (rol != null) {
    		users = rol.getAllUsers();
	    	java.util.Collections.sort(users,new SortName());
    	}
    	return users;
		}});
		return users;
    }
    
	/*
	 * Get users from role sorted
	 * @param String roleName
	 */
    public static List<IUser> getUsersFromGroupSorted(final String name) throws Exception {
    	List<IUser> users;
		users = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<List<IUser>>() {
					public List<IUser> call() throws Exception {			        		    			

    	List<IUser> users = new ArrayList<IUser>();
    	List<IRole> rols = findGruppeFilter(name, true);
    	if (rols.size()>0) {
	    	IRole rol = rols.get(0);
	    	if (rol != null) {
	    		users = rol.getAllUsers();
		    	java.util.Collections.sort(users,new SortName());
	    	}
    	}
    	return users;
		}});
		return users;
    }
    
	/*
	 * Get users sorted
	 * 
	 */
    public static List<IUser> getUsersSorted() throws Exception {
    	List<IUser> users;
		users = Ivy.wf().getSecurityContext().executeAsSystemUser(
				new Callable<List<IUser>>() {
					public List<IUser> call() throws Exception {			        		    			
    	List<IUser> users = new ArrayList<IUser>();
    	for (IUser ur : Ivy.wf().getSecurityContext().getUsers()) {
    		if (!ur.getName().equalsIgnoreCase("SYSTEM")) {
    			users.add(ur);
    		}
    	}
    	java.util.Collections.sort(users,new SortName());
    	return users;
		}});
		return users;
    }

    /*
     * Comparator for sorting IUsers by name
     */
    public static class SortName implements Comparator<IUser>{
    	@Override
    	public int compare(IUser a1, IUser a2) {
    		return a1.getDisplayName().compareToIgnoreCase(a2.getDisplayName()); //Namen
    	}
    }
    
    /*
     * Comparator for sorting IRoles by name
     */
    public static class SortNameRole implements Comparator<IRole>{
    	@Override
    	public int compare(IRole a1, IRole a2) {
    		//return a1.getName().compareToIgnoreCase(a2.getName());
    		return a1.getDisplayName().compareToIgnoreCase(a2.getDisplayName());
    	}
    }
    
}