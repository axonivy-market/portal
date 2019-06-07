package ch.ivy.addon.portalkit.util;

import java.util.Comparator;
import java.util.stream.Collectors;

import ch.ivy.gawfs.DragAndDropController;
import ch.ivy.gawfs.Formelement;

public class ExpressProcessUtils {
  private ExpressProcessUtils() {}

  public static void sortIndexInPanels(DragAndDropController controller) {
    controller.setSelectedFormelementsHeader(controller.getSelectedFormelementsHeader().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
    controller.setSelectedFormelementsLeftPanel(controller.getSelectedFormelementsLeftPanel().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
    controller.setSelectedFormelementsRightPanel(controller.getSelectedFormelementsRightPanel().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
    controller.setSelectedFormelementsFooter(controller.getSelectedFormelementsFooter().stream().sorted(Comparator.comparingInt(Formelement::getIndexInPanel)).collect(Collectors.toList()));
  }
}
