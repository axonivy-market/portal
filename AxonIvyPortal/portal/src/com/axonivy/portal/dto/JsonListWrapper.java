package com.axonivy.portal.dto;

import java.util.List;
import java.util.Objects;

/**
 * Generic wrapper for a versioned list of objects, used across Portal for the
 * root-level JSON object shape:
 *
 * <pre>{@code
 * { "version": "...", "items": [ ... ] }
 * }</pre>
 *
 * This replaces ad-hoc root-array JSON files and Jackson's
 * {@code SerializationFeature.WRAP_ROOT_VALUE} (which does not respect
 * {@code @JsonRootName} on list elements and instead wraps under the runtime
 * container class name, e.g. {@code ArrayList}).
 *
 * Abstract so that Portal entity types can declare a small, self-documenting
 * concrete subclass (e.g. {@code DashboardListWrapper}) when static typing is
 * useful. For call sites where the element type is only known at runtime via
 * a {@code Class<T>}, use {@link GenericJsonListWrapper} instead.
 *
 * @param <T> the type of elements in the wrapped list
 */
public class JsonListWrapper<T> {

  public static final String VERSION_FIELD_NAME = "version";
  public static final String ITEMS_FIELD_NAME = "items";

  private String version;
  private List<T> items;

  public JsonListWrapper() {
  }

  public JsonListWrapper(String version, List<T> items) {
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
