[Ivy]
[>Created: Mon Apr 03 14:28:33 CEST 2017]
158AD9D4C0D7D059 3.20 #module
>Proto >Proto Collection #zClass
Ds0 DEVCaseDetails Big #zClass
Ds0 B #cInfo
Ds0 #process
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @StartRequest f0 '' #zField
Ds0 @EndTask f1 '' #zField
Ds0 @RichDialog f3 '' #zField
Ds0 @PushWFArc f4 '' #zField
>Proto Ds0 Ds0 DEVCaseDetails #zField
Ds0 f0 outLink start.ivp #txt
Ds0 f0 type ch.ivy.addon.portal.generic.admin.CaseDetailData #txt
Ds0 f0 inParamDecl '<> param;' #txt
Ds0 f0 inParamTable 'out.caseId=8;
' #txt
Ds0 f0 actionDecl 'ch.ivy.addon.portal.generic.admin.CaseDetailData out;
' #txt
Ds0 f0 guid 158AD9D4C20AB382 #txt
Ds0 f0 requestEnabled true #txt
Ds0 f0 triggerEnabled false #txt
Ds0 f0 callSignature start() #txt
Ds0 f0 persist false #txt
Ds0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ds0 f0 showInStartList 1 #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 @C|.responsibility Everybody #txt
Ds0 f0 81 49 30 30 -21 17 #rect
Ds0 f0 @|StartRequestIcon #fIcon
Ds0 f1 type ch.ivy.addon.portal.generic.admin.CaseDetailData #txt
Ds0 f1 521 57 30 30 0 15 #rect
Ds0 f1 @|EndIcon #fIcon
Ds0 f3 targetWindow NEW:card: #txt
Ds0 f3 targetDisplay TOP #txt
Ds0 f3 richDialogId ch.ivy.addon.portal.generic.CaseInformation #txt
Ds0 f3 startMethod start(java.lang.Long) #txt
Ds0 f3 type ch.ivy.addon.portal.generic.admin.CaseDetailData #txt
Ds0 f3 requestActionDecl '<java.lang.Long caseId> param;' #txt
Ds0 f3 requestMappingAction 'param.caseId=in.caseId;
' #txt
Ds0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.admin.CaseDetailData out;
' #txt
Ds0 f3 responseMappingAction 'out=in;
' #txt
Ds0 f3 windowConfiguration '* ' #txt
Ds0 f3 isAsynch false #txt
Ds0 f3 isInnerRd false #txt
Ds0 f3 userContext '* ' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show case detail</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f3 208 44 128 40 -43 -8 #rect
Ds0 f3 @|RichDialogIcon #fIcon
Ds0 f4 expr out #txt
Ds0 f4 111 64 208 64 #arcP
>Proto Ds0 .type ch.ivy.addon.portal.generic.admin.CaseDetailData #txt
>Proto Ds0 .processKind NORMAL #txt
>Proto Ds0 0 0 32 24 18 0 #rect
>Proto Ds0 @|BIcon #fIcon
Ds0 f0 mainOut f4 tail #connect
Ds0 f4 head f3 mainIn #connect
