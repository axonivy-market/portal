[Ivy]
15306A682D565719 9.2.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemProcess Big #zClass
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
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdMethod f3 '' #zField
Cs0 @CallSub f5 '' #zField
Cs0 @Alternative f4 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @CallSub f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
>Proto Cs0 Cs0 CaseItemProcess #zField
Cs0 f0 guid 168031B841CA9289 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 243 83 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 109 96 243 96 #arcP
Cs0 f3 guid 16DCE2B01E7553A8 #txt
Cs0 f3 method openDetails(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Cs0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase,Boolean inFrame> param;' #txt
Cs0 f3 inParameterMapAction 'out.iCase=param.iCase;
out.inFrame=param.inFrame;
' #txt
Cs0 f3 outParameterDecl '<> result;' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDetails(iCase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 179 26 26 -25 15 #rect
Cs0 f3 @|UdMethodIcon #fIcon
Cs0 f5 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ch.ivyteam.ivy.workflow.ICase,Boolean)' #txt
Cs0 f5 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Cs0 f5 requestMappingAction 'param.caseData=in.iCase;
param.isShowBackButton=true;
' #txt
Cs0 f5 responseMappingAction 'out=in;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 288 170 144 44 -65 -8 #rect
Cs0 f5 @|CallSubIcon #fIcon
Cs0 f4 176 176 32 32 0 16 #rect
Cs0 f4 @|AlternativeIcon #fIcon
Cs0 f7 109 192 176 192 #arcP
Cs0 f6 expr in #txt
Cs0 f6 outCond !in.inFrame #txt
Cs0 f6 208 192 288 192 #arcP
Cs0 f8 processCall 'Functional Processes/OpenPortalCaseDetailsHook:callForIFrame(ch.ivyteam.ivy.workflow.ICase,Boolean)' #txt
Cs0 f8 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Cs0 f8 requestMappingAction 'param.caseData=in.iCase;
param.isShowBackButton=true;
' #txt
Cs0 f8 responseMappingAction 'out=in;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails in iframe</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 288 266 192 44 -91 -8 #rect
Cs0 f8 @|CallSubIcon #fIcon
Cs0 f9 expr in #txt
Cs0 f9 192 208 288 288 #arcP
Cs0 f9 1 192 288 #addKink
Cs0 f9 1 0.3028103786695175 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f7 tail #connect
Cs0 f7 head f4 in #connect
Cs0 f4 out f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f4 out f9 tail #connect
Cs0 f9 head f8 mainIn #connect
