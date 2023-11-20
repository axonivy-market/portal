const pathName = window.location.pathname;
const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));
const searchProcessesURL = "/api/global-search/processes";
const searchTasksURL = "/api/global-search/tasks";
const searchCasesURL = "/api/global-search/cases";
const searchId = "quick-global-search-component:global-search-data";
const eleSearchId = "[id='quick-global-search-component:global-search-data']";
const PROCESS_TAB_INDEX = '0';
const TASK_TAB_INDEX = '1';
const CASE_TAB_INDEX = '2';

$(document).ready(function () {
    $(eleSearchId).val('');
    $(".process-tab-title").click(() => {
        focusSearchBox();
        searchProcess($(eleSearchId).val());
    });
    $(".task-tab-title").click(() => {
        focusSearchBox();
        searchTask($(eleSearchId).val());
    });
    $(".case-tab-title").click(() => {
        focusSearchBox();
        searchCase($(eleSearchId).val());
    });
});

$(eleSearchId).click((e) => {
    if (!window.matchMedia("(max-width: 767px)").matches) {
        $(eleSearchId).outerWidth(600);
    } else {
        $(eleSearchId).outerWidth(200);
    }

    search(e.target.value);
});

function resetSearchBar() {
    if (!window.matchMedia("(max-width: 767px)").matches) {
        $(eleSearchId).outerWidth(200);
    } else {
        $(eleSearchId).css('width', '100%');
    }
}

function focusSearchBox() {
    $(eleSearchId).focus();
}

// search process
const searchHandler = async (event) => {
    let keyword = event.target.value;
    search(keyword);
};

function search(keyword) {
    let activeTab = $("li.ui-tabs-header.search-bar[aria-selected='true']");
    if (activeTab) {
        let tabIndex = activeTab.attr('data-index');
        if (PROCESS_TAB_INDEX === tabIndex) {
            searchProcess(keyword);
        } else if (TASK_TAB_INDEX === tabIndex) {
            searchTask(keyword);
        } else if (CASE_TAB_INDEX === tabIndex) {
            searchCase(keyword);
        }
    }
}

document.getElementById(searchId).addEventListener("keyup", debounce(searchHandler, 200));

function handleError(err) {
    console.log(err);
}

function searchProcess(keyword) {
    fetch(baseURL + searchProcessesURL, {
        method: "POST",
        headers: {"X-Requested-By": "ivy", "Content-Type": "application/json", "Accept": "application/json"},
        body: JSON.stringify({"query": keyword.trim()})
    }).then((response) => {
        response.json().then(results => {
            $("#process-search-results").html("");
            if (results) {
                results.forEach((rs, index) => {
                    document.getElementById("process-search-results").innerHTML += processComponent(rs);
                });
            }
        }, err => handleError(err));
    }, err => handleError(err));

}

function searchTask(keyword) {
    fetch(baseURL + searchTasksURL, {
        method: "POST",
        headers: {"X-Requested-By": "ivy", "Content-Type": "application/json", "Accept": "application/json"},
        body: JSON.stringify({"query": keyword.trim()})
    }).then((response) => {
        response.json().then(results => {
            $("#task-search-results").html("");
            if (results) {
                results.forEach((rs, index) => {
                    document.getElementById("task-search-results").innerHTML += taskComponent(rs);
                });
            }
        }, err => handleError(err));
    }, err => handleError(err));
}

function searchCase(keyword) {
    fetch(baseURL + searchCasesURL, {
        method: "POST",
        headers: {"X-Requested-By": "ivy", "Content-Type": "application/json", "Accept": "application/json"},
        body: JSON.stringify({"query": keyword.trim()})
    }).then((response) => {
        response.json().then(results => {
            $("#case-search-results").html("");
            if (results) {
                results.forEach((rs, index) => {
                    document.getElementById("case-search-results").innerHTML += caseComponent(rs);
                });
            }
        }, err => handleError(err));
    }, err => handleError(err));
}

function debounce(func, delay) {
    let timeout;
    return function executedFunc(...args) {
        if (timeout) {
            clearTimeout(timeout);
        }

        timeout = setTimeout(() => {
            func(...args);
            timeout = null;
        }, delay);
    };
}

let processComponent = (process) => {
    return `<div class="task-even-row full-mode task-start-list-item js-task-start-list-item">
              <div class="task-start-link js-task-start-link">
                <div class="task-priority-item">
                  <span class="priority-cell search-item">
                    <span class="priority-icon">
                      <i class="${process.icon}"></i>
                    </span>
                  </span>
                </div>
                <div class="task-start-info">
                  <span class="name-cell">
                  ${process.name}
                  </span>
                  <span id="search-results-tabview:task-results:task-list-scroller:0:task-item:task-name-component:task-description"
                   class="description-cell ">${process.description}</span>
                </div>
              </div>
            </div>`;
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
    return `<div class="task-even-row full-mode task-start-list-item js-task-start-list-item">
                <div class="task-start-link js-task-start-link">
                  <div class="task-priority-item">
                    <span class="priority-cell search-item">
                      <span class="priority-icon">
                        <i class="${iconClass}"></i>
                      </span>
                    </span>
                  </div>
                  <div class="task-start-info">
                    <span class="name-cell">
                    ${task.name}
                    </span>
                  </div>
                </div>
              </div>`;
}

let caseComponent = (caze) => {
    return `<div class="task-even-row full-mode task-start-list-item js-task-start-list-item">
                <div class="task-start-link js-task-start-link">
                  <div class="task-start-info">
                    <span class="name-cell">
                    ${caze.name}
                    </span>
                    <span id="search-results-tabview:task-results:task-list-scroller:0:task-item:task-name-component:task-description"
                     class="description-cell ">${caze.description}</span>
                  </div>
                </div>
              </div>`;
}
