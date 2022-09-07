var $descriptionContentContainer, $descriptionContent, $iconMoreLink, $iconLessLink;
handleDisplayIconMoreLink();

function showWidgetDescriptionInplaceEditor() {
  $(".js-case-description-output").hide();
  $(".js-case-description-inplace").show();
  hideIconLessLink();
  hideIconMoreLink();
  PF('widget-description-inplace').show();
  scrollGeneralContainerToBottom();
}

function scrollGeneralContainerToBottom() {
  var $caseGeneralContainer = $("[id$=':case-detail-general-container']");
  $caseGeneralContainer.animate({ scrollTop: $caseGeneralContainer.prop("scrollHeight") }, 1000);
}

function displayMoreText() {
  if (!$descriptionContentContainer || $descriptionContentContainer.length == 0) {
    return;
  }
  $descriptionContentContainer.removeClass("short-description");
  $descriptionContentContainer.addClass("long-description");
  scrollGeneralContainerToBottom();
  hideIconMoreLink();
  displayIconLessLink();
}

function displayLessText() {
  if (!$descriptionContentContainer || $descriptionContentContainer.length == 0) {
    return;
  }
  $descriptionContentContainer.scrollTop = 0
  $descriptionContentContainer.removeClass("long-description");
  $descriptionContentContainer.addClass("short-description");

  hideIconLessLink();
  displayIconMoreLink();
}

function displayIconMoreLink() {
  if (!$iconMoreLink || $iconMoreLink.length == 0) {
    return;
  }
  $iconMoreLink.css("display", "block");
  scrollGeneralContainerToBottom();
}

function hideIconMoreLink() {
  if (!$iconMoreLink || $iconMoreLink.length == 0) {
    return;
  }
  $iconMoreLink.css("display", "none");
}

function displayIconLessLink() {
  if (!$iconLessLink || $iconLessLink.length == 0
    || !$descriptionContent || $descriptionContent.length == 0) {
    return;
  }
  $iconLessLink.css("display", "block");
}

function hideIconLessLink() {
  if (!$iconLessLink || $iconLessLink.length == 0) {
    return;
  }
  $iconLessLink.css("display", "none");
}

function handleDisplayIconMoreLink() {
  let caseDescriptionForm = $("[id$='case-description-form']");
  $descriptionContentContainer = caseDescriptionForm.find("[id$='description-content'");
  if (!$descriptionContentContainer || $descriptionContentContainer.length == 0) {
    return;
  }

  $descriptionContent = caseDescriptionForm.find("[id$='case-description-output']");
  $iconMoreLink = caseDescriptionForm.find("[id$='icon-more-link']");
  $iconLessLink = caseDescriptionForm.find("[id$='icon-less-link']");
  if (!$descriptionContent || !$iconMoreLink || !$iconLessLink) {
    return;
  }

  $descriptionContentContainer.addClass("long-description");
  $descriptionContentContainer.removeClass("short-description");
  $iconMoreLink.css("display", "none");
  $iconLessLink.css("display", "none");

  if ($descriptionContent.outerHeight() > 36) { // 36 is height of two lines
    $descriptionContentContainer.addClass("short-description");
    $descriptionContentContainer.removeClass("long-description");
    $iconMoreLink.css("display", "block");
  } else {
    $descriptionContentContainer.removeClass("short-description");
    $descriptionContentContainer.addClass("long-description");
    $iconMoreLink.css("display", "none");
  }
}

$(window).resize(() => {
  handleDisplayIconMoreLink();
});