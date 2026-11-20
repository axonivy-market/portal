/* ===========================================================================
   View mode of the Smart Statistic page.

   Three kinds of chart live here at once and they are drawn by two different
   mechanisms:

     - pinned cards and grid cards carry .js-statistic-chart and a chart id,
       and statistic.js fetches each one from the REST endpoint;
     - the answer card is an unsaved proposal with no id, so it is drawn from
       the preview JSON the bean pushes back as an AJAX callback parameter.

   The answer card therefore must NOT carry .js-statistic-chart: initClientCharts
   would try to fetch a chart that does not exist yet, and previewChart() draws
   into charts[0], which would be a grid card. smartStatRenderAnswer() calls the
   underlying generateChart() directly on the one element it means.
   =========================================================================== */

var smartStatGrid = null;

/* Set by the page so the mode-switch path can re-init without re-reading EL. */
window.smartStatGridConfig = window.smartStatGridConfig || {};

function smartStatGridElement() {
  return document.querySelector('.smart-grid.grid-stack');
}

function smartStatSerializeNodes() {
  return smartStatGrid.engine.nodes.map(function (node) {
    return { id: node.id, x: node.x, y: node.y, w: node.w, h: node.h };
  });
}

/** Survives ajax updates, so the section stays in arrange mode across a re-render. */
var smartStatGridArranging = false;

function smartStatToggleGridArrange() {
  smartStatGridArranging = !smartStatGridArranging;
  smartStatApplyGridArrange();
}

function smartStatApplyGridArrange() {
  document.body.classList.toggle('smart-grid-arranging', smartStatGridArranging);
  if (smartStatGrid) {
    smartStatGrid.enableMove(smartStatGridArranging);
    smartStatGrid.enableResize(smartStatGridArranging);
  }
}

function initSmartStatGrid() {
  var element = smartStatGridElement();
  if (!element || typeof GridStack === 'undefined') {
    return;
  }

  // init(opts, element), not initAll(): initAll grabs every .grid-stack on the page, which
  // would also capture an unrelated dashboard fragment if one is ever embedded here.
  // Locked unless the section is being arranged. That is not only an affordance: gridstack marks
  // the handle draggable="true", and a native draggable ancestor swallows the first click on any
  // button inside it - which is what made the card menu need two clicks.
  smartStatGrid = GridStack.init({
    column: 12,
    cellHeight: 100,
    handle: '.smart-card__handle',
    disableDrag: !smartStatGridArranging,
    disableResize: !smartStatGridArranging,
    resizable: { handles: 'e, se, s, sw, w' }
  }, element);

  // Fires for both drag and resize.
  smartStatGrid.on('change', function () {
    // Below its minWidth gridstack collapses to a single column. Saving then would flatten
    // the real layout, so skip it - the same guard the Portal dashboard uses.
    if (smartStatGrid.opts.minWidth >= smartStatGrid.el.clientWidth) {
      return;
    }
    saveSmartGridLayout([
      { name: 'nodes', value: JSON.stringify(smartStatSerializeNodes()) }
    ]);
  });
}

/* ---------------------------------------------------------------------------
   How a card arranges itself.

   The width decides, not the user: 11-12 columns puts the texts in a column
   beside the chart, 5-10 puts them in a strip under it, anything narrower
   tucks them behind a footer tab. The server renders the mode from the stored
   width so the first paint is right; from then on the browser measures, which
   is what makes a drag-resize rearrange the card as you let go of it.
   --------------------------------------------------------------------------- */

var SMART_SIDEBAR_FROM = 11;
var SMART_CAPTION_FROM = 5;
var smartStatCardSizes = null;

/** One twelfth of the board, so a pinned card is measured on the same scale as a grid card. */
function smartStatColumnWidth() {
  var host = smartStatGridElement() || document.querySelector('.smart-page');
  var width = host ? host.clientWidth : 0;
  return width > 0 ? width / 12 : 0;
}

