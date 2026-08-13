package com.axonivy.portal.dto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public abstract class JsonListWrapper<T> {

  public static final String VERSION_FIELD_NAME = "version";
  public static final String ITEMS_FIELD_NAME = "items";

  private String version;
  private List<T> items;

  protected JsonListWrapper() {
  }

  @JsonCreator
  protected JsonListWrapper(
      @JsonProperty(VERSION_FIELD_NAME) String version,
      @JsonProperty(ITEMS_FIELD_NAME) List<T> items) {
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

  // ---- File I/O helpers ----

  /**
   * Deserializes a wrapper of the given type from a JSON file.
   *
   * Usage:
   * JsonListWrapper<Dashboard> wrapper = JsonListWrapper.fromFile(
   *     file, new TypeReference<DashboardListWrapper>() {});
   */
  public static <W extends JsonListWrapper<?>> W fromFile(File file, TypeReference<W> typeRef) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(file, typeRef);
  }

  /**
   * Serializes this wrapper to a JSON file.
   */
  public void toFile(File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writerWithDefaultPrettyPrinter().writeValue(file, this);
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
