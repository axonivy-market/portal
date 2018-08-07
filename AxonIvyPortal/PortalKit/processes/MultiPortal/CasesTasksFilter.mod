[Ivy]
[>Created: Thu Feb 04 11:29:07 ICT 2016]
14BE2BC70CCEF8B2 3.18 #module
>Proto >Proto Collection #zClass
Ce0 CasesTasksFilter Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .resExport .resExport #zField
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @GridStep f52 '' #zField
Ce0 @StartSub f53 '' #zField
Ce0 @CallSub f54 '' #zField
Ce0 @GridStep f1 '' #zField
Ce0 @Alternative f55 '' #zField
Ce0 @PushWFArc f60 '' #zField
Ce0 @PushWFArc f58 '' #zField
Ce0 @PushWFArc f57 '' #zField
Ce0 @PushWFArc f56 '' #zField
Ce0 @GridStep f2 '' #zField
Ce0 @CallSub f4 '' #zField
Ce0 @GridStep f7 '' #zField
Ce0 @Alternative f8 '' #zField
Ce0 @StartSub f9 '' #zField
Ce0 @PushWFArc f11 '' #zField
Ce0 @PushWFArc f12 '' #zField
Ce0 @PushWFArc f13 '' #zField
Ce0 @PushWFArc f15 '' #zField
Ce0 @Alternative f148 '' #zField
Ce0 @EndSub f3 '' #zField
Ce0 @CallSub f144 '' #zField
Ce0 @PushWFArc f40 '' #zField
Ce0 @PushWFArc f107 '' #zField
Ce0 @PushWFArc f5 '' #zField
Ce0 @PushWFArc f10 '' #zField
Ce0 @PushWFArc f14 '' #zField
Ce0 @PushWFArc f16 '' #zField
>Proto Ce0 Ce0 CasesTasksFilter #zField
Ce0 f52 actionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f52 actionTable 'out=in;
' #txt
Ce0 f52 actionCode 'import ch.ivy.addon.portalkit.lazy.CaseDataModel;

CaseDataModel cdm = new CaseDataModel(in.cases);
in.cases = cdm.load(in.createdFrom,in.createdTo,in.priorities);' #txt
Ce0 f52 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>lazy
stuff</name>
        <nameStyle>5,7
5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f52 286 228 36 24 20 -2 #rect
Ce0 f52 @|StepIcon #fIcon
Ce0 f53 inParamDecl '<java.lang.String user,java.util.Date createdFrom,java.util.Date createdTo,List<java.lang.String> priorities> param;' #txt
Ce0 f53 inParamTable 'out.createdFrom=param.createdFrom;
out.createdTo=param.createdTo;
out.priorities=param.priorities;
out.user=param.user;
' #txt
Ce0 f53 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivyteam.ivy.workflow.ICase> cases> result;
' #txt
Ce0 f53 outParamTable 'result.errors=in.errors;
result.cases=in.cases;
' #txt
Ce0 f53 actionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f53 callSignature findCases(String,java.util.Date,java.util.Date,List<String>) #txt
Ce0 f53 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCases
(String,Date,Date,String)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f53 291 35 26 26 14 0 #rect
Ce0 f53 @|StartSubIcon #fIcon
Ce0 f54 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f54 processCall MultiPortal/CaseService:findAllCases(String) #txt
Ce0 f54 doCall true #txt
Ce0 f54 requestActionDecl '<java.lang.String user> param;
' #txt
Ce0 f54 requestMappingAction 'param.user=in.user;
' #txt
Ce0 f54 responseActionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f54 responseMappingAction 'out=in;
out.cases=result.cases;
out.errors=result.errors;
' #txt
Ce0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllCases(String)</name>
    </language>
</elementInfo>
' #txt
Ce0 f54 286 116 36 24 20 -2 #rect
Ce0 f54 @|CallSubIcon #fIcon
Ce0 f1 actionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f1 actionTable 'out=in;
' #txt
Ce0 f1 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No cases
//TODO</name>
        <nameStyle>9,7
