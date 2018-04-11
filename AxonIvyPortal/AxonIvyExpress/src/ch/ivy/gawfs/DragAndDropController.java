package ch.ivy.gawfs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.DragDropEvent;

import ch.ivy.gawfs.enums.FormElementType;
import ch.ivyteam.ivy.environment.Ivy;

public class DragAndDropController extends CommonDragAndDropController implements Serializable {

  private static final long serialVersionUID = 2896675125359151717L;

  // Panel DropId
  private static final String SELECTED_FORM_ELEMENTS_FOOTER_FIELD_SET = "selectedFormelementsFooterFieldSet";
  private static final String SELECTED_FORM_ELEMENTS_RIGHT_PANEL_FIELD_SET = "selectedFormelementsRightPanelFieldSet";
  private static final String SELECTED_FORM_ELEMENTS_LEFT_PANEL_FIELD_SET = "selectedFormelementsLeftPanelFieldSet";
  private static final String SELECTED_FORM_ELEMENTS_HEADER_FIELD_SET = "selectedFormelementsHeaderFieldSet";
  private static final String AVAILABLE_FORM_ELEMENTS_FIELD = "availableFormelementsField";
  // Panel DragId
  private static final String SELECTED_FORM_ELEMENTS_FOOTER = "selectedFormelementsFooter";
  private static final String SELECTED_FORM_ELEMENTS_RIGHT_PANEL = "selectedFormelementsRightPanel";
  private static final String SELECTED_FORM_ELEMENTS_LEFT_PANEL = "selectedFormelementsLeftPanel";
  private static final String SELECTED_FORM_ELEMENTS_HEADER = "selectedFormelementsHeader";
  private static final String AVAILABLE_FORM_ELEMENTS = "availableFormelements";

  private List<Formelement> selectedFormelementsHeader;
  private List<Formelement> selectedFormelementsLeftPanel;
  private List<Formelement> selectedFormelementsRightPanel;
  private List<Formelement> selectedFormelementsFooter;

  public DragAndDropController() {
    super();
    selectedFormelementsHeader = new ArrayList<Formelement>();
    selectedFormelementsLeftPanel = new ArrayList<Formelement>();
    selectedFormelementsRightPanel = new ArrayList<Formelement>();
    selectedFormelementsFooter = new ArrayList<Formelement>();
  }

  @Override
  public void onFieldDrop(DragDropEvent ddEvent) {
    String[] dragInfo = StringUtils.split(ddEvent.getDragId(), SEPARATOR_CHAR);
    String[] dropInfo = StringUtils.split(ddEvent.getDropId(), SEPARATOR_CHAR);
    if (dragInfo.length != NUMBER_DRAG_EVENT_INFO || dropInfo.length != NUMBER_DROP_EVENT_INFO || StringUtils.equals(dropInfo[1], dragInfo[1])) {
      return;
    }
    List<Formelement> dragFormElements = getFormElements(dragInfo[1]);
    List<Formelement> dropFormElements = getFormElements(dropInfo[1]);
    Formelement dragElement = dragFormElements.get(Integer.parseInt(dragInfo[2]));

    if (!Objects.isNull(dragElement) && FormElementType.FILE_UPLOAD.equals(dragElement.getType())) {
      if (StringUtils.equals(dropInfo[1], SELECTED_FORM_ELEMENTS_LEFT_PANEL_FIELD_SET) || StringUtils.equals(dropInfo[1], SELECTED_FORM_ELEMENTS_RIGHT_PANEL_FIELD_SET)) {
        displayFileUploadPositionNotice();
        return;
      }
    }
    if (!Objects.isNull(dragElement)) {
      dropFormElements.add(dragElement);
      dragFormElements.remove(dragElement);
    }
    updateForm();
  }

  private void displayFileUploadPositionNotice() {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Ivy.cms().co("/Dialogs/components/CaseDocument/fileUploadElementPosition"), "");
    FacesContext.getCurrentInstance().addMessage(AVAILABLE_FORM_ELEMENTS, message);
  }

  private List<Formelement> getFormElements(String panelCode) {
    if (StringUtils.equals(AVAILABLE_FORM_ELEMENTS, panelCode) || StringUtils.equals(AVAILABLE_FORM_ELEMENTS_FIELD, panelCode)) {
      return availableFormelements;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER_FIELD_SET, panelCode)) {
      return selectedFormelementsHeader;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_LEFT_PANEL, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_LEFT_PANEL_FIELD_SET, panelCode)) {
      return selectedFormelementsLeftPanel;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_RIGHT_PANEL, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_RIGHT_PANEL_FIELD_SET, panelCode)) {
      return selectedFormelementsRightPanel;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_FOOTER, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_FOOTER_FIELD_SET, panelCode)) {
      return selectedFormelementsFooter;
    }
    return Collections.emptyList();
  }

  public List<Formelement> getSelectedFormelementsHeader() {
    return selectedFormelementsHeader;
  }

  public void setSelectedFormelementsHeader(List<Formelement> selectedFormelementsHeader) {
    this.selectedFormelementsHeader = selectedFormelementsHeader;
  }

  public List<Formelement> getSelectedFormelementsLeftPanel() {
    return selectedFormelementsLeftPanel;
  }

  public void setSelectedFormelementsLeftPanel(List<Formelement> selectedFormelementsLeftPanel) {
    this.selectedFormelementsLeftPanel = selectedFormelementsLeftPanel;
  }

  public List<Formelement> getSelectedFormelementsRightPanel() {
    return selectedFormelementsRightPanel;
  }

  public void setSelectedFormelementsRightPanel(List<Formelement> selectedFormelementsRightPanel) {
    this.selectedFormelementsRightPanel = selectedFormelementsRightPanel;
  }

  public List<Formelement> getSelectedFormelementsFooter() {
    return selectedFormelementsFooter;
  }

  public void setSelectedFormelementsFooter(List<Formelement> selectedFormelementsFooter) {
    this.selectedFormelementsFooter = selectedFormelementsFooter;
  }
}
