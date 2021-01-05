package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

@ManagedBean
@ViewScoped
public class UserProcessWidgetBean implements Serializable {

private static final long serialVersionUID = -5889375917550618261L;
  
  private List<UserProcess> processes;
  
  @PostConstruct
  public void init() {
    UserProcess p1 = new UserProcess();
    p1.setIcon("si si-pie-line-graph-desktop-2");
    p1.setProcessName("Alpha company name 1");
    
    UserProcess p2 = new UserProcess();
    p2.setIcon("si si-database-settings");
    p2.setProcessName("Alpha company name 2");
    
    UserProcess p3 = new UserProcess();
    p3.setIcon("si si-real-estate-action-house-key");
    p3.setProcessName("Alpha company name 3");
    
    processes = new ArrayList<>();
    processes.add(p1);
    processes.add(p2);
    processes.add(p3);
  }
  
  public List<UserProcess> getProcesses() {
    return processes;
  }
}
