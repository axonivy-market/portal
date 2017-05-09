package de.eon.components.utility;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.jndi.IJndiSecuritySystemConstants;

import de.eon.components.EmployeeType;
import de.eon.components.GenderType;
import de.eon.components.bo.LdapPerson;
import de.eon.components.bo.LdapPersonFilter;

import org.apache.commons.lang.StringUtils;

/**
 * Service class for the read query to the GDS with LDAP
 * @author A28030
 *
 */
public class LdapUtility {

	private static final String LDAP_UID = "uid";
	private static final String LDAP_SURNAME = "sn";
	private static final String LDAP_GIVEN_NAME = "givenName";
	private static final String LDAP_PHONE_NUMBER = "telephoneNumber";
	private static final String LDAP_MOBILE_NUMBER = "mobile";
	private static final String LDAP_BU_SHORT = "eonBUshort";
	private static final String LDAP_BU_LONG = "eonBusinessUnit";
	private static final String LDAP_BU_ID = "eonBusinessUnitID";
	private static final String LDAP_E_MAIL = "mail";
	private static final String LDAP_LANGUAGE = "eonPreferredLanguage";
	private static final String LDAP_DEPARTMENT_SHORT = "eonDepartmentNumberShort";
	private static final String LDAP_DEPARTMENT_LONG = "departmentNumber";
	private static final String LDAP_DEPARTMENT_ID = "eonDepartmentID";
	private static final String LDAP_ORGANIZATION_LONG = "eonOrganization";
	private static final String LDAP_ORGANIZATION_ID = "eonOrganizationID";
	private static final String LDAP_JOB_TITLE = "title";
	private static final String LDAP_OFFICE_ROOM = "roomNumber";
	private static final String LDAP_OFFICE_COUNTRY = "eonOfficeCountry";
	private static final String LDAP_OFFICE_STREET = "eonOfficeStreet";
	private static final String LDAP_STREET = "street";
	private static final String LDAP_OFFICE_CITY = "eonOfficeL";
	private static final String LDAP_OFFICE_POSTAL_CODE = "eonOfficePostalCode";
	private static final String LDAP_POSTAL_CODE = "postalCode";
	private static final String LDAP_COST_CENTER = "eonCostCenter";
	private static final String LDAP_GENDER = "eonGender";
	private static final String LDAP_EMPLOYEE_TYPE = "employeeType";
	private static final String LDAP_EON_POSITION = "eonPosition";
	private static final String LDAP_PERSON_TITLE = "personTitle";
	private static final String LDAP_EON_TITLE = "eonTitle";
	private static final String LDAP_EON_PRIMARY_PHONE = "eonPrimPhone";
	private static final String LDAP_EON_PRIMARY_MOBILE = "eonPrimMobile";
	private static final String LDAP_FUNCTIONAL_DEPARTMENT_ID = "eonFunctionalDepartmentID";
	
	/**
	 * finds all information to a given KID
	 * @param kid
	 * @return LdapPerson
	 */
	public static LdapPerson getPerson(String kid) {
		
		/*if(Ivy.wf().getApplication().getName().equalsIgnoreCase("designer")){
			if (kid.equals("T1234")) {
				return null;
			}
			return DesignerLdapPerson();
		}*/
		return DesignerLdapPerson();

		// Prepare Environment
		/*Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		
		 ISecurityContext s = Ivy.wf().getSecurityContext();
         String host = s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_SERVER_HOST_NAME_PROPERTY_NAME).getValue();
         String providerUrl;
         if(Ivy.var().get("de_eon_components_ldap_url_provider").toString().length()>0){
        	 providerUrl = Ivy.var().get("de_eon_components_ldap_url_provider").toString();
         } else {
        	 providerUrl = "ldaps://" + host + "/o=eon,c=de";
         }
         env.put(Context.PROVIDER_URL, providerUrl);
         env.put(Context.SECURITY_PRINCIPAL, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_USER_NAME_PROPERTY_NAME).getValue());
         env.put(Context.SECURITY_CREDENTIALS, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_PASSWORD_PROPERTY_NAME).getValue());

		DirContext ctx = null;
		NamingEnumeration<?> results = null;
		LdapPerson person = null;
		try {
			// Authentification against LDAP
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			// check KID Syntax
			Pattern patt = Pattern.compile(".*?([a-zA-Z]\\d{4,5})");
			Matcher match = patt.matcher(kid);
			if (match.matches()) {
				kid = match.group(1);
				results = ctx.search("", "(&(" + LDAP_UID + "=" + kid.toUpperCase()
						+ ")(objectclass=eonAccount))", controls);

				if (results.hasMore()) {
					SearchResult searchResult = (SearchResult) results.next();
					person = mapPerson(searchResult);
				}
			}else{
				// Pattern not found
				Ivy.log().warn("KID could not be resolved: " + kid);
			}
		} catch (NameNotFoundException e) {
			// The base context was not found.
			// Just clean up and exit.
			// AND WARN!
			Ivy.log().warn("Context not found for LdapUtility.getPerson(kid) with exception:"+e);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
		}
		return person;*/
	}
	
