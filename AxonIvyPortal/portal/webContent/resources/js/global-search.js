console.log(document.getElementById("quick-global-search-component:global-search-data"))
const pathName = window.location.pathname;
const baseURL = pathName.substring(0, pathName.indexOf('/faces/'));
const searchProcessesURL = "/api/global-search/processes";
const searchTasksURL = "/api/global-search/tasks";
const searchCasesURL = "/api/global-search/cases";
const searchId = "quick-global-search-component:global-search-data";
const eleSearchId = "[id='quick-global-search-component:global-search-data']";
const processTabIndex = 0;
const taskTabIndex = 1;
const caseTabIndex = 2;
const startsWithTask = 'task: ';
const startsWithProcess = 'process: ';
const startsWithCase = 'case: ';
const requestInit = body => {
    return {
              method: "POST",
              headers: { "X-Requested-By": "ivy", "Content-Type": "application/json", "Accept": "application/json" },
              body: body
            }
    }


$(document).ready(function () {
    console.log(window.location.pathname)
	document.getElementById(searchId).addEventListener("keyup", event => {
	    if (event.key === 'Enter') {
	        portalGlobalSearch();
	        return false;
	    } else {
	        debounce(searchHandler(event), 500);
	    }
	});
    $(eleSearchId).val('');
    $(".search-bar.process-tab-title").click(() => {
        focusSearchBox();
        let keyword = $(eleSearchId).val().toLowerCase();
        if (keyword.startsWith(startsWithProcess)) {
            keyword = keyword.slice(keyword.indexOf(':') + 1);
        }
        searchProcess(keyword);
    });
    $(".search-bar.task-tab-title").click(() => {
        focusSearchBox();
        let keyword = $(eleSearchId).val().toLowerCase();
        if (keyword.startsWith(startsWithTask)) {
            keyword = keyword.slice(keyword.indexOf(':') + 1);
        }
        searchTask(keyword);
    });
    $(".search-bar.case-tab-title").click(() => {
        focusSearchBox();
        let keyword = $(eleSearchId).val().toLowerCase();
        if (keyword.startsWith(startsWithCase)) {
            keyword = keyword.slice(keyword.indexOf(':') + 1);
        }
        searchCase(keyword);
    });
});

$(eleSearchId).click((e) => {
    if (!window.matchMedia("(max-width: 767px)").matches) {
        $(eleSearchId).addClass('global-large-search-bar').removeClass('global-small-search-bar');
    } else {
        $(eleSearchId).removeClass('global-large-search-bar').addClass('global-small-search-bar');
    }

    search(e.target.value, processTabIndex);
});

function resetSearchBar() {
    if (!window.matchMedia("(max-width: 767px)").matches) {
        $(eleSearchId).removeClass('global-large-search-bar').addClass('global-small-search-bar');
    } else {
        $(eleSearchId).addClass('global-default-search-bar').removeClass('global-large-search-bar').removeClass('global-small-search-bar');
    }
}

function viewAllResults(tabIndex) {
    handlePromise(tabIndex).then(() => {
        portalGlobalSearch();
        return false;
    })

}

function handlePromise(tabIndex) {
    return new Promise((resolve, reject) => {
        let searchInput = $('#quick-global-search-component\\:global-search-data');
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

function analyzeInputSearch(input) {
    let activeTab = $("li.ui-tabs-header.search-bar[aria-selected='true']");
    let tabIndex = activeTab.attr('data-index');
    let keyword = input;
    let explicit = false;
    if (input.startsWith(startsWithProcess)) {
        keyword = input.slice(input.indexOf(':')+1);
        tabIndex = processTabIndex;
        explicit = true;
    }
    if (input.startsWith(startsWithTask)) {
        keyword = input.slice(input.indexOf(':')+1);
        tabIndex = taskTabIndex;
        explicit = true;
    }
    if (input.startsWith(startsWithCase)) {
        keyword = input.slice(input.indexOf(':')+1);
        tabIndex = caseTabIndex;
        explicit = true;
    }

    return { keyword , tabIndex, explicit }
}

function searchHandler(event) {
    let inputSearch = event.target.value.toLowerCase();
    let analyzedInput = analyzeInputSearch(inputSearch);
    let keyword = analyzedInput.keyword;
    let tabIndex = analyzedInput.tabIndex;
    if (analyzedInput.explicit) {
        let activeTab = $("li.ui-tabs-header.search-bar[data-index='"+tabIndex+"']");
        activeTab.click();
    } else {
        search(keyword.trim(), tabIndex);
    }
}

function search(keyword, tabIndex) {
    if (processTabIndex === tabIndex) {
        searchProcess(keyword);
    } else if (taskTabIndex === tabIndex) {
        searchTask(keyword);
    } else if (caseTabIndex === tabIndex) {
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
        $(viewAllLink).removeClass('u-hidden');
        $(viewAllLink).find('a').html(data.viewAllText + ' (' + data.totalResult + ')')
        $(noResults).addClass('u-hidden');
    } else {
        $(viewAllLink).addClass('u-hidden');
        $(noResults).removeClass('u-hidden');
    }
}

function searchProcess(keyword) {
    let body = JSON.stringify({ "query": keyword })
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
    let body = JSON.stringify({ "query": keyword })
    fetch(baseURL + searchTasksURL, requestInit(body))
        .then((response) => {
            response.json().then(data => {
                let $resultsContainer = $("#task-search-results");
                $resultsContainer.html("");
                if (data) {
                    data.results.forEach((rs, index) => {
                        document.getElementById("task-search-results").innerHTML += taskComponent(rs);
                    });
                    handleUISearchResults($resultsContainer, data);
                }
            }, err => handleError(err));
        }, err => handleError(err));
}

function searchCase(keyword) {
    let body = JSON.stringify({ "query": keyword })
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
    return '<a href= ' + process.link + ' class="task-even-row full-mode task-start-list-item js-task-start-list-item">'+
           '   <div class="task-start-link js-task-start-link">'+
           '     <div class="task-priority-item">'+
           '       <span class="priority-cell search-item">'+
           '         <span class="priority-icon">'+
           '           <i class="' + process.icon + '"></i>'+
           '         </span>'+
           '       </span>'+
           '     </div>'+
           '     <div class="task-start-info">'+
           '       <span class="name-cell">' + process.name + '</span>'+
           '     </div>'+
           '    </div>'+
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
    return  '<a href=' + task.link +' class="task-even-row full-mode task-start-list-item js-task-start-list-item">' +
            '  <div class="task-start-link js-task-start-link">' +
            '     <div class="task-priority-item">' +
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
    return '<a href=' + caze.link + ' target="_blank"  class="task-even-row full-mode task-start-list-item js-task-start-list-item">'+
           '     <div class="task-start-link js-task-start-link">'+
           '       <div class="task-priority-item">'+
           '         <span class="priority-cell search-item">'+
           '           <span class="priority-icon">'+
           '             <i class="si si-layout-bullets"></i>'+
           '           </span>'+
           '         </span>'+
           '       </div>'+
           '       <div class="task-start-info">'+
           '         <span class="name-cell">'+ caze.name +
           '         </span>'+
           '       </div>'+
           '     </div>'+
           '</a>';
}
