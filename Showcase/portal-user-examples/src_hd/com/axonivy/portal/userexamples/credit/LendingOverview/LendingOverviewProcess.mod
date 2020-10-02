[Ivy]
172598595FEC9964 9.2.0 #module
>Proto >Proto Collection #zClass
Ls0 LendingOverviewProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @UdEvent f3 '' #zField
Ls0 @UdExitEnd f4 '' #zField
Ls0 @PushWFArc f5 '' #zField
Ls0 @GridStep f6 '' #zField
Ls0 @PushWFArc f7 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @UdMethod f8 '' #zField
Ls0 @UserDialog f9 '' #zField
Ls0 @PushWFArc f10 '' #zField
Ls0 @UdEvent f11 '' #zField
Ls0 @UserDialog f12 '' #zField
Ls0 @PushWFArc f13 '' #zField
>Proto Ls0 Ls0 LendingOverviewProcess #zField
Ls0 f0 guid 172598596081B8FA #txt
Ls0 f0 method start(ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Ls0 f0 inParameterDecl '<ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess> param;' #txt
Ls0 f0 inParameterMapAction 'out.userProcess=param.userProcess;
' #txt
Ls0 f0 outParameterDecl '<> result;' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(UserProcess)</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 83 51 26 26 -16 15 #rect
Ls0 f0 @|UdInitIcon #fIcon
Ls0 f1 395 51 26 26 0 12 #rect
Ls0 f1 @|UdProcessEndIcon #fIcon
Ls0 f3 guid 1725985961185C80 #txt
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ls0 f3 83 147 26 26 -15 15 #rect
Ls0 f3 @|UdEventIcon #fIcon
Ls0 f4 211 147 26 26 0 12 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f5 109 160 211 160 #arcP
Ls0 f6 actionTable 'out=in;
' #txt
Ls0 f6 actionCode 'import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import ch.ivy.addon.portalkit.bo.CaseMapDetail;
import ch.ivyteam.ivy.casemap.runtime.model.IStage;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;

in.caseMapDetail = new CaseMapDetail();
in.caseMapDetail.setName(in.userProcess.getProcessName());
in.caseMapDetail.setIcon(in.userProcess.getIcon());
in.caseMapDetail.setStartLink(in.userProcess.link);
in.caseMapDetail.setDescription(in.userProcess.getDescription());


in.caseMapDetail.setStages(Arrays.asList(ivy.cms.co("/Processes/CaseMap/Stage/identification"), 
ivy.cms.co("/Processes/CaseMap/Stage/creditRating"), ivy.cms.co("/Processes/CaseMap/Stage/approval")));

// STAGE
Map stageMap = new HashMap();
stageMap.put(ivy.cms.co("/Processes/CaseMap/Stage/identification"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/collectPersonalData"), ivy.cms.co("/Processes/CaseMap/verifyPersonalData")));
stageMap.put(ivy.cms.co("/Processes/CaseMap/Stage/creditRating"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/checkCompanyRegister"), ivy.cms.co("/Processes/CaseMap/internalSolvencyCheck")));
stageMap.put(ivy.cms.co("/Processes/CaseMap/Stage/approval"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/approveLevel1"), ivy.cms.co("/Processes/CaseMap/approveLevel2"), ivy.cms.co("/Processes/CaseMap/createContract")));

in.caseMapDetail.setStageDetail(stageMap);

// PROCCESS
Map processMap = new HashMap();
processMap.put(ivy.cms.co("/Processes/CaseMap/collectPersonalData"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/CollectPersonDataDescription/description1"), ivy.cms.co("/Processes/CaseMap/CollectPersonDataDescription/description2"), ivy.cms.co("/Processes/CaseMap/CollectPersonDataDescription/description3")));
processMap.put(ivy.cms.co("/Processes/CaseMap/verifyPersonalData"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/VerifyPersonalDataDescription/description1"), ivy.cms.co("/Processes/CaseMap/VerifyPersonalDataDescription/description2")));
processMap.put(ivy.cms.co("/Processes/CaseMap/checkCompanyRegister"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/CheckCompanyRegisterDescription/description1"), ivy.cms.co("/Processes/CaseMap/CheckCompanyRegisterDescription/description2")));
processMap.put(ivy.cms.co("/Processes/CaseMap/internalSolvencyCheck"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/InternalSolvencyCheckDescription/description1"), ivy.cms.co("/Processes/CaseMap/InternalSolvencyCheckDescription/description2")));	
processMap.put(ivy.cms.co("/Processes/CaseMap/externalSolvencyCheck"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/ExternalSolvencyServiceDescription/description1")));		
processMap.put(ivy.cms.co("/Processes/CaseMap/approveLevel1"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/ApprovalLevel12/description1"), ivy.cms.co("/Processes/CaseMap/ApprovalLevel12/description2"), ivy.cms.co("/Processes/CaseMap/ApprovalLevel12/description3")));	
processMap.put(ivy.cms.co("/Processes/CaseMap/approveLevel2"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/ApprovalLevel12/description1"), ivy.cms.co("/Processes/CaseMap/ApprovalLevel12/description2"), ivy.cms.co("/Processes/CaseMap/ApprovalLevel12/description3")));	
processMap.put(ivy.cms.co("/Processes/CaseMap/createContract"), 
	Arrays.asList(ivy.cms.co("/Processes/CaseMap/ContractCreation/description1"), ivy.cms.co("/Processes/CaseMap/ContractCreation/description2")));	
	
in.caseMapDetail.setProcessDetail(processMap);

// SIDE STEP
Map sideStepMap = new HashMap();
sideStepMap.put(ivy.cms.co("/Processes/CaseMap/Stage/creditRating"), Arrays.asList(ivy.cms.co("/Processes/CaseMap/externalSolvencyCheck")));

in.caseMapDetail.setSideStepDetail(sideStepMap);

// PRE CONDITION
Map preConditionMap = new HashMap();
preConditionMap.put(ivy.cms.co("/Processes/CaseMap/approveLevel1"), true);
preConditionMap.put(ivy.cms.co("/Processes/CaseMap/approveLevel2"), true);
preConditionMap.put(ivy.cms.co("/Processes/CaseMap/createContract"), true);

in.caseMapDetail.setPreConditionDetail(preConditionMap);	

ICaseMap caseMap = ivy.casemap.find().byPath("CaseMap/Lending");
if (caseMap != null){	
	Map iconMap = new HashMap();
	for (IStage stage : caseMap.getStages()){		
		if (ivy.cms.coLocale("/Processes/CaseMap/Stage/identification", Locale.ENGLISH).equalsIgnoreCase(stage.getName())){
			iconMap.put(ivy.cms.co("/Processes/CaseMap/Stage/identification"), stage.getIcon().getCssClass());		
		}
		if (ivy.cms.coLocale("/Processes/CaseMap/Stage/creditRating", Locale.ENGLISH).equalsIgnoreCase(stage.getName())){
			iconMap.put(ivy.cms.co("/Processes/CaseMap/Stage/creditRating"), stage.getIcon().getCssClass());				
		}
		if (ivy.cms.coLocale("/Processes/CaseMap/Stage/approval", Locale.ENGLISH).equalsIgnoreCase(stage.getName())){
			iconMap.put(ivy.cms.co("/Processes/CaseMap/Stage/approval"), stage.getIcon().getCssClass());				
		}
		
	}
	in.caseMapDetail.setStageIcon(iconMap);
}





' #txt
Ls0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data</name>
    </language>
</elementInfo>
' #txt
Ls0 f6 224 42 112 44 -21 -8 #rect
Ls0 f6 @|StepIcon #fIcon
Ls0 f7 109 64 224 64 #arcP
Ls0 f2 336 64 395 64 #arcP
Ls0 f8 guid 1725FD5F6C762123 #txt
Ls0 f8 method navigateToDetail(Integer) #txt
Ls0 f8 inParameterDecl '<Integer index> param;' #txt
Ls0 f8 inParameterMapAction 'out.stageIndex=param.index;
' #txt
Ls0 f8 outParameterDecl '<> result;' #txt
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToDetail(Integer)</name>
    </language>
</elementInfo>
' #txt
Ls0 f8 83 243 26 26 -25 15 #rect
Ls0 f8 @|UdMethodIcon #fIcon
Ls0 f9 dialogId com.axonivy.portal.userexamples.credit.LendingDetail #txt
Ls0 f9 startMethod start(ch.ivy.addon.portalkit.bo.CaseMapDetail,Integer) #txt
Ls0 f9 requestActionDecl '<ch.ivy.addon.portalkit.bo.CaseMapDetail caseMapDetail,Integer index> param;' #txt
Ls0 f9 requestMappingAction 'param.caseMapDetail=in.caseMapDetail;
param.index=in.stageIndex;
' #txt
Ls0 f9 responseMappingAction 'out=in;
' #txt
Ls0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LendingDetail</name>
    </language>
</elementInfo>
' #txt
Ls0 f9 232 234 112 44 -38 -8 #rect
Ls0 f9 @|UserDialogIcon #fIcon
Ls0 f10 109 256 232 256 #arcP
Ls0 f11 guid 17298009F9A705DB #txt
Ls0 f11 actionTable 'out=in;
' #txt
Ls0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Ls0 f11 83 339 26 26 -14 15 #rect
Ls0 f11 @|UdEventIcon #fIcon
Ls0 f12 dialogId com.axonivy.portal.userexamples.ExampleHomePage #txt
Ls0 f12 startMethod start() #txt
Ls0 f12 requestActionDecl '<> param;' #txt
Ls0 f12 responseMappingAction 'out=in;
' #txt
Ls0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExampleHomePage</name>
    </language>
</elementInfo>
' #txt
Ls0 f12 224 330 128 44 -55 -8 #rect
Ls0 f12 @|UserDialogIcon #fIcon
Ls0 f13 109 352 224 352 #arcP
>Proto Ls0 .type com.axonivy.portal.userexamples.credit.LendingOverview.LendingOverviewData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f3 mainOut f5 tail #connect
Ls0 f5 head f4 mainIn #connect
Ls0 f0 mainOut f7 tail #connect
Ls0 f7 head f6 mainIn #connect
Ls0 f6 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f8 mainOut f10 tail #connect
Ls0 f10 head f9 mainIn #connect
Ls0 f11 mainOut f13 tail #connect
Ls0 f13 head f12 mainIn #connect
