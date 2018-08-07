function CaseItemDocument(downloadMonitorDialogId) {
  var downloadMonitorId = downloadMonitorDialogId;
  $(downloadMonitorId).css("display","none");
  return {
    showStatus : function() {
      $(downloadMonitorId).css("display","");
    },
  
    hideStatus : function() {
      $(downloadMonitorId).css("display","none");
    }
  }
};