// For calculating the height of Datascroller table in CaseItem details
var isShowScrollBar = false;
var maxItems = 3;

var taskItemHeight = 0;
var caseItemHeight = 0;
var tasksPanel = 0;
var casesPanel = 0;

var relatedTaskSize = 0;
var technicalCaseSize = 0;
var spaceToExpandTasksList = 0;
var spaceToExpandCasesList = 0;

var CaseRelatedTask = {

  init : function(relatedTask, technicalCase) {
    relatedTaskSize = relatedTask || 0;
    technicalCaseSize = technicalCase || 0;
    isShowScrollBar = relatedTaskSize + technicalCaseSize > 6 ? true:false;

    taskItemHeight = this.getHeightOfRelatedTaskRow();
    caseItemHeight = this.getHeightOfTechnicalRow();
    
    tasksPanel = taskItemHeight*maxItems;
    casesPanel = caseItemHeight*maxItems;

    if (technicalCaseSize < maxItems) {
      spaceToExpandTasksList = casesPanel - (technicalCaseSize*caseItemHeight);
    }

    if (relatedTaskSize < maxItems) {
      spaceToExpandCasesList = tasksPanel - (relatedTaskSize*taskItemHeight);
    }
  },

  setUpScrollBar : function() {
    this.scrollBarForTasks();
    this.scrollBarForTechnicalCase();
  },

  // For related-task table
  scrollBarForTasks : function() {
    if (relatedTaskSize == 0) {
      return;
    }

    var taskList = $('[id$="case-item-details:related-tasks:tasks"]');
    var taskListBody = taskList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');
    var scrollHeightForTasks = 0;
    if (relatedTaskSize > maxItems) {
      scrollHeightForTasks = tasksPanel + spaceToExpandTasksList;
      if (isShowScrollBar) {
        taskListBody.css("border-right", ".1em solid");
      } else {
        scrollHeightForTasks += 8;
      }
    } else {
        // If data is equal maxItem, need to add padding of Table's DataScroller
        scrollHeightForTasks = relatedTaskSize * taskItemHeight + 8;
    }

    taskListBody.css("height", scrollHeightForTasks);
  },

  // For TechnicalCases table
  scrollBarForTechnicalCase : function() {
    if (technicalCaseSize == 0) {
      return;
    }
    var caseList = $('[id$="case-item-details:related-tasks:cases"]');
    var caseListBody = caseList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');
    var scrollHeightForCases = 0;
    if (technicalCaseSize > maxItems) {
      scrollHeightForCases = casesPanel + spaceToExpandCasesList;
      if (isShowScrollBar) {
        caseListBody.css("border-right", ".1em solid");
      } else {
        scrollHeightForCases += 8;
      }
    } else {
        // If data is equal maxItem, need to add padding of Table's DataScroller
        scrollHeightForCases = technicalCaseSize * caseItemHeight + 8;
    }

    caseListBody.css("height", scrollHeightForCases);
  },

  getHeightOfRelatedTaskRow : function() {
    var taskRow = $('.grid-item-content-list-item.related-task-content.js-related-task');
    return taskRow.length > 3 ? taskRow[taskRow.length - 2].offsetHeight : 33;
  },

  getHeightOfTechnicalRow : function() {
    var caseRow = $('.grid-item-content-list-item.related-task-content.js-technical-case');
    return caseRow.length > 3 ? caseRow[caseRow.length - 2].offsetHeight : 33;
  }

}
