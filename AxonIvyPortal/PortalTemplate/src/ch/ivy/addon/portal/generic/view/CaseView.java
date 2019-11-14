package ch.ivy.addon.portal.generic.view;

import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;

public class CaseView {

  public static class Builder {

    private CaseLazyDataModel dataModel;
    private String keyword = "";
    private GlobalCaseId autoSelectCaseId = GlobalCaseId.createDefaultInstance();
    private String title = "";
    private boolean hideCaseFilter = false;

    public Builder dataModel(CaseLazyDataModel dataModel) {
      this.dataModel = dataModel;
      return this;
    }

    public Builder filterBy(String keyword) {
      this.keyword = keyword;
      return this;
    }

    public Builder autoSelectIfExists(GlobalCaseId globalCaseId) {
      this.autoSelectCaseId = globalCaseId;
      return this;
    }

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }
    
    public Builder hideCaseFilter(boolean isHideCaseFilter){
      this.hideCaseFilter = isHideCaseFilter;
      return this;
    }
    
    public CaseView buildNewView() {
      return new CaseView(this);
    }
  }

  private final CaseLazyDataModel dataModel;
  private final String keyword;
  private final GlobalCaseId autoSelectCaseId;
  private final String title;
  private String caseName = "";
  private boolean hideCaseFilter = false;

  public CaseView(Builder builder) {
    dataModel = builder.dataModel;
    keyword = builder.keyword;
    autoSelectCaseId = builder.autoSelectCaseId;
    title = builder.title;
    this.hideCaseFilter = builder.hideCaseFilter;
  }

  public static Builder create() {
    return new Builder();
  }

  public CaseLazyDataModel getDataModel() {
    return dataModel;
  }

  public String getKeyword() {
    return keyword;
  }

  public GlobalCaseId getAutoSelectCaseId() {
    return autoSelectCaseId;
  }

  public String getTitle() {
    return title;
  }

  public String getCaseName() {
    return caseName;
  }

  public boolean isHideCaseFilter() {
    return hideCaseFilter;
  }
}
