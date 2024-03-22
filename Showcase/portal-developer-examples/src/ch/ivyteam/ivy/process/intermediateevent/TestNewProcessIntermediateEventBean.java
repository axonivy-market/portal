/**
 * 
 */
package ch.ivyteam.ivy.process.intermediateevent;

import ch.ivyteam.ivy.persistence.PersistencyException;

public final class TestNewProcessIntermediateEventBean extends AbstractProcessIntermediateEventBean {

  /**
   * This is a bean to create task with state WAITING_FOR_INTERMEDIATE_EVENT
   * for the example of how Portal show technical tasks
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
}
