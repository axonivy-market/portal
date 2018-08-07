[Ivy]
[>Created: Tue Apr 12 11:03:02 ICT 2016]
1540379C4B7261E4 3.18 #module
>Proto >Proto Collection #zClass
Ot0 OpenPortalCaseList Big #zClass
Ot0 B #cInfo
Ot0 #process
Ot0 @TextInP .resExport .resExport #zField
Ot0 @TextInP .type .type #zField
Ot0 @TextInP .processKind .processKind #zField
Ot0 @AnnotationInP-0n ai ai #zField
Ot0 @MessageFlowInP-0n messageIn messageIn #zField
Ot0 @MessageFlowOutP-0n messageOut messageOut #zField
Ot0 @TextInP .xml .xml #zField
Ot0 @TextInP .responsibility .responsibility #zField
Ot0 @StartSub f0 '' #zField
Ot0 @EndSub f1 '' #zField
Ot0 @RichDialog f3 '' #zField
Ot0 @PushWFArc f4 '' #zField
Ot0 @PushWFArc f2 '' #zField
Ot0 @StartSub f5 '' #zField
Ot0 @EndSub f6 '' #zField
Ot0 @RichDialog f7 '' #zField
Ot0 @PushWFArc f8 '' #zField
Ot0 @PushWFArc f9 '' #zField
Ot0 @EndSub f10 '' #zField
Ot0 @StartSub f11 '' #zField
Ot0 @RichDialog f12 '' #zField
Ot0 @PushWFArc f13 '' #zField
Ot0 @PushWFArc f14 '' #zField
>Proto Ot0 Ot0 OpenPortalCaseList #zField
Ot0 f0 inParamDecl '<org.primefaces.model.TreeNode selectedNode> param;' #txt
Ot0 f0 inParamTable 'out.selectedNode=param.selectedNode;
' #txt
Ot0 f0 outParamDecl '<> result;
' #txt
Ot0 f0 actionDecl 'ch.ivy.addon.portal.generic.OpenPortalCaseListData out;
' #txt
Ot0 f0 callSignature openPortalCaseList(org.primefaces.model.TreeNode) #txt
Ot0 f0 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalCaseList(TreeNode)</name>
    </language>
