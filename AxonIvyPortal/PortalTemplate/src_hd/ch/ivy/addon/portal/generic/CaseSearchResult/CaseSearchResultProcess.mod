[Ivy]
[>Created: Thu May 26 15:08:20 ICT 2016]
15438977A8AF4FC1 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseSearchResultProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @CallSub f6 '' #zField
Cs0 @RichDialogProcessStart f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
>Proto Cs0 Cs0 CaseSearchResultProcess #zField
Cs0 f0 guid 15438977A9CA76C0 #txt
Cs0 f0 type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
Cs0 f1 253 85 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 75 96 253 96 #arcP
Cs0 f6 type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
Cs0 f6 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Cs0 f6 doCall true #txt
Cs0 f6 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Cs0 f6 requestMappingAction 'param.view=in.view;
' #txt
Cs0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData out;
' #txt
Cs0 f6 responseMappingAction 'out=in;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 246 180 36 24 20 -2 #rect
Cs0 f6 @|CallSubIcon #fIcon
Cs0 f8 guid 1543BE219788553F #txt
Cs0 f8 type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
Cs0 f8 actionDecl 'ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portal.generic.view.CaseView;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;

String keyword = Attrs.currentContext().get("keyword") as String;
keyword = keyword.trim();
RemoteCase remoteCase = Attrs.currentContext().get("foundCase") as RemoteCase;

String title = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/case"), keyword));
GlobalCaseId caseId = GlobalCaseId.inServer(remoteCase.server.id).caseId(remoteCase.id).build();
CaseLazyDataModel dataModel = new CaseLazyDataModel();
dataModel.setKeyword(keyword);

out.view = ch.ivy.addon.portal.generic.view.CaseView.create().dataModel(dataModel).withTitle(title).autoSelectIfExists(caseId).buildNewView();' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 53 181 22 22 14 0 #rect
Cs0 f8 @|RichDialogProcessStartIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 75 192 246 192 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f8 mainOut f9 tail #connect
Cs0 f9 head f6 mainIn #connect
