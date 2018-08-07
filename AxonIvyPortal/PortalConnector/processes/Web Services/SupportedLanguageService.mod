[Ivy]
[>Created: Wed Feb 03 16:49:26 ICT 2016]
14E90F0F6A258B63 3.18 #module
>Proto >Proto Collection #zClass
Se0 SupportedLanguageService Big #zClass
Se0 WS #cInfo
Se0 #process
Se0 @TextInP .webServiceName .webServiceName #zField
Se0 @TextInP .implementationClassName .implementationClassName #zField
Se0 @TextInP .authenticationType .authenticationType #zField
Se0 @TextInP .resExport .resExport #zField
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @StartWS ws0 '' #zField
Se0 @GridStep f1 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @CallSub f59 '' #zField
Se0 @EndWS f35 '' #zField
Se0 @PushWFArc f61 '' #zField
Se0 @PushWFArc f0 '' #zField
>Proto Se0 Se0 SupportedLanguageService #zField
Se0 ws0 inParamDecl '<java.lang.String applicationName> param;' #txt
Se0 ws0 inParamTable 'out.applicationName=param.applicationName;
' #txt
Se0 ws0 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<java.lang.String> supportedLanguages> result;
' #txt
Se0 ws0 outParamTable 'result.errors=in.errors;
result.supportedLanguages=in.supportedLanguages;
' #txt
Se0 ws0 actionDecl 'ch.ivy.ws.addon.SupportedLanguageServiceData out;
' #txt
Se0 ws0 callSignature getSupportedLanguages(String) #txt
Se0 ws0 useUserDefinedException false #txt
Se0 ws0 taskData '#
#Mon Jul 20 13:31:51 ICT 2015
TaskTriggered.PRI=2
' #txt
Se0 ws0 caseData '#
#Mon Jul 20 13:31:51 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Se0 ws0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Se0 ws0 type ch.ivy.ws.addon.SupportedLanguageServiceData #txt
Se0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getSupportedLanguages(String)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 ws0 @C|.responsibility Everybody #txt
Se0 ws0 83 35 26 26 14 0 #rect
Se0 ws0 @|StartWSIcon #fIcon
Se0 f1 actionDecl 'ch.ivy.ws.addon.SupportedLanguageServiceData out;
' #txt
Se0 f1 actionTable 'out=in;
' #txt
Se0 f1 actionCode 'import ch.ivy.ws.addon.util.SupportedLanguageLoader;
import ch.ivy.ws.addon.WSException;
try {
	in.supportedLanguages = new SupportedLanguageLoader(in.applicationName).getSupportedLanguages();
} catch(Exception e){
	in.errors.add(new WSException(e));
}
' #txt
Se0 f1 type ch.ivy.ws.addon.SupportedLanguageServiceData #txt
Se0 f1 78 116 36 24 20 -2 #rect
Se0 f1 @|StepIcon #fIcon
Se0 f2 expr out #txt
Se0 f2 96 61 96 116 #arcP
Se0 f59 type ch.ivy.ws.addon.SupportedLanguageServiceData #txt
Se0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Se0 f59 doCall true #txt
Se0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Se0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Se0 f59 responseActionDecl 'ch.ivy.ws.addon.SupportedLanguageServiceData out;
' #txt
Se0 f59 responseMappingAction 'out=in;
' #txt
Se0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f59 78 196 36 24 20 -2 #rect
Se0 f59 @|CallSubIcon #fIcon
Se0 f35 type ch.ivy.ws.addon.SupportedLanguageServiceData #txt
Se0 f35 83 267 26 26 14 0 #rect
Se0 f35 @|EndWSIcon #fIcon
Se0 f61 expr out #txt
Se0 f61 96 220 96 267 #arcP
Se0 f0 expr out #txt
Se0 f0 96 140 96 196 #arcP
>Proto Se0 .webServiceName ch.ivy.ws.addon.SupportedLanguageService #txt
>Proto Se0 .type ch.ivy.ws.addon.SupportedLanguageServiceData #txt
>Proto Se0 .processKind WEB_SERVICE #txt
>Proto Se0 -8 -8 16 16 16 26 #rect
>Proto Se0 '' #fIcon
Se0 ws0 mainOut f2 tail #connect
Se0 f2 head f1 mainIn #connect
Se0 f59 mainOut f61 tail #connect
Se0 f61 head f35 mainIn #connect
Se0 f1 mainOut f0 tail #connect
Se0 f0 head f59 mainIn #connect
