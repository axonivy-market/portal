[Ivy]
[>Created: Thu Jan 19 18:12:33 ICT 2017]
150406C1AFBE566B 3.18 #module
>Proto >Proto Collection #zClass
An0 AbstractSynchronizingConfiguration Big #zClass
An0 B #cInfo
An0 #process
An0 @TextInP .resExport .resExport #zField
An0 @TextInP .type .type #zField
An0 @TextInP .processKind .processKind #zField
An0 @AnnotationInP-0n ai ai #zField
An0 @MessageFlowInP-0n messageIn messageIn #zField
An0 @MessageFlowOutP-0n messageOut messageOut #zField
An0 @TextInP .xml .xml #zField
An0 @TextInP .responsibility .responsibility #zField
An0 @EndTask f1 '' #zField
An0 @StartRequest f0 '' #zField
An0 @CallSub f5 '' #zField
An0 @PushWFArc f2 '' #zField
An0 @CallSub f9 '' #zField
An0 @EndTask f10 '' #zField
An0 @StartRequest f12 '' #zField
An0 @GridStep f13 '' #zField
An0 @PushWFArc f15 '' #zField
An0 @PushWFArc f17 '' #zField
An0 @CallSub f18 '' #zField
An0 @EndTask f19 '' #zField
An0 @StartRequest f21 '' #zField
An0 @GridStep f22 '' #zField
An0 @PushWFArc f24 '' #zField
An0 @PushWFArc f26 '' #zField
An0 @CallSub f31 '' #zField
An0 @PushWFArc f35 '' #zField
An0 @EndTask f30 '' #zField
An0 @StartRequest f27 '' #zField
An0 @GridStep f7 '' #zField
An0 @PushWFArc f8 '' #zField
An0 @PushWFArc f29 '' #zField
An0 @PushWFArc f3 '' #zField
An0 @PushWFArc f4 '' #zField
An0 @PushWFArc f6 '' #zField
An0 @StartRequest f11 '' #zField
An0 @EndTask f14 '' #zField
An0 @GridStep f20 '' #zField
An0 @PushWFArc f23 '' #zField
An0 @CallSub f25 '' #zField
An0 @PushWFArc f28 '' #zField
An0 @PushWFArc f16 '' #zField
>Proto An0 An0 AbstractSynchronizingConfiguration #zField
An0 f1 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f1 51 291 26 26 14 0 #rect
An0 f1 @|EndIcon #fIcon
An0 f0 outLink addOrUpdateOne.ivp #txt
An0 f0 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f0 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity businessEntity> param;' #txt
An0 f0 inParamTable 'out.businessEntity=param.businessEntity;
' #txt
An0 f0 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f0 guid 150406C1B0057D7C #txt
An0 f0 requestEnabled false #txt
An0 f0 triggerEnabled true #txt
An0 f0 callSignature addOrUpdateOne(ch.ivy.addon.portalkit.persistence.domain.BusinessEntity) #txt
An0 f0 persist false #txt
An0 f0 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntityDescription")%>
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.EXROL=Everybody' #txt
An0 f0 caseData 'case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntityDescription")%>
case.name=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>' #txt
An0 f0 showInStartList 1 #txt
An0 f0 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
ivy.case.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntityDescription\")%>"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
taskUpdDef.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntityDescription\")%>"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
An0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addOrUpdateOne(BusinessEntity)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f0 @C|.responsibility Everybody #txt
An0 f0 51 83 26 26 14 0 #rect
An0 f0 @|StartRequestIcon #fIcon
An0 f5 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f5 processCall SynchronizeDataProcesses/Synchronizing:addOrUpdateToAllPortalServer(List<ch.ivy.ws.addon.CustomPropertyPair>) #txt
An0 f5 doCall true #txt
An0 f5 requestActionDecl '<List<ch.ivy.ws.addon.CustomPropertyPair> customPropertyPairs> param;
' #txt
An0 f5 requestMappingAction 'param.customPropertyPairs=in.customPropertyPairs;
' #txt
An0 f5 responseActionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f5 responseMappingAction 'out=in;
' #txt
An0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update
configuration data</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f5 46 228 36 24 19 -2 #rect
An0 f5 @|CallSubIcon #fIcon
An0 f2 expr out #txt
An0 f2 64 252 64 291 #arcP
An0 f9 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f9 processCall SynchronizeDataProcesses/Synchronizing:addOrUpdateToAllPortalServer(List<ch.ivy.ws.addon.CustomPropertyPair>) #txt
An0 f9 doCall true #txt
An0 f9 requestActionDecl '<List<ch.ivy.ws.addon.CustomPropertyPair> customPropertyPairs> param;
' #txt
An0 f9 requestMappingAction 'param.customPropertyPairs=in.customPropertyPairs;
' #txt
An0 f9 responseActionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f9 responseMappingAction 'out=in;
' #txt
An0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update
configuration data</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f9 302 228 36 24 19 -2 #rect
An0 f9 @|CallSubIcon #fIcon
An0 f10 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f10 307 291 26 26 14 0 #rect
An0 f10 @|EndIcon #fIcon
An0 f12 outLink addOrUpdateMany.ivp #txt
An0 f12 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f12 inParamDecl '<List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;' #txt
An0 f12 inParamTable 'out.businessEntities=param.businessEntities;
' #txt
An0 f12 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f12 guid 150406F795C4408A #txt
An0 f12 requestEnabled false #txt
An0 f12 triggerEnabled true #txt
An0 f12 callSignature addOrUpdateMany(List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>) #txt
An0 f12 persist false #txt
An0 f12 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.EXROL=Everybody
TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntitiesDescription")%>' #txt
An0 f12 caseData 'case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntitiesDescription")%>
case.name=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>' #txt
An0 f12 showInStartList 1 #txt
An0 f12 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
ivy.case.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntitiesDescription\")%>"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
taskUpdDef.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/AddOrUpdateBusinessEntitiesDescription\")%>"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
An0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addOrUpdateMany(List&lt;BusinessEntity&gt;)</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f12 @C|.responsibility Everybody #txt
An0 f12 307 83 26 26 14 0 #rect
An0 f12 @|StartRequestIcon #fIcon
An0 f13 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f13 actionTable 'out=in;
' #txt
An0 f13 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;

