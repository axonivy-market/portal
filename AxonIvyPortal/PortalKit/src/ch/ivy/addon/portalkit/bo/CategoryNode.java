package ch.ivy.addon.portalkit.bo;

public class CategoryNode extends MainMenuNode {

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
