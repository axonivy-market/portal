function CaseWidget(outerPanelId) {
  this.setUpScrollbar = function() {
    var childElements = $('.js-case-item');
    if (childElements.length > 0) {
      var container = $('.js-case-list > .ui-datascroller-content');
      var mainAreaPanel = $('#' + outerPanelId);
      var caseWidgetHeaderContainer = $('.widget-header');
      var announcementMessageContainer = $('.js-announcement-message');
      var caseWidgetSortMenuContainer = $('.js-case-widget-column-header');
      var caseWidgetFilterContainer = $('.js-filter-container');
      var error = 5;
      var globalSearchInput = $('.js-global-search');
      var globalSearchTabHeader = $('.ui-tabs-nav');
      if (globalSearchTabHeader.length > 0) {
        error = 55; // included margin, padding in search page
      }

      var availableHeight = mainAreaPanel.outerHeight()||0 - caseWidgetHeaderContainer.outerHeight(true)||0
          - caseWidgetSortMenuContainer.outerHeight(true)||0 - caseWidgetFilterContainer.outerHeight(true)||0
          - globalSearchInput.outerHeight(true)||0 - globalSearchTabHeader.outerHeight(true)||0
          - announcementMessageContainer.outerHeight(true)||0 - error;

      if (!!availableHeight) {
        container.height(availableHeight);
      }
    }
  };

  this.setupHeader = function() {
    var caseListToolKit = CaseListToolKit();
    caseListToolKit.setupHeader();
  }
}

function CaseListToolKit() {
  function hideColumnInMediumScreen($header, $cell) {
    $header.addClass("hidden-md");
    $cell.addClass("hidden-md");
  }

  function displayColumnInMediumScreen($header, $cell) {
    $header.removeClass("hidden-md");
    $cell.removeClass("hidden-md");
  }

  function hideColumnInSmallScreen($header, $cell) {
    $header.addClass("hidden-sm");
    $cell.addClass("hidden-sm");
  }

  function displayColumnInSmallScreen($header, $cell) {
    $header.removeClass("hidden-sm");
    $cell.removeClass("hidden-sm");
  }
  
  return {
    setupHeader : function() {
      var caseSortMenu = $('.js-case-widget-column-header');
      var caseEntry = $('.js-case-item-header').first();
      var noEntry = caseEntry.length == 0;
      if (noEntry) {
        $(caseSortMenu).hide();
      } else {
        $(caseSortMenu).show();
      }
      
      $.each(caseSortMenu.children('a'), function(i, header) {
        var cell = $(caseEntry).children().get(i);
        $(header).outerWidth($(cell).outerWidth());
      });
     
      $.each(caseSortMenu.children('.js-unsortable-header-cell'), function(i, header) {
          var cell = $(caseEntry).children().get(i);
          $(header).outerWidth($(cell).outerWidth());
      });
    },
    
    responsiveInLargeScreen : function(){
      this.setupHeader();
    },
    
    responsiveInMediumScreen : function(){
      var $mainMenu = $('.js-left-sidebar');
      var $secondLevelMenu = $('#second-level-menu');
      var $creatorColumnHeader = $('.js-creator-column-header');
      var $creatorCell = $('.js-creator-cell');

      if ($mainMenu.hasClass('in') && $secondLevelMenu.hasClass('on')) {
        hideColumnInMediumScreen($creatorColumnHeader, $creatorCell);
      } else {
        displayColumnInMediumScreen($creatorColumnHeader, $creatorCell);
      }
      this.setupHeader();
    },
  
    responsiveInSmallScreen : function() {
      var $mainMenu = $('.js-left-sidebar');
      var $secondLevelMenu = $('#second-level-menu');
      var $idColumnHeader = $('.js-id-column-header');
      var $idCell = $('.js-id-cell');
      var $creatorColumnHeader = $('.js-creator-column-header');
      var $creatorCell = $('.js-creator-cell');
      var $createColumnHeader = $('.js-create-column-header');
      var $createCell = $('.js-create-cell');
      var $expiryColumnHeader = $('.js-expiry-column-header');
      var $expiryCell = $('.js-finish-cell');
      

      if ($mainMenu.hasClass('in') && $secondLevelMenu.hasClass('on')) {
        hideColumnInSmallScreen($creatorColumnHeader, $creatorCell);
        hideColumnInSmallScreen($idColumnHeader, $idCell);
        hideColumnInSmallScreen($createColumnHeader, $createCell);
        hideColumnInSmallScreen($expiryColumnHeader, $expiryCell);
      } else if ($mainMenu.hasClass('in') || $secondLevelMenu.hasClass('on')) {
        hideColumnInSmallScreen($creatorColumnHeader, $creatorCell);
        hideColumnInSmallScreen($idColumnHeader, $idCell);
        displayColumnInSmallScreen($createColumnHeader, $createCell);
        displayColumnInSmallScreen($expiryColumnHeader, $expiryCell);
      } else {
    	hideColumnInSmallScreen($creatorColumnHeader, $creatorCell);
        displayColumnInSmallScreen($idColumnHeader, $idCell);
        displayColumnInSmallScreen($createColumnHeader, $createCell);
        displayColumnInSmallScreen($expiryColumnHeader, $expiryCell);
      }
      this.setupHeader();
      var caseWidget = new CaseWidget("main-area-panel");
      caseWidget.setUpScrollbar();
    },
    
    responsive : function() {
      if (viewPort.isMediumScreen()) {
        this.responsiveInMediumScreen();
      } else if (viewPort.isSmallScreen()) {
        this.responsiveInSmallScreen();
      } else {
        this.setupHeader();
      }
    }
  }
}