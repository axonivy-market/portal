package ch.ivy.addon.portal.chat;

import javax.ws.rs.container.AsyncResponse;

/**
 * Stores information for each chat long-polling request, it includes AsyncResponse and clientId
 */
public class ResponseInfo {
  private AsyncResponse asyncResponse;
  private String clientId;

  public ResponseInfo(AsyncResponse asyncResponse, String clientId) {
    super();
    this.asyncResponse = asyncResponse;
    this.clientId = clientId;
  }

  public AsyncResponse getAsyncResponse() {
    return asyncResponse;
  }

  public void setAsyncResponse(AsyncResponse asyncResponse) {
    this.asyncResponse = asyncResponse;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

}