</elementInfo>
' #txt
Ot0 f0 51 83 26 26 14 0 #rect
Ot0 f0 @|StartSubIcon #fIcon
Ot0 f1 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f1 51 339 26 26 14 0 #rect
Ot0 f1 @|EndSubIcon #fIcon
Ot0 f3 targetWindow NEW:card: #txt
Ot0 f3 targetDisplay TOP #txt
Ot0 f3 richDialogId ch.ivy.addon.portal.generic.PortalCaseList #txt
Ot0 f3 startMethod start(org.primefaces.model.TreeNode) #txt
Ot0 f3 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f3 requestActionDecl '<org.primefaces.model.TreeNode category> param;' #txt
Ot0 f3 requestMappingAction 'param.category=in.selectedNode;
' #txt
Ot0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalCaseListData out;
' #txt
Ot0 f3 responseMappingAction 'out=in;
' #txt
Ot0 f3 windowConfiguration '* ' #txt
Ot0 f3 isAsynch false #txt
Ot0 f3 isInnerRd false #txt
Ot0 f3 userContext '* ' #txt
Ot0 f3 46 212 36 24 20 -2 #rect
Ot0 f3 @|RichDialogIcon #fIcon
Ot0 f4 expr out #txt
Ot0 f4 64 109 64 212 #arcP
Ot0 f2 expr out #txt
Ot0 f2 64 236 64 339 #arcP
Ot0 f5 inParamDecl '<java.lang.Long caseId,java.lang.Long serverId> param;' #txt
Ot0 f5 inParamTable 'out.caseId=param.caseId;
out.serverId=param.serverId;
' #txt
Ot0 f5 outParamDecl '<> result;
' #txt
Ot0 f5 actionDecl 'ch.ivy.addon.portal.generic.OpenPortalCaseListData out;
' #txt
Ot0 f5 callSignature openFromLinkedTask(Long,Long) #txt
Ot0 f5 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openFromLinkedTask(TreeNode)</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ot0 f5 307 83 26 26 14 0 #rect
Ot0 f5 @|StartSubIcon #fIcon
Ot0 f6 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f6 307 339 26 26 14 0 #rect
Ot0 f6 @|EndSubIcon #fIcon
Ot0 f7 targetWindow NEW:card: #txt
Ot0 f7 targetDisplay TOP #txt
Ot0 f7 richDialogId ch.ivy.addon.portal.generic.PortalCaseList #txt
Ot0 f7 startMethod start(java.lang.Long,java.lang.Long) #txt
Ot0 f7 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f7 requestActionDecl '<java.lang.Long caseId, java.lang.Long serverId> param;' #txt
Ot0 f7 requestMappingAction 'param.caseId=in.caseId;
param.serverId=in.serverId;
' #txt
Ot0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalCaseListData out;
' #txt
Ot0 f7 responseMappingAction 'out=in;
' #txt
Ot0 f7 windowConfiguration '* ' #txt
Ot0 f7 isAsynch false #txt
Ot0 f7 isInnerRd false #txt
Ot0 f7 userContext '* ' #txt
Ot0 f7 302 212 36 24 20 -2 #rect
Ot0 f7 @|RichDialogIcon #fIcon
Ot0 f8 expr out #txt
Ot0 f8 320 109 320 212 #arcP
Ot0 f9 expr out #txt
Ot0 f9 320 236 320 339 #arcP
Ot0 f10 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f10 595 339 26 26 14 0 #rect
Ot0 f10 @|EndSubIcon #fIcon
Ot0 f11 inParamDecl '<java.lang.String keyword,java.lang.Long caseId,java.lang.Long serverId> param;' #txt
Ot0 f11 inParamTable 'out.caseId=param.caseId;
out.keyword=param.keyword;
out.serverId=param.serverId;
' #txt
Ot0 f11 outParamDecl '<> result;
' #txt
Ot0 f11 actionDecl 'ch.ivy.addon.portal.generic.OpenPortalCaseListData out;
' #txt
Ot0 f11 callSignature openFromCaseSearching(String,Long,Long) #txt
Ot0 f11 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openFromCaseSearching(String,Long,Long)</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ot0 f11 595 83 26 26 14 0 #rect
Ot0 f11 @|StartSubIcon #fIcon
Ot0 f12 targetWindow NEW:card: #txt
Ot0 f12 targetDisplay TOP #txt
Ot0 f12 richDialogId ch.ivy.addon.portal.generic.PortalCaseList #txt
Ot0 f12 startMethod start(String,java.lang.Long,java.lang.Long) #txt
Ot0 f12 type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
Ot0 f12 requestActionDecl '<String keyword, java.lang.Long selectedCaseId, java.lang.Long serverId> param;' #txt
Ot0 f12 requestMappingAction 'param.keyword=in.keyword;
param.selectedCaseId=in.caseId;
param.serverId=in.serverId;
' #txt
Ot0 f12 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalCaseListData out;
' #txt
Ot0 f12 responseMappingAction 'out=in;
' #txt
Ot0 f12 windowConfiguration '* ' #txt
Ot0 f12 isAsynch false #txt
Ot0 f12 isInnerRd false #txt
Ot0 f12 userContext '* ' #txt
Ot0 f12 590 212 36 24 20 -2 #rect
Ot0 f12 @|RichDialogIcon #fIcon
Ot0 f13 expr out #txt
Ot0 f13 608 109 608 212 #arcP
Ot0 f14 expr out #txt
Ot0 f14 608 236 608 339 #arcP
>Proto Ot0 .type ch.ivy.addon.portal.generic.OpenPortalCaseListData #txt
>Proto Ot0 .processKind CALLABLE_SUB #txt
>Proto Ot0 0 0 32 24 18 0 #rect
>Proto Ot0 @|BIcon #fIcon
Ot0 f0 mainOut f4 tail #connect
Ot0 f4 head f3 mainIn #connect
Ot0 f3 mainOut f2 tail #connect
Ot0 f2 head f1 mainIn #connect
Ot0 f5 mainOut f8 tail #connect
Ot0 f8 head f7 mainIn #connect
Ot0 f7 mainOut f9 tail #connect
Ot0 f9 head f6 mainIn #connect
Ot0 f11 mainOut f13 tail #connect
Ot0 f13 head f12 mainIn #connect
Ot0 f12 mainOut f14 tail #connect
Ot0 f14 head f10 mainIn #connect
