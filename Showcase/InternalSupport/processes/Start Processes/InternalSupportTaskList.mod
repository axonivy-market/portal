[Ivy]
16381C31523F828C 3.23 #module
>Proto >Proto Collection #zClass
It0 InternalSupportTaskList Big #zClass
It0 B #cInfo
It0 #process
It0 @TextInP .resExport .resExport #zField
It0 @TextInP .type .type #zField
It0 @TextInP .processKind .processKind #zField
It0 @AnnotationInP-0n ai ai #zField
It0 @MessageFlowInP-0n messageIn messageIn #zField
It0 @MessageFlowOutP-0n messageOut messageOut #zField
It0 @TextInP .xml .xml #zField
It0 @TextInP .responsibility .responsibility #zField
It0 @StartRequest f0 '' #zField
It0 @EndTask f1 '' #zField
It0 @GridStep f69 '' #zField
It0 @CallSub f84 '' #zField
It0 @CallSub f73 '' #zField
It0 @PushWFArc f118 '' #zField
It0 @PushWFArc f17 '' #zField
It0 @PushWFArc f2 '' #zField
It0 @GridStep f4 '' #zField
It0 @PushWFArc f5 '' #zField
It0 @PushWFArc f3 '' #zField
>Proto It0 It0 InternalSupportTaskList #zField
It0 f0 outLink start.ivp #txt
It0 f0 type internaltest.InternalSupportTaskListData #txt
It0 f0 inParamDecl '<> param;' #txt
It0 f0 actionDecl 'internaltest.InternalSupportTaskListData out;
' #txt
It0 f0 guid 16381C31527A8019 #txt
It0 f0 requestEnabled true #txt
It0 f0 triggerEnabled false #txt
It0 f0 callSignature start() #txt
It0 f0 caseData businessCase.attach=true #txt
It0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
It0 f0 @C|.responsibility Everybody #txt
It0 f0 81 49 30 30 -21 17 #rect
It0 f0 @|StartRequestIcon #fIcon
It0 f1 type internaltest.InternalSupportTaskListData #txt
It0 f1 817 49 30 30 0 15 #rect
It0 f1 @|EndIcon #fIcon
It0 f69 actionDecl 'internaltest.InternalSupportTaskListData out;
' #txt
It0 f69 actionTable 'out=in;
' #txt
It0 f69 actionCode 'import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portal.generic.view.TaskView;

in.taskDataModel.setIgnoreInvolvedUser(in.hasReadAllTasksPermisson);
in.taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

in.taskView = TaskView.create().dataModel(in.taskDataModel).noTaskFoundMessage("").showHeaderToolbar(false).createNewTaskView();
' #txt
It0 f69 type internaltest.InternalSupportTaskListData #txt
It0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, task view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
It0 f69 518 52 36 24 -53 -48 #rect
It0 f69 @|StepIcon #fIcon
It0 f84 type internaltest.InternalSupportTaskListData #txt
It0 f84 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
It0 f84 doCall true #txt
It0 f84 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
It0 f84 requestMappingAction 'param.taskView=in.taskView;
' #txt
It0 f84 responseActionDecl 'internaltest.InternalSupportTaskListData out;
' #txt
It0 f84 responseMappingAction 'out=in;
' #txt
It0 f84 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
It0 f84 636 44 104 40 -48 -12 #rect
It0 f84 @|CallSubIcon #fIcon
It0 f73 type internaltest.InternalSupportTaskListData #txt
It0 f73 processCall 'Functional Processes/InitializeTaskDataModelOverride:call()' #txt
It0 f73 doCall true #txt
It0 f73 requestActionDecl '<> param;
' #txt
It0 f73 responseActionDecl 'internaltest.InternalSupportTaskListData out;
' #txt
It0 f73 responseMappingAction 'out=in;
out.taskDataModel=result.dataModel;
' #txt
It0 f73 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data model</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
It0 f73 328 42 112 44 -40 -8 #rect
It0 f73 @|CallSubIcon #fIcon
It0 f118 expr out #txt
It0 f118 440 64 518 64 #arcP
It0 f17 expr out #txt
It0 f17 554 64 636 64 #arcP
It0 f2 expr out #txt
It0 f2 740 64 817 64 #arcP
It0 f4 actionDecl 'internaltest.InternalSupportTaskListData out;
' #txt
It0 f4 actionTable 'out=in;
' #txt
It0 f4 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();' #txt
It0 f4 security system #txt
It0 f4 type internaltest.InternalSupportTaskListData #txt
It0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Permission Check</name>
    </language>
</elementInfo>
' #txt
It0 f4 168 42 112 44 -51 -8 #rect
It0 f4 @|StepIcon #fIcon
It0 f5 expr out #txt
It0 f5 111 64 168 64 #arcP
It0 f3 expr out #txt
It0 f3 280 64 328 64 #arcP
>Proto It0 .type internaltest.InternalSupportTaskListData #txt
>Proto It0 .processKind NORMAL #txt
>Proto It0 0 0 32 24 18 0 #rect
>Proto It0 @|BIcon #fIcon
It0 f73 mainOut f118 tail #connect
It0 f118 head f69 mainIn #connect
It0 f69 mainOut f17 tail #connect
It0 f17 head f84 mainIn #connect
It0 f84 mainOut f2 tail #connect
It0 f2 head f1 mainIn #connect
It0 f0 mainOut f5 tail #connect
It0 f5 head f4 mainIn #connect
It0 f4 mainOut f3 tail #connect
It0 f3 head f73 mainIn #connect
