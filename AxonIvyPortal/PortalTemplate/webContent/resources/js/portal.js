var mainMenuMode = "collapsed";
var FIRST_LEVEL_MENU_MODE = 'portal-first-level-menu-mode';
var SECOND_LEVEL_MENU_MODE = 'portal-second-level-menu-mode';
var ACTIVE_MENU_ITEM = 'portal-active-menu-item';

var Portal = {
  init : function(responsiveToolkit, updateFlag) {
    typeof(updateFlag) == "undefinied" ? false : updateFlag;
    if ($('form.login-form').size() > 0) {
      return;
    }

    if (mainMenuMode === "expanded") {
      $('.js-left-sidebar').addClass('in').toggleClass('left-sidebar-animation');
      $('.js-left-sidebar-toggle').addClass('in').toggleClass('left-sidebar-animation');
      $('.left-sidebar-sub-menu-name').toggleClass('left-sidebar-animation');
      $('.left-sidebar-menu-name').toggleClass('left-sidebar-animation');
      $('.left-sidebar-menu-icon').toggleClass('left-sidebar-animation');
      $("div[class*='left-sidebar-menu-item-tooltip']").toggleClass('u-invisibility');
      setTimeout(function() {
        $('.js-left-sidebar').toggleClass('left-sidebar-animation');
        $('.js-left-sidebar-toggle').toggleClass('left-sidebar-animation');
        $('.left-sidebar-sub-menu-name').toggleClass('left-sidebar-animation');
        $('.left-sidebar-menu-name').toggleClass('left-sidebar-animation');
        $('.left-sidebar-menu-icon').toggleClass('left-sidebar-animation');
        ;
      }, 1);
    }

    setTimeout(function() {
      $('.left-sidebar-menu-header').toggleClass('left-sidebar-animation');
    }, 3);

    // Update screen when window size is changed
    $(window).resize(function() {
      responsiveToolkit.updateLayoutWithoutAnimation();
    });

    responsiveToolkit.updateLayoutWithoutAnimation();
    $('#main-area-panel').removeClass('u-invisibility');
    $('#left-menu').removeClass('u-invisibility');
  },
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