function smartStatApplyCardLayouts() {
  var unit = smartStatColumnWidth();
  if (unit <= 0) {
    return;
  }
  document.querySelectorAll('.smart-card[data-layout]').forEach(function (card) {
    // Rounded because gridstack's margin makes a card a few pixels narrower than its columns.
    var columns = Math.round(card.offsetWidth / unit);
    var mode = columns >= SMART_SIDEBAR_FROM
      ? 'SIDEBAR'
      : (columns >= SMART_CAPTION_FROM ? 'CAPTION' : 'TUCKED');
    if (card.dataset.layout === mode) {
      return;
    }
    card.dataset.layout = mode;
    // Opening the tab is only meaningful while tucked; widening the card shows the texts anyway.
    if (mode !== 'TUCKED') {
      card.classList.remove('smart-card--open');
    }
  });
}

/**
 * Re-measures on any size change, whatever caused it - a drag-resize, the window, or the pinned
 * row reflowing. Re-attached after every AJAX update, since those replace the cards.
 */
function smartStatWatchCardWidths() {
  if (typeof ResizeObserver === 'undefined') {
    smartStatApplyCardLayouts();
    return;
  }
  if (smartStatCardSizes) {
    smartStatCardSizes.disconnect();
  }
  smartStatCardSizes = new ResizeObserver(smartStatApplyCardLayouts);
  document.querySelectorAll('.smart-card[data-layout]').forEach(function (card) {
    smartStatCardSizes.observe(card);
  });
  smartStatApplyCardLayouts();
}

/** The tucked mode keeps both texts behind a footer tab; this is what opens them. */
function smartStatToggleTucked(link) {
  var card = link && link.closest ? link.closest('.smart-card') : null;
  if (!card) {
    return;
  }
  var open = card.classList.toggle('smart-card--open');
  link.setAttribute('aria-expanded', String(open));
  var config = window.smartStatGridConfig || {};
  var label = link.querySelector('.smart-layout__tab-label') || link;
  label.textContent = open
    ? (config.aiEvalHide || 'Hide the AI evaluation')
    : (config.aiEvalShow || 'Show the AI evaluation');
}

/**
 * Draws every saved chart on the page - pinned row and grid alike. statistic.js scans the
 * whole document and fetches each one by id, so this must run after the grid has laid out.
 */
function smartStatDrawCharts() {
  var config = window.smartStatGridConfig;
  if (!config || !config.apiUri || typeof initClientCharts !== 'function') {
    return;
  }
  // Not while the editor is open. initClientCharts scans the WHOLE document for
  // .js-statistic-chart and POSTs for each one by its data-chart-id; the editor's preview
  // canvas carries both, but "preview-statistic-chart" is not a saved chart, so the endpoint
  // answers "Chart with ID preview-statistic-chart not found" and that response body is
  // painted straight into the canvas. Nothing is lost by skipping: the board is not rendered
  // while the modal is up, and the preview is drawn by handlePreviewChart instead.
  if (document.querySelector('.smart-config-modal')) {
    return;
  }
  smartStatWatchSkeletons();
  setTimeout(function () {
    initClientCharts(config.apiUri, config.language, config.datePattern, config.locale);
  }, 50);
}

/**
 * Lifts each chart's skeleton once its canvas holds something.
 *
 * statistic.js fetches every chart separately and ends each one - drawn, empty or failed - by
 * emptying the canvas element and appending to it, so its first child is the signal. A timer
 * would either uncover an empty box or leave a drawn chart hidden.
 */
function smartStatWatchSkeletons() {
  document.querySelectorAll('.smart-region--chart').forEach(function (region) {
    var canvas = region.querySelector('.js-statistic-chart');
    if (!canvas) {
      return;
    }
    if (canvas.childElementCount > 0) {
      region.classList.add('smart-region--ready');
      return;
    }
    region.classList.remove('smart-region--ready');
    var observer = new MutationObserver(function () {
      if (canvas.childElementCount > 0) {
        region.classList.add('smart-region--ready');
        observer.disconnect();
      }
    });
    observer.observe(canvas, { childList: true });
  });
}

/**
 * Draws the unsaved proposal from the preview JSON.
 *
 * Deliberately not previewChart(): that helper targets charts[0] and, on failure, calls
 * PF('previewButton') unguarded - neither of which holds in view mode.
 */
function smartStatRenderAnswer(xhr, status, args) {
  if (!args || !args.jsonResponse) {
    return;
  }
  var element = document.getElementById('smart-answer-canvas');
  if (!element || typeof generateChart !== 'function') {
    return;
  }
  var config = window.smartStatGridConfig || {};
  if (typeof initConfig === 'function') {
    initConfig(config.language, config.locale, config.datePattern);
  }
  try {
    var chart = generateChart(element, JSON.parse(args.jsonResponse));
    if (chart) {
      chart.render();
    }
  } catch (error) {
    console.error('Smart statistic: could not draw the proposed chart.', error);
  }
  smartStatRevealDraft(element);
}

