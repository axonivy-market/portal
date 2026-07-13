package com.axonivy.portal.components.comparator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;

class TestCustomSubMenuItemComparator {

  private final CustomSubMenuItemComparator comparator = new CustomSubMenuItemComparator();

  private CustomSubMenuItem item(Integer index, String label) {
    CustomSubMenuItem item = new CustomSubMenuItem();
    item.setIndex(index);
    item.setLabel(label);
    return item;
  }

  @Test
  void compare_differentIndex_comparesByIndex() {
    assertThat(comparator.compare(item(1, "b"), item(2, "a"))).isNegative();
    assertThat(comparator.compare(item(2, "a"), item(1, "b"))).isPositive();
  }

  @Test
  void compare_sameIndex_comparesByLabel() {
    assertThat(comparator.compare(item(1, "a"), item(1, "b"))).isNegative();
    assertThat(comparator.compare(item(1, "b"), item(1, "a"))).isPositive();
  }

  @Test
  void compare_sameIndexAndLabel_returnsZero() {
    assertThat(comparator.compare(item(1, "a"), item(1, "a"))).isZero();
  }

  @Test
  void compare_nullIndex_treatedAsMaxValue() {
    assertThat(comparator.compare(item(null, "a"), item(1, "a"))).isPositive();
    assertThat(comparator.compare(item(1, "a"), item(null, "a"))).isNegative();
  }

  @Test
  void compare_nullLabel_treatedAsEmptyString() {
    assertThat(comparator.compare(item(1, null), item(1, "a"))).isNegative();
    assertThat(comparator.compare(item(1, "a"), item(1, null))).isPositive();
  }

  @Test
  void compare_nullItem_treatedAsMaxIndexAndEmptyLabel() {
    assertThat(comparator.compare(null, item(1, "a"))).isPositive();
    assertThat(comparator.compare(item(1, "a"), null)).isNegative();
  }

  @Test
  void compare_bothNullItems_returnsZero() {
    assertThat(comparator.compare(null, null)).isZero();
  }
}
