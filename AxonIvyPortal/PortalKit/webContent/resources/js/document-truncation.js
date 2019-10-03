function DocumentTruncation() {
  return {
    
    truncate : function() {
      $('.js-document-column').css('display','inline-block');
      $('.js-download-command').each(function () {
        var documentHeight = $(this).outerHeight();
        var containerHeight = $(this).parent().outerHeight();
        var chars = 20;

        var notLoadDocumentColumn = documentHeight == 0 && $(this).children(".js-document-name").text() != '' && $('.js-document-column').css('display') == 'none';
        if (notLoadDocumentColumn) {
          $('.js-document-column').css('display','inline-block');
          documentHeight = $(this).outerHeight();
        }

        while (documentHeight > containerHeight) {
          var documentName = $(this).children(".js-document-name").text();
          $(this).children(".js-document-name").text(documentName.slice(0, chars) + '...' + documentName.slice(-chars));
          chars--;
          documentHeight = $(this).outerHeight();
        }

        if (notLoadDocumentColumn) {
            $('.js-document-column').css('display','none');
          }
      });
    },
  }
}

$(document).ready(function() {
  documentTruncation = new DocumentTruncation();
  documentTruncation.truncate();
});