/** The draft sits below the pinned row, so bring the eye to it rather than expecting a scroll. */
function smartStatRevealDraft(element) {
  var card = element.closest ? element.closest('.smart-card--draft') : null;
  if (!card || !card.scrollIntoView) {
    return;
  }
  var still = window.matchMedia && window.matchMedia('(prefers-reduced-motion: reduce)').matches;
  card.scrollIntoView({ block: 'nearest', behavior: still ? 'auto' : 'smooth' });
}

/**
 * Runs after every AJAX update that can change which cards are on the page.
 *
 * Auto-refresh timers are cleared first: statistic.js keeps them in a module-level list and
 * they would otherwise keep firing against elements that no longer exist.
 */
function smartStatAfterModeSwitch() {
  if (typeof clearChartInterval === 'function') {
    clearChartInterval();
  }
  smartStatGrid = null;
  initSmartStatGrid();
  smartStatWatchCardWidths();
  smartStatDrawCharts();
  // Arranging the board is client-only state, so it has to be re-applied after a re-render.
  smartStatApplyGridArrange();
  smartStatSyncConfigModalState();
  smartStatPreviewOnOpen();
}

/**
 * Draws the chart the editor has just been opened onto.
 *
 * The page's "preview" remote command carries autoRun, but autoRun only fires the script it
 * renders with the page - and the command sits outside the panel the mode switch updates, so it
 * is neither re-rendered nor re-armed. Entering the editor through a card's Edit action therefore
 * used to leave the preview pane on its empty message until "Generate preview" was pressed by
 * hand, while entering the page with a chart id drew it immediately. This closes that gap by
 * calling the same command, so both routes draw through one pipeline.
 *
 * The flag is the bean's: a blank new chart has nothing to draw and is deliberately left alone.
 */
function smartStatPreviewOnOpen() {
  var modal = document.querySelector('.smart-config-modal');
  if (!modal || modal.getAttribute('data-preview-on-open') !== 'true') {
    return;
  }
  if (typeof preview === 'function') {
    preview();
  }
}

/**
 * Backdrop click or the modal's own close button: both just mean "leave the editor".
 *
 * By class, not by client id: the editor is a composite component on SmartStatisticPage,
 * so its ids carry that component's prefix, while the older SmartStatisticConfiguration
 * page renders the same markup unprefixed. A class is the one handle both agree on.
 */
function smartStatCancelConfig() {
  var cancel = document.querySelector('.smart-config__cancel');
  if (cancel) {
    cancel.click();
  }
}

/** The page behind the modal must not scroll along with it. */
function smartStatSyncConfigModalState() {
  document.body.classList.toggle('smart-config-modal-open', !!document.querySelector('.smart-config-modal'));
}

document.addEventListener('keydown', function(event) {
  if (event.key === 'Escape' && document.querySelector('.smart-config-modal')) {
    smartStatCancelConfig();
  }
});

/**
 * Same reasoning as the prompt bar: a disabled input is skipped when JSF decodes the request,
 * so the typed question would never reach the bean. readOnly looks the same and still submits.
 */
var smartStatAskInFlight = false;

function smartStatAskBusy(busy) {
  var bar = document.querySelector('.smart-ask');
  var field = smartStatAskField();
  smartStatAskInFlight = busy;
  if (bar) {
    bar.classList.toggle('smart-ask--busy', busy);
  }
  if (field) {
    field.readOnly = busy;
    field.classList.toggle('smart-ask__input--busy', busy);
  }
}

function smartStatAskField() {
  return document.querySelector('.smart-ask__input');
}

function smartStatSubmitAsk() {
  if (smartStatAskInFlight) {
    return;
  }
  var button = document.querySelector('.smart-ask__submit');
  if (button) {
    button.click();
  }
}

function smartStatAskKeyDown(event) {
  if (event.key !== 'Enter') {
    return true;
  }
  event.preventDefault();
  smartStatSubmitAsk();
  return false;
}

