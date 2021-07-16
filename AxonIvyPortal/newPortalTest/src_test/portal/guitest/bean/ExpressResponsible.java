package portal.guitest.bean;

public class ExpressResponsible {

  private String responsibleName;
  private boolean isGroup;

  public ExpressResponsible(String responsibleName, boolean isGroup) {
    super();
    this.responsibleName = responsibleName;
    this.isGroup = isGroup;
  }

  public String getResponsibleName() {
    return responsibleName;
  }

  public boolean isGroup() {
    return isGroup;
  }
}
