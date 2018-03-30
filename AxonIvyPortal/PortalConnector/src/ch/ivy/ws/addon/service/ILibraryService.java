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
}
