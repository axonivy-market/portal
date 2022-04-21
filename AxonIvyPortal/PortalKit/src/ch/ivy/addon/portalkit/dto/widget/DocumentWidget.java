package ch.ivy.addon.portalkit.dto.widget;

public class DocumentWidget extends AbstractWidget {

  private static final long serialVersionUID = 5548153647953211294L;
  private static final int DEFAULT_ROW_PER_PAGE = 5;
  private int rowPerPage;

  public DocumentWidget() {}

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
