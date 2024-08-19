var currentTheme;
var ThemeChecker = {
  updateLayoutByTheme: function (theme) {
    if (theme == null || theme == currentTheme) {
      return;
    }

    currentTheme = theme;
    if (theme.includes('serenity')) {
      $('body').removeClass('freya dark light').addClass('serenity');
    } else {
      $('body').removeClass('serenity light dark').addClass(theme.includes('-light') ? 'freya light' : 'freya dark');
    }

    baseURI = $('body').get(0).baseURI;

    if (this.isCaseListOrTaskListPage(baseURI) && theme.includes('-light')) {
      var bodyClass = "body[class*='freya light']";
      $(bodyClass).css("background-color", "white");
    }

  },
  isCaseListOrTaskListPage: function (baseURI) {
    return baseURI.includes('Case') || baseURI.includes('Task');
  }
}