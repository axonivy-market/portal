[Ivy]
1503C0F82FA8D5F4 3.20 #module
>Proto >Proto Collection #zClass
Sn0 SynchronizeServerConfiguration Big #zClass
Sn0 B #cInfo
Sn0 #process
Sn0 @TextInP .resExport .resExport #zField
Sn0 @TextInP .type .type #zField
Sn0 @TextInP .processKind .processKind #zField
Sn0 @AnnotationInP-0n ai ai #zField
Sn0 @MessageFlowInP-0n messageIn messageIn #zField
Sn0 @MessageFlowOutP-0n messageOut messageOut #zField
Sn0 @TextInP .xml .xml #zField
Sn0 @TextInP .responsibility .responsibility #zField
Sn0 @StartRequest f0 '' #zField
Sn0 @EndTask f1 '' #zField
Sn0 @GridStep f7 '' #zField
Sn0 @Trigger f5 '' #zField
Sn0 @PushWFArc f4 '' #zField
Sn0 @CallSub f8 '' #zField
Sn0 @PushWFArc f13 '' #zField
Sn0 @StartRequest f6 '' #zField
Sn0 @EndTask f9 '' #zField
Sn0 @CallSub f14 '' #zField
Sn0 @PushWFArc f15 '' #zField
Sn0 @PushWFArc f16 '' #zField
Sn0 @Trigger f2 '' #zField
Sn0 @PushWFArc f3 '' #zField
Sn0 @PushWFArc f10 '' #zField
Sn0 @GridStep f11 '' #zField
Sn0 @PushWFArc f17 '' #zField
Sn0 @PushWFArc f12 '' #zField
>Proto Sn0 Sn0 SynchronizeServerConfiguration #zField
Sn0 f0 outLink add.ivp #txt
Sn0 f0 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f0 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Sn0 f0 inParamTable 'out.server=param.server;
' #txt
Sn0 f0 actionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f0 guid 1503C0F8314B7202 #txt
Sn0 f0 requestEnabled false #txt
Sn0 f0 triggerEnabled true #txt
Sn0 f0 callSignature add(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Sn0 f0 persist false #txt
Sn0 f0 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/AddServerConfigDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/AddServerConfigDescription")%>
TaskTriggered.ROL=SYSTEM
TaskTriggered.EXROL=Everybody' #txt
Sn0 f0 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/SynchronizeServerConfigurationCaseName")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/AddServerConfigDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=true' #txt
Sn0 f0 showInStartList 1 #txt
Sn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add(Server)</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f0 @C|.responsibility Everybody #txt
Sn0 f0 131 83 26 26 14 0 #rect
Sn0 f0 @|StartRequestIcon #fIcon
Sn0 f1 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f1 131 451 26 26 14 0 #rect
Sn0 f1 @|EndIcon #fIcon
Sn0 f7 actionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f7 actionTable 'out=in;
' #txt
Sn0 f7 actionCode 'import ch.ivyteam.util.Property;
import ch.ivy.addon.portalkit.webservice.PortalSynchronizationRequestPreparer;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivy.addon.portalkit.service.PortalDataService;

in.customPropertyPairs = new java.util.ArrayList();
PortalSynchronizationRequestPreparer preparer = new PortalSynchronizationRequestPreparer();
PortalDataService service = new PortalDataService();
for(Property property : service.getAllPortalCustomProperty()){
	in.customPropertyPairs.add(preparer.convertPropertyToRequestData(property));
}
in.customPropertyPairs.add(preparer.getIncrementIdPropertyPair());


' #txt
Sn0 f7 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all configuration data</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f7 126 164 36 24 20 -2 #rect
Sn0 f7 @|StepIcon #fIcon
Sn0 f5 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f5 processCall 'Business Processes/AbstractSynchronizingConfiguration:addOrUpdateOne(ch.ivy.addon.portalkit.persistence.domain.BusinessEntity)' #txt
Sn0 f5 doCall true #txt
Sn0 f5 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity businessEntity> param;
' #txt
Sn0 f5 requestMappingAction 'param.businessEntity=in.server;
' #txt
Sn0 f5 responseActionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f5 responseMappingAction 'out=in;
' #txt
Sn0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>trigger update to 
all portal server</name>
        <nameStyle>36,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f5 126 380 36 24 20 -2 #rect
Sn0 f5 @|TriggerIcon #fIcon
Sn0 f4 expr out #txt
Sn0 f4 144 404 144 451 #arcP
Sn0 f8 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f8 processCall SynchronizeDataProcesses/AddOrUpdateWSCalling:addOrUpdate(List<ch.ivy.ws.addon.CustomPropertyPair>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Sn0 f8 doCall true #txt
Sn0 f8 requestActionDecl '<List<ch.ivy.ws.addon.CustomPropertyPair> customerPropertyPairs,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Sn0 f8 requestMappingAction 'param.customerPropertyPairs=in.customPropertyPairs;
param.server=in.server;
' #txt
Sn0 f8 responseActionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f8 responseMappingAction 'out=in;
' #txt
Sn0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add configuration 
data for new server</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f8 126 292 36 24 20 -2 #rect
Sn0 f8 @|CallSubIcon #fIcon
Sn0 f13 expr out #txt
Sn0 f13 144 316 144 380 #arcP
Sn0 f6 outLink delete.ivp #txt
Sn0 f6 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f6 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Sn0 f6 inParamTable 'out.server=param.server;
' #txt
Sn0 f6 actionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f6 guid 150D099ACEC2F264 #txt
Sn0 f6 requestEnabled false #txt
Sn0 f6 triggerEnabled true #txt
Sn0 f6 callSignature delete(ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Sn0 f6 persist false #txt
Sn0 f6 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/DeleteServerConfigurationDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/DeleteServerConfigurationDescription")%>
TaskTriggered.ROL=SYSTEM
TaskTriggered.EXROL=Everybody' #txt
Sn0 f6 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/SynchronizeServerConfigurationCaseName")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeServerProcess/DeleteServerConfigurationDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=true' #txt
Sn0 f6 showInStartList 1 #txt
Sn0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete(Server)</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f6 @C|.responsibility Everybody #txt
Sn0 f6 435 83 26 26 14 0 #rect
Sn0 f6 @|StartRequestIcon #fIcon
Sn0 f9 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f9 435 307 26 26 14 0 #rect
Sn0 f9 @|EndIcon #fIcon
Sn0 f14 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f14 processCall SynchronizeDataProcesses/DeleteWSCalling:deleteByPrefix(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Sn0 f14 doCall true #txt
Sn0 f14 requestActionDecl '<java.lang.String keyPrefix,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Sn0 f14 requestMappingAction 'param.keyPrefix=ch.ivy.addon.portalkit.persistence.variable.PropertyKey.PORTAL_PROPERTY_START + ".";
param.server=in.server;
' #txt
Sn0 f14 responseActionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f14 responseMappingAction 'out=in;
' #txt
Sn0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clean portal data 
on deleted server</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f14 430 148 36 24 20 -2 #rect
Sn0 f14 @|CallSubIcon #fIcon
Sn0 f15 expr out #txt
Sn0 f15 448 109 448 148 #arcP
Sn0 f16 expr out #txt
Sn0 f16 144 109 144 164 #arcP
Sn0 f2 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f2 processCall 'Business Processes/AbstractSynchronizingConfiguration:delete(ch.ivy.addon.portalkit.persistence.domain.BusinessEntity)' #txt
Sn0 f2 doCall true #txt
Sn0 f2 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity businessEntity> param;
' #txt
Sn0 f2 requestMappingAction 'param.businessEntity=in.server;
' #txt
Sn0 f2 responseActionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f2 responseMappingAction 'out=in;
' #txt
Sn0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>trigger update to 
all portal server</name>
        <nameStyle>36,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f2 430 220 36 24 20 -2 #rect
Sn0 f2 @|TriggerIcon #fIcon
Sn0 f3 expr out #txt
Sn0 f3 448 172 448 220 #arcP
Sn0 f10 expr out #txt
Sn0 f10 448 244 448 307 #arcP
Sn0 f11 actionDecl 'ch.ivy.add.portalkit.SynchronizeServerConfigurationData out;
' #txt
Sn0 f11 actionTable 'out=in;
' #txt
Sn0 f11 actionCode 'import ch.ivy.addon.portalkit.service.ServerWorkingOnDetector;
import ch.ivy.ws.addon.CustomPropertyPair;

CustomPropertyPair myServerId = new CustomPropertyPair();
myServerId.setKey(ServerWorkingOnDetector.MY_SERVER_ID_KEY);
for (CustomPropertyPair prop : in.customPropertyPairs) {
	if (prop.getKey().equals(myServerId.getKey())) {
		myServerId = prop;
	}
}

myServerId.setValue(String.valueOf(in.server.id));
in.customPropertyPairs.add(myServerId);' #txt
Sn0 f11 type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
Sn0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add MyServerId of new server</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sn0 f11 126 228 36 24 20 -2 #rect
Sn0 f11 @|StepIcon #fIcon
Sn0 f17 expr out #txt
Sn0 f17 144 188 144 228 #arcP
Sn0 f12 expr out #txt
Sn0 f12 144 252 144 292 #arcP
>Proto Sn0 .type ch.ivy.add.portalkit.SynchronizeServerConfigurationData #txt
>Proto Sn0 .processKind NORMAL #txt
>Proto Sn0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Sn0 0 0 32 24 18 0 #rect
>Proto Sn0 @|BIcon #fIcon
Sn0 f5 mainOut f4 tail #connect
Sn0 f4 head f1 mainIn #connect
Sn0 f8 mainOut f13 tail #connect
Sn0 f13 head f5 mainIn #connect
Sn0 f6 mainOut f15 tail #connect
Sn0 f15 head f14 mainIn #connect
Sn0 f0 mainOut f16 tail #connect
Sn0 f16 head f7 mainIn #connect
Sn0 f14 mainOut f3 tail #connect
Sn0 f3 head f2 mainIn #connect
Sn0 f2 mainOut f10 tail #connect
Sn0 f10 head f9 mainIn #connect
Sn0 f7 mainOut f17 tail #connect
Sn0 f17 head f11 mainIn #connect
Sn0 f11 mainOut f12 tail #connect
Sn0 f12 head f8 mainIn #connect
