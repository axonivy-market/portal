[Ivy]
[>Created: Thu Jun 09 17:27:02 ICT 2016]
150374D567D113F5 3.18 #module
>Proto >Proto Collection #zClass
Pe0 PortalDataService Big #zClass
Pe0 WS #cInfo
Pe0 #process
Pe0 @TextInP .webServiceName .webServiceName #zField
Pe0 @TextInP .implementationClassName .implementationClassName #zField
Pe0 @TextInP .authenticationType .authenticationType #zField
Pe0 @TextInP .resExport .resExport #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @MessageFlowInP-0n messageIn messageIn #zField
Pe0 @MessageFlowOutP-0n messageOut messageOut #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartWS f2 '' #zField
Pe0 @EndWS f1 '' #zField
Pe0 @GridStep f3 '' #zField
Pe0 @PushWFArc f4 '' #zField
Pe0 @StartWS f0 '' #zField
Pe0 @EndWS f7 '' #zField
Pe0 @GridStep f9 '' #zField
Pe0 @PushWFArc f10 '' #zField
Pe0 @PushWFArc f8 '' #zField
Pe0 @GridStep f12 '' #zField
Pe0 @EndWS f13 '' #zField
Pe0 @StartWS f14 '' #zField
Pe0 @PushWFArc f15 '' #zField
Pe0 @PushWFArc f16 '' #zField
Pe0 @PushWFArc f6 '' #zField
Pe0 @ErrorBoundaryEvent Et0 ErrorBoundaryEvent #zField
Pe0 @ErrorBoundaryEvent Et1 ErrorBoundaryEvent #zField
Pe0 @ErrorBoundaryEvent Et2 ErrorBoundaryEvent #zField
Pe0 @StartWS f18 '' #zField
Pe0 @EndWS f19 '' #zField
Pe0 @GridStep f21 '' #zField
Pe0 @PushWFArc f22 '' #zField
Pe0 @PushWFArc f20 '' #zField
Pe0 @ErrorBoundaryEvent Et3 ErrorBoundaryEvent #zField
Pe0 @PushWFArc f17 '' #zField
Pe0 @PushWFArc f23 '' #zField
Pe0 @PushWFArc f11 '' #zField
Pe0 @PushWFArc f5 '' #zField
>Proto Pe0 Pe0 PortalDataService #zField
Pe0 f2 inParamDecl '<List<ch.ivy.ws.portaldata.model.CustomPropertyPair> customPropertyPairs> param;' #txt
Pe0 f2 inParamTable 'out.customPropertyPairs=param.customPropertyPairs;
' #txt
Pe0 f2 outParamDecl '<java.lang.String message,ch.ivy.ws.addon.ReturnedStatus status> result;
' #txt
Pe0 f2 outParamTable 'result.message=in.message;
result.status=in.returnedStatus;
' #txt
Pe0 f2 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f2 callSignature addOrUpdate(List<ch.ivy.ws.portaldata.model.CustomPropertyPair>) #txt
Pe0 f2 useUserDefinedException false #txt
Pe0 f2 taskData TaskTriggered.PRI=2 #txt
Pe0 f2 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Pe0 f2 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addOrUpdate(List&lt;CustomPropertyPair&gt;)</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f2 @C|.responsibility Everybody #txt
Pe0 f2 83 91 26 26 17 -1 #rect
Pe0 f2 @|StartWSIcon #fIcon
Pe0 f1 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f1 83 355 26 26 14 0 #rect
Pe0 f1 @|EndWSIcon #fIcon
Pe0 f3 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f3 actionTable 'out=in;
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.SUCCESSFUL;
' #txt
Pe0 f3 actionCode 'import ch.ivy.ws.portaldata.service.PortalDataService;

PortalDataService service = new PortalDataService();
service.addOrUpdate(in.customPropertyPairs);' #txt
Pe0 f3 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add or update property</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f3 78 204 36 24 20 -2 #rect
Pe0 f3 @|StepIcon #fIcon
Pe0 f4 expr out #txt
Pe0 f4 96 117 96 204 #arcP
Pe0 f0 inParamDecl '<java.lang.String propertyKeyToBeDeleted> param;' #txt
Pe0 f0 inParamTable 'out.propertyKeyToBeDelete=param.propertyKeyToBeDeleted;
' #txt
Pe0 f0 outParamDecl '<java.lang.String message,ch.ivy.ws.addon.ReturnedStatus returnedStatus> result;
' #txt
Pe0 f0 outParamTable 'result.message=in.message;
result.returnedStatus=in.returnedStatus;
' #txt
Pe0 f0 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f0 callSignature delete(String) #txt
Pe0 f0 useUserDefinedException false #txt
Pe0 f0 taskData TaskTriggered.PRI=2 #txt
Pe0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Pe0 f0 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete(String)</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f0 @C|.responsibility Everybody #txt
Pe0 f0 379 91 26 26 14 0 #rect
Pe0 f0 @|StartWSIcon #fIcon
Pe0 f7 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f7 379 347 26 26 14 0 #rect
Pe0 f7 @|EndWSIcon #fIcon
Pe0 f9 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f9 actionTable 'out=in;
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.SUCCESSFUL;
' #txt
Pe0 f9 actionCode 'import ch.ivy.ws.portaldata.service.PortalDataService;

