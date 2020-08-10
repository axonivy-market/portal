[Ivy]
146C8E81DE07F973 7.5.0 #module
>Proto >Proto Collection #zClass
Te0 TaskService Big #zClass
Te0 B #cInfo
Te0 #process
Te0 @TextInP .type .type #zField
Te0 @TextInP .processKind .processKind #zField
Te0 @AnnotationInP-0n ai ai #zField
Te0 @TextInP .xml .xml #zField
Te0 @TextInP .responsibility .responsibility #zField
Te0 @StartSub f0 '' #zField
Te0 @GridStep f6 '' #zField
Te0 @EndSub f8 '' #zField
Te0 @EndSub f21 '' #zField
Te0 @StartSub f18 '' #zField
Te0 @GridStep f22 '' #zField
Te0 @EndSub f25 '' #zField
Te0 @StartSub f26 '' #zField
Te0 @GridStep f27 '' #zField
Te0 @StartSub f1 '' #zField
Te0 @GridStep f2 '' #zField
Te0 @EndSub f3 '' #zField
Te0 @GridStep f24 '' #zField
Te0 @StartSub f39 '' #zField
Te0 @EndSub f40 '' #zField
Te0 @PushWFArc f54 '' #zField
Te0 @PushWFArc f11 '' #zField
Te0 @PushWFArc f13 '' #zField
Te0 @PushWFArc f17 '' #zField
Te0 @PushWFArc f7 '' #zField
Te0 @PushWFArc f4 '' #zField
Te0 @PushWFArc f5 '' #zField
Te0 @PushWFArc f9 '' #zField
Te0 @PushWFArc f10 '' #zField
Te0 @PushWFArc f12 '' #zField
>Proto Te0 Te0 TaskService #zField
Te0 f0 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Te0 f0 inParamTable 'out.count=param.count;
out.startIndex=param.startIndex;
out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f0 outParamDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> result;' #txt
Te0 f0 outParamTable 'result.tasks=in.tasks;
' #txt
Te0 f0 callSignature findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer) #txt
Te0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksByCriteria(TaskSearchCriteria,String)</name>
    </language>
</elementInfo>
' #txt
Te0 f0 81 81 30 30 -68 21 #rect
Te0 f0 @|StartSubIcon #fIcon
Te0 f6 actionTable 'out=in;
' #txt
Te0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().findTasksByCriteria(in.taskSearchCriteria, in.startIndex, in.count);
out.tasks = dto.tasks;
' #txt
Te0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find tasks</name>
    </language>
