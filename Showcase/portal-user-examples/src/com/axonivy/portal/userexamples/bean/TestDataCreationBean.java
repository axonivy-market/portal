package com.axonivy.portal.userexamples.bean;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class TestDataCreationBean extends AbstractProcessStartEventBean {

  public TestDataCreationBean() {
    super("Test Data Creation", "Create Portal User Example test data");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig configuration) {
    super.initialize(eventRuntime, configuration);
    getEventBeanRuntime().poll().disable();
    try {
      initTestData();
    } catch (IOException e) {
      Ivy.log().error("Cannot create test data");
    }
  }

  private void initTestData() throws IOException {

    IWebStartable start1 = Ivy.wf().findStartable(IApplication.current().getName()
        + "/portal-user-examples/Start Processes/LeaveRequest/start.ivp").orElse(null);


    if (start1 != null) {
      URI uri = URI.create(start1.getLink().getAbsolute());
      HttpURLConnection conncection = (HttpURLConnection) uri.toURL().openConnection();
      conncection.setRequestMethod("GET");
      int responseCode = conncection.getResponseCode();
      Ivy.log().info(responseCode);
    }

    IWebStartable start2 = Ivy.wf().findStartable(IApplication.current().getName()
        + "/portal-user-examples/Start Processes/LeaveRequest/start.ivp").orElse(null);


    if (start2 != null) {
      URI uri = URI.create(start2.getLink().getAbsolute());
      HttpURLConnection conncection = (HttpURLConnection) uri.toURL().openConnection();
      conncection.setRequestMethod("GET");
      int responseCode = conncection.getResponseCode();
      Ivy.log().info(responseCode);
    }
  }
}
