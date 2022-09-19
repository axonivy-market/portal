var grids;
var originalGridstackHeight = 0;
loadGrid();

function loadGrid() {
  grids = GridStack.initAll({
    column: 12,
    cellHeight: 100,
    resizable: {
      handles: 'e, se, s, sw, w'
    }
  });

  grids.forEach(function (grid, i) {
    grid.on('change', function () {
      var serializedData = [];
      grid.engine.nodes.forEach(function (node) {
        serializedData.push({
          id: node.id,
          x: node.x,
          y: node.y,
          w: node.w,
          h: node.h
        });
      });
      let isReadOnlyMode = false;
      let dashboardViewModeInput = $("input[id$='dashboard-view-mode']");
      if (dashboardViewModeInput.length > 0) {
        isReadOnlyMode = dashboardViewModeInput.val() === 'true';
      }
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

function removeWidgetContent(widgetId) {
  var removeWidgetId = '.grid-stack-item[gs-id="' + widgetId + '"]';
  $(removeWidgetId).remove();
  updateDashboardWidget();
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

  var isSafari = isSafariBrowser();
  if (isSafari) {
    $(widget.get(0)).parent().addClass('expand-fullscreen');
    $(widget.get(0)).closest('.js-dashboard__body').addClass('expand-fullscreen');
    $(widget.get(0)).closest('.js-layout-content').addClass('expand-fullscreen');
  }
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
  if (displayMode === 'IMAGE_MODE') {
    setupImageProcessWidget();
  }
  else if (displayMode === 'FULL_MODE') {
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
    let $processHeader = $(this).find('.process-grid__header');
    let $processIcon = $(this).find("i#icon");
    let $processName = $(this).find(".process-name");
    let $processCategory = $(this).find('.rounded-button.process-category');
    let $processNameText = $processName.find(".process-grid-view-name");
    let $processDescription = $(this).find(".process-description");
    let $moreInformation = $(this).find(".process-more-info-link");
    removeStyle($processIcon);
    removeStyle($processName);
    removeStyle($processCategory);
    removeStyle($processDescription);
    removeStyle($moreInformation);
    removeStyle($processHeader);
    removeStyle($processNameText);
    let availableHeightForWidget = getAvailableHeightOfWidget($(this));

    setupProcessWidgetDescription($processDescription, availableHeightForWidget);
    availableHeightForWidget = availableHeightForWidget - $processDescription.outerHeight(true);
    if (availableHeightForWidget < 0) {
      $processIcon.css('font-size', '3.5rem');
      availableHeightForWidget = getAvailableHeightOfWidget($(this));
    }
    if (availableHeightForWidget < 0) {
      $processNameText.css('font-size', '1.2rem');
      availableHeightForWidget = getAvailableHeightOfWidget($(this));
    }
    if (availableHeightForWidget <= 0) {
      $moreInformation.hide();
      availableHeightForWidget = getAvailableHeightOfWidget($(this));
    }
    if (availableHeightForWidget <= 0) {
      $processDescription.hide();
      availableHeightForWidget = getAvailableHeightOfWidget($(this));
    }
    if (availableHeightForWidget <= 0) {
      $processCategory.hide();
      $processHeader.css({'display': 'flex', 'padding-right': '2rem', 'padding-top': '0', 'text-align': 'left'});
      setLineClamp($processNameText, 3);
      removeStyle($moreInformation);
      availableHeightForWidget = getAvailableHeightOfWidget($(this));
    }
    if (availableHeightForWidget <= 0) {
      setLineClamp($processNameText, 2);
      availableHeightForWidget = getAvailableHeightOfWidget($(this));
      if (availableHeightForWidget < 0) {
        $moreInformation.hide();
      }
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