import ch.ivy.ws.addon.CustomPropertyPair;
import ch.ivy.addon.portalkit.webservice.PortalSynchronizationRequestPreparer;

in.customPropertyPairs = new java.util.ArrayList();
PortalSynchronizationRequestPreparer preparer = new PortalSynchronizationRequestPreparer();
for(BusinessEntity entity : in.businessEntities){
	CustomPropertyPair customPropertyPair = preparer.convertPortalEntityToRequestData(entity);
	in.customPropertyPairs.add(customPropertyPair);
}
in.customPropertyPairs.add(preparer.getIncrementIdPropertyPair());
' #txt
An0 f13 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>convert configuration objects
to customPropertyPairs</name>
        <nameStyle>52,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f13 302 164 36 24 20 -2 #rect
An0 f13 @|StepIcon #fIcon
An0 f15 expr out #txt
An0 f15 320 188 320 228 #arcP
An0 f17 expr out #txt
An0 f17 320 252 320 291 #arcP
An0 f18 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f18 processCall SynchronizeDataProcesses/Synchronizing:delete(String) #txt
An0 f18 doCall true #txt
An0 f18 requestActionDecl '<java.lang.String keyToBeDeleted> param;
' #txt
An0 f18 requestMappingAction 'param.keyToBeDeleted=in.propertyKeyToBeDeleted;
' #txt
An0 f18 responseActionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f18 responseMappingAction 'out=in;
' #txt
An0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete
configuration data</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f18 598 228 36 24 19 -2 #rect
An0 f18 @|CallSubIcon #fIcon
An0 f19 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f19 603 291 26 26 14 0 #rect
An0 f19 @|EndIcon #fIcon
An0 f21 outLink delete.ivp #txt
An0 f21 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f21 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity businessEntity> param;' #txt
An0 f21 inParamTable 'out.businessEntity=param.businessEntity;
' #txt
An0 f21 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f21 guid 1504071F443A2BD1 #txt
An0 f21 requestEnabled false #txt
An0 f21 triggerEnabled true #txt
An0 f21 callSignature delete(ch.ivy.addon.portalkit.persistence.domain.BusinessEntity) #txt
An0 f21 persist false #txt
An0 f21 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntityDescription")%>
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>' #txt
An0 f21 caseData 'case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntityDescription")%>
case.name=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>' #txt
An0 f21 showInStartList 1 #txt
An0 f21 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
ivy.case.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntityDescription\")%>"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
taskUpdDef.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntityDescription\")%>"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
An0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete(BusinessEntity)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f21 @C|.responsibility Everybody #txt
An0 f21 603 83 26 26 14 0 #rect
An0 f21 @|StartRequestIcon #fIcon
An0 f22 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f22 actionTable 'out=in;
' #txt
An0 f22 actionCode 'import ch.ivy.addon.portalkit.webservice.PortalSynchronizationRequestPreparer;

