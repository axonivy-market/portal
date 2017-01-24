package test002ITInfrastructure.DeliverandConfigureEquipment;

import ch.ivyteam.ivy.richdialog.exec.panel.IRichDialogPanel;
import ch.ivyteam.ivy.richdialog.rdpanels.RichDialogGridBagPanel;

/**
 * <p>DeliverandConfigureEquipmentPanel is a rich dialog panel implementation.
 *
 * <p>Please note that a rich dialog panel is not an instance of a Swing 
 * container, but of an ULCContainer. As such it can not be run 
 * or instantiated outside the ULC framework.
 */
@SuppressWarnings("all")
public class DeliverandConfigureEquipmentPanel extends RichDialogGridBagPanel 
implements IRichDialogPanel 
{ 
  /** Serial version id */
  private static final long serialVersionUID = 1L;
  
  /**
   * Create a new instance of DeliverandConfigureEquipmentPanel
   */
  public DeliverandConfigureEquipmentPanel()
  {
    super();
    initialize();
  }
  
  /**
   * This method initializes DeliverandConfigureEquipmentPanel
   * @return void
   */
  private void initialize()
  {
  }
}