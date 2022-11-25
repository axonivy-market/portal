// Portal News Widget
function setupNewsWidgetContent(newsWidgetId, lazy) {
  if (lazy) {
    setTimeout(() => {
      setupNewsWidgetContent(newsWidgetId);
    }, 300);
  }
  let newsWidget = $('[id$=' + newsWidgetId + ']');
  if (newsWidget.length > 0) {
    let newsWidgetContent = newsWidget.find("[id$=':news-widget__content'");
    let items = newsWidgetContent.find(".news-item");
    $.each(items, function () {
      setupSeeMoreLessContent(this);
    })
  }
}

function setupSeeMoreLessContent(newsItem) {
  let newsItemContent = getNewsItemContent(newsItem);
  $(newsItemContent).addClass('line-clamp-text-3');
  const heightRatio = newsItemContent[0].scrollHeight - newsItemContent[0].clientHeight;
  let seeMoreLink = getSeeMoreLink(newsItem);
  let seeLessLink = getSeeLessLink(newsItem);
  $(seeLessLink).addClass('hide');
  if (heightRatio > 0) {
    $(seeMoreLink).removeClass('hide');
  } else {
    $(seeMoreLink).addClass('hide');
  }
}

function seeMoreNewsItemContent(newsItemId) {
  let newsItem = $('[id$=' + newsItemId + ']');
  let seeMoreLink = getSeeMoreLink(newsItem);
  let seeLessLink = getSeeLessLink(newsItem);
  $(seeMoreLink).addClass('hide');
  $(seeLessLink).removeClass('hide');
  let newsItemContent = getNewsItemContent(newsItem);
  $(newsItemContent).removeClass('line-clamp-text-3');
}

function seeLessNewsItemContent(newsItemId) {
  let newsItem = $('[id$=' + newsItemId + ']');
  let seeMoreLink = getSeeMoreLink(newsItem);
  let seeLessLink = getSeeLessLink(newsItem);
  $(seeMoreLink).removeClass('hide');
  $(seeLessLink).addClass('hide');
  let newsItemContent = getNewsItemContent(newsItem);
  $(newsItemContent).addClass('line-clamp-text-3');
}

function getNewsItemContent(newsItem) {
  return $(newsItem).find(".news__content");
}

function getSeeMoreLink(newsItem) {
  let newsItemActions = $(newsItem).find(".news-item__actions");
  return $(newsItemActions).find("[id$=':news-item-see-more']");
}

function getSeeLessLink(newsItem) {
  let newsItemActions = $(newsItem).find(".news-item__actions");
  return $(newsItemActions).find("[id$=':news-item-see-less']");
}