// Override Carousel Header of Primeface
$(document).ready(function() {
  var statisticContainer = $('.js-statistic-widget-container.compact-mode');
  var statisticCarousel = statisticContainer.find('.statistic-carousel');
  if (statisticCarousel.length == 0) {
    return;
  }

  // initialization parameter
  var index = 0;
  var nextEvent = 0;
  // Get EffectDuration of Carousel. If not define, use default value as 500
  var effectDuration = parseInt(statisticCarousel.find('[id$="0:carousel-effect-duration"]').text());
  if (isNaN(effectDuration) || effectDuration < 0) {
    effectDuration = 500;
  }

  validateTitleLength(index, statisticCarousel);

  var nextNav = statisticCarousel.find('.ui-carousel-next-button');
  var prevNav = statisticCarousel.find('.ui-carousel-prev-button');
  var headerTitle = statisticCarousel.find('.ui-carousel-header-title');
  // Set default chart header titles
  var itemsContainer = statisticCarousel.find('.ui-carousel-items');
  var charSize = statisticCarousel.find('.ui-carousel-viewport').children(".ui-carousel-items").children("li").length;
  headerTitle.append(statisticCarousel.find('[id$="0:chart-name-container"]').clone());

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
    validateTitleLength(index, statisticCarousel);
    var dir = headerTitle.get(0).innerHTML = "";
    headerTitle.append(statisticCarousel.find('[id$="' + curentIndex + ':chart-name-container"]').clone());
  }
  
  function validateTitleLength(index, statisticCarousel) {
    var maxTitleSize = 30;
    var titleHeader = statisticCarousel.find('[id$="' + index + ':chart-name"]');
    var activeTitle = titleHeader.text();
    var titleLength = activeTitle.length;
    
    if (titleLength > 30) {
      activeTitle = activeTitle.substring(0, maxTitleSize);
      
      if (titleHeader.get(0) !== undefined) {
        titleHeader.get(0).innerHTML = activeTitle + ' ...';
      }
    }
  }

});

