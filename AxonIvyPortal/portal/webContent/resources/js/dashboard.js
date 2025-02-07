var grids;
var originalGridstackHeight = 0;

const defaultNumberOfCells = 12;
const defaultCellHeight = 100;
const additionalHeightForResizeHandler = 15;

// Store the initial window height to detect changes
let windowHeight = window.innerHeight;

function loadGrid(isResponsive) {

  // Set default height for cells
  var gridCellHeight = defaultCellHeight;

  if (isResponsive) {
	var dashboardModificationPageHeaderHeight = 0;
    const announcementHeight = ($('.js-announcement-message').outerHeight(true) || 0);
    const shareDashboardLinkHeight = ($('.js-share-dashboard-link').outerHeight(true) || 0);

    if ($('.js-dashboard__header').length > 0) {
        dashboardModificationPageHeaderHeight = ($('.js-dashboard__header').outerHeight(true) || 0) + additionalHeightForResizeHandler;
    }

    // calculate available height then divide by default number of cells to get height value for each cell
    gridCellHeight = (PortalLayout.getAvailableHeight() - announcementHeight
        - shareDashboardLinkHeight - dashboardModificationPageHeaderHeight) / defaultNumberOfCells;

    // If the calculated cell height is less than or equals to 0, use the default cell height instead
    gridCellHeight = gridCellHeight > 0 ? gridCellHeight : defaultCellHeight;
  }

  grids = GridStack.initAll({
    column: defaultNumberOfCells,
    cellHeight: gridCellHeight,
    resizable: {
      handles: 'e, se, s, sw, w'
    }
  });
  let mapGridItems = new Map();
  grids.forEach(function (grid) {
    grid.batchUpdate();
    let gridItems = grid.getGridItems();
    grid.removeAll(true);
    let h=0;
    gridItems.sort((a, b) => $(a).attr('default-y') - $(b).attr('default-y') || $(a).attr('default-x') - $(b).attr('default-x'))
      .forEach(e => {
        let el = $(e);
        el.attr('gs-y', h)
        h += parseInt(el.attr('gs-h'));
        const data = {
           id: el.attr('gs-id'),
           x: el.attr('gs-x'),
           y: el.attr('gs-y'),
           w: el.attr('gs-w'),
           h: el.attr('gs-h')
         };
         mapGridItems.set(data.id, data)
      });

    gridItems.forEach(ele => grid.addWidget(ele));
    grid.commit();

  })

  grids.forEach(function (grid, i) {
    grid.on('change', function (e) {
      let serializedData = [];
      let isReadOnlyMode = false;
      let dashboardViewModeInput = $("input[id$='dashboard-view-mode']");
      if (dashboardViewModeInput.length > 0) {
        isReadOnlyMode = dashboardViewModeInput.val() === 'true';
      }
      grid.engine.nodes.forEach(function (node) {
        let gridItem = mapGridItems.get(node.id);
        if (gridItem !== undefined && isReadOnlyMode) {
          node.x = gridItem.x;
          node.y = gridItem.y;
          node.w = gridItem.w;
          node.h = gridItem.h;
        }
        serializedData.push({
          id: node.id,
          x: node.x,
          y: node.y,
          w: node.w,
          h: node.h
        });
      });

      if (grid.opts.minWidth < grid.el.clientWidth && !isReadOnlyMode) {
        saveConfigurationCommand([{
          name: 'nodes',
          value: JSON.stringify(serializedData, null, '')
        }]);
      }
    });

    grid.on('resize resizestop', function (event, element) {
      var elementId = element.gridstackNode.id;
      var descriptionElement = $("[gs-id='" + elementId + "']").find('.js-image-widget-mode .js-process-description');
      if (descriptionElement.length > 0) {
        setupImageProcessWidgetDescription(descriptionElement);
      }
      udateResizableTablesWhenResizeWidget();
      setupGridProcessWidget();
    });

    // Disable all pointer events of iframes when edit widgets
    var dashboardBody = $('div.js-dashboard__body');
    if (dashboardBody.hasClass('readonly')) {
      dashboardBody.removeClass('dashboard__body__iframe--disabled');
    } else {
      dashboardBody.addClass('dashboard__body__iframe--disabled');
    }
  });
}

