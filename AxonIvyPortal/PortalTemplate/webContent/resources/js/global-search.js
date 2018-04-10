function showHideOverlayPanel(){
   var searchInputValue =$('#global-search-data')[0].value;
   if(searchInputValue != ""){
     PF('search-result-overlay-panel').show();
   } else {
     PF('search-result-overlay-panel').hide();
   }
}