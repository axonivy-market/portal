[Ivy]
1543CB1F7FCE2CC1 9.2.0 #module
>Proto >Proto Collection #zClass
Nr0 Navigator Big #zClass
Nr0 B #cInfo
Nr0 #process
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
Nr0 @StartSub f20 '' #zField
Nr0 @EndSub f21 '' #zField
Nr0 @PushWFArc f22 '' #zField
Nr0 @InfoButton f53 '' #zField
Nr0 @InfoButton f23 '' #zField
Nr0 @StartSub f24 '' #zField
Nr0 @EndSub f25 '' #zField
Nr0 @PushWFArc f26 '' #zField
Nr0 @InfoButton f27 '' #zField
Nr0 @EndSub f28 '' #zField
Nr0 @StartSub f29 '' #zField
Nr0 @PushWFArc f30 '' #zField
Nr0 @StartSub f32 '' #zField
Nr0 @EndSub f33 '' #zField
Nr0 @PushWFArc f34 '' #zField
Nr0 @AnnotationArc f31 '' #zField
Nr0 @EndSub f35 '' #zField
Nr0 @InfoButton f36 '' #zField
Nr0 @StartSub f37 '' #zField
Nr0 @PushWFArc f38 '' #zField
Nr0 @EndSub f39 '' #zField
Nr0 @InfoButton f40 '' #zField
Nr0 @StartSub f41 '' #zField
Nr0 @PushWFArc f42 '' #zField
Nr0 @AnnotationArc f43 '' #zField
Nr0 @AnnotationArc f44 '' #zField
Nr0 @AnnotationArc f45 '' #zField
Nr0 @AnnotationArc f46 '' #zField
>Proto Nr0 Nr0 Navigator #zField
Nr0 f0 inParamDecl '<ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;' #txt
Nr0 f0 outParamDecl '<> result;' #txt
Nr0 f0 callSignature viewCase(ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewCase(GlobalCaseId)</name>
    </language>
</elementInfo>
' #txt
Nr0 f0 51 83 26 26 14 0 #rect
Nr0 f0 @|StartSubIcon #fIcon
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
Nr0 f5 inParamDecl '<Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,String caseName> param;' #txt
Nr0 f5 outParamDecl '<> result;' #txt
Nr0 f5 callSignature viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String) #txt
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
Nr0 f8 inParamDecl '<String chartName,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
Nr0 f8 outParamDecl '<> result;' #txt
Nr0 f8 callSignature viewTaskForAnalytic(String,ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
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
Nr0 f9 724 551 26 26 14 0 #rect
Nr0 f9 @|EndSubIcon #fIcon
Nr0 f10 expr out #txt
Nr0 f10 737 433 737 551 #arcP
Nr0 f13 inParamDecl '<String chartName,ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
Nr0 f13 outParamDecl '<> result;' #txt
Nr0 f13 callSignature viewCaseForAnalytic(String,ch.ivyteam.ivy.workflow.query.CaseQuery) #txt
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
Nr0 f20 inParamDecl '<String businessCaseName,ch.ivy.addon.portalkit.dto.GlobalCaseId businessCaseId> param;' #txt
Nr0 f20 outParamDecl '<> result;' #txt
Nr0 f20 callSignature viewTechnicalCasesOfBusniessCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTechnicalCasesOfBusniessCase(String,GlobalCaseId)</name>
    </language>
</elementInfo>
' #txt
Nr0 f20 1521 81 30 30 -164 17 #rect
Nr0 f20 @|StartSubIcon #fIcon
Nr0 f21 1521 337 30 30 0 15 #rect
Nr0 f21 @|EndSubIcon #fIcon
Nr0 f22 expr out #txt
Nr0 f22 1536 111 1536 337 #arcP
Nr0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit&#xD;
to override and implement the functionality&#xD;
of redirecting into a HtmlDialog for viewing case.&#xD;
&#xD;
By default, this is doing nothing. The default implementation, however,&#xD;
is put in PortalTemplate.</name>
        <nameStyle>15,5,8,0
252,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f53 1632 147 496 172 -238 -84 #rect
Nr0 f53 @|IBIcon #fIcon
Nr0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Nr0 f23 1600 498 496 172 -238 -84 #rect
Nr0 f23 @|IBIcon #fIcon
Nr0 f24 inParamDecl '<Long taskId> param;' #txt
Nr0 f24 outParamDecl '<> result;' #txt
Nr0 f24 callSignature viewRelatedTask(Long) #txt
Nr0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewRelatedTask(String)</name>
        <nameStyle>23,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f24 1523 411 26 26 14 0 #rect
Nr0 f24 @|StartSubIcon #fIcon
Nr0 f25 1523 555 26 26 14 0 #rect
Nr0 f25 @|EndSubIcon #fIcon
Nr0 f26 expr out #txt
Nr0 f26 1536 437 1536 555 #arcP
Nr0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Nr0 f27 128 842 496 172 -238 -84 #rect
Nr0 f27 @|IBIcon #fIcon
Nr0 f28 51 899 26 26 14 0 #rect
Nr0 f28 @|EndSubIcon #fIcon
Nr0 f29 inParamDecl '<Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,String caseName> param;' #txt
Nr0 f29 outParamDecl '<> result;' #txt
Nr0 f29 callSignature viewTaskInFrame(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String) #txt
Nr0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTaskInFrame(String,GlobalCaseId,String)</name>
        <nameStyle>15,5
28,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f29 51 755 26 26 14 0 #rect
Nr0 f29 @|StartSubIcon #fIcon
Nr0 f30 expr out #txt
Nr0 f30 64 781 64 899 #arcP
Nr0 f32 inParamDecl '<ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;' #txt
Nr0 f32 outParamDecl '<> result;' #txt
Nr0 f32 callSignature viewCaseItemDetailsInIFrame(ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewCaseItemDetailsInIFrame(GlobalCaseId)</name>
    </language>
</elementInfo>
' #txt
Nr0 f32 724 751 26 26 14 0 #rect
Nr0 f32 @|StartSubIcon #fIcon
Nr0 f33 724 895 26 26 14 0 #rect
Nr0 f33 @|EndSubIcon #fIcon
Nr0 f34 expr out #txt
Nr0 f34 737 777 737 895 #arcP
Nr0 f31 128 928 64 840 #arcP
Nr0 f35 1523 899 26 26 14 0 #rect
Nr0 f35 @|EndSubIcon #fIcon
Nr0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Nr0 f36 1600 842 496 172 -238 -84 #rect
Nr0 f36 @|IBIcon #fIcon
Nr0 f37 inParamDecl '<Long taskId> param;' #txt
Nr0 f37 outParamDecl '<> result;' #txt
Nr0 f37 callSignature viewRelatedTaskInFrame(Long) #txt
Nr0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewRelatedTaskInFrame(String)</name>
        <nameStyle>22,5
8,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f37 1523 755 26 26 14 0 #rect
Nr0 f37 @|StartSubIcon #fIcon
Nr0 f38 expr out #txt
Nr0 f38 1536 781 1536 899 #arcP
Nr0 f39 49 1329 30 30 0 15 #rect
Nr0 f39 @|EndSubIcon #fIcon
Nr0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Technical Note

This callable is introduced for clients of PortalKit&#xD;
to override and implement the functionality&#xD;
of redirecting into a HtmlDialog for viewing case.&#xD;
&#xD;
By default, this is doing nothing. The default implementation, however,&#xD;
is put in PortalTemplate.</name>
        <nameStyle>15,5,8,0
252,5,8
</nameStyle>
    </language>
</elementInfo>
' #txt
Nr0 f40 160 1139 496 172 -238 -84 #rect
Nr0 f40 @|IBIcon #fIcon
Nr0 f41 inParamDecl '<String businessCaseName,ch.ivy.addon.portalkit.dto.GlobalCaseId businessCaseId> param;' #txt
Nr0 f41 outParamDecl '<> result;' #txt
Nr0 f41 callSignature viewTechnicalCasesOfBusniessCaseInFrame(String,ch.ivy.addon.portalkit.dto.GlobalCaseId) #txt
Nr0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewTechnicalCasesOfBusniessCaseInFrame(String,GlobalCaseId)</name>
    </language>
</elementInfo>
' #txt
Nr0 f41 49 1073 30 30 34 3 #rect
Nr0 f41 @|StartSubIcon #fIcon
Nr0 f42 expr out #txt
Nr0 f42 64 1103 64 1329 #arcP
Nr0 f43 1600 584 1536 496 #arcP
Nr0 f44 1600 928 1536 840 #arcP
Nr0 f45 1632 233 1536 224 #arcP
Nr0 f45 1 1536 192 #addKink
Nr0 f45 0 0.6532732997428413 0 0 #arcLabel
Nr0 f46 160 1225 64 1216 #arcP
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
Nr0 f20 mainOut f22 tail #connect
Nr0 f22 head f21 mainIn #connect
Nr0 f24 mainOut f26 tail #connect
Nr0 f26 head f25 mainIn #connect
Nr0 f29 mainOut f30 tail #connect
Nr0 f30 head f28 mainIn #connect
Nr0 f32 mainOut f34 tail #connect
Nr0 f34 head f33 mainIn #connect
Nr0 f27 ao f31 tail #connect
Nr0 f31 head f30 ai #connect
Nr0 f37 mainOut f38 tail #connect
Nr0 f38 head f35 mainIn #connect
Nr0 f41 mainOut f42 tail #connect
Nr0 f42 head f39 mainIn #connect
Nr0 f23 ao f43 tail #connect
Nr0 f43 head f26 ai #connect
Nr0 f36 ao f44 tail #connect
Nr0 f44 head f38 ai #connect
Nr0 f53 ao f45 tail #connect
Nr0 f45 head f22 ai #connect
Nr0 f40 ao f46 tail #connect
Nr0 f46 head f42 ai #connect
