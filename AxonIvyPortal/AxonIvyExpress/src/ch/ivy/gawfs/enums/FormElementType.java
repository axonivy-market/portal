package ch.ivy.gawfs.enums;

public enum FormElementType {
  INPUT_TEXT("InputFieldText"),
  INPUT_DATE("InputFieldDate"),
  INPUT_NUMBER("InputFieldNumber"),
  INPUT_TEXT_AREA("InputTextAreal"),
  CHECKBOX("ManyCheckbox"),
  RADIO_BUTTON("OneRadio"),
  FILE_UPLOAD("FileUpload");

  private final String value;

  FormElementType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}