[Ivy]
176EF69EC66B4E37 9.3.1 #module
>Proto >Proto Collection #zClass
Ps0 PortalCaseDetailsOverrideProcess Big #zClass
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
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 PortalCaseDetailsOverrideProcess #zField
Ps0 f0 guid 16BBB5AA8B6BE5B7 #txt
Ps0 f0 method start(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Ps0 f0 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase caseInfo,Boolean isShowBackButton> param;' #txt
Ps0 f0 inParameterMapAction 'out.caseInfo=param.caseInfo;
out.isShowBackButton=param.isShowBackButton;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ICase,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f1 467 51 26 26 0 12 #rect
Ps0 f3 guid 16BBB5AA8D8DEFBB #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -15 12 #rect
Ps0 f4 211 147 26 26 0 12 #rect
Ps0 f5 expr out #txt
Ps0 f5 109 160 211 160 #arcP
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;

in.isTaskStartedInDetails = ivy.session.getAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString()) as Boolean;' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check is open from done task</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 224 42 176 44 -82 -8 #rect
Ps0 f7 expr out #txt
Ps0 f7 109 64 224 64 #arcP
Ps0 f2 400 64 467 64 #arcP
>Proto Ps0 .type com.axonivy.portal.developerexamples.customization.PortalCaseDetailsOverride.PortalCaseDetailsOverrideData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f0 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
