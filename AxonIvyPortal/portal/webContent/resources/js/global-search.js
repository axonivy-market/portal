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
    $(".search-bar.process-tab-title").click(() => {
        focusSearchBox();
        searchProcess($(eleSearchId).val());
    });
    $(".search-bar.task-tab-title").click(() => {
        focusSearchBox();
        searchTask($(eleSearchId).val());
    });
    $(".search-bar.case-tab-title").click(() => {
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

function handleUISearchResults($parent, results) {
    let viewAllLink = $parent.siblings('.global-search-all-results')[0];
    let noResults = $parent.siblings('.global-search-no-results')[0];
    if (results && results.length > 0) {
        $(viewAllLink).removeClass('u-hidden');
        $(noResults).addClass('u-hidden');
    } else {
        $(viewAllLink).addClass('u-hidden');
        $(noResults).removeClass('u-hidden');
    }
}

function searchProcess(keyword) {
    fetch(baseURL + searchProcessesURL, {
        method: "POST",
        headers: {"X-Requested-By": "ivy", "Content-Type": "application/json", "Accept": "application/json"},
        body: JSON.stringify({"query": keyword.trim()})
    }).then((response) => {
        response.json().then(results => {
            let $resultsContainer = $("#process-search-results");
            $resultsContainer.html("");
            if (results) {
                results.forEach((rs, index) => {
                    document.getElementById("process-search-results").innerHTML += processComponent(rs);
                });
            }
            handleUISearchResults($resultsContainer, results);
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
            let $resultsContainer = $("#task-search-results");
            $resultsContainer.html("");
            if (results) {
                results.forEach((rs, index) => {
                    document.getElementById("task-search-results").innerHTML += taskComponent(rs);
                });
            }
            handleUISearchResults($resultsContainer, results);
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
            let $resultsContainer = $("#case-search-results");
            $resultsContainer.html("");
            if (results) {
                results.forEach((rs, index) => {
                    document.getElementById("case-search-results").innerHTML += caseComponent(rs);
                });
            }
            handleUISearchResults($resultsContainer, results);
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
    return `<a href="${process.link}" target="_blank" class="task-even-row full-mode task-start-list-item js-task-start-list-item">
              <div class="task-start-link js-task-start-link">
                <div class="task-priority-item">
                  <span class="priority-cell search-item">
                    <span class="priority-icon">
                      <i class="${process.icon}"></i>
                    </span>
                  </span>
                </div>
                <div class="task-start-info">
                  <span class="name-cell"> ${process.name}</span>
                </div>
               </div>
            </a>`;
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
    return `<a href="${task.link}" target="_blank" class="task-even-row full-mode task-start-list-item js-task-start-list-item">
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
              </a>`;
}

let caseComponent = (caze) => {
    return `<a href="${caze.link}" target="_blank"  class="task-even-row full-mode task-start-list-item js-task-start-list-item">
                <div class="task-start-link js-task-start-link">
                  <div class="task-priority-item">
                    <span class="priority-cell search-item">
                      <span class="priority-icon">
                        <i class="si si-layout-bullets"></i>
                      </span>
                    </span>
                  </div>
                  <div class="task-start-info">
                    <span class="name-cell">
                    ${caze.name}
                    </span>
                  </div>
                </div>
              </a>`;
}
