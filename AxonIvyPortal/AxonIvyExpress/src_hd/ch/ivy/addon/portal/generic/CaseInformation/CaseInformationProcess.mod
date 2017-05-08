[Ivy]
[>Created: Mon Nov 28 17:07:52 PST 2016]
158AD736DC876153 3.18 #module
>Proto >Proto Collection #zClass
Ts0 CaseInformationProcess Big #zClass
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
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 CaseInformationProcess #zField
Ts0 f0 guid 14EAACC2F57D5596 #txt
Ts0 f0 type ch.ivy.addon.portal.generic.CaseInformation.CaseInformationData #txt
Ts0 f0 method start(java.lang.Long) #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portal.generic.CaseInformation.CaseInformationData #txt
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f6 actionDecl 'ch.ivy.addon.portal.generic.CaseInformation.CaseInformationData out;
' #txt
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;


in.casex = CaseUtils.findcase(in.caseId);


' #txt
Ts0 f6 type ch.ivy.addon.portal.generic.CaseInformation.CaseInformationData #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find case</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 46 148 36 24 20 -2 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f7 expr out #txt
Ts0 f7 64 107 64 148 #arcP
Ts0 f2 expr out #txt
Ts0 f2 64 172 64 213 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.CaseInformation.CaseInformationData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start method</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>160</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f7 tail #connect
Ts0 f7 head f6 mainIn #connect
Ts0 f6 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
