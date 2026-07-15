package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.axonivy.portal.components.ivydata.exception.PortalIvyDataException;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.test.log.LoggerAccess;

@IvyTest
class TestErrorHandler {

  @RegisterExtension
  LoggerAccess log = new LoggerAccess(ErrorHandler.class.getName());

  @Test
  void addError_logsErrorWithExceptionDetails() {
    PortalIvyDataException error = new PortalIvyDataException("myApp", "something went wrong");

    ErrorHandler.addError(error);

    assertThat(log.errors()).anyMatch(msg -> msg.contains("myApp") && msg.contains("something went wrong"));
  }
}
