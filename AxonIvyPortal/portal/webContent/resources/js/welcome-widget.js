function escapeHtml(unsafe){
  return unsafe
         .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;");
}
WelcomeWidgetConfiguration = {

  oldImageStyleClass : '',
  oldTextStyleClass : '',

  init : function(isGreeting) {
    this.updatePreviewText(isGreeting);
    this.updatePreviewTextPosition();
    this.updatePreviewTextColor();
	this.updatePreviewTextColorDarkMode();
    this.updatePreviewTextSize();
    this.updateStyleClasses();
    this.updatePreviewImageFit();
  },
  updatePreviewText : function(isGreeting) {
    var previewDialog = $('#new-widget-configuration-dialog');
    var welcomeText = '';
    var findWelcomeText = previewDialog.find('.js-welcome-text-input.language-to-preview');
    if (findWelcomeText != undefined && findWelcomeText.get(0) != undefined){
      welcomeText = findWelcomeText.get(0).value;
    }

    if (isGreeting == 'true' || (isGreeting == undefined && $('.js-greeting-text').length != 0)) {
      var findGreeting = previewDialog.find('.js-greeting-text.language-to-preview')
    if (findGreeting != undefined && findGreeting.get(0) != undefined){
      console.log(1)
      welcomeText = findGreeting.get(0).innerHTML + welcomeText;
    }
      
    }
	var findPreviewText = $('#new-widget-configuration-dialog').find('.js-preview-text');
    if (findPreviewText != undefined && findPreviewText.get(0) != undefined){
      console.log(2)
      findPreviewText.get(0).innerHTML = escapeHtml(welcomeText);
    }
    
	var findPreviewTextDarkMode = $('#new-widget-configuration-dialog').find('.js-preview-text-dark-mode');
    if (findPreviewTextDarkMode != undefined && findPreviewTextDarkMode.get(0) != undefined){
      console.log(3)
      findPreviewTextDarkMode.get(0).innerHTML = escapeHtml(welcomeText);
      console.log(findPreviewTextDarkMode.get(0))
    }

  },

  updatePreviewTextPosition : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var selectedPosition = previewDialog.find('input[id $="selected-welcome-text-position"]').get(0).value;
    var previewText = previewDialog.find('.js-preview-text');
    if (selectedPosition == 'BOTTOM_LEFT') {
      previewText.removeClass('top right center').addClass('bottom left');
    }
    if (selectedPosition == 'BOTTOM_RIGHT') {
      previewText.removeClass('top left center').addClass('bottom right');
    }
    if (selectedPosition == 'TOP_LEFT') {
      previewText.removeClass('bottom right center').addClass('top left');
    }
    if (selectedPosition == 'TOP_RIGHT') {
      previewText.removeClass('bottom left center').addClass('top right');
    }
    if (selectedPosition == 'CENTER') {
      previewText.removeClass('bottom top left right').addClass('center');
    }
	
    var previewTextDarkMode = previewDialog.find('.js-preview-text-dark-mode');
    if (selectedPosition == 'BOTTOM_LEFT') {
      previewTextDarkMode.removeClass('top right center').addClass('bottom left');
    }
    if (selectedPosition == 'BOTTOM_RIGHT') {
      previewTextDarkMode.removeClass('top left center').addClass('bottom right');
    }
    if (selectedPosition == 'TOP_LEFT') {
      previewTextDarkMode.removeClass('bottom right center').addClass('top left');
    }
    if (selectedPosition == 'TOP_RIGHT') {
      previewTextDarkMode.removeClass('bottom left center').addClass('top right');
    }
    if (selectedPosition == 'CENTER') {
      previewTextDarkMode.removeClass('bottom top left right').addClass('center');
    }
  },
  
