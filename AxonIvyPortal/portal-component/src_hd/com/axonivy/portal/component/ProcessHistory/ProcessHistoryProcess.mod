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
Ps0 @UdMethod f3 '' #zField
Ps0 @CallSub f4 '' #zField
Ps0 @UdProcessEnd f6 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @Alternative f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @PushWFArc f12 '' #zField
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
Ps0 f3 guid 1811D800009FD86C #txt
Ps0 f3 method openDetails(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Ps0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase,Boolean isOpenInFrame> param;' #txt
Ps0 f3 inParameterMapAction 'out.iCase=param.iCase;
out.isOpenInFrame=param.isOpenInFrame;
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
Ps0 f4 processCall 'Functional Processes/OpenCaseDetailsHook:call(ch.ivyteam.ivy.workflow.ICase,Boolean,Boolean)' #txt
Ps0 f4 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseView,Boolean isOpenInFrame,Boolean isShowBackButton> param;' #txt
Ps0 f4 requestMappingAction 'param.caseView=in.iCase;
param.isOpenInFrame=in.isOpenInFrame;
param.isShowBackButton=true;
' #txt
Ps0 f4 responseMappingAction 'out=in;
out.caseDetailsUrl=result.caseDetailsUrl;
' #txt
Ps0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
Ps0 f4 224 138 144 44 -65 -8 #rect
Ps0 f4 @|CallSubIcon #fIcon
Ps0 f6 699 147 26 26 0 12 #rect
Ps0 f6 @|UdProcessEndIcon #fIcon
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import com.axonivy.portal.component.util.PortalNavigatorInFrameAPI;

PortalNavigatorInFrameAPI.navigateToUrl(in.caseDetailsUrl);' #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to case details</name>
    </language>
</elementInfo>
' #txt
Ps0 f9 512 138 144 44 -65 -8 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f11 656 160 699 160 #arcP
Ps0 f5 109 160 224 160 #arcP
Ps0 f7 440 144 32 32 0 16 #rect
Ps0 f7 @|AlternativeIcon #fIcon
Ps0 f8 368 160 440 160 #arcP
Ps0 f10 expr in #txt
Ps0 f10 outCond in.isOpenInFrame #txt
Ps0 f10 472 160 512 160 #arcP
Ps0 f12 expr in #txt
Ps0 f12 456 176 712 173 #arcP
Ps0 f12 1 456 232 #addKink
Ps0 f12 2 712 232 #addKink
Ps0 f12 1 0.505859375 0 0 #arcLabel
>Proto Ps0 .type com.axonivy.portal.component.ProcessHistory.ProcessHistoryData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f9 mainOut f11 tail #connect
Ps0 f11 head f6 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f4 mainOut f8 tail #connect
Ps0 f8 head f7 in #connect
Ps0 f7 out f10 tail #connect
Ps0 f10 head f9 mainIn #connect
Ps0 f7 out f12 tail #connect
Ps0 f12 head f6 mainIn #connect
