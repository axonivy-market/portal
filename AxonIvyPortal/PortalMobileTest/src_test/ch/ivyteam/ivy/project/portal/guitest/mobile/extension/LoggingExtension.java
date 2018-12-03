package ch.ivyteam.ivy.project.portal.guitest.mobile.extension;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoggingExtension implements BeforeEachCallback{

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
     System.out.println("Running  test: " + context.getTestMethod().get().getName());
  }

}
