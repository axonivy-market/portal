function initActionMenuKeyboard(panel) {
  var content = panel.jq.children('.ui-overlaypanel-content');
  var items = content.find('a.portal-action-menu-item').filter(':visible').not('.ui-state-disabled');
  if (!items.length) {
    return;
  }
  content.attr('role', 'menu');
  if (panel.jq.attr('aria-label')) {
    content.attr('aria-label', panel.jq.attr('aria-label'));
  }
  items.parentsUntil(content).attr('role', 'none');
  items.attr({ 'role': 'menuitem', 'tabindex': '-1' });
  items.first().trigger('focus');

  content.off('keydown.actionMenu').on('keydown.actionMenu', function (event) {
    var index = items.index(event.target);
    if (index === -1) {
      return;
    }
    var next;
    switch (event.key) {
      case 'ArrowDown':
        next = items.eq((index + 1) % items.length);
        break;
      case 'ArrowUp':
        next = items.eq((index - 1 + items.length) % items.length);
        break;
      case 'Home':
        next = items.first();
        break;
      case 'End':
        next = items.last();
        break;
      case ' ':
        event.preventDefault();
        event.target.click();
        return;
      case 'Escape':
      case 'Tab':
        event.preventDefault();
        panel.hide();
        if (panel.targetElement) {
          panel.targetElement.trigger('focus');
        }
        return;
      default:
        return;
    }
    event.preventDefault();
    next.trigger('focus');
  });
}
