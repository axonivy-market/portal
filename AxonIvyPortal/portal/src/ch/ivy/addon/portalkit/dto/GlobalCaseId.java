package ch.ivy.addon.portalkit.dto;

public class GlobalCaseId {
  private boolean isBusinessCase;
  private String uuid;

  public GlobalCaseId(String uuid, boolean isBusinessCase) {
    this.isBusinessCase = isBusinessCase;
    this.uuid = uuid;
  }
  
  public static class Builder {
    private boolean isBusinessCase;
    private String uuid;

    public Builder uuid(String uuid) {
      this.uuid = uuid;
      return this;
    }

    public Builder isBusinessCase(boolean isBusinessCase) {
      this.isBusinessCase = isBusinessCase;
      return this;
    }

    public GlobalCaseId build() {
      return new GlobalCaseId(uuid, isBusinessCase);
    }
  }
  
  public static Builder uuid(String uuid) {
    Builder builder = new Builder();
    builder.uuid(uuid);
    return builder;
  }

  public static GlobalCaseId createDefaultInstance() {
    return new GlobalCaseId("", false);
  }

  public String uuid() {
    return this.uuid;
  }

  public boolean isBusinessCase() {
    return this.isBusinessCase;
  }
}
