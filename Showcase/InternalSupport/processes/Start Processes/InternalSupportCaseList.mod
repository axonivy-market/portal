[Ivy]
16381D6D0D2BA002 3.23 #module
>Proto >Proto Collection #zClass
It0 InternalSupportCaseList Big #zClass
It0 B #cInfo
It0 #process
It0 @TextInP .resExport .resExport #zField
It0 @TextInP .type .type #zField
It0 @TextInP .processKind .processKind #zField
It0 @AnnotationInP-0n ai ai #zField
It0 @MessageFlowInP-0n messageIn messageIn #zField
It0 @MessageFlowOutP-0n messageOut messageOut #zField
It0 @TextInP .xml .xml #zField
It0 @TextInP .responsibility .responsibility #zField
It0 @StartRequest f0 '' #zField
It0 @EndTask f1 '' #zField
It0 @GridStep f24 '' #zField
It0 @CallSub f62 '' #zField
It0 @CallSub f56 '' #zField
It0 @PushWFArc f52 '' #zField
It0 @PushWFArc f34 '' #zField
It0 @PushWFArc f2 '' #zField
It0 @GridStep f4 '' #zField
It0 @PushWFArc f5 '' #zField
It0 @PushWFArc f3 '' #zField
>Proto It0 It0 InternalSupportCaseList #zField
It0 f0 outLink start.ivp #txt
It0 f0 type internaltest.InternalSupportCaseListData #txt
It0 f0 inParamDecl '<> param;' #txt
It0 f0 actionDecl 'internaltest.InternalSupportCaseListData out;
' #txt
It0 f0 guid 16381D6D0D674614 #txt
It0 f0 requestEnabled true #txt
It0 f0 triggerEnabled false #txt
It0 f0 callSignature start() #txt
It0 f0 persist false #txt
It0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
It0 f0 caseData businessCase.attach=true #txt
It0 f0 showInStartList 1 #txt
It0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
It0 f0 @C|.responsibility Everybody #txt
It0 f0 81 49 30 30 -21 17 #rect
It0 f0 @|StartRequestIcon #fIcon
It0 f1 type internaltest.InternalSupportCaseListData #txt
It0 f1 865 49 30 30 0 15 #rect
It0 f1 @|EndIcon #fIcon
It0 f24 actionDecl 'internaltest.InternalSupportCaseListData out;
' #txt
It0 f24 actionTable 'out=in;
' #txt
It0 f24 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.CaseView;


in.caseDataModel.setIgnoreInvolvedUser(in.hasReadAllCasesPermission);

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	in.caseDataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	in.caseDataModel.setInvolvedApplications(applicationName);
}

in.caseView = CaseView.create().dataModel(in.caseDataModel).buildNewView();' #txt
It0 f24 type internaltest.InternalSupportCaseListData #txt
It0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, case view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
It0 f24 502 52 36 24 -66 -45 #rect
It0 f24 @|StepIcon #fIcon
It0 f62 type internaltest.InternalSupportCaseListData #txt
It0 f62 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
It0 f62 doCall true #txt
It0 f62 requestActionDecl '<> param;
' #txt
It0 f62 responseActionDecl 'internaltest.InternalSupportCaseListData out;
' #txt
It0 f62 responseMappingAction 'out=in;
out.caseDataModel=result.caseDataModel;
' #txt
It0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize case 
data model</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
It0 f62 328 42 112 44 -38 -20 #rect
It0 f62 @|CallSubIcon #fIcon
It0 f56 type internaltest.InternalSupportCaseListData #txt
It0 f56 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
It0 f56 doCall true #txt
It0 f56 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
It0 f56 requestMappingAction 'param.view=in.caseView;
' #txt
It0 f56 responseActionDecl 'internaltest.InternalSupportCaseListData out;
' #txt
It0 f56 responseMappingAction 'out=in;
' #txt
It0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
It0 f56 672 40 112 48 -49 -12 #rect
It0 f56 @|CallSubIcon #fIcon
It0 f52 expr out #txt
It0 f52 440 64 502 64 #arcP
It0 f34 expr out #txt
It0 f34 538 64 672 64 #arcP
It0 f2 expr out #txt
It0 f2 784 64 865 64 #arcP
It0 f4 actionDecl 'internaltest.InternalSupportCaseListData out;
' #txt
It0 f4 actionTable 'out=in;
' #txt
It0 f4 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();' #txt
It0 f4 security system #txt
It0 f4 type internaltest.InternalSupportCaseListData #txt
It0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Permission check</name>
    </language>
</elementInfo>
' #txt
It0 f4 168 42 112 44 -49 -8 #rect
It0 f4 @|StepIcon #fIcon
It0 f5 expr out #txt
It0 f5 111 64 168 64 #arcP
It0 f3 expr out #txt
It0 f3 280 64 328 64 #arcP
>Proto It0 .type internaltest.InternalSupportCaseListData #txt
>Proto It0 .processKind NORMAL #txt
>Proto It0 0 0 32 24 18 0 #rect
>Proto It0 @|BIcon #fIcon
It0 f24 mainOut f34 tail #connect
It0 f34 head f56 mainIn #connect
It0 f62 mainOut f52 tail #connect
It0 f52 head f24 mainIn #connect
It0 f56 mainOut f2 tail #connect
It0 f2 head f1 mainIn #connect
It0 f0 mainOut f5 tail #connect
It0 f5 head f4 mainIn #connect
It0 f4 mainOut f3 tail #connect
It0 f3 head f62 mainIn #connect
