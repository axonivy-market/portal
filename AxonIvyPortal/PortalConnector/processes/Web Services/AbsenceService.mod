[Ivy]
[>Created: Thu Jun 09 17:27:16 ICT 2016]
14EFD6DC6E4A27E6 3.18 #module
>Proto >Proto Collection #zClass
Ae0 AbsenceService Big #zClass
Ae0 WS #cInfo
Ae0 #process
Ae0 @TextInP .webServiceName .webServiceName #zField
Ae0 @TextInP .implementationClassName .implementationClassName #zField
Ae0 @TextInP .authenticationType .authenticationType #zField
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartWS ws0 '' #zField
Ae0 @GridStep f1 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @GridStep f4 '' #zField
Ae0 @StartWS f5 '' #zField
Ae0 @PushWFArc f6 '' #zField
Ae0 @StartWS f8 '' #zField
Ae0 @GridStep f11 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @CallSub f59 '' #zField
Ae0 @Alternative f58 '' #zField
Ae0 @EndWS f35 '' #zField
Ae0 @PushWFArc f60 '' #zField
Ae0 @PushWFArc f61 '' #zField
Ae0 @PushWFArc f13 '' #zField
Ae0 @PushWFArc f0 '' #zField
Ae0 @PushWFArc f7 '' #zField
>Proto Ae0 Ae0 AbsenceService #zField
Ae0 ws0 inParamDecl '<List<java.lang.String> applicationNames> param;' #txt
Ae0 ws0 inParamTable 'out.applicationNames=param.applicationNames;
' #txt
Ae0 ws0 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,java.util.List<ch.ivy.ws.addon.types.Absence> absences> result;
' #txt
Ae0 ws0 outParamTable 'result.errors=in.errors;
result.absences=in.absences;
' #txt
Ae0 ws0 actionDecl 'ch.ivy.ws.addon.AbsenceServiceData out;
' #txt
Ae0 ws0 callSignature getAbsencesOfAllUsers(List<String>) #txt
Ae0 ws0 useUserDefinedException false #txt
Ae0 ws0 taskData TaskTriggered.PRI=2 #txt
Ae0 ws0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 ws0 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getAbsencesOfAllUsers
(List&lt;String&gt;)</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 ws0 @C|.responsibility Everybody #txt
Ae0 ws0 51 51 26 26 14 0 #rect
Ae0 ws0 @|StartWSIcon #fIcon
Ae0 f1 actionDecl 'ch.ivy.ws.addon.AbsenceServiceData out;
' #txt
Ae0 f1 actionTable 'out=in;
' #txt
Ae0 f1 actionCode 'import ch.ivy.ws.addon.bo.AbsenceServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try {
	AbsenceServiceResult absenceServiceResult = WsServiceFactory.getAbsenceService().getAbsences(in.applicationNames);
	in.absences = absenceServiceResult.getAbsences();
	in.errors = absenceServiceResult.getErrors();
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ae0 f1 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f1 46 132 36 24 20 -2 #rect
Ae0 f1 @|StepIcon #fIcon
Ae0 f2 expr out #txt
Ae0 f2 64 77 64 132 #arcP
Ae0 f4 actionDecl 'ch.ivy.ws.addon.AbsenceServiceData out;
' #txt
Ae0 f4 actionTable 'out=in;
' #txt
Ae0 f4 actionCode 'import ch.ivy.ws.addon.bo.AbsenceServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try {
	AbsenceServiceResult absenceServiceResult = WsServiceFactory.getAbsenceService().getAbsences(in.userName, in.applicationNames);
	in.absences = absenceServiceResult.getAbsences();
	in.errors = absenceServiceResult.getErrors();
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ae0 f4 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f4 238 132 36 24 20 -2 #rect
Ae0 f4 @|StepIcon #fIcon
Ae0 f5 inParamDecl '<java.lang.String userName,List<java.lang.String> applicationNames> param;' #txt
Ae0 f5 inParamTable 'out.applicationNames=param.applicationNames;
out.userName=param.userName;
' #txt
Ae0 f5 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,java.util.List<ch.ivy.ws.addon.types.Absence> absences> result;
' #txt
Ae0 f5 outParamTable 'result.errors=in.errors;
result.absences=in.absences;
' #txt
Ae0 f5 actionDecl 'ch.ivy.ws.addon.AbsenceServiceData out;
' #txt
Ae0 f5 callSignature getAbsences(String,List<String>) #txt
Ae0 f5 useUserDefinedException false #txt
Ae0 f5 taskData TaskTriggered.PRI=2 #txt
Ae0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f5 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getAbsences
(String,List&lt;String&gt;)</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f5 @C|.responsibility Everybody #txt
Ae0 f5 243 51 26 26 14 0 #rect
Ae0 f5 @|StartWSIcon #fIcon
Ae0 f6 expr out #txt
Ae0 f6 256 77 256 132 #arcP
Ae0 f8 inParamDecl '<java.lang.String userName,List<java.lang.String> applicationNames,List<ch.ivy.ws.addon.types.IvyAbsence> absencesToUpdate> param;' #txt
Ae0 f8 inParamTable 'out.absencesToUpdate=param.absencesToUpdate;
out.applicationNames=param.applicationNames;
out.userName=param.userName;
' #txt
Ae0 f8 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ae0 f8 outParamTable 'result.errors=in.errors;
' #txt
Ae0 f8 actionDecl 'ch.ivy.ws.addon.AbsenceServiceData out;
' #txt
Ae0 f8 callSignature setAbsences(String,List<String>,List<ch.ivy.ws.addon.types.IvyAbsence>) #txt
Ae0 f8 useUserDefinedException false #txt
Ae0 f8 taskData TaskTriggered.PRI=2 #txt
Ae0 f8 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f8 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setAbsences
(String,List&lt;String&gt;,List&lt;IvyAbsence&gt;)</name>
        <nameStyle>50,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f8 @C|.responsibility Everybody #txt
