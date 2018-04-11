package ch.ivy.gawfs;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import ch.ivy.gawfs.enums.FormElementType;
import ch.ivy.gawfs.enums.UploadFileFormat;

public class DynaFormController implements Serializable {

  private static final String CONTROL_SPACER = "Spacer";

  private static final long serialVersionUID = -1486400417160014320L;

  private DynaFormModel model;
  private DynaFormRow tempRow;
  private CommonDragAndDropController commonDragAndDropController;

  public DynaFormController(DragAndDropController dragAndDropController) {
    this.commonDragAndDropController = dragAndDropController;
    model = new DynaFormModel();

  }

  /**
   * Create form model and elements
   */
  public void createForm() {
    if (commonDragAndDropController instanceof FourPanelDragAndDropController) {
      createForm((FourPanelDragAndDropController) commonDragAndDropController);
    } else if (commonDragAndDropController instanceof SixPanelDragAndDropController) {
      createForm((SixPanelDragAndDropController) commonDragAndDropController);
    } else {
      createForm((DragAndDropController) commonDragAndDropController);
    }
  }

  /**
   * Initialize "File Upload" element
   * 
   * @return initialized "File Upload" element
   */
  public static Formelement initializeFileUploadElement() {
    Formelement fileUpload = new Formelement();
    fileUpload.setType(FormElementType.FILE_UPLOAD);

    // Initialize value for number of files allowed
    fileUpload.setIntSetting(0);

    for (UploadFileFormat format : UploadFileFormat.values()) {
      fileUpload.addOption(new FormelementOption(format.getLabel()));
    }
    return fileUpload;
  }

  public String submitForm() {
    FacesMessage.Severity sev = FacesContext.getCurrentInstance().getMaximumSeverity();
    boolean hasErrors = (sev != null && (FacesMessage.SEVERITY_ERROR.compareTo(sev) >= 0));

    RequestContext requestContext = RequestContext.getCurrentInstance();
    requestContext.addCallbackParam("isValid", !hasErrors);

    return null;
  }

  public DynaFormModel getModel() {
    return model;
  }

  private void createForm(FourPanelDragAndDropController dragAndDropController) {
    model = new DynaFormModel();
    dragAndDropController.getFinalUsedFormelements().clear();
  }

  private void createForm(SixPanelDragAndDropController dragAndDropController) {
    model = new DynaFormModel();
    dragAndDropController.getFinalUsedFormelements().clear();
    initializeHeaderSection(dragAndDropController);
    initializeLeftAndRightSection(dragAndDropController);
    initializeFooterSection(dragAndDropController);
  }


  private void createForm(DragAndDropController dragAndDropController) {
    model = new DynaFormModel();
    dragAndDropController.getFinalUsedFormelements().clear();

    initializeHeaderSection(dragAndDropController);
    initializeMainSections(dragAndDropController);
    initializeFooterSection(dragAndDropController);
  }

