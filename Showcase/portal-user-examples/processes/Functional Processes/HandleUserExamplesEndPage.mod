[Ivy]
1709A202E7AEC7E8 9.2.0 #module
>Proto >Proto Collection #zClass
He0 HandleEndPage Big #zClass
He0 B #cInfo
He0 #process
He0 @TextInP .type .type #zField
He0 @TextInP .processKind .processKind #zField
He0 @TextInP .xml .xml #zField
He0 @TextInP .responsibility .responsibility #zField
He0 @EndSub f3 '' #zField
He0 @StartSub f5 '' #zField
He0 @GridStep f4 '' #zField
He0 @PushWFArc f6 '' #zField
He0 @PushWFArc f7 '' #zField
>Proto He0 He0 HandleEndPage #zField
He0 f3 354 73 30 30 0 15 #rect
He0 f3 @|EndSubIcon #fIcon
He0 f5 inParamDecl '<> param;' #txt
He0 f5 outParamDecl '<String callbackUrl> result;' #txt
He0 f5 outParamTable 'result.callbackUrl=in.callbackUrl;
' #txt
He0 f5 callSignature handleUserExamplesEndPage() #txt
He0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handleUserExamplesEndPage()</name>
        <nameStyle>25,5
2,5
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f5 50 73 30 30 -48 17 #rect
He0 f5 @|StartSubIcon #fIcon
He0 f4 actionTable 'out=in;
out.caseId=in.caseId;
' #txt
He0 f4 actionCode 'in.caseId =  ivy.task.getCase().getBusinessCase().getId();
in.callbackUrl = ivy.html.startRef("1709A97D25EAB86E/start.ivp")+"?caseId=" + in.caseId;
' #txt
He0 f4 security system #txt
He0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get end page url</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f4 185 66 112 44 -44 -8 #rect
He0 f4 @|StepIcon #fIcon
He0 f6 expr out #txt
He0 f6 80 88 185 88 #arcP
He0 f7 expr out #txt
He0 f7 297 88 354 88 #arcP
>Proto He0 .type com.axonivy.portal.userexamples.credit.HandleEndPageData #txt
>Proto He0 .processKind CALLABLE_SUB #txt
>Proto He0 0 0 32 24 18 0 #rect
>Proto He0 @|BIcon #fIcon
He0 f5 mainOut f6 tail #connect
He0 f6 head f4 mainIn #connect
He0 f4 mainOut f7 tail #connect
He0 f7 head f3 mainIn #connect
