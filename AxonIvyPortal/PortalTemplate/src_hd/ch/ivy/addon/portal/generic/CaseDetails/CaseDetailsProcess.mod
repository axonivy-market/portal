[Ivy]
1522FB42693C4374 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseDetailsProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 CaseDetailsProcess #zField
Cs0 f0 guid 1522FB426B9F2A54 #txt
Cs0 f0 method start(Number) #txt
Cs0 f0 inParameterDecl '<Number caseId> param;' #txt
Cs0 f0 inParameterMapAction 'out.currentCaseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Number)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 77 77 22 22 -38 13 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 325 77 22 22 14 0 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

in.currentCase = CaseUtils.findCase(in.currentCaseId);' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get case by id</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 160 64 128 48 -31 -8 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 99 88 160 88 #arcP
Cs0 f2 expr out #txt
Cs0 f2 288 88 325 88 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseDetails.CaseDetailsData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f3 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
