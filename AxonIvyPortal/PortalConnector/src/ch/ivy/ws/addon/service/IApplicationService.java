package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.types.IvyApplication;

public interface IApplicationService{
  List<IvyApplication> getAllApplications() throws WSException;
  List<IvyApplication> getApplicationsBy(List<String> configApplicationsOfServer);
  List<Long> convertApplicationIdsToList(String applicationIds);
}
