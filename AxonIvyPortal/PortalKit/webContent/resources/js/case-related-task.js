var CaseRelatedTask = {
  maxItems : 3,
  paddingBottom : 8,
  taskHeight : 0, caseHeight : 0,
  taskScrollHeight : 0, caseScrollHeight : 0,
  relatedTaskSize : 0, technicalCaseSize : 0,
  spaceToExpandTasksList : 0, spaceToExpandCasesList : 0,

  init : function(relatedTask, technicalCase) {
    this.relatedTaskSize = relatedTask || 0;
    this.technicalCaseSize = technicalCase || 0;

    this.taskHeight = this.getHeightOfRelatedTaskRow();
    this.caseHeight = this.getHeightOfTechnicalRow();
    
    this.taskScrollHeight = this.taskHeight*this.maxItems;
    this.caseScrollHeight = this.caseHeight*this.maxItems;

    if (this.technicalCaseSize < this.maxItems) {
      this.spaceToExpandTasksList = this.caseScrollHeight - (this.technicalCaseSize*this.caseHeight);
    }

    if (this.relatedTaskSize < this.maxItems) {
      this.spaceToExpandCasesList = this.taskScrollHeight - (this.relatedTaskSize*this.taskHeight);
    }
    
    if (this.technicalCaseSize == 0 || this.relatedTaskSize == 0) {
      this.paddingBottom = 10;
    }
  },

  setUpScrollBar : function() {
    this.scrollBarForTasks();
    this.scrollBarForTechnicalCase();
  },
  
  scrollBarForTasks : function() {
    if (this.relatedTaskSize == 0) {
      return;
    }
    var scrollHeightTask = 0;
    if (this.relatedTaskSize > this.maxItems) {
      scrollHeightTask = this.taskScrollHeight + this.spaceToExpandTasksList;
    } else {
        scrollHeightTask = this.relatedTaskSize * this.taskHeight;
    }
    scrollHeightTask = scrollHeightTask + this.paddingBottom;

    var taskList = $('[id$="case-item-details:related-tasks:tasks"]');
    var taskListBody = taskList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');

    taskListBody.css("height", scrollHeightTask);
  },

  scrollBarForTechnicalCase : function() {
    if (this.technicalCaseSize == 0) {
      return;
    }
    var scrollHeightCases = 0;
    if (this.technicalCaseSize > this.maxItems) {
      scrollHeightCases = this.caseScrollHeight + this.spaceToExpandCasesList;
    } else {
        scrollHeightCases = this.technicalCaseSize * this.caseHeight;
    }
    scrollHeightCases = scrollHeightCases + this.paddingBottom;

    var caseList = $('[id$="case-item-details:related-tasks:cases"]');
    var caseListBody = caseList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');

    caseListBody.css("height", scrollHeightCases);
  },

  getHeightOfRelatedTaskRow : function() {
    var taskRow = $('.grid-item-content-list-item.related-task-content.js-related-task');
    return taskRow.length > 0 ? taskRow[taskRow.length - 1].offsetHeight : 32;
  },

  getHeightOfTechnicalRow : function() {
    var caseRow = $('.grid-item-content-list-item.related-task-content.js-technical-case');
    return caseRow.length > 0 ? caseRow[caseRow.length - 1].offsetHeight : 32;
  }

}
