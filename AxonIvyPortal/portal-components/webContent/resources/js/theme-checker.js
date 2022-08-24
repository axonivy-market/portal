var currentTheme;
var ThemeChecker = {
  updateLayoutByTheme: function (theme) {
    if (theme == null || theme == currentTheme) {
      return;
    }

    currentTheme = theme;
    if (theme.includes('serenity')) {
      $('body').removeClass('freya').removeClass('dark').removeClass('light').addClass('serenity');
    }

    if (theme.includes('freya')) {
      $('body').removeClass('serenity').addClass('freya');

      if (theme.includes('-dark')) {
        $('body').removeClass('light').addClass('dark');
      }

      if (theme.includes('-light')) {
        $('body').removeClass('dark').addClass('light');
      }
    }
  }
}