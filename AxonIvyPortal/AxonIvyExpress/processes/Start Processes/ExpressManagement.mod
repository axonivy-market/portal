[Ivy]
16F4048921116813 7.5.0 #module
>Proto >Proto Collection #zClass
Er0 ExpressManagement Big #zClass
Er0 B #cInfo
Er0 #process
Er0 @TextInP .type .type #zField
Er0 @TextInP .processKind .processKind #zField
Er0 @TextInP .xml .xml #zField
Er0 @TextInP .responsibility .responsibility #zField
Er0 @StartRequest f3 '' #zField
Er0 @EndTask f4 '' #zField
Er0 @UserDialog f6 '' #zField
Er0 @PushWFArc f5 '' #zField
Er0 @PushWFArc f0 '' #zField
>Proto Er0 Er0 ExpressManagement #zField
Er0 f3 outLink expressManagement.ivp #txt
Er0 f3 inParamDecl '<> param;' #txt
Er0 f3 requestEnabled true #txt
Er0 f3 triggerEnabled false #txt
Er0 f3 callSignature expressManagement() #txt
Er0 f3 startName 'Express Helper' #txt
Er0 f3 startDescription 'Express Helper' #txt
Er0 f3 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Dialogs/ExpressManagement/expressManagement")%>
TaskTriggered.NAM=<%\=ivy.cms.co("/Dialogs/ExpressManagement/expressManagement")%>
TaskTriggered.customFields.STRING.embedInFrame="false"' #txt
Er0 f3 caseData 'businessCase.attach=true
case.description=<%\=ivy.cms.co("/Dialogs/ExpressManagement/expressManagement")%>
case.name=<%\=ivy.cms.co("/Dialogs/ExpressManagement/expressManagement")%>' #txt
Er0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>expressManagement.ivp</name>
    </language>
</elementInfo>
' #txt
Er0 f3 @C|.responsibility Everybody #txt
Er0 f3 80 152 32 32 -46 23 #rect
Er0 f3 @|StartRequestIcon #fIcon
Er0 f4 601 153 30 30 0 15 #rect
Er0 f4 @|EndIcon #fIcon
Er0 f6 dialogId ch.ivy.gawfs.workflowCreation.ExpressManagement #txt
Er0 f6 startMethod start(ch.ivy.addon.portalkit.datamodel.ExpressProcessLazyDataModel) #txt
Er0 f6 requestActionDecl '<ch.ivy.addon.portalkit.datamodel.ExpressProcessLazyDataModel dataModel> param;' #txt
Er0 f6 requestMappingAction 'param.dataModel=in.dataModel;
' #txt
Er0 f6 responseMappingAction 'out=in;
' #txt
Er0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Express Management</name>
    </language>
</elementInfo>
' #txt
Er0 f6 288 146 128 44 -60 -8 #rect
Er0 f6 @|UserDialogIcon #fIcon
Er0 f5 416 168 601 168 #arcP
Er0 f0 112 168 288 168 #arcP
>Proto Er0 .type gawfs.ExpressHelperData #txt
>Proto Er0 .processKind NORMAL #txt
>Proto Er0 0 0 32 24 18 0 #rect
>Proto Er0 @|BIcon #fIcon
Er0 f6 mainOut f5 tail #connect
Er0 f5 head f4 mainIn #connect
Er0 f3 mainOut f0 tail #connect
Er0 f0 head f6 mainIn #connect
