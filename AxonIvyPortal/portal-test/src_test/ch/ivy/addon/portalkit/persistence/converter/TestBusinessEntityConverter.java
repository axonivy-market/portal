package ch.ivy.addon.portalkit.persistence.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

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
  void entityToJsonValue_singleObject_isNotWrapped() {
    SampleEntity entity = new SampleEntity("foo", 42);
    String json = BusinessEntityConverter.entityToJsonValue(entity);
    assertThat(json).doesNotContain("\"version\"").doesNotContain("\"items\"");
    assertThat(json).contains("\"name\":\"foo\"").contains("\"value\":42");
  }

  @Test
  void entityToJsonValue_list_usesVersionItemsWrapperShape() {
    List<SampleEntity> entities = List.of(new SampleEntity("a", 1), new SampleEntity("b", 2));
    String json = BusinessEntityConverter.entityToJsonValue(entities);
    assertThat(json).contains("\"version\":\"14.0.0\"").contains("\"items\":[");
  }

  @Test
  void entityToJsonValue_andJsonValueToEntities_roundTrip() {
    List<SampleEntity> entities = List.of(new SampleEntity("a", 1), new SampleEntity("b", 2));
    String json = BusinessEntityConverter.entityToJsonValue(entities);

    List<SampleEntity> result = BusinessEntityConverter.jsonValueToEntities(json, SampleEntity.class);

    assertThat(result).hasSize(2);
    assertThat(result.get(0).name).isEqualTo("a");
    assertThat(result.get(1).value).isEqualTo(2);
  }

  @Test
  void jsonValueToEntities_emptyItemsInWrapper_returnsEmptyList() {
    String json = "{\"version\":\"1.0\",\"items\":[]}";
    List<SampleEntity> result = BusinessEntityConverter.jsonValueToEntities(json, SampleEntity.class);
    assertThat(result).isEmpty();
  }

  @Test
  void jsonValueToEntities_legacyPlainArray_stillReadable() {
    String json = "[{\"name\":\"a\",\"value\":1},{\"name\":\"b\",\"value\":2}]";
    List<SampleEntity> result = BusinessEntityConverter.jsonValueToEntities(json, SampleEntity.class);
    assertThat(result).hasSize(2);
    assertThat(result.get(0).name).isEqualTo("a");
  }


  @Test
  void jsonValueToEntities_blankInput_returnsEmptyList() {
    assertThat(BusinessEntityConverter.jsonValueToEntities(null, SampleEntity.class)).isEmpty();
    assertThat(BusinessEntityConverter.jsonValueToEntities("", SampleEntity.class)).isEmpty();
  }

  @Test
  void convertJsonNodeToList_wrapperShape_returnsItems() throws Exception {
    String json = "{\"version\":\"14.0.0\",\"items\":[{\"name\":\"a\",\"value\":1}]}";
    var node = BusinessEntityConverter.getObjectMapper().readTree(json);
    List<SampleEntity> result = BusinessEntityConverter.convertJsonNodeToList(node, SampleEntity.class);
    assertThat(result).hasSize(1);
    assertThat(result.get(0).name).isEqualTo("a");
  }
}