function getPortalGridsCurrentRow(widgetType) {
  let currentRowNumber = 0;
  let dashboard = $("#dashboard-body");
  if (dashboard.length > 0) {
    let gridStack = dashboard.find("#grid-stack");
    if (gridStack.length > 0) {
      currentRowNumber = gridStack.attr("gs-current-row");
    }
  }
  addNewDashboardWidgetCommand([{
    name: 'portalGridsCurrentRow',
    value: currentRowNumber
  }]);
}

function scrollToWidget(widgetId) {
  let widgetClass = ".js-dashboard-widget-" + widgetId;
  let $container = $("#dashboard-body");
  let $scrollTo = $(widgetClass);
  $container.scrollTop(
    $scrollTo.offset().top - $container.offset().top + $container.scrollTop() - 10
  );
}

function handleFilterCategoryHeight(e) {
  var filterTab = $(document.getElementById(e.id)).find("[id$=':filter-tab']");
  if (filterTab.length > 0 && filterTab.get(0).ariaHidden == "false") {
    var categoryInfo = filterTab.find(".js-category-filter-info");
    if (categoryInfo.length > 0 && categoryInfo.get(0).offsetHeight > 160) {
      $(categoryInfo).toggleClass("minimize-content");
      filterTab.find(".js-category-toggle").toggleClass("si-add-circle").toggleClass("si-subtract-circle");
    }
  }
}

function toggleCategoryInfo(e) {
  var categoryToggle = $(e);
  var categoryInfo = categoryToggle.parent().find(".js-category-filter-info");
  categoryInfo.toggleClass("minimize-content");
  categoryToggle.toggleClass("si-add-circle").toggleClass("si-subtract-circle");
}

function disabledResetDashboardActions() {
  PF('reset-dashboard-button').disable();
  $('.cancel-reset-dashboard-link').addClass('ui-state-disabled');
}

function DashboardToolKit() {
  return {
    setupScrollbar: function () {
      setupScrollbar();
    },

    responsive: function () {
      this.setupScrollbar();
    }
  };
}

function setupScrollbar() {
  var viewMode = $(".js-dashboard__wrapper.js-view-mode");
  if (viewMode.length > 0) {
    return;
  }
  var container = $('.js-dashboard__body');
  var gridstackItems = $('.grid-stack-item');
  if (gridstackItems.length > 0) {
    container.removeAttr('style');
    var $dashboardHeader = $(".js-dashboard__header");
    var headerContainer = ($dashboardHeader.outerHeight(true) || 0);
    var announcementMessageContainer = ($('.js-announcement-message').outerHeight(true) || 0);
    var mainScreenHeight = PortalLayout.getAvailableHeight();

    var availableHeight = mainScreenHeight - headerContainer - announcementMessageContainer;
    if (!!availableHeight) {
      var $dashboardWrapper = $(".js-dashboard__wrapper");
      if (container.outerHeight() > availableHeight && !isMobileDevices()) {
        PortalLayout.removeLayoutContentPaddingBottom();
        $dashboardWrapper.css('margin-right', '-' + PortalLayout.getPaddingRightLayoutContent());
        $dashboardHeader.css('padding-right', PortalLayout.getPaddingRightLayoutContent());
        container.css('padding-right', PortalLayout.getPaddingRightLayoutContent());
      } else {
        $dashboardWrapper.removeAttr('style');
        $dashboardHeader.removeAttr('style');
        PortalLayout.removeJsStyleOnLayoutContent();
      }
      availableHeight = availableHeight - PortalLayout.getYPaddingLayoutContent();
      container.outerHeight(availableHeight);
    }
  }
  container.removeClass('u-invisibility');
}

function isSafariBrowser() {
  return /iPad|iPhone|iPod/.test(navigator.platform);
}

function expandFullscreen(index, widgetId) {
  var widget = $('div.grid-stack-item[gs-id = "' + widgetId + '"]');
  widget.addClass('expand-fullscreen');
  widget.get(0).innerWidth = window.innerWidth;
  widget.get(0).outerWidth = window.outerWidth;
  widget.get(0).innerHeight = window.innerHeight;
  widget.get(0).outerHeight = window.outerHeight;

  originalGridstackHeight = $(widget.get(0)).parent('.grid-stack').height();
  $(widget.get(0)).parent('.grid-stack').height($(widget.get(0)).height());

  // Hide dashboard overlay panel is opening
  hideAllDashboardOverlayPanels();
  resizeTableBody();
  var isSafari = isSafariBrowser();
  if (isSafari) {
    $(widget.get(0)).parent().addClass('expand-fullscreen');
    $(widget.get(0)).closest('.js-dashboard__body').addClass('expand-fullscreen');
    $(widget.get(0)).closest('.js-layout-content').addClass('expand-fullscreen');
  }
}

