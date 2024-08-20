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
    console.log(this.isCaseListOrTaskListPage(baseURI));
    if (this.isCaseListOrTaskListPage(baseURI)) {
      var whiteBackgroundClass = "ui-case-task-background-color";
      $('body').addClass(whiteBackgroundClass);
    }

  },
  isCaseListOrTaskListPage: function (baseURI) {
    console.log(baseURI);
    return baseURI.includes('com.axonivy.portal.developerexamples.customization.TaskWidget/TaskWidget.xhtml') || baseURI.includes('com.axonivy.portal.developerexamples.customization.CaseWidget/CaseWidget.xhtml');

  }
}