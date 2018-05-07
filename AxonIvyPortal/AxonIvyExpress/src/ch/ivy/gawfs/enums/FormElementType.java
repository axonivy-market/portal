package ch.ivy.gawfs.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum FormElementType {
  INPUT_TEXT("InputFieldText", "/Dialogs/workflowCreation/FormDefinition/InputTypes/TextBox"),
  INPUT_DATE("InputFieldDate", "/Dialogs/workflowCreation/FormDefinition/InputTypes/DateSelection"),
  INPUT_NUMBER("InputFieldNumber", "/Dialogs/workflowCreation/FormDefinition/InputTypes/NumberField"),
  INPUT_TEXT_AREA("InputTextArea", "/Dialogs/workflowCreation/FormDefinition/InputArea"),
  CHECKBOX("ManyCheckbox", "/Dialogs/workflowCreation/FormDefinition/InputCheckbox"),
  RADIO_BUTTON("OneRadio", "/Dialogs/workflowCreation/FormDefinition/InputCheckbox"),
  FILE_UPLOAD("FileUpload", "/Dialogs/workflowCreation/FormDefinition/InputFileUpload");

  private final String value;
  private final String label;

  FormElementType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  public String getValue() {
    return value;
  }

  public String getLabel() {
    return Ivy.cms().co(label);
  }
}