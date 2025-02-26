if (document) {
  const pathName = window.location.pathname;
  const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));
  const searchProcessesURL = "/api/global-search/processes";
  const searchTasksURL = "/api/global-search/tasks";
  const searchCasesURL = "/api/global-search/cases";
  const searchId = "global-search-component:global-search-data";
  const eleSearchId = "[id='global-search-component:global-search-data']";
  const processTabIndex = 0;
  const taskTabIndex = 1;
  const caseTabIndex = 2;
  const startsWithTask = 'task: ';
  const startsWithProcess = 'process: ';
  const startsWithCase = 'case: ';
  const waitTime = 500;
  const PROCESS_TAB = 'PROCESS_TAB';
  const TASK_TAB = 'TASK_TAB';
  const CASE_TAB = 'CASE_TAB';
  const requestInit = body => {
    return {
      method: "POST",
      headers: {"X-Requested-By": "ivy", "Content-Type": "application/json", "Accept": "application/json"},
      body
    }
  }

  $(document).ready(function () {
    $(eleSearchId).val('');

    $(".search-bar.process-tab-title-global").click(() => {
      focusSearchBox();
      let keyword = $(eleSearchId).val().toLowerCase();
      if (keyword.startsWith(startsWithTask)) {
        keyword = keyword.replace(startsWithTask, startsWithProcess);
      }
      if (keyword.startsWith(startsWithCase)) {
        keyword = keyword.replace(startsWithCase, startsWithProcess);
      }
      $(eleSearchId).val(keyword);

      searchProcess(keyword);
    });
    $(".search-bar.task-tab-title-global").click(() => {
      focusSearchBox();
      let keyword = $(eleSearchId).val().toLowerCase();
      if (keyword.startsWith(startsWithCase)) {
        keyword = keyword.replace(startsWithCase, startsWithTask);
      }
      if (keyword.startsWith(startsWithProcess)) {
        keyword = keyword.replace(startsWithProcess, startsWithTask);
      }
      $(eleSearchId).val(keyword);

      searchTask(keyword);
    });
    $(".search-bar.case-tab-title-global").click(() => {
      focusSearchBox();
      let keyword = $(eleSearchId).val().toLowerCase();
      if (keyword.startsWith(startsWithTask)) {
        keyword = keyword.replace(startsWithTask, startsWithCase);
      }
      if (keyword.startsWith(startsWithProcess)) {
        keyword = keyword.replace(startsWithProcess, startsWithCase);
      }
      $(eleSearchId).val(keyword);

      searchCase(keyword);
    });
  });

  $(eleSearchId).on('keyup', () => {
      $(eleSearchId).click();
  });

  $(eleSearchId).click((e) => {
    if (!window.matchMedia("(max-width: 767px)").matches) {
      $(eleSearchId).addClass('global-large-search-bar').removeClass('global-small-search-bar');
    } else {
      $(eleSearchId).removeClass('global-large-search-bar').addClass('global-small-search-bar');
    }
    let processTab = $("li.ui-tabs-header.search-bar[aria-selected='true'].process-tab-title-global").length;
    let taskTab = $("li.ui-tabs-header.search-bar[aria-selected='true'].task-tab-title-global").length;
    let caseTab = $("li.ui-tabs-header.search-bar[aria-selected='true'].case-tab-title-global").length;
    if (processTab) {
      search(e.target.value, PROCESS_TAB);
    } else if (taskTab) {
      search(e.target.value, TASK_TAB);
    } else if (caseTab) {
      search(e.target.value, CASE_TAB);
    }
  });

  function resetSearchBar() {
    if (!window.matchMedia("(max-width: 767px)").matches) {
      $(eleSearchId).removeClass('global-large-search-bar').addClass('global-small-search-bar');
    } else {
      $(eleSearchId).addClass('global-default-search-bar').removeClass('global-large-search-bar').removeClass('global-small-search-bar');
    }
  }

  function viewAllResults(tabIndex) {
    rcResetAllId();
    handlePromise(tabIndex).then(() => {
      portalGlobalSearch([{name: 'activeTabIndex', value: getTabActiveIndex()}]);
      return false;
    })

  }

  function getTabActiveIndex() {
    let activeTab = $("li.ui-tabs-header.search-bar[aria-selected='true']");
    return activeTab.attr('data-index') * 1;
  }

  function handlePromise(tabIndex) {
    return new Promise((resolve, reject) => {
      let searchInput = $('#global-search-component\\:global-search-data');
      let searchValue = searchInput.val().toLowerCase();
      if (tabIndex === taskTabIndex && !searchValue.startsWith(startsWithTask)) {
        searchInput.val(startsWithTask + searchValue);
      } else if (tabIndex === caseTabIndex && !searchValue.startsWith(startsWithCase)) {
        searchInput.val(startsWithCase + searchValue);
      }
      resolve('success');
      reject('fail');
    })
  }

  function focusSearchBox() {
    $(eleSearchId).focus();
  }

  let timer;

  document.getElementById(searchId).addEventListener('keyup', event => {
    clearTimeout(timer);
    timer = setTimeout(() => {
      doneTyping(event);
    }, waitTime);
  });

  function doneTyping(event) {
    if (event.key === 'Enter') {
      portalGlobalSearch([{name: 'activeTabIndex', value: getTabActiveIndex()}]);
      return false;
    } else {
      searchHandler();
    }
  }

  function analyzeInputSearch(input) {
    let activeTab = $("li.ui-tabs-header.search-bar[aria-selected='true']");
    let tabIndex = activeTab.attr('data-index') * 1;
    let keyword = input;
    let explicit = false;
    if (input.startsWith(startsWithProcess)) {
      keyword = input.slice(input.indexOf(':') + 1);
      tabIndex = $("li.ui-tabs-header.search-bar.process-tab-title-global").attr('data-index') * 1
      explicit = true;
    }
    if (input.startsWith(startsWithTask)) {
      keyword = input.slice(input.indexOf(':') + 1);
      tabIndex = $("li.ui-tabs-header.search-bar.task-tab-title-global").attr('data-index') * 1;
      explicit = true;
    }
    if (input.startsWith(startsWithCase)) {
      keyword = input.slice(input.indexOf(':') + 1);
      tabIndex = $("li.ui-tabs-header.search-bar.case-tab-title-global").attr('data-index') * 1
      explicit = true;
    }

    return {keyword, tabIndex, explicit}
  }

  function searchHandler() {
    let inputSearch = $(eleSearchId).val().toLowerCase();
    let analyzedInput = analyzeInputSearch(inputSearch);
    let keyword = analyzedInput.keyword;
    let tabIndex = analyzedInput.tabIndex;
    if (analyzedInput.explicit) {
      let activeTab = $("li.ui-tabs-header.search-bar[data-index='" + tabIndex + "']");
      activeTab.click();
    } else {
      let processTab = $("li.ui-tabs-header.search-bar[aria-selected='true'].process-tab-title-global").length;
      let taskTab = $("li.ui-tabs-header.search-bar[aria-selected='true'].task-tab-title-global").length;
      let caseTab = $("li.ui-tabs-header.search-bar[aria-selected='true'].case-tab-title-global").length;
      if (processTab) {
        search(keyword.trim(), PROCESS_TAB);
      } else if (taskTab) {
        search(keyword.trim(), TASK_TAB);
      } else if (caseTab) {
        search(keyword.trim(), CASE_TAB);
      }
    }
  }

  function search(keyword, tabCategory) {
    if (PROCESS_TAB === tabCategory) {
      searchProcess(keyword);
    } else if (TASK_TAB === tabCategory) {
      searchTask(keyword);
    } else if (CASE_TAB === tabCategory) {
      searchCase(keyword);
    }
  }

  function handleError(err) {
    console.log(err);
  }

  function handleUISearchResults($parent, data) {
    let viewAllLink = $parent.siblings('.global-search-all-results')[0];
    let noResults = $parent.siblings('.global-search-no-results')[0];
    if (data && data.totalResult > 0) {
      $(viewAllLink).removeClass('hidden');
      $(viewAllLink).find('a').html(data.viewAllText + ' (' + data.totalResult + ')')
      $(noResults).addClass('hidden');
    } else {
      $(viewAllLink).addClass('hidden');
      $(noResults).removeClass('hidden');
    }
    initConfirmDialog()
  }

  function searchProcess(keyword) {
    if (keyword.startsWith(startsWithProcess)) {
      keyword = keyword.slice(keyword.indexOf(':') + 1);
    }
    let body = JSON.stringify({"query": keyword})
    fetch(baseURL + searchProcessesURL, requestInit(body))
      .then((response) => {
        response.json().then(data => {
          let $resultsContainer = $("#process-search-results");
          $resultsContainer.html("");
          if (data) {
            data.results.forEach((rs, index) => {
              document.getElementById("process-search-results").innerHTML += processComponent(rs);
            });
            handleUISearchResults($resultsContainer, data);
          }
        }, err => handleError(err));
      }, err => handleError(err));

  }

  function searchTask(keyword) {
    if (keyword.startsWith(startsWithTask)) {
      keyword = keyword.slice(keyword.indexOf(':') + 1);
    }
    let body = JSON.stringify({"query": keyword})
    fetch(baseURL + searchTasksURL, requestInit(body))
      .then((response) => {
        response.json().then(data => {
          let promise = new Promise((resolve, reject) => {
            let $resultsContainer = $("#task-search-results");
            $resultsContainer.html("");
            if (data) {
              data.results.forEach((rs, index) => {
                document.getElementById("task-search-results").innerHTML += taskComponent(rs);
              });
            }
            resolve($resultsContainer);
            reject('fail');
          });
          promise.then(results => {
            handleUISearchResults(results, data)
          });
        }, err => handleError(err));
      }, err => handleError(err));
  }

  function searchCase(keyword) {
    if (keyword.startsWith(startsWithCase)) {
      keyword = keyword.slice(keyword.indexOf(':') + 1);
    }
    let body = JSON.stringify({"query": keyword})
    fetch(baseURL + searchCasesURL, requestInit(body))
      .then((response) => {
        response.json().then(data => {
          let $resultsContainer = $("#case-search-results");
          $resultsContainer.html("");
          if (data) {
            data.results.forEach((rs, index) => {
              document.getElementById("case-search-results").innerHTML += caseComponent(rs);
            });
            handleUISearchResults($resultsContainer, data);
          }
        }, err => handleError(err));
      }, err => handleError(err));
  }

  function initConfirmDialog() {
    if (PrimeFaces.widgets['search-task-losing-confirmation-dialog']) {
      $("a.search-task-list-item").off('click').on('click', e => {
        PF('search-task-losing-confirmation-dialog').show();
        let id = $(e.target).closest('.search-task-list-item').find('.search-item-result').attr("data-id");
        rcUpdateCurrentItemId([{name: 'id', value: id}, {name: 'type', value: 'TASK'}]);
        return false;
      });
      $("a.search-case-list-item").off('click').on('click', e => {
        PF('search-task-losing-confirmation-dialog').show();
        let id = $(e.target).closest('.search-case-list-item').find('.search-item-result').attr("data-id");
        rcUpdateCurrentItemId([{name: 'id', value: id}, {name: 'type', value: 'CASE'}]);
        return false;
      });
      $("a.search-process-list-item").off('click').on('click', e => {
        PF('search-task-losing-confirmation-dialog').show();
        let id = $(e.target).closest('.search-process-list-item').find('.search-item-result').attr("data-id");
        rcUpdateCurrentItemId([{name: 'id', value: id}, {name: 'type', value: 'PROCESS'}]);
        return false;
      });

    }
  }




  let processComponent = (process) => {
    return '<a href="' + process.link + '" class="task-even-row full-mode task-start-list-item js-task-start-list-item search-process-list-item">' +
      '   <div class="task-start-link js-task-start-link">' +
      '     <div class="task-priority-item">' +
      '       <span class="priority-cell search-item-result"  data-id="' + process.link + '">' +
      '         <span class="priority-icon">' +
      '           <i class="' + process.icon + '"></i>' +
      '         </span>' +
      '       </span>' +
      '     </div>' +
      '     <div class="task-start-info">' +
      '       <span class="name-cell">' + process.name + '</span>' +
      '     </div>' +
      '    </div>' +
      '</a>';
  }

  let taskComponent = (task) => {
    let priority = task.priority;
    let iconClass;
    switch (priority) {
      case 'LOW':
        iconClass = "priority si si-navigation-down-circle low-priority";
        break;
      case 'NORMAL':
        iconClass = "priority si si-navigation-right-circle-1 normal-priority";
        break;
      case 'HIGH':
        iconClass = "priority si si-navigation-up-circle high-priority";
        break;
      default:
        iconClass = "priority si si-alert-circle exception-priority";
        break;
    }
    return '<a href="' + task.link + '" class="task-even-row full-mode task-start-list-item js-task-start-list-item search-task-list-item">' +
      '  <div class="task-start-link js-task-start-link">' +
      '     <div class="task-priority-item search-item-result" data-id="' + task.uuid + '">' +
      '       <span class="priority-cell search-item">' +
      '        <span class="priority-icon">' +
      '          <i class="' + iconClass + '"></i>' +
      '      </span>' +
      '     </span>' +
      '   </div>' +
      '  <div class="task-start-info">' +
      '    <span class="name-cell">' + task.name +
      '    </span>' +
      '  </div>' +
      ' </div>' +
      '</a>';
  }

  let caseComponent = (caze) => {
    return '<a href="' + caze.link + '" class="task-even-row full-mode task-start-list-item js-task-start-list-item search-case-list-item">' +
      '     <div class="task-start-link js-task-start-link">' +
      '       <div class="task-priority-item">' +
      '         <span class="priority-cell search-item-result" data-id="' + caze.uuid + '">' +
      '           <span class="priority-icon">' +
      '             <i class="si si-layout-bullets"></i>' +
      '           </span>' +
      '         </span>' +
      '       </div>' +
      '       <div class="task-start-info">' +
      '         <span class="name-cell">' + caze.name +
      '         </span>' +
      '       </div>' +
      '     </div>' +
      '</a>';
  }
}
