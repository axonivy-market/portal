[Ivy]
[>Created: Fri Jan 15 18:35:23 ICT 2016]
14EAACC2F37B2C71 3.18 #module
>Proto >Proto Collection #zClass
Ts0 TaskInformationProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogInitStart f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
>Proto Ts0 Ts0 TaskInformationProcess #zField
Ts0 f1 type ch.ivy.addon.portal.generic.TaskInformation.TaskInformationData #txt
Ts0 f1 53 277 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f6 actionDecl 'ch.ivy.addon.portal.generic.TaskInformation.TaskInformationData out;
' #txt
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

in.task = TaskUtils.findTaskUserHasPermissionToSee(in.taskId);
if(!in.#task is initialized) {
	in.taskNotFound = true;	
}
' #txt
Ts0 f6 type ch.ivy.addon.portal.generic.TaskInformation.TaskInformationData #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find task</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 46 156 36 24 20 -2 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 180 64 277 #arcP
Ts0 f7 guid 15093015B7F70D56 #txt
Ts0 f7 type ch.ivy.addon.portal.generic.TaskInformation.TaskInformationData #txt
Ts0 f7 method startFromSearch(java.lang.Long) #txt
Ts0 f7 disableUIEvents true #txt
Ts0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long taskId> param = methodEvent.getInputArguments();
' #txt
Ts0 f7 inParameterMapAction 'out.taskId=param.taskId;
' #txt
Ts0 f7 outParameterDecl '<> result;
' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startFromSearch(Long)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f7 53 85 22 22 -52 -32 #rect
Ts0 f7 @|RichDialogInitStartIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 64 107 64 156 #arcP
Ts0 f8 0 0.4899030339903018 0 0 #arcLabel
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskInformation.TaskInformationData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start method</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>400</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f6 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f7 mainOut f8 tail #connect
Ts0 f8 head f6 mainIn #connect
