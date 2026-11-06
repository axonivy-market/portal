package com.axonivy.portal.jsonschema.module;

import java.util.Set;

import com.axonivy.portal.jsonschema.annotation.PortalSchemaInclude;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.Module;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;

public class PortalSchemaModule implements Module {

  Set<String> ignoreDashboardFields = Set.of("oldId");

  @Override
  public void applyToConfigBuilder(SchemaGeneratorConfigBuilder builder) {
    builder.forFields().withIgnoreCheck(field -> shouldIgnoreFields(field));
    builder.forMethods()
        .withIgnoreCheck(method -> !method.isPublic() || method.getArgumentCount() != 0 ||
            method.getAnnotation(PortalSchemaInclude.class) == null);
  }

  private boolean shouldIgnoreFields(FieldScope field) {
    Class<?> targetClass = field.getDeclarationDetails().getSchemaTargetType().getErasedType();
    if (Dashboard.class.equals(targetClass)) {
      return ignoreDashboardFields.contains(field.getDeclaredName());
    }
    return false;
  }

}
