[Ivy]
[>Created: Mon Nov 16 14:57:17 ICT 2015]
1503C5419245E19B 3.18 #module
>Proto >Proto Collection #zClass
Ae0 SynchronizingTriggerProcess Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @EndTask f1 '' #zField
Ae0 @CallSub f3 '' #zField
Ae0 @PushWFArc f4 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @EndTask f8 '' #zField
Ae0 @StartRequest f5 '' #zField
Ae0 @StartRequest f6 '' #zField
Ae0 @EndTask f7 '' #zField
Ae0 @CallSub f11 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @PushWFArc f10 '' #zField
Ae0 @CallSub f13 '' #zField
Ae0 @PushWFArc f14 '' #zField
Ae0 @PushWFArc f9 '' #zField
Ae0 @EndTask f15 '' #zField
Ae0 @StartRequest f16 '' #zField
Ae0 @CallSub f17 '' #zField
Ae0 @PushWFArc f18 '' #zField
Ae0 @PushWFArc f19 '' #zField
>Proto Ae0 Ae0 SynchronizingTriggerProcess #zField
Ae0 f0 outLink addOrUpdate.ivp #txt
Ae0 f0 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f0 inParamDecl '<java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL,List<ch.ivy.ws.addon.CustomPropertyPair> customPropertiesPair> param;' #txt
Ae0 f0 inParamTable 'out.customPropertyPairs=param.customPropertiesPair;
out.isNTLMAuthentication=param.isNTLMAuthentication;
out.serverURL=param.serverURL;
' #txt
Ae0 f0 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f0 guid 1503C54192B617EA #txt
Ae0 f0 requestEnabled false #txt
Ae0 f0 triggerEnabled true #txt
Ae0 f0 callSignature addOrUpdate(Boolean,String,List<ch.ivy.ws.addon.CustomPropertyPair>) #txt
Ae0 f0 persist false #txt
Ae0 f0 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addOrUpdate(Boolean,String,List&lt;CustomPropertyPair&gt;)</name>
        <nameStyle>52,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 91 107 26 26 14 0 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f1 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f1 91 243 26 26 14 0 #rect
Ae0 f1 @|EndIcon #fIcon
Ae0 f3 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f3 processCall SynchronizeDataProcesses/AddOrUpdateWSCalling:addOrUpdate(List<ch.ivy.ws.addon.CustomPropertyPair>,Boolean,String) #txt
Ae0 f3 doCall true #txt
Ae0 f3 requestActionDecl '<List<ch.ivy.ws.addon.CustomPropertyPair> customerPropertyPairs,java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL> param;
' #txt
Ae0 f3 requestMappingAction 'param.customerPropertyPairs=in.customPropertyPairs;
param.isNTLMAuthentication=in.isNTLMAuthentication;
param.serverURL=in.serverURL;
' #txt
Ae0 f3 responseActionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f3 responseMappingAction 'out=in;
' #txt
Ae0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AddOrUpdateWSCalling</name>
    </language>
</elementInfo>
' #txt
Ae0 f3 86 172 36 24 20 -2 #rect
Ae0 f3 @|CallSubIcon #fIcon
Ae0 f4 expr out #txt
Ae0 f4 104 133 104 172 #arcP
Ae0 f2 expr out #txt
Ae0 f2 104 196 104 243 #arcP
Ae0 f8 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f8 667 467 26 26 14 0 #rect
Ae0 f8 @|EndIcon #fIcon
Ae0 f5 outLink delete.ivp #txt
Ae0 f5 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f5 inParamDecl '<java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL,java.lang.String key> param;' #txt
Ae0 f5 inParamTable 'out.isNTLMAuthentication=param.isNTLMAuthentication;
out.key=param.key;
out.serverURL=param.serverURL;
' #txt
Ae0 f5 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f5 guid 150CBA77E99B5B1E #txt
Ae0 f5 requestEnabled false #txt
Ae0 f5 triggerEnabled true #txt
Ae0 f5 callSignature delete(Boolean,String,String) #txt
Ae0 f5 persist false #txt
Ae0 f5 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f5 showInStartList 1 #txt
Ae0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete(Boolean,String,String)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f5 @C|.responsibility Everybody #txt
Ae0 f5 91 315 26 26 14 0 #rect
Ae0 f5 @|StartRequestIcon #fIcon
Ae0 f6 outLink deleteByPrefix.ivp #txt
Ae0 f6 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f6 inParamDecl '<java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL,java.lang.String keyPrefix> param;' #txt
Ae0 f6 inParamTable 'out.isNTLMAuthentication=param.isNTLMAuthentication;
out.keyPrefix=param.keyPrefix;
out.serverURL=param.serverURL;
' #txt
Ae0 f6 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f6 guid 150CBA77E951AD0F #txt
Ae0 f6 requestEnabled false #txt
Ae0 f6 triggerEnabled true #txt
Ae0 f6 callSignature deleteByPrefix(Boolean,String,String) #txt
Ae0 f6 persist false #txt
Ae0 f6 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f6 showInStartList 1 #txt
Ae0 f6 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteByPrefix(Boolean,String,String)</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f6 @C|.responsibility Everybody #txt
Ae0 f6 667 307 26 26 14 0 #rect
Ae0 f6 @|StartRequestIcon #fIcon
Ae0 f7 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f7 91 467 26 26 14 0 #rect
Ae0 f7 @|EndIcon #fIcon
Ae0 f11 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f11 processCall SynchronizeDataProcesses/DeleteWSCalling:delete(Boolean,String,String) #txt
Ae0 f11 doCall true #txt
Ae0 f11 requestActionDecl '<java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL,java.lang.String key> param;
' #txt
Ae0 f11 requestMappingAction 'param.isNTLMAuthentication=in.isNTLMAuthentication;
param.serverURL=in.serverURL;
param.key=in.key;
' #txt
Ae0 f11 responseActionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f11 responseMappingAction 'out=in;
' #txt
Ae0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DeleteWSCalling</name>
    </language>
