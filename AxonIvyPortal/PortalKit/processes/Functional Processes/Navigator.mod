[Ivy]
1543CB1F7FCE2CC1 3.20 #module
>Proto >Proto Collection #zClass
Nr0 Navigator Big #zClass
Nr0 B #cInfo
Nr0 #process
Nr0 @TextInP .resExport .resExport #zField
Nr0 @TextInP .type .type #zField
Nr0 @TextInP .processKind .processKind #zField
Nr0 @AnnotationInP-0n ai ai #zField
Nr0 @MessageFlowInP-0n messageIn messageIn #zField
Nr0 @MessageFlowOutP-0n messageOut messageOut #zField
Nr0 @TextInP .xml .xml #zField
Nr0 @TextInP .responsibility .responsibility #zField
Nr0 @StartSub f0 '' #zField
Nr0 @EndSub f1 '' #zField
Nr0 @InfoButton f3 '' #zField
Nr0 @PushWFArc f2 '' #zField
Nr0 @AnnotationArc f4 '' #zField
Nr0 @StartSub f5 '' #zField
Nr0 @EndSub f6 '' #zField
Nr0 @PushWFArc f7 '' #zField
Nr0 @InfoButton f11 '' #zField
Nr0 @AnnotationArc f12 '' #zField
Nr0 @StartSub f8 '' #zField
Nr0 @EndSub f9 '' #zField
Nr0 @PushWFArc f10 '' #zField
Nr0 @StartSub f13 '' #zField
Nr0 @EndSub f14 '' #zField
Nr0 @PushWFArc f15 '' #zField
Nr0 @InfoButton f16 '' #zField
Nr0 @AnnotationArc f17 '' #zField
Nr0 @InfoButton f18 '' #zField
Nr0 @AnnotationArc f19 '' #zField
>Proto Nr0 Nr0 Navigator #zField
Nr0 f0 inParamDecl '<java.lang.String caseName,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;' #txt
Nr0 f0 outParamDecl '<> result;
' #txt
Nr0 f0 actionDecl 'ch.ivy.add.portalkit.NavigatorData out;
' #txt
Nr0 f0 callSignature viewCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f0 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewCase(String,GlobalCaseId)</name>
    </language>
</elementInfo>
' #txt
Nr0 f0 51 83 26 26 14 0 #rect
Nr0 f0 @|StartSubIcon #fIcon
Nr0 f1 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f1 51 339 26 26 14 0 #rect
Nr0 f1 @|EndSubIcon #fIcon
Nr0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing case.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f3 128 162 496 172 -238 -84 #rect
Nr0 f3 @|IBIcon #fIcon
Nr0 f2 expr out #txt
Nr0 f2 64 109 64 339 #arcP
Nr0 f4 128 248 64 224 #arcP
Nr0 f5 inParamDecl '<java.lang.Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,java.lang.String caseName> param;' #txt
Nr0 f5 outParamDecl '<> result;
' #txt
Nr0 f5 actionDecl 'ch.ivy.add.portalkit.NavigatorData out;
' #txt
Nr0 f5 callSignature viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String) #txt
Nr0 f5 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTask(String,GlobalCaseId,String)</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f5 51 411 26 26 14 0 #rect
Nr0 f5 @|StartSubIcon #fIcon
Nr0 f6 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f6 51 555 26 26 14 0 #rect
Nr0 f6 @|EndSubIcon #fIcon
Nr0 f7 expr out #txt
Nr0 f7 64 437 64 555 #arcP
Nr0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing task.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f11 128 498 496 172 -238 -84 #rect
Nr0 f11 @|IBIcon #fIcon
Nr0 f12 128 584 64 496 #arcP
Nr0 f8 inParamDecl '<java.lang.String chartName,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
Nr0 f8 outParamDecl '<> result;
' #txt
Nr0 f8 actionDecl 'ch.ivy.add.portalkit.NavigatorData out;
' #txt
Nr0 f8 callSignature viewTaskForAnalytic(String,ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
Nr0 f8 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTaskForAnalytic(String, TaskQuery)</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f8 724 407 26 26 14 0 #rect
Nr0 f8 @|StartSubIcon #fIcon
Nr0 f9 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f9 724 551 26 26 14 0 #rect
Nr0 f9 @|EndSubIcon #fIcon
Nr0 f10 expr out #txt
Nr0 f10 737 433 737 551 #arcP
Nr0 f13 inParamDecl '<java.lang.String chartName,ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
Nr0 f13 outParamDecl '<> result;
' #txt
Nr0 f13 actionDecl 'ch.ivy.add.portalkit.NavigatorData out;
' #txt
Nr0 f13 callSignature viewCaseForAnalytic(String,ch.ivyteam.ivy.workflow.query.CaseQuery) #txt
Nr0 f13 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewCaseForAnalytic(String, CaseQuery)</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f13 724 84 26 26 14 0 #rect
Nr0 f13 @|StartSubIcon #fIcon
Nr0 f14 type ch.ivy.add.portalkit.NavigatorData #txt
Nr0 f14 724 340 26 26 14 0 #rect
Nr0 f14 @|EndSubIcon #fIcon
Nr0 f15 expr out #txt
Nr0 f15 737 110 737 340 #arcP
Nr0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing case.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f16 815 163 496 172 -238 -84 #rect
Nr0 f16 @|IBIcon #fIcon
Nr0 f17 815 249 737 225 #arcP
Nr0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit
to override and implement the functionality
of redirecting into a HtmlDialog for viewing task.

By default, this is doing nothing. The default implementation, however,
is put in PortalTemplate.</name>
        <nameStyle>15,0,5,8
1,5,8
246,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f18 857 497 496 172 -238 -84 #rect
Nr0 f18 @|IBIcon #fIcon
Nr0 f19 857 583 737 492 #arcP
>Proto Nr0 .type ch.ivy.add.portalkit.NavigatorData #txt
>Proto Nr0 .processKind CALLABLE_SUB #txt
>Proto Nr0 0 0 32 24 18 0 #rect
>Proto Nr0 @|BIcon #fIcon
Nr0 f0 mainOut f2 tail #connect
Nr0 f2 head f1 mainIn #connect
Nr0 f3 ao f4 tail #connect
Nr0 f4 head f2 ai #connect
Nr0 f5 mainOut f7 tail #connect
Nr0 f7 head f6 mainIn #connect
Nr0 f11 ao f12 tail #connect
Nr0 f12 head f7 ai #connect
Nr0 f8 mainOut f10 tail #connect
Nr0 f10 head f9 mainIn #connect
Nr0 f13 mainOut f15 tail #connect
Nr0 f15 head f14 mainIn #connect
Nr0 f16 ao f17 tail #connect
Nr0 f17 head f15 ai #connect
Nr0 f18 ao f19 tail #connect
Nr0 f19 head f10 ai #connect
