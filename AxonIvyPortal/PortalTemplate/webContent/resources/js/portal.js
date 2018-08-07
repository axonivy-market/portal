var mainMenuMode = "collapsed";
var FIRST_LEVEL_MENU_MODE = 'portal-first-level-menu-mode';
var SECOND_LEVEL_MENU_MODE = 'portal-second-level-menu-mode';
var ACTIVE_MENU_ITEM = 'portal-active-menu-item';

var Portal = {
		init: function(responsiveToolkit){
		  if ($('form.login-form').size() > 0) {
		    return;
		  }
			updateMainMenuMode();
			var previousActiveMenuItem = localStorage.getItem('active-menu-item');
			updateActivedMenuItem();
			
			if (mainMenuMode === "expanded") {
				$('.js-left-sidebar').addClass('in').toggleClass('left-sidebar-animation');
				$('.js-left-sidebar-toggle').addClass('in').toggleClass('left-sidebar-animation');
				$('.left-sidebar-sub-menu-name').toggleClass('left-sidebar-animation');
				$('.left-sidebar-menu-name').toggleClass('left-sidebar-animation');
				$('.left-sidebar-menu-icon').toggleClass('left-sidebar-animation');
				setTimeout(function() {
					$('.js-left-sidebar').toggleClass('left-sidebar-animation');
					$('.js-left-sidebar-toggle').toggleClass('left-sidebar-animation');
					$('.left-sidebar-sub-menu-name').toggleClass('left-sidebar-animation');
					$('.left-sidebar-menu-name').toggleClass('left-sidebar-animation');
					$('.left-sidebar-menu-icon').toggleClass('left-sidebar-animation');
					;
				}, 1);
			}

			var selectedSubMenuItemClass = $('.ivy-active');

			if (previousActiveMenuItem == getMenuUniqueClassFrom(getActiveMenuItemClassList())) {
				var animatedElement = $('.left-sidebar-animation');
				animatedElement.toggleClass('left-sidebar-animation');
				setTimeout(function() {
					animatedElement.toggleClass('left-sidebar-animation');
				}, 2);
			}

			if (previousActiveMenuItem != null && previousActiveMenuItem != getMenuUniqueClassFrom(getActiveMenuItemClassList())) {
				selectedSubMenuItemClass.toggleClass('expand').toggleClass('left-sidebar-animation');
				animatePreviousActiveMenuItem('.' + previousActiveMenuItem);
			} else {
				selectedSubMenuItemClass.toggleClass('expand');
			}

			setTimeout(function() {
				$('.left-sidebar-menu-header').toggleClass('left-sidebar-animation');
			}, 3);

			$('.js-left-sidebar').colourBrightness();
			MainMenu.init(responsiveToolkit);
			SecondLevelMenu.init(responsiveToolkit);
			expandSecondLevelMenuItem();

			// Update screen when window size is changed
			$(window).resize(function() {
				responsiveToolkit.updateLayoutWithoutAnimation();
			});
			$('.second-level-menu-body .ui-treetable-selectable-node').click(function() {
				storeMenuState(this)
			});
			
			responsiveToolkit.updateLayoutWithoutAnimation();
			$('#main-area-panel').removeClass('u-invisibility');
			$('#left-menu').removeClass('u-invisibility');
		},	
}

function updateMainMenuMode() {
	mainMenuMode = localStorage.getItem(FIRST_LEVEL_MENU_MODE);
}

function toggleMainMenuMode() {
	updateMainMenuMode();
	localStorage.setItem(FIRST_LEVEL_MENU_MODE, getReversedState(mainMenuMode));
}

function getReversedState(state) {
	if (state == "expanded") {
		return "collapsed";
	} else {
		return "expanded";
	}
}

