[Ivy]
160FD01492D362BE 3.26 #module
>Proto >Proto Collection #zClass
Cs0 CaseWidget Big #zClass
Cs0 B #cInfo
Cs0 #process
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @StartRequest f0 '' #zField
Cs0 @EndTask f1 '' #zField
Cs0 @RichDialog f6 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @GridStep f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @StartRequest f7 '' #zField
Cs0 @EndTask f8 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @GridStep f10 '' #zField
Cs0 @RichDialog f9 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f13 '' #zField
>Proto Cs0 Cs0 CaseWidget #zField
Cs0 f0 outLink showAdditionalCaseDetails.ivp #txt
Cs0 f0 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f0 inParamDecl '<java.lang.Long caseId> param;' #txt
Cs0 f0 inParamTable 'out.caseId=param.caseId;
' #txt
Cs0 f0 actionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f0 guid 160FD0149322CE4E #txt
Cs0 f0 requestEnabled true #txt
Cs0 f0 triggerEnabled false #txt
Cs0 f0 callSignature showAdditionalCaseDetails(Long) #txt
Cs0 f0 persist false #txt
Cs0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Cs0 f0 caseData businessCase.attach=true #txt
Cs0 f0 showInStartList 0 #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showAdditionalCaseDetails.ivp</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 @C|.responsibility Everybody #txt
Cs0 f0 81 49 30 30 -85 17 #rect
Cs0 f0 @|StartRequestIcon #fIcon
Cs0 f1 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f1 673 49 30 30 0 15 #rect
Cs0 f1 @|EndIcon #fIcon
Cs0 f6 targetWindow NEW #txt
Cs0 f6 targetDisplay TOP #txt
Cs0 f6 richDialogId ch.ivy.addon.portal.generic.AdditionalCaseDetails #txt
Cs0 f6 startMethod start(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f6 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f6 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Cs0 f6 requestMappingAction 'param.iCase=in.iCase;
' #txt
Cs0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f6 responseMappingAction 'out=in;
' #txt
Cs0 f6 isAsynch false #txt
Cs0 f6 isInnerRd false #txt
Cs0 f6 userContext '* ' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show Additional
Case Details</name>
        <nameStyle>5,7
23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 416 42 128 44 -42 -16 #rect
Cs0 f6 @|RichDialogIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 544 64 673 64 #arcP
Cs0 f2 actionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'out.iCase = ivy.wf.findCase(in.caseId);' #txt
Cs0 f2 security system #txt
Cs0 f2 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find case by id</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 200 42 112 44 -41 -8 #rect
Cs0 f2 @|StepIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 111 64 200 64 #arcP
Cs0 f5 expr out #txt
Cs0 f5 312 64 416 64 #arcP
Cs0 f7 outLink showCaseItemDetails.ivp #txt
Cs0 f7 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f7 inParamDecl '<java.lang.Long caseId> param;' #txt
Cs0 f7 inParamTable 'out.caseId=param.caseId;
' #txt
Cs0 f7 actionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f7 guid 16B9DC623746E769 #txt
Cs0 f7 requestEnabled true #txt
Cs0 f7 triggerEnabled false #txt
Cs0 f7 callSignature showCaseItemDetails(Long) #txt
Cs0 f7 persist false #txt
Cs0 f7 caseData businessCase.attach=true #txt
Cs0 f7 showInStartList 1 #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showCaseItemDetails.ivp</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 @C|.responsibility Everybody #txt
Cs0 f7 81 241 30 30 -74 18 #rect
Cs0 f7 @|StartRequestIcon #fIcon
Cs0 f8 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f8 673 241 30 30 0 15 #rect
Cs0 f8 @|EndIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 111 256 208 256 #arcP
Cs0 f10 actionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f10 actionTable 'out=in;
' #txt
Cs0 f10 actionCode 'out.iCase = ivy.wf.findCase(in.caseId);

ivy.log.info(out.iCase.getName());' #txt
Cs0 f10 security system #txt
Cs0 f10 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find case by id</name>
    </language>
</elementInfo>
' #txt
Cs0 f10 208 234 112 44 -41 -8 #rect
Cs0 f10 @|StepIcon #fIcon
Cs0 f9 richDialogId ch.ivy.addon.portal.generic.CaseItemDetail #txt
Cs0 f9 startMethod useICaseToViewDetail(ch.ivyteam.ivy.workflow.ICase,String) #txt
Cs0 f9 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f9 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase iCase,String menuState> param;' #txt
Cs0 f9 requestMappingAction 'param.iCase=in.iCase;
' #txt
Cs0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f9 responseMappingAction 'out=in;
' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show Case detail</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 440 234 112 44 -48 -8 #rect
Cs0 f9 @|RichDialogIcon #fIcon
Cs0 f11 expr out #txt
Cs0 f11 320 256 440 256 #arcP
Cs0 f13 expr out #txt
Cs0 f13 552 256 673 256 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseWidgetData #txt
>Proto Cs0 .processKind NORMAL #txt
>Proto Cs0 0 0 32 24 18 0 #rect
>Proto Cs0 @|BIcon #fIcon
Cs0 f6 mainOut f4 tail #connect
Cs0 f4 head f1 mainIn #connect
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f2 mainIn #connect
Cs0 f2 mainOut f5 tail #connect
Cs0 f5 head f6 mainIn #connect
Cs0 f7 mainOut f12 tail #connect
Cs0 f12 head f10 mainIn #connect
Cs0 f10 mainOut f11 tail #connect
Cs0 f11 head f9 mainIn #connect
Cs0 f9 mainOut f13 tail #connect
Cs0 f13 head f8 mainIn #connect
