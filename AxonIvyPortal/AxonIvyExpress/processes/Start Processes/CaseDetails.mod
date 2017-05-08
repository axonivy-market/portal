[Ivy]
[>Created: Fri Mar 17 13:19:14 CET 2017]
158B26636E6F7441 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseDetails Big #zClass
Cs0 B #cInfo
Cs0 #process
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @StartRequest f0 '' #zField
Cs0 @EndTask f1 '' #zField
Cs0 @RichDialog f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @GridStep f5 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @RichDialog f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @RichDialog f20 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f6 '' #zField
>Proto Cs0 Cs0 CaseDetails #zField
Cs0 f0 outLink startgawfsCaseDetails.ivp #txt
Cs0 f0 type gawfs.CaseDetailsData #txt
Cs0 f0 inParamDecl '<java.lang.Integer caseId> param;' #txt
Cs0 f0 inParamTable 'out.caseId=param.caseId;
' #txt
Cs0 f0 actionDecl 'gawfs.CaseDetailsData out;
' #txt
Cs0 f0 guid 158B266371BE0CB2 #txt
Cs0 f0 requestEnabled true #txt
Cs0 f0 triggerEnabled false #txt
Cs0 f0 callSignature startgawfsCaseDetails(Integer) #txt
Cs0 f0 persist false #txt
Cs0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Cs0 f0 showInStartList 1 #txt
Cs0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>gawfsCaseDetails.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 @C|.responsibility Everybody #txt
Cs0 f0 81 49 30 30 -60 17 #rect
Cs0 f0 @|StartRequestIcon #fIcon
Cs0 f1 type gawfs.CaseDetailsData #txt
Cs0 f1 641 49 30 30 0 15 #rect
Cs0 f1 @|EndIcon #fIcon
Cs0 f3 targetWindow NEW:card: #txt
Cs0 f3 targetDisplay TOP #txt
Cs0 f3 richDialogId de.eon.gawfs.portal.CaseDetails #txt
Cs0 f3 startMethod start(gawfs.ExecutePredefinedWorkflowData) #txt
Cs0 f3 type gawfs.CaseDetailsData #txt
Cs0 f3 requestActionDecl '<gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData> param;' #txt
Cs0 f3 responseActionDecl 'gawfs.CaseDetailsData out;
' #txt
Cs0 f3 responseMappingAction 'out=in;
' #txt
Cs0 f3 windowConfiguration '* ' #txt
Cs0 f3 isAsynch false #txt
Cs0 f3 isInnerRd false #txt
Cs0 f3 userContext '* ' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show Case</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 312 130 112 44 -32 -8 #rect
Cs0 f3 @|RichDialogIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 424 152 641 68 #arcP
Cs0 f5 actionDecl 'gawfs.CaseDetailsData out;
' #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'import gawfs.ExecutePredefinedWorkflowData;
// get Process Data of Last Task of Case

// in.caseId = 3;   for manual testing

import ch.ivyteam.ivy.workflow.PropertyOrder;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.persistence.OrderDirection;
ch.ivyteam.ivy.workflow.IPropertyFilter filter;

filter = ivy.wf.createTaskPropertyFilter(ch.ivyteam.ivy.workflow.TaskProperty.CASE_ID, 
                                         ch.ivyteam.logicalexpression.RelationalOperator.EQUAL, 
                                         in.caseId.longValue());

filter = filter.and(ivy.wf.createTaskPropertyFilter(ch.ivyteam.ivy.workflow.TaskProperty.STATE, 
                                            ch.ivyteam.logicalexpression.RelationalOperator.UNEQUAL, 
                                            ch.ivyteam.ivy.workflow.TaskState.DESTROYED.intValue()));

ivy.log.info("Show PDV for caseId:"+in.caseId);

List order = PropertyOrder.create(ch.ivyteam.ivy.workflow.TaskProperty.ID, OrderDirection.DESCENDING);

Number iStart = 0;
Number iCount = -1;
Boolean bAll = true;

ch.ivyteam.ivy.persistence.IQueryResult aQueryResult = ivy.wf.findTasks(filter, order, iStart, iCount, bAll);

List<ITask> lst = aQueryResult.getResultList();

ITask task;
ch.ivyteam.ivy.scripting.objects.CompositeObject processdata;

if (lst.size() != 0) {
                task = lst.get(0);

  if(task.getState().equals(ch.ivyteam.ivy.workflow.TaskState.DONE)) {
    processdata = task.getEndProcessData();
    //check if not initialized getStartProcessData()

  } 
  else {
    processdata = task.getStartProcessData();
    //check if not initialized then use getEndProcessData()
  }

  if (processdata!=null) {
    out.executePredefinedWorkflowData = processdata as ExecutePredefinedWorkflowData; //.get("executePredefinedWorkflowData")              
  }
                
}
' #txt
Cs0 f5 type gawfs.CaseDetailsData #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getCaseData</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 168 130 112 44 -36 -8 #rect
Cs0 f5 @|StepIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 280 152 312 152 #arcP
Cs0 f7 targetWindow NEW:card: #txt
Cs0 f7 targetDisplay TOP #txt
Cs0 f7 richDialogId ch.ivy.addon.portal.generic.CaseDetails #txt
Cs0 f7 startMethod start(ch.ivy.addon.portal.generic.CaseDetailsData) #txt
Cs0 f7 type gawfs.CaseDetailsData #txt
Cs0 f7 requestActionDecl '<ch.ivy.addon.portal.generic.CaseDetailsData caseDetailsData> param;' #txt
Cs0 f7 requestMappingAction 'param.caseDetailsData.caseId=in.caseId;
' #txt
Cs0 f7 responseActionDecl 'gawfs.CaseDetailsData out;
' #txt
Cs0 f7 windowConfiguration '* ' #txt
Cs0 f7 isAsynch false #txt
Cs0 f7 isInnerRd false #txt
Cs0 f7 userContext '* ' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseDetails</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 216 42 112 44 -34 -8 #rect
Cs0 f7 @|RichDialogIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 111 64 216 64 #arcP
Cs0 f20 targetWindow NEW:card: #txt
Cs0 f20 targetDisplay TOP #txt
Cs0 f20 richDialogId ch.ivy.addon.portal.generic.PortalCaseList #txt
Cs0 f20 startMethod start() #txt
Cs0 f20 type gawfs.CaseDetailsData #txt
Cs0 f20 requestActionDecl '<> param;' #txt
Cs0 f20 responseActionDecl 'gawfs.CaseDetailsData out;
' #txt
Cs0 f20 responseMappingAction 'out=in;
' #txt
Cs0 f20 windowConfiguration '* ' #txt
Cs0 f20 isAsynch false #txt
Cs0 f20 isInnerRd false #txt
Cs0 f20 userContext '* ' #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>my cases</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f20 411 42 107 45 -32 -9 #rect
Cs0 f20 @|RichDialogIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 328 64 410 63 #arcP
Cs0 f6 expr out #txt
Cs0 f6 517 63 641 63 #arcP
>Proto Cs0 .type gawfs.CaseDetailsData #txt
>Proto Cs0 .processKind NORMAL #txt
>Proto Cs0 0 0 32 24 18 0 #rect
>Proto Cs0 @|BIcon #fIcon
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f5 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f0 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f7 mainOut f9 tail #connect
Cs0 f9 head f20 mainIn #connect
Cs0 f20 mainOut f6 tail #connect
Cs0 f6 head f1 mainIn #connect
