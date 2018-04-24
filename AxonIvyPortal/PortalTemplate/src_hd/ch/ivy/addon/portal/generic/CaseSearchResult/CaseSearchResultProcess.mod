[Ivy]
15438977A8AF4FC1 3.20 #module
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
Cs0 @CallSub f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @GridStep f7 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f5 '' #zField
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
Cs0 f6 494 180 36 24 20 -2 #rect
Cs0 f6 @|CallSubIcon #fIcon
Cs0 f8 guid 1543BE219788553F #txt
Cs0 f8 type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
Cs0 f8 actionDecl 'ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 53 181 22 22 -48 17 #rect
Cs0 f8 @|RichDialogProcessStartIcon #fIcon
Cs0 f3 type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
Cs0 f3 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Cs0 f3 doCall true #txt
Cs0 f3 requestActionDecl '<> param;
' #txt
Cs0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData out;
' #txt
Cs0 f3 responseMappingAction 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InitializeCaseDataModel</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 120 170 144 44 -66 -8 #rect
Cs0 f3 @|CallSubIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 75 192 120 192 #arcP
Cs0 f7 actionDecl 'ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData out;
' #txt
Cs0 f7 actionTable 'out=in;
' #txt
Cs0 f7 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portal.generic.view.CaseView;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;

String keyword = Attrs.currentContext().get("keyword") as String;
keyword = keyword.trim();
RemoteCase remoteCase = Attrs.currentContext().get("foundCase") as RemoteCase;

String title = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/case"), keyword));
GlobalCaseId caseId = GlobalCaseId.inServer(remoteCase.server.id).caseId(remoteCase.id).build();
in.caseDataModel.queryCriteria.setKeyword(keyword);
in.caseDataModel.setIgnoreInvolvedUser(PermissionUtils.checkReadAllCasesPermission());
in.caseDataModel.setNotKeepFilter(true);
out.view = ch.ivy.addon.portal.generic.view.CaseView.create().dataModel(in.caseDataModel).withTitle(title).autoSelectIfExists(caseId).buildNewView();' #txt
Cs0 f7 type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize data</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 328 170 112 44 -35 -8 #rect
Cs0 f7 @|StepIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 264 192 328 192 #arcP
Cs0 f5 expr out #txt
Cs0 f5 440 192 494 192 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CaseSearchResult.CaseSearchResultData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f8 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f3 mainOut f9 tail #connect
Cs0 f9 head f7 mainIn #connect
Cs0 f7 mainOut f5 tail #connect
Cs0 f5 head f6 mainIn #connect