/* Portal's main menu */
var MainMenu = {
    urlToFirstLevelMenu : [["Processes.xhtml", ["Processes", "Prozesse", "Processus"]], ["PortalTasks.xhtml", ["Tasks", "Aufgaben", "Tâches"]],
      ["PortalCases.xhtml", ["Cases", "Vorgänge", "Dossiers"]], ["PortalDashBoard.xhtml", ["Statistics","Statistiken", "Statistiques"]]],

	init : function(responsiveToolkit) {
		this.highlightFirstLevelMenu();
		this.responsiveToolkit = responsiveToolkit;
		this.$mainMenu = $('.js-left-sidebar');
		this.$mainMenuToggle = $('.js-left-sidebar-toggle');
		this.bindEvents();
	},

	showMainMenu : function() {
		this.$mainMenuToggle.toggleClass('in');
		this.$mainMenu.toggleClass('in');
	},

	bindEvents : function() {
		var $this = this;
		this.$mainMenuToggle.on("click", function() {
			$this.showMainMenu();
			// Toggle menu state
			toggleMainMenuMode();
			// Update layout to corresponding viewport here
			$this.responsiveToolkit.updateLayoutWithAnimation();
		});
	},

	highlightFirstLevelMenu : function() {
    var firstLevelMenu = MainMenu.getFirstLevelMenuBasedOnPageUrl();
    MainMenu.displaySecondLevelMenu(firstLevelMenu);
    var $activeFirstLevelMenu = $('div.ivy-active .left-sidebar-sub-menu-name').filter(function(index) {
      if (firstLevelMenu) {
        return (firstLevelMenu.indexOf($(this).text()) > -1);
      }
    });
    $activeFirstLevelMenu.parent().addClass('active-menu-item');
  },
  
  getFirstLevelMenuBasedOnPageUrl : function() {
    var pageUrl = window.location.pathname;
    for (var i = 0; i < MainMenu.urlToFirstLevelMenu.length; i++) {
      if (pageUrl.indexOf(MainMenu.urlToFirstLevelMenu[i][0]) > -1) {
        return MainMenu.urlToFirstLevelMenu[i][1];
      }
    }
  },
  
  displaySecondLevelMenu : function(firstLevelMenu) {
    if(firstLevelMenu == null || "Tasks" !== firstLevelMenu[0]) {
      $('.second-level-menu').addClass('hide');
    }
  },
}



/* Portal's second level menu */
var SecondLevelMenu = {
	init : function(responsiveToolkit) {
		this.responsiveToolkit = responsiveToolkit;
		this.secondLevelMenu = $("#second-level-menu");
		this.secondLevelMenuHeader = this.secondLevelMenu.find("a.second-level-menu-header");
		this.secondLevelMenuBody = this.secondLevelMenu.find("div.second-level-menu-body");
		this.bindEvents();
		if (localStorage.getItem(SECOND_LEVEL_MENU_MODE) === 'expanded') {
			this.secondLevelMenu.addClass("on");
			this.responsiveToolkit.updateLayoutWithoutAnimation();
		}
		this.setupScrollbar();
	},

	bindEvents : function() {
		var $this = this;
		this.secondLevelMenuHeader.on("click", function() {
			if ($this.secondLevelMenu.hasClass('on')) {
				localStorage.setItem(SECOND_LEVEL_MENU_MODE, 'collapsed');
			} else {
				localStorage.setItem(SECOND_LEVEL_MENU_MODE, 'expanded');
			}
			$this.secondLevelMenu.toggleClass("on");
			$this.responsiveToolkit.updateLayoutWithAnimation();
		});
	},
	
    setupScrollbar : function() {
	  	var container = $('.second-level-menu');
	  	var container2 = $('.second-level-menu-body');
	  	$('.ui-treetable-scrollable-body').height(container.outerHeight() - (container2.outerHeight(true) - container2.outerHeight()));
    }
}

function storeMenuState(menuItem) {
  var activeMenuPath = '';
  var appName = $('a.ivy-active').find('> span').text();
  
  var feature = $('a.active-menu-item .left-sidebar-sub-menu-name').text();
  
  var firstLevelMenuPath = appName + '/' + feature + '/';
  activeMenuPath = firstLevelMenuPath + getCategoryClass(menuItem);
  var storageData = new Object();
  storageData.currentPath = activeMenuPath;
  storageData.isMenuItemExpanding = $(menuItem).find('.ui-treetable-toggler').hasClass('ui-icon-triangle-1-s');
  storeSecondLevelMenuStateToInputElement(storageData);
}

function storeFirstLevelMenuStateToInputElement(menuState) {
  $('[id$=first-level-menu-state-info]').val(JSON.stringify(menuState));
}

