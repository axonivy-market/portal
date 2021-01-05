[Ivy]
16EF3459A3FCF253 9.2.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalFinishTaskHandleProcess Big #zClass
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
Ps0 @GridStep f8 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @InfoButton f7 '' #zField
Ps0 @AnnotationArc f9 '' #zField
>Proto Ps0 Ps0 PortalFinishTaskHandleProcess #zField
Ps0 f0 guid 15C67E57F20669EF #txt
Ps0 f0 method start(Boolean,ch.ivyteam.ivy.workflow.ICase) #txt
Ps0 f0 inParameterDecl '<Boolean isTaskFinished,ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Ps0 f0 inParameterMapAction 'out.iCase=param.iCase;
out.isTaskFinished=param.isTaskFinished;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(isTaskFinished, iCase)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 211 64 #arcP
Ps0 f8 actionTable 'out=in;
' #txt
Ps0 f8 actionCode 'import ch.ivy.addon.portalkit.util.GrowlMessageUtils;

GrowlMessageUtils.addFeedbackMessage(in.isTaskFinished, in.iCase);' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 152 138 144 44 -54 -16 #rect
Ps0 f8 @|StepIcon #fIcon
Ps0 f4 339 147 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f5 296 160 339 160 #arcP
Ps0 f3 guid 16EF347B4B0F3ECF #txt
Ps0 f3 method close() #txt
Ps0 f3 inParameterDecl '<> param;' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -23 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f6 109 160 152 160 #arcP
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sample scenario to test&#13;
start a task &gt; finish the task</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 184 202 160 44 -74 -16 #rect
Ps0 f7 @|IBIcon #fIcon
Ps0 f9 264 202 224 182 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f8 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f3 mainOut f6 tail #connect
Ps0 f6 head f8 mainIn #connect
Ps0 f7 ao f9 tail #connect
Ps0 f9 head f8 @CG|ai #connect
