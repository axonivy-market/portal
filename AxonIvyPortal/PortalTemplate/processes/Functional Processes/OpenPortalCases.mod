[Ivy]
1540379C4B7261E4 7.5.0 #module
>Proto >Proto Collection #zClass
Ot0 OpenPortalCases Big #zClass
Ot0 B #cInfo
Ot0 #process
Ot0 @TextInP .type .type #zField
Ot0 @TextInP .processKind .processKind #zField
Ot0 @AnnotationInP-0n ai ai #zField
Ot0 @MessageFlowInP-0n messageIn messageIn #zField
Ot0 @MessageFlowOutP-0n messageOut messageOut #zField
Ot0 @TextInP .xml .xml #zField
Ot0 @TextInP .responsibility .responsibility #zField
Ot0 @StartSub f0 '' #zField
Ot0 @EndSub f1 '' #zField
Ot0 @UserDialog f3 '' #zField
Ot0 @PushWFArc f4 '' #zField
Ot0 @PushWFArc f2 '' #zField
>Proto Ot0 Ot0 OpenPortalCases #zField
Ot0 f0 inParamDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;' #txt
Ot0 f0 inParamTable 'out.view=param.view;
' #txt
Ot0 f0 outParamDecl '<> result;' #txt
Ot0 f0 callSignature useView(ch.ivy.addon.portal.generic.view.CaseView) #txt
Ot0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(CaseView)</name>
    </language>
</elementInfo>
' #txt
Ot0 f0 179 83 26 26 14 0 #rect
Ot0 f0 @|StartSubIcon #fIcon
Ot0 f1 179 307 26 26 14 0 #rect
Ot0 f1 @|EndSubIcon #fIcon
Ot0 f3 dialogId ch.ivy.addon.portal.generic.PortalCases #txt
Ot0 f3 startMethod useView(CaseView) #txt
Ot0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView caseView> param;' #txt
Ot0 f3 requestMappingAction 'param.caseView=in.view;
' #txt
Ot0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalCasesData out;
' #txt
Ot0 f3 responseMappingAction 'out=in;
' #txt
Ot0 f3 174 196 36 24 20 -2 #rect
Ot0 f3 @|UserDialogIcon #fIcon
Ot0 f4 expr out #txt
Ot0 f4 192 109 192 196 #arcP
Ot0 f2 expr out #txt
Ot0 f2 192 220 192 307 #arcP
>Proto Ot0 .type ch.ivy.addon.portal.generic.OpenPortalCasesData #txt
>Proto Ot0 .processKind CALLABLE_SUB #txt
>Proto Ot0 0 0 32 24 18 0 #rect
>Proto Ot0 @|BIcon #fIcon
Ot0 f0 mainOut f4 tail #connect
Ot0 f4 head f3 mainIn #connect
Ot0 f3 mainOut f2 tail #connect
Ot0 f2 head f1 mainIn #connect
