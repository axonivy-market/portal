[Ivy]
16854B53D2D387A2 7.5.0 #module
>Proto >Proto Collection #zClass
By0 BuildTaskQuery Big #zClass
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
By0 @StartSub f7 '' #zField
By0 @PushWFArc f8 '' #zField
>Proto By0 By0 BuildTaskQuery #zField
By0 f0 inParamDecl '<> param;' #txt
By0 f0 outParamDecl '<ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> result;' #txt
By0 f0 outParamTable 'result.taskQuery=in.#taskQuery;
' #txt
By0 f0 callSignature buildTaskQuery() #txt
By0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buildTaskQuery()</name>
        <nameStyle>16,5
</nameStyle>
    </language>
</elementInfo>
' #txt
By0 f0 77 49 30 30 -47 17 #rect
By0 f0 @|StartSubIcon #fIcon
By0 f1 469 49 30 30 0 15 #rect
By0 f1 @|EndSubIcon #fIcon
By0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HINT: build your task query

out.taskQuery = TaskQuery.create().where().customField().stringField("CustomVarCharField5").isLike("%HELLO WORLD%");

If you want to differentiate the customization in home page from customization in other places like task list, 
just check  the attribute isQueryForHomePage

if (in.isQueryForHomePage) { // in home page
	in.taskQuery = TaskQuery.create().where().activatorUserId().isNotNull();
}</name>
        <nameStyle>425,5
</nameStyle>
    </language>
</elementInfo>
' #txt
By0 f5 204 140 688 172 -340 -80 #rect
By0 f5 @|IBIcon #fIcon
By0 f2 actionTable 'out=in;
' #txt
By0 f2 actionCode 'import ch.ivyteam.ivy.workflow.query.TaskQuery;

if (in.isQueryForHomePage) { // in home page
	//in.taskQuery = TaskQuery.create().where().activatorUserId().isNotNull();
}' #txt
By0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build task query</name>
    </language>
</elementInfo>
' #txt
By0 f2 232 42 112 44 -43 -8 #rect
By0 f2 @|StepIcon #fIcon
By0 f3 expr out #txt
By0 f3 107 64 232 64 #arcP
By0 f4 expr out #txt
By0 f4 344 64 469 64 #arcP
By0 f6 548 140 288 86 #arcP
By0 f7 inParamDecl '<Boolean isQueryForHomePage> param;' #txt
By0 f7 inParamTable 'out.isQueryForHomePage=param.isQueryForHomePage;
' #txt
By0 f7 outParamDecl '<ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> result;' #txt
By0 f7 outParamTable 'result.taskQuery=in.#taskQuery;
' #txt
By0 f7 callSignature buildTaskQuery(Boolean) #txt
By0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buildTaskQuery(Boolean)</name>
    </language>
</elementInfo>
' #txt
By0 f7 81 145 30 30 -70 17 #rect
By0 f7 @|StartSubIcon #fIcon
By0 f8 expr out #txt
By0 f8 109 153 244 86 #arcP
>Proto By0 .type _ch.ivyteam.ivy.project.portal.examples.BuildTaskQueryOverrideData #txt
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
By0 f7 mainOut f8 tail #connect
By0 f8 head f2 mainIn #connect
