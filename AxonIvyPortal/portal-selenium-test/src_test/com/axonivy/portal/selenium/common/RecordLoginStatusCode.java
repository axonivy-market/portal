package com.axonivy.portal.selenium.common;

import com.browserup.bup.filters.ResponseFilter;
import com.browserup.bup.util.HttpMessageContents;
import com.browserup.bup.util.HttpMessageInfo;

import io.netty.handler.codec.http.HttpResponse;

public final class RecordLoginStatusCode implements ResponseFilter {
  public final String LOGIN = "Login.xhtml";
  public int code;
  public boolean isAjax;

  @Override
  public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
    if (messageInfo.getOriginalUrl().endsWith(LOGIN)) {
      code = response.status().code();
      var facesRequest = messageInfo.getOriginalRequest().headers().get("Faces-Request");
      isAjax = facesRequest != null && "partial/ajax".equals(facesRequest);
    }
  }
}
