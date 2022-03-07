[Ivy]
14FEEC13F8B8E7D2 9.4.0 #module
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
Ps0 @UdMethod f0 '' #zField
Ps0 @CallSub f6 '' #zField
Ps0 @UdProcessEnd f8 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @GridStep f4 '' #zField
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
Ps0 f3 403 83 26 26 0 12 #rect
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
Ps0 f6 processCall 'Ivy Data Processes/ProcessService:findProcesses()' #txt
Ps0 f6 requestActionDecl '<> param;' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
out.processes=result.processes;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessService</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 192 170 112 44 -43 -8 #rect
Ps0 f8 403 179 26 26 0 12 #rect
Ps0 f14 expr out #txt
Ps0 f14 304 192 403 192 #arcP
Ps0 f9 expr out #txt
Ps0 f9 109 192 192 192 #arcP
Ps0 f2 320 96 403 96 #arcP
Ps0 f5 109 96 192 96 #arcP
Ps0 f4 actionTable 'out=in;
' #txt
Ps0 f4 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

in.showInformationLink = GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_PROCESS_INFORMATION);' #txt
Ps0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide/show&#13;
Information link</name>
    </language>
</elementInfo>
' #txt
Ps0 f4 192 74 128 44 -41 -16 #rect
>Proto Ps0 .type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f6 mainOut f14 tail #connect
Ps0 f14 head f8 mainIn #connect
Ps0 f0 mainOut f9 tail #connect
Ps0 f9 head f6 mainIn #connect
Ps0 f1 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f4 mainOut f2 tail #connect
Ps0 f2 head f3 mainIn #connect