PortalDataService service = new PortalDataService();
service.delete(in.propertyKeyToBeDelete);' #txt
Pe0 f9 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete</name>
        <nameStyle>6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f9 374 204 36 24 20 -2 #rect
Pe0 f9 @|StepIcon #fIcon
Pe0 f10 expr out #txt
Pe0 f10 392 117 392 204 #arcP
Pe0 f8 expr out #txt
Pe0 f8 392 228 392 347 #arcP
Pe0 f12 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f12 actionTable 'out=in;
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.SUCCESSFUL;
' #txt
Pe0 f12 actionCode 'import ch.ivy.ws.portaldata.service.PortalDataService;

PortalDataService service = new PortalDataService();
service.deleteByPrefix(in.keyPrefixToBeDeleted);' #txt
Pe0 f12 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete by prefix</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f12 766 204 36 24 20 -2 #rect
Pe0 f12 @|StepIcon #fIcon
Pe0 f13 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f13 771 347 26 26 14 0 #rect
Pe0 f13 @|EndWSIcon #fIcon
Pe0 f14 inParamDecl '<java.lang.String keyPrefixToBeDeleted> param;' #txt
Pe0 f14 inParamTable 'out.keyPrefixToBeDeleted=param.keyPrefixToBeDeleted;
' #txt
Pe0 f14 outParamDecl '<java.lang.String message,ch.ivy.ws.addon.ReturnedStatus returnedStatus> result;
' #txt
Pe0 f14 outParamTable 'result.message=in.message;
result.returnedStatus=in.returnedStatus;
' #txt
Pe0 f14 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f14 callSignature deleteByPrefix(String) #txt
Pe0 f14 useUserDefinedException false #txt
Pe0 f14 taskData TaskTriggered.PRI=2 #txt
Pe0 f14 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Pe0 f14 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteByPrefix(String)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f14 @C|.responsibility Everybody #txt
Pe0 f14 771 91 26 26 14 0 #rect
Pe0 f14 @|StartWSIcon #fIcon
Pe0 f15 expr out #txt
Pe0 f15 784 117 784 204 #arcP
Pe0 f16 expr out #txt
Pe0 f16 784 228 784 347 #arcP
Pe0 f6 expr out #txt
Pe0 f6 96 228 96 355 #arcP
Pe0 Et0 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 Et0 actionTable 'out=in;
out.message=error.stackTrace.toString();
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.ERROR;
' #txt
Pe0 Et0 actionCode 'ivy.log.error("Error", error);' #txt
Pe0 Et0 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 Et0 attachedToRef 150374D567D113F5-f3 #txt
Pe0 Et0 77 225 26 26 14 0 #rect
Pe0 Et0 @|ErrorBoundaryEventIcon #fIcon
Pe0 Et1 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 Et1 actionTable 'out=in;
out.message=error.stackTrace.toString();
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.ERROR;
' #txt
Pe0 Et1 actionCode 'ivy.log.error("Error", error);' #txt
Pe0 Et1 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 Et1 attachedToRef 150374D567D113F5-f9 #txt
Pe0 Et1 349 225 26 26 14 0 #rect
Pe0 Et1 @|ErrorBoundaryEventIcon #fIcon
Pe0 Et2 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 Et2 actionTable 'out=in;
out.message=error.stackTrace.toString();
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.ERROR;
' #txt
Pe0 Et2 actionCode 'ivy.log.error("Error", error);' #txt
Pe0 Et2 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 Et2 attachedToRef 150374D567D113F5-f12 #txt
Pe0 Et2 765 225 26 26 14 0 #rect
Pe0 Et2 @|ErrorBoundaryEventIcon #fIcon
Pe0 f18 inParamDecl '<java.util.List<java.lang.String> propertyKeysToBeDeleted> param;' #txt
Pe0 f18 inParamTable 'out.propertyKeysToBeDeleted=param.propertyKeysToBeDeleted;
' #txt
Pe0 f18 outParamDecl '<java.lang.String message,ch.ivy.ws.addon.ReturnedStatus returnedStatus> result;
' #txt
Pe0 f18 outParamTable 'result.message=in.message;
result.returnedStatus=in.returnedStatus;
' #txt
Pe0 f18 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f18 callSignature deleteManyProperties(List<String>) #txt
Pe0 f18 useUserDefinedException false #txt
Pe0 f18 taskData TaskTriggered.PRI=2 #txt
Pe0 f18 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Pe0 f18 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteManyProperties(List&lt;String&gt;)</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f18 @C|.responsibility Everybody #txt
Pe0 f18 523 91 26 26 14 0 #rect
Pe0 f18 @|StartWSIcon #fIcon
Pe0 f19 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f19 523 339 26 26 14 0 #rect
Pe0 f19 @|EndWSIcon #fIcon
Pe0 f21 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 f21 actionTable 'out=in;
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.SUCCESSFUL;
' #txt
Pe0 f21 actionCode 'import ch.ivy.ws.portaldata.service.PortalDataService;

