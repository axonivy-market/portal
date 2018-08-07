package ch.ivy.addon.portalkit.dto;

public class GlobalCaseId {

  public static class Builder {
    private long serverId;
    private long caseId;

    public Builder caseId(long id) {
      this.caseId = id;
      return this;
    }

    public GlobalCaseId build() {
      return new GlobalCaseId(serverId, caseId);
    }
  }

  public static Builder inServer(long id) {
    Builder builder = new Builder();
    builder.serverId = id;
    return builder;
  }

  public static GlobalCaseId createDefaultInstance() {
    return new GlobalCaseId(-1, -1);
  }

  private long serverId;
  private long caseId;

  public GlobalCaseId(long serverId, long caseId) {
    this.serverId = serverId;
    this.caseId = caseId;
  }

  public long id() {
    return caseId;
  }

  public long serverId() {
    return serverId;
  }

}
