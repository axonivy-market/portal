[Ivy]
15EF07D454EF13E4 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 LogoutSettingProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @CallSub f8 '' #zField
Ps0 @UdMethod f10 '' #zField
Ps0 @CallSub f11 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @UdProcessEnd f15 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @Alternative f7 '' #zField
Ps0 @PushWFArc f12 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @PushWFArc f16 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @GridStep f18 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdMethod f20 '' #zField
Ps0 @UdProcessEnd f21 '' #zField
Ps0 @CallSub f22 '' #zField
Ps0 @PushWFArc f23 '' #zField
Ps0 @PushWFArc f24 '' #zField
>Proto Ps0 Ps0 LogoutSettingProcess #zField
Ps0 f0 guid 15EF07DBEF876B54 #txt
Ps0 f0 method start(String) #txt
Ps0 f0 inParameterDecl '<String isWorkingOnATask> param;' #txt
Ps0 f0 inParameterMapAction 'out.isWorkingOnATask=param.isWorkingOnATask.toBoolean();
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 51 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 307 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 77 64 307 64 #arcP
Ps0 f3 guid 15EF07E1D7F2C49C #txt
Ps0 f3 method logout(ITask) #txt
Ps0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ps0 f3 inParameterMapAction 'out.task=param.task;
' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logout(ITask)</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 51 147 26 26 -21 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f4 563 147 26 26 0 12 #rect
Ps0 f4 @|UdProcessEndIcon #fIcon
Ps0 f8 processCall 'Functional Processes/Logout:call(Boolean,ITask)' #txt
Ps0 f8 requestActionDecl '<Boolean isTaskReserve,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ps0 f8 requestMappingAction 'param.isTaskReserve=false;
param.task=in.task;
' #txt
Ps0 f8 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData out;
' #txt
Ps0 f8 responseMappingAction 'out=in;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend logout</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 232 138 112 44 -37 -8 #rect
Ps0 f8 @|CallSubIcon #fIcon
Ps0 f10 guid 15EF0CF6DE58BE72 #txt
Ps0 f10 method reserveTask(ITask) #txt
Ps0 f10 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ps0 f10 inParameterMapAction 'out.task=param.task;
' #txt
Ps0 f10 outParameterDecl '<> result;' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserveTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 51 437 26 26 -38 15 #rect
Ps0 f10 @|UdMethodIcon #fIcon
Ps0 f11 processCall 'Functional Processes/Logout:call(Boolean,ITask)' #txt
Ps0 f11 requestActionDecl '<Boolean isTaskReserve,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ps0 f11 requestMappingAction 'param.isTaskReserve=true;
param.task=in.task;
' #txt
Ps0 f11 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData out;
' #txt
Ps0 f11 responseMappingAction 'out=in;
' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend logout</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f11 137 427 112 44 -37 -8 #rect
Ps0 f11 @|CallSubIcon #fIcon
Ps0 f14 expr out #txt
Ps0 f14 76 449 137 449 #arcP
Ps0 f15 307 437 26 26 0 12 #rect
Ps0 f15 @|UdProcessEndIcon #fIcon
Ps0 f6 expr out #txt
Ps0 f6 249 449 307 449 #arcP
Ps0 f7 144 144 32 32 0 16 #rect
Ps0 f7 @|AlternativeIcon #fIcon
Ps0 f12 expr out #txt
Ps0 f12 77 160 144 160 #arcP
Ps0 f9 expr in #txt
Ps0 f9 outCond '!(in.isWorkingOnATask && in.task.getState() != ch.ivyteam.ivy.workflow.TaskState.DONE)' #txt
Ps0 f9 176 160 232 160 #arcP
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import org.primefaces.PrimeFaces;
PrimeFaces.current().executeScript("PF(''logoutConfirmation'').show()");' #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display warning dialog</name>
    </language>
</elementInfo>
' #txt
Ps0 f13 224 234 128 44 -58 -8 #rect
Ps0 f13 @|StepIcon #fIcon
Ps0 f16 expr in #txt
Ps0 f16 160 176 224 256 #arcP
Ps0 f16 1 160 256 #addKink
Ps0 f16 1 0.1556203489120305 0 0 #arcLabel
Ps0 f17 352 256 576 173 #arcP
Ps0 f17 1 576 256 #addKink
Ps0 f17 0 0.8452393120637643 0 0 #arcLabel
Ps0 f18 actionTable 'out=in;
' #txt
Ps0 f18 actionCode 'import org.primefaces.PrimeFaces;
PrimeFaces.current().executeScript("returnHomePage()");' #txt
Ps0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Return to homepage</name>
    </language>
</elementInfo>
' #txt
Ps0 f18 392 138 112 44 -53 -8 #rect
Ps0 f18 @|StepIcon #fIcon
Ps0 f19 expr out #txt
Ps0 f19 344 160 392 160 #arcP
Ps0 f5 504 160 563 160 #arcP
Ps0 f20 guid 16EC9E47E7987902 #txt
Ps0 f20 method logoutInDialog(ch.ivyteam.ivy.workflow.ITask) #txt
Ps0 f20 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ps0 f20 inParameterMapAction 'out.task=param.task;
' #txt
Ps0 f20 outParameterDecl '<> result;' #txt
Ps0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logoutInDialog(ITask)</name>
    </language>
</elementInfo>
' #txt
Ps0 f20 51 339 26 26 -23 15 #rect
Ps0 f20 @|UdMethodIcon #fIcon
Ps0 f21 307 339 26 26 0 12 #rect
Ps0 f21 @|UdProcessEndIcon #fIcon
Ps0 f22 processCall 'Functional Processes/Logout:call(Boolean,ITask)' #txt
Ps0 f22 requestActionDecl '<Boolean isTaskReserve,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ps0 f22 requestMappingAction 'param.isTaskReserve=false;
param.task=in.task;
' #txt
Ps0 f22 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData out;
' #txt
Ps0 f22 responseMappingAction 'out=in;
' #txt
Ps0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend logout</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f22 137 330 112 44 -37 -8 #rect
Ps0 f22 @|CallSubIcon #fIcon
Ps0 f23 expr out #txt
Ps0 f23 249 352 307 352 #arcP
Ps0 f24 77 352 137 352 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f10 mainOut f14 tail #connect
Ps0 f14 head f11 mainIn #connect
Ps0 f11 mainOut f6 tail #connect
Ps0 f6 head f15 mainIn #connect
Ps0 f3 mainOut f12 tail #connect
Ps0 f12 head f7 in #connect
Ps0 f7 out f9 tail #connect
Ps0 f9 head f8 mainIn #connect
Ps0 f7 out f16 tail #connect
Ps0 f16 head f13 mainIn #connect
Ps0 f13 mainOut f17 tail #connect
Ps0 f17 head f4 mainIn #connect
Ps0 f8 mainOut f19 tail #connect
Ps0 f19 head f18 mainIn #connect
Ps0 f18 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f22 mainOut f23 tail #connect
Ps0 f23 head f21 mainIn #connect
Ps0 f20 mainOut f24 tail #connect
Ps0 f24 head f22 mainIn #connect
