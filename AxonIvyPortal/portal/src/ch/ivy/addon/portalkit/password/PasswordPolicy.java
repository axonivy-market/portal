package ch.ivy.addon.portalkit.password;

import java.io.Serializable;
import java.util.Arrays;

import ch.ivy.addon.portalkit.enums.PasswordPolicyType;

public class PasswordPolicy implements Serializable {

  private static final long serialVersionUID = -734939092543763197L;

  private PasswordPolicyType type;
  private String title;
  private Boolean active;
  private int value;

  public PasswordPolicy() {
  }

  public PasswordPolicyType getType() {
    return type;
  }

  public void setType(PasswordPolicyType type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getValidationMessage() {
    return type.getValidationMessage(Arrays.asList(value));
  }
}