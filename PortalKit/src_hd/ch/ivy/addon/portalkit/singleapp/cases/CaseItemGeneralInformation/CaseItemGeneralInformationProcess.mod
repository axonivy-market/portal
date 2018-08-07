[Ivy]
[>Created: Fri Mar 25 14:02:34 ICT 2016]
153AC8F95BA416FF 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemGeneralInformationProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @RichDialogProcessStart f3 '' #zField
Cs0 @RichDialogEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 CaseItemGeneralInformationProcess #zField
Cs0 f0 guid 1533586B531F3838 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData #txt
Cs0 f0 method start(Long) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData #txt
Cs0 f1 53 213 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 guid 1533586B542C594C #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 149 85 22 22 14 0 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData #txt
Cs0 f4 guid 1533586B5439E123 #txt
Cs0 f4 149 213 22 22 14 0 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 160 107 160 213 #arcP
Cs0 f6 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData out;
' #txt
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'in.internalCase = ivy.wf.findCase(in.caseId);' #txt
Cs0 f6 security system #txt
Cs0 f6 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get case</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 46 148 36 24 20 -2 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 64 107 64 148 #arcP
Cs0 f2 expr out #txt
Cs0 f2 64 172 64 213 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseItemGeneralInformation.CaseItemGeneralInformationData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f0 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
