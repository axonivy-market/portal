package ch.ivy.addon.portalkit.dto.widget;

public class HistoryWidget extends AbstractWidget {

  private static final long serialVersionUID = -4972389298376434497L;
  private static final int DEFAULT_ROW_PER_PAGE = 5;
  private int rowPerPage;

  public HistoryWidget() {}

  public int getRowPerPage() {
    if (rowPerPage == 0) {
      rowPerPage = DEFAULT_ROW_PER_PAGE;
    }
    return rowPerPage;
  }

  public void setRowPerPage(int rowPerPage) {
    this.rowPerPage = rowPerPage;
  }
}
