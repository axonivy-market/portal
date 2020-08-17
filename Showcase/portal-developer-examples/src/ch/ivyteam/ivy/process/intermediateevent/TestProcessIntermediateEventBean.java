/**
 * 
 */
package ch.ivyteam.ivy.process.intermediateevent;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.process.extension.IIvyScriptEditor;
import ch.ivyteam.ivy.process.extension.IProcessExtensionConfigurationEditorEnvironment;
import ch.ivyteam.ivy.process.extension.impl.AbstractProcessExtensionConfigurationEditor;

/**
 * @author nqhoan
 *
 */
public class TestProcessIntermediateEventBean extends AbstractProcessIntermediateEventBean {

  /**
   * 
   */
  public TestProcessIntermediateEventBean() {
    super("TestProcessIntermediateEventBean", "Description of TestProcessIntermediateEventBean", String.class);
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
  public static class Editor extends AbstractProcessExtensionConfigurationEditor {

    /**
     * 
     */
    private IIvyScriptEditor demoIvyScriptField;

    @Override
    protected void createEditorPanelContent(Container editorPanel,
        IProcessExtensionConfigurationEditorEnvironment editorEnvironment) {

      // ===> Add here your code to create new ui widgets and to add them to the editor panel here <===
      // You can use the editorEnvironment to create TextField that are ivyScript aware.

      demoIvyScriptField = editorEnvironment.createIvyScriptEditor();
      editorPanel.add(demoIvyScriptField.getComponent(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
          GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }

    @Override
    protected void loadUiDataFromConfiguration() {

      // ===> Add here your code to load data from the configuration to the ui widgets <===
      // You can use the getBeanConfiguration() or getBeanConfigurationProperty() methods to load the configuration

      demoIvyScriptField.setText(getBeanConfigurationProperty("demo"));
    }

    @Override
    protected boolean saveUiDataToConfiguration() {

      // Clear the bean configuration and all its properties to flush outdated configurations.
      clearBeanConfiguration();

      // ===> Add here your code to save the data in the ui widgets to the configuration <===
      // You can use the setBeanConfiguration() or setBeanConfigurationProperty() methods to save the configuration

      setBeanConfigurationProperty("demo", demoIvyScriptField.getText());
      return true;
    }
  }

}