function resizeTableBody() {
  const resizeObserver = new ResizeObserver(entries => {
    requestAnimationFrame(() => {
      entries.forEach(entry => {
        let tableBody = $(entry.target);
        let parentHeight = tableBody.parents('.grid-stack-item-content.card.dashboard-card').height();

        if (!window.matchMedia("(max-width: 767px)").matches) {
          if (tableBody.height() !== parentHeight - 100) {
            tableBody.height(parentHeight - 100);
          }
        } else {
          tableBody.height(parentHeight * 0.9);
        }

        const widgetName = tableBody.parents('.grid-stack-item').find('.js-table-widget-var').val();
        if (!widgetName) return;

        const widget = PF(widgetName);
        widget.cfg.scrollHeight = tableBody.parents('.ui-datatable-scrollable').height().toString();
        if (tableBody.parents('.js-resizing').length > 0) {
          widget.init(widget.cfg);
        }
        widget.setupScrolling();

        if (widget.headerTable.length === 1) {
          $(widget.headerTable[0]).find('th.ui-state-focus').removeClass('ui-state-focus');
        }
      });

    });
  });
  document.querySelectorAll('.ui-datatable-scrollable-body').forEach(sb => {
    setTimeout(() => resizeObserver.observe(sb), 50);
  });
}

function collapseFullscreen(index, widgetId) {
  var widget = $('div.grid-stack-item[gs-id = "' + widgetId + '"]');
  widget.removeClass('expand-fullscreen');

  var isSafari = isSafariBrowser();

  if (isSafari) {
    $(widget.get(0)).parent().removeClass('expand-fullscreen');
    $(widget.get(0)).closest('.js-dashboard__body').removeClass('expand-fullscreen');
    $(widget.get(0)).closest('.js-layout-content').removeClass('expand-fullscreen');
  }

  $(widget.get(0)).parent('.grid-stack').height(originalGridstackHeight);
  // Hide dashboard overlay panel is opening
  hideAllDashboardOverlayPanels();
  resizeTableBody();
}

function loadWidgetFirstTime(loadingClass, widgetClass) {
  var loading = $('.' + loadingClass);
  if (loading.length > 0) {
    loading.addClass('u-display-none');
  }
  var widget = $('.' + widgetClass);
  if (widget.length == 0) {
    widget = $("[data-process-id='" + widgetClass + "']");
  }
  if (widget.length > 0) {
    widget.removeClass('u-display-none');
    widget.removeClass('u-invisibility');
  }
  resizeTableBody();
}

function loadCaseAndTaskWidgetFirstTime(loadingClass, widgetClass) {

  resizeTableBody();

  setTimeout(function() {
    var loading = $('.' + loadingClass);
    if (loading.length > 0) {
      loading.addClass('u-display-none');
    }
    var widget = $('.' + widgetClass);
    if (widget.length == 0) {
      widget = $("[data-process-id='" + widgetClass + "']");
    }
    if (widget.length > 0) {
      widget.removeClass('u-display-none');
      widget.removeClass('u-invisibility');
    }
    $('.js-resizing').find('table[role="grid"]').addClass('w-min');
  }, 50);
}

function initTableWidget(table) {
  if (table === undefined || table.cfg === undefined) {
    return;
  }

  setTimeout(function(){
    var $table = $(document.getElementById(table.id));
    table.cfg.scrollHeight = $table.height().toString();
    table.init(table.cfg);
  }, 500);
}

function hideAllDashboardOverlayPanels() {
  var openingOverlayPanel = $(".js-dashboard-overlay-panel");
  if (openingOverlayPanel.length > 0) {
    $.each(openingOverlayPanel, function(i, overlayPanel) {
      let hidePanelId = overlayPanel.id;
      if (hidePanelId.includes(':')) {
        let fullIdPath = overlayPanel.id.split(':');
        hidePanelId = fullIdPath[fullIdPath.length - 1];
      }

      if ($("div[id $= " + hidePanelId + "]").length > 0 && PF(hidePanelId).isVisible()) {
        PF(hidePanelId).hide();
      }
    });
  }
}

