/* ===========================================================================
   Natural-language bar of the Smart Statistic configuration page.

   Namespaced smartStat* because the user-to-user Portal Chat feature (chat.js)
   already owns a global `chat` object on the same page.
   =========================================================================== */

function smartStatPromptField() {
  return document.querySelector('.smart-prompt__input');
}

/**
 * Locks the bar while the agent is working.
 *
 * readOnly, NOT disabled: PrimeFaces serialises the form after onstart runs, and JSF skips
 * disabled inputs when decoding, so disabling here would drop the very prompt being sent.
 */
function smartStatPromptBusy(busy) {
  var field = smartStatPromptField();
  if (field) {
    field.readOnly = busy;
    field.classList.toggle('smart-prompt__input--busy', busy);
  }
  var row = document.querySelector('.smart-prompt');
  if (row) {
    row.classList.toggle('smart-prompt--busy', busy);
    row.setAttribute('aria-busy', busy ? 'true' : 'false');
  }
  if (!busy && field) {
    field.focus();
  }
}

/** Enter submits; the button keeps its own click handler for the mouse path. */
function smartStatPromptKeyDown(event) {
  if (event.key !== 'Enter') {
    return true;
  }
  event.preventDefault();
  var field = smartStatPromptField();
  if (!field || !field.value || field.value.trim() === '' || field.readOnly) {
    return false;
  }
  if (window.PF && PF('applyPromptButton')) {
    PF('applyPromptButton').jq.trigger('click');
  }
  return false;
}

document.addEventListener('DOMContentLoaded', function () {
  if (window.$) {
    // Any AJAX failure must release the bar, otherwise one bad request leaves it locked
    // and the only recovery is a reload.
    $(document).on('pfAjaxError', function () {
      smartStatPromptBusy(false);
    });
  }
});
