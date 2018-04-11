$(function() {
  $('#global-search-data').click(hideUserSettingMenu);
});

function showHideOverlayPanel(){
   var searchInputValue =$('#global-search-data')[0].value;
   if(searchInputValue != ""){
     PF('search-result-overlay-panel').show();
   } else {
     PF('search-result-overlay-panel').hide();
   }
}

function hideUserSettingMenu(){
	var userSettingContainer =$('#user-setting-container');
	if(userSettingContainer.hasClass('active-menu')){
		userSettingContainer.removeClass('active-menu');
	}
}