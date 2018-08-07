package ch.ivy.addon.portalkit.service;

import java.util.Objects;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.ws.addon.IvyCase;

public class CaseInforActionService {

	public IvyCase prepareDataToSaveCase(RemoteCase remoteCase) {
		IvyCase ivyCase = new IvyCase();
		ivyCase.setId(remoteCase.getId());
		ivyCase.setServerId(remoteCase.getServer().getId());
		ivyCase.setDescription(emptyIfNull(remoteCase.getDescription()));
		ivyCase.setName(emptyIfNull(remoteCase.getName()));
		return ivyCase;
	}

	private static String emptyIfNull(String string) {
		return Objects.toString(string, "");
	}
}