updatePreviewImageFit : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var selectedFit = previewDialog.find('input[id $="selected-welcome-image-fit"]').get(0).value;
    var images = previewDialog.find('.js-preview-image');
    for (var i = 0; i < images.length;i ++){
      var currentImage = images[i];
      $(currentImage).removeClass (function (index, className) {
      return (className.match (/(^|\s)welcome-image-fit-\S+/g) || []).join(' ');
    });
    if (selectedFit == 'COVER') {
      $(currentImage).addClass('welcome-image-fit-cover'); 
    } else if (selectedFit == 'FILL') {
      $(currentImage).addClass('welcome-image-fit-fill');
    } else if (selectedFit == 'NONE') {
      $(currentImage).addClass('welcome-image-fit-none');
    } else if (selectedFit == 'CONTAIN') {
      $(currentImage).addClass('welcome-image-fit-contain');
    }
    }
  },

  updatePreviewTextColor : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var findSelectedColor = previewDialog.find('.js-welcome-text-color');
    if (findSelectedColor != undefined){
      var selectedColor = findSelectedColor.get(0).value;
      var findPreviewText = previewDialog.find('.js-preview-text');
      if (findPreviewText != undefined){
        findPreviewText.css({'color': selectedColor});
      }	
	}
  },

  updatePreviewTextColorDarkMode : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var findSelectedColorDarkMode = previewDialog.find('.js-welcome-text-color-dark-mode');
    if (findSelectedColorDarkMode != undefined){
      var selectedColorDarkMode = findSelectedColorDarkMode.get(0).value;
      var findPreviewTextDarkMode = previewDialog.find('.js-preview-text-dark-mode');
      if (findPreviewTextDarkMode != undefined){
        findPreviewTextDarkMode.css({'color': selectedColorDarkMode});	
      }
    }
  	
  },

  updatePreviewTextSize : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var selectedTextSize = previewDialog.find('input[id $="selected-welcome-text-size"]').get(0).value;
    var previewText = previewDialog.find('.js-preview-text');
    var previewTextDarkMode = previewDialog.find('.js-preview-text-dark-mode');
    if (selectedTextSize == 'NORMAL_TEXT') {
      previewText.removeClass('HEADING_1 HEADING_2 HEADING_3').addClass('NORMAL_TEXT');
      previewTextDarkMode.removeClass('HEADING_1 HEADING_2 HEADING_3').addClass('NORMAL_TEXT');
      return;
    }
    if (selectedTextSize == 'HEADING_1') {
      previewText.removeClass('HEADING_2 HEADING_3 NORMAL_TEXT').addClass('HEADING_1');
      previewTextDarkMode.removeClass('HEADING_2 HEADING_3 NORMAL_TEXT').addClass('HEADING_1');
      return;
    }
    if (selectedTextSize == 'HEADING_2') {
      previewText.removeClass('HEADING_1 HEADING_3 NORMAL_TEXT').addClass('HEADING_2');
      previewTextDarkMode.removeClass('HEADING_1 HEADING_3 NORMAL_TEXT').addClass('HEADING_2');
      return;
    }
    if (selectedTextSize == 'HEADING_3') {
      previewText.removeClass('HEADING_1 HEADING_2 NORMAL_TEXT').addClass('HEADING_3');
      previewTextDarkMode.removeClass('HEADING_1 HEADING_2 NORMAL_TEXT').addClass('HEADING_3');
      return;
    }

  },

  updateStyleClasses : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var imageStyleClass = previewDialog.find('.js-image-style-class').attr('value');
    if (typeof imageStyleClass === 'undefined') {
      return;
    }
    var image = previewDialog.find('.js-preview-image');
    if (this.oldImageStyleClass == "") {
      this.oldImageStyleClass = image.attr('class');
    }
    image.attr('class', this.oldImageStyleClass + ' ' + imageStyleClass);

    var textStyleClass = previewDialog.find('.js-text-style-class').attr('value');
    var welcomeText = previewDialog.find('.js-preview-text');
	var welcomeTextDarkMode = previewDialog.find('.js-preview-text-dark-mode');
    if (this.oldTextStyleClass == "") {
      this.oldTextStyleClass = welcomeText.attr('class');
    }
    welcomeText.attr('class', this.oldTextStyleClass + ' ' + textStyleClass);
    welcomeTextDarkMode.attr('class', this.oldTextStyleClass + ' ' + textStyleClass);
  }
}

