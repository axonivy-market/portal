package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.IsAliveServiceResult;

public interface IIsAliveService {

	/**
	 * Checking, if applications from list are active
	 * @param apps
	 * @return IsAliveServiceResult
	 * @throws WSException
	 */
	public IsAliveServiceResult isActive(List<String> apps) throws WSException;
}
