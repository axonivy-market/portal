[Ivy]
[>Created: Thu Jan 26 16:13:22 ICT 2017]
1503C5419245E19B 3.19 #module
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
Ae0 f0 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,List<ch.ivy.ws.addon.CustomPropertyPair> customPropertiesPair> param;' #txt
Ae0 f0 inParamTable 'out.customPropertyPairs=param.customPropertiesPair;
out.server=param.server;
' #txt
Ae0 f0 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f0 guid 1503C54192B617EA #txt
Ae0 f0 requestEnabled false #txt
Ae0 f0 triggerEnabled true #txt
Ae0 f0 callSignature addOrUpdate(ch.ivy.addon.portalkit.persistence.domain.Server,List<ch.ivy.ws.addon.CustomPropertyPair>) #txt
Ae0 f0 persist false #txt
Ae0 f0 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/AddOrUpdatePropertyOfServerDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.ROL=SYSTEM
TaskTriggered.EXROL=Everybody' #txt
Ae0 f0 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/AddOrUpdatePropertyOfServerDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=false' #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addOrUpdate(Server,List&lt;CustomPropertyPair&gt;)</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 115 115 26 26 14 0 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f1 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f1 115 307 26 26 14 0 #rect
Ae0 f1 @|EndIcon #fIcon
Ae0 f3 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f3 processCall SynchronizeDataProcesses/AddOrUpdateWSCalling:addOrUpdate(List<ch.ivy.ws.addon.CustomPropertyPair>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f3 doCall true #txt
Ae0 f3 requestActionDecl '<List<ch.ivy.ws.addon.CustomPropertyPair> customerPropertyPairs,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ae0 f3 requestMappingAction 'param.customerPropertyPairs=in.customPropertyPairs;
param.server=in.server;
' #txt
Ae0 f3 responseActionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f3 responseMappingAction 'out=in;
' #txt
Ae0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AddOrUpdateWSCalling</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f3 110 212 36 24 20 -2 #rect
Ae0 f3 @|CallSubIcon #fIcon
Ae0 f4 expr out #txt
Ae0 f4 128 141 128 212 #arcP
Ae0 f2 expr out #txt
Ae0 f2 128 236 128 307 #arcP
Ae0 f8 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f8 659 595 26 26 14 0 #rect
Ae0 f8 @|EndIcon #fIcon
Ae0 f5 outLink delete.ivp #txt
Ae0 f5 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f5 inParamDecl '<java.lang.String key,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ae0 f5 inParamTable 'out.key=param.key;
out.server=param.server;
' #txt
Ae0 f5 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f5 guid 150CBA77E99B5B1E #txt
Ae0 f5 requestEnabled false #txt
Ae0 f5 triggerEnabled true #txt
Ae0 f5 callSignature delete(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f5 persist false #txt
Ae0 f5 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeletePropertyOfServerByPrefixDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.ROL=SYSTEM
TaskTriggered.EXROL=Everybody' #txt
Ae0 f5 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeletePropertyOfServerDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=false' #txt
Ae0 f5 showInStartList 1 #txt
Ae0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete(String,Server)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f5 @C|.responsibility Everybody #txt
Ae0 f5 115 403 26 26 14 0 #rect
Ae0 f5 @|StartRequestIcon #fIcon
Ae0 f6 outLink deleteByPrefix.ivp #txt
Ae0 f6 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f6 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String keyPrefix> param;' #txt
Ae0 f6 inParamTable 'out.keyPrefix=param.keyPrefix;
out.server=param.server;
' #txt
Ae0 f6 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f6 guid 150CBA77E951AD0F #txt
Ae0 f6 requestEnabled false #txt
Ae0 f6 triggerEnabled true #txt
Ae0 f6 callSignature deleteByPrefix(ch.ivy.addon.portalkit.persistence.domain.Server,String) #txt
Ae0 f6 persist false #txt
Ae0 f6 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeletePropertyOfServerByPrefixDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.ROL=SYSTEM
TaskTriggered.EXROL=Everybody' #txt
Ae0 f6 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeletePropertyOfServerByPrefixDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=false' #txt
Ae0 f6 showInStartList 1 #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteByPrefix(Server,String)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f6 @C|.responsibility Everybody #txt
Ae0 f6 659 403 26 26 14 0 #rect
Ae0 f6 @|StartRequestIcon #fIcon
Ae0 f7 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f7 115 595 26 26 14 0 #rect
Ae0 f7 @|EndIcon #fIcon
Ae0 f11 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f11 processCall SynchronizeDataProcesses/DeleteWSCalling:delete(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f11 doCall true #txt
Ae0 f11 requestActionDecl '<java.lang.String key,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ae0 f11 requestMappingAction 'param.key=in.key;
param.server=in.server;
' #txt
Ae0 f11 responseActionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f11 responseMappingAction 'out=in;
' #txt
Ae0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DeleteWSCalling</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f11 110 500 36 24 20 -2 #rect
Ae0 f11 @|CallSubIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 128 429 128 500 #arcP
Ae0 f10 expr out #txt
Ae0 f10 128 524 128 595 #arcP
Ae0 f13 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f13 processCall SynchronizeDataProcesses/DeleteWSCalling:deleteByPrefix(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f13 doCall true #txt
Ae0 f13 requestActionDecl '<java.lang.String keyPrefix,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ae0 f13 requestMappingAction 'param.keyPrefix=in.keyPrefix;
param.server=in.server;
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
Ae0 f13 654 500 36 24 20 -2 #rect
Ae0 f13 @|CallSubIcon #fIcon
Ae0 f14 expr out #txt
Ae0 f14 672 429 672 500 #arcP
Ae0 f9 expr out #txt
Ae0 f9 672 524 672 595 #arcP
Ae0 f15 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f15 339 595 26 26 14 0 #rect
Ae0 f15 @|EndIcon #fIcon
Ae0 f16 outLink deleteManyProperties.ivp #txt
Ae0 f16 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f16 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,List<java.lang.String> keys> param;' #txt
Ae0 f16 inParamTable 'out.keys=param.keys;
out.server=param.server;
' #txt
Ae0 f16 actionDecl 'ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData out;
' #txt
Ae0 f16 guid 1510E77977614BF3 #txt
Ae0 f16 requestEnabled false #txt
Ae0 f16 triggerEnabled true #txt
Ae0 f16 callSignature deleteManyProperties(ch.ivy.addon.portalkit.persistence.domain.Server,List<String>) #txt
Ae0 f16 persist false #txt
Ae0 f16 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeletePropertiesOfServerDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
TaskTriggered.ROL=SYSTEM
TaskTriggered.EXROL=Everybody' #txt
Ae0 f16 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/SynchronizeDataCaseName")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/SynchronizeDataProcess/DeletePropertiesOfServerDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=false' #txt
Ae0 f16 showInStartList 1 #txt
Ae0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteManyProperties(Server,List&lt;String&gt;)</name>
        <nameStyle>41,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f16 @C|.responsibility Everybody #txt
Ae0 f16 339 403 26 26 14 0 #rect
Ae0 f16 @|StartRequestIcon #fIcon
Ae0 f17 type ch.ivy.add.portalkit.synchronization.SynchronizingTriggerProcessData #txt
Ae0 f17 processCall SynchronizeDataProcesses/DeleteWSCalling:deleteManyProperties(List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ae0 f17 doCall true #txt
Ae0 f17 requestActionDecl '<List<java.lang.String> keys,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Ae0 f17 requestMappingAction 'param.keys=in.keys;
param.server=in.server;
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
Ae0 f17 334 500 36 24 20 -2 #rect
Ae0 f17 @|CallSubIcon #fIcon
Ae0 f18 expr out #txt
Ae0 f18 352 429 352 500 #arcP
Ae0 f19 expr out #txt
Ae0 f19 352 524 352 595 #arcP
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