WelcomeWidget = {
  init : function(widgetId, welcomeTextColor, welcomeTextColorDarkMode, welcomeTextPosition, welcomeTextSize, welcomeImageFit, imageInlineStyle) {
    this.updateWelcomeTextStyles(widgetId, welcomeTextColor, welcomeTextColorDarkMode, welcomeTextPosition, welcomeTextSize);
    this.updateImageFit(widgetId, welcomeImageFit);
    this.updateImageInlineStyle(widgetId,imageInlineStyle);
  },

  updateWelcomeTextStyles : function(widgetId, welcomeTextColor, welcomeTextColorDarkMode, welcomeTextPosition, welcomeTextSize) {
    var widget = $('div.grid-stack-item[gs-id = ' + widgetId + ']');
    var welcomeText = widget.find('[id $= "welcome-text"]');
    welcomeText.css({
      'color' : welcomeTextColor,
    });

    switch(welcomeTextPosition) {
      case 'BOTTOM_LEFT':
        welcomeText.addClass('bottom').addClass('left');
        break;
      case 'BOTTOM_RIGHT':
        welcomeText.addClass('bottom').addClass('right');
        break;
      case 'TOP_LEFT':
        welcomeText.addClass('top').addClass('left');
        break;
      case 'TOP_RIGHT':
        welcomeText.addClass('top').addClass('right');
        break;
      case 'CENTER':
        welcomeText.addClass('center');
        break;
      default:
        break;
    }

    welcomeText.addClass(welcomeTextSize);

    var welcomeTextDarkMode = widget.find('[id $= "welcome-text-dark-mode"]');
    welcomeTextDarkMode.css({
      'color' : welcomeTextColorDarkMode,
    });
	
    switch(welcomeTextPosition) {
      case 'BOTTOM_LEFT':
        welcomeTextDarkMode.addClass('bottom').addClass('left');
        break;
      case 'BOTTOM_RIGHT':
        welcomeTextDarkMode.addClass('bottom').addClass('right');
        break;
      case 'TOP_LEFT':
        welcomeTextDarkMode.addClass('top').addClass('left');
        break;
      case 'TOP_RIGHT':
        welcomeTextDarkMode.addClass('top').addClass('right');
        break;
      case 'CENTER':
        welcomeTextDarkMode.addClass('center');
        break;
      default:
        break;
    }

    welcomeTextDarkMode.addClass(welcomeTextSize);
  },

  updateImageFit: function(widgetId, welcomeImageFit) {
    var widget = $('div.grid-stack-item[gs-id = ' + widgetId + ']');
    var images = widget.find('.js-welcome-image');

    for (var i = 0; i < images.length; i++){
    var currentImage = images[i];
    $(currentImage).removeClass (function (index, className) {
      return (className.match (/(^|\s)welcome-image-fit-\S+/g) || []).join(' ');
    }); 
    if (welcomeImageFit == 'COVER') {
      $(currentImage).addClass('welcome-image-fit-cover'); 
    } else if (welcomeImageFit == 'FILL') {
      $(currentImage).addClass('welcome-image-fit-fill');
    } else if (welcomeImageFit == 'NONE') {
      $(currentImage).addClass('welcome-image-fit-none');
    } else if (welcomeImageFit == 'CONTAIN') {
      $(currentImage).addClass('welcome-image-fit-contain');
    }
    }


  },

  updateImageInlineStyle: function(widgetId, imageInlineStyle) {
    var widget = $('div.grid-stack-item[gs-id="' + widgetId + '"]');
    var images = widget.find('.js-welcome-image');
    for (var i = 0; i < images.length; i++) {
        images[i].style.cssText = imageInlineStyle;
    }
  }
}
