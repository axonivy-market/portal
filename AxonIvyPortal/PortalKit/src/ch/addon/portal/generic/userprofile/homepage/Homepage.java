package ch.addon.portal.generic.userprofile.homepage;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Homepage {

  private HomepageType type;
  private String name = "";
  private String label;
  private String link;

  public HomepageType getType() {
    return type;
  }

  public void setType(HomepageType type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String getLabel() {
    return label;
  }
  
  public void setLabel(String label) {
    this.label = label;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Homepage)) {
      return false;
    }
    Homepage other = (Homepage) obj;
    EqualsBuilder builder = new EqualsBuilder();
    builder.append(name.toLowerCase(), other.getName().toLowerCase());
    return builder.isEquals();
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    builder.append(name.toLowerCase());
    return builder.toHashCode();
  }
}