/**
 * Clicking a suggestion fills the box and then presses Ask.
 *
 * Going through the input rather than straight to the bean means the user sees the question
 * that is about to be sent, and there is only ever one submit path to reason about.
 */
function smartStatAskSuggestion(chip) {
  if (smartStatAskInFlight) {
    return;
  }
  var field = smartStatAskField();
  if (!field || !chip) {
    return;
  }
  field.value = (chip.textContent || '').trim();
  // Let PrimeFaces and any listener see the change before the request is built.
  field.dispatchEvent(new Event('input', { bubbles: true }));
  field.dispatchEvent(new Event('change', { bubbles: true }));
  smartStatSubmitAsk();
}

/** A div with role="button" has to answer Enter and Space itself. */
function smartStatSuggestionKeyDown(event, chip) {
  if (!smartStatIsActivationKey(event)) {
    return true;
  }
  event.preventDefault();
  smartStatAskSuggestion(chip);
  return false;
}

function smartStatIsActivationKey(event) {
  return event.key === 'Enter' || event.key === ' ' || event.key === 'Spacebar';
}

/* ---------------------------------------------------------------------------
   What the agent said about the chart it built.

   Collapsed by default: the chart is the answer and the commentary is only
   wanted when the answer surprises you. Purely client side, so every AJAX
   update re-collapses it for the next question - which is the behaviour you
   want, since the text belongs to the answer that has just been replaced.
   --------------------------------------------------------------------------- */

/** The draft's own explanation, scoped to its card so it opens next to the chart it describes. */
function smartStatToggleWhy(link) {
  var card = link && link.closest ? link.closest('.smart-card') : null;
  var panel = card ? card.querySelector('.smart-thinking') : null;
  if (!panel) {
    return;
  }
  var wasHidden = panel.hasAttribute('hidden');
  if (wasHidden) {
    panel.removeAttribute('hidden');
  } else {
    panel.setAttribute('hidden', 'hidden');
  }
  link.setAttribute('aria-expanded', String(wasHidden));
}

function smartStatToggleThinking(link) {
  var panel = document.querySelector('.smart-thinking');
  if (!panel || !link) {
    return;
  }
  var config = window.smartStatGridConfig || {};
  var wasHidden = panel.hasAttribute('hidden');
  if (wasHidden) {
    panel.removeAttribute('hidden');
  } else {
    panel.setAttribute('hidden', 'hidden');
  }
  // Write into the label span, not the link: the link also holds the icon element, which
  // setting textContent on it would delete.
  var label = link.querySelector('.smart-thinking__label') || link;
  label.textContent = wasHidden
    ? (config.thinkingHide || 'Hide thinking')
    : (config.thinkingShow || 'Show thinking');
  link.setAttribute('aria-expanded', String(wasHidden));
}

var smartStatInsightTrigger = null;

/** Reading a chart takes seconds; the spark spins until the reply comes back. */
/** The icon may be a plain <i> (command link) or a PrimeFaces <span class="ui-button-icon">. */
function smartStatInsightIcon(link) {
  return link ? link.querySelector('.ui-button-icon, i') : null;
}

function smartStatInsightBusy(link) {
  if (!link || !link.classList) {
    return;
  }
  smartStatInsightTrigger = link;
  link.classList.add('smart-card__spark--busy');
  var icon = smartStatInsightIcon(link);
  if (icon) {
    icon.className = icon.className.replace('ti-sparkles', 'ti-loader-2');
  }
}

/**
 * Reveals the insight text next to the trigger that asked for it, streamed in rather than
 * dropped in all at once. No view-mode update: the region already exists in the DOM, just
 * hidden, so this only has to un-hide it and fill in the text.
 */
function smartStatShowInsight(xhr, status, args) {
  var link = smartStatInsightTrigger;
  smartStatInsightTrigger = null;
  if (!link) {
    return;
  }
  link.classList.remove('smart-card__spark--busy');
  var icon = smartStatInsightIcon(link);
  if (icon) {
    icon.className = icon.className.replace('ti-loader-2', 'ti-sparkles');
  }
  if (!args || typeof args.insightText !== 'string') {
    return;
  }
  var card = link.closest('.smart-card');
  if (!card) {
    return;
  }
  var texts = card.querySelector('.smart-layout__texts');
  if (texts) {
    texts.classList.remove('smart-layout__texts--hidden');
  }
  var layout = card.querySelector('.smart-layout');
  if (layout) {
    layout.classList.add('smart-layout--texts');
  }
  var config = window.smartStatGridConfig || {};
  if (config.insightRefresh) {
    link.setAttribute('title', config.insightRefresh);
    link.setAttribute('aria-label', config.insightRefresh);
  }
  smartStatStreamText(card.querySelector('.smart-card__insight-text'), args.insightText);
}

