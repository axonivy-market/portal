[Ivy]
[>Created: Mon Feb 29 10:21:57 ICT 2016]
1508F2CD9F746207 3.18 #module
>Proto >Proto Collection #zClass
Sk0 GlobalSearch Big #zClass
Sk0 B #cInfo
Sk0 #process
Sk0 @TextInP .resExport .resExport #zField
Sk0 @TextInP .type .type #zField
Sk0 @TextInP .processKind .processKind #zField
Sk0 @AnnotationInP-0n ai ai #zField
Sk0 @MessageFlowInP-0n messageIn messageIn #zField
Sk0 @MessageFlowOutP-0n messageOut messageOut #zField
Sk0 @TextInP .xml .xml #zField
Sk0 @TextInP .responsibility .responsibility #zField
Sk0 @GridStep f5 '' #zField
Sk0 @CallSub f3 '' #zField
Sk0 @EndTask f4 '' #zField
Sk0 @StartEvent f6 '' #zField
Sk0 @PushWFArc f9 '' #zField
Sk0 @Split f0 '' #zField
Sk0 @PushWFArc f1 '' #zField
Sk0 @Join f7 '' #zField
Sk0 @SJArc f10 '' #zField
Sk0 @PushWFArc f8 '' #zField
Sk0 @CallSub f11 '' #zField
Sk0 @SJArc f13 '' #zField
Sk0 @CallSub f14 '' #zField
Sk0 @SJArc f16 '' #zField
Sk0 @GridStep f17 '' #zField
Sk0 @PushWFArc f18 '' #zField
Sk0 @PushWFArc f15 '' #zField
Sk0 @GridStep f19 '' #zField
Sk0 @PushWFArc f20 '' #zField
Sk0 @PushWFArc f2 '' #zField
Sk0 @GridStep f21 '' #zField
Sk0 @PushWFArc f22 '' #zField
Sk0 @PushWFArc f12 '' #zField
>Proto Sk0 Sk0 GlobalSearch #zField
Sk0 f5 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f5 actionTable 'out=in;
' #txt
Sk0 f5 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portal.generic.bean.UserMenuBean;

FacesContext context = FacesContext.getCurrentInstance();
UserMenuBean userMenuBean = context.getApplication().evaluateExpressionGet(context, "#{userMenuBean}", UserMenuBean.class) as UserMenuBean;

