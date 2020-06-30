package ch.ivy.addon.portalkit.util;

public class ScreenshotMargin {
  private int marginTop;
  private int marginRight;
  private int marginBottom;
  private int marginLeft;

  /**
   * Generate a margin option with order top, right, bottom, left
   * 
   * @param marginTop
   * @param marginRight
   * @param marginBottom
   * @param marginLeft
   */
  public ScreenshotMargin(int marginTop, int marginRight, int marginBottom, int marginLeft) {
    this.marginTop = marginTop;
    this.marginRight = marginRight;
    this.marginBottom = marginBottom;
    this.marginLeft = marginLeft;
  }

  /**
   * Generate a margin option
   * 
   * @param margin
   */
  public ScreenshotMargin(int margin) {
    this.marginTop = margin;
    this.marginRight = margin;
    this.marginBottom = margin;
    this.marginLeft = margin;
  }
  
  public ScreenshotMargin(int topBottom, int leftRight) {
    this.marginTop = topBottom;
    this.marginRight = leftRight;
    this.marginBottom = topBottom;
    this.marginLeft = leftRight;
  }

  public int getMarginTop() {
    return marginTop;
  }

  public void setMarginTop(int marginTop) {
    this.marginTop = marginTop;
  }

  public int getMarginRight() {
    return marginRight;
  }

  public void setMarginRight(int marginRight) {
    this.marginRight = marginRight;
  }

  public int getMarginBottom() {
    return marginBottom;
  }

  public void setMarginBottom(int marginBottom) {
    this.marginBottom = marginBottom;
  }

  public int getMarginLeft() {
    return marginLeft;
  }

  public void setMarginLeft(int marginLeft) {
    this.marginLeft = marginLeft;
  }


}
