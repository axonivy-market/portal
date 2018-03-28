package ch.ivy.addon.portalkit.bo;

public class RemoteLibrary {

  private String id;
  private String projectName;
  private String projectVersion;
  private String application;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectVersion() {
    return projectVersion;
  }

  public void setProjectVersion(String projectVersion) {
    this.projectVersion = projectVersion;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }
}