6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f1 382 228 36 24 20 -2 #rect
Ce0 f1 @|StepIcon #fIcon
Ce0 f1 -14336|-14336|-16777216 #nodeStyle
Ce0 f55 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f55 290 178 28 28 14 0 #rect
Ce0 f55 @|AlternativeIcon #fIcon
Ce0 f60 expr in #txt
Ce0 f60 318 192 400 228 #arcP
Ce0 f60 1 400 192 #addKink
Ce0 f60 0 0.7464523100143388 0 0 #arcLabel
Ce0 f58 expr in #txt
Ce0 f58 outCond 'in.cases.size() >0' #txt
Ce0 f58 304 206 304 228 #arcP
Ce0 f57 expr out #txt
Ce0 f57 304 140 304 178 #arcP
Ce0 f56 expr out #txt
Ce0 f56 304 61 304 116 #arcP
Ce0 f2 actionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f2 actionTable 'out=in;
' #txt
Ce0 f2 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No tasks
//TODO</name>
        <nameStyle>9,7
6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f2 134 228 36 24 20 -2 #rect
Ce0 f2 @|StepIcon #fIcon
Ce0 f2 -14336|-14336|-16777216 #nodeStyle
Ce0 f4 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f4 processCall MultiPortal/TaskService:findActiveTasks(String) #txt
Ce0 f4 doCall true #txt
Ce0 f4 requestActionDecl '<java.lang.String user> param;
' #txt
Ce0 f4 requestMappingAction 'param.user=in.user;
' #txt
Ce0 f4 responseActionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f4 responseMappingAction 'out=in;
out.errors=result.errors;
out.tasks=result.tasks;
' #txt
Ce0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findActiveTasks(String)</name>
    </language>
</elementInfo>
' #txt
Ce0 f4 38 100 36 24 20 -2 #rect
Ce0 f4 @|CallSubIcon #fIcon
Ce0 f7 actionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f7 actionTable 'out=in;
' #txt
Ce0 f7 actionCode 'import ch.ivy.addon.portalkit.lazy.TaskDataModel;

TaskDataModel tdm = new TaskDataModel(in.tasks);
in.tasks = tdm.load(in.createdFrom,in.createdTo,in.priorities);
' #txt
Ce0 f7 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>lazy
stuff</name>
        <nameStyle>5,7
5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f7 38 228 36 24 20 -2 #rect
Ce0 f7 @|StepIcon #fIcon
Ce0 f8 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f8 42 162 28 28 14 0 #rect
Ce0 f8 @|AlternativeIcon #fIcon
Ce0 f9 inParamDecl '<java.lang.String user,java.util.Date createdFrom,java.util.Date createdTo,List<java.lang.String> priorities> param;' #txt
Ce0 f9 inParamTable 'out.createdFrom=param.createdFrom;
out.createdTo=param.createdTo;
out.priorities=param.priorities;
out.user=param.user;
' #txt
Ce0 f9 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivyteam.ivy.workflow.ITask> tasks> result;
' #txt
Ce0 f9 outParamTable 'result.errors=in.errors;
result.tasks=in.tasks;
' #txt
Ce0 f9 actionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f9 callSignature findActiveTasks(String,java.util.Date,java.util.Date,List<String>) #txt
Ce0 f9 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findActiveTasks
(String,Date,Date,List&lt;String&gt;)</name>
        <nameStyle>47,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f9 43 35 26 26 14 0 #rect
