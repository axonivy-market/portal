package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.comparator.RemoteUserComparator;
import ch.ivy.addon.portalkit.mapper.RemoteSecurityMemberMapper;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskWorkerFilter extends TaskFilter {

  @JsonIgnore
  private List<RemoteSecurityMember> workers;
  private RemoteSecurityMember selectedWorker;
  @JsonIgnore
  private static final String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/worker");
  }

  @Override
  public String value() {
    if (selectedWorker == null) {
      return ALL;
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedWorker));
  }

  @Override
  public TaskQuery buildQuery() {
    if (selectedWorker == null) {
      return null;
    }

    String memberName = selectedWorker.getMemberName();
    return TaskQuery.create().where().workerUserName().isEqual(memberName);
  }

  @Override
  public void resetValues() {
    selectedWorker = null;
  }

  public String formatName(RemoteSecurityMember worker) {
    if (StringUtils.isBlank(worker.getDisplayName())) {
      return worker.getMemberName();
    }
    return worker.getDisplayName() + " (" + worker.getMemberName() + ")";
  }

  public List<RemoteSecurityMember> getWorkers() {
    if (workers == null) {
      initWorkers();
    }
    return workers;
  }

  @SuppressWarnings("unchecked")
  private void initWorkers() {
    workers = new ArrayList<>();
    try {
      List<RemoteUser> users =
          ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteUser>>() {
            @Override
            public List<RemoteUser> call() throws Exception {
              if (Ivy.request().getApplication().getName().equals(IApplication.PORTAL_APPLICATION_NAME)) {
                return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllUsers").call()
                    .get("users", List.class);
              }
              return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllUsersByApplication")
                  .call(Ivy.request().getApplication().getName()).get("users", List.class);
            }
          });

      List<RemoteUser> distinctUsers =
          users.stream().collect(
              Collectors.collectingAndThen(
                  Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RemoteUser::getUsername))),
                  ArrayList::new));

      Collections.sort(distinctUsers, new RemoteUserComparator());

      workers.addAll(RemoteSecurityMemberMapper.mapFromRemoteUsers(distinctUsers));
    } catch (Exception e) {
      Ivy.log().error("Can't get list of users in worker filter", e);
    }
  }

  public void setWorkers(List<RemoteSecurityMember> workers) {
    this.workers = workers;
  }

  public RemoteSecurityMember getSelectedWorker() {
    return selectedWorker;
  }

  public void setSelectedWorker(RemoteSecurityMember selectedWorker) {
    this.selectedWorker = selectedWorker;
  }
}