</elementInfo>
' #txt
Te0 f6 280 74 112 44 -28 -8 #rect
Te0 f6 @|StepIcon #fIcon
Te0 f8 513 81 30 30 0 15 #rect
Te0 f8 @|EndSubIcon #fIcon
Te0 f21 513 273 30 30 0 15 #rect
Te0 f21 @|EndSubIcon #fIcon
Te0 f18 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f18 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f18 outParamDecl '<Long totalTasks> result;' #txt
Te0 f18 outParamTable 'result.totalTasks=in.totalTasks;
' #txt
Te0 f18 callSignature countTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Te0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countTasksByCriteria(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f18 81 177 30 30 -89 26 #rect
Te0 f18 @|StartSubIcon #fIcon
Te0 f22 actionTable 'out=in;
' #txt
Te0 f22 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().findCategoriesByCriteria(in.taskCategorySearchCriteria);
out.categoryTree = dto.#categoryTree;
' #txt
Te0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find categories</name>
    </language>
</elementInfo>
' #txt
Te0 f22 280 266 112 44 -42 -8 #rect
Te0 f22 @|StepIcon #fIcon
Te0 f25 513 177 30 30 0 15 #rect
Te0 f25 @|EndSubIcon #fIcon
Te0 f26 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria taskCategorySearchCriteria> param;' #txt
Te0 f26 inParamTable 'out.taskCategorySearchCriteria=param.taskCategorySearchCriteria;
' #txt
Te0 f26 outParamDecl '<ch.ivyteam.ivy.workflow.category.CategoryTree categoryTree> result;' #txt
Te0 f26 outParamTable 'result.categoryTree=in.#categoryTree;
' #txt
Te0 f26 callSignature findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria) #txt
Te0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCategoriesByCriteria(TaskCategorySearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f26 81 273 30 30 -89 28 #rect
Te0 f26 @|StartSubIcon #fIcon
Te0 f27 actionTable 'out=in;
' #txt
Te0 f27 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().countTasksByCriteria(in.taskSearchCriteria);
out.totalTasks = dto.totalTasks;
' #txt
Te0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count tasks</name>
    </language>
</elementInfo>
' #txt
Te0 f27 280 170 112 44 -33 -8 #rect
Te0 f27 @|StepIcon #fIcon
Te0 f1 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f1 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f1 outParamDecl '<ch.ivy.addon.portalkit.bo.PriorityStatistic priorityStatistic> result;' #txt
Te0 f1 outParamTable 'result.priorityStatistic=in.priorityStatistic;
' #txt
Te0 f1 callSignature analyzePriorityStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Te0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzePriorityStatistic(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f1 81 369 30 30 -68 21 #rect
Te0 f1 @|StartSubIcon #fIcon
Te0 f2 actionTable 'out=in;
' #txt
Te0 f2 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().analyzePriorityStatistic(in.taskSearchCriteria);
out.priorityStatistic = dto.priorityStatistic;
' #txt
Te0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze priority statistic</name>
    </language>
</elementInfo>
' #txt
Te0 f2 264 362 144 44 -62 -8 #rect
Te0 f2 @|StepIcon #fIcon
Te0 f3 513 369 30 30 0 15 #rect
Te0 f3 @|EndSubIcon #fIcon
Te0 f24 actionTable 'out=in;
' #txt
Te0 f24 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().analyzeExpiryStatistic(in.taskSearchCriteria);
out.expiryStatistic = dto.expiryStatistic;
' #txt
Te0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze expiry statistic</name>
    </language>
</elementInfo>
' #txt
Te0 f24 272 458 128 44 -60 -8 #rect
Te0 f24 @|StepIcon #fIcon
Te0 f39 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f39 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f39 outParamDecl '<ch.ivy.addon.portalkit.bo.ExpiryStatistic expiryStatistic> result;' #txt
Te0 f39 outParamTable 'result.expiryStatistic=in.expiryStatistic;
' #txt
Te0 f39 callSignature analyzeExpiryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Te0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeExpiryStatistic(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f39 81 465 30 30 -68 21 #rect
Te0 f39 @|StartSubIcon #fIcon
Te0 f40 513 465 30 30 0 15 #rect
Te0 f40 @|EndSubIcon #fIcon
Te0 f54 expr out #txt
Te0 f54 111 96 280 96 #arcP
Te0 f11 expr out #txt
Te0 f11 111 192 280 192 #arcP
Te0 f13 expr out #txt
Te0 f13 111 288 280 288 #arcP
Te0 f17 expr out #txt
Te0 f17 111 384 264 384 #arcP
Te0 f7 expr out #txt
Te0 f7 111 480 272 480 #arcP
Te0 f4 392 96 513 96 #arcP
Te0 f5 392 192 513 192 #arcP
Te0 f9 392 288 513 288 #arcP
Te0 f10 408 384 513 384 #arcP
Te0 f12 400 480 513 480 #arcP
>Proto Te0 .type ch.ivyteam.wf.processes.TaskServiceData #txt
>Proto Te0 .processKind CALLABLE_SUB #txt
>Proto Te0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>RESET TASK</swimlaneLabel>
        <swimlaneLabel>DELEGATE TASK</swimlaneLabel>
        <swimlaneLabel>SEARCH TASKS</swimlaneLabel>
        <swimlaneLabel>CAN USER RESUME TASK</swimlaneLabel>
        <swimlaneLabel>CREATE NOTE</swimlaneLabel>
        <swimlaneLabel>COUNT TASKS BY CRITERIA</swimlaneLabel>
        <swimlaneLabel>FIND CATEGORIES</swimlaneLabel>
        <swimlaneLabel>STATISTIC</swimlaneLabel>
        <swimlaneLabel>SAVE</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>248</swimlaneSize>
    <swimlaneSize>344</swimlaneSize>
    <swimlaneSize>472</swimlaneSize>
    <swimlaneSize>200</swimlaneSize>
    <swimlaneSize>256</swimlaneSize>
    <swimlaneSize>280</swimlaneSize>
    <swimlaneSize>1016</swimlaneSize>
    <swimlaneSize>1120</swimlaneSize>
    <swimlaneSize>288</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Te0 0 0 32 24 18 0 #rect
>Proto Te0 @|BIcon #fIcon
Te0 f0 mainOut f54 tail #connect
Te0 f54 head f6 mainIn #connect
Te0 f18 mainOut f11 tail #connect
Te0 f11 head f27 mainIn #connect
Te0 f26 mainOut f13 tail #connect
Te0 f13 head f22 mainIn #connect
Te0 f1 mainOut f17 tail #connect
Te0 f17 head f2 mainIn #connect
Te0 f39 mainOut f7 tail #connect
Te0 f7 head f24 mainIn #connect
Te0 f6 mainOut f4 tail #connect
Te0 f4 head f8 mainIn #connect
Te0 f27 mainOut f5 tail #connect
Te0 f5 head f25 mainIn #connect
Te0 f22 mainOut f9 tail #connect
Te0 f9 head f21 mainIn #connect
Te0 f2 mainOut f10 tail #connect
Te0 f10 head f3 mainIn #connect
Te0 f24 mainOut f12 tail #connect
Te0 f12 head f40 mainIn #connect
