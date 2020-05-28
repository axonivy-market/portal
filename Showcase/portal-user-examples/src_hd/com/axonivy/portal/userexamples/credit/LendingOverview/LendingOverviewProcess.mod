[Ivy]
172598595FEC9964 7.5.0 #module
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
Ls0 f6 actionCode 'import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import ch.ivy.addon.portalkit.bo.CaseMapDetail;
import ch.ivyteam.ivy.casemap.runtime.model.IStage;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;



in.caseMapDetail = new CaseMapDetail();
in.caseMapDetail.setStages(Arrays.asList("Identification", "Credit rating", "Approval"/*, "TEST"*/));

Map stageMap = new HashMap();
stageMap.put("Identification", Arrays.asList("Collect personal data", "Verify personal data"));
stageMap.put("Credit rating", Arrays.asList("Check company register", "Internal solvency check", "External solvency service"));
stageMap.put("Approval", Arrays.asList("Approve level 1", "Approve level 2", "Contract creation"));
//stageMap.put("TEST", Arrays.asList("Nam chu", "Nam mai", "Nam le"));
in.caseMapDetail.setStageDetail(stageMap);


ICaseMap caseMap = ivy.casemap.find().byPath("CaseMap/Lending");
if (caseMap != null){
	ivy.log.error("{0}",caseMap.getStages().size());
	Map iconMap = new HashMap();
	for (IStage stage : caseMap.getStages()){
		ivy.log.error("stage is {0} and icon is {1}",stage.getName(), stage.getIcon().getCssClass());
		iconMap.put(stage.getName(), stage.getIcon().getCssClass());
		
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
