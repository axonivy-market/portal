[Ivy]
1511A66AF619A768 3.20 #module
>Proto >Proto Collection #zClass
Ca0 CleanData Big #zClass
Ca0 B #cInfo
Ca0 #process
Ca0 @TextInP .resExport .resExport #zField
Ca0 @TextInP .type .type #zField
Ca0 @TextInP .processKind .processKind #zField
Ca0 @AnnotationInP-0n ai ai #zField
Ca0 @MessageFlowInP-0n messageIn messageIn #zField
Ca0 @MessageFlowOutP-0n messageOut messageOut #zField
Ca0 @TextInP .xml .xml #zField
Ca0 @TextInP .responsibility .responsibility #zField
Ca0 @StartRequest f0 '' #zField
Ca0 @EndTask f1 '' #zField
Ca0 @GridStep f3 '' #zField
Ca0 @PushWFArc f4 '' #zField
Ca0 @PushWFArc f2 '' #zField
Ca0 @GridStep f5 '' #zField
Ca0 @EndTask f6 '' #zField
Ca0 @StartRequest f7 '' #zField
Ca0 @PushWFArc f8 '' #zField
Ca0 @PushWFArc f9 '' #zField
Ca0 @EndTask f59 '' #zField
Ca0 @GridStep f57 '' #zField
Ca0 @StartRequest f61 '' #zField
Ca0 @PushWFArc f60 '' #zField
Ca0 @PushWFArc f56 '' #zField
Ca0 @StartRequest f10 '' #zField
Ca0 @GridStep f11 '' #zField
Ca0 @EndTask f12 '' #zField
Ca0 @PushWFArc f13 '' #zField
Ca0 @PushWFArc f14 '' #zField
Ca0 @StartRequest f15 '' #zField
Ca0 @GridStep f16 '' #zField
Ca0 @PushWFArc f18 '' #zField
Ca0 @EndTask f17 '' #zField
Ca0 @PushWFArc f19 '' #zField
Ca0 @StartRequest f20 '' #zField
Ca0 @EndTask f21 '' #zField
Ca0 @GridStep f23 '' #zField
Ca0 @PushWFArc f24 '' #zField
Ca0 @PushWFArc f22 '' #zField
>Proto Ca0 Ca0 CleanData #zField
Ca0 f0 outLink cleanCompletedCases.ivp #txt
Ca0 f0 type portalKit_test.Data #txt
Ca0 f0 inParamDecl '<> param;' #txt
Ca0 f0 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f0 guid 1511A66AF64BC0C6 #txt
Ca0 f0 requestEnabled true #txt
Ca0 f0 triggerEnabled false #txt
Ca0 f0 callSignature cleanCompletedCases() #txt
Ca0 f0 persist false #txt
Ca0 f0 startName 'Clean completed cases' #txt
Ca0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ca0 f0 showInStartList 1 #txt
Ca0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cleanCompletedCases.ivp</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f0 @C|.responsibility Everybody #txt
Ca0 f0 51 83 26 26 -57 -34 #rect
Ca0 f0 @|StartRequestIcon #fIcon
Ca0 f1 type portalKit_test.Data #txt
Ca0 f1 51 275 26 26 14 0 #rect
Ca0 f1 @|EndIcon #fIcon
Ca0 f3 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f3 actionTable 'out=in;
' #txt
Ca0 f3 actionCode 'import ch.ivy.addon.portalkit.test.util.TaskUtils;
TaskUtils.deleteCompletedCases();' #txt
Ca0 f3 type portalKit_test.Data #txt
Ca0 f3 46 188 36 24 20 -2 #rect
Ca0 f3 @|StepIcon #fIcon
Ca0 f4 expr out #txt
Ca0 f4 64 109 64 188 #arcP
Ca0 f2 expr out #txt
Ca0 f2 64 212 64 275 #arcP
Ca0 f5 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f5 actionTable 'out=in;
' #txt
Ca0 f5 actionCode 'import ch.ivy.addon.portalkit.service.UserProcessService;
UserProcessService userProcess =new UserProcessService();
userProcess.deleteAll(userProcess.findAll());' #txt
Ca0 f5 security system #txt
Ca0 f5 type portalKit_test.Data #txt
Ca0 f5 222 188 36 24 20 -2 #rect
Ca0 f5 @|StepIcon #fIcon
Ca0 f6 type portalKit_test.Data #txt
Ca0 f6 227 275 26 26 14 0 #rect
Ca0 f6 @|EndIcon #fIcon
Ca0 f7 outLink CleanFavoriteProcess.ivp #txt
Ca0 f7 type portalKit_test.Data #txt
Ca0 f7 inParamDecl '<> param;' #txt
Ca0 f7 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f7 guid 1511A77910D26F49 #txt
Ca0 f7 requestEnabled true #txt
Ca0 f7 triggerEnabled false #txt
Ca0 f7 callSignature CleanFavoriteProcess() #txt
Ca0 f7 persist false #txt
Ca0 f7 startName '(For autotest) Clean all favorite processes' #txt
Ca0 f7 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ca0 f7 showInStartList 1 #txt
Ca0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CleanFavoriteProcess.ivp</name>
        <nameStyle>24,5,7
