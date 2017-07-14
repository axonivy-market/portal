package ch.ivy.addon.portalkit.service;


import java.util.List;

import ch.ivy.addon.portalkit.bo.Process;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.environment.Ivy;

public class ProcessService {

  private static ProcessService service = new ProcessService();

  public static ProcessService getInstance() {
    return service;
  }

  public BusinessDataInfo<Process> save(Process processRepository) {
    return repo().save(processRepository);
  }

  public Process findById(String id) {
    return repo().find(id, Process.class);
  }

  public List<Process> findAll() {
    return repo().search(Process.class).orderBy().field("processName").ascending().execute().getAll();
  }

  public void delete(String id) {
    Process process = findById(id);
    repo().delete(process);
  }

  private BusinessDataRepository repo() {
    return Ivy.repo();
  }
}