userMenuBean.setDataForSearchResult(in.processes, in.tasks, in.cases);
' #txt
Sk0 f5 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>bind data</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sk0 f5 270 364 36 24 20 -2 #rect
Sk0 f5 @|StepIcon #fIcon
Sk0 f3 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f3 processCall MultiPortal/TaskService:findTasksByCriteria(ch.ivy.ws.addon.TaskSearchCriteria) #txt
Sk0 f3 doCall true #txt
Sk0 f3 requestActionDecl '<ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria> param;
' #txt
Sk0 f3 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Sk0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f3 responseMappingAction 'out=in;
out.tasks=result.tasks;
' #txt
Sk0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sk0 f3 78 244 36 24 20 -2 #rect
Sk0 f3 @|CallSubIcon #fIcon
Sk0 f4 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f4 275 427 26 26 14 0 #rect
Sk0 f4 @|EndIcon #fIcon
Sk0 f6 outerBean "ch.ivy.addon.portal.generic.bean.GlobalSearchListener" #txt
Sk0 f6 outLink eventLink.ivp #txt
Sk0 f6 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f6 @C|.responsibility Everybody #txt
Sk0 f6 275 51 26 26 14 0 #rect
Sk0 f6 @|StartEventIcon #fIcon
Sk0 f9 expr out #txt
Sk0 f9 288 388 288 427 #arcP
Sk0 f0 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out1;
ch.ivy.addon.portal.generic.GlobalSearchData out2;
ch.ivy.addon.portal.generic.GlobalSearchData out3;
' #txt
Sk0 f0 actionTable 'out1=in;
out2=in;
out3=in;
' #txt
Sk0 f0 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f0 274 114 28 28 14 0 #rect
Sk0 f0 @|ThreadIcon #fIcon
Sk0 f1 expr out #txt
Sk0 f1 288 77 288 114 #arcP
Sk0 f7 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f7 actionTable 'out=in1;
out.cases=in3.cases;
out.processes=in2.processes;
out.tasks=in1.tasks;
' #txt
Sk0 f7 274 306 28 28 14 0 #rect
Sk0 f7 @|JoinIcon #fIcon
Sk0 f10 expr out #txt
Sk0 f10 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f10 var in1 #txt
Sk0 f10 96 268 274 320 #arcP
Sk0 f10 1 96 320 #addKink
Sk0 f10 0 0.9611949423962465 0 0 #arcLabel
Sk0 f8 expr out #txt
Sk0 f8 288 334 288 364 #arcP
Sk0 f11 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f11 processCall MultiPortal/ProcessStart:findProcessStartsByCriteria(ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Sk0 f11 doCall true #txt
Sk0 f11 requestActionDecl '<ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;
' #txt
Sk0 f11 requestMappingAction 'param.processSearchCriteria=in.processSearchCriteria;
' #txt
Sk0 f11 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f11 responseMappingAction 'out=in;
out.processes=result.processStarts;
' #txt
Sk0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessStart</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sk0 f11 270 244 36 24 20 -2 #rect
Sk0 f11 @|CallSubIcon #fIcon
Sk0 f13 expr out #txt
Sk0 f13 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f13 var in2 #txt
Sk0 f13 288 268 288 306 #arcP
Sk0 f13 0 0.5031468756524428 0 0 #arcLabel
Sk0 f14 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f14 processCall MultiPortal/CaseService:findCasesWithDestroyPermissionByCriteria(ch.ivy.ws.addon.CaseSearchCriteria,String) #txt
Sk0 f14 doCall true #txt
Sk0 f14 requestActionDecl '<ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria,java.lang.String username> param;
' #txt
Sk0 f14 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.username=in.username;
' #txt
Sk0 f14 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f14 responseMappingAction 'out=in;
out.cases=result.cases;
' #txt
Sk0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sk0 f14 462 244 36 24 20 -2 #rect
Sk0 f14 @|CallSubIcon #fIcon
Sk0 f16 expr out #txt
Sk0 f16 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f16 var in3 #txt
Sk0 f16 480 268 302 320 #arcP
Sk0 f16 1 480 320 #addKink
Sk0 f16 1 0.23223506746973935 0 0 #arcLabel
Sk0 f17 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f17 actionTable 'out=in;
' #txt
Sk0 f17 actionCode 'import ch.ivy.ws.addon.CaseState;

in.caseSearchCriteria.involvedUsername = in.username;

List<CaseState> states = new List<CaseState>();
states.add(CaseState.DESTROYED);
states.add(CaseState.ZOMBIE);
in.caseSearchCriteria.excludedStates = states;

in.caseSearchCriteria.keyword = in.keyword;' #txt
Sk0 f17 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sk0 f17 462 180 36 24 20 -2 #rect
Sk0 f17 @|StepIcon #fIcon
Sk0 f18 expr out3 #txt
Sk0 f18 302 128 480 180 #arcP
Sk0 f18 1 480 128 #addKink
Sk0 f18 0 0.6356695856336364 0 0 #arcLabel
Sk0 f15 expr out #txt
Sk0 f15 480 204 480 244 #arcP
Sk0 f19 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f19 actionTable 'out=in;
' #txt
Sk0 f19 actionCode 'import ch.ivy.ws.addon.TaskState;

in.taskSearchCriteria.involvedUsername = in.username;

List<TaskState> states = new List<TaskState>();
states.add(TaskState.SUSPENDED);
states.add(TaskState.RESUMED);
states.add(TaskState.PARKED);
in.taskSearchCriteria.includedStates = states;

in.taskSearchCriteria.keyword = in.keyword;' #txt
Sk0 f19 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sk0 f19 78 180 36 24 20 -2 #rect
Sk0 f19 @|StepIcon #fIcon
Sk0 f20 expr out1 #txt
Sk0 f20 274 128 96 180 #arcP
Sk0 f20 1 96 128 #addKink
Sk0 f20 0 0.8119307153972276 0 0 #arcLabel
Sk0 f2 expr out #txt
Sk0 f2 96 204 96 244 #arcP
Sk0 f21 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Sk0 f21 actionTable 'out=in;
' #txt
Sk0 f21 actionCode 'in.processSearchCriteria.involvedUsername = in.username;
in.processSearchCriteria.keyword = in.keyword;' #txt
Sk0 f21 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Sk0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sk0 f21 270 180 36 24 20 -2 #rect
Sk0 f21 @|StepIcon #fIcon
Sk0 f22 expr out2 #txt
Sk0 f22 288 142 288 180 #arcP
Sk0 f22 0 0.49685312434755724 0 0 #arcLabel
Sk0 f12 expr out #txt
Sk0 f12 288 204 288 244 #arcP
>Proto Sk0 .type ch.ivy.addon.portal.generic.GlobalSearchData #txt
>Proto Sk0 .processKind NORMAL #txt
>Proto Sk0 0 0 32 24 18 0 #rect
>Proto Sk0 @|BIcon #fIcon
Sk0 f5 mainOut f9 tail #connect
Sk0 f9 head f4 mainIn #connect
Sk0 f6 mainOut f1 tail #connect
Sk0 f1 head f0 in #connect
Sk0 f3 mainOut f10 tail #connect
Sk0 f10 head f7 in #connect
Sk0 f7 mainOut f8 tail #connect
Sk0 f8 head f5 mainIn #connect
Sk0 f11 mainOut f13 tail #connect
Sk0 f13 head f7 in #connect
Sk0 f14 mainOut f16 tail #connect
Sk0 f16 head f7 in #connect
Sk0 f18 head f17 mainIn #connect
Sk0 f17 mainOut f15 tail #connect
Sk0 f15 head f14 mainIn #connect
Sk0 f0 out f20 tail #connect
Sk0 f20 head f19 mainIn #connect
Sk0 f19 mainOut f2 tail #connect
Sk0 f2 head f3 mainIn #connect
Sk0 f0 out f22 tail #connect
Sk0 f22 head f21 mainIn #connect
Sk0 f0 out f18 tail #connect
Sk0 f21 mainOut f12 tail #connect
Sk0 f12 head f11 mainIn #connect
