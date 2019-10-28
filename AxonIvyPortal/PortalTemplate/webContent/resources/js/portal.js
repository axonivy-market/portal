var Portal = {
  init : function(responsiveToolkit) {
    if ($('form.login-form').length > 0) {
      return;
    }
    // Update menuitem when access page by direct link
    MainMenu.init(responsiveToolkit);
    
    // Update screen when window size is changed
    $(window).resize(function() {
      setTimeout(function() {
        responsiveToolkit.updateLayoutWithoutAnimation();
      }, 250);
    });

    responsiveToolkit.updateLayoutWithoutAnimation();
    this.updateLayoutContent();
  },
  
  // Remove u-invisibility class when DOM is pasted already
  updateLayoutContent : function() {
    $('#main-area-panel').removeClass('u-invisibility');
    $("[id$='main-navigator-container']").removeClass('u-invisibility');
  }
}

function searchIconByName(element) {
  var keyword = element.value.toLowerCase();
  var icons = $(".icon-selection-dialog-selecting-icon");
  for (i = 0; i < icons.length; i++) {
    var icon = icons[i].innerHTML;
    if (icon.indexOf(keyword) > -1 || icon.split("-").join(" ").indexOf(keyword) > -1) {
      icons[i].style.display= "";
    } else {
	  icons[i].style.display= "none";
    }
  }
}

var MainMenu = {
  urlToMenu : [["Processes.xhtml", "PROCESS"],
      ["PortalTasks.xhtml", "TASK"],
      ["TaskWidget.xhtml", "TASK"],
      ["PortalTaskDetails.xhtml", "TASK"],
      ["PortalCases.xhtml", "CASE"],
      ["CaseWidget.xhtml", "CASE"],
      ["PortalCaseDetails.xhtml", "CASE"],
      ["CaseItemDetails.xhtml", "CASE"],
      ["PortalDashBoard.xhtml", "DASHBOARD"],
      ["TaskAnalysis.xhtml", "DASHBOARD"]],

  init : function(responsiveToolkit) {
    this.highlightMenuItem();
    this.responsiveToolkit = responsiveToolkit;
  },
  
  highlightMenuItem : function() {
    var firstLevelMenu = MainMenu.getMenuBasedOnPageUrl();
    var parentActiveMenuId = MainMenu.getFirstParentMenuActive();

    var $activeMenuItem = $(".layout-menu li[id^='" + parentActiveMenuId + "'] .submenu").filter(
        function(index) {
          if (firstLevelMenu) {
            return $(this).hasClass(firstLevelMenu);
          }
        });
    $activeMenuItem.parent().addClass('active-menuitem');
  },

  getFirstParentMenuActive : function() {
    var parentId = "";
    var parentMenuActive = $(".layout-menu .active-menuitem").not(".submenu-container");
    if (parentMenuActive.length > 0) {
      parentId = parentMenuActive[0].id;
      MainMenu.getSubMenuActive();
    }
    return parentId;
  },

  getSubMenuActive : function() {
    var subMenuActive = $(".layout-menu .active-menuitem.submenu-container");

    for (var i = 0; i < subMenuActive.length; i++) {
      var item = subMenuActive.get(i);
      $(item).removeClass('active-menuitem');
    }

  },

  getMenuBasedOnPageUrl : function() {
    var pageUrl = window.location.pathname;
    for (var i = 0; i < MainMenu.urlToMenu.length; i++) {
      if (pageUrl.indexOf(MainMenu.urlToMenu[i][0]) > -1) {
        return MainMenu.urlToMenu[i][1];
      }
    }
  }
}

function handleError(xhr, renderDetail){
  if (renderDetail){
    document.getElementById('ajax-indicator:show-more').onclick = function (){
      document.getElementById('ajax-indicator:error-code').innerHTML = xhr.status;
      document.getElementById('ajax-indicator:error-text').innerHTML = xhr.statusText;
      document.getElementById('ajax-indicator:error-url').innerHTML = xhr.pfSettings.url;
      document.getElementById('ajax-indicator:error-ready-state').innerHTML = xhr.readyState;
      document.getElementById('ajax-indicator:error-type').innerHTML = xhr.pfSettings.type;
      document.getElementById('ajax-indicator:error-args').innerHTML = JSON.stringify(xhr.pfArgs);
      document.getElementById('ajax-indicator:pfSettings-source').innerHTML = xhr.pfSettings.source.id;
      document.getElementById('ajax-indicator:form-data').innerHTML = decodeURIComponent(xhr.pfSettings.data);
      document.getElementById('ajax-indicator:response-text').innerHTML = xhr.responseText;
      document.getElementById('ajax-indicator:xhr').innerHTML = JSON.stringify(xhr);
      PF('detail-error-dialog').show();
    }
  }
  PF('error-ajax-dialog').show();
}