var smartStatStreamSeq = 0;

/** One character at a time, cancelling cleanly if the same text node is asked for again. */
function smartStatStreamText(el, text) {
  if (!el) {
    return;
  }
  var seq = ++smartStatStreamSeq;
  el.dataset.streamSeq = String(seq);
  var reduceMotion = window.matchMedia && window.matchMedia('(prefers-reduced-motion: reduce)').matches;
  if (!text || reduceMotion) {
    el.textContent = text || '';
    return;
  }
  el.textContent = '';
  var i = 0;
  var STEP_MS = 12;
  (function tick() {
    if (el.dataset.streamSeq !== String(seq)) {
      return;
    }
    i += 1;
    el.textContent = text.slice(0, i);
    if (i < text.length) {
      window.setTimeout(tick, STEP_MS);
    }
  })();
}

/**
 * Covers the preview frame with its skeleton while a preview is being fetched.
 *
 * The button that starts the fetch runs global="false", so this stands in for the page-wide
 * progress loader: the wait is shown where the answer will appear. Toggled on the frame rather
 * than the skeleton itself, matching how a board card reveals its own chart.
 */
var smartStatPreviewBusySince = 0;

/** Below this a placeholder reads as a flicker, which looks like the button did nothing. */
var SMART_PREVIEW_MIN_MS = 400;

function smartStatPreviewBusy(busy) {
  var frame = document.querySelector('.smart-preview__frame');
  if (!frame) {
    return;
  }
  if (busy) {
    smartStatPreviewBusySince = new Date().getTime();
    frame.classList.add('smart-preview--busy');
    return;
  }
  // The chart itself is drawn as soon as the answer lands - this only holds the skeleton over
  // it long enough to have been seen, so a fast reply still reads as "it went and fetched it".
  var elapsed = new Date().getTime() - smartStatPreviewBusySince;
  window.setTimeout(function () {
    frame.classList.remove('smart-preview--busy');
  }, Math.max(0, SMART_PREVIEW_MIN_MS - elapsed));
}

/**
 * Draws the configuration preview.
 *
 * The counterpart of smartStatRenderAnswer(): this one is the saved-chart path and can use
 * previewChart(), because in configuration mode the preview canvas IS charts[0] and
 * PF('previewButton') does exist.
 *
 * Reads the three locale values from smartStatGridConfig rather than taking them as
 * arguments, so every caller is a bare oncomplete handler.
 */
function handlePreviewChart(xhr, status, args) {
  if (!args || !args.jsonResponse) {
    return;
  }
  var config = window.smartStatGridConfig || {};
  previewChart(JSON.parse(args.jsonResponse), config.language, config.datePattern, config.locale);
}

/**
 * PrimeFaces renders the multi-select permissions autocomplete without the roles a screen
 * reader needs to announce it as a listbox. Patched here rather than reported upstream
 * because the markup is generated per widget.
 */
function smartStatPatchAutoCompleteAria() {
  var tokens = document.querySelectorAll('.ui-autocomplete-input-token');
  Array.prototype.forEach.call(tokens, function (token) {
    if (!token.getAttribute('role')) {
      token.setAttribute('role', 'option');
    }
  });

  // The label's real client id, read off the element: hardcoding it cannot work now that
  // the editor is a composite component, and the id it used to name never existed anyway.
  var label = document.querySelector('[id$=":permission-label"]');
  var containers = document.querySelectorAll('.ui-autocomplete-multiple-container');
  Array.prototype.forEach.call(containers, function (container) {
    if (label && !container.getAttribute('aria-labelledby')) {
      container.setAttribute('aria-labelledby', label.id);
    }
  });
}

document.addEventListener('DOMContentLoaded', function () {
  initSmartStatGrid();
  smartStatWatchCardWidths();
  smartStatDrawCharts();
  smartStatSyncConfigModalState();
  smartStatPatchAutoCompleteAria();
});
