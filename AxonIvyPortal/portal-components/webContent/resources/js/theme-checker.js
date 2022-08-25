var currentTheme;
var ThemeChecker = {
  updateLayoutByTheme: function (theme) {
    if (theme == null || theme == currentTheme) {
      return;
    }

    currentTheme = theme;
    if (theme.includes('serenity')) {
      $('body').removeClass('freya dark light').addClass('serenity');
    }

    if (theme.includes('freya')) {
      $('body').removeClass('serenity').addClass(theme.includes('-light') ? 'freya light' : 'freya dark');
    }
  }
}