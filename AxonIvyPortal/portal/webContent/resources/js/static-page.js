// Store the initial window height to detect changes
let windowHeight = window.innerHeight;

$(document).ready(() => {

  // Calculate and set height for Iframe once the document is ready
  setTimeout(() => {
    updateIframeHeight(calculateAvailableHeight());
  }, 100);

  // resize timeout ID
  let resizeTimeout;

  // Add event to update Iframe height after window is resized
  window.addEventListener('resize', function () {
    clearTimeout(resizeTimeout);

    resizeTimeout = setTimeout(function () {
      // If window height is changed, update the Iframe height
      if (window.innerHeight !== windowHeight) {
        updateIframeHeight(calculateAvailableHeight());
        windowHeight = window.innerHeight;
      }
    }, 1000); // Delay execution by 1 sec to avoid multiple DOM recalculations during resize
  });
});

// Functions for calculations
const parseNumber = (value) => parseInt(Math.round(parseFloat(value) || 0));

const getElementOuterHeight = (selector) => parseNumber($(selector).outerHeight(true));

const getElementMargins = (selector) => 
  parseNumber($(selector).css('margin-top')) + parseNumber($(selector).css('margin-bottom'));

const getElementPaddings = (selector) => 
  parseNumber($(selector).css('padding-top')) + parseNumber($(selector).css('padding-bottom'));

// Main calculation function
const calculateAvailableHeight = () => {
  const announcementHeight = getElementOuterHeight('.js-announcement-message');
  const announcementMargins = getElementMargins('.js-announcement-message');
  const layoutMainPaddings = getElementPaddings('.js-layout-main');
  const layoutContentPaddings = getElementPaddings('.js-layout-content');

  return window.innerHeight - layoutMainPaddings - announcementHeight - announcementMargins - layoutContentPaddings;
};

function updateIframeHeight(availableHeight) {
  var iframe = $('#iFrame');
  if (iframe.length > 0) {
    iframe.outerHeight(availableHeight);
    iframe.parent('.js-layout-content').addClass('flex flex-column');
  }
}