package com.axonivy.portal.developerexamples.component.customize;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;

public class CustomizedIvyDocument extends IvyDocument {
  private String customer;
  
  public String getCustomer() {
    return customer;
  }
  
  public void setCustomer(String customer) {
    this.customer = customer;
  }
}
