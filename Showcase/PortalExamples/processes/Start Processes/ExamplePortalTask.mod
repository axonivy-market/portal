[Ivy]
1641C8FC2E4CE89E 3.23 #module
>Proto >Proto Collection #zClass
Pk0 ExamplePortalTask Big #zClass
Pk0 B #cInfo
Pk0 #process
Pk0 @TextInP .resExport .resExport #zField
Pk0 @TextInP .type .type #zField
Pk0 @TextInP .processKind .processKind #zField
Pk0 @AnnotationInP-0n ai ai #zField
Pk0 @MessageFlowInP-0n messageIn messageIn #zField
Pk0 @MessageFlowOutP-0n messageOut messageOut #zField
Pk0 @TextInP .xml .xml #zField
Pk0 @TextInP .responsibility .responsibility #zField
Pk0 @StartRequest f0 '' #zField
Pk0 @EndTask f1 '' #zField
Pk0 @GridStep f69 '' #zField
Pk0 @CallSub f73 '' #zField
Pk0 @CallSub f84 '' #zField
Pk0 @PushWFArc f17 '' #zField
Pk0 @PushWFArc f118 '' #zField
Pk0 @PushWFArc f2 '' #zField
Pk0 @PushWFArc f3 '' #zField
>Proto Pk0 Pk0 ExamplePortalTask #zField
Pk0 f0 outLink startPortalTask.ivp #txt
Pk0 f0 type ch.ivy.addon.portal.generic.PortalTaskData #txt
Pk0 f0 inParamDecl '<> param;' #txt
Pk0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskData out;
' #txt
Pk0 f0 guid 164121D08CC970F4 #txt
Pk0 f0 requestEnabled true #txt
Pk0 f0 triggerEnabled false #txt
Pk0 f0 callSignature startPortalTask() #txt
Pk0 f0 persist false #txt
Pk0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pk0 f0 caseData businessCase.attach=true #txt
Pk0 f0 showInStartList 0 #txt
Pk0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalTask.ivp</name>
    </language>
</elementInfo>
' #txt
Pk0 f0 @C|.responsibility Everybody #txt
Pk0 f0 81 49 30 30 -50 17 #rect
Pk0 f0 @|StartRequestIcon #fIcon
Pk0 f1 type ch.ivy.addon.portal.generic.PortalTaskData #txt
Pk0 f1 777 49 30 30 0 15 #rect
Pk0 f1 @|EndIcon #fIcon
Pk0 f69 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskData out;
' #txt
Pk0 f69 actionTable 'out=in;
' #txt
Pk0 f69 actionCode 'import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.util.PermissionUtils;

boolean hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();
in.taskDataModel.setIgnoreInvolvedUser(hasReadAllTasksPermisson);
in.taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

in.taskView = TaskView.create().dataModel(in.taskDataModel).noTaskFoundMessage("").showHeaderToolbar(false).createNewTaskView();
' #txt
Pk0 f69 type ch.ivy.addon.portal.generic.PortalTaskData #txt
Pk0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, task view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pk0 f69 390 52 36 24 20 -2 #rect
Pk0 f69 @|StepIcon #fIcon
Pk0 f73 type ch.ivy.addon.portal.generic.PortalTaskData #txt
Pk0 f73 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Pk0 f73 doCall true #txt
Pk0 f73 requestActionDecl '<> param;
' #txt
Pk0 f73 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskData out;
' #txt
Pk0 f73 responseMappingAction 'out=in;
out.taskDataModel=result.dataModel;
' #txt
Pk0 f73 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data model</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Pk0 f73 192 42 112 44 -40 -8 #rect
Pk0 f73 @|CallSubIcon #fIcon
Pk0 f84 type ch.ivy.addon.portal.generic.PortalTaskData #txt
Pk0 f84 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Pk0 f84 doCall true #txt
Pk0 f84 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Pk0 f84 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pk0 f84 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskData out;
' #txt
Pk0 f84 responseMappingAction 'out=in;
' #txt
Pk0 f84 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pk0 f84 604 44 104 40 -48 -12 #rect
Pk0 f84 @|CallSubIcon #fIcon
Pk0 f17 expr out #txt
Pk0 f17 426 64 604 64 #arcP
Pk0 f118 expr out #txt
Pk0 f118 304 64 390 64 #arcP
Pk0 f2 expr out #txt
Pk0 f2 708 64 777 64 #arcP
Pk0 f3 expr out #txt
Pk0 f3 111 64 192 64 #arcP
>Proto Pk0 .type ch.ivy.addon.portal.generic.PortalTaskData #txt
>Proto Pk0 .processKind NORMAL #txt
>Proto Pk0 0 0 32 24 18 0 #rect
>Proto Pk0 @|BIcon #fIcon
Pk0 f73 mainOut f118 tail #connect
Pk0 f118 head f69 mainIn #connect
Pk0 f69 mainOut f17 tail #connect
Pk0 f17 head f84 mainIn #connect
Pk0 f84 mainOut f2 tail #connect
Pk0 f2 head f1 mainIn #connect
Pk0 f0 mainOut f3 tail #connect
Pk0 f3 head f73 mainIn #connect
