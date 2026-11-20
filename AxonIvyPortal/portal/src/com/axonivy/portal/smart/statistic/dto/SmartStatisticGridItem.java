package com.axonivy.portal.smart.statistic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * One entry of a user's personal chart list: which chart, where it sits, and whether it is
 * pinned to the top of the page.
 *
 * The short JSON names are deliberately the same ones {@code WidgetLayout} uses, so a grid that
 * was stored before pinning existed still reads back correctly - the missing {@code p} simply
 * defaults to false. {@code ignoreUnknown} covers the {@code style} and {@code styleClass}
 * fields those older payloads may carry.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmartStatisticGridItem implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Matches the dashboard's default statistic widget size. */
  public static final int DEFAULT_WIDTH = 4;
  public static final int DEFAULT_HEIGHT = 5;
  /** A single figure needs far less room than a bar, line or pie chart. */
  public static final int NUMBER_WIDTH = 3;
  public static final int NUMBER_HEIGHT = 3;

  /**
   * The size a draft is drawn at, and therefore the size it keeps when it is kept. The view sizes
   * the draft box from these too, so keeping a chart never moves or resizes it.
   */
  public static final int DRAFT_WIDTH = 6;
  public static final int DRAFT_HEIGHT = 4;

  private String id;

  @JsonProperty("w")
  private int width = DEFAULT_WIDTH;
  @JsonProperty("h")
  private int height = DEFAULT_HEIGHT;
  @JsonProperty("x")
  private int axisX;
  @JsonProperty("y")
  private int axisY;
  @JsonProperty("p")
  private boolean pinned;

  public SmartStatisticGridItem() {}

  public SmartStatisticGridItem(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getAxisX() {
    return axisX;
  }

  public void setAxisX(int axisX) {
    this.axisX = axisX;
  }

  public int getAxisY() {
    return axisY;
  }

  public void setAxisY(int axisY) {
    this.axisY = axisY;
  }

  public boolean isPinned() {
    return pinned;
  }

  public void setPinned(boolean pinned) {
    this.pinned = pinned;
  }
}
