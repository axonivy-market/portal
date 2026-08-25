package com.axonivy.portal.userexamples.dto;

import java.util.List;
import java.util.Objects;

public abstract class JsonListWrapper<T> {

  public static final String VERSION_FIELD_NAME = "version";
  public static final String ITEMS_FIELD_NAME = "items";

  private String version;
  private List<T> items;

  protected JsonListWrapper() {
  }

  protected JsonListWrapper(String version, List<T> items) {
    this.version = version;
    this.items = items;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JsonListWrapper<?> other)) {
      return false;
    }
    return Objects.equals(version, other.version) && Objects.equals(items, other.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, items);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{version='" + version + "', items=" + items + "}";
  }
}
