$(function () {
  setTimeout(() => {
    handleDisplayIconMoreLink();
  }, 0);
})

function displayMoreText() {
  let descriptionContent = document.getElementById("description-content");

  if (!descriptionContent) {
    return;
  }

  descriptionContent.classList.remove("short-description");
  descriptionContent.classList.add("long-description");
  hideIconMoreLink();
  displayIconLessLink();
}

function displayLessText() {
  let descriptionContent = document.getElementById("description-content");

  if (!descriptionContent) {
    return;
  }

  descriptionContent.scrollTop = 0
  descriptionContent.classList.remove("long-description");
  descriptionContent.classList.add("short-description");

  hideIconLessLink();
  displayIconMoreLink();
}


function displayIconMoreLink() {
  let iconMoreLink = document.getElementById("icon-more-link");
  if (!iconMoreLink) {
    return;
  }

  iconMoreLink.style.setProperty("display", "block");
}

function hideIconMoreLink() {
  let iconMoreLink = document.getElementById("icon-more-link");
  if (!iconMoreLink) {
    return;
  }

  iconMoreLink.style.setProperty("display", "none");
}

function displayIconLessLink() {
  let iconLessLink = document.getElementById("icon-less-link");
  let descriptionContent = document.getElementById("description-content");
  if (!iconLessLink || !descriptionContent) {
    return;
  }

  iconLessLink.classList.add("display");
}

function hideIconLessLink() {
  let iconLessLink = document.getElementById("icon-less-link");
  if (!iconLessLink) {
    return;
  }

  iconLessLink.classList.remove("display");
}

function handleDisplayIconMoreLink() {
  let descriptionContentContainer = document.getElementById("description-content");
  if (!descriptionContentContainer) {
      return;
  }

  let descriptionContent = document.getElementById("task-description-output");
  let iconMoreLink = document.getElementById("icon-more-link");
  let iconLessLink = document.getElementById("icon-less-link");
  if (!descriptionContent || !iconMoreLink || !iconLessLink) {
    return;
  }

  descriptionContentContainer.classList.add("long-description");
  descriptionContentContainer.classList.remove("short-description");
  iconMoreLink.style.setProperty("display", "none");
  iconLessLink.classList.remove("display");

  if (descriptionContent.offsetHeight > 36) { // 36 is height of two lines
    descriptionContentContainer.classList.add("short-description");
    descriptionContentContainer.classList.remove("long-description");
    iconMoreLink.style.setProperty("display", "block");
  } else {
    descriptionContentContainer.classList.remove("short-description");
    descriptionContentContainer.classList.add("long-description");
    iconMoreLink.style.setProperty("display", "none");
  }
}

$(window).resize(() => {
  handleDisplayIconMoreLink();
});