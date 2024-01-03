package com.axonivy.portal.userexamples.dto;

import ch.ivyteam.ivy.workflow.category.Category;

public class CategoryNode extends MainMenuNode {

  public CategoryNode() { }

  public CategoryNode(String value, String category) {
    setValue(value);
    setCategory(category);
  }

  public CategoryNode(Category category) {
    setValue(category.getName());
    setCategory(category.getCmsUri());
  }

  private String category;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return this.getCategory();
  }
}