in.propertyKeyToBeDeleted = in.businessEntity.getPropertyKey();
' #txt
An0 f22 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get property key 
of configuration object</name>
        <nameStyle>41,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f22 598 164 36 24 20 -2 #rect
An0 f22 @|StepIcon #fIcon
An0 f24 expr out #txt
An0 f24 616 188 616 228 #arcP
An0 f26 expr out #txt
An0 f26 616 252 616 291 #arcP
An0 f31 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f31 processCall SynchronizeDataProcesses/Synchronizing:deleteByPrefix(String) #txt
An0 f31 doCall true #txt
An0 f31 requestActionDecl '<java.lang.String keyPrefixToBeDeleted> param;
' #txt
An0 f31 requestMappingAction 'param.keyPrefixToBeDeleted=in.keyPrefixToBeDeleted;
' #txt
An0 f31 responseActionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f31 responseMappingAction 'out=in;
' #txt
An0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete
configuration data</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f31 1126 196 36 24 19 -2 #rect
An0 f31 @|CallSubIcon #fIcon
An0 f35 expr out #txt
An0 f35 1144 220 1144 291 #arcP
An0 f30 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f30 1131 291 26 26 14 0 #rect
An0 f30 @|EndIcon #fIcon
An0 f27 outLink deleteByPropertyPrefix.ivp #txt
An0 f27 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f27 inParamDecl '<java.lang.String propertyPrefix> param;' #txt
An0 f27 inParamTable 'out.keyPrefixToBeDeleted=param.propertyPrefix;
' #txt
An0 f27 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f27 guid 150407730E281D66 #txt
An0 f27 requestEnabled false #txt
An0 f27 triggerEnabled true #txt
An0 f27 callSignature deleteByPropertyPrefix(String) #txt
An0 f27 persist false #txt
An0 f27 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.EXROL=Everybody
TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeletePropertyOfServerByPrefixDescription")%>' #txt
An0 f27 caseData 'case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntityByPrefixDescription")%>
case.name=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>' #txt
An0 f27 showInStartList 1 #txt
An0 f27 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
ivy.case.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntityByPrefixDescription\")%>"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
taskUpdDef.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/DeletePropertyOfServerByPrefixDescription\")%>"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
An0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteByPropertyPrefix(String)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f27 @C|.responsibility Everybody #txt
An0 f27 1131 83 26 26 14 0 #rect
An0 f27 @|StartRequestIcon #fIcon
An0 f7 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f7 actionTable 'out=in;
' #txt
An0 f7 actionCode 'import ch.ivy.addon.portalkit.webservice.PortalSynchronizationRequestPreparer;

PortalSynchronizationRequestPreparer preparer = new PortalSynchronizationRequestPreparer();
in.customPropertyPairs = new java.util.ArrayList();
in.customPropertyPairs.add(preparer.convertPortalEntityToRequestData(in.businessEntity));
in.customPropertyPairs.add(preparer.getIncrementIdPropertyPair());' #txt
An0 f7 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>convert configuration object 
to customPropertyPair</name>
        <nameStyle>51,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f7 46 164 36 24 20 -2 #rect
