[Ivy]
178ED537303DFF8E 9.3.1 #module
>Proto >Proto Collection #zClass
Es0 ExampleApplicationProcesses Big #zClass
Es0 B #cInfo
Es0 #process
Es0 @AnnotationInP-0n ai ai #zField
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @StartRequest f0 '' #zField
Es0 @EndTask f1 '' #zField
Es0 @GridStep f3 '' #zField
Es0 @PushWFArc f4 '' #zField
Es0 @PushWFArc f2 '' #zField
>Proto Es0 Es0 ExampleApplicationProcesses #zField
Es0 f0 outLink start.ivp #txt
Es0 f0 inParamDecl '<> param;' #txt
Es0 f0 requestEnabled true #txt
Es0 f0 triggerEnabled false #txt
Es0 f0 callSignature start() #txt
Es0 f0 startName 'Example Application Processes' #txt
Es0 f0 caseData businessCase.attach=true #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Es0 f0 @C|.responsibility Everybody #txt
Es0 f0 81 49 30 30 -21 17 #rect
Es0 f1 337 49 30 30 0 15 #rect
Es0 f3 actionTable 'out=in;
' #txt
Es0 f3 actionCode 'import ch.ivy.addon.portalkit.service.UserProcessService;
String appName = ivy.wf.getApplication().getName();
String defaultUserProcesses =
    "[{\"id\":\"1\",\"processType\":\"IVY_PROCESS\",\"processName\":\"Alpha Company Task\",\"icon\":\"fa-building\",\"processId\":\""+ appName + 
    "/portal-developer-examples/Start Processes/ProcessHistoryComponent/createAlphaCompany.ivp\",\"index\":1},{\"id\":\"2\",\"processType\":\"IVY_PROCESS\",\"processName\":\"View Alpha Process History\",\"icon\":\"fa-list-alt\",\"processId\":\"" + appName + 
    "/portal-developer-examples/Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompany.ivp\",\"index\":2},{\"id\":\"3\",\"processType\":\"IVY_PROCESS\",\"processName\":\"View Alpha Process History in Dialog\",\"icon\":\"fa-asterisk\",\"processId\":\"" + appName +
    "/portal-developer-examples/Start Processes/ProcessHistoryComponent/viewProcessHistoryOfAlphaCompanyInDialog.ivp\",\"index\":3},{\"id\":\"4\",\"processType\":\"EXPRESS_PROCESS\",\"processName\":\"Favorite Express Process Display Name\",\"icon\":\"fa fa-play\",\"processId\":\"Your Express Process Name\",\"index\":4},{\"id\":\"5\",\"processType\":\"EXTERNAL_LINK\",\"processName\":\"Favorite External Process Display Name\",\"icon\":\"fa fa-play\",\"processId\":\"Your External Link Name\",\"index\":5,\"link\":\"https://www.axonactive.com\"}]";
ivy.var.set(UserProcessService.getInstance().getConfigKey(), defaultUserProcesses);' #txt
Es0 f3 168 42 112 44 0 -8 #rect
Es0 f4 111 64 168 64 #arcP
Es0 f2 280 64 337 64 #arcP
>Proto Es0 .type com.axonivy.portal.developerexamples.ExampleApplicationProcessesData #txt
>Proto Es0 .processKind NORMAL #txt
>Proto Es0 0 0 32 24 18 0 #rect
>Proto Es0 @|BIcon #fIcon
Es0 f0 mainOut f4 tail #connect
Es0 f4 head f3 mainIn #connect
Es0 f3 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
