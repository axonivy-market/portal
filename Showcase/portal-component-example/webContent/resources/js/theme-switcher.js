$(document).ready(function() {
  changeTheme();
});

function changeTheme() {
  var selectedTheme = $('label[id $=":theme-selection_label"]').text();

  PrimeFaces.changeTheme(selectedTheme);
  PrimeFaces.ajax.RESOURCE = null;

  updateLinkForLayoutStylesheet(selectedTheme);

  if (selectedTheme.includes('freya-ivy-')) {
    if (selectedTheme.includes('-dark')) {
      PrimeFaces.FreyaConfigurator.changeLayout('ivy', 'dark');
    } else {
      PrimeFaces.FreyaConfigurator.changeLayout('ivy', 'light');
    }
    PrimeFaces.FreyaConfigurator.beforeResourceChange();
  }

  if (typeof ThemeChecker !== 'undefined') {
    ThemeChecker.updateLayoutByTheme(selectedTheme);
  }
}

function updateLinkForLayoutStylesheet(theme) {
  var $cssLinks = $('link[href*=' + '"javax.faces.resource"]');
  if (theme == 'serenity-ivy') {
    $cssLinks.each(function(index, item) {
      if (item.href.includes('freya-layout')) {
        var href = item.href;
        href = href.replace('freya-layout', 'serenity-layout');
        href = href.replace('-dark', '');
        href = href.replace('-light', '');
        item.setAttribute('href', href);
      }
    });
  }

  if (theme.includes('freya-ivy-')) {
    $cssLinks.each(function(index, item) {
      if (item.href.includes('serenity-layout')) {
        var href = item.href;
        href = href.replace('serenity-layout', 'freya-layout');

        if (item.href.includes('-dark')) {
          href = href.replace('layout-ivy', 'layout-ivy-dark');
        } else {
          href = href.replace('layout-ivy', 'layout-ivy-light');
        }

        item.setAttribute('href', href);
      }
    });
  }
}