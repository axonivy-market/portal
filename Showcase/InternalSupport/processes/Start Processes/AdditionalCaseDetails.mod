[Ivy]
16102669E18BD8F5 3.20 #module
>Proto >Proto Collection #zClass
As0 AdditionalCaseDetails Big #zClass
As0 B #cInfo
As0 #process
As0 @TextInP .resExport .resExport #zField
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @StartRequest f0 '' #zField
As0 @EndTask f1 '' #zField
As0 @RichDialog f6 '' #zField
As0 @PushWFArc f4 '' #zField
As0 @GridStep f212 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @PushWFArc f225 '' #zField
As0 @CallSub f224 '' #zField
As0 @PushWFArc f3 '' #zField
>Proto As0 As0 AdditionalCaseDetails #zField
As0 f0 outLink showAdditionalCaseDetails.ivp #txt
As0 f0 type internaltest.AdditionalCaseDetailsData #txt
As0 f0 inParamDecl '<java.lang.Long serverId,java.lang.Long caseId> param;' #txt
As0 f0 inParamTable 'out.remoteCaseId=param.caseId;
out.serverId=param.serverId;
' #txt
As0 f0 actionDecl 'internaltest.AdditionalCaseDetailsData out;
' #txt
As0 f0 guid 1610267E5E119582 #txt
As0 f0 requestEnabled true #txt
As0 f0 triggerEnabled false #txt
As0 f0 callSignature showAdditionalCaseDetails(Long,Long) #txt
As0 f0 persist false #txt
As0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
As0 f0 caseData businessCase.attach=true #txt
As0 f0 showInStartList 0 #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showAdditionalCaseDetails.ivp</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f0 @C|.responsibility Everybody #txt
As0 f0 98 65 30 30 -85 17 #rect
As0 f0 @|StartRequestIcon #fIcon
As0 f1 type internaltest.AdditionalCaseDetailsData #txt
As0 f1 609 65 30 30 0 15 #rect
As0 f1 @|EndIcon #fIcon
As0 f6 targetWindow NEW #txt
As0 f6 targetDisplay TOP #txt
As0 f6 richDialogId internalPortal.casedetails.AdditionalCaseDetailsCustomization #txt
As0 f6 startMethod start(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
As0 f6 type internaltest.AdditionalCaseDetailsData #txt
As0 f6 requestActionDecl '<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param;' #txt
As0 f6 requestMappingAction 'param.remoteCase=in.remoteCase;
' #txt
As0 f6 responseActionDecl 'internaltest.AdditionalCaseDetailsData out;
' #txt
As0 f6 responseMappingAction 'out=in;
' #txt
As0 f6 isAsynch false #txt
As0 f6 isInnerRd false #txt
As0 f6 userContext '* ' #txt
As0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Customization
Additional Case Details Page</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f6 384 58 192 44 -76 -16 #rect
As0 f6 @|RichDialogIcon #fIcon
As0 f4 expr out #txt
As0 f4 576 80 609 80 #arcP
As0 f212 actionDecl 'internaltest.AdditionalCaseDetailsData out;
' #txt
As0 f212 actionTable 'out=in;
' #txt
As0 f212 actionCode 'import ch.ivy.addon.portalkit.service.ServerService;

ServerService serverService = new ServerService();
in.server = serverService.findById(in.serverId);' #txt
As0 f212 type internaltest.AdditionalCaseDetailsData #txt
As0 f212 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find server</name>
    </language>
</elementInfo>
' #txt
As0 f212 220 68 40 24 -32 19 #rect
As0 f212 @|StepIcon #fIcon
As0 f2 expr out #txt
As0 f2 128 80 220 80 #arcP
As0 f225 expr out #txt
As0 f225 260 80 300 80 #arcP
As0 f224 type internaltest.AdditionalCaseDetailsData #txt
As0 f224 processCall MultiPortal/CaseService:findCase(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
As0 f224 doCall true #txt
As0 f224 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
As0 f224 requestMappingAction 'param.server=in.server;
param.caseId=in.remoteCaseId;
' #txt
As0 f224 responseActionDecl 'internaltest.AdditionalCaseDetailsData out;
' #txt
As0 f224 responseMappingAction 'out=in;
out.remoteCase=result.remoteCase;
' #txt
As0 f224 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Case</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f224 300 68 40 24 -26 21 #rect
As0 f224 @|CallSubIcon #fIcon
As0 f3 expr out #txt
As0 f3 340 80 384 80 #arcP
>Proto As0 .type internaltest.AdditionalCaseDetailsData #txt
>Proto As0 .processKind NORMAL #txt
>Proto As0 0 0 32 24 18 0 #rect
>Proto As0 @|BIcon #fIcon
As0 f6 mainOut f4 tail #connect
As0 f4 head f1 mainIn #connect
As0 f212 mainOut f225 tail #connect
As0 f225 head f224 mainIn #connect
As0 f0 mainOut f2 tail #connect
As0 f2 head f212 mainIn #connect
As0 f224 mainOut f3 tail #connect
As0 f3 head f6 mainIn #connect
