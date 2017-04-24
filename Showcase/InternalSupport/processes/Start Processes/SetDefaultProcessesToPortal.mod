[Ivy]
[>Created: Mon Apr 24 10:01:44 ICT 2017]
15B9DE75323C55DA 3.20 #module
>Proto >Proto Collection #zClass
Sl0 SetDefaultProcessesToPortal Big #zClass
Sl0 B #cInfo
Sl0 #process
Sl0 @TextInP .resExport .resExport #zField
Sl0 @TextInP .type .type #zField
Sl0 @TextInP .processKind .processKind #zField
Sl0 @AnnotationInP-0n ai ai #zField
Sl0 @MessageFlowInP-0n messageIn messageIn #zField
Sl0 @MessageFlowOutP-0n messageOut messageOut #zField
Sl0 @TextInP .xml .xml #zField
Sl0 @TextInP .responsibility .responsibility #zField
Sl0 @StartRequest f0 '' #zField
Sl0 @EndTask f1 '' #zField
Sl0 @GridStep f3 '' #zField
Sl0 @PushWFArc f4 '' #zField
Sl0 @PushWFArc f2 '' #zField
>Proto Sl0 Sl0 SetDefaultProcessesToPortal #zField
Sl0 f0 outLink start.ivp #txt
Sl0 f0 type internaltest.Data #txt
Sl0 f0 inParamDecl '<> param;' #txt
Sl0 f0 actionDecl 'internaltest.Data out;
' #txt
Sl0 f0 guid 15B9DE7532A2CBC0 #txt
Sl0 f0 requestEnabled true #txt
Sl0 f0 triggerEnabled false #txt
Sl0 f0 callSignature start() #txt
Sl0 f0 caseData businessCase.attach=true #txt
Sl0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Sl0 f0 @C|.responsibility Everybody #txt
Sl0 f0 51 83 26 26 14 0 #rect
Sl0 f0 @|StartRequestIcon #fIcon
Sl0 f1 type internaltest.Data #txt
Sl0 f1 51 339 26 26 14 0 #rect
Sl0 f1 @|EndIcon #fIcon
Sl0 f3 actionDecl 'internaltest.Data out;
' #txt
Sl0 f3 actionTable 'out=in;
' #txt
Sl0 f3 actionCode 'import ch.ivyteam.ivy.workflow.StandardProcessType;
String defaultProcessLibraryName = "ch.ivyteam.ivy.project.portal:portalTemplate"; // use library name of your project with the default (start) processes
ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, defaultProcessLibraryName);' #txt
Sl0 f3 security system #txt
Sl0 f3 type internaltest.Data #txt
Sl0 f3 46 196 36 24 20 -2 #rect
Sl0 f3 @|StepIcon #fIcon
Sl0 f4 expr out #txt
Sl0 f4 64 109 64 196 #arcP
Sl0 f2 expr out #txt
Sl0 f2 64 220 64 339 #arcP
>Proto Sl0 .type internaltest.Data #txt
>Proto Sl0 .processKind NORMAL #txt
>Proto Sl0 0 0 32 24 18 0 #rect
>Proto Sl0 @|BIcon #fIcon
Sl0 f0 mainOut f4 tail #connect
Sl0 f4 head f3 mainIn #connect
Sl0 f3 mainOut f2 tail #connect
Sl0 f2 head f1 mainIn #connect