// Start Process Dashboard Widget
function respondProcessWidget(displayMode) {
 if (displayMode === 'FULL_MODE') {
    setupGridProcessWidget();
  }
}

// Setup for Image process
function setupImageProcessWidget() {
  var imageContainers = $('.js-image-widget-mode .js-image-process-item-container');
  if (imageContainers.length > 0) {
    imageContainers.each(function () {
      var imageUrl = $(this).find("img").attr("src");
      $(this).css('background-image', 'url("' + imageUrl + '")');
    });
  }

  var processDescriptions = $('.js-image-widget-mode .js-process-description');
  if (processDescriptions.length > 0) {
    processDescriptions.each(function () {
      setupImageProcessWidgetDescription($(this));
    });
  }
}

function setupImageProcessWidgetDescription(e) {
  var height = e.height();
  var descriptionContent = e.find('.js-process-item-description');
  removeStyle(descriptionContent);
  var lineHeight = parseFloat(descriptionContent.css('line-height'));
  var lineClamp = Math.floor(height / lineHeight);
  if (lineClamp == 2) lineClamp = 1;
  setLineClamp(descriptionContent, lineClamp.toString());
  if (lineClamp == 0) {
    descriptionContent.hide();
  }
}

// Setup for Full Grid process
function setupGridProcessWidget() {
  let processWidgets = $(".grid-view-form");
  if (processWidgets.length == 0) {
    return;
  }
  processWidgets.each(function () {
    let $processActionGroup = $(this).find('.process-action-group');
    let $processHeader = $(this).find('.process-grid__header');
    let $processIcon = $(this).find("i#icon");
    let $processName = $(this).find(".process-name");
    let $processCategory = $(this).find('.rounded-button.process-category');
    let $processNameText = $processName.find(".process-grid-view-name");
    let $processDescription = $(this).find(".process-description");
    let $moreInformation = $(this).find(".process-more-info-link");
    removeStyle($processActionGroup);
    removeStyle($processIcon);
    removeStyle($processName);
    removeStyle($processCategory);
    removeStyle($processDescription);
    removeStyle($moreInformation);
    removeStyle($processHeader);
    removeStyle($processNameText);

    $processHeader.css('padding-top', $processActionGroup.height() + parseInt($processActionGroup.css('padding-bottom')) + 'px');
    let availableHeightForWidget = getAvailableHeightOfWidget($(this));

    setupProcessWidgetDescription($processDescription, availableHeightForWidget);
    availableHeightForWidget = availableHeightForWidget - $processDescription.outerHeight(true);
    if (availableHeightForWidget <= 0) {
      $processIcon.css('font-size', '3.5rem');
      $processNameText.css('font-size', '1.2rem');
    } else {
      setupProcessWidgetDescription($processDescription, availableHeightForWidget);
    }
    availableHeightForWidget = getAvailableHeightOfWidget($(this));
    if (availableHeightForWidget <= 0) {
      $moreInformation.hide();
    } else {
      setupProcessWidgetDescription($processDescription, availableHeightForWidget);
    }
    availableHeightForWidget = getAvailableHeightOfWidget($(this));
    if (availableHeightForWidget <= 0) {
      $processDescription.hide();
    } else {
      removeStyle($processDescription);
      setupProcessWidgetDescription($processDescription, availableHeightForWidget);
    }
    availableHeightForWidget = getAvailableHeightOfWidget($(this));
    if (availableHeightForWidget <= 0) {
      $processCategory.hide();
      $processHeader.css({'display': 'flex', 'padding-top': '0', 'text-align': 'left'});
      if ($processActionGroup.find("button").length > 0) {
        $processHeader.css({'padding-right': '2rem'});
      }
      setLineClamp($processNameText, 3);
      removeStyle($moreInformation);
    } else {
      removeStyle($processDescription);
      setupProcessWidgetDescription($processDescription, availableHeightForWidget);
    }
    availableHeightForWidget = getAvailableHeightOfWidget($(this));
    if (availableHeightForWidget <= 0) {
      setLineClamp($processNameText, 2);
      availableHeightForWidget = getAvailableHeightOfWidget($(this));
      if (availableHeightForWidget < 0) {
        $moreInformation.hide();
      }
    } else {
      removeStyle($processDescription);
      setupProcessWidgetDescription($processDescription, availableHeightForWidget);
    }
    if ($(this).width() < $processHeader.outerWidth(true)) {
      $processName.hide();
      $processHeader.css({'padding-right': '0'});
    }
  });
}

