package com.axonivy.portal.components.persistence.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class TestBusinessEntityConverter {

  static class SampleEntity {
    public String name;
    public int value;

    public SampleEntity() {}

    public SampleEntity(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }

  @Test
  void entityToJsonValue_andJsonValueToEntity_roundTrip() {
    SampleEntity entity = new SampleEntity("foo", 42);
    String json = BusinessEntityConverter.entityToJsonValue(entity);
    assertThat(json).contains("\"name\":\"foo\"").contains("\"value\":42");

    SampleEntity result = BusinessEntityConverter.jsonValueToEntity(json, SampleEntity.class);
    assertThat(result.name).isEqualTo("foo");
    assertThat(result.value).isEqualTo(42);
  }

  @Test
  void entityToJsonNode_returnsTraversableNode() {
    SampleEntity entity = new SampleEntity("bar", 7);
    assertThat(BusinessEntityConverter.entityToJsonNode(entity).get("name").asText()).isEqualTo("bar");
  }

  @ParameterizedTest
  @NullAndEmptySource
  void jsonValueToEntities_blankInput_returnsEmptyList(String value) {
    List<SampleEntity> result = BusinessEntityConverter.jsonValueToEntities(value, SampleEntity.class);
    assertThat(result).isEmpty();
  }

  @Test
  void jsonValueToEntities_validJsonArray_returnsList() {
    String json = "[{\"name\":\"a\",\"value\":1},{\"name\":\"b\",\"value\":2}]";
    List<SampleEntity> result = BusinessEntityConverter.jsonValueToEntities(json, SampleEntity.class);
    assertThat(result).hasSize(2);
    assertThat(result.get(0).name).isEqualTo("a");
    assertThat(result.get(1).value).isEqualTo(2);
  }

  @Test
  void prettyPrintEntityToJsonValue_containsNewlines() {
    SampleEntity entity = new SampleEntity("foo", 1);
    String pretty = BusinessEntityConverter.prettyPrintEntityToJsonValue(entity);
    assertThat(pretty).contains("\n");
  }
}
