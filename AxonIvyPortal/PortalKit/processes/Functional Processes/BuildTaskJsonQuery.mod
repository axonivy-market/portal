[Ivy]
[>Created: Wed Apr 12 08:58:03 ICT 2017]
15B32B518F94CCBD 3.20 #module
>Proto >Proto Collection #zClass
By0 BuildTaskJsonQuery Big #zClass
By0 B #cInfo
By0 #process
By0 @TextInP .resExport .resExport #zField
By0 @TextInP .type .type #zField
By0 @TextInP .processKind .processKind #zField
By0 @AnnotationInP-0n ai ai #zField
By0 @MessageFlowInP-0n messageIn messageIn #zField
By0 @MessageFlowOutP-0n messageOut messageOut #zField
By0 @TextInP .xml .xml #zField
By0 @TextInP .responsibility .responsibility #zField
By0 @StartSub f0 '' #zField
By0 @EndSub f1 '' #zField
By0 @GridStep f3 '' #zField
By0 @PushWFArc f4 '' #zField
By0 @PushWFArc f2 '' #zField
By0 @InfoButton f5 '' #zField
By0 @AnnotationArc f6 '' #zField
>Proto By0 By0 BuildTaskJsonQuery #zField
By0 f0 inParamDecl '<> param;' #txt
By0 f0 outParamDecl '<java.lang.String jsonQuery> result;
' #txt
By0 f0 outParamTable 'result.jsonQuery=in.jsonQuery;
' #txt
By0 f0 actionDecl 'ch.ivy.add.portalkit.BuildTaskJsonQueryData out;
' #txt
By0 f0 callSignature buildTaskJsonQuery() #txt
By0 f0 type ch.ivy.add.portalkit.BuildTaskJsonQueryData #txt
By0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buildTaskJsonQuery()</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
By0 f0 51 83 26 26 14 0 #rect
By0 f0 @|StartSubIcon #fIcon
By0 f1 type ch.ivy.add.portalkit.BuildTaskJsonQueryData #txt
By0 f1 51 339 26 26 14 0 #rect
By0 f1 @|EndSubIcon #fIcon
By0 f3 actionDecl 'ch.ivy.add.portalkit.BuildTaskJsonQueryData out;
' #txt
By0 f3 actionTable 'out=in;
' #txt
By0 f3 type ch.ivy.add.portalkit.BuildTaskJsonQueryData #txt
By0 f3 46 212 36 24 20 -2 #rect
By0 f3 @|StepIcon #fIcon
By0 f4 expr out #txt
By0 f4 64 109 64 212 #arcP
By0 f2 expr out #txt
By0 f2 64 236 64 339 #arcP
By0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HINT: build your task query and convert it to json string

out.jsonQuery = TaskQuery.create().where().customVarCharField5().isLike("%HELLO WORLD%").asJson();</name>
        <nameStyle>157
</nameStyle>
    </language>
</elementInfo>
' #txt
By0 f5 152 194 592 60 -293 -24 #rect
By0 f5 @|IBIcon #fIcon
By0 f6 152 224 82 224 #arcP
>Proto By0 .type ch.ivy.add.portalkit.BuildTaskJsonQueryData #txt
>Proto By0 .processKind CALLABLE_SUB #txt
>Proto By0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto By0 0 0 32 24 18 0 #rect
>Proto By0 @|BIcon #fIcon
By0 f0 mainOut f4 tail #connect
By0 f4 head f3 mainIn #connect
By0 f3 mainOut f2 tail #connect
By0 f2 head f1 mainIn #connect
By0 f5 ao f6 tail #connect
By0 f6 head f3 @CG|ai #connect
