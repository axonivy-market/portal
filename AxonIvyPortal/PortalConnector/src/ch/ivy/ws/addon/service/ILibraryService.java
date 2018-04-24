package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.LibraryServiceResult;

public interface ILibraryService {

	/**
	 * Get project libraries except Portal projects
	 * @param apps
	 * @return LibraryServiceResult
	 * @throws WSException
	 */
	public LibraryServiceResult getLibraries(List<String> apps) throws WSException;
	
	/**
	 * Find project library of an application by id 
	 * @param libraryId
	 * @param appName
	 * @return LibraryServiceResult
	 * @throws WSException
	 */
	public LibraryServiceResult getLibrary(String libraryId, String appName) throws WSException;
}
