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
Te0 @CallSub f10 '' #zField
Te0 @PushWFArc f14 '' #zField
Te0 @PushWFArc f15 '' #zField
Te0 @EndSub f21 '' #zField
Te0 @StartSub f18 '' #zField
Te0 @GridStep f22 '' #zField
Te0 @EndSub f25 '' #zField
Te0 @StartSub f26 '' #zField
Te0 @GridStep f27 '' #zField
Te0 @CallSub f28 '' #zField
Te0 @CallSub f29 '' #zField
Te0 @PushWFArc f32 '' #zField
Te0 @PushWFArc f35 '' #zField
Te0 @PushWFArc f36 '' #zField
Te0 @PushWFArc f38 '' #zField
Te0 @StartSub f1 '' #zField
Te0 @GridStep f2 '' #zField
Te0 @EndSub f3 '' #zField
Te0 @CallSub f4 '' #zField
Te0 @PushWFArc f9 '' #zField
Te0 @PushWFArc f16 '' #zField
Te0 @CallSub f20 '' #zField
Te0 @GridStep f24 '' #zField
Te0 @StartSub f39 '' #zField
Te0 @EndSub f40 '' #zField
Te0 @PushWFArc f42 '' #zField
Te0 @PushWFArc f44 '' #zField
Te0 @GridStep f46 '' #zField
Te0 @EndSub f47 '' #zField
Te0 @StartSub f48 '' #zField
Te0 @CallSub f49 '' #zField
Te0 @PushWFArc f52 '' #zField
Te0 @PushWFArc f53 '' #zField
Te0 @PushWFArc f54 '' #zField
Te0 @PushWFArc f11 '' #zField
Te0 @PushWFArc f13 '' #zField
Te0 @PushWFArc f17 '' #zField
Te0 @PushWFArc f7 '' #zField
Te0 @PushWFArc f33 '' #zField
>Proto Te0 Te0 TaskService #zField
Te0 f0 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Te0 f0 inParamTable 'out.count=param.count;
out.startIndex=param.startIndex;
out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> result;' #txt
Te0 f0 outParamTable 'result.errors=in.errors;
result.tasks=in.tasks;
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
out.errors = dto.errors;' #txt
Te0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find tasks</name>
    </language>
</elementInfo>
' #txt
Te0 f6 192 74 112 44 -28 -8 #rect
Te0 f6 @|StepIcon #fIcon
Te0 f8 561 81 30 30 0 15 #rect
Te0 f8 @|EndSubIcon #fIcon
Te0 f10 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f10 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Te0 f10 requestMappingAction 'param.exceptions=in.errors;
' #txt
Te0 f10 responseActionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f10 responseMappingAction 'out=in;
' #txt
Te0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Te0 f10 376 74 112 44 -35 -8 #rect
Te0 f10 @|CallSubIcon #fIcon
Te0 f14 expr out #txt
Te0 f14 304 96 376 96 #arcP
Te0 f15 expr out #txt
Te0 f15 488 96 561 96 #arcP
Te0 f21 569 273 30 30 0 15 #rect
Te0 f21 @|EndSubIcon #fIcon
Te0 f18 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f18 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f18 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,Long totalTasks> result;' #txt
Te0 f18 outParamTable 'result.errors=in.errors;
result.totalTasks=in.totalTasks;
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
out.errors = dto.errors;' #txt
Te0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find categories</name>
    </language>
</elementInfo>
' #txt
Te0 f22 200 266 112 44 -42 -8 #rect
Te0 f22 @|StepIcon #fIcon
Te0 f25 569 177 30 30 0 15 #rect
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
out.errors = dto.errors;' #txt
Te0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count tasks</name>
    </language>
</elementInfo>
' #txt
Te0 f27 200 170 112 44 -33 -8 #rect
Te0 f27 @|StepIcon #fIcon
Te0 f28 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f28 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Te0 f28 requestMappingAction 'param.exceptions=in.errors;
' #txt
Te0 f28 responseActionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f28 responseMappingAction 'out=in;
' #txt
Te0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Te0 f28 384 170 112 44 -35 -8 #rect
Te0 f28 @|CallSubIcon #fIcon
Te0 f29 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f29 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Te0 f29 requestMappingAction 'param.exceptions=in.errors;
' #txt
Te0 f29 responseActionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f29 responseMappingAction 'out=in;
' #txt
Te0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Te0 f29 384 266 112 44 -35 -8 #rect
Te0 f29 @|CallSubIcon #fIcon
Te0 f32 expr out #txt
Te0 f32 496 192 569 192 #arcP
Te0 f35 expr out #txt
Te0 f35 496 288 569 288 #arcP
Te0 f36 expr out #txt
Te0 f36 312 192 384 192 #arcP
Te0 f38 expr out #txt
Te0 f38 312 288 384 288 #arcP
Te0 f1 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f1 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f1 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,ch.ivy.addon.portalkit.bo.PriorityStatistic priorityStatistic> result;' #txt
Te0 f1 outParamTable 'result.errors=in.errors;
result.priorityStatistic=in.priorityStatistic;
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
out.errors = dto.errors;' #txt
Te0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze priority statistic</name>
    </language>