  /**
   * Loop to create all Elements places in the Header Section
   * 
   */
  private void initializeHeaderSection(DragAndDropController dragAndDropController) {
    for (Formelement element : dragAndDropController.getSelectedFormelementsHeader()) {
      dragAndDropController.addFinalUsedFormelements(element);
      tempRow = model.createRegularRow();
      String elementType = element.getType().getValue();
      if (!element.getType().equals(FormElementType.FILE_UPLOAD)) {
        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, elementType, 3, 1);
        label.setForControl(control);
      } else {
        DynaFormLabel label = tempRow.addLabel(element.getLabel(), 4, 1);
        tempRow = model.createRegularRow();
        DynaFormControl control = tempRow.addControl(element, elementType, 4, 1);
        label.setForControl(control);
      }
    }
  }

  /**
   * Loop to create all Elements from the Left and Right Section
   */
  private void initializeMainSections(DragAndDropController dragAndDropController) {
    for (int i = 0; i < getMax(dragAndDropController.getSelectedFormelementsLeftPanel().size(), dragAndDropController.getSelectedFormelementsRightPanel().size()); i++) {
      tempRow = model.createRegularRow();

      // Elements from the Left Section
      if (dragAndDropController.getSelectedFormelementsLeftPanel().size() > i) {
        Formelement element = dragAndDropController.getSelectedFormelementsLeftPanel().get(i);
        dragAndDropController.addFinalUsedFormelements(element);

        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue());
        label.setForControl(control);
      } else {
        tempRow.addLabel(StringUtils.EMPTY);
        tempRow.addControl(null, CONTROL_SPACER);
      }

      // Elements from the Right Section
      if (dragAndDropController.getSelectedFormelementsRightPanel().size() > i) {
        Formelement element = dragAndDropController.getSelectedFormelementsRightPanel().get(i);
        dragAndDropController.addFinalUsedFormelements(element);

        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue());
        label.setForControl(control);

      } else {
        tempRow.addLabel(StringUtils.EMPTY);
        tempRow.addControl(null, CONTROL_SPACER);

      }
    }
  }

  /**
   * Loop to create all Elements places in the Footer Section
   */
  private void initializeFooterSection(DragAndDropController dragAndDropController) {
    for (Formelement element : dragAndDropController.getSelectedFormelementsFooter()) {
      dragAndDropController.addFinalUsedFormelements(element);
      tempRow = model.createRegularRow();
      String elementType = element.getType().getValue();

      if (!element.getType().equals(FormElementType.FILE_UPLOAD)) {
        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, elementType, 3, 1);
        label.setForControl(control);
      } else {
        DynaFormLabel label = tempRow.addLabel(element.getLabel(), 4, 1);
        tempRow = model.createRegularRow();
        DynaFormControl control = tempRow.addControl(element, elementType, 4, 1);
        label.setForControl(control);
      }
    }
  }

  private void initializeFooterSection(SixPanelDragAndDropController dragAndDropController) {
    // Loop to create all Elements places in the Footer Section
    for (Formelement element : dragAndDropController.getSelectedFormelementsFooter()) {
      dragAndDropController.addFinalUsedFormelements(element);
      tempRow = model.createRegularRow();
      if (!FormElementType.FILE_UPLOAD.equals(element.getType())) {
        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue(), 5, 1);
        label.setForControl(control);
      } else {
        DynaFormLabel label = tempRow.addLabel(element.getLabel(), 6, 1);
        tempRow = model.createRegularRow();
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue(), 6, 1);
        label.setForControl(control);
      }
    }
  }

  private void initializeLeftAndRightSection(SixPanelDragAndDropController dragAndDropController) {
    // Loop to create all Elements from the Left and Right Section
    for (int i = 0; i < getMax(dragAndDropController.getSelectedFormelementsLeftPanel().size(), dragAndDropController.getSelectedFormelementsRightPanel().size()); i++) {
      tempRow = model.createRegularRow();
      // Elements from the Left Section
      if (dragAndDropController.getSelectedFormelementsLeftPanel().size() > i) {
        Formelement element = dragAndDropController.getSelectedFormelementsLeftPanel().get(i);
        dragAndDropController.addFinalUsedFormelements(element);
        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue(), 3, 1);
        label.setForControl(control);
      } else {
        tempRow.addLabel(StringUtils.EMPTY);
        tempRow.addControl(null, CONTROL_SPACER, 3, 1);
      }

      // Elements from the Right Section
      if (dragAndDropController.getSelectedFormelementsRightPanel().size() > i) {
        Formelement element = dragAndDropController.getSelectedFormelementsRightPanel().get(i);
        dragAndDropController.addFinalUsedFormelements(element);
        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue());
        label.setForControl(control);
      } else {
        tempRow.addLabel(StringUtils.EMPTY);
        tempRow.addControl(null, CONTROL_SPACER);
      }
    }
  }

  private void initializeHeaderSection(SixPanelDragAndDropController dragAndDropController) {
    // Loop to create all Elements places in the Header Section
    for (int i = 0; i < getMax(dragAndDropController.getSelectedFormelementsHeader1().size(), dragAndDropController.getSelectedFormelementsHeader2().size(), dragAndDropController
        .getSelectedFormelementsHeader3().size()); i++) {
      tempRow = model.createRegularRow();
      // Elements from the Header Section 1
      if (dragAndDropController.getSelectedFormelementsHeader1().size() > i) {
        Formelement element = dragAndDropController.getSelectedFormelementsHeader1().get(i);
        dragAndDropController.addFinalUsedFormelements(element);

        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue());
        label.setForControl(control);
      } else {
        tempRow.addLabel(StringUtils.EMPTY);
        tempRow.addControl(null, CONTROL_SPACER);
      }
      // Elements from the Header Section 2
      if (dragAndDropController.getSelectedFormelementsHeader2().size() > i) {
        Formelement element = dragAndDropController.getSelectedFormelementsHeader2().get(i);
        dragAndDropController.addFinalUsedFormelements(element);

        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue());
        label.setForControl(control);
      } else {
        tempRow.addLabel(StringUtils.EMPTY);
        tempRow.addControl(null, CONTROL_SPACER);
      }
      // Elements from the Header Section 3
      if (dragAndDropController.getSelectedFormelementsHeader3().size() > i) {
        Formelement element = dragAndDropController.getSelectedFormelementsHeader3().get(i);
        dragAndDropController.addFinalUsedFormelements(element);

        DynaFormLabel label = tempRow.addLabel(element.getLabel());
        DynaFormControl control = tempRow.addControl(element, element.getType().getValue());
        label.setForControl(control);
      } else {
        tempRow.addLabel(StringUtils.EMPTY);
        tempRow.addControl(null, CONTROL_SPACER);
      }
    }
  }

  private int getMax(int... a) {
    int max = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
      }
    }
    return max;
  }

}
