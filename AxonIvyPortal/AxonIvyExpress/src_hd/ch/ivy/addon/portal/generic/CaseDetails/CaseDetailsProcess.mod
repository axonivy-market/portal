[Ivy]
[>Created: Fri Mar 17 09:21:32 CET 2017]
159CC453BFB343AF 3.18 #module
>Proto >Proto Collection #zClass
cs0 CaseDetailsProcess Big #zClass
cs0 RD #cInfo
cs0 #process
cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
cs0 @TextInP .resExport .resExport #zField
cs0 @TextInP .type .type #zField
cs0 @TextInP .processKind .processKind #zField
cs0 @AnnotationInP-0n ai ai #zField
cs0 @MessageFlowInP-0n messageIn messageIn #zField
cs0 @MessageFlowOutP-0n messageOut messageOut #zField
cs0 @TextInP .xml .xml #zField
cs0 @TextInP .responsibility .responsibility #zField
cs0 @RichDialogInitStart f0 '' #zField
cs0 @RichDialogProcessEnd f1 '' #zField
cs0 @RichDialogProcessStart f3 '' #zField
cs0 @RichDialogEnd f4 '' #zField
cs0 @PushWFArc f5 '' #zField
cs0 @GridStep f6 '' #zField
cs0 @PushWFArc f7 '' #zField
cs0 @PushWFArc f2 '' #zField
>Proto cs0 cs0 CaseDetailsProcess #zField
cs0 f0 guid 158B29F8CA660AAA #txt
cs0 f0 type ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData #txt
cs0 f0 method start(ch.ivy.addon.portal.generic.CaseDetailsData) #txt
cs0 f0 disableUIEvents true #txt
cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portal.generic.CaseDetailsData caseDetailsData> param = methodEvent.getInputArguments();
' #txt
cs0 f0 inParameterMapAction 'out.caseDetailsData=param.caseDetailsData;
' #txt
cs0 f0 outParameterDecl '<ch.ivy.addon.portal.generic.CaseDetailsData caseDetailsData> result;
' #txt
cs0 f0 outParameterMapAction 'result.caseDetailsData=in.caseDetailsData;
' #txt
cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(CaseDetailsData)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f0 83 51 26 26 -63 15 #rect
cs0 f0 @|RichDialogInitStartIcon #fIcon
cs0 f1 type ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData #txt
cs0 f1 307 51 26 26 0 12 #rect
cs0 f1 @|RichDialogProcessEndIcon #fIcon
cs0 f3 guid 158B29F8CBA56B05 #txt
cs0 f3 type ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData #txt
cs0 f3 actionDecl 'ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData out;
' #txt
cs0 f3 actionTable 'out=in;
' #txt
cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
cs0 f3 83 147 26 26 -15 12 #rect
cs0 f3 @|RichDialogProcessStartIcon #fIcon
cs0 f4 type ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData #txt
cs0 f4 guid 158B29F8CBB4ED16 #txt
cs0 f4 347 147 26 26 0 12 #rect
cs0 f4 @|RichDialogEndIcon #fIcon
cs0 f5 expr out #txt
cs0 f5 109 160 347 160 #arcP
cs0 f6 actionDecl 'ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData out;
' #txt
cs0 f6 actionTable 'out=in;
' #txt
cs0 f6 actionCode 'in.thisCase = ivy.wf.findCase(in.caseDetailsData.caseId);' #txt
cs0 f6 type ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData #txt
cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get Case and Task</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f6 190 52 36 24 -18 16 #rect
cs0 f6 @|StepIcon #fIcon
cs0 f7 expr out #txt
cs0 f7 109 64 190 64 #arcP
cs0 f2 expr out #txt
cs0 f2 226 64 307 64 #arcP
>Proto cs0 .type ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData #txt
>Proto cs0 .processKind HTML_DIALOG #txt
>Proto cs0 -8 -8 16 16 16 26 #rect
>Proto cs0 '' #fIcon
cs0 f3 mainOut f5 tail #connect
cs0 f5 head f4 mainIn #connect
cs0 f0 mainOut f7 tail #connect
cs0 f7 head f6 mainIn #connect
cs0 f6 mainOut f2 tail #connect
cs0 f2 head f1 mainIn #connect
