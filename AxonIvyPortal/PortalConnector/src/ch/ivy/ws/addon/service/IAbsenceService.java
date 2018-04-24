package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AbsenceServiceResult;
import ch.ivy.ws.addon.bo.SubstituteServiceResult;
import ch.ivy.ws.addon.types.IvyAbsence;
import ch.ivy.ws.addon.types.IvySubstitute;

/**
 * Service class for user absence & substitute
 * @author ptanh
 *
 */
public interface IAbsenceService {

	/**
	 * Get user absence settings for the passed user
	 * @param username
	 * @param apps 
	 * @return AbsenceServiceResult
	 * @throws WSException
	 */
	public AbsenceServiceResult getAbsences(String username, List<String> apps) throws WSException;
	
	
	/**
	 * Returns absences of all users of each application in given applications list
	 * 
	 * @param apps
	 * @return AbsenceServiceResult
	 * @throws WSException
	 */
	public AbsenceServiceResult getAbsences(List<String> apps) throws WSException;
	
	/**
	 * Save the absence settings for the passed user
	 * @param apps Parameter for special case , delete all absences of passed user in this application list 
	 * @param username
	 * @param absences Absences value to set
	 * @return AbsenceServiceResult
	 * @throws WSException
	 */
	public AbsenceServiceResult setAbsences(List<String> apps, String username, List<IvyAbsence> absences) throws WSException;
	
	/**
	 * Get user absence settings for the passed user
	 * @param username
	 * @param apps 
	 * @return SubstituteServiceResult
	 * @throws WSException
	 */
	public SubstituteServiceResult getSubstitutes(String username, List<String> apps) throws WSException;
	
	/**
	 * Save the substitutes for the passed user
	 * @param username
	 * @param substitutes substitute list to set
	 * @return SubstituteServiceResult
	 * @throws WSException
	 */
	public SubstituteServiceResult setSubstitutes(String username, List<IvySubstitute> substitutes) throws WSException;
	
	
}
