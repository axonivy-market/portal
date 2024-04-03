/**
 * 
 */
package ch.ivyteam.ivy.process.intermediateevent;

import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.process.extension.ui.ExtensionUiBuilder;
import ch.ivyteam.ivy.process.extension.ui.IUiFieldEditor;
import ch.ivyteam.ivy.process.extension.ui.UiEditorExtension;

public class TestNewProcessIntermediateEventBean extends AbstractProcessIntermediateEventBean {

  /**
   * 
   */
  public TestNewProcessIntermediateEventBean() {
    super("TestNewProcessIntermediateEventBean", "Description of TestNewProcessIntermediateEventBean", String.class);
  }

  @Override
  public void poll() {
    boolean eventOccured = false;
    String additionalInformation = "";
    String resultObject = "";
    // An external system was trigger to do something. 
    // The external system or the one who triggered the external system must provide
    // a event identifier so that later the event identifier can be used to match 
    // the waiting IntermediateEvent with the event from the external system.
    // Therefore, the event identifier has to be provided twice. 
    // First, on the IntermediateEvent Inscription Mask to define for 
    // which event the IntermediateEvent has to wait for.
    // Second, on the IntermediateEventBean to specify which event was received.
    // However, the external system that sends the event must somehow provide the event identifier in his event data.
    String eventIdentifier = "";

    // ===> Add here your code to poll for new events from the external system
    //      that should trigger the continue of waiting processes <===
    // Parse the event identifier and the result object out of the event data

    if (eventOccured) {
      try {
        getEventBeanRuntime().fireProcessIntermediateEventEx(eventIdentifier, resultObject, additionalInformation);
      } catch (PersistencyException ex) {
        // ===> Add here your exception handling code if the event cannot be processed <===

      }
    }
  }

  /**
   * @author nqhoan
   *
   */
  public static class Editor extends UiEditorExtension {

    /**
     * 
     */
    private IUiFieldEditor demoField;

    @Override
    public void initUiFields(ExtensionUiBuilder ui) {
      // ===> Add here your code to create new ui widgets <===

      demoField = ui.scriptField().create();
    }

    @Override
    protected void loadUiDataFromConfiguration() {
      // ===> Add here your code to load data from the configuration to the ui widgets <===
      // You can use the getBeanConfiguration() or getBeanConfigurationProperty() methods to load the configuration
      demoField.setText(getBeanConfigurationProperty("demo"));
    }

    @Override
    protected boolean saveUiDataToConfiguration() {
      // Clear the bean configuration and all its properties to flush outdated configurations.
      clearBeanConfiguration();

      // ===> Add here your code to save the data in the ui widgets to the configuration <===
      // You can use the setBeanConfiguration() or setBeanConfigurationProperty() methods to save the configuration

      setBeanConfigurationProperty("demo", demoField.getText());
      return true;
    }
  }

}
