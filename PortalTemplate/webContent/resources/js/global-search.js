$(function() {
  $('#global-search-container').clickOff(resetGlobalSearch);
  $('#global-search').mouseenter(resetGlobalSearch);
  $('#global-search').click(focusInSearchText);
});

function resetGlobalSearch() {
  var searchInput = $(PF('global-search-data').jqId);
  var isNotActiveMenu = !($('#global-search-container').hasClass('active-menu'));
  var canResetSearchData = isNotActiveMenu && (searchInput.val().length > 0);
  if (canResetSearchData) {
     searchInput.val(null);
     resetSearch();
  }
}

function updateScrollbar() {
  $("#global-search-result-container").perfectScrollbar({
    suppressScrollX : true
  });
}

function focusInSearchText() {
  $(PF('global-search-data').jqId).focus();
}