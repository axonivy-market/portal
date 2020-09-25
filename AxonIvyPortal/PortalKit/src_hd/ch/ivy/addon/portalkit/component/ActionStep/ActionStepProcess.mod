[Ivy]
16B6880352B52912 9.2.0 #module
>Proto >Proto Collection #zClass
As0 ActionStepProcess Big #zClass
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
As0 @UdEvent f3 '' #zField
As0 @UdExitEnd f4 '' #zField
As0 @PushWFArc f5 '' #zField
As0 @UdMethod f6 '' #zField
As0 @CallSub f7 '' #zField
As0 @PushWFArc f8 '' #zField
>Proto As0 As0 ActionStepProcess #zField
As0 f0 guid 16B6880355C388FF #txt
As0 f0 method start() #txt
As0 f0 inParameterDecl 'ch.ivy.add.portalkit.Data out;
' #txt
As0 f0 outParameterDecl '<> result;' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f0 @|UdInitIcon #fIcon
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|UdProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
As0 f3 guid 16B688035827903A #txt
As0 f3 actionTable 'out=in;
' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
As0 f3 83 243 26 26 -15 12 #rect
As0 f3 @|UdEventIcon #fIcon
As0 f4 211 243 26 26 0 12 #rect
As0 f4 @|UdExitEndIcon #fIcon
As0 f5 expr out #txt
As0 f5 109 256 211 256 #arcP
As0 f6 guid 1700AAD6E969D73E #txt
As0 f6 method openDetails(ICase) #txt
As0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
As0 f6 inParameterMapAction 'out.iCase=param.iCase;
' #txt
As0 f6 outParameterDecl '<> result;' #txt
As0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDetails(iCase)</name>
    </language>
</elementInfo>
' #txt
As0 f6 83 147 26 26 -25 15 #rect
As0 f6 @|UdMethodIcon #fIcon
As0 f7 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ch.ivyteam.ivy.workflow.ICase,Boolean)' #txt
As0 f7 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
As0 f7 requestMappingAction 'param.caseData=in.iCase;
param.isShowBackButton=true;
' #txt
As0 f7 responseMappingAction 'out=in;
' #txt
As0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
As0 f7 224 138 144 44 -65 -8 #rect
As0 f7 @|CallSubIcon #fIcon
As0 f8 109 160 224 160 #arcP
>Proto As0 .type ch.ivy.addon.portalkit.component.ActionStep.ActionStepData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f3 mainOut f5 tail #connect
As0 f5 head f4 mainIn #connect
As0 f8 head f7 mainIn #connect
As0 f6 mainOut f8 tail #connect
