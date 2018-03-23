[Ivy]
1624D1BB2301B683 3.20 #module
>Proto >Proto Collection #zClass
Is0 InvestmentRequestCaseDetailPageProcess Big #zClass
Is0 RD #cInfo
Is0 #process
Is0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Is0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Is0 @TextInP .resExport .resExport #zField
Is0 @TextInP .type .type #zField
Is0 @TextInP .processKind .processKind #zField
Is0 @AnnotationInP-0n ai ai #zField
Is0 @MessageFlowInP-0n messageIn messageIn #zField
Is0 @MessageFlowOutP-0n messageOut messageOut #zField
Is0 @TextInP .xml .xml #zField
Is0 @TextInP .responsibility .responsibility #zField
Is0 @RichDialogInitStart f0 '' #zField
Is0 @RichDialogProcessEnd f1 '' #zField
Is0 @PushWFArc f2 '' #zField
Is0 @RichDialogProcessStart f3 '' #zField
Is0 @RichDialogEnd f4 '' #zField
Is0 @PushWFArc f5 '' #zField
>Proto Is0 Is0 InvestmentRequestCaseDetailPageProcess #zField
Is0 f0 guid 1624D1BB2437AE35 #txt
Is0 f0 type ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailPage.InvestmentRequestCaseDetailPageData #txt
Is0 f0 method start(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Is0 f0 disableUIEvents true #txt
Is0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Is0 f0 inParameterMapAction 'out.remoteCase=param.remoteCase;
' #txt
Is0 f0 outParameterDecl '<> result;
' #txt
Is0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(RemoteCase)</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f0 83 51 26 26 -53 15 #rect
Is0 f0 @|RichDialogInitStartIcon #fIcon
Is0 f1 type ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailPage.InvestmentRequestCaseDetailPageData #txt
Is0 f1 211 51 26 26 0 12 #rect
Is0 f1 @|RichDialogProcessEndIcon #fIcon
Is0 f2 expr out #txt
Is0 f2 109 64 211 64 #arcP
Is0 f3 guid 1624D1BB25CB4E0F #txt
Is0 f3 type ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailPage.InvestmentRequestCaseDetailPageData #txt
Is0 f3 actionDecl 'ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailPage.InvestmentRequestCaseDetailPageData out;
' #txt
Is0 f3 actionTable 'out=in;
' #txt
Is0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Is0 f3 83 147 26 26 -15 12 #rect
Is0 f3 @|RichDialogProcessStartIcon #fIcon
Is0 f4 type ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailPage.InvestmentRequestCaseDetailPageData #txt
Is0 f4 guid 1624D1BB25D58E50 #txt
Is0 f4 211 147 26 26 0 12 #rect
Is0 f4 @|RichDialogEndIcon #fIcon
Is0 f5 expr out #txt
Is0 f5 109 160 211 160 #arcP
>Proto Is0 .type ch.ivyteam.ivy.project.portal.examples.customization.InvestmentRequestCaseDetailPage.InvestmentRequestCaseDetailPageData #txt
>Proto Is0 .processKind HTML_DIALOG #txt
>Proto Is0 -8 -8 16 16 16 26 #rect
>Proto Is0 '' #fIcon
Is0 f0 mainOut f2 tail #connect
Is0 f2 head f1 mainIn #connect
Is0 f3 mainOut f5 tail #connect
Is0 f5 head f4 mainIn #connect
