[Ivy]
160FD01492D362BE 3.20 #module
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
Cs0 @GridStep f212 '' #zField
Cs0 @CallSub f224 '' #zField
Cs0 @PushWFArc f225 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
>Proto Cs0 Cs0 CaseWidget #zField
Cs0 f0 outLink showAdditionalCaseDetails.ivp #txt
Cs0 f0 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f0 inParamDecl '<java.lang.Long serverId,java.lang.Long caseId> param;' #txt
Cs0 f0 inParamTable 'out.caseId=param.caseId;
out.serverId=param.serverId;
' #txt
Cs0 f0 actionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f0 guid 160FD0149322CE4E #txt
Cs0 f0 requestEnabled true #txt
Cs0 f0 triggerEnabled false #txt
Cs0 f0 callSignature showAdditionalCaseDetails(Long,Long) #txt
Cs0 f0 persist false #txt
Cs0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Cs0 f0 caseData businessCase.attach=true #txt
Cs0 f0 showInStartList 0 #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showAdditionalCaseDetails.ivp</name>
        <nameStyle>29,5,7
</nameStyle>
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
Cs0 f6 startMethod start(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f6 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f6 requestActionDecl '<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param;' #txt
Cs0 f6 requestMappingAction 'param.remoteCase=in.remoteCase;
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
Cs0 f6 496 42 128 44 -42 -16 #rect
Cs0 f6 @|RichDialogIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 624 64 673 64 #arcP
Cs0 f212 actionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f212 actionTable 'out=in;
' #txt
Cs0 f212 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.server = serverService.findById(in.serverId);' #txt
Cs0 f212 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f212 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find server</name>
    </language>
</elementInfo>
' #txt
Cs0 f212 232 52 40 24 -32 19 #rect
Cs0 f212 @|StepIcon #fIcon
Cs0 f224 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Cs0 f224 processCall MultiPortal/CaseService:findCase(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f224 doCall true #txt
Cs0 f224 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f224 requestMappingAction 'param.server=in.server;
param.caseId=in.caseId;
' #txt
Cs0 f224 responseActionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Cs0 f224 responseMappingAction 'out=in;
out.remoteCase=result.remoteCase;
' #txt
Cs0 f224 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Case</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f224 392 52 40 24 -26 21 #rect
Cs0 f224 @|CallSubIcon #fIcon
Cs0 f225 expr out #txt
Cs0 f225 272 64 392 64 #arcP
Cs0 f2 expr out #txt
Cs0 f2 432 64 496 64 #arcP
Cs0 f3 expr out #txt
Cs0 f3 111 64 232 64 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseWidgetData #txt
>Proto Cs0 .processKind NORMAL #txt
>Proto Cs0 0 0 32 24 18 0 #rect
>Proto Cs0 @|BIcon #fIcon
Cs0 f6 mainOut f4 tail #connect
Cs0 f4 head f1 mainIn #connect
Cs0 f212 mainOut f225 tail #connect
Cs0 f225 head f224 mainIn #connect
Cs0 f224 mainOut f2 tail #connect
Cs0 f2 head f6 mainIn #connect
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f212 mainIn #connect
