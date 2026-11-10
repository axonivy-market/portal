package com.axonivy.portal.jsonschema.module;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.axonivy.portal.bo.Statistic;
import com.github.victools.jsonschema.generator.FieldScope;
import com.github.victools.jsonschema.generator.MemberScope;
import com.github.victools.jsonschema.generator.MethodScope;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.github.victools.jsonschema.module.jackson.JacksonSchemaModule;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;

public class PortalJacksonSchemaModule extends JacksonSchemaModule {

  private static final Map<Class<?>, Set<String>> EXCLUDED_PROPERTIES =
      Map.of(
          ColumnModel.class, Set.of("filter", "filterFrom", "filterList", "filterTo"),
          WidgetLayout.class, Set.of("id"),
          Statistic.class, Set.of("canDrillDown", "additionalConfigs"));

  public PortalJacksonSchemaModule() {
    super(
        JacksonOption.ALWAYS_REF_SUBTYPES,
        JacksonOption.FLATTENED_ENUMS_FROM_JSONVALUE,
        JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
  }

  @Override
  protected boolean shouldIgnoreMethod(MethodScope method) {
    if (!method.isPublic() || method.getArgumentCount() != 0 || super.shouldIgnoreMethod(method) || isExcluded(method)) {
      return true;
    }

    var targetType =
        method.getDeclarationDetails().getSchemaTargetType();

    var beanDescription = getBeanDescriptionForClass(targetType);

    return beanDescription.findProperties().stream()
        .filter(property -> property.couldSerialize())
        .map(property -> property.getGetter())
        .filter(Objects::nonNull)
        .noneMatch(getter -> getter.getAnnotated().equals(method.getRawMember()));
  }

  @Override
  protected boolean shouldIgnoreField(FieldScope field) {
    return super.shouldIgnoreField(field) || isExcluded(field);
  }

  private boolean isExcluded(MemberScope<?, ?> member) {
    Class<?> targetClass =
        member.getDeclarationDetails()
            .getSchemaTargetType()
            .getErasedType();

    String propertyName = member.getSchemaPropertyName();

    return EXCLUDED_PROPERTIES.entrySet().stream()
        .anyMatch(entry -> entry.getKey().isAssignableFrom(targetClass)
            && entry.getValue().contains(propertyName));
  }
}
