[Ivy]
14C7986FD23D7C89 3.20 #module
>Proto >Proto Collection #zClass
Ae0 InternalSupportPortalHome Big #zClass
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
Ae0 @RichDialog f6 '' #zField
Ae0 @StartRequest f3 '' #zField
Ae0 @RichDialog f8 '' #zField
Ae0 @PushWFArc f9 '' #zField
Ae0 @StartRequest f10 '' #zField
Ae0 @RichDialog f11 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @StartRequest f13 '' #zField
Ae0 @RichDialog f14 '' #zField
Ae0 @PushWFArc f15 '' #zField
Ae0 @StartRequest f16 '' #zField
Ae0 @RichDialog f17 '' #zField
Ae0 @PushWFArc f18 '' #zField
Ae0 @RichDialog f20 '' #zField
Ae0 @StartRequest f19 '' #zField
Ae0 @PushWFArc f21 '' #zField
Ae0 @PushWFArc f22 '' #zField
>Proto Ae0 Ae0 InternalSupportPortalHome #zField
Ae0 f0 outLink start.ivp #txt
Ae0 f0 type internalPortal.Data #txt
Ae0 f0 inParamDecl '<java.lang.Number taskIdentifier> param;' #txt
Ae0 f0 inParamTable 'out.taskId=param.taskIdentifier;
' #txt
Ae0 f0 actionDecl 'internalPortal.Data out;
' #txt
Ae0 f0 guid 14BEE532267D336E #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature start(Number) #txt
Ae0 f0 persist false #txt
Ae0 f0 startName 'Internal Support Home' #txt
Ae0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f0 caseData businessCase.attach=false #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 81 49 30 30 -21 17 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f6 targetWindow NEW:card: #txt
Ae0 f6 targetDisplay TOP #txt
Ae0 f6 richDialogId internalPortal.InternalPortalHome #txt
Ae0 f6 startMethod start() #txt
Ae0 f6 type internalPortal.Data #txt
Ae0 f6 requestActionDecl '<> param;' #txt
Ae0 f6 responseActionDecl 'internalPortal.Data out;
' #txt
Ae0 f6 responseMappingAction 'out=in;
' #txt
Ae0 f6 windowConfiguration '* ' #txt
Ae0 f6 isAsynch false #txt
Ae0 f6 isInnerRd false #txt
Ae0 f6 userContext '* ' #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>homepage</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f6 246 52 36 24 23 4 #rect
Ae0 f6 @|RichDialogIcon #fIcon
Ae0 f3 outLink caseDetails.ivp #txt
Ae0 f3 type internalPortal.Data #txt
Ae0 f3 inParamDecl '<java.lang.Number caseId> param;' #txt
Ae0 f3 inParamTable 'out.caseId=param.caseId;
' #txt
Ae0 f3 actionDecl 'internalPortal.Data out;
' #txt
Ae0 f3 guid 14C9DEEEB08F6031 #txt
Ae0 f3 requestEnabled true #txt
Ae0 f3 triggerEnabled false #txt
Ae0 f3 callSignature caseDetails(Number) #txt
Ae0 f3 persist false #txt
Ae0 f3 taskData '#
#Wed Apr 15 14:09:38 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f3 caseData '#
#Wed Apr 15 14:09:38 ICT 2015
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
Ae0 f3 showInStartList 0 #txt
Ae0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>caseDetails</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f3 @C|.responsibility Everybody #txt
Ae0 f3 75 211 26 26 14 0 #rect
Ae0 f3 @|StartRequestIcon #fIcon
Ae0 f8 targetWindow NEW:card: #txt
Ae0 f8 targetDisplay TOP #txt
Ae0 f8 richDialogId internalPortal.casedetails.PortalCaseDetails #txt
Ae0 f8 startMethod start(Number) #txt
Ae0 f8 type internalPortal.Data #txt
Ae0 f8 requestActionDecl '<Number caseId> param;' #txt
Ae0 f8 requestMappingAction 'param.caseId=in.caseId;
' #txt
Ae0 f8 responseActionDecl 'internalPortal.Data out;
' #txt
Ae0 f8 responseMappingAction 'out=in;
' #txt
Ae0 f8 windowConfiguration '* ' #txt
Ae0 f8 isAsynch false #txt
Ae0 f8 isInnerRd false #txt
Ae0 f8 userContext '* ' #txt
Ae0 f8 254 212 36 24 20 -2 #rect
Ae0 f8 @|RichDialogIcon #fIcon
Ae0 f9 expr out #txt
Ae0 f9 101 224 254 224 #arcP
Ae0 f10 outLink holidayRequest.ivp #txt
Ae0 f10 type internalPortal.Data #txt
Ae0 f10 inParamDecl '<> param;' #txt
Ae0 f10 actionDecl 'internalPortal.Data out;
' #txt
Ae0 f10 guid 14CA24B3C94C5EFA #txt
Ae0 f10 requestEnabled true #txt
Ae0 f10 triggerEnabled false #txt
Ae0 f10 callSignature holidayRequest() #txt
Ae0 f10 persist false #txt
Ae0 f10 taskData '#
#Wed Apr 15 14:09:44 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f10 caseData '#
#Wed Apr 15 14:09:44 ICT 2015
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
Ae0 f10 showInStartList 0 #txt
Ae0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>holidayRequest.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f10 @C|.responsibility Everybody #txt
Ae0 f10 75 299 26 26 14 0 #rect
Ae0 f10 @|StartRequestIcon #fIcon
Ae0 f11 targetWindow NEW:card: #txt
Ae0 f11 targetDisplay TOP #txt
Ae0 f11 richDialogId internalPortal.caselist.HolidayRequestCaseList #txt
Ae0 f11 startMethod start() #txt
Ae0 f11 type internalPortal.Data #txt
Ae0 f11 requestActionDecl '<> param;' #txt
Ae0 f11 responseActionDecl 'internalPortal.Data out;
' #txt
Ae0 f11 responseMappingAction 'out=in;
' #txt
Ae0 f11 windowConfiguration '* ' #txt
Ae0 f11 isAsynch false #txt
Ae0 f11 isInnerRd false #txt
Ae0 f11 userContext '* ' #txt
Ae0 f11 254 301 36 22 20 -2 #rect
Ae0 f11 @|RichDialogIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 101 312 254 312 #arcP
Ae0 f13 outLink publicactionRequest.ivp #txt
Ae0 f13 type internalPortal.Data #txt
Ae0 f13 inParamDecl '<> param;' #txt
Ae0 f13 actionDecl 'internalPortal.Data out;
' #txt
Ae0 f13 guid 14CA28989412FBC0 #txt
Ae0 f13 requestEnabled true #txt
Ae0 f13 triggerEnabled false #txt
Ae0 f13 callSignature publicactionRequest() #txt
Ae0 f13 persist false #txt
Ae0 f13 taskData '#
#Wed Apr 15 14:09:50 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f13 caseData '#
#Wed Apr 15 14:09:50 ICT 2015
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
Ae0 f13 showInStartList 0 #txt
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>publicactionRequest.ivp</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f13 @C|.responsibility Everybody #txt
Ae0 f13 75 363 26 26 14 0 #rect
Ae0 f13 @|StartRequestIcon #fIcon
Ae0 f14 targetWindow NEW:card: #txt
Ae0 f14 targetDisplay TOP #txt
Ae0 f14 richDialogId internalPortal.caselist.PublicationCaseList #txt
Ae0 f14 startMethod start() #txt
Ae0 f14 type internalPortal.Data #txt
Ae0 f14 requestActionDecl '<> param;' #txt
Ae0 f14 responseActionDecl 'internalPortal.Data out;
' #txt
Ae0 f14 responseMappingAction 'out=in;
' #txt
Ae0 f14 windowConfiguration '* ' #txt
Ae0 f14 isAsynch false #txt
Ae0 f14 isInnerRd false #txt
Ae0 f14 userContext '* ' #txt
Ae0 f14 254 364 36 24 20 -2 #rect
Ae0 f14 @|RichDialogIcon #fIcon
Ae0 f15 expr out #txt
Ae0 f15 101 376 254 376 #arcP
Ae0 f16 outLink ProvidedCaseList.ivp #txt
Ae0 f16 type internalPortal.Data #txt
Ae0 f16 inParamDecl '<> param;' #txt
Ae0 f16 actionDecl 'internalPortal.Data out;
' #txt
Ae0 f16 guid 14D88FA09A59A3D9 #txt
Ae0 f16 requestEnabled true #txt
Ae0 f16 triggerEnabled false #txt
Ae0 f16 callSignature ProvidedCaseList() #txt
Ae0 f16 persist false #txt
Ae0 f16 taskData '#
#Mon May 25 09:50:16 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f16 caseData '#
#Mon May 25 09:50:16 ICT 2015
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
Ae0 f16 showInStartList 1 #txt
Ae0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProvidedCaseList.ivp</name>
    </language>
