[Ivy]
14FEEC13F8B8E7D2 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessWidgetProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f1 '' #zField
Ps0 @UdProcessEnd f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @UdMethod f0 '' #zField
Ps0 @CallSub f6 '' #zField
Ps0 @UdProcessEnd f8 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @PushWFArc f9 '' #zField
>Proto Ps0 Ps0 ProcessWidgetProcess #zField
Ps0 f1 guid 167CEF3C0BEA1F15 #txt
Ps0 f1 method start() #txt
Ps0 f1 inParameterDecl '<> param;' #txt
Ps0 f1 outParameterDecl '<> result;' #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f1 83 83 26 26 -12 16 #rect
Ps0 f1 @|UdInitIcon #fIcon
Ps0 f3 307 83 26 26 0 12 #rect
Ps0 f3 @|UdProcessEndIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 109 96 307 96 #arcP
Ps0 f0 guid 167CF9865BEC5C1B #txt
Ps0 f0 method collectProcesses() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.start.IWebStartable> processes> result;' #txt
Ps0 f0 outParameterMapAction 'result.processes=in.processes;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectProcesses()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 179 26 26 -42 19 #rect
Ps0 f0 @|UdMethodIcon #fIcon
Ps0 f6 processCall 'Ivy Data Processes/ProcessService:findProcesses()' #txt
Ps0 f6 requestActionDecl '<> param;' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
out.errors=result.errors;
out.processes=result.processes;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessService</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 168 170 112 44 -43 -8 #rect
Ps0 f6 @|CallSubIcon #fIcon
Ps0 f8 347 179 26 26 0 12 #rect
Ps0 f8 @|UdProcessEndIcon #fIcon
Ps0 f14 expr out #txt
Ps0 f14 280 192 347 192 #arcP
Ps0 f9 expr out #txt
Ps0 f9 109 192 168 192 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f1 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f6 mainOut f14 tail #connect
Ps0 f14 head f8 mainIn #connect
Ps0 f0 mainOut f9 tail #connect
Ps0 f9 head f6 mainIn #connect