</elementInfo>
' #txt
Te0 f2 176 362 144 44 -62 -8 #rect
Te0 f2 @|StepIcon #fIcon
Te0 f3 561 369 30 30 0 15 #rect
Te0 f3 @|EndSubIcon #fIcon
Te0 f4 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f4 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Te0 f4 requestMappingAction 'param.exceptions=in.errors;
' #txt
Te0 f4 responseActionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f4 responseMappingAction 'out=in;
' #txt
Te0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Te0 f4 376 362 112 44 -35 -8 #rect
Te0 f4 @|CallSubIcon #fIcon
Te0 f9 expr out #txt
Te0 f9 488 384 561 384 #arcP
Te0 f16 expr out #txt
Te0 f16 320 384 376 384 #arcP
Te0 f20 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f20 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Te0 f20 requestMappingAction 'param.exceptions=in.errors;
' #txt
Te0 f20 responseActionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f20 responseMappingAction 'out=in;
' #txt
Te0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Te0 f20 376 458 112 44 -35 -8 #rect
Te0 f20 @|CallSubIcon #fIcon
Te0 f24 actionTable 'out=in;
' #txt
Te0 f24 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().analyzeExpiryStatistic(in.taskSearchCriteria);
out.expiryStatistic = dto.expiryStatistic;
out.errors = dto.errors;' #txt
Te0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze expiry statistic</name>
    </language>
</elementInfo>
' #txt
Te0 f24 184 458 128 44 -60 -8 #rect
Te0 f24 @|StepIcon #fIcon
Te0 f39 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f39 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f39 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,ch.ivy.addon.portalkit.bo.ExpiryStatistic expiryStatistic> result;' #txt
Te0 f39 outParamTable 'result.errors=in.errors;
result.expiryStatistic=in.expiryStatistic;
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
Te0 f40 561 465 30 30 0 15 #rect
Te0 f40 @|EndSubIcon #fIcon
Te0 f42 expr out #txt
Te0 f42 488 480 561 480 #arcP
Te0 f44 expr out #txt
Te0 f44 312 480 376 480 #arcP
Te0 f46 actionTable 'out=in;
' #txt
Te0 f46 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().analyzeElapsedTimeOfTasks(in.taskSearchCriteria);
out.elapsedTimeStatistic = dto.elapsedTimeStatistic;
out.errors = dto.errors;' #txt
Te0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze elapsed time statistic</name>
    </language>
</elementInfo>
' #txt
Te0 f46 160 554 176 44 -80 -8 #rect
Te0 f46 @|StepIcon #fIcon
Te0 f47 561 561 30 30 0 15 #rect
Te0 f47 @|EndSubIcon #fIcon
Te0 f48 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f48 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f48 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic elapsedTimeStatistic> result;' #txt
Te0 f48 outParamTable 'result.errors=in.errors;
result.elapsedTimeStatistic=in.elapsedTimeStatistic;
' #txt
Te0 f48 callSignature analyzeElapsedTime(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Te0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeElapsedTime(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f48 81 561 30 30 -68 21 #rect
Te0 f48 @|StartSubIcon #fIcon
Te0 f49 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f49 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Te0 f49 requestMappingAction 'param.exceptions=in.errors;
' #txt
Te0 f49 responseActionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f49 responseMappingAction 'out=in;
' #txt
Te0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Te0 f49 376 554 112 44 -35 -8 #rect
Te0 f49 @|CallSubIcon #fIcon
Te0 f52 expr out #txt
Te0 f52 336 576 376 576 #arcP
Te0 f53 expr out #txt
Te0 f53 488 576 561 576 #arcP
Te0 f54 expr out #txt
Te0 f54 111 96 192 96 #arcP
Te0 f11 expr out #txt
Te0 f11 111 192 200 192 #arcP
Te0 f13 expr out #txt
Te0 f13 111 288 200 288 #arcP
Te0 f17 expr out #txt
Te0 f17 111 384 176 384 #arcP
Te0 f7 expr out #txt
Te0 f7 111 480 184 480 #arcP
Te0 f33 expr out #txt
Te0 f33 111 576 160 576 #arcP
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
Te0 f6 mainOut f14 tail #connect
Te0 f14 head f10 mainIn #connect
Te0 f10 mainOut f15 tail #connect
Te0 f15 head f8 mainIn #connect
Te0 f27 mainOut f36 tail #connect
Te0 f36 head f28 mainIn #connect
Te0 f28 mainOut f32 tail #connect
Te0 f32 head f25 mainIn #connect
Te0 f22 mainOut f38 tail #connect
Te0 f38 head f29 mainIn #connect
Te0 f29 mainOut f35 tail #connect
Te0 f35 head f21 mainIn #connect
Te0 f2 mainOut f16 tail #connect
Te0 f16 head f4 mainIn #connect
Te0 f4 mainOut f9 tail #connect
Te0 f9 head f3 mainIn #connect
Te0 f24 mainOut f44 tail #connect
Te0 f44 head f20 mainIn #connect
Te0 f20 mainOut f42 tail #connect
Te0 f42 head f40 mainIn #connect
Te0 f46 mainOut f52 tail #connect
Te0 f52 head f49 mainIn #connect
Te0 f49 mainOut f53 tail #connect
Te0 f53 head f47 mainIn #connect
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
Te0 f48 mainOut f33 tail #connect
Te0 f33 head f46 mainIn #connect
