[Ivy]
164120111758D0E6 3.23 #module
>Proto >Proto Collection #zClass
Pe0 PortalCase Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @TextInP .resExport .resExport #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @MessageFlowInP-0n messageIn messageIn #zField
Pe0 @MessageFlowOutP-0n messageOut messageOut #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartRequest f0 '' #zField
Pe0 @EndTask f1 '' #zField
Pe0 @GridStep f24 '' #zField
Pe0 @CallSub f62 '' #zField
Pe0 @PushWFArc f52 '' #zField
Pe0 @CallSub f56 '' #zField
Pe0 @PushWFArc f2 '' #zField
Pe0 @PushWFArc f4 '' #zField
Pe0 @PushWFArc f7 '' #zField
>Proto Pe0 Pe0 PortalCase #zField
Pe0 f0 outLink start.ivp #txt
Pe0 f0 type ch.ivy.addon.portal.generic.PortalCaseData #txt
Pe0 f0 inParamDecl '<> param;' #txt
Pe0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseData out;
' #txt
Pe0 f0 guid 16412011179D3EF7 #txt
Pe0 f0 requestEnabled true #txt
Pe0 f0 triggerEnabled false #txt
Pe0 f0 callSignature start() #txt
Pe0 f0 persist false #txt
Pe0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pe0 f0 caseData businessCase.attach=true #txt
Pe0 f0 showInStartList 0 #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Pe0 f0 @C|.responsibility Everybody #txt
Pe0 f0 81 49 30 30 -21 17 #rect
Pe0 f0 @|StartRequestIcon #fIcon
Pe0 f1 type ch.ivy.addon.portal.generic.PortalCaseData #txt
Pe0 f1 657 49 30 30 0 15 #rect
Pe0 f1 @|EndIcon #fIcon
Pe0 f24 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseData out;
' #txt
Pe0 f24 actionTable 'out=in;
' #txt
Pe0 f24 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.CaseView;

boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
in.caseDataModel.setIgnoreInvolvedUser(hasReadAllCasesPermission);

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	in.caseDataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	in.caseDataModel.setInvolvedApplications(applicationName);
}

in.caseView = CaseView.create().dataModel(in.caseDataModel).buildNewView();' #txt
Pe0 f24 type ch.ivy.addon.portal.generic.PortalCaseData #txt
Pe0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, case view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f24 366 52 36 24 -63 17 #rect
Pe0 f24 @|StepIcon #fIcon
Pe0 f62 type ch.ivy.addon.portal.generic.PortalCaseData #txt
Pe0 f62 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Pe0 f62 doCall true #txt
Pe0 f62 requestActionDecl '<> param;
' #txt
Pe0 f62 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseData out;
' #txt
Pe0 f62 responseMappingAction 'out=in;
out.caseDataModel=result.caseDataModel;
' #txt
Pe0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize case 
data model</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f62 184 42 112 44 -38 -20 #rect
Pe0 f62 @|CallSubIcon #fIcon
Pe0 f52 expr out #txt
Pe0 f52 296 64 366 64 #arcP
Pe0 f56 type ch.ivy.addon.portal.generic.PortalCaseData #txt
Pe0 f56 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Pe0 f56 doCall true #txt
Pe0 f56 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Pe0 f56 requestMappingAction 'param.view=in.caseView;
' #txt
Pe0 f56 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseData out;
' #txt
Pe0 f56 responseMappingAction 'out=in;
' #txt
Pe0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f56 504 40 112 48 -49 -12 #rect
Pe0 f56 @|CallSubIcon #fIcon
Pe0 f2 expr out #txt
Pe0 f2 402 64 504 64 #arcP
Pe0 f4 expr out #txt
Pe0 f4 616 64 657 64 #arcP
Pe0 f7 expr out #txt
Pe0 f7 111 64 184 64 #arcP
>Proto Pe0 .type ch.ivy.addon.portal.generic.PortalCaseData #txt
>Proto Pe0 .processKind NORMAL #txt
>Proto Pe0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f62 mainOut f52 tail #connect
Pe0 f52 head f24 mainIn #connect
Pe0 f24 mainOut f2 tail #connect
Pe0 f2 head f56 mainIn #connect
Pe0 f56 mainOut f4 tail #connect
Pe0 f4 head f1 mainIn #connect
Pe0 f0 mainOut f7 tail #connect
Pe0 f7 head f62 mainIn #connect
