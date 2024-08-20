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

    // Change background color in Task Case List Page
    $(document).ready(function () {
      var taskListClass = document.getElementsByClassName('task-list-container js-task-list-container').length > 0;
      var caseListClass = document.getElementsByClassName('js-case-default-widget-container case-default-widget-container').length > 0;
      if (taskListClass || caseListClass) {
        $('body').addClass('ui-case-task-background-color');
      }
    })
  },
}