</elementInfo>
' #txt
Ae0 f11 86 388 36 24 20 -2 #rect
Ae0 f11 @|CallSubIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 104 341 104 388 #arcP
Ae0 f10 expr out #txt
Ae0 f10 104 412 104 467 #arcP
Ae0 f13 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f13 processCall SynchronizeDataProcesses/DeleteWSCalling:deleteByPrefix(Boolean,String,String) #txt
Ae0 f13 doCall true #txt
Ae0 f13 requestActionDecl '<java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL,java.lang.String keyPrefix> param;
' #txt
Ae0 f13 requestMappingAction 'param.isNTLMAuthentication=in.isNTLMAuthentication;
param.serverURL=in.serverURL;
param.keyPrefix=in.keyPrefix;
' #txt
Ae0 f13 responseActionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f13 responseMappingAction 'out=in;
' #txt
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete By Prefix 
WSCalling</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f13 662 388 36 24 20 -2 #rect
Ae0 f13 @|CallSubIcon #fIcon
Ae0 f14 expr out #txt
Ae0 f14 680 333 680 388 #arcP
Ae0 f9 expr out #txt
Ae0 f9 680 412 680 467 #arcP
Ae0 f15 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f15 323 467 26 26 14 0 #rect
Ae0 f15 @|EndIcon #fIcon
Ae0 f16 outLink deleteManyProperties.ivp #txt
Ae0 f16 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f16 inParamDecl '<java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL,List<java.lang.String> keys> param;' #txt
Ae0 f16 inParamTable 'out.isNTLMAuthentication=param.isNTLMAuthentication;
out.keys=param.keys;
out.serverURL=param.serverURL;
' #txt
Ae0 f16 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f16 guid 1510E77977614BF3 #txt
Ae0 f16 requestEnabled false #txt
Ae0 f16 triggerEnabled true #txt
Ae0 f16 callSignature deleteManyProperties(Boolean,String,List<String>) #txt
Ae0 f16 persist false #txt
Ae0 f16 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f16 showInStartList 1 #txt
Ae0 f16 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteManyProperties(Boolean,String,List&lt;String&gt;)</name>
        <nameStyle>49,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f16 @C|.responsibility Everybody #txt
Ae0 f16 323 315 26 26 14 0 #rect
Ae0 f16 @|StartRequestIcon #fIcon
Ae0 f17 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f17 processCall SynchronizeDataProcesses/DeleteWSCalling:deleteManyProperties(Boolean,String,List<String>) #txt
Ae0 f17 doCall true #txt
Ae0 f17 requestActionDecl '<java.lang.Boolean isNTLMAuthentication,java.lang.String serverURL,List<java.lang.String> keys> param;
' #txt
Ae0 f17 requestMappingAction 'param.isNTLMAuthentication=in.isNTLMAuthentication;
param.serverURL=in.serverURL;
param.keys=in.keys;
' #txt
Ae0 f17 responseActionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f17 responseMappingAction 'out=in;
' #txt
Ae0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete many properties
WS Calling</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f17 318 388 36 24 20 -2 #rect
Ae0 f17 @|CallSubIcon #fIcon
Ae0 f18 expr out #txt
Ae0 f18 336 341 336 388 #arcP
Ae0 f19 expr out #txt
Ae0 f19 336 412 336 467 #arcP
>Proto Ae0 .type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f0 mainOut f4 tail #connect
Ae0 f4 head f3 mainIn #connect
Ae0 f3 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f5 mainOut f12 tail #connect
Ae0 f12 head f11 mainIn #connect
Ae0 f11 mainOut f10 tail #connect
Ae0 f10 head f7 mainIn #connect
Ae0 f6 mainOut f14 tail #connect
Ae0 f14 head f13 mainIn #connect
Ae0 f13 mainOut f9 tail #connect
Ae0 f9 head f8 mainIn #connect
Ae0 f16 mainOut f18 tail #connect
Ae0 f18 head f17 mainIn #connect
Ae0 f17 mainOut f19 tail #connect
Ae0 f19 head f15 mainIn #connect
