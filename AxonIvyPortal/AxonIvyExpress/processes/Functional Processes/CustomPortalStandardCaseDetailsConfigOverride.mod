[Ivy]
[>Created: Fri Mar 17 15:24:45 CET 2017]
15ADC7FD52F99E2E 3.18 #module
>Proto >Proto Collection #zClass
Cg0 CustomPortalStandardCaseDetailsConfig Big #zClass
Cg0 B #cInfo
Cg0 #process
Cg0 @TextInP .resExport .resExport #zField
Cg0 @TextInP .type .type #zField
Cg0 @TextInP .processKind .processKind #zField
Cg0 @AnnotationInP-0n ai ai #zField
Cg0 @MessageFlowInP-0n messageIn messageIn #zField
Cg0 @MessageFlowOutP-0n messageOut messageOut #zField
Cg0 @TextInP .xml .xml #zField
Cg0 @TextInP .responsibility .responsibility #zField
Cg0 @StartSub f0 '' #zField
Cg0 @EndSub f1 '' #zField
Cg0 @GridStep f5 '' #zField
Cg0 @PushWFArc f3 '' #zField
Cg0 @PushWFArc f2 '' #zField
Cg0 @InfoButton f11 '' #zField
>Proto Cg0 Cg0 CustomPortalStandardCaseDetailsConfig #zField
Cg0 f0 inParamDecl '<ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData portalCaseListData> param;' #txt
Cg0 f0 inParamTable 'out.portalCaseListData=param.portalCaseListData;
' #txt
Cg0 f0 outParamDecl '<ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData portalCaseListData> result;
' #txt
Cg0 f0 outParamTable 'result.portalCaseListData=in.portalCaseListData;
' #txt
Cg0 f0 actionDecl 'gawfs.CustomPortalStandardCaseDetailsConfigOverrideData out;
' #txt
Cg0 f0 callSignature call(ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData) #txt
Cg0 f0 type gawfs.CustomPortalStandardCaseDetailsConfigOverrideData #txt
Cg0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(PortalCaseListData)</name>
    </language>
</elementInfo>
' #txt
Cg0 f0 51 83 26 26 14 0 #rect
Cg0 f0 @|StartSubIcon #fIcon
Cg0 f1 type gawfs.CustomPortalStandardCaseDetailsConfigOverrideData #txt
Cg0 f1 507 83 26 26 14 0 #rect
Cg0 f1 @|EndSubIcon #fIcon
Cg0 f5 actionDecl 'gawfs.CustomPortalStandardCaseDetailsConfigOverrideData out;
' #txt
Cg0 f5 actionTable 'out=in;
' #txt
Cg0 f5 actionCode 'import ch.ivy.addon.portalkit.util.UrlHelper;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivy.addon.portalkit.util.CaseUtils;


//Set Standard CaseDetails Process Signature e.g. "ShowCaseDetail(Integer)" to be set if ICase.CaseDetailsProcess is empty
String caseDetailsProcessSignature = "gawfsCaseDetails(Integer)";


//-------------------------------------------------------------------------


//check is such a process is available the process with corresponding signature
if (ivy.wf.findProcessStartsBySignature(caseDetailsProcessSignature).size() > 0) {
	//get process start link
	String caseDetailRequestPath = UrlHelper.getFullLinkBySignature(caseDetailsProcessSignature);
	
	//set caseDetailsRequestPath for all processes with empty ICase.CaseDetailsProcess
	CaseUtils.setCaseDetailsProcessIfEmpty(in.portalCaseListData.userCasesTemp, caseDetailRequestPath);
	CaseUtils.setCaseDetailsProcessIfEmpty(in.portalCaseListData.adminCasesTemp, caseDetailRequestPath);

}else{
	//log error if process with corresponding signature not found
	ivy.log.error("No CaseDetails process found with the signatur <" + caseDetailsProcessSignature + ">!");
}

' #txt
Cg0 f5 security system #txt
Cg0 f5 type gawfs.CustomPortalStandardCaseDetailsConfigOverrideData #txt
Cg0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomCaseDetailsConfig</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f5 238 84 36 24 20 -2 #rect
Cg0 f5 @|StepIcon #fIcon
Cg0 f3 expr out #txt
Cg0 f3 77 96 238 96 #arcP
Cg0 f2 expr out #txt
Cg0 f2 274 96 507 96 #arcP
Cg0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Override this project in your application portal in order
to apply appplication specifiy configuration.

This Process sets a Standard CaseDetails Process 
for all processes where ICase.CaseDetailsProcess is empty
</name>
        <nameStyle>213,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f11 88 138 336 108 -165 -48 #rect
Cg0 f11 @|IBIcon #fIcon
Cg0 f11 -1|-1|-65536 #nodeStyle
>Proto Cg0 .type gawfs.CustomPortalStandardCaseDetailsConfigOverrideData #txt
>Proto Cg0 .processKind CALLABLE_SUB #txt
>Proto Cg0 0 0 32 24 18 0 #rect
>Proto Cg0 @|BIcon #fIcon
Cg0 f0 mainOut f3 tail #connect
Cg0 f3 head f5 mainIn #connect
Cg0 f5 mainOut f2 tail #connect
Cg0 f2 head f1 mainIn #connect
