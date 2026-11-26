package com.axonivy.portal.schema.generator;

import com.axonivy.portal.schema.module.PortalJacksonSchemaModule;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

import io.github.axonivy.json.schema.ExpressiveSchemaModule;
import tools.jackson.databind.node.ObjectNode;

public class PortalSchemaGenerator {

  private final SchemaGenerator generator = new SchemaGenerator(config().build());

  public ObjectNode generateSchema(Class<?> rootType) {
    return generator.generateSchema(rootType);
  }

  private static SchemaGeneratorConfigBuilder config() {
    return new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, exportPreset())
        .with(new PortalJacksonSchemaModule())
        .with(new ExpressiveSchemaModule());
  }

  /**
   * Options used to export the schemas. Each option is explained next to its line.
   * For more information on how the options work, see
   * https://victools.github.io/jsonschema-generator/#generator-options
   */
  private static OptionPreset exportPreset() {
    return new OptionPreset(
        Option.SCHEMA_VERSION_INDICATOR, // add "$schema" so validators know the JSON Schema draft (2020-12)
        Option.ADDITIONAL_FIXED_TYPES, // map types like UUID, URI, Date to plain string/number schemas instead of object shapes
        Option.FLATTENED_ENUMS, // enums become {"type": "string", "enum": [...]} with constant names, simpler for JSON consumers
        Option.NONPUBLIC_NONSTATIC_FIELDS_WITH_GETTERS, // include private fields that have a getter, since they appear in the JSON output
        Option.FIELDS_DERIVED_FROM_ARGUMENTFREE_METHODS, // treat argument-free methods like getName() as property "name"
        Option.ALLOF_CLEANUP_AT_THE_END, // merge unnecessary allOf wrappers at the end to keep the schema readable
        Option.GETTER_METHODS, // include getX()/isX() getters as properties, matching the JSON output
        Option.NONSTATIC_NONVOID_NONGETTER_METHODS, // include other public methods that return a value (computed values)
        Option.DUPLICATE_MEMBER_ATTRIBUTE_CLEANUP_AT_THE_END, // remove attributes repeated in member schemas that already reference a shared definition
        Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT); // additionalProperties: false, so unknown keys (e.g. typos) are rejected
  }
}
