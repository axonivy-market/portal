// Override Carousel Header of Primeface
$(document).ready(function() {

  var statisticContainer = $('.js-statistic-widget-container.compact-mode');
  var statisticCarousel = statisticContainer.find('.js-statistic-carousel');
  var charSize = parseInt(statisticCarousel.find('[id$="0:number-of-chart"]').text());
  //$(".ui-carousel-item.ui-carousel-item-active.ui-carousel-item-start.ui-carousel-item-end").length;
  if (statisticCarousel.length == 0 || charSize == 0) {
    return;
  }

  // initialization parameter
  var nextNav = statisticCarousel.find('.ui-carousel-next');
  var prevNav = statisticCarousel.find('.ui-carousel-prev');
  var index = 0;
  var nextEvent = 0;

  // EffectDuration of Carousel is not a property since PF11. Use default value as 500
    effectDuration = 500;
	
  // Set default chart header titles
  var headerTitle = statisticCarousel.find('.ui-carousel-header');
  headerTitle.append(statisticCarousel.find('[id$=":' + index + ':chart-name-container"]').clone());

  // On click event of Carousel-nav
  // * Select current chart header
  // * Copy to Carousel header
  nextNav.click(function(event) {

    if (nextEvent == 0) {
      nextEvent = effectDuration;
      setTimeout(function() {

        index += 1;
        if (index > charSize - 1) {
          index = 0;
        }

        updateHeaderTitle(index, statisticCarousel);
        nextEvent = 0;
      }, effectDuration);
    }
  });

  prevNav.click(function() {
    if (nextEvent == 0) {
      nextEvent = effectDuration;
      setTimeout(function() {

        index -= 1;
        if ((index < 0)) {
          index = charSize - 1;
        }
        if (index == charSize) {
          index -= 1;
        }

        updateHeaderTitle(index, statisticCarousel);
        nextEvent = 0;
      }, effectDuration);
    }
  });

  nextNav.on('dblclick', function() {
    return false;
  });

  prevNav.on('dblclick', function() {
    return false;
  });

  function updateHeaderTitle(curentIndex, statisticCarousel) {
    headerTitle.get(0).innerHTML = "";
    headerTitle.append(statisticCarousel.find('[id$=":' + curentIndex + ':chart-name-container"]').clone());
  }

});
