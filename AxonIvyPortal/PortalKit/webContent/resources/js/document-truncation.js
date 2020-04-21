var ellipsis = '...';
var documentNameHiddenClass = 'js-hidden-document-name';
var documentNameClass = 'js-document-name';

function DocumentTruncation() {
  return {

    truncate : function() {
      var $caseItem = caseItem();
      var $taskItem = taskItem();

      // Check Document item component is included inside Case/Task detail
      if ($caseItem.length > 0 || $taskItem.length > 0) {
        if ($('.js-case-details').length === 0 && $('.js-task-details').length === 0) {
          // Detail page is close, don't need to truncate document
          return;
        }
        // If detail page includes more item columns, need to check on
        // responsive handle button component
        var $itemColumns;
        var $responsiveButtons;
        var $selectedItem = $taskItem;

        if ($caseItem.length > 0) {
          $selectedItem = $caseItem;
          $itemColumns = $('.js-case-details-item', $selectedItem);
        } else {
          $itemColumns = $('.js-task-details-item', $selectedItem);
        }
        $responsiveButtons = $('.js-responsive-handle-button', $selectedItem);

        if ($itemColumns.length > 2 && $responsiveButtons.length > 0) {
          var isDisplayMainMenu = $('.js-left-sidebar').hasClass('in');
          var isDisplaySecondLevelMenu = $('#second-level-menu').hasClass('on') && !$('#second-level-menu').hasClass('hide');
          var isDocResponsiveButtonClicked = $('.js-document-column-responsive-button', $selectedItem).hasClass('is-clicked');
          if (viewPort.isSmallScreen() && isDisplayMainMenu && isDisplaySecondLevelMenu && !isDocResponsiveButtonClicked) {
            // 2 menus is open and DocResponsiveButtonClicked is not clicked,
            // don't need truncate document
            return;
          }

          // Truncate document name
          truncateText(isDocResponsiveButtonClicked);
        } else {
          // Detail page has more spaces, can do truncate text without
          // additional check
          // Truncate document name
          truncateText();
        }
      } else {
        // Document item component is stand-alone
        truncateText();
      }
    },
  }
}

function truncateText(needRestoreOriginText) {
  $('.js-download-command').each(function() {
    if (needRestoreOriginText) {
      // If ResponsiveButton of Document item is clicked, restore document name
      restoreOriginalText($(this));
    }

    var documentHeight = $(this).outerHeight();
    var containerHeight = $(this).parent().outerHeight();
    var chars = 20;

    while (documentHeight > containerHeight) {
      var $documentNameComponent = $(this).children('.' + documentNameClass);
      var documentName = $documentNameComponent.text();

      // Document name is splitted to 2 parts: start and end part. Also include
      // 3 dots between 2 parts
      // documentChars is number of maximum chars in a part
      // documentChars includes total of document name chars and 3 dots between
      var documentChars = Math.floor(documentName.length / 2) - 3;

      chars = chars > documentChars ? documentChars : chars;
      $documentNameComponent.text(documentName.slice(0, chars - 3) + ellipsis + documentName.slice(-chars));
      documentHeight = $(this).outerHeight();
      chars--;
      if (chars <= 1 && documentHeight > containerHeight) {
        // To keep performance, after several times to try truncate, this mean
        // no more spaces for this component
        documentHeight = containerHeight;
        $documentNameComponent.text(documentName.slice(0, chars) + ellipsis);
      }
    }
  });
}

function restoreOriginalText(parentComponent) {
  var documentNameHiddenValue = parentComponent.children('.' + documentNameHiddenClass).text();
  if (documentNameHiddenValue.length !== 0) {
    parentComponent.children('.' + documentNameClass).text(documentNameHiddenValue);
  }
}

function caseItem() {
  var $caseItem = $('.show-case-details-mode').has('.js-case-details:not(.opened)');
  if ($caseItem.length === 0) {
    $caseItem = $('.show-case-details-mode');
  }
  return $caseItem;
}

function taskItem() {
  var $taskItem = $('.show-task-details-mode').has('.js-task-details:not(.opened)');
  if ($taskItem.length === 0) {
    $taskItem = $('.show-task-details-mode');
  }
  return $taskItem;
}

$(document).ready(function() {
  documentTruncation = new DocumentTruncation();
  documentTruncation.truncate();
});

$(".js-document-column-responsive-button").on("click", function() {
  documentTruncation = new DocumentTruncation();
  documentTruncation.truncate();
});

$(window).resize(function() {
  var documentDisplay = $('.js-document-column').css('display');
  if (documentDisplay !== undefined && documentDisplay.localeCompare('none') != 0) {
    documentTruncation = new DocumentTruncation();
    documentTruncation.truncate();
  }
});