	private static LdapPerson DesignerLdapPerson() {
		LdapPerson p = new LdapPerson();
		p.setKid("D1111");
		p.setGivenName("Xpert.ivy");
		p.setSn("Designer");
		p.setEonBUshort("IVY");
		p.setEonBusinessUnit("Designer BU");
		p.setEonDepartmentNumberShort("I-VY");
		p.setEonPreferredLanguage("DE");
		p.setMail(Ivy.var().get("de_eon_components_ldap_designer_email"));
		p.setMobile("+49 000 12345");
		p.setTelephoneNumber("+49 00 12345-6789");
		p.setDepartmentNumber("IVY Development");
		p.setTitle("Developer");
		p.setEonOfficeStreet("Example road 1");
		p.setRoomNumber("Room 2");
		p.setEonOfficeL("Xperttown");
		p.setEonOfficePostalCode("12345");
		p.setEonOfficecountry("DE");
		p.setEonCostCenter("C123456789");
		p.setEonGender(GenderType.M);
		p.setEmployeeType(EmployeeType.I);
		p.setPersonTitle("Dr.");
		p.setEonTitle("Dr.");
		p.setEonPrimMobile("+49 175 2681469");
		p.setEonPrimPhone("+49 511 12178-928");
		return p;
	}

	public static List<String> getPersons(String dn) {

		// Prepare Environment
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");

		ISecurityContext s = Ivy.wf().getSecurityContext();
        String host = s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_SERVER_HOST_NAME_PROPERTY_NAME).getValue();
        String providerUrl = "ldaps://" + host;
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put(Context.SECURITY_PRINCIPAL, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_USER_NAME_PROPERTY_NAME).getValue());
        env.put(Context.SECURITY_CREDENTIALS, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_PASSWORD_PROPERTY_NAME).getValue());


		DirContext ctx = null;
		NamingEnumeration<?> results = null;
		List<String> kids = new LinkedList<String>();
		try {
			// Authentification against LDAP
			ctx = new InitialDirContext(env);
			
			// Set up attributes to search for
	        String[] searchAttributes = new String[1];
	        searchAttributes[0] = "uniqueMember";

	        Attributes attributes =
	        	ctx.getAttributes(dn, searchAttributes);
	        if (attributes != null) {
	            Attribute memberAtts = attributes.get("uniqueMember");
	            if (memberAtts != null) {
	                for (NamingEnumeration<?> vals = memberAtts.getAll(); vals.hasMoreElements(); ){
	                	String kid = (String)vals.nextElement();
	                	// check KID Syntax
	        			Pattern patt = Pattern.compile("cn=([a-zA-Z]\\d{4,5}),.*?");
	        			Matcher match = patt.matcher(kid);
	        			if (match.matches()) {
	        				kid = match.group(1);
	        				kids.add(kid);
	        			}else{
	        				// Pattern not found
	        				Ivy.log().warn("KID could not be resolved: " + kid);
	        			}
	                }
	            }
	        }
		} catch (NameNotFoundException e) {
			// The base context was not found.
			// Just clean up and exit.
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
		}
		return kids;
	}
	
	

	/**
	 * finds all information to a given user identified by given name and surname
	 * @param sn surname of the user
	 * @param givenName given name of the user
	 * @return List<LdapPerson>
	 */
	public static List<LdapPerson> getPerson(String sn, String givenName) {
		
		if(Ivy.wf().getApplication().getName().equalsIgnoreCase("designer")){
			List<LdapPerson> personen = new ArrayList<LdapPerson>();
			personen.add(DesignerLdapPerson());
			return personen;
		}
		// Prepare Environment
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		ISecurityContext s = Ivy.wf().getSecurityContext();
        String host = s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_SERVER_HOST_NAME_PROPERTY_NAME).getValue();
        String providerUrl;
        if(Ivy.var().get("de_eon_components_ldap_url_provider").toString().length()>0){
       	 providerUrl = Ivy.var().get("de_eon_components_ldap_url_provider").toString();
        } else {
       	 providerUrl = "ldaps://" + host + "/o=eon,c=de";
        }
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put(Context.SECURITY_PRINCIPAL, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_USER_NAME_PROPERTY_NAME).getValue());
        env.put(Context.SECURITY_CREDENTIALS, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_PASSWORD_PROPERTY_NAME).getValue());

		DirContext ctx = null;
		NamingEnumeration<?> results = null;
		List<LdapPerson> personen = new ArrayList<LdapPerson>();
		try {
			// Authentification against LDAP
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search("", "(&(" + LDAP_SURNAME + "=" + sn + ")(" + LDAP_GIVEN_NAME + "=" + givenName
					+ ")(objectclass=eonAccount)(eonUserType=primary))", controls);

			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				personen.add(mapPerson(searchResult));
			}
		} catch (NameNotFoundException e) {
			// The base context was not found.
			// Just clean up and exit.
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
		}
		return personen;
	}
	
	/**
	 * finds all information to a given user identified by given name and surname
	 * @param sn surname of the user
	 * @param givenName given name of the user
	 * @return List<LdapPerson>
	 */
	public static List<LdapPerson> getPersons(LdapPersonFilter filter) {
		
		if(Ivy.wf().getApplication().getName().equalsIgnoreCase("designer")){
			List<LdapPerson> personen = new ArrayList<LdapPerson>();
			personen.add(DesignerLdapPerson());
			return personen;
		}
		// Prepare Environment
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		ISecurityContext s = Ivy.wf().getSecurityContext();
        String host = s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_SERVER_HOST_NAME_PROPERTY_NAME).getValue();
        String providerUrl;
        if(Ivy.var().get("de_eon_components_ldap_url_provider").toString().length()>0){
       	 providerUrl = Ivy.var().get("de_eon_components_ldap_url_provider").toString();
        } else {
       	 providerUrl = "ldaps://" + host + "/o=eon,c=de";
        }
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put(Context.SECURITY_PRINCIPAL, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_USER_NAME_PROPERTY_NAME).getValue());
        env.put(Context.SECURITY_CREDENTIALS, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_PASSWORD_PROPERTY_NAME).getValue());

		DirContext ctx = null;
		NamingEnumeration<?> results = null;
		List<LdapPerson> personen = new ArrayList<LdapPerson>();
		try {
			// Authentification against LDAP
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String querry = "(&";
			if (filter.getSurname() != null && !filter.getSurname().isEmpty()) {
				querry = querry + "(" + LDAP_SURNAME + "=" + filter.getSurname() + ")";
			}
			if (filter.getFirstname() != null && !filter.getFirstname().isEmpty()) {
				querry = querry + "(" + LDAP_GIVEN_NAME + "=" + filter.getFirstname() + ")";
			}
			if (filter.getKid() != null && !filter.getKid().isEmpty()) {
				querry = querry + "(" + LDAP_UID + "=" + filter.getKid() + ")";
			}
			querry = querry + "(objectclass=eonAccount)(eonUserType=primary))";
			results = ctx.search("", querry, controls);

			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				personen.add(mapPerson(searchResult));
			}
		} catch (NameNotFoundException e) {
			// The base context was not found.
			// Just clean up and exit.
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
		}
		return personen;
	}

	/**
	 * reads all information from the LDAP query into the LDAPPerson object
	 * @param searchResult
	 * @return
	 */
	private static LdapPerson mapPerson(SearchResult searchResult){
		LdapPerson person = new LdapPerson();
		
		Attributes attributes = searchResult.getAttributes();
		person = new LdapPerson();
		Attribute attr = attributes.get(LDAP_PHONE_NUMBER);
		try {
			person.setTelephoneNumber((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_MOBILE_NUMBER);
		try {
			person.setMobile((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_BU_SHORT);
		try {
			person.setEonBUshort((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_E_MAIL);
		try {
			person.setMail((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_GIVEN_NAME);
		try {
			person.setGivenName((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_SURNAME);
		try {
			person.setSn((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_DEPARTMENT_SHORT);
		try {
			person.setEonDepartmentNumberShort((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_BU_LONG);
		try {
			person.setEonBusinessUnit((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_LANGUAGE);
		try {
			person.setEonPreferredLanguage((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_UID);
		try {
			person.setKid((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_DEPARTMENT_LONG);
		try {
			person.setDepartmentNumber((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_JOB_TITLE);
		try {
			person.setTitle((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_OFFICE_STREET);
		try {
			person.setEonOfficeStreet((String) attr.get());
		} catch (Exception e) {
			// try fall back attribute
			attr = attributes.get(LDAP_STREET);
			try {
				person.setEonOfficeStreet((String) attr.get());
			} catch (Exception e2) {
				// Skip Attribute
			}
		}
		
		attr = attributes.get(LDAP_OFFICE_CITY);
		try {
			person.setEonOfficeL((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_OFFICE_POSTAL_CODE);
		try {
			person.setEonOfficePostalCode((String) attr.get());
		} catch (Exception e) {
			// try fall back attribute
			attr = attributes.get(LDAP_POSTAL_CODE);
			try {
				person.setEonOfficePostalCode((String) attr.get());
			} catch (Exception e2) {
				// Skip Attribute
			}
		}
		
		attr = attributes.get(LDAP_OFFICE_COUNTRY);
		try {
			person.setEonOfficecountry((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_OFFICE_ROOM);
		try {
			person.setRoomNumber((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_BU_ID);
		try {
			person.setEonBusinessUnitID((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_ORGANIZATION_LONG);
		try {
			person.setEonOrganization((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_ORGANIZATION_ID);
		try {
			person.setEonOrganizationID((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_DEPARTMENT_ID);
		try {
			person.setEonDepartmentID((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_COST_CENTER);
		try {
			String value = (String) attr.get();
			if (value != null) {
				if (value.contains("$")) {
					// Structure should be HR$C123456789
					String[] values = StringUtils.split(value, "$");
					// return last segment
					value = values[values.length - 1];
				}
				person.setEonCostCenter(value);
			}
		} catch (Exception e) {
			// Skip Attribute
		}

		attr = attributes.get(LDAP_PERSON_TITLE);
		try {
			person.setPersonTitle((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_EON_TITLE);
		try {
			person.setEonTitle((String) attr.get());
		} catch (Exception e) {
			// Skip Attribute				
		}
		
		// Auto initialisation if value could not be read
		// ENUMs
		attr = attributes.get(LDAP_GENDER);
		try {
			String value = (String) attr.get();
			person.setEonGender(GenderType.valueOf(value.toUpperCase()));
		} catch (Exception e) {
			// init with unknown
			person.setEonGender(GenderType.UNKNOWN);
		}
		
		attr = attributes.get(LDAP_EMPLOYEE_TYPE);
		try {
			String value = (String) attr.get();
			person.setEmployeeType(EmployeeType.valueOf(value.toUpperCase()));
		} catch (Exception e) {
			// init with unknown
			person.setEmployeeType(EmployeeType.UNKNOWN);
		}
		
		attr = attributes.get(LDAP_EON_PRIMARY_MOBILE);
		try {
			person.setEonPrimMobile((String) attr.get());
		}catch (Exception e) {
			// Skip Attribute
		}
		
		attr = attributes.get(LDAP_EON_PRIMARY_PHONE);
		try {
			person.setEonPrimPhone((String) attr.get());
		}catch (Exception e) {
			// Skip Attribute
		}
		
		return person;
	}
	
	/**
	 * finds the line manager to a given KID
	 * @param kid
	 * @return LdapPerson
	 */
	public static LdapPerson getLineManagerByKID(String kid) {
		
		if(Ivy.wf().getApplication().getName().equalsIgnoreCase("designer")){
			return DesignerLdapPerson();
		}

		// Prepare Environment
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		 
		ISecurityContext s = Ivy.wf().getSecurityContext();
		String host = s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_SERVER_HOST_NAME_PROPERTY_NAME).getValue();
		String providerUrl;
        if(Ivy.var().get("de_eon_components_ldap_url_provider").toString().length()>0){
       	 providerUrl = Ivy.var().get("de_eon_components_ldap_url_provider").toString();
        } else {
       	 providerUrl = "ldaps://" + host + "/o=eon,c=de";
        }
		env.put(Context.PROVIDER_URL, providerUrl);
		env.put(Context.SECURITY_PRINCIPAL, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_USER_NAME_PROPERTY_NAME).getValue());
		env.put(Context.SECURITY_CREDENTIALS, s.getConfigurationProperty(IJndiSecuritySystemConstants.JNDI_PASSWORD_PROPERTY_NAME).getValue());
		
		DirContext ctx = null;
		NamingEnumeration<?> results = null;
		LdapPerson person = null;
		try {
			// Authentification against LDAP
			ctx = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			// check KID Syntax
			Pattern patt = Pattern.compile(".*?([a-zA-Z]\\d{4,5})");
			Matcher match = patt.matcher(kid);
			if (match.matches()) { //KID is okay
				String eonPosition = "leaderof@";
				kid = match.group(1);
				results = ctx.search("", "(&(" + LDAP_UID + "=" + kid.toUpperCase()
						+ ")(objectclass=eonAccount))", controls);

				Attribute af = null;
				if (results.hasMore()) { //User with KID found
					SearchResult searchResult = (SearchResult) results.next();
					Attributes aList = searchResult.getAttributes();
					af = aList.get(LDAP_FUNCTIONAL_DEPARTMENT_ID); //get always here because aList is available
//					if (null == af || af.toString().length()==0) {// old method with Position
						Attribute a = aList.get(LDAP_EON_POSITION);
						String staff = null;
						Pattern staffPatt = Pattern.compile("staff@(.*?)");
						String reportsTo = null;
						Pattern reportsToPatt = Pattern.compile("reportsto@(.*?)");
						String contractor = null;
						Pattern contractorPatt = Pattern.compile("contractor@(.*?)");
						Matcher m;
						if (null != a) {
							for (int index = 0; index < a.size(); index++) {
								String entry = (String) a.get(index);
								m = staffPatt.matcher(entry);
								if (m.matches()) {
									staff = m.group(1);
								} else {
									m = reportsToPatt.matcher(entry);
									if (m.matches()) {
										reportsTo = m.group(1);
									}
									else {
										m = contractorPatt.matcher(entry);
										if (m.matches()) {
											contractor = m.group(1);
										}
									}
								}
							}
						}
						// Check if user is a line manager himself
						if (null != contractor && !contractor.trim().isEmpty()) {
							// get line manager of superior department external
							eonPosition += contractor;
						} else if (null != reportsTo && !reportsTo.trim().isEmpty()) {
							// get line manager of superior department
							eonPosition += reportsTo;
						} else {
							// get line manager of current department
							eonPosition += staff;
						}
//					}
				}
				results.close();

//				if (null == af || af.toString().length()==0) {
					Ivy.log().info("Search for line manager with position:"+eonPosition);
					// get line manager from eonPosition
					results = ctx.search("", "(&(" + LDAP_EON_POSITION + "=" + eonPosition
							+ ")(objectclass=eonAccount)(eonUserType=primary))", controls);
					if (results.hasMore()) { //Line Manager found
						SearchResult searchResult = (SearchResult) results.next();
						Ivy.log().info("Line manager with position found:"+searchResult.getName());
						person = mapPerson(searchResult);
					}
					else {
						if (kid != null) {
							Ivy.log().info("Line manager with position not found for KID:"+kid.toUpperCase());
						}
						else {
							Ivy.log().info("Line manager with position not found for KID:"+kid);							
						}
					}
//				}
				if (af != null) { //new method with FunctionalDepartmentID
					String fid = af.toString().substring(27).trim();
					Ivy.log().info("Search for line manager with functionalDepartmentID:"+fid);
					results = ctx.search("", "(&(" + LDAP_FUNCTIONAL_DEPARTMENT_ID + "=" + fid
							+ ")(objectclass=eonAccount)(eonUserType=primary)(eonFunctionalPosition=leaderof*))", controls);
					int i = 0;
					while (results.hasMore()) { //Line Manager found
						SearchResult searchResult = (SearchResult) results.next();
						if (i==0) {
							Ivy.log().info("Line manager with functionalDepartmentID found ("+(i+1)+"):"+searchResult.getName());
							if (person == null) {//found already with position
								person = mapPerson(searchResult);
							}
							else {
								Ivy.log().warn("But Line manager with position is taken for KID:"+kid.toUpperCase());
								if (!person.getKid().equals(mapPerson(searchResult).getKid())) {
									LdapPerson fperson = mapPerson(searchResult);
									Ivy.log().error("Line manager differs! Found for functionalDepartmentID:"+fperson.getGivenName()+
											" "+fperson.getSn()+" ("+fperson.getKid()+")"+
											"/Found for position:"+person.getGivenName()+" "+person.getSn()+" ("+person.getKid()+")");
								}
							}
						}
						else {
							Ivy.log().warn("Line manager with functionalDepartmentID more than one found ("+(i+1)+"):"+searchResult.getName());
						}
						i++;
					}
					if (i==0) {
						if (person == null) {
							Ivy.log().warn("Line manager with functionalDepartmentID not found for KID:"+kid.toUpperCase());
						}
						else {
							Ivy.log().info("Line manager with functionalDepartmentID not found for KID:"+kid.toUpperCase());
						}
					}
				}
				else {
					Ivy.log().warn("Line manager with functionalDepartmentID (attribute 'eonFunctionalDepartmentID' not available) not found for KID:"+kid.toUpperCase());
				}
			}else{
				// Pattern not found
				Ivy.log().warn("KID is not correct for getting line manager:" + kid);
			}
		} catch (NameNotFoundException e) {
			// The base context was not found.
			// Just clean up and exit.
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
		}
		return person;
	}
	
	public static void main(String[] args) {
		getLineManagerByKID("U5100");
	}
	
	
	/**
	 * gets formatted name to given LdapPerson
	 * format example: Max Mustermann (M54683)
	 * @param LdapPerson person
	 * @return String formattedName
	 * @see LdapPerson
	 */
	public static String getPersonFormattedName(LdapPerson ldp) {
		String formattedName = "";
		
		if(!StringUtils.isEmpty(ldp.getKid())) {
			formattedName = ldp.getKid();
			
			List<String> names = new ArrayList<String>();
			
			if(!StringUtils.isEmpty(ldp.getGivenName())) {
				names.add(ldp.getGivenName());
			}
			
			if(!StringUtils.isEmpty(ldp.getSn())) {
				names.add(ldp.getSn());
			}
			
			if(!names.isEmpty()) {
				formattedName = StringUtils.join(names, " ") + " (" + formattedName + ")";
			}
		}
		
		return formattedName;
	}
}