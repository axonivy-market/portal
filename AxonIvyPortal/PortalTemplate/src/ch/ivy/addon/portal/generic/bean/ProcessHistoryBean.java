package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.datamodel.CaseHistoryLazyDataModel;

@ManagedBean
@ViewScoped
public class ProcessHistoryBean implements Serializable {

  private static final long serialVersionUID = 6256241637974800291L;
  private CaseHistoryLazyDataModel dataModel;
  private String businessEntityId;

  public void initData(String businessEntityId) {
    dataModel = new CaseHistoryLazyDataModel(businessEntityId);
  }
  
  public CaseHistoryLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(CaseHistoryLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public String getBusinessEntityId() {
    return businessEntityId;
  }

  public void setBusinessEntityId(String businessEntityId) {
    this.businessEntityId = businessEntityId;
  }

}
