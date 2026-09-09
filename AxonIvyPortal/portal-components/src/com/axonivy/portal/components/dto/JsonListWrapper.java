package com.axonivy.portal.components.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;

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

  /**
   * Version of this wrapper's own JSON <em>collection format</em> - i.e. the shape
   * {@code {"version": ..., "items": [...]}} itself - not the schema version of any individual
   * entity inside {@code items}. Entities no longer carry their own version (see
   * {@code PortalPackageService}); this is the single value written and read for the container.
   * Deliberately its own constant rather than reusing some entity type's {@code *JsonVersion.LATEST_VERSION}
   * (e.g. {@code DashboardJsonVersion}), since the wrapper format and any one entity's schema version
   * are independent concerns that happen to share a numeric value today, not the same value by
   * definition.
   */
  public static final String FORMAT_VERSION = "14.0.0";

  private String version;
  private List<T> items;

  public JsonListWrapper() {
  }

  public JsonListWrapper(String version, List<T> items) {
    this.version = version;
    this.items = items;
    clearItemVersions();
  }

  /**
   * Clears any per-item "version" property on every item, so it is never possible to build a
   * wrapper whose items still carry the pre-wrapper, entity-level version field - it belongs to this
   * container now (see {@link #FORMAT_VERSION}), not to individual entities. Uses reflection rather
   * than a shared interface or base class because this is a generic, dependency-free DTO shared
   * across Portal modules, and not every wrapped item type has a "version" property in the first
   * place (e.g. custom menu items) - those are simply left untouched.
   *
   * <p>Only runs from this constructor, not from {@link #setItems(List)} - Jackson deserializes an
   * incoming {"version": ..., "items": [...]} payload through the no-arg constructor and the
   * setters, so parsing an already-migrated import is unaffected; this only strips versions when
   * Portal code explicitly builds a new wrapper (i.e. on export/serialization).
   */
  private void clearItemVersions() {
    if (items == null) {
      return;
    }
    for (T item : items) {
      if (item == null) {
        continue;
      }
      try {
        Method setVersion = item.getClass().getMethod("setVersion", String.class);
        setVersion.invoke(item, (String) null);
      } catch (NoSuchMethodException e) {
        // This item type has no per-item "version" property - nothing to clear.
      } catch (IllegalAccessException | InvocationTargetException e) {
        // Unexpected - don't fail the whole export/serialization over this.
      }
    }
  }

  /**
   * Shared check for the canonical wrapper shape {@code {"version": ..., "items": [...]}}, so every
   * migrator and converter recognizes it the same way instead of each duplicating this check locally.
   */
  public static boolean isListWrapper(JsonNode node) {
    return node != null && node.isObject()
        && node.has(ITEMS_FIELD_NAME)
        && node.get(ITEMS_FIELD_NAME).isArray();
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
