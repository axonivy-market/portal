package ch.ivy.addon.portalkit.enums;

public enum WebServiceEndPoint {

  IS_ALIVE("14684B7E15F81BA6"), ABSENCE("14EFD6DC6E4A27E6"), CASE("137A1AD8C8C617F9"), USER_SETTING("138D76541AF4C4CF"), SUPPORTED_LANGUAGE(
      "14E90F0F6A258B63"), PROCESS_START("137A1AD0988B83E7"), TASK("1380566F9095B9C4"), SECURITY_SETTING("138056017E3F98C2"), PORTAL_DATA("150374D567D113F5");

  private String wsProcessId;

  WebServiceEndPoint(String wsProcessId) {
    this.wsProcessId = wsProcessId;
  }

  @Override
  public String toString() {
    return "/" + wsProcessId;
  }
  
  public String wsProcessId(){
    return wsProcessId;
  }
}
