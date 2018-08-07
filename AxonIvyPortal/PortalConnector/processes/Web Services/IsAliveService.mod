[Ivy]
[>Created: Thu Jun 09 17:27:56 ICT 2016]
14684B7E15F81BA6 3.18 #module
>Proto >Proto Collection #zClass
Ie0 IsAliveService Big #zClass
Ie0 WS #cInfo
Ie0 #process
Ie0 @TextInP .webServiceName .webServiceName #zField
Ie0 @TextInP .implementationClassName .implementationClassName #zField
Ie0 @TextInP .authenticationType .authenticationType #zField
Ie0 @TextInP .resExport .resExport #zField
Ie0 @TextInP .type .type #zField
Ie0 @TextInP .processKind .processKind #zField
Ie0 @AnnotationInP-0n ai ai #zField
Ie0 @MessageFlowInP-0n messageIn messageIn #zField
Ie0 @MessageFlowOutP-0n messageOut messageOut #zField
Ie0 @TextInP .xml .xml #zField
Ie0 @TextInP .responsibility .responsibility #zField
Ie0 @StartWS ws0 '' #zField
Ie0 @GridStep f1 '' #zField
Ie0 @PushWFArc f2 '' #zField
Ie0 @CallSub f59 '' #zField
Ie0 @Alternative f58 '' #zField
Ie0 @EndWS f35 '' #zField
Ie0 @PushWFArc f60 '' #zField
Ie0 @PushWFArc f61 '' #zField
Ie0 @PushWFArc f3 '' #zField
>Proto Ie0 Ie0 IsAliveService #zField
Ie0 ws0 inParamDecl '<List<java.lang.String> apps> param;' #txt
Ie0 ws0 inParamTable 'out.apps=param.apps;
' #txt
Ie0 ws0 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyApplication> applicationList,java.lang.Boolean isAlive> result;
' #txt
Ie0 ws0 outParamTable 'result.errors=in.errors;
result.applicationList=in.applicationList;
result.isAlive=in.isAlive;
' #txt
Ie0 ws0 actionDecl 'ch.ivy.ws.addon.IsAliveServiceData out;
' #txt
Ie0 ws0 callSignature isAlive(List<String>) #txt
Ie0 ws0 useUserDefinedException false #txt
Ie0 ws0 taskData TaskTriggered.PRI=2 #txt
Ie0 ws0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ie0 ws0 type ch.ivy.ws.addon.IsAliveServiceData #txt
Ie0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>isAlive(List&lt;String&gt;)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 ws0 @C|.responsibility Everybody #txt
Ie0 ws0 51 51 26 26 14 0 #rect
Ie0 ws0 @|StartWSIcon #fIcon
Ie0 ws0 -13016147|-1|-16777216 #nodeStyle
Ie0 f1 actionDecl 'ch.ivy.ws.addon.IsAliveServiceData out;
' #txt
Ie0 f1 actionTable 'out=in;
' #txt
Ie0 f1 actionCode 'import ch.ivy.ws.addon.bo.IsAliveServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;
try{
	in.isAlive = true;
		
	//in.applicationList = WsServiceFactory.getIsAliveService().isActive(in.apps);
	IsAliveServiceResult iasResult = WsServiceFactory.getIsAliveService().isActive(in.apps);
	in.applicationList = iasResult.getApps();
	in.errors = iasResult.getErrors();

	
} catch(WSException e){
	in.errors.add(e);
}' #txt
Ie0 f1 type ch.ivy.ws.addon.IsAliveServiceData #txt
Ie0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>isAlive</name>
        <nameStyle>7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f1 46 132 36 24 20 -2 #rect
Ie0 f1 @|StepIcon #fIcon
Ie0 f1 -13016147|-1|-16777216 #nodeStyle
Ie0 f2 expr out #txt
Ie0 f2 64 77 64 132 #arcP
Ie0 f59 type ch.ivy.ws.addon.IsAliveServiceData #txt
Ie0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Ie0 f59 doCall true #txt
Ie0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Ie0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Ie0 f59 responseActionDecl 'ch.ivy.ws.addon.IsAliveServiceData out;
' #txt
Ie0 f59 responseMappingAction 'out=in;
' #txt
Ie0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f59 46 284 36 24 20 -2 #rect
Ie0 f59 @|CallSubIcon #fIcon
Ie0 f58 type ch.ivy.ws.addon.IsAliveServiceData #txt
Ie0 f58 50 218 28 28 14 0 #rect
Ie0 f58 @|AlternativeIcon #fIcon
Ie0 f35 type ch.ivy.ws.addon.IsAliveServiceData #txt
Ie0 f35 51 355 26 26 14 0 #rect
Ie0 f35 @|EndWSIcon #fIcon
Ie0 f60 expr in #txt
Ie0 f60 64 246 64 284 #arcP
Ie0 f61 expr out #txt
Ie0 f61 64 308 64 355 #arcP
Ie0 f3 expr out #txt
Ie0 f3 64 156 64 218 #arcP
>Proto Ie0 .webServiceName ch.ivy.ws.addon.IsAliveService #txt
>Proto Ie0 .authenticationType 'HTTP Basic' #txt
>Proto Ie0 .type ch.ivy.ws.addon.IsAliveServiceData #txt
>Proto Ie0 .processKind WEB_SERVICE #txt
>Proto Ie0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ie0 -8 -8 16 16 16 26 #rect
>Proto Ie0 '' #fIcon
Ie0 ws0 mainOut f2 tail #connect
Ie0 f2 head f1 mainIn #connect
Ie0 f58 out f60 tail #connect
Ie0 f60 head f59 mainIn #connect
Ie0 f59 mainOut f61 tail #connect
Ie0 f61 head f35 mainIn #connect
Ie0 f1 mainOut f3 tail #connect
Ie0 f3 head f58 in #connect
