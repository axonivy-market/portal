[Ivy]
180F979F5AF44684 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessHistoryProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @CallSub f5 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @PushWFArc f6 '' #zField
>Proto Ps0 Ps0 ProcessHistoryProcess #zField
Ps0 f0 guid 161935DA38EB511D #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 211 64 #arcP
Ps0 f5 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ch.ivyteam.ivy.workflow.ICase,Boolean)' #txt
Ps0 f5 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Ps0 f5 requestMappingAction 'param.caseData=in.iCase;
param.isShowBackButton=true;
' #txt
Ps0 f5 responseMappingAction 'out=in;
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
Ps0 f5 224 138 144 44 -65 -8 #rect
Ps0 f5 @|CallSubIcon #fIcon
Ps0 f3 guid 1811D800009FD86C #txt
Ps0 f3 method openDetails(ICase) #txt
Ps0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Ps0 f3 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDetails(iCase)</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -25 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f6 109 160 224 160 #arcP
>Proto Ps0 .type com.axonivy.portal.component.ProcessHistory.ProcessHistoryData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f6 head f5 mainIn #connect
Ps0 f3 mainOut f6 tail #connect
