[Ivy]
16BBB5AA8A5CD43A 9.3.1 #module
>Proto >Proto Collection #zClass
Ps0 PortalCaseDetailsProcess Big #zClass
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
Ps0 @PushWFArc f2 '' #zField
Ps0 @GridStep f8 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f7 '' #zField
>Proto Ps0 Ps0 PortalCaseDetailsProcess #zField
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
Ps0 f1 571 51 26 26 0 12 #rect
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
Ps0 f6 360 42 176 44 -82 -8 #rect
Ps0 f2 536 64 571 64 #arcP
Ps0 f8 actionTable 'out=in;
' #txt
Ps0 f8 actionCode 'import ch.ivy.addon.portalkit.datamodel.internal.RelatedCaseLazyDataModel;

in.caseLazyDataModel = new RelatedCaseLazyDataModel(in.caseInfo.getId());' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data model</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 216 42 112 44 -40 -8 #rect
Ps0 f9 expr out #txt
Ps0 f9 109 64 216 64 #arcP
Ps0 f7 328 64 360 64 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalCaseDetails.PortalCaseDetailsData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f6 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f0 mainOut f9 tail #connect
Ps0 f9 head f8 mainIn #connect
Ps0 f8 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
