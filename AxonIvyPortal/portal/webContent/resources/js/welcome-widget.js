WelcomeWidgetConfiguration = {

  oldImageStyleClass : '',
  oldTextStyleClass : '',

  init : function(isGreeting) {
    this.updatePreviewText(isGreeting);
    this.updatePreviewTextPosition();
    this.updatePreviewTextColor();
    this.updatePreviewTextSize();
    this.updateStyleClasses();
  },

  updatePreviewText : function(isGreeting) {
    var previewDialog = $('#new-widget-configuration-dialog');
    var welcomeText = previewDialog.find('.js-welcome-text-input.language-to-preview').get(0).value;

    if (isGreeting == 'true' || (isGreeting == undefined && $('.js-greeting-text').length != 0)) {
      welcomeText = previewDialog.find('.js-greeting-text.language-to-preview').get(0).innerHTML + welcomeText;
    }
    $('#new-widget-configuration-dialog').find('.js-preview-text').get(0).innerHTML = welcomeText;
  },

  updatePreviewTextPosition : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var selectedPosition = previewDialog.find('input[id $="selected-welcome-text-position"]').get(0).value;
    var previewText = previewDialog.find('.js-preview-text');
    if (selectedPosition == 'BOTTOM_LEFT') {
      previewText.removeClass('top right center').addClass('bottom left');
      return;
    }
    if (selectedPosition == 'BOTTOM_RIGHT') {
      previewText.removeClass('top left center').addClass('bottom right');
      return;
    }
    if (selectedPosition == 'TOP_LEFT') {
      previewText.removeClass('bottom right center').addClass('top left');
      return;
    }
    if (selectedPosition == 'TOP_RIGHT') {
      previewText.removeClass('bottom left center').addClass('top right');
      return;
    }
    if (selectedPosition == 'CENTER') {
      previewText.removeClass('bottom top left right').addClass('center');
      return;
    }
  },
  
updatePreviewImageFit : function() {
var previewDialog = $('#new-widget-configuration-dialog');
    var selectedFit = previewDialog.find('input[id $="selected-welcome-image-fit"]').get(0).value;
    var image = previewDialog.find('.js-preview-image');    
    
    if (selectedFit == 'COVER') {
      image.removeClass('welcome-image-fit-none welcome-image-fit-fill welcome-image-fit-contain').addClass('welcome-image-fit-cover'); 
    } else if (selectedFit == 'FILL') {
      image.removeClass('welcome-image-fit-none welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-fill');
    } else if (selectedFit == 'NONE') {
      image.removeClass('welcome-image-fit-fill welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-none');
    } else if (selectedFit == 'CONTAIN') {
      image.removeClass('welcome-image-fit-fill welcome-image-fit-cover welcome-image-fit-none').addClass('welcome-image-fit-contain');
    }
  },

  updatePreviewTextColor : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var selectedColor = '#' + previewDialog.find('.js-welcome-text-color input[type = "hidden"]').get(0).value;
    previewDialog.find('.js-preview-text').css({'color': selectedColor});
    previewDialog.find('.js-selected-welcome-text-color').get(0).innerHTML = selectedColor;
  },

  updatePreviewTextSize : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var selectedTextSize = previewDialog.find('input[id $="selected-welcome-text-size"]').get(0).value;
    var previewText = previewDialog.find('.js-preview-text');
    if (selectedTextSize == 'NORMAL_TEXT') {
      previewText.removeClass('HEADING_1 HEADING_2 HEADING_3').addClass('NORMAL_TEXT');
      return;
    }
    if (selectedTextSize == 'HEADING_1') {
      previewText.removeClass('HEADING_2 HEADING_3 NORMAL_TEXT').addClass('HEADING_1');
      return;
    }
    if (selectedTextSize == 'HEADING_2') {
      previewText.removeClass('HEADING_1 HEADING_3 NORMAL_TEXT').addClass('HEADING_2');
      return;
    }
    if (selectedTextSize == 'HEADING_3') {
      previewText.removeClass('HEADING_1 HEADING_2 NORMAL_TEXT').addClass('HEADING_3');
      return;
    }
  },

  updateStyleClasses : function() {
    var previewDialog = $('#new-widget-configuration-dialog');
    var imageStyleClass = previewDialog.find('.js-image-style-class').attr('value');
    var image = previewDialog.find('.js-preview-image');
    if (this.oldImageStyleClass == "") {
      this.oldImageStyleClass = image.attr('class');
    }
    image.attr('class', this.oldImageStyleClass + ' ' + imageStyleClass);

    var textStyleClass = previewDialog.find('.js-text-style-class').attr('value');
    var welcomeText = previewDialog.find('.js-preview-text');
    if (this.oldTextStyleClass == "") {
      this.oldTextStyleClass = welcomeText.attr('class');
    }
    welcomeText.attr('class', this.oldTextStyleClass + ' ' + textStyleClass);
  }
}

