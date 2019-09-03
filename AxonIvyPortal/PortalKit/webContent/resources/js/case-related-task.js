function CaseRelatedTask(relatedTask, technicalCase) {
    var relatedTaskSize = relatedTask || 0;
    var technicalCaseSize = technicalCase || 0;
    
    var maxScrollHeight = 100;
    var maxSpaceToExpand = 90;
    var rowHeight = 32;
    var maxItems = 3;
    var paddingBottom = 12;

    var spaceToExpand = 0;
    if (relatedTaskSize == 0 || technicalCase == 0) {
      spaceToExpand = maxScrollHeight;
    }

    var spaceToExpandTasksList = 0;
    if (technicalCaseSize < maxItems) {
      spaceToExpandTasksList = maxSpaceToExpand - technicalCaseSize*rowHeight;
    }

    var spaceToExpandCasesList = 0;
    if (relatedTaskSize < maxItems) {
      spaceToExpandCasesList = maxSpaceToExpand - relatedTaskSize*rowHeight;
    }

    this.setUpScrollBarForRelatedTask = function() {
      var scrollHeightTask = relatedTaskSize*rowHeight + paddingBottom;
      if (relatedTaskSize > maxItems) {
        scrollHeightTask = maxScrollHeight + spaceToExpand + spaceToExpandTasksList;  
      }
      var taskList = $('[id$="case-item-details:related-tasks:tasks"]');
      var taskListBody = taskList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');

      taskListBody.css("height", scrollHeightTask);
    }

    this.setUpScrollBarForTechnicalCase = function() {
     var scrollHeightCases = technicalCaseSize*rowHeight + paddingBottom;
     if (technicalCaseSize > maxItems) {
       scrollHeightCases = maxScrollHeight + spaceToExpand + spaceToExpandCasesList;
     }
     var caseList = $('[id$="case-item-details:related-tasks:cases"]');
     var caseListBody = caseList.find('.ui-datascroller-content.ui-widget-content.ui-corner-all');

      caseListBody.css("height", scrollHeightCases);
    }

}
