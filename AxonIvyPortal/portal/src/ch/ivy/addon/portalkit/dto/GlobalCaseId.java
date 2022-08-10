package ch.ivy.addon.portalkit.dto;

public class GlobalCaseId {
  private long caseId;
  private boolean isBusinessCase;

  public GlobalCaseId(long caseId, boolean isBusinessCase) {
    this.caseId = caseId;
    this.isBusinessCase = isBusinessCase;
  }

  public static class Builder {
    private long caseId;
    private boolean isBusinessCase;

    public Builder caseId(long id) {
      this.caseId = id;
      return this;
    }

    public Builder isBusinessCase(boolean isBusinessCase) {
      this.isBusinessCase = isBusinessCase;
      return this;
    }

    public GlobalCaseId build() {
      return new GlobalCaseId(caseId, isBusinessCase);
    }
  }
  
  public static Builder caseId(long caseId) {
    Builder builder = new Builder();
    builder.caseId(caseId);
    return builder;
  }

  public static GlobalCaseId createDefaultInstance() {
    return new GlobalCaseId(-1, false);
  }

  public long id() {
    return caseId;
  }

  public boolean isBusinessCase() {
    return isBusinessCase;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof GlobalCaseId)) {
      return false;
    }
    GlobalCaseId other = (GlobalCaseId) obj;

    return this.caseId == other.caseId;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = result * 31 + (int) (this.caseId ^ (this.caseId >>> 32));
    return result;
  }

}
