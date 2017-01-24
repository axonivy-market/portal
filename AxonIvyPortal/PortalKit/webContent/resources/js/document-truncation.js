function DocumentTruncation() {
  return {
    
    truncate : function() {
      $('.js-download-command').each(function () {
        var documentHeight = $(this).outerHeight();
        var containerHeight = $(this).parent().outerHeight();
        var chars = 20;
        
        while (documentHeight > containerHeight) {
          var documentName = $(this).children(".js-document-name").text();
          $(this).children(".js-document-name").text(documentName.slice(0, chars) + '...' + documentName.slice(-chars));
          chars--;
          documentHeight = $(this).outerHeight();
        }
      });
    },
  }
}

$(document).ready(function() {
  documentTruncation = new DocumentTruncation();
  documentTruncation.truncate();
});