PortalDataService service = new PortalDataService();
service.delete(in.propertyKeysToBeDeleted);' #txt
Pe0 f21 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete properties</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f21 518 196 36 24 20 -2 #rect
Pe0 f21 @|StepIcon #fIcon
Pe0 f22 expr out #txt
Pe0 f22 536 117 536 196 #arcP
Pe0 f20 expr out #txt
Pe0 f20 536 220 536 339 #arcP
Pe0 Et3 actionDecl 'ch.ivy.ws.protaldata.PortalDataServiceData out;
' #txt
Pe0 Et3 actionTable 'out=in;
out.message=error.stackTrace.toString();
out.returnedStatus=ch.ivy.ws.addon.ReturnedStatus.ERROR;
' #txt
Pe0 Et3 actionCode 'ivy.log.error("Error", error);' #txt
Pe0 Et3 type ch.ivy.ws.protaldata.PortalDataServiceData #txt
Pe0 Et3 attachedToRef 150374D567D113F5-f21 #txt
Pe0 Et3 517 217 26 26 14 0 #rect
Pe0 Et3 @|ErrorBoundaryEventIcon #fIcon
Pe0 f17 765 238 771 360 #arcP
Pe0 f17 1 696 238 #addKink
Pe0 f17 2 696 360 #addKink
Pe0 f17 1 0.27468397468816397 0 0 #arcLabel
Pe0 f23 543 230 549 352 #arcP
Pe0 f23 1 584 230 #addKink
Pe0 f23 2 584 352 #addKink
Pe0 f23 1 0.6116150052245584 0 0 #arcLabel
Pe0 f11 375 238 405 360 #arcP
Pe0 f11 1 464 238 #addKink
Pe0 f11 2 464 360 #addKink
Pe0 f11 1 0.3062640802753718 0 0 #arcLabel
Pe0 f5 103 238 109 368 #arcP
Pe0 f5 1 184 238 #addKink
Pe0 f5 2 184 368 #addKink
Pe0 f5 1 0.2867046013300879 0 0 #arcLabel
>Proto Pe0 .webServiceName ch.ivy.ws.addon.PortalDataService #txt
>Proto Pe0 .authenticationType 'HTTP Basic' #txt
>Proto Pe0 .type ch.ivy.ws.protaldata.PortalDataServiceData #txt
>Proto Pe0 .processKind WEB_SERVICE #txt
>Proto Pe0 -8 -8 16 16 16 26 #rect
>Proto Pe0 '' #fIcon
Pe0 f2 mainOut f4 tail #connect
Pe0 f4 head f3 mainIn #connect
Pe0 f0 mainOut f10 tail #connect
Pe0 f10 head f9 mainIn #connect
Pe0 f9 mainOut f8 tail #connect
Pe0 f8 head f7 mainIn #connect
Pe0 f14 mainOut f15 tail #connect
Pe0 f15 head f12 mainIn #connect
Pe0 f12 mainOut f16 tail #connect
Pe0 f16 head f13 mainIn #connect
Pe0 f3 mainOut f6 tail #connect
Pe0 f6 head f1 mainIn #connect
Pe0 Et0 mainOut f5 tail #connect
Pe0 f5 head f1 mainIn #connect
Pe0 Et1 mainOut f11 tail #connect
Pe0 f11 head f7 mainIn #connect
Pe0 Et2 mainOut f17 tail #connect
Pe0 f17 head f13 mainIn #connect
Pe0 f18 mainOut f22 tail #connect
Pe0 f22 head f21 mainIn #connect
Pe0 f21 mainOut f20 tail #connect
Pe0 f20 head f19 mainIn #connect
Pe0 Et3 mainOut f23 tail #connect
Pe0 f23 head f19 mainIn #connect
