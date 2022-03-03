[Ivy]
15493AEB89F5A807 9.4.0 #module
>Proto >Proto Collection #zClass
Os0 OpenPortalTasks Big #zClass
Os0 B #cInfo
Os0 #process
Os0 @TextInP .type .type #zField
Os0 @TextInP .processKind .processKind #zField
Os0 @AnnotationInP-0n ai ai #zField
Os0 @MessageFlowInP-0n messageIn messageIn #zField
Os0 @MessageFlowOutP-0n messageOut messageOut #zField
Os0 @TextInP .xml .xml #zField
Os0 @TextInP .responsibility .responsibility #zField
Os0 @StartSub f0 '' #zField
Os0 @EndSub f1 '' #zField
Os0 @UserDialog f3 '' #zField
Os0 @PushWFArc f2 '' #zField
Os0 @PushWFArc f7 '' #zField
Os0 @StartSub f4 '' #zField
Os0 @EndSub f5 '' #zField
Os0 @UserDialog f6 '' #zField
Os0 @PushWFArc f8 '' #zField
Os0 @PushWFArc f9 '' #zField
Os0 @InfoButton f10 '' #zField
Os0 @AnnotationArc f11 '' #zField
Os0 @InfoButton f12 '' #zField
Os0 @AnnotationArc f13 '' #zField
>Proto Os0 Os0 OpenPortalTasks #zField
Os0 f0 inParamDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f0 inParamTable 'out.taskView=param.taskView;
' #txt
Os0 f0 outParamDecl '<> result;' #txt
Os0 f0 callSignature useView(ch.ivy.addon.portal.generic.view.TaskView) #txt
Os0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(TaskView)</name>
    </language>
</elementInfo>
' #txt
Os0 f0 83 99 26 26 14 -22 #rect
Os0 f1 627 99 26 26 14 0 #rect
Os0 f3 dialogId ch.ivy.addon.portal.generic.PortalTasks #txt
Os0 f3 startMethod useTaskView(TaskView) #txt
Os0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f3 requestMappingAction 'param.taskView=in.taskView;
' #txt
Os0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalTasksData out;
' #txt
Os0 f3 responseMappingAction 'out=in;
' #txt
Os0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalTasks</name>
    </language>
</elementInfo>
' #txt
Os0 f3 312 90 112 44 -33 -8 #rect
Os0 f2 expr out #txt
Os0 f2 424 112 627 112 #arcP
Os0 f2 0 0.49728593711698305 0 0 #arcLabel
Os0 f7 expr out #txt
Os0 f7 109 112 312 112 #arcP
Os0 f4 inParamDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f4 inParamTable 'out.taskView=param.taskView;
' #txt
Os0 f4 outParamDecl '<> result;' #txt
Os0 f4 callSignature useViewInFrame(ch.ivy.addon.portal.generic.view.TaskView) #txt
Os0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useViewInFrame(TaskView)</name>
    </language>
</elementInfo>
' #txt
Os0 f4 83 371 26 26 9 -30 #rect
Os0 f5 627 371 26 26 14 0 #rect
Os0 f6 dialogId ch.ivy.addon.portal.component.iframe.PortalTaskListInFrame #txt
Os0 f6 startMethod start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Os0 f6 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f6 requestMappingAction 'param.taskView=in.taskView;
' #txt
Os0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalTasksData out;
' #txt
Os0 f6 responseMappingAction 'out=in;
' #txt
Os0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalTaskListInFrame</name>
    </language>
</elementInfo>
' #txt
Os0 f6 304 362 144 44 -62 -8 #rect
Os0 f8 expr out #txt
Os0 f8 109 384 304 384 #arcP
Os0 f8 0 0.5000000000000001 0 0 #arcLabel
Os0 f9 expr out #txt
Os0 f9 448 384 627 384 #arcP
Os0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated Note**&#13;
&#13;
**This callable will be removed from Portal 11.&#13;
**To override Portal task widget''s UI: you should use Axon Ivy HTMLOverride Dialog to override the PortalTaskListInFrame Html dialog.</name>
    </language>
</elementInfo>
' #txt
Os0 f10 144 458 736 76 -358 -34 #rect
Os0 f11 144 496 101 395 #arcP
Os0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated Note**&#13;
&#13;
**This callable will be removed from Portal 11.&#13;
**To override Portal task widget''s UI: you should use Axon Ivy HTMLOverride Dialog to override the PortalTasks Html dialog.</name>
    </language>
</elementInfo>
' #txt
Os0 f12 152 170 672 76 -329 -34 #rect
Os0 f13 152 208 102 123 #arcP
>Proto Os0 .type ch.ivy.addon.portal.generic.OpenPortalTasksData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f3 mainOut f2 tail #connect
Os0 f2 head f1 mainIn #connect
Os0 f0 mainOut f7 tail #connect
Os0 f7 head f3 mainIn #connect
Os0 f6 mainOut f9 tail #connect
Os0 f9 head f5 mainIn #connect
Os0 f4 mainOut f8 tail #connect
Os0 f8 head f6 mainIn #connect
Os0 f10 ao f11 tail #connect
Os0 f11 head f4 @CG|ai #connect
Os0 f12 ao f13 tail #connect
Os0 f13 head f0 @CG|ai #connect
