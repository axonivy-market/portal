
var maxItems = 7;

var ProcessItemHeight = 55;
var favouritesProcessSize = 0;

var UserFavouritesProcess = {

  init : function(favouritesProcess) {
	favouritesProcessSize = favouritesProcess || 0;
	if( window.innerWidth < 1024 &&  window.innerWidth > 640 ) {
		maxItems = 5;
	} else if(window.innerWidth <= 640 ) {
		maxItems = 3;
	}
  },

  setUpScrollBar : function() {
    this.scrollBarForProcesses();
  },

  scrollBarForProcesses : function() {
    if (favouritesProcessSize == 0) {
      return;
    }

    var favouritesProcessList = $('[id$="process-widget:user-process-container"]');
    var favouritesProcessListBody = favouritesProcessList.find('.compact-processes-container.widget-content');
    var scrollHeightForProcess = 0;
    if (favouritesProcessSize > maxItems) {
    	scrollHeightForProcess = ProcessItemHeight*maxItems + 2;
    	favouritesProcessListBody.css("max-height", scrollHeightForProcess);
    }
  },

}
