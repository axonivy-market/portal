package com.axonivy.portal.migration.common;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A single migration step that upgrades one entity's JSON representation from one schema version to
 * the next.
 *
 * <p><b>In-place mutation contract:</b> {@link #convert(JsonNode)} mutates the supplied
 * {@link JsonNode} directly (e.g. via {@code ObjectNode.put}/{@code set}/{@code remove}) rather than
 * returning a new node. Callers - the various {@code Json*Migrator} classes - rely on this: they pass
 * the same node through a chain of converters and expect each one to modify it in place, then read
 * the (by-reference) result back afterwards. Implementations must not assume the returned value of
 * {@code convert} is used, and must not replace the node's identity (e.g. by wrapping it in a new
 * node and expecting callers to pick up the wrapper).
 */
public interface IJsonConverter {

  /**
   * @return the version which is introduced by this converter
   */
  AbstractJsonVersion version();

  /**
   * Mutates {@code jsonNode} in place to upgrade it to the schema version returned by
   * {@link #version()}. See the class-level contract above - this method does not return the
   * migrated result, it changes {@code jsonNode} itself.
   */
  void convert(JsonNode jsonNode);
}
