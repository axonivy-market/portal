package com.axonivy.portal.enums.statistic;

/**
 * How a card arranges its chart against the AI reading and the user's note.
 *
 * Not a stored preference: it follows the card's own width on the 12-column board, so resizing a
 * card rearranges it. Each constant has a matching mode in smart-statistic.css, selected through
 * the card's {@code data-layout} attribute.
 */
public enum CardLayout {

  /** Nearly the whole board: the texts take a column beside the chart. */
  SIDEBAR,

  /** Around half: the texts share a strip under the chart. */
  CAPTION,

  /** A small tile: the texts collapse to one footer row and open on click. */
  TUCKED;

  private static final int SIDEBAR_FROM = 11;
  private static final int CAPTION_FROM = 5;

  public static CardLayout forColumns(int columns) {
    if (columns >= SIDEBAR_FROM) {
      return SIDEBAR;
    }
    return columns >= CAPTION_FROM ? CAPTION : TUCKED;
  }
}