</nameStyle>
        <desc>Clean all favorite processes before run UI test</desc>
    </language>
</elementInfo>
' #txt
Ca0 f7 @C|.responsibility Everybody #txt
Ca0 f7 227 83 26 26 -70 -36 #rect
Ca0 f7 @|StartRequestIcon #fIcon
Ca0 f8 expr out #txt
Ca0 f8 240 109 240 188 #arcP
Ca0 f9 expr out #txt
Ca0 f9 240 212 240 275 #arcP
Ca0 f59 type portalKit_test.Data #txt
Ca0 f59 395 275 26 26 14 0 #rect
Ca0 f59 @|EndIcon #fIcon
Ca0 f57 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f57 actionTable 'out=in;
' #txt
Ca0 f57 actionCode 'import ch.ivy.addon.portalkit.test.util.BusinessDataUtils;
import ch.ivy.addon.portalkit.test.util.TaskConcurrencyUtils;
import ch.ivy.addon.portalkit.test.util.TaskUtils;

TaskConcurrencyUtils.setInProcess(true);
TaskUtils.destroyAllCase();
BusinessDataUtils.clearAllBusinessData();' #txt
Ca0 f57 type portalKit_test.Data #txt
Ca0 f57 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear all tasks &amp; cases</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f57 390 188 36 24 20 -2 #rect
Ca0 f57 @|StepIcon #fIcon
Ca0 f61 outLink cleanAllCasesByDestroying.ivp #txt
Ca0 f61 type portalKit_test.Data #txt
Ca0 f61 inParamDecl '<> param;' #txt
Ca0 f61 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f61 guid 1511A783AA4A9FBC #txt
Ca0 f61 requestEnabled true #txt
Ca0 f61 triggerEnabled false #txt
Ca0 f61 callSignature cleanAllCasesByDestroying() #txt
Ca0 f61 persist false #txt
Ca0 f61 startName 'Clean all cases by destroying and clear all business data' #txt
Ca0 f61 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ca0 f61 caseData businessCase.attach=false #txt
Ca0 f61 showInStartList 1 #txt
Ca0 f61 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cleanAllCasesByDestroying.ivp</name>
        <nameStyle>29,5,7
</nameStyle>
        <desc>Detroy all tasks &amp; cases in system &amp; clean all business data</desc>
    </language>
