[Ivy]
160FD493AA8D5416 7.5.0 #module
>Proto >Proto Collection #zClass
As0 AdditionalCaseDetailsProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @UdInit f0 '' #zField
As0 @UdProcessEnd f1 '' #zField
As0 @PushWFArc f2 '' #zField
>Proto As0 As0 AdditionalCaseDetailsProcess #zField
As0 f0 guid 160FD493ABB7A5B5 #txt
As0 f0 method start(ch.ivyteam.ivy.workflow.ICase) #txt
As0 f0 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
As0 f0 inParameterMapAction 'out.selectedCase=param.iCase;
' #txt
As0 f0 outParameterDecl '<> result;' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ICase)</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -53 15 #rect
As0 f0 @|UdInitIcon #fIcon
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|UdProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
>Proto As0 .type ch.ivy.addon.portal.generic.AdditionalCaseDetails.AdditionalCaseDetailsData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
