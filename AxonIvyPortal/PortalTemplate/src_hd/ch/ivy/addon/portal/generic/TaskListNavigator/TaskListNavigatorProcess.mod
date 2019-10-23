[Ivy]
15F6ACE164737414 7.5.0 #module
>Proto >Proto Collection #zClass
Ss0 TaskListNavigatorProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @MessageFlowInP-0n messageIn messageIn #zField
Ss0 @MessageFlowOutP-0n messageOut messageOut #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @UdInit f0 '' #zField
Ss0 @UdProcessEnd f1 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @CallSub f40 '' #zField
Ss0 @UdEvent f8 '' #zField
Ss0 @CallSub f7 '' #zField
Ss0 @GridStep f10 '' #zField
Ss0 @PushWFArc f4 '' #zField
Ss0 @PushWFArc f5 '' #zField
Ss0 @PushWFArc f3 '' #zField
>Proto Ss0 Ss0 TaskListNavigatorProcess #zField
Ss0 f0 guid 15F6ACE17192667A #txt
Ss0 f0 method start() #txt
Ss0 f0 inParameterDecl '<> param;' #txt
Ss0 f0 outParameterDecl '<> result;' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 83 51 26 26 -16 15 #rect
Ss0 f0 @|UdInitIcon #fIcon
Ss0 f1 211 51 26 26 0 12 #rect
Ss0 f1 @|UdProcessEndIcon #fIcon
Ss0 f2 expr out #txt
Ss0 f2 109 64 211 64 #arcP
Ss0 f40 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Ss0 f40 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Ss0 f40 requestMappingAction 'param.taskView=in.taskView;
' #txt
Ss0 f40 responseActionDecl 'ch.ivy.addon.portal.generic.TaskListNavigator.TaskListNavigatorData out;
' #txt
Ss0 f40 responseMappingAction 'out=in;
' #txt
Ss0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f40 768 136 112 48 -48 -12 #rect
Ss0 f40 @|CallSubIcon #fIcon
Ss0 f8 guid 15F6AD8AACDD69BB #txt
Ss0 f8 actionTable 'out=in;
' #txt
Ss0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openTask</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f8 83 147 26 26 -27 15 #rect
Ss0 f8 @|UdEventIcon #fIcon
Ss0 f7 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Ss0 f7 requestActionDecl '<> param;' #txt
Ss0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.TaskListNavigator.TaskListNavigatorData out;
' #txt
Ss0 f7 responseMappingAction 'out=in;
out.taskDataModel=result.dataModel;
' #txt
Ss0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InitializeTaskDataModel</name>
    </language>
</elementInfo>
' #txt
Ss0 f7 224 138 144 44 -65 -8 #rect
Ss0 f7 @|CallSubIcon #fIcon
Ss0 f10 actionTable 'out=in;
' #txt
Ss0 f10 actionCode 'import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.util.PermissionUtils;

in.taskDataModel.setAdminQuery(PermissionUtils.checkReadAllTasksPermission());
in.taskDataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

in.taskView = TaskView.create().dataModel(in.taskDataModel).noTaskFoundMessage("").showHeaderToolbar(false).createNewTaskView();' #txt
Ss0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build data model, task view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f10 488 138 160 44 -74 -8 #rect
Ss0 f10 @|StepIcon #fIcon
Ss0 f4 648 160 768 160 #arcP
Ss0 f5 368 160 488 160 #arcP
Ss0 f3 109 160 224 160 #arcP
>Proto Ss0 .type ch.ivy.addon.portal.generic.TaskListNavigator.TaskListNavigatorData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f10 mainOut f4 tail #connect
Ss0 f4 head f40 mainIn #connect
Ss0 f7 mainOut f5 tail #connect
Ss0 f5 head f10 mainIn #connect
Ss0 f8 mainOut f3 tail #connect
Ss0 f3 head f7 mainIn #connect
