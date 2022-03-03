[Ivy]
146C8E81DE07F973 9.4.0 #module
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
Te0 @GridStep f14 '' #zField
Te0 @EndSub f15 '' #zField
Te0 @StartSub f16 '' #zField
Te0 @PushWFArc f19 '' #zField
Te0 @PushWFArc f20 '' #zField
Te0 @StartSub f23 '' #zField
Te0 @EndSub f28 '' #zField
Te0 @GridStep f29 '' #zField
Te0 @PushWFArc f30 '' #zField
Te0 @PushWFArc f31 '' #zField
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
Te0 f0 81 49 30 30 -68 21 #rect
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
Te0 f6 280 42 112 44 -28 -8 #rect
Te0 f8 513 49 30 30 0 15 #rect
Te0 f21 513 241 30 30 0 15 #rect
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
Te0 f18 81 145 30 30 -89 26 #rect
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
Te0 f22 280 234 112 44 -42 -8 #rect
Te0 f25 513 145 30 30 0 15 #rect
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
Te0 f26 81 241 30 30 -89 28 #rect
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
Te0 f27 280 138 112 44 -33 -8 #rect
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
Te0 f1 81 337 30 30 -68 21 #rect
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
Te0 f2 264 330 144 44 -62 -8 #rect
Te0 f3 513 337 30 30 0 15 #rect
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
Te0 f24 272 426 128 44 -60 -8 #rect
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
Te0 f39 81 433 30 30 -68 21 #rect
Te0 f40 513 433 30 30 0 15 #rect
Te0 f54 expr out #txt
Te0 f54 111 64 280 64 #arcP
Te0 f11 expr out #txt
Te0 f11 111 160 280 160 #arcP
Te0 f13 expr out #txt
Te0 f13 111 256 280 256 #arcP
Te0 f17 expr out #txt
Te0 f17 111 352 264 352 #arcP
Te0 f7 expr out #txt
Te0 f7 111 448 272 448 #arcP
Te0 f4 392 64 513 64 #arcP
Te0 f5 392 160 513 160 #arcP
Te0 f9 392 256 513 256 #arcP
Te0 f10 408 352 513 352 #arcP
Te0 f12 400 448 513 448 #arcP
Te0 f14 actionTable 'out=in;
' #txt
Te0 f14 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().analyzeTaskStateStatistic(in.taskSearchCriteria);
out.taskStateStatistic = dto.taskStateStatistic;
' #txt
Te0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze expiry statistic</name>
    </language>
</elementInfo>
' #txt
Te0 f14 272 554 128 44 -60 -8 #rect
Te0 f15 513 561 30 30 0 15 #rect
Te0 f16 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria criteria> param;' #txt
Te0 f16 inParamTable 'out.taskSearchCriteria=param.criteria;
' #txt
Te0 f16 outParamDecl '<ch.ivy.addon.portalkit.bo.TaskStateStatistic taskStateStatistic> result;' #txt
Te0 f16 outParamTable 'result.taskStateStatistic=in.taskStateStatistic;
' #txt
Te0 f16 callSignature analyzeTaskStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Te0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeTaskStateStatistic(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f16 81 561 30 30 -68 21 #rect
Te0 f19 expr out #txt
Te0 f19 111 576 272 576 #arcP
Te0 f20 400 576 513 576 #arcP
Te0 f23 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria criteria> param;' #txt
Te0 f23 inParamTable 'out.taskSearchCriteria=param.criteria;
' #txt
Te0 f23 outParamDecl '<ch.ivy.addon.portalkit.bo.TaskCategoryStatistic taskCategoryStatistic> result;' #txt
Te0 f23 outParamTable 'result.taskCategoryStatistic=in.taskCategoryStatistic;
' #txt
Te0 f23 callSignature analyzeTaskCategoryStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Te0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeTaskCategoryStatistic(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f23 81 689 30 30 -68 21 #rect
Te0 f28 513 689 30 30 0 15 #rect
Te0 f29 actionTable 'out=in;
' #txt
Te0 f29 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().analyzeTaskCategoryStatistic(in.taskSearchCriteria);
out.taskCategoryStatistic = dto.taskCategoryStatistic;
' #txt
Te0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze expiry statistic</name>
    </language>
</elementInfo>
' #txt
Te0 f29 272 682 128 44 -60 -8 #rect
Te0 f30 expr out #txt
Te0 f30 111 704 272 704 #arcP
Te0 f31 400 704 513 704 #arcP
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
    <swimlaneSize>352</swimlaneSize>
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
Te0 f16 mainOut f19 tail #connect
Te0 f19 head f14 mainIn #connect
Te0 f14 mainOut f20 tail #connect
Te0 f20 head f15 mainIn #connect
Te0 f23 mainOut f30 tail #connect
Te0 f30 head f29 mainIn #connect
Te0 f29 mainOut f31 tail #connect
Te0 f31 head f28 mainIn #connect