</elementInfo>
' #txt
Ae0 f16 @C|.responsibility Everybody #txt
Ae0 f16 75 443 26 26 14 0 #rect
Ae0 f16 @|StartRequestIcon #fIcon
Ae0 f17 targetWindow NEW:card: #txt
Ae0 f17 targetDisplay TOP #txt
Ae0 f17 richDialogId internalPortal.caselist.ProvidedCasesCaseTableTest #txt
Ae0 f17 startMethod start() #txt
Ae0 f17 type internalPortal.Data #txt
Ae0 f17 requestActionDecl '<> param;' #txt
Ae0 f17 responseActionDecl 'internalPortal.Data out;
' #txt
Ae0 f17 responseMappingAction 'out=in;
' #txt
Ae0 f17 windowConfiguration '* ' #txt
Ae0 f17 isAsynch false #txt
Ae0 f17 isInnerRd false #txt
Ae0 f17 userContext '* ' #txt
Ae0 f17 246 444 36 24 20 -2 #rect
Ae0 f17 @|RichDialogIcon #fIcon
Ae0 f18 expr out #txt
Ae0 f18 101 456 246 456 #arcP
Ae0 f20 targetWindow NEW:card: #txt
Ae0 f20 targetDisplay TOP #txt
Ae0 f20 richDialogId internalPortal.InternalPortalHomeNotRequiredLogin #txt
Ae0 f20 startMethod start() #txt
Ae0 f20 type internalPortal.Data #txt
Ae0 f20 requestActionDecl '<> param;' #txt
Ae0 f20 responseActionDecl 'internalPortal.Data out;
' #txt
Ae0 f20 responseMappingAction 'out=in;
' #txt
Ae0 f20 windowConfiguration '* ' #txt
Ae0 f20 isAsynch false #txt
Ae0 f20 isInnerRd false #txt
Ae0 f20 userContext '* ' #txt
Ae0 f20 238 532 36 24 20 -2 #rect
Ae0 f20 @|RichDialogIcon #fIcon
Ae0 f19 outLink HomeNotRequiredLogin.ivp #txt
Ae0 f19 type internalPortal.Data #txt
Ae0 f19 inParamDecl '<> param;' #txt
Ae0 f19 actionDecl 'internalPortal.Data out;
' #txt
Ae0 f19 guid 14EAA83324513ADF #txt
Ae0 f19 requestEnabled true #txt
Ae0 f19 triggerEnabled false #txt
Ae0 f19 callSignature HomeNotRequiredLogin() #txt
Ae0 f19 persist false #txt
Ae0 f19 taskData '#
#Mon Jul 20 15:10:27 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f19 caseData '#
#Mon Jul 20 15:10:27 ICT 2015
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
Ae0 f19 showInStartList 1 #txt
Ae0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HomeNotRequiredLogin.ivp</name>
    </language>
</elementInfo>
' #txt
Ae0 f19 @C|.responsibility Everybody #txt
Ae0 f19 75 531 26 26 -23 16 #rect
Ae0 f19 @|StartRequestIcon #fIcon
Ae0 f21 expr out #txt
Ae0 f21 101 544 238 544 #arcP
Ae0 f22 expr out #txt
Ae0 f22 111 64 246 64 #arcP
>Proto Ae0 .type internalPortal.Data #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f3 mainOut f9 tail #connect
Ae0 f9 head f8 mainIn #connect
Ae0 f10 mainOut f12 tail #connect
Ae0 f12 head f11 mainIn #connect
Ae0 f13 mainOut f15 tail #connect
Ae0 f15 head f14 mainIn #connect
Ae0 f16 mainOut f18 tail #connect
Ae0 f18 head f17 mainIn #connect
Ae0 f19 mainOut f21 tail #connect
Ae0 f21 head f20 mainIn #connect
Ae0 f0 mainOut f22 tail #connect
Ae0 f22 head f6 mainIn #connect
