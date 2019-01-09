[Ivy]
146C8E81DE07F973 3.23 #module
>Proto >Proto Collection #zClass
Te0 TaskService Big #zClass
Te0 B #cInfo
Te0 #process
Te0 @TextInP .resExport .resExport #zField
Te0 @TextInP .type .type #zField
Te0 @TextInP .processKind .processKind #zField
Te0 @AnnotationInP-0n ai ai #zField
Te0 @TextInP .xml .xml #zField
Te0 @TextInP .responsibility .responsibility #zField
Te0 @StartSub f0 '' #zField
Te0 @GridStep f6 '' #zField
Te0 @EndSub f8 '' #zField
Te0 @CallSub f10 '' #zField
Te0 @GridStep f11 '' #zField
Te0 @PushWFArc f13 '' #zField
Te0 @PushWFArc f14 '' #zField
Te0 @PushWFArc f15 '' #zField
Te0 @PushWFArc f17 '' #zField
Te0 @EndSub f21 '' #zField
Te0 @StartSub f18 '' #zField
Te0 @GridStep f22 '' #zField
Te0 @GridStep f19 '' #zField
Te0 @GridStep f23 '' #zField
Te0 @EndSub f25 '' #zField
Te0 @StartSub f26 '' #zField
Te0 @GridStep f27 '' #zField
Te0 @CallSub f28 '' #zField
Te0 @CallSub f29 '' #zField
Te0 @PushWFArc f31 '' #zField
Te0 @PushWFArc f32 '' #zField
Te0 @PushWFArc f33 '' #zField
Te0 @PushWFArc f34 '' #zField
Te0 @PushWFArc f35 '' #zField
Te0 @PushWFArc f36 '' #zField
Te0 @PushWFArc f37 '' #zField
Te0 @PushWFArc f38 '' #zField
>Proto Te0 Te0 TaskService #zField
Te0 f0 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;' #txt
Te0 f0 inParamTable 'out.count=param.count;
out.startIndex=param.startIndex;
out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> result;
' #txt
Te0 f0 outParamTable 'result.errors=in.errors;
result.tasks=in.tasks;
' #txt
Te0 f0 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f0 callSignature findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer) #txt
Te0 f0 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksByCriteria(TaskSearchCriteria,String)</name>
    </language>
</elementInfo>
' #txt
Te0 f0 81 81 30 30 -68 21 #rect
Te0 f0 @|StartSubIcon #fIcon
Te0 f6 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f6 actionTable 'out=in;
' #txt
Te0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().findTasksByCriteria(in.taskSearchCriteria, in.startIndex, in.count);
out.tasks = dto.tasks;
out.errors = dto.errors;' #txt
Te0 f6 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find tasks</name>
    </language>