An0 f7 @|StepIcon #fIcon
An0 f8 expr out #txt
An0 f8 64 188 64 228 #arcP
An0 f29 expr out #txt
An0 f29 64 109 64 164 #arcP
An0 f3 expr out #txt
An0 f3 320 109 320 164 #arcP
An0 f4 expr out #txt
An0 f4 616 109 616 164 #arcP
An0 f6 expr out #txt
An0 f6 1144 109 1144 196 #arcP
An0 f11 outLink deleteManyProperties.ivp #txt
An0 f11 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f11 inParamDecl '<List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;' #txt
An0 f11 inParamTable 'out.businessEntities=param.businessEntities;
' #txt
An0 f11 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f11 guid 1510E6C9D89D044E #txt
An0 f11 requestEnabled false #txt
An0 f11 triggerEnabled true #txt
An0 f11 callSignature deleteManyProperties(List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>) #txt
An0 f11 persist false #txt
An0 f11 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.EXROL=Everybody
TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntitiesDescription")%>' #txt
An0 f11 caseData 'case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntitiesDescription")%>
case.name=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>' #txt
An0 f11 showInStartList 1 #txt
An0 f11 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
ivy.case.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntitiesDescription\")%>"));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName\")%>"));
taskUpdDef.setDescription(engine.expandMacros("<%=ivy.cms.co(\"/Processes/Cases/SynchronizeDataProcess/DeleteBusinessEntitiesDescription\")%>"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
An0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteManyProperties(List&lt;BusinessEntity&gt;)</name>
        <nameStyle>42,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f11 @C|.responsibility Everybody #txt
An0 f11 819 83 26 26 14 0 #rect
An0 f11 @|StartRequestIcon #fIcon
An0 f14 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f14 819 291 26 26 14 0 #rect
An0 f14 @|EndIcon #fIcon
An0 f20 actionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f20 actionTable 'out=in;
' #txt
An0 f20 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;

for(BusinessEntity property : in.businessEntities){
	in.propertyKeysToBeDeleted.add(property.getPropertyKey());
}' #txt
An0 f20 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get property keys of configuration object</name>
        <nameStyle>41,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f20 814 164 36 24 20 -2 #rect
An0 f20 @|StepIcon #fIcon
An0 f23 expr out #txt
An0 f23 832 109 832 164 #arcP
An0 f25 type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
An0 f25 processCall SynchronizeDataProcesses/Synchronizing:deleteManyProperties(List<String>) #txt
An0 f25 doCall true #txt
An0 f25 requestActionDecl '<List<java.lang.String> keysToBeDeleted> param;
' #txt
An0 f25 requestMappingAction 'param.keysToBeDeleted=in.propertyKeysToBeDeleted;
' #txt
An0 f25 responseActionDecl 'ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData out;
' #txt
An0 f25 responseMappingAction 'out=in;
' #txt
An0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete configuration datas</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
An0 f25 814 228 36 24 20 -2 #rect
An0 f25 @|CallSubIcon #fIcon
An0 f28 expr out #txt
An0 f28 832 188 832 228 #arcP
An0 f16 expr out #txt
An0 f16 832 252 832 291 #arcP
>Proto An0 .type ch.ivy.add.portalkit.AbstractSynchronizingConfigurationData #txt
>Proto An0 .processKind NORMAL #txt
>Proto An0 0 0 32 24 18 0 #rect
>Proto An0 @|BIcon #fIcon
An0 f5 mainOut f2 tail #connect
An0 f2 head f1 mainIn #connect
An0 f13 mainOut f15 tail #connect
An0 f15 head f9 mainIn #connect
An0 f9 mainOut f17 tail #connect
An0 f17 head f10 mainIn #connect
An0 f22 mainOut f24 tail #connect
An0 f24 head f18 mainIn #connect
An0 f18 mainOut f26 tail #connect
An0 f26 head f19 mainIn #connect
An0 f31 mainOut f35 tail #connect
An0 f35 head f30 mainIn #connect
An0 f8 head f5 mainIn #connect
An0 f7 mainOut f8 tail #connect
An0 f0 mainOut f29 tail #connect
An0 f29 head f7 mainIn #connect
An0 f12 mainOut f3 tail #connect
An0 f3 head f13 mainIn #connect
An0 f21 mainOut f4 tail #connect
An0 f4 head f22 mainIn #connect
An0 f27 mainOut f6 tail #connect
An0 f6 head f31 mainIn #connect
An0 f11 mainOut f23 tail #connect
An0 f23 head f20 mainIn #connect
An0 f20 mainOut f28 tail #connect
An0 f28 head f25 mainIn #connect
An0 f25 mainOut f16 tail #connect
An0 f16 head f14 mainIn #connect
