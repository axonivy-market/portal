function showWidgetDescriptionInplaceEditor() {
  $(".js-case-description-output").hide();
  $(".js-case-description-inplace").show();
  PF('widget-description-inplace').show();
  scrollGeneralContainerToBottom();
}

function scrollGeneralContainerToBottom() {
  var $caseGeneralContainer = $("[id$=':case-detail-general-container']");
  $caseGeneralContainer.animate({ scrollTop: $caseGeneralContainer.prop("scrollHeight") }, 1000);
}
