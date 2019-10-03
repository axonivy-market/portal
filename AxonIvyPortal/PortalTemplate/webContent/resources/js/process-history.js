function CaseWidget() {
  this.hideListIfEmptyCase = function(emptyRow) {
    if(emptyRow) {
      $header = $('.js-case-widget-column-header');
      $list = $('.process-history-list');
      $header.hide();
      $list.hide();
    }
  }
}
