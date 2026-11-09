package com.axonivy.portal.jsonschema.generator;

import com.axonivy.portal.jsonschema.module.PortalJacksonSchemaModule;
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

  private static OptionPreset exportPreset() {
    return new OptionPreset(
           Option.SCHEMA_VERSION_INDICATOR,
           Option.ADDITIONAL_FIXED_TYPES,
           Option.STANDARD_FORMATS,
           Option.FLATTENED_ENUMS,
           Option.PUBLIC_NONSTATIC_FIELDS,
           Option.NONPUBLIC_NONSTATIC_FIELDS_WITH_GETTERS,
           Option.FIELDS_DERIVED_FROM_ARGUMENTFREE_METHODS,
           Option.ALLOF_CLEANUP_AT_THE_END,
           Option.GETTER_METHODS,
           Option.NONSTATIC_NONVOID_NONGETTER_METHODS,
           Option.DUPLICATE_MEMBER_ATTRIBUTE_CLEANUP_AT_THE_END,
           Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT
    );
  }
}
