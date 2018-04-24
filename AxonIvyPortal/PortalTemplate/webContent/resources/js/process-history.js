function CaseWidget() {
  var replacedClass = 'replaced';
  var isSelectedClass = 'is-selected';
  var MEDIUM_RESPONSIVE_THRESHOLD = 950;
  var LARGE_RESPONSIVE_THRESHOLD = 1550;
  
  this.setupHeader = function() {
    $processHistoryListHeader = $('.js-case-widget-column-header');
    $uiDatascrollerContent = $('.process-history-list .ui-datascroller-content');
    $uiDatascrollerlist = $('.process-history-list .ui-datascroller-content .ui-datascroller-list');
    if ($uiDatascrollerContent.width() > $uiDatascrollerlist.width()) {
      $processHistoryListHeader.css("padding-right", "20px");
    } else {
      $processHistoryListHeader.css("padding-right", "2px");
    }
  }

  this.setupCaseBody = function() {

    var $caseItem = caseItem();
    if ($caseItem.length !== 0) {
      var $caseItemWidth = $caseItem.width();
      
      var $caseDetails = $('.js-case-details', $caseItem);
      var $responsiveHandleContainer = $('.js-responsive-handle-container', $caseItem);
      if($caseItemWidth < LARGE_RESPONSIVE_THRESHOLD) {
        $responsiveHandleContainer.show();
      }
      
      if ($caseItemWidth >= MEDIUM_RESPONSIVE_THRESHOLD && $caseItemWidth < LARGE_RESPONSIVE_THRESHOLD) {
        var $responsiveButtons = $('.js-responsive-handle-button', $caseItem);
        var $relatedTaskResponsiveButton = $('.js-related-task-column-responsive-button', $caseItem);
        var $historyResponsiveButton = $('.js-history-column-responsive-button');

        var $itemColumns = $('.js-case-details-item', $caseItem);
        var $historyColumn = $('.js-history-column', $caseItem);
        var $documentColumn = $('.js-document-column', $caseItem);

        $itemColumns.show().css('opacity', 1);
        $relatedTaskResponsiveButton.hide();
        $hiddenColumn = $('.js-case-details-item:not(.' + replacedClass + ')', $caseItem).slice(-2);
        $historyResponsiveButton.show();
        $hiddenColumn.hide();
        $responsiveButtons.removeClass(isSelectedClass);
        $historyColumn.addClass(replacedClass);
        responsiveButton($('.' + replacedClass, $caseItem), $caseItem).addClass(isSelectedClass);
      }

      if ($caseItemWidth < MEDIUM_RESPONSIVE_THRESHOLD) {
        var $itemColumns = $('.js-case-details-item', $caseItem);
        var $historyColumn = $('.js-history-column', $caseItem);
        var $relatedTaskColumn = $('.js-related-task-column', $caseItem);

        var $responsiveButtons = $('.js-responsive-handle-button', $caseItem);
        var $relatedTaskResponsiveButton = $('.js-related-task-column-responsive-button', $caseItem);
        var $historyResponsiveButton = $('.js-history-column-responsive-button');

        $responsiveHandleContainer.show();

        $hiddenColumns = $('.js-case-details-item:not(.' + replacedClass + ')', $caseItem).slice(-3);
        $relatedTaskResponsiveButton.show();

        $hiddenColumns.hide();
        $responsiveButtons.removeClass(isSelectedClass);
        $relatedTaskColumn.addClass(replacedClass);
        responsiveButton($('.' + replacedClass, $caseItem), $caseItem).addClass(isSelectedClass);

        if ($historyColumn.css('display') === 'none' && $historyResponsiveButton.css('display') === 'none') {
          $historyResponsiveButton.show();
        }
      }
    }
  }
  
  this.hideListIfEmptyCase = function(emptyRow) {
    if(emptyRow) {
      $header = $('.js-case-widget-column-header');
      $list = $('.process-history-list');
      $header.hide();
      $list.hide();
    }
  }
  
  function caseItem() {
    $caseItem = $('.show-case-details-mode');
    return $caseItem;
  }

  function responsiveButton($itemColumn, $caseItem) {
    var theClass = $itemColumn.attr('class').match(/js[\w-]*[\w-]column\b/);
    return $('.' + theClass + '-responsive-button', $caseItem);
  }
}

function processHistory() {
  this.setup = function() {
    var caseWidget = new CaseWidget();
    caseWidget.setupHeader();
    caseWidget.setupCaseBody();
  }
}