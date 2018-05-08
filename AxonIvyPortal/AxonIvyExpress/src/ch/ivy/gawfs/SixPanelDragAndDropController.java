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

public class SixPanelDragAndDropController extends CommonDragAndDropController implements Serializable {

  private static final long serialVersionUID = 2896675125359151717L;

  // Panel DropId
  private static final String SELECTED_FORM_ELEMENTS_HEADER_PANEL_COLUMN_1 = "selectedFormelementsHeaderPanelColumn-1";
  private static final String SELECTED_FORM_ELEMENTS_HEADER_PANEL_COLUMN_2 = "selectedFormelementsHeaderPanelColumn-2";
  private static final String SELECTED_FORM_ELEMENTS_HEADER_PANEL_COLUMN_3 = "selectedFormelementsHeaderPanelColumn-3";
  private static final String SELECTED_FORM_ELEMENTS_LEFT_PANEL_COLUMN = "selectedFormelementsLeftPanelColumn";
  private static final String SELECTED_FORM_ELEMENTS_RIGHT_PANEL_COLUMN = "selectedFormelementsRightPanelColumn";
  private static final String SELECTED_FORM_ELEMENTS_FOOTER_PANEL_ROW = "selectedFormelementsFooterPanelRow";
  private static final String AVAILABLE_FORM_ELEMENTS_FIELD = "availableFormelementsField";
  // Panel DragId
  private static final String SELECTED_FORM_ELEMENTS_HEADER_PANEL_DATA_GRID_1 = "selectedFormelementsHeaderPanelDataGrid-1";
  private static final String SELECTED_FORM_ELEMENTS_HEADER_PANEL_DATA_GRID_2 = "selectedFormelementsHeaderPanelDataGrid-2";
  private static final String SELECTED_FORM_ELEMENTS_HEADER_PANEL_DATA_GRID_3 = "selectedFormelementsHeaderPanelDataGrid-3";
  private static final String SELECTED_FORM_ELEMENTS_LEFT_PANEL_DATA_GRID = "selectedFormelementsLeftPanelDataGrid";
  private static final String SELECTED_FORM_ELEMENTS_RIGHT_PANEL_DATA_GRID = "selectedFormelementsRightPanelDataGrid";
  private static final String SELECTED_FORM_ELEMENTS_FOOTERPANEL_DATA_GRID = "selectedFormelementsFooterPanelDataGrid";
  private static final String AVAILABLE_FORM_ELEMENTS = "availableFormelements";

  private List<Formelement> selectedFormelementsHeader1;
  private List<Formelement> selectedFormelementsHeader2;
  private List<Formelement> selectedFormelementsHeader3;
  private List<Formelement> selectedFormelementsLeftPanel;
  private List<Formelement> selectedFormelementsRightPanel;
  private List<Formelement> selectedFormelementsFooter;

  public SixPanelDragAndDropController() {
    super();
    selectedFormelementsHeader1 = new ArrayList<>();
    selectedFormelementsHeader2 = new ArrayList<>();
    selectedFormelementsHeader3 = new ArrayList<>();
    selectedFormelementsLeftPanel = new ArrayList<>();
    selectedFormelementsRightPanel = new ArrayList<>();
    selectedFormelementsFooter = new ArrayList<>();
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
      if (!StringUtils.equals(dropInfo[1], SELECTED_FORM_ELEMENTS_FOOTER_PANEL_ROW) && !StringUtils.equals(dropInfo[1], AVAILABLE_FORM_ELEMENTS_FIELD)) {
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
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER_PANEL_COLUMN_1, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER_PANEL_DATA_GRID_1, panelCode)) {
      return selectedFormelementsHeader1;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER_PANEL_COLUMN_2, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER_PANEL_DATA_GRID_2, panelCode)) {
      return selectedFormelementsHeader2;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER_PANEL_COLUMN_3, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_HEADER_PANEL_DATA_GRID_3, panelCode)) {
      return selectedFormelementsHeader3;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_LEFT_PANEL_COLUMN, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_LEFT_PANEL_DATA_GRID, panelCode)) {
      return selectedFormelementsLeftPanel;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_RIGHT_PANEL_COLUMN, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_RIGHT_PANEL_DATA_GRID, panelCode)) {
      return selectedFormelementsRightPanel;
    } else if (StringUtils.equals(SELECTED_FORM_ELEMENTS_FOOTER_PANEL_ROW, panelCode) || StringUtils.equals(SELECTED_FORM_ELEMENTS_FOOTERPANEL_DATA_GRID, panelCode)) {
      return selectedFormelementsFooter;
    }
    return Collections.emptyList();
  }

  public List<Formelement> getSelectedFormelementsHeader1() {
    return selectedFormelementsHeader1;
  }

  public void setSelectedFormelementsHeader1(List<Formelement> selectedFormelementsHeader1) {
    this.selectedFormelementsHeader1 = selectedFormelementsHeader1;
  }

  public List<Formelement> getSelectedFormelementsHeader2() {
    return selectedFormelementsHeader2;
  }

  public void setSelectedFormelementsHeader2(List<Formelement> selectedFormelementsHeader2) {
    this.selectedFormelementsHeader2 = selectedFormelementsHeader2;
  }

  public List<Formelement> getSelectedFormelementsHeader3() {
    return selectedFormelementsHeader3;
  }

  public void setSelectedFormelementsHeader3(List<Formelement> selectedFormelementsHeader3) {
    this.selectedFormelementsHeader3 = selectedFormelementsHeader3;
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
