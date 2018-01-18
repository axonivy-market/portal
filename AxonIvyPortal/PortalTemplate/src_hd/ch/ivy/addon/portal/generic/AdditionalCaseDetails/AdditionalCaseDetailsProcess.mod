[Ivy]
160FD493AA8D5416 3.20 #module
>Proto >Proto Collection #zClass
As0 AdditionalCaseDetailsProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
As0 @TextInP .rdData2UIAction .rdData2UIAction #zField
As0 @TextInP .resExport .resExport #zField
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @RichDialogInitStart f0 '' #zField
As0 @RichDialogProcessEnd f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @RichDialogProcessStart f3 '' #zField
As0 @RichDialogEnd f4 '' #zField
As0 @PushWFArc f5 '' #zField
>Proto As0 As0 AdditionalCaseDetailsProcess #zField
As0 f0 guid 160FD493ABB7A5B5 #txt
As0 f0 type ch.ivy.addon.portal.generic.AdditionalCaseDetails.AdditionalCaseDetailsData #txt
As0 f0 method start(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
As0 f0 disableUIEvents true #txt
As0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
As0 f0 inParameterMapAction 'out.remoteCase=param.remoteCase;
' #txt
As0 f0 outParameterDecl '<> result;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(RemoteCase)</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -53 15 #rect
As0 f0 @|RichDialogInitStartIcon #fIcon
As0 f1 type ch.ivy.addon.portal.generic.AdditionalCaseDetails.AdditionalCaseDetailsData #txt
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|RichDialogProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
As0 f3 guid 160FD493AD68506B #txt
As0 f3 type ch.ivy.addon.portal.generic.AdditionalCaseDetails.AdditionalCaseDetailsData #txt
As0 f3 actionDecl 'ch.ivy.addon.portal.generic.AdditionalCaseDetails.AdditionalCaseDetailsData out;
' #txt
As0 f3 actionTable 'out=in;
' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
As0 f3 83 147 26 26 -15 12 #rect
As0 f3 @|RichDialogProcessStartIcon #fIcon
As0 f4 type ch.ivy.addon.portal.generic.AdditionalCaseDetails.AdditionalCaseDetailsData #txt
As0 f4 guid 160FD493AD7145F1 #txt
As0 f4 211 147 26 26 0 12 #rect
As0 f4 @|RichDialogEndIcon #fIcon
As0 f5 expr out #txt
As0 f5 109 160 211 160 #arcP
>Proto As0 .type ch.ivy.addon.portal.generic.AdditionalCaseDetails.AdditionalCaseDetailsData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f3 mainOut f5 tail #connect
As0 f5 head f4 mainIn #connect
