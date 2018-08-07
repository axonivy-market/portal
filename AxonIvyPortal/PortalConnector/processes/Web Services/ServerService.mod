[Ivy]
[>Created: Tue Jul 05 11:40:50 ICT 2016]
155B8D3292AB7784 3.18 #module
>Proto >Proto Collection #zClass
Se0 ServerService Big #zClass
Se0 WS #cInfo
Se0 #process
Se0 @TextInP .webServiceName .webServiceName #zField
Se0 @TextInP .implementationClassName .implementationClassName #zField
Se0 @TextInP .authenticationType .authenticationType #zField
Se0 @TextInP .resExport .resExport #zField
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @MessageFlowInP-0n messageIn messageIn #zField
Se0 @MessageFlowOutP-0n messageOut messageOut #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @StartWS ws0 '' #zField
Se0 @EndWS ws1 '' #zField
Se0 @GridStep f2 '' #zField
Se0 @PushWFArc f3 '' #zField
Se0 @ProcessException f4 '' #zField
Se0 @CallSub f5 '' #zField
Se0 @EndWS f6 '' #zField
Se0 @PushWFArc f7 '' #zField
Se0 @PushWFArc f8 '' #zField
Se0 @PushWFArc f9 '' #zField
>Proto Se0 Se0 ServerService #zField
Se0 ws0 inParamDecl '<> param;' #txt
Se0 ws0 outParamDecl '<java.lang.String externalHost,List<ch.ivy.ws.addon.WSException> erros> result;
' #txt
Se0 ws0 outParamTable 'result.externalHost=in.externalHost;
result.erros=in.errors;
' #txt
Se0 ws0 actionDecl 'ch.ivy.ws.addon.ServerServiceData out;
' #txt
Se0 ws0 callSignature getExternalHost() #txt
Se0 ws0 useUserDefinedException false #txt
Se0 ws0 taskData TaskTriggered.PRI=2 #txt
Se0 ws0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Se0 ws0 type ch.ivy.ws.addon.ServerServiceData #txt
Se0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getExternalHost()</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 ws0 @C|.responsibility Everybody #txt
Se0 ws0 155 147 26 26 14 0 #rect
Se0 ws0 @|StartWSIcon #fIcon
Se0 ws1 type ch.ivy.ws.addon.ServerServiceData #txt
Se0 ws1 155 307 26 26 14 0 #rect
Se0 ws1 @|EndWSIcon #fIcon
Se0 f2 actionDecl 'ch.ivy.ws.addon.ServerServiceData out;
' #txt
Se0 f2 actionTable 'out=in;
' #txt
Se0 f2 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
in.externalHost = WsServiceFactory.getServerService().getExternalHost();' #txt
Se0 f2 type ch.ivy.ws.addon.ServerServiceData #txt
Se0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get external host</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f2 150 236 36 24 20 -2 #rect
Se0 f2 @|StepIcon #fIcon
Se0 f3 expr out #txt
Se0 f3 168 173 168 236 #arcP
Se0 f4 .resExport export #txt
Se0 f4 actionDecl 'ch.ivy.ws.addon.ServerServiceData out;
' #txt
Se0 f4 actionTable 'out=in;
' #txt
Se0 f4 actionCode 'import ch.ivy.ws.addon.WSException;
in.errors.add(new WSException(10006, error));' #txt
Se0 f4 type ch.ivy.ws.addon.ServerServiceData #txt
Se0 f4 errorCode ivy:portalconnector:error:serverservice #txt
Se0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ivy:portalconnector:error:serverservice</name>
    </language>
</elementInfo>
' #txt
Se0 f4 387 147 26 26 14 0 #rect
Se0 f4 @|ExceptionIcon #fIcon
Se0 f5 type ch.ivy.ws.addon.ServerServiceData #txt
Se0 f5 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Se0 f5 doCall true #txt
Se0 f5 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Se0 f5 requestMappingAction 'param.errors=in.errors;
' #txt
Se0 f5 responseActionDecl 'ch.ivy.ws.addon.ServerServiceData out;
' #txt
Se0 f5 responseMappingAction 'out=in;
' #txt
Se0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f5 382 236 36 24 20 -2 #rect
Se0 f5 @|CallSubIcon #fIcon
Se0 f6 type ch.ivy.ws.addon.ServerServiceData #txt
Se0 f6 387 307 26 26 14 0 #rect
Se0 f6 @|EndWSIcon #fIcon
Se0 f7 expr out #txt
Se0 f7 400 260 400 307 #arcP
Se0 f8 expr out #txt
Se0 f8 400 173 400 236 #arcP
Se0 f9 expr out #txt
Se0 f9 168 260 168 307 #arcP
>Proto Se0 .webServiceName ch.ivy.ws.addon.ServerService #txt
>Proto Se0 .authenticationType 'HTTP Basic' #txt
>Proto Se0 .type ch.ivy.ws.addon.ServerServiceData #txt
>Proto Se0 .processKind WEB_SERVICE #txt
>Proto Se0 -8 -8 16 16 16 26 #rect
>Proto Se0 '' #fIcon
Se0 ws0 mainOut f3 tail #connect
Se0 f3 head f2 mainIn #connect
Se0 f5 mainOut f7 tail #connect
Se0 f7 head f6 mainIn #connect
Se0 f4 mainOut f8 tail #connect
Se0 f8 head f5 mainIn #connect
Se0 f2 mainOut f9 tail #connect
Se0 f9 head ws1 mainIn #connect
