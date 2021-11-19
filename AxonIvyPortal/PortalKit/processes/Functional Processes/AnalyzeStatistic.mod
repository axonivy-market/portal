[Ivy]
1602F513613E1225 9.3.1 #module
>Proto >Proto Collection #zClass
ac0 AnalyzeStatistic Big #zClass
ac0 B #cInfo
ac0 #process
ac0 @TextInP .type .type #zField
ac0 @TextInP .processKind .processKind #zField
ac0 @AnnotationInP-0n ai ai #zField
ac0 @MessageFlowInP-0n messageIn messageIn #zField
ac0 @MessageFlowOutP-0n messageOut messageOut #zField
ac0 @TextInP .xml .xml #zField
ac0 @TextInP .responsibility .responsibility #zField
ac0 @StartSub f0 '' #zField
ac0 @EndSub f1 '' #zField
ac0 @StartSub f3 '' #zField
ac0 @StartSub f4 '' #zField
ac0 @EndSub f5 '' #zField
ac0 @EndSub f6 '' #zField
ac0 @CallSub f11 '' #zField
ac0 @PushWFArc f2 '' #zField
ac0 @CallSub f15 '' #zField
ac0 @PushWFArc f7 '' #zField
ac0 @CallSub f19 '' #zField
ac0 @PushWFArc f8 '' #zField
ac0 @StartSub f27 '' #zField
ac0 @CallSub f28 '' #zField
ac0 @EndSub f30 '' #zField
ac0 @PushWFArc f33 '' #zField
ac0 @StartSub f36 '' #zField
ac0 @EndSub f37 '' #zField
ac0 @CallSub f39 '' #zField
ac0 @PushWFArc f44 '' #zField
ac0 @PushWFArc f22 '' #zField
ac0 @PushWFArc f24 '' #zField
ac0 @PushWFArc f26 '' #zField
ac0 @PushWFArc f35 '' #zField
ac0 @PushWFArc f43 '' #zField
ac0 @GridStep f23 '' #zField
ac0 @GridStep f31 '' #zField
ac0 @GridStep f25 '' #zField
ac0 @GridStep f21 '' #zField
ac0 @GridStep f41 '' #zField
ac0 @PushWFArc f9 '' #zField
ac0 @PushWFArc f10 '' #zField
ac0 @PushWFArc f12 '' #zField
ac0 @PushWFArc f13 '' #zField
ac0 @PushWFArc f14 '' #zField
ac0 @EndSub f38 '' #zField
ac0 @StartSub f40 '' #zField
ac0 @CallSub f51 '' #zField
ac0 @PushWFArc f54 '' #zField
ac0 @PushWFArc f16 '' #zField
>Proto ac0 ac0 AnalyzeStatistic #zField
ac0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
ac0 f0 inParamTable 'out.taskSearchCriteria.customTaskQuery=param.taskQuery;
' #txt
ac0 f0 outParamDecl '<ch.ivy.addon.portalkit.bo.PriorityStatistic result> result;' #txt
ac0 f0 outParamTable 'result.result=in.priorityStatisticResult;
' #txt
ac0 f0 callSignature analyzePriorityStatistic(ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
ac0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzePriorityStatistic(TaskQuery)</name>
    </language>
</elementInfo>
' #txt
ac0 f0 113 49 30 30 -94 17 #rect
ac0 f1 561 49 30 30 0 15 #rect
ac0 f3 inParamDecl '<ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
ac0 f3 inParamTable 'out.caseSearchCriteria.customCaseQuery=param.caseQuery;
' #txt
ac0 f3 outParamDecl '<ch.ivy.addon.portalkit.bo.CaseStateStatistic result> result;' #txt
ac0 f3 outParamTable 'result.result=in.caseStateStatisticResult;
' #txt
ac0 f3 callSignature analyzeCaseStateStatistic(ch.ivyteam.ivy.workflow.query.CaseQuery) #txt
ac0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeCaseStateStatistic(CaseQuery)</name>
    </language>
</elementInfo>
' #txt
ac0 f3 113 145 30 30 -106 17 #rect
ac0 f4 inParamDecl '<ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
ac0 f4 inParamTable 'out.taskSearchCriteria.customTaskQuery=param.taskQuery;
' #txt
ac0 f4 outParamDecl '<ch.ivy.addon.portalkit.bo.ExpiryStatistic result> result;' #txt
ac0 f4 outParamTable 'result.result=in.expiryStatisticResult;
' #txt
ac0 f4 callSignature analyzeExpiryStatistic(ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
ac0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeExpiryStatistic(TaskQuery)</name>
    </language>
</elementInfo>
' #txt
ac0 f4 113 241 30 30 -91 17 #rect
ac0 f5 561 145 30 30 0 15 #rect
ac0 f6 561 241 30 30 0 15 #rect
ac0 f11 processCall 'Ivy Data Processes/TaskService:analyzePriorityStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)' #txt
ac0 f11 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
ac0 f11 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
ac0 f11 responseActionDecl 'ch.ivy.add.portalkit.AnalyzeStatisticData out;
' #txt
ac0 f11 responseMappingAction 'out=in;
out.priorityStatisticResult=result.priorityStatistic;
' #txt
ac0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f11 384 42 112 44 -33 -8 #rect
ac0 f2 expr out #txt
ac0 f2 496 64 561 64 #arcP
ac0 f15 processCall 'Ivy Data Processes/CaseService:analyzeCaseStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
ac0 f15 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
ac0 f15 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
ac0 f15 responseActionDecl 'ch.ivy.add.portalkit.AnalyzeStatisticData out;
' #txt
ac0 f15 responseMappingAction 'out=in;
out.caseStateStatisticResult=result.caseStateStatistic;
' #txt
ac0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f15 384 138 112 44 -35 -8 #rect
ac0 f7 expr out #txt
ac0 f7 496 160 561 160 #arcP
ac0 f19 processCall 'Ivy Data Processes/TaskService:analyzeExpiryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)' #txt
ac0 f19 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
ac0 f19 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
ac0 f19 responseActionDecl 'ch.ivy.add.portalkit.AnalyzeStatisticData out;
' #txt
ac0 f19 responseMappingAction 'out=in;
out.expiryStatisticResult=result.expiryStatistic;
' #txt
ac0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f19 384 234 112 44 -33 -8 #rect
ac0 f8 expr out #txt
ac0 f8 496 256 561 256 #arcP
ac0 f27 inParamDecl '<ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
ac0 f27 inParamTable 'out.caseSearchCriteria.customCaseQuery=param.caseQuery;
' #txt
ac0 f27 outParamDecl '<ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic result> result;' #txt
ac0 f27 outParamTable 'result.result=in.elapsedTimeStatisticResult;
' #txt
ac0 f27 callSignature analyzeElapsedTimeStatistic(ch.ivyteam.ivy.workflow.query.CaseQuery) #txt
ac0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeElapsedTimeStatistic(CaseQuery)</name>
    </language>
</elementInfo>
' #txt
ac0 f27 113 337 30 30 -114 17 #rect
ac0 f28 processCall 'Ivy Data Processes/CaseService:analyzeElapsedTimeByCaseCategory(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
ac0 f28 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
ac0 f28 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
ac0 f28 responseActionDecl 'ch.ivy.add.portalkit.AnalyzeStatisticData out;
' #txt
ac0 f28 responseMappingAction 'out=in;
out.elapsedTimeStatisticResult=result.elapsedTimeStatistic;
' #txt
ac0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f28 386 330 112 44 -35 -8 #rect
ac0 f30 563 337 30 30 0 15 #rect
ac0 f33 expr out #txt
ac0 f33 498 352 563 352 #arcP
ac0 f36 inParamDecl '<ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
ac0 f36 inParamTable 'out.caseSearchCriteria.customCaseQuery=param.caseQuery;
' #txt
ac0 f36 outParamDecl '<ch.ivyteam.ivy.workflow.category.CategoryTree result> result;' #txt
ac0 f36 outParamTable 'result.result=in.caseCategoryTree;
' #txt
ac0 f36 callSignature findCaseCategories(ch.ivyteam.ivy.workflow.query.CaseQuery) #txt
ac0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCaseCategories(CaseQuery)</name>
    </language>
</elementInfo>
' #txt
ac0 f36 113 433 30 30 -90 17 #rect
ac0 f37 561 433 30 30 0 15 #rect
ac0 f39 processCall 'Ivy Data Processes/CaseService:findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria)' #txt
ac0 f39 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria caseCategorySearchCriteria> param;' #txt
ac0 f39 responseActionDecl 'ch.ivy.add.portalkit.AnalyzeStatisticData out;
' #txt
ac0 f39 responseMappingAction 'out=in;
out.caseCategoryTree=result.categoryTree;
' #txt
ac0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f39 384 426 112 44 -35 -8 #rect
ac0 f44 expr out #txt
ac0 f44 496 448 561 448 #arcP
ac0 f22 expr out #txt
ac0 f22 143 64 224 64 #arcP
ac0 f24 expr out #txt
ac0 f24 143 160 224 160 #arcP
ac0 f26 expr out #txt
ac0 f26 143 256 224 256 #arcP
ac0 f35 expr out #txt
ac0 f35 143 352 226 352 #arcP
ac0 f43 expr out #txt
ac0 f43 143 448 224 448 #arcP
ac0 f23 actionTable 'out=in;
' #txt
ac0 f23 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.caseSearchCriteria.businessCase = true;
in.caseSearchCriteria.adminQuery = PermissionUtils.checkReadAllCasesPermission();
in.caseSearchCriteria.sorted = false;' #txt
ac0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f23 224 138 112 44 -22 -8 #rect
ac0 f31 actionTable 'out=in;
' #txt
ac0 f31 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.caseSearchCriteria.businessCase = true;
in.caseSearchCriteria.adminQuery = PermissionUtils.checkReadAllCasesPermission();
in.caseSearchCriteria.sorted = false;' #txt
ac0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f31 226 330 112 44 -22 -8 #rect
ac0 f25 actionTable 'out=in;
' #txt
ac0 f25 actionCode 'import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.util.PermissionUtils;

in.taskSearchCriteria.sorted = false;
if (PermissionUtils.checkReadAllTasksPermission()) {
  in.taskSearchCriteria.includedStates.addAll(TaskSearchCriteria.STANDARD_STATES);
  in.taskSearchCriteria.extendStatesQueryByPermission(PermissionUtils.checkReadAllTasksPermission());
  in.taskSearchCriteria.includedStates.remove(TaskState.DONE);
}

' #txt
ac0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f25 224 234 112 44 -22 -8 #rect
ac0 f21 actionTable 'out=in;
' #txt
ac0 f21 actionCode 'import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivy.addon.portalkit.util.PermissionUtils;

in.taskSearchCriteria.sorted = false;
if (PermissionUtils.checkReadAllTasksPermission()) {
  in.taskSearchCriteria.includedStates.addAll(TaskSearchCriteria.STANDARD_STATES);
  in.taskSearchCriteria.extendStatesQueryByPermission(PermissionUtils.checkReadAllTasksPermission());
}
' #txt
ac0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f21 224 42 112 44 -22 -8 #rect
ac0 f41 actionTable 'out=in;
' #txt
ac0 f41 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.caseSearchCriteria.setAdminQuery(PermissionUtils.checkReadAllCasesPermission());
' #txt
ac0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
ac0 f41 224 426 112 44 -22 -8 #rect
ac0 f9 expr out #txt
ac0 f9 336 64 384 64 #arcP
ac0 f10 expr out #txt
ac0 f10 336 160 384 160 #arcP
ac0 f12 expr out #txt
ac0 f12 336 256 384 256 #arcP
ac0 f13 expr out #txt
ac0 f13 338 352 386 352 #arcP
ac0 f14 expr out #txt
ac0 f14 336 448 384 448 #arcP
ac0 f38 561 529 30 30 0 15 #rect
ac0 f40 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria caseCustomFieldSearchCriteria> param;' #txt
ac0 f40 inParamTable 'out.caseCustomFieldSearchCriteria=param.caseCustomFieldSearchCriteria;
' #txt
ac0 f40 outParamDecl '<java.util.List<String> result> result;' #txt
ac0 f40 outParamTable 'result.result=in.caseCustomFields;
' #txt
ac0 f40 callSignature findCaseCustomFields(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria) #txt
ac0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCaseCustomFields(CaseCustomFieldSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
ac0 f40 113 529 30 30 -117 17 #rect
ac0 f51 processCall 'Ivy Data Processes/CaseService:findValuesOfCustomField(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria)' #txt
ac0 f51 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria caseCustomFieldSearchCriteria> param;' #txt
ac0 f51 requestMappingAction 'param.caseCustomFieldSearchCriteria=in.caseCustomFieldSearchCriteria;
' #txt
ac0 f51 responseActionDecl 'ch.ivy.add.portalkit.AnalyzeStatisticData out;
' #txt
ac0 f51 responseMappingAction 'out=in;
out.caseCustomFields=result.customFields;
' #txt
ac0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
ac0 f51 384 522 112 44 -35 -8 #rect
ac0 f54 expr out #txt
ac0 f54 496 544 561 544 #arcP
ac0 f16 expr out #txt
ac0 f16 143 544 384 544 #arcP
>Proto ac0 .type ch.ivy.add.portalkit.AnalyzeStatisticData #txt
>Proto ac0 .processKind CALLABLE_SUB #txt
>Proto ac0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto ac0 0 0 32 24 18 0 #rect
>Proto ac0 @|BIcon #fIcon
ac0 f11 mainOut f2 tail #connect
ac0 f2 head f1 mainIn #connect
ac0 f15 mainOut f7 tail #connect
ac0 f7 head f5 mainIn #connect
ac0 f19 mainOut f8 tail #connect
ac0 f8 head f6 mainIn #connect
ac0 f0 mainOut f22 tail #connect
ac0 f22 head f21 mainIn #connect
ac0 f3 mainOut f24 tail #connect
ac0 f24 head f23 mainIn #connect
ac0 f4 mainOut f26 tail #connect
ac0 f26 head f25 mainIn #connect
ac0 f28 mainOut f33 tail #connect
ac0 f33 head f30 mainIn #connect
ac0 f27 mainOut f35 tail #connect
ac0 f35 head f31 mainIn #connect
ac0 f36 mainOut f43 tail #connect
ac0 f43 head f41 mainIn #connect
ac0 f39 mainOut f44 tail #connect
ac0 f44 head f37 mainIn #connect
ac0 f21 mainOut f9 tail #connect
ac0 f9 head f11 mainIn #connect
ac0 f23 mainOut f10 tail #connect
ac0 f10 head f15 mainIn #connect
ac0 f25 mainOut f12 tail #connect
ac0 f12 head f19 mainIn #connect
ac0 f31 mainOut f13 tail #connect
ac0 f13 head f28 mainIn #connect
ac0 f41 mainOut f14 tail #connect
ac0 f14 head f39 mainIn #connect
ac0 f51 mainOut f54 tail #connect
ac0 f54 head f38 mainIn #connect
ac0 f40 mainOut f16 tail #connect
ac0 f16 head f51 mainIn #connect
