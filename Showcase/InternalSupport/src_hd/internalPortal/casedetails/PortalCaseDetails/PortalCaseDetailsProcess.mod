[Ivy]
[>Created: Thu Apr 16 11:17:05 ICT 2015]
14C9D5FEDF9A85A0 3.17 #module
>Proto >Proto Collection #zClass
Ps0 PortalCaseDetailsProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @GridStep f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 PortalCaseDetailsProcess #zField
Ps0 f0 guid 14BEF201D4239EF7 #txt
Ps0 f0 type internalPortal.casedetails.PortalCaseDetails.PortalCaseDetailsData #txt
Ps0 f0 method start(Number) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Number caseId> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.homeCaseId=param.caseId;
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type internalPortal.casedetails.PortalCaseDetails.PortalCaseDetailsData #txt
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f3 actionDecl 'internalPortal.casedetails.PortalCaseDetails.PortalCaseDetailsData out;
' #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
in.steps.add("Create Quotation");
in.steps.add("Approve by PM");
in.steps.add("Send Quotation");
in.steps.add("Create Order");
in.steps.add("WBS element BL");
in.steps.add("WBS element COL");
in.steps.add("Finished");
in.actualStepIndex = 2;
in.homeCase = CaseUtils.getCaseById(in.homeCaseId);
in.homeLink = ivy.html.startref("Start Processes/InternalSupportPortalHome/start.ivp");' #txt
Ps0 f3 type internalPortal.casedetails.PortalCaseDetails.PortalCaseDetailsData #txt
Ps0 f3 142 52 36 24 20 -2 #rect
Ps0 f3 @|StepIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 109 64 142 64 #arcP
Ps0 f2 expr out #txt
Ps0 f2 178 64 211 64 #arcP
>Proto Ps0 .type internalPortal.casedetails.PortalCaseDetails.PortalCaseDetailsData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f3 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
