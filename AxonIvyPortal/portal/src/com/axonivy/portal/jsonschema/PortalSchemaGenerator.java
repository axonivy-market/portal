package com.axonivy.portal.jsonschema;

import com.axonivy.portal.jsonschema.module.DashboardSchemaModule;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.github.victools.jsonschema.module.jackson.JacksonSchemaModule;

import tools.jackson.databind.node.ObjectNode;

public class PortalSchemaGenerator {

  private final SchemaGenerator generator = new SchemaGenerator(config().build());

  public ObjectNode generateSchema(Class<?> rootType) {
    return generator.generateSchema(rootType);
  }
  
  private static SchemaGeneratorConfigBuilder config() {
    return new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, exportPreset())
        .with(new JacksonSchemaModule(JacksonOption.ALWAYS_REF_SUBTYPES, JacksonOption.INCLUDE_ONLY_JSONPROPERTY_ANNOTATED_METHODS))
        .with(new DashboardSchemaModule());
  }

  private static OptionPreset exportPreset() {
       OptionPreset exportPreset = new OptionPreset(
           Option.SCHEMA_VERSION_INDICATOR,
           Option.ADDITIONAL_FIXED_TYPES,
           Option.STANDARD_FORMATS,
           Option.FLATTENED_ENUMS,
           Option.FLATTENED_OPTIONALS,
           Option.PUBLIC_NONSTATIC_FIELDS,
           Option.NONPUBLIC_NONSTATIC_FIELDS_WITH_GETTERS,
           Option.FIELDS_DERIVED_FROM_ARGUMENTFREE_METHODS,
           Option.ALLOF_CLEANUP_AT_THE_END,
           Option.GETTER_METHODS,
           Option.DUPLICATE_MEMBER_ATTRIBUTE_CLEANUP_AT_THE_END
   );
   return exportPreset; 
  }

}
