package ch.ivy.addon.portalkit.bean;

import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.task.responsible.Responsible;
import ch.ivyteam.ivy.workflow.task.responsible.Responsibles;

@ManagedBean
@ViewScoped
public class ResponsibleBean {
  
  public int getResponsiblesSize(Responsibles responsibles) {
    return CollectionUtils.isEmpty(responsibles.all()) ? 0 : responsibles.all().size();
  }
  
  public List<Responsible> getResponsibleLimit10(Responsibles responsibles) {
    List<Responsible> list = responsibles.all();
    return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list.subList(0, Math.min(10, list.size()));
  }
  
  public ISecurityMember getFirstResponsible(Responsibles responsibles) {
    return CollectionUtils.isEmpty(responsibles.all()) ? null : responsibles.all().getFirst().get();
  }
}
