[Ivy]
[>Created: Fri May 27 16:07:46 ICT 2016]
154F054217050170 3.18 #module
>Proto >Proto Collection #zClass
Ae0 ApplicationService Big #zClass
Ae0 WS #cInfo
Ae0 #process
Ae0 @TextInP .webServiceName .webServiceName #zField
Ae0 @TextInP .implementationClassName .implementationClassName #zField
Ae0 @TextInP .authenticationType .authenticationType #zField
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartWS ws0 '' #zField
Ae0 @EndWS ws1 '' #zField
Ae0 @GridStep f1 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @PushWFArc f0 '' #zField
Ae0 @ProcessException f3 '' #zField
Ae0 @CallSub f59 '' #zField
Ae0 @PushWFArc f5 '' #zField
Ae0 @EndWS f4 '' #zField
Ae0 @PushWFArc f6 '' #zField
Ae0 @StartWS f7 '' #zField
Ae0 @EndWS f8 '' #zField
Ae0 @GridStep f10 '' #zField
Ae0 @PushWFArc f11 '' #zField
Ae0 @PushWFArc f9 '' #zField
>Proto Ae0 Ae0 ApplicationService #zField
Ae0 ws0 inParamDecl '<> param;' #txt
Ae0 ws0 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyApplication> applications> result;
' #txt
Ae0 ws0 outParamTable 'result.errors=in.errors;
result.applications=in.applications;
' #txt
Ae0 ws0 actionDecl 'ch.ivy.ws.addon.ApplicationServiceData out;
' #txt
Ae0 ws0 callSignature getAllApplications() #txt
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
Ae0 ws0 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getAllApplications()</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 ws0 @C|.responsibility Everybody #txt
Ae0 ws0 203 99 26 26 14 0 #rect
Ae0 ws0 @|StartWSIcon #fIcon
Ae0 ws1 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 ws1 203 299 26 26 14 0 #rect
Ae0 ws1 @|EndWSIcon #fIcon
Ae0 f1 actionDecl 'ch.ivy.ws.addon.ApplicationServiceData out;
' #txt
Ae0 f1 actionTable 'out=in;
' #txt
Ae0 f1 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;

out.applications = WsServiceFactory.getApplicationService().getAllApplications();' #txt
Ae0 f1 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all active applications
of server</name>
        <nameStyle>37
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f1 136 184 160 48 -60 -20 #rect
Ae0 f1 @|StepIcon #fIcon
Ae0 f2 expr out #txt
Ae0 f2 216 125 216 184 #arcP
Ae0 f0 expr out #txt
Ae0 f0 216 232 216 299 #arcP
Ae0 f3 .resExport export #txt
Ae0 f3 actionDecl 'ch.ivy.ws.addon.ApplicationServiceData out;
' #txt
Ae0 f3 actionTable 'out=in;
' #txt
Ae0 f3 actionCode 'import ch.ivy.ws.addon.WSException;
in.errors.add(new WSException(10006, error));' #txt
Ae0 f3 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 f3 errorCode ivy:portalconnector:error:applicationservice #txt
Ae0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ivy:portalconnector:error:applicationservice</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f3 667 99 26 26 14 0 #rect
Ae0 f3 @|ExceptionIcon #fIcon
Ae0 f59 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Ae0 f59 doCall true #txt
Ae0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Ae0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Ae0 f59 responseActionDecl 'ch.ivy.ws.addon.ApplicationServiceData out;
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
Ae0 f59 662 196 36 24 20 -2 #rect
Ae0 f59 @|CallSubIcon #fIcon
Ae0 f5 expr out #txt
Ae0 f5 680 125 680 196 #arcP
Ae0 f4 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 f4 667 283 26 26 14 0 #rect
Ae0 f4 @|EndWSIcon #fIcon
Ae0 f6 expr out #txt
Ae0 f6 680 220 680 283 #arcP
Ae0 f7 inParamDecl '<List<java.lang.String> applicationNames> param;' #txt
Ae0 f7 inParamTable 'out.applicationNames=param.applicationNames;
' #txt
Ae0 f7 outParamDecl '<List<ch.ivy.ws.addon.types.IvyApplication> applications,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ae0 f7 outParamTable 'result.applications=in.applications;
result.errors=in.errors;
' #txt
Ae0 f7 actionDecl 'ch.ivy.ws.addon.ApplicationServiceData out;
' #txt
Ae0 f7 callSignature getApplicationsByAppNames(List<String>) #txt
Ae0 f7 useUserDefinedException false #txt
Ae0 f7 taskData TaskTriggered.PRI=2 #txt
Ae0 f7 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f7 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getApplicationsByAppNames(List&lt;String&gt;)</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f7 @C|.responsibility Everybody #txt
Ae0 f7 387 99 26 26 14 0 #rect
Ae0 f7 @|StartWSIcon #fIcon
Ae0 f8 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 f8 387 291 26 26 14 0 #rect
Ae0 f8 @|EndWSIcon #fIcon
Ae0 f10 actionDecl 'ch.ivy.ws.addon.ApplicationServiceData out;
' #txt
Ae0 f10 actionTable 'out=in;
' #txt
Ae0 f10 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;

in.applications = WsServiceFactory.getApplicationService().getApplicationsBy(in.applicationNames);' #txt
Ae0 f10 type ch.ivy.ws.addon.ApplicationServiceData #txt
Ae0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get active applications
of server by app names</name>
        <nameStyle>46
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f10 320 186 160 44 -60 -16 #rect
Ae0 f10 @|StepIcon #fIcon
Ae0 f11 expr out #txt
Ae0 f11 400 125 400 186 #arcP
Ae0 f9 expr out #txt
Ae0 f9 400 230 400 291 #arcP
>Proto Ae0 .webServiceName ch.ivy.ws.addon.ApplicationService #txt
>Proto Ae0 .type ch.ivy.ws.addon.ApplicationServiceData #txt
>Proto Ae0 .processKind WEB_SERVICE #txt
>Proto Ae0 -8 -8 16 16 16 26 #rect
>Proto Ae0 '' #fIcon
Ae0 ws0 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f1 mainOut f0 tail #connect
Ae0 f0 head ws1 mainIn #connect
Ae0 f3 mainOut f5 tail #connect
Ae0 f5 head f59 mainIn #connect
Ae0 f59 mainOut f6 tail #connect
Ae0 f6 head f4 mainIn #connect
Ae0 f7 mainOut f11 tail #connect
Ae0 f11 head f10 mainIn #connect
Ae0 f10 mainOut f9 tail #connect
Ae0 f9 head f8 mainIn #connect
