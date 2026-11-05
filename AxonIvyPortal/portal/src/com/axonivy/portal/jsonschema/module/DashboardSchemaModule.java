package com.axonivy.portal.jsonschema.module;

import java.util.Arrays;

import com.axonivy.portal.jsonschema.annotation.PortalSchemaIgnoreFields;
import com.github.victools.jsonschema.generator.MemberScope;
import com.github.victools.jsonschema.generator.Module;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;

public class DashboardSchemaModule implements Module {
  @Override
  public void applyToConfigBuilder(SchemaGeneratorConfigBuilder builder) {
    builder.forFields().withIgnoreCheck(this::shouldIgnore);
  }

  private boolean shouldIgnore(MemberScope<?, ?> member) {
    Class<?> modelClass = member.getDeclarationDetails().getSchemaTargetType().getErasedType();
    PortalSchemaIgnoreFields annotation = modelClass.getAnnotation(PortalSchemaIgnoreFields.class);
    if (annotation == null) {
      return false;
    }
    String propertyName = member.getSchemaPropertyName();
    return Arrays.stream(annotation.value()).anyMatch(propertyName::equals);
  }
}