WelcomeWidget = {
  init : function(widgetId, welcomeTextColor, welcomeTextPosition, welcomeTextSize, welcomeImageFit, imageInlineStyle) {
    this.updateWelcomeTextStyles(widgetId, welcomeTextColor, welcomeTextPosition, welcomeTextSize);
    this.updateImageFit(widgetId, welcomeImageFit);
    this.updateImageInlineStyle(imageInlineStyle);
  },

  updateWelcomeTextStyles : function(widgetId, welcomeTextColor, welcomeTextPosition, welcomeTextSize) {
    var widget = $('div.grid-stack-item[gs-id = ' + widgetId + ']');
    var welcomeText = widget.find('[id $= "welcome-text"]');
    welcomeText.css({
      'color' : '#' + welcomeTextColor,
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

    switch(welcomeTextSize) {
      case 'NORMAL_TEXT':
        welcomeText.addClass('NORMAL_TEXT');
        break;
      case 'HEADING_1':
        welcomeText.addClass('HEADING_1');
        break;
      case 'HEADING_2':
        welcomeText.addClass('HEADING_2');
        break;
      case 'HEADING_3':
        welcomeText.addClass('HEADING_3');
        break;
      default:
        break;
    }
  },
  updateImageFit: function(widgetId, welcomeImageFit){
  	var widget = $('div.grid-stack-item[gs-id = ' + widgetId + ']');
  	var defaultImage = widget.find('[id $= "default-image"]');
  	var defaultImageDark = widget.find('[id $= "default-image-dark"]');
  	var welcomeImage = widget.find('[id $= "welcome-image"]');
  	  	
  	if (welcomeImageFit == 'COVER') {
      defaultImage.removeClass('welcome-image-fit-none welcome-image-fit-fill welcome-image-fit-contain').addClass('welcome-image-fit-cover'); 
      defaultImageDark.removeClass('welcome-image-fit-none welcome-image-fit-fill welcome-image-fit-contain').addClass('welcome-image-fit-cover');
      welcomeImage.removeClass('welcome-image-fit-none welcome-image-fit-fill welcome-image-fit-contain').addClass('welcome-image-fit-cover');
    } else if (welcomeImageFit == 'FILL') {
      defaultImage.removeClass('welcome-image-fit-none welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-fill');
      defaultImageDark.removeClass('welcome-image-fit-none welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-fill');
      welcomeImage.removeClass('welcome-image-fit-none welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-fill');
    } else if (welcomeImageFit == 'NONE') {
      defaultImage.removeClass('welcome-image-fit-fill welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-none');
      defaultImageDark.removeClass('welcome-image-fit-fill welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-none');
      welcomeImage.removeClass('welcome-image-fit-fill welcome-image-fit-contain welcome-image-fit-cover').addClass('welcome-image-fit-none');
    } else if (welcomeImageFit == 'CONTAIN') {
      defaultImage.removeClass('welcome-image-fit-fill welcome-image-fit-cover welcome-image-fit-none').addClass('welcome-image-fit-contain');
      defaultImageDark.removeClass('welcome-image-fit-fill welcome-image-fit-cover welcome-image-fit-none').addClass('welcome-image-fit-contain');
      welcomeImage.removeClass('welcome-image-fit-fill welcome-image-fit-cover welcome-image-fit-none').addClass('welcome-image-fit-contain');
    }
	
  },
  updateImageInlineStyle:function(imageInlineStyle){
  	console.log(imageInlineStyle);
  	var image = document.getElementsByClassName('js-welcome-image');
  	for (var i = 0; i < image.length; i++){
  	image[i].style.cssText = imageInlineStyle;
  	}
  }
}