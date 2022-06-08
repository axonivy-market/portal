[Ivy]
15306A682D565719 7.5.0 #module
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
Cs0 @CallSub f4 '' #zField
Cs0 @Alternative f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f6 '' #zField
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
Cs0 f1 307 83 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 109 96 307 96 #arcP
Cs0 f3 guid 16DCE2B01E7553A8 #txt
Cs0 f3 method openDetails(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Cs0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase,Boolean isCallFromProcessHistory> param;' #txt
Cs0 f3 inParameterMapAction 'out.iCase=param.iCase;
out.isCallFromProcessHistory=param.isCallFromProcessHistory;
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
Cs0 f5 296 170 144 44 -65 -8 #rect
Cs0 f5 @|CallSubIcon #fIcon
Cs0 f4 processCall 'Functional Processes/OpenProcessHistoryCaseDetailsHook:call(ch.ivyteam.ivy.workflow.ICase,Boolean)' #txt
Cs0 f4 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Cs0 f4 requestMappingAction 'param.caseData=in.iCase;
param.isShowBackButton=true;
' #txt
Cs0 f4 responseMappingAction 'out=in;
' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenProcessHistoryCaseDetails</name>
    </language>
</elementInfo>
' #txt
Cs0 f4 288 298 192 44 -91 -8 #rect
Cs0 f4 @|CallSubIcon #fIcon
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is open details from&#13;
process history?</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 192 176 32 32 -46 -57 #rect
Cs0 f7 @|AlternativeIcon #fIcon
Cs0 f8 109 192 192 192 #arcP
Cs0 f9 expr in #txt
Cs0 f9 outCond 'in.isCallFromProcessHistory == true' #txt
Cs0 f9 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 208 208 288 320 #arcP
Cs0 f9 1 208 320 #addKink
Cs0 f9 0 0.8571428571428571 15 0 #arcLabel
Cs0 f6 expr in #txt
Cs0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 224 192 296 192 #arcP
Cs0 f6 0 0.4583333333333333 0 8 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItem.CaseItemData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f8 tail #connect
Cs0 f8 head f7 in #connect
Cs0 f7 out f9 tail #connect
Cs0 f9 head f4 mainIn #connect
Cs0 f7 out f6 tail #connect
Cs0 f6 head f5 mainIn #connect
