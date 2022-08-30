var $descriptionContentContainer, $descriptionContent, $iconMoreLink, $iconLessLink;
handleDisplayIconMoreLink();

function showWidgetDescriptionInplaceEditor() {
  $(".js-task-description-output").hide();
  $(".js-task-description-inplace").show();
  hideIconLessLink();
  hideIconMoreLink();
  PF('widget-description-inplace').show();
}

function displayMoreText() {
  if (!$descriptionContentContainer || $descriptionContentContainer.length == 0) {
    return;
  }
  $descriptionContentContainer.removeClass("short-description");
  $descriptionContentContainer.addClass("long-description");
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
  taskDescriptionForm = $("[id$='task-detail-description-form']");
  $descriptionContentContainer = taskDescriptionForm.find("[id$='description-content']");
  if (!$descriptionContentContainer || $descriptionContentContainer.length == 0) {
    return;
  }

  $descriptionContent = taskDescriptionForm.find("[id$='task-description-output']");
  $iconMoreLink = taskDescriptionForm.find("[id$='icon-more-link']");
  $iconLessLink = taskDescriptionForm.find("[id$='icon-less-link']");
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