</elementInfo>
' #txt
Te0 f6 408 74 112 44 -28 -8 #rect
Te0 f6 @|StepIcon #fIcon
Te0 f8 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f8 777 81 30 30 0 15 #rect
Te0 f8 @|EndSubIcon #fIcon
Te0 f10 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f10 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f10 doCall true #txt
Te0 f10 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
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
Te0 f10 592 74 112 44 -35 -8 #rect
Te0 f10 @|CallSubIcon #fIcon
Te0 f11 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f11 actionTable 'out=in;
' #txt
Te0 f11 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.taskSearchCriteria.apps = service.findActiveIvyAppsBy(in.taskSearchCriteria.apps);
' #txt
Te0 f11 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Te0 f11 168 74 176 44 -81 -8 #rect
Te0 f11 @|StepIcon #fIcon
Te0 f13 expr out #txt
Te0 f13 344 96 408 96 #arcP
Te0 f14 expr out #txt
Te0 f14 520 96 592 96 #arcP
Te0 f15 expr out #txt
Te0 f15 704 96 777 96 #arcP
Te0 f17 expr out #txt
Te0 f17 111 96 168 96 #arcP
Te0 f21 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f21 785 337 30 30 0 15 #rect
Te0 f21 @|EndSubIcon #fIcon
Te0 f18 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f18 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f18 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.lang.Long totalTasks> result;
' #txt
Te0 f18 outParamTable 'result.errors=in.errors;
result.totalTasks=in.totalTasks;
' #txt
Te0 f18 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f18 callSignature countTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Te0 f18 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countTasksByCriteria(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f18 81 209 30 30 -89 26 #rect
Te0 f18 @|StartSubIcon #fIcon
Te0 f22 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f22 actionTable 'out=in;
' #txt
Te0 f22 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().findCategoriesByCriteria(in.taskCategorySearchCriteria);
out.categoryTree = dto.#categoryTree;
out.errors = dto.errors;' #txt
Te0 f22 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find categories</name>
    </language>
</elementInfo>
' #txt
Te0 f22 416 330 112 44 -42 -8 #rect
Te0 f22 @|StepIcon #fIcon
Te0 f19 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f19 actionTable 'out=in;
' #txt
Te0 f19 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.taskCategorySearchCriteria.apps = service.findActiveIvyAppsBy(in.taskCategorySearchCriteria.apps);
' #txt
Te0 f19 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Te0 f19 184 330 176 44 -81 -8 #rect
Te0 f19 @|StepIcon #fIcon
Te0 f23 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f23 actionTable 'out=in;
' #txt
Te0 f23 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.taskSearchCriteria.apps = service.findActiveIvyAppsBy(in.taskSearchCriteria.apps);
' #txt
Te0 f23 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Te0 f23 176 202 176 44 -81 -8 #rect
Te0 f23 @|StepIcon #fIcon
Te0 f25 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f25 785 209 30 30 0 15 #rect
Te0 f25 @|EndSubIcon #fIcon
Te0 f26 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria taskCategorySearchCriteria> param;' #txt
Te0 f26 inParamTable 'out.taskCategorySearchCriteria=param.taskCategorySearchCriteria;
' #txt
Te0 f26 outParamDecl '<ch.ivyteam.ivy.workflow.category.CategoryTree categoryTree> result;
' #txt
Te0 f26 outParamTable 'result.categoryTree=in.#categoryTree;
' #txt
Te0 f26 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f26 callSignature findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria) #txt
Te0 f26 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCategoriesByCriteria(TaskCategorySearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f26 81 337 30 30 -89 28 #rect
Te0 f26 @|StartSubIcon #fIcon
Te0 f27 actionDecl 'ch.ivyteam.wf.processes.TaskServiceData out;
' #txt
Te0 f27 actionTable 'out=in;
' #txt
Te0 f27 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyTaskResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.TaskService;

IvyTaskResultDTO dto = TaskService.newInstance().countTasksByCriteria(in.taskSearchCriteria);
out.totalTasks = dto.totalTasks;
out.errors = dto.errors;' #txt
Te0 f27 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count tasks</name>
    </language>
</elementInfo>
' #txt
Te0 f27 416 202 112 44 -33 -8 #rect
Te0 f27 @|StepIcon #fIcon
Te0 f28 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f28 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f28 doCall true #txt
Te0 f28 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
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
Te0 f28 600 202 112 44 -35 -8 #rect
Te0 f28 @|CallSubIcon #fIcon
Te0 f29 type ch.ivyteam.wf.processes.TaskServiceData #txt
Te0 f29 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Te0 f29 doCall true #txt
Te0 f29 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
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
Te0 f29 600 330 112 44 -35 -8 #rect
Te0 f29 @|CallSubIcon #fIcon
Te0 f31 expr out #txt
Te0 f31 352 224 416 224 #arcP
Te0 f32 expr out #txt
Te0 f32 712 224 785 224 #arcP
Te0 f33 expr out #txt
Te0 f33 111 224 176 224 #arcP
Te0 f34 expr out #txt
Te0 f34 360 352 416 352 #arcP
Te0 f35 expr out #txt
Te0 f35 712 352 785 352 #arcP
Te0 f36 expr out #txt
Te0 f36 528 224 600 224 #arcP
Te0 f37 expr out #txt
Te0 f37 111 352 184 352 #arcP
Te0 f38 expr out #txt
Te0 f38 528 352 600 352 #arcP
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
Te0 f11 mainOut f13 tail #connect
Te0 f13 head f6 mainIn #connect
Te0 f6 mainOut f14 tail #connect
Te0 f14 head f10 mainIn #connect
Te0 f10 mainOut f15 tail #connect
Te0 f15 head f8 mainIn #connect
Te0 f0 mainOut f17 tail #connect
Te0 f17 head f11 mainIn #connect
Te0 f23 mainOut f31 tail #connect
Te0 f31 head f27 mainIn #connect
Te0 f27 mainOut f36 tail #connect
Te0 f36 head f28 mainIn #connect
Te0 f18 mainOut f33 tail #connect
Te0 f33 head f23 mainIn #connect
Te0 f28 mainOut f32 tail #connect
Te0 f32 head f25 mainIn #connect
Te0 f19 mainOut f34 tail #connect
Te0 f34 head f22 mainIn #connect
Te0 f22 mainOut f38 tail #connect
Te0 f38 head f29 mainIn #connect
Te0 f29 mainOut f35 tail #connect
Te0 f35 head f21 mainIn #connect
Te0 f26 mainOut f37 tail #connect
Te0 f37 head f19 mainIn #connect
