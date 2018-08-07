package ch.ivy.gawfs;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
  private static final long serialVersionUID = 205667893049947067L;
  private String name;
  private String size;
  private Date modifiedDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

}
