[Ivy]
15F80B73A255C137 7.5.0 #module
>Proto >Proto Collection #zClass
Ws0 WarningBeforeLostSessionProcess Big #zClass
Ws0 RD #cInfo
Ws0 #process
Ws0 @TextInP .type .type #zField
Ws0 @TextInP .processKind .processKind #zField
Ws0 @AnnotationInP-0n ai ai #zField
Ws0 @MessageFlowInP-0n messageIn messageIn #zField
Ws0 @MessageFlowOutP-0n messageOut messageOut #zField
Ws0 @TextInP .xml .xml #zField
Ws0 @TextInP .responsibility .responsibility #zField
Ws0 @UdInit f0 '' #zField
Ws0 @UdProcessEnd f1 '' #zField
Ws0 @PushWFArc f2 '' #zField
Ws0 @UdEvent f3 '' #zField
Ws0 @UdExitEnd f4 '' #zField
Ws0 @PushWFArc f5 '' #zField
Ws0 @UdMethod f6 '' #zField
Ws0 @UdProcessEnd f7 '' #zField
Ws0 @CallSub f9 '' #zField
Ws0 @PushWFArc f8 '' #zField
Ws0 @UdMethod f11 '' #zField
Ws0 @UdProcessEnd f12 '' #zField
Ws0 @PushWFArc f13 '' #zField
Ws0 @PushWFArc f10 '' #zField
>Proto Ws0 Ws0 WarningBeforeLostSessionProcess #zField
Ws0 f0 guid 15F80B73AFE43AE4 #txt
Ws0 f0 method start() #txt
Ws0 f0 inParameterDecl '<> param;' #txt
Ws0 f0 outParameterDecl '<> result;' #txt
Ws0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ws0 f0 83 51 26 26 -16 15 #rect
Ws0 f0 @|UdInitIcon #fIcon
Ws0 f1 211 51 26 26 0 12 #rect
Ws0 f1 @|UdProcessEndIcon #fIcon
Ws0 f2 expr out #txt
Ws0 f2 109 64 211 64 #arcP
Ws0 f3 guid 15F80B73B31B73BD #txt
Ws0 f3 actionTable 'out=in;
' #txt
Ws0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ws0 f3 83 147 26 26 -15 12 #rect
Ws0 f3 @|UdEventIcon #fIcon
Ws0 f4 211 147 26 26 0 12 #rect
Ws0 f4 @|UdExitEndIcon #fIcon
Ws0 f5 expr out #txt
Ws0 f5 109 160 211 160 #arcP
Ws0 f6 guid 15F8F475530B4DA3 #txt
Ws0 f6 method logout(ITask) #txt
Ws0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ws0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ws0 f6 outParameterDecl '<> result;' #txt
Ws0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logout(ITask)</name>
    </language>
</elementInfo>
' #txt
Ws0 f6 83 243 26 26 -21 15 #rect
Ws0 f6 @|UdMethodIcon #fIcon
Ws0 f7 347 243 26 26 0 12 #rect
Ws0 f7 @|UdProcessEndIcon #fIcon
Ws0 f9 processCall 'Functional Processes/Logout:call(Boolean,ITask)' #txt
Ws0 f9 requestActionDecl '<Boolean isTaskReserve,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ws0 f9 requestMappingAction 'param.isTaskReserve=false;
param.task=in.task;
' #txt
Ws0 f9 responseActionDecl 'ch.ivy.addon.portalkit.component.WarningBeforeLostSession.WarningBeforeLostSessionData out;
' #txt
Ws0 f9 responseMappingAction 'out=in;
' #txt
Ws0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Logout</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f9 176 234 112 44 -19 -8 #rect
Ws0 f9 @|CallSubIcon #fIcon
Ws0 f8 expr out #txt
Ws0 f8 288 256 347 256 #arcP
Ws0 f11 guid 15F94574C89A91BA #txt
Ws0 f11 method extendSession() #txt
Ws0 f11 inParameterDecl '<> param;' #txt
Ws0 f11 outParameterDecl '<> result;' #txt
Ws0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>extendSession()</name>
    </language>
</elementInfo>
' #txt
Ws0 f11 83 371 26 26 -45 15 #rect
Ws0 f11 @|UdMethodIcon #fIcon
Ws0 f12 339 371 26 26 0 12 #rect
Ws0 f12 @|UdProcessEndIcon #fIcon
Ws0 f13 expr out #txt
Ws0 f13 109 384 339 384 #arcP
Ws0 f10 expr out #txt
Ws0 f10 109 256 176 256 #arcP
>Proto Ws0 .type ch.ivy.addon.portalkit.component.WarningBeforeLostSession.WarningBeforeLostSessionData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f3 mainOut f5 tail #connect
Ws0 f5 head f4 mainIn #connect
Ws0 f9 mainOut f8 tail #connect
Ws0 f8 head f7 mainIn #connect
Ws0 f11 mainOut f13 tail #connect
Ws0 f13 head f12 mainIn #connect
Ws0 f6 mainOut f10 tail #connect
Ws0 f10 head f9 mainIn #connect
