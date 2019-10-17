[Ivy]
15F95D721D0C7224 7.5.0 #module
>Proto >Proto Collection #zClass
By0 BuildCaseQuery Big #zClass
By0 B #cInfo
By0 #process
By0 @TextInP .type .type #zField
By0 @TextInP .processKind .processKind #zField
By0 @AnnotationInP-0n ai ai #zField
By0 @MessageFlowInP-0n messageIn messageIn #zField
By0 @MessageFlowOutP-0n messageOut messageOut #zField
By0 @TextInP .xml .xml #zField
By0 @TextInP .responsibility .responsibility #zField
By0 @StartSub f0 '' #zField
By0 @EndSub f1 '' #zField
By0 @InfoButton f5 '' #zField
By0 @GridStep f2 '' #zField
By0 @PushWFArc f3 '' #zField
By0 @PushWFArc f4 '' #zField
By0 @AnnotationArc f6 '' #zField
>Proto By0 By0 BuildCaseQuery #zField
By0 f0 inParamDecl '<> param;' #txt
By0 f0 outParamDecl '<ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> result;' #txt
By0 f0 outParamTable 'result.caseQuery=in.#caseQuery;
' #txt
By0 f0 callSignature buildCaseQuery() #txt
By0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buildCaseQuery()</name>
        <nameStyle>16,5
</nameStyle>
    </language>
</elementInfo>
' #txt
By0 f0 77 49 30 30 -48 17 #rect
By0 f0 @|StartSubIcon #fIcon
By0 f1 469 49 30 30 0 15 #rect
By0 f1 @|EndSubIcon #fIcon
By0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HINT: build your case query

import ch.ivyteam.ivy.workflow.query.CaseQuery;
out.caseQuery = CaseQuery.create().where().customField().stringField("CustomVarCharField5").isLike("%HELLO WORLD%");</name>
        <nameStyle>193,5
</nameStyle>
    </language>
</elementInfo>
' #txt
By0 f5 12 148 704 76 -344 -32 #rect
By0 f5 @|IBIcon #fIcon
By0 f2 actionTable 'out=in;
' #txt
By0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build case query</name>
    </language>
</elementInfo>
' #txt
By0 f2 232 42 112 44 -45 -8 #rect
By0 f2 @|StepIcon #fIcon
By0 f3 expr out #txt
By0 f3 107 64 232 64 #arcP
By0 f4 expr out #txt
By0 f4 344 64 469 64 #arcP
By0 f6 364 148 238 86 #arcP
>Proto By0 .type ch.ivy.add.portalkit.BuildCaseQueryData #txt
>Proto By0 .processKind CALLABLE_SUB #txt
>Proto By0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto By0 0 0 32 24 18 0 #rect
>Proto By0 @|BIcon #fIcon
By0 f0 mainOut f3 tail #connect
By0 f3 head f2 mainIn #connect
By0 f2 mainOut f4 tail #connect
By0 f4 head f1 mainIn #connect
By0 f5 ao f6 tail #connect
By0 f6 head f2 @CG|ai #connect
