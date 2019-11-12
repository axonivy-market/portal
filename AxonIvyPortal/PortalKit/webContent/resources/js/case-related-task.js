var isShowScrollBar = false;
var maxItems = 3;

var taskHeight = 0;
var caseHeight = 0;
var taskScrollHeight = 0;
var caseScrollHeight = 0;

var relatedTaskSize = 0;
var technicalCaseSize = 0;
var spaceToExpandTasksList = 0;
var spaceToExpandCasesList = 0;

var CaseRelatedTask = {

  init : function(relatedTask, technicalCase) {
    relatedTaskSize = relatedTask || 0;
    technicalCaseSize = technicalCase || 0;
    isShowScrollBar = relatedTaskSize + technicalCaseSize > 6 ? true:false;

    taskHeight = this.getHeightOfRelatedTaskRow();
    caseHeight = this.getHeightOfTechnicalRow();
    
    taskScrollHeight = taskHeight*maxItems;
    caseScrollHeight = caseHeight*maxItems;

    if (technicalCaseSize < maxItems) {
      spaceToExpandTasksList = caseScrollHeight - (technicalCaseSize*caseHeight);
    }

    if (relatedTaskSize < maxItems) {
      spaceToExpandCasesList = taskScrollHeight - (relatedTaskSize*taskHeight);
    }
  },

  setUpScrollBar : function() {
    this.scrollBarForTasks();
    this.scrollBarForTechnicalCase();
  },

  scrollBarForTasks : function() {
    if (relatedTaskSize == 0) {
      return;
    }

    var taskList = $('[id$="case-item-details:related-tasks:tasks"]');
    var taskListBody = taskList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');
    var scrollHeightTask = 0;
    if (relatedTaskSize > maxItems) {
      scrollHeightTask = taskScrollHeight + spaceToExpandTasksList;
      if (isShowScrollBar) {
        taskListBody.css("border-right", ".1em solid");
      } else {
        scrollHeightCases += 8;
      }
    } else {
        // If data is equal maxItem, need to add padding of Table's DataScroller
        scrollHeightTask = relatedTaskSize * taskHeight + 8;
    }

    taskListBody.css("height", scrollHeightTask);
  },

  scrollBarForTechnicalCase : function() {
    if (this.technicalCaseSize == 0) {
      return;
    }
    var caseList = $('[id$="case-item-details:related-tasks:cases"]');
    var caseListBody = caseList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');
    var scrollHeightCases = 0;
    if (technicalCaseSize > maxItems) {
      scrollHeightCases = caseScrollHeight + spaceToExpandCasesList;
      if (isShowScrollBar) {
        caseListBody.css("border-right", ".1em solid");
      } else {
        scrollHeightCases += 8;
      }
    } else {
        // If data is equal maxItem, need to add padding of Table's DataScroller
        scrollHeightCases = technicalCaseSize * caseHeight + 8;
    }

    caseListBody.css("height", scrollHeightCases);
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
