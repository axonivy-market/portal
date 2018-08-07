var viewPort = (function(){
	 var LARGE_SCREEN = "large";
	 var MEDIUM_SCREEN = "medium";
	 var SMALL_SCREEN = "small";

	 
	 function isSmallScreen(){
		 return detectViewport() === SMALL_SCREEN;
	  }
	  
	  function isMediumScreen(){
		 return detectViewport() === MEDIUM_SCREEN;
	  }
	  
	  function isLargeScreen(){
		 return detectViewport() === LARGE_SCREEN;
	  }
	  
	  function detectViewport(){
		  var screenWidth = Math.max($(document).width(), window.innerWidth || 0);
	      if (screenWidth >= 1920) {
	        return LARGE_SCREEN;
	      }
	      if (screenWidth >= 1366) {
	        return MEDIUM_SCREEN;
	      } else {
	        return SMALL_SCREEN;
	      }
	  }
	  
	  return {
		    isSmallScreen : isSmallScreen,

		    /**
		     * Check if screen size is medium screen size
		     */
		    isMediumScreen : isMediumScreen,

		    /**
		     * Check if screen size is large screen size
		     */
		    isLargeScreen : isLargeScreen,
		  };
})();