</elementInfo>
' #txt
Ca0 f61 @C|.responsibility Everybody #txt
Ca0 f61 395 83 26 26 -58 -39 #rect
Ca0 f61 @|StartRequestIcon #fIcon
Ca0 f60 expr out #txt
Ca0 f60 408 212 408 275 #arcP
Ca0 f56 expr out #txt
Ca0 f56 408 109 408 188 #arcP
Ca0 f10 outLink cleanAbsences.ivp #txt
Ca0 f10 type portalKit_test.Data #txt
Ca0 f10 inParamDecl '<> param;' #txt
Ca0 f10 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f10 guid 15215DD536E39BDA #txt
Ca0 f10 requestEnabled true #txt
Ca0 f10 triggerEnabled false #txt
Ca0 f10 callSignature cleanAbsences() #txt
Ca0 f10 persist false #txt
Ca0 f10 startName '(For autotest) Clean absences' #txt
Ca0 f10 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ca0 f10 showInStartList 1 #txt
Ca0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cleanAbsences.ivp</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f10 @C|.responsibility Everybody #txt
Ca0 f10 587 83 26 26 -57 -34 #rect
Ca0 f10 @|StartRequestIcon #fIcon
Ca0 f11 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f11 actionTable 'out=in;
' #txt
Ca0 f11 actionCode 'import ch.ivy.addon.portalkit.test.util.AbsenceUtils;
AbsenceUtils.cleanAllAbsences();' #txt
Ca0 f11 security system #txt
Ca0 f11 type portalKit_test.Data #txt
Ca0 f11 582 188 36 24 20 -2 #rect
Ca0 f11 @|StepIcon #fIcon
Ca0 f12 type portalKit_test.Data #txt
Ca0 f12 587 275 26 26 14 0 #rect
Ca0 f12 @|EndIcon #fIcon
Ca0 f13 expr out #txt
Ca0 f13 600 109 600 188 #arcP
Ca0 f14 expr out #txt
Ca0 f14 600 212 600 275 #arcP
Ca0 f15 outLink cleanupCases.ivp #txt
Ca0 f15 type portalKit_test.Data #txt
Ca0 f15 inParamDecl '<> param;' #txt
Ca0 f15 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f15 guid 15234E4AE8E69E5B #txt
Ca0 f15 requestEnabled true #txt
Ca0 f15 triggerEnabled false #txt
Ca0 f15 callSignature cleanupCases() #txt
Ca0 f15 persist false #txt
Ca0 f15 startName '(For autotest) Clean up all cases and clear all business data' #txt
Ca0 f15 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ca0 f15 caseData businessCase.attach=false #txt
Ca0 f15 showInStartList 1 #txt
Ca0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cleanupCases.ivp</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f15 @C|.responsibility Everybody #txt
Ca0 f15 723 83 26 26 -43 -32 #rect
Ca0 f15 @|StartRequestIcon #fIcon
Ca0 f16 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f16 actionTable 'out=in;
' #txt
Ca0 f16 actionCode 'import ch.ivy.addon.portalkit.test.util.BusinessDataUtils;
import ch.ivy.addon.portalkit.test.util.CaseUtils;
CaseUtils.deleteAllCases();
BusinessDataUtils.clearAllBusinessData();' #txt
Ca0 f16 security system #txt
Ca0 f16 type portalKit_test.Data #txt
Ca0 f16 718 180 36 24 20 -2 #rect
Ca0 f16 @|StepIcon #fIcon
Ca0 f18 expr out #txt
Ca0 f18 736 109 736 180 #arcP
Ca0 f17 type portalKit_test.Data #txt
Ca0 f17 723 275 26 26 14 0 #rect
Ca0 f17 @|EndIcon #fIcon
Ca0 f19 expr out #txt
Ca0 f19 736 204 736 275 #arcP
Ca0 f20 outLink cleanupGlobalVars.ivp #txt
Ca0 f20 type portalKit_test.Data #txt
Ca0 f20 inParamDecl '<> param;' #txt
Ca0 f20 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f20 guid 15F9B59618135CB9 #txt
Ca0 f20 requestEnabled true #txt
Ca0 f20 triggerEnabled false #txt
Ca0 f20 callSignature cleanupGlobalVars() #txt
Ca0 f20 persist false #txt
Ca0 f20 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ca0 f20 caseData businessCase.attach=true #txt
Ca0 f20 showInStartList 1 #txt
Ca0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cleanupGlobalVars</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ca0 f20 @C|.responsibility Everybody #txt
Ca0 f20 849 81 30 30 -57 -32 #rect
Ca0 f20 @|StartRequestIcon #fIcon
Ca0 f21 type portalKit_test.Data #txt
Ca0 f21 849 273 30 30 0 15 #rect
Ca0 f21 @|EndIcon #fIcon
Ca0 f23 actionDecl 'portalKit_test.Data out;
' #txt
Ca0 f23 actionTable 'out=in;
' #txt
Ca0 f23 actionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;
GlobalSettingService globalSettingService = new GlobalSettingService();
globalSettingService.deleteAll(globalSettingService.findAll());' #txt
Ca0 f23 security system #txt
Ca0 f23 type portalKit_test.Data #txt
Ca0 f23 808 170 112 44 0 -8 #rect
Ca0 f23 @|StepIcon #fIcon
Ca0 f24 expr out #txt
Ca0 f24 864 111 864 170 #arcP
Ca0 f22 expr out #txt
Ca0 f22 864 214 864 273 #arcP
>Proto Ca0 .type portalKit_test.Data #txt
>Proto Ca0 .processKind NORMAL #txt
>Proto Ca0 0 0 32 24 18 0 #rect
>Proto Ca0 @|BIcon #fIcon
Ca0 f0 mainOut f4 tail #connect
Ca0 f4 head f3 mainIn #connect
Ca0 f3 mainOut f2 tail #connect
Ca0 f2 head f1 mainIn #connect
Ca0 f7 mainOut f8 tail #connect
Ca0 f8 head f5 mainIn #connect
Ca0 f5 mainOut f9 tail #connect
Ca0 f9 head f6 mainIn #connect
Ca0 f57 mainOut f60 tail #connect
Ca0 f60 head f59 mainIn #connect
Ca0 f61 mainOut f56 tail #connect
Ca0 f56 head f57 mainIn #connect
Ca0 f10 mainOut f13 tail #connect
Ca0 f13 head f11 mainIn #connect
Ca0 f11 mainOut f14 tail #connect
Ca0 f14 head f12 mainIn #connect
Ca0 f15 mainOut f18 tail #connect
Ca0 f18 head f16 mainIn #connect
Ca0 f16 mainOut f19 tail #connect
Ca0 f19 head f17 mainIn #connect
Ca0 f20 mainOut f24 tail #connect
Ca0 f24 head f23 mainIn #connect
Ca0 f23 mainOut f22 tail #connect
Ca0 f22 head f21 mainIn #connect