Ce0 f9 @|StartSubIcon #fIcon
Ce0 f11 expr in #txt
Ce0 f11 70 176 152 228 #arcP
Ce0 f11 1 152 176 #addKink
Ce0 f11 0 0.8837620364717167 0 0 #arcLabel
Ce0 f12 expr in #txt
Ce0 f12 outCond in.tasks.size()>0 #txt
Ce0 f12 56 190 56 228 #arcP
Ce0 f13 expr out #txt
Ce0 f13 56 124 56 162 #arcP
Ce0 f15 expr out #txt
Ce0 f15 56 61 56 100 #arcP
Ce0 f148 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f148 138 330 28 28 14 0 #rect
Ce0 f148 @|AlternativeIcon #fIcon
Ce0 f3 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f3 139 427 26 26 14 0 #rect
Ce0 f3 @|EndSubIcon #fIcon
Ce0 f144 type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
Ce0 f144 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.ws.addon.WsException>)' #txt
Ce0 f144 doCall true #txt
Ce0 f144 requestActionDecl '<java.util.List<ch.ivy.ws.addon.WsException> exceptions> param;
' #txt
Ce0 f144 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f144 responseActionDecl 'ch.ivyteam.wf.processes.CaseTaskCriteria out;
' #txt
Ce0 f144 responseMappingAction 'out=in;
' #txt
Ce0 f144 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors if any</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f144 134 380 36 24 20 -2 #rect
Ce0 f144 @|CallSubIcon #fIcon
Ce0 f40 expr out #txt
Ce0 f40 152 404 152 427 #arcP
Ce0 f107 expr in #txt
Ce0 f107 152 358 152 380 #arcP
Ce0 f5 expr out #txt
Ce0 f5 152 252 152 330 #arcP
Ce0 f5 0 0.6258108943875441 0 0 #arcLabel
Ce0 f10 expr out #txt
Ce0 f10 56 252 138 344 #arcP
Ce0 f10 1 56 344 #addKink
Ce0 f10 1 0.11846451713537068 0 0 #arcLabel
Ce0 f14 expr out #txt
Ce0 f14 304 252 163 341 #arcP
Ce0 f14 1 304 296 #addKink
Ce0 f14 0 0.9177319714841085 0 0 #arcLabel
Ce0 f16 expr out #txt
Ce0 f16 400 252 166 344 #arcP
Ce0 f16 1 400 344 #addKink
Ce0 f16 1 0.18144932768694524 0 0 #arcLabel
>Proto Ce0 .type ch.ivyteam.wf.processes.CaseTaskCriteria #txt
>Proto Ce0 .processKind CALLABLE_SUB #txt
>Proto Ce0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Tasks Filer</swimlaneLabel>
        <swimlaneLabel>Cases Filter</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>248</swimlaneSize>
    <swimlaneSize>256</swimlaneSize>
    <swimlaneColor>-3342388</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f53 mainOut f56 tail #connect
Ce0 f56 head f54 mainIn #connect
Ce0 f54 mainOut f57 tail #connect
Ce0 f57 head f55 in #connect
Ce0 f55 out f58 tail #connect
Ce0 f58 head f52 mainIn #connect
Ce0 f55 out f60 tail #connect
Ce0 f60 head f1 mainIn #connect
Ce0 f9 mainOut f15 tail #connect
Ce0 f15 head f4 mainIn #connect
Ce0 f4 mainOut f13 tail #connect
Ce0 f13 head f8 in #connect
Ce0 f8 out f12 tail #connect
Ce0 f12 head f7 mainIn #connect
Ce0 f8 out f11 tail #connect
Ce0 f11 head f2 mainIn #connect
Ce0 f144 mainOut f40 tail #connect
Ce0 f40 head f3 mainIn #connect
Ce0 f148 out f107 tail #connect
Ce0 f107 head f144 mainIn #connect
Ce0 f2 mainOut f5 tail #connect
Ce0 f5 head f148 in #connect
Ce0 f7 mainOut f10 tail #connect
Ce0 f10 head f148 in #connect
Ce0 f52 mainOut f14 tail #connect
Ce0 f14 head f148 in #connect
Ce0 f1 mainOut f16 tail #connect
Ce0 f16 head f148 in #connect