function setupProcessWidgetDescription(processDescriptionContainer, availableHeightForWidget) {
  var descriptionText = processDescriptionContainer.find('.process-description__text');
  removeStyle(descriptionText);
  var height = processDescriptionContainer.height();
  if (availableHeightForWidget && height > availableHeightForWidget) {
    height = Math.floor(availableHeightForWidget);
  }
  var lineHeight = parseFloat(descriptionText.css('line-height'));
  var lineClamp = Math.floor(height / lineHeight);
  setLineClamp(descriptionText, lineClamp);
  if (lineClamp <= 0) {
    descriptionText.hide();
  }
}

function getAvailableHeightOfWidget(widget) {
  let $processHeader = $(widget).find(".process-grid__header");
  let $processDescription = $(widget).find(".process-description");
  const processDescriptionXSpaces = parseInt($processDescription.outerHeight(true) - $processDescription.height());

  let $processStartContainer = $(widget).find("[id^='start-button-wrap-']");
  const startProcessButtonHeight = $(widget).find("button[id$=':start-button']").outerHeight(true);
  const processStartContainerXSpaces = parseInt($processStartContainer.outerHeight(true) - $processStartContainer.height());

  return $(widget).height() - $processHeader.outerHeight(true) - startProcessButtonHeight - processDescriptionXSpaces - processStartContainerXSpaces;
}
// End Process Dashboard Widget

function setLineClamp(element, number) {
  $(element).css('-webkit-line-clamp', number.toString());
}

function removeStyle(element) {
  $(element).removeAttr('style');
}

function searchNewWidgetByNameOrDescription(input) {
  var keyword = input.value.toLowerCase();
  $('.js-widget').each(function() {
    if ($(this).find('.new-widget-dialog__item-title span').text().toLowerCase().includes(keyword)) {
      $(this).removeClass('u-hidden');
    } else {
      $(this).addClass('u-hidden');
    }
  });

  var noResult = true;
  $('.js-widget-fieldset').each(function() {
    if ($(this).find('.js-widget:not(".u-hidden")').length == 0) {
      $(this).addClass('u-hidden');
    } else {
      $(this).removeClass('u-hidden');
      noResult = false;
    }
  });

  noResult ? $('.js-no-widget').removeClass('u-hidden') : $('.js-no-widget').addClass('u-hidden');
}

function udateResizableTablesWhenResizeWidget() {
  const scrollableBody = document.querySelectorAll('.ui-datatable-scrollable-body');
  scrollableBody.forEach((sb) => {
    let tableBody = $(sb);
    if (tableBody.parents('.js-resizing').length == 0) {
      return;
    }

    let parentHeight = tableBody.parents('.grid-stack-item-content.card.dashboard-card').height();
    if (!window.matchMedia("(max-width: 767px)").matches) {
      tableBody.height(parentHeight - 100);
    } else {
      tableBody.height(parentHeight * 0.9);
    }

    const widgetName = tableBody.parents('.grid-stack-item').find('.js-table-widget-var').val();
    if (widgetName === undefined) {
      return;
    }

    // Update scrolling of the Primefaces widget
    const widget = PF(widgetName);
    if (widget) {
      widget.cfg.scrollHeight = tableBody.parents('.ui-datatable-scrollable').height().toString();
      widget.renderDeferred(widget.cfg);
    }
  });

}

function updateDashboardWhenResizeWindow(isResponsiveDashboard) {
  if (!isResponsiveDashboard) {
    return;
  }

  // resize timeout ID
  let resizeTimeout;

  window.addEventListener('resize', function () {
    clearTimeout(resizeTimeout);

    resizeTimeout = setTimeout(function () {

   // Check if the window height has changed
   if (window.innerHeight !== windowHeight) {

     // Call the remote command 'updateDashboardWidget'
     updateDashboardWidget();

     windowHeight = window.innerHeight;
      }
    }, 1000); // Delay execution by 1 sec to avoid send multiple requests
  });
}