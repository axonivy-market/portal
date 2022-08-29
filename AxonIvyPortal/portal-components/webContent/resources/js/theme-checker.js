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
  }
}