Ae0 f8 435 51 26 26 15 -5 #rect
Ae0 f8 @|StartWSIcon #fIcon
Ae0 f11 actionDecl 'ch.ivy.ws.addon.AbsenceServiceData out;
' #txt
Ae0 f11 actionTable 'out=in;
' #txt
Ae0 f11 actionCode 'import ch.ivy.ws.addon.bo.AbsenceServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try {
	AbsenceServiceResult result = WsServiceFactory.getAbsenceService().setAbsences(in.applicationNames, in.userName, in.absencesToUpdate);
	in.errors = result.errors;
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ae0 f11 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setAbsences</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f11 430 132 36 24 20 -2 #rect
Ae0 f11 @|StepIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 448 77 448 132 #arcP
Ae0 f59 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Ae0 f59 doCall true #txt
Ae0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Ae0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Ae0 f59 responseActionDecl 'ch.ivy.ws.addon.AbsenceServiceData out;
' #txt
Ae0 f59 responseMappingAction 'out=in;
' #txt
Ae0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f59 238 268 36 24 20 -2 #rect
Ae0 f59 @|CallSubIcon #fIcon
Ae0 f58 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f58 242 202 28 28 14 0 #rect
Ae0 f58 @|AlternativeIcon #fIcon
Ae0 f35 type ch.ivy.ws.addon.AbsenceServiceData #txt
Ae0 f35 243 339 26 26 14 0 #rect
Ae0 f35 @|EndWSIcon #fIcon
Ae0 f60 expr in #txt
Ae0 f60 256 230 256 268 #arcP
Ae0 f61 expr out #txt
Ae0 f61 256 292 256 339 #arcP
Ae0 f13 expr out #txt
Ae0 f13 64 156 242 216 #arcP
Ae0 f13 1 64 216 #addKink
Ae0 f13 1 0.24633744540935054 0 0 #arcLabel
Ae0 f0 expr out #txt
Ae0 f0 256 156 256 202 #arcP
Ae0 f7 expr out #txt
Ae0 f7 448 156 270 216 #arcP
Ae0 f7 1 448 216 #addKink
Ae0 f7 1 0.2880492640399535 0 0 #arcLabel
>Proto Ae0 .webServiceName ch.ivy.ws.addon.AbsenceService #txt
>Proto Ae0 .authenticationType 'HTTP Basic' #txt
>Proto Ae0 .type ch.ivy.ws.addon.AbsenceServiceData #txt
>Proto Ae0 .processKind WEB_SERVICE #txt
>Proto Ae0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
</elementInfo>
' #txt
>Proto Ae0 -8 -8 16 16 16 26 #rect
>Proto Ae0 '' #fIcon
Ae0 ws0 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f5 mainOut f6 tail #connect
Ae0 f6 head f4 mainIn #connect
Ae0 f8 mainOut f12 tail #connect
Ae0 f12 head f11 mainIn #connect
Ae0 f58 out f60 tail #connect
Ae0 f60 head f59 mainIn #connect
Ae0 f59 mainOut f61 tail #connect
Ae0 f61 head f35 mainIn #connect
Ae0 f1 mainOut f13 tail #connect
Ae0 f13 head f58 in #connect
Ae0 f4 mainOut f0 tail #connect
Ae0 f0 head f58 in #connect
Ae0 f11 mainOut f7 tail #connect
Ae0 f7 head f58 in #connect