function storeSecondLevelMenuStateToInputElement(menuState) {
  $('[id$=task-menu-state-info]').val(JSON.stringify(menuState));
}


function animatePreviousActiveMenuItem(previousActiveMenuItem) {
	$(previousActiveMenuItem).toggleClass('expand').toggleClass('ivy-active');
	setTimeout(function() {
		$(previousActiveMenuItem).toggleClass('left-sidebar-animation').toggleClass('expand').toggleClass('ivy-active');
		;
	}, 1);
}

function updateActivedMenuItem() {
	var activeMenuItemClassList = getActiveMenuItemClassList();
	localStorage.setItem('active-menu-item', getMenuUniqueClassFrom(getActiveMenuItemClassList()));
}

function getMenuUniqueClassFrom(menuClassList) {
	var result = '';
	$.each(menuClassList, function(index, value) {
		if (value.indexOf('js-left-side-bar-item') > -1) {
			result = menuClassList[index];
		}
	});
	return result;
}

function getActiveMenuItemClassList() {
	return $('.ivy-active').attr('class').split(" ");
}

function saveMenuState(menuItem) {
  var firstLevelMenu = $(menuItem).find('.left-sidebar-sub-menu-name').text();
  var appName = $(menuItem).parentsUntil($('form'), '.left-sidebar-sub-menu').prev().find('.left-sidebar-menu-name')
      .text();
  storeMenuStateToInputElementWith(appName, firstLevelMenu, '');
}

function storeMenuStateToInputElementWith(appName, feature, secondLevelPath) {
  var menuState = new Object();
  var currentMenuPath = appName;
  if (feature !== '') {
    currentMenuPath += '/' + feature;
    if (secondLevelPath !== '') {
      currentMenuPath += '/' + secondLevelPath;      
    }
  }
  menuState.currentPath = currentMenuPath;
  if (secondLevelPath == '') {
    storeFirstLevelMenuStateToInputElement(menuState);
  } else {
    storeSecondLevelMenuStateToInputElement(menuState);
  }
  
}

function expandSecondLevelMenuItem() {
	var menuData = prepareMenuData();
	var menuItemElements = $('.second-level-menu-body tbody tr').get();
	$.each(menuItemElements, function(index, item) {
		if (menuData != "") {
			var contains = false;
			if (menuData.currentPath.startsWith(getCategoryClass(item))) {
				var contains = true;
			}

			if (!contains) {
				$(item).find("span.ui-treetable-toggler").click();
			} else {
				$(item).toggleClass('on');
				if (getCategoryClass(item) == (menuData.currentPath)) {
					if (menuData.isMenuItemExpanding) {
						$(item).find("span.ui-treetable-toggler").click();
						$(item).toggleClass('collapsed');
					}
					$(item).toggleClass('js-active-second-level-menu-item');
				}
			}
		} else {
			$(item).find("span.ui-treetable-toggler").click();
		}
	});
}

function prepareMenuData() {
	var menuData = getMenuStateInfo();
	if (menuData != "") {
		menuData.currentPath = removeAppNameAndMenuTypeFromPath(menuData.currentPath);
	}
	return menuData;
}

function getMenuStateInfo() {
  var menuData = $('*[id$=output-menu-state-info]').text();
  if (menuData != "") {
    menuData = JSON.parse(menuData);
  }
  return menuData;
}

function removeAppNameAndMenuTypeFromPath(menuItemPath) {
  if (menuItemPath.split('/').length <= 2) {
    return '';
  }
	var appNameAndMenuType = menuItemPath.split('/', 2);
	var result = menuItemPath.replace(appNameAndMenuType[0] + '/', '').replace(appNameAndMenuType[1] + '/', '');
	return result;
}

function getCategoryClass(element) {
	var elementClass = $(element).attr("class");
	var category = /(All_Tasks|My_Tasks|Group_Tasks|Unassigned_Tasks)[^\s\\]*\b/.exec(elementClass);
	if (category != null && category.length > 0) {
		return category[0];
	}
	return "";
}

if (!String.prototype.startsWith) {
	String.prototype.startsWith = function(searchString, position) {
		position = position || 0;
		return this.indexOf(searchString, position) === position;
	};
}
