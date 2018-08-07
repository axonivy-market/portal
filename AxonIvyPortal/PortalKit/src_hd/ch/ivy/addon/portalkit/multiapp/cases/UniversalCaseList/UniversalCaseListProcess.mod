[Ivy]
[>Created: Fri Apr 10 15:48:21 ICT 2015]
14BCA504E9D78C32 3.17 #module
>Proto >Proto Collection #zClass
Cs0 UniversalCaseListProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f2 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogProcessStart f7 '' #zField
Cs0 @RichDialogProcessEnd f8 '' #zField
Cs0 @GridStep f15 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @CallSub f47 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @CallSub f6 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f11 '' #zField
>Proto Cs0 Cs0 UniversalCaseListProcess #zField
Cs0 f0 guid 14749DFCD54AB7CC #txt
Cs0 f0 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inActionCode 'out.user = ivy.session.getSessionUserName();' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 30 30 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f1 454 30 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 actionDecl 'ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData out;
' #txt
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'import java.util.Calendar;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivy.addon.portalkit.lazy.CaseDataModel;
import javax.faces.context.FacesContext;
import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;

//Create error message
if(in.errors.size()>0){
	in.errorLink = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/found") + in.errors.size() + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/clickToShowDetail");
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification"), "<span style=\"cursor:pointer\"><a onclick=\"PF(''errorDialogUniversalCase'').show(); hideGrowl()\">"+in.errorLink+"</a></span>"));
	}

//init list of application
CaseDataModel tdm = new CaseDataModel(in.cases);
in.apps = tdm.getApplications();
in.selectedApps = tdm.getApplications();

//init workflow priorities
in.selectedWorkflowPriorities.add("HIGH").add("NORMAL").add("LOW");

//init time filter
Calendar c = Calendar.getInstance();
in.createdTo = null;// = c.getTime();
in.createdFrom = null;// = c.getTime();

' #txt
Cs0 f2 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f2 382 28 36 24 20 -2 #rect
Cs0 f2 @|StepIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 418 40 454 40 #arcP
Cs0 f7 guid 14758A4171446E45 #txt
Cs0 f7 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f7 actionDecl 'ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData out;
' #txt
Cs0 f7 actionTable 'out=in;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onFilter</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 30 118 20 20 13 0 #rect
Cs0 f7 @|RichDialogProcessStartIcon #fIcon
Cs0 f8 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f8 454 118 20 20 13 0 #rect
Cs0 f8 @|RichDialogProcessEndIcon #fIcon
Cs0 f15 actionDecl 'ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData out;
' #txt
Cs0 f15 actionTable 'out=in;
' #txt
Cs0 f15 actionCode 'import ch.ivy.addon.portalkit.lazy.CaseDataModel;
import javax.faces.context.FacesContext;
import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;

//Create error message
if(in.errors.size()>0){
	in.errorLink = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/found") + in.errors.size() + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/clickToShowDetail");
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification"), "<span style=\"cursor:pointer\"><a onclick=\"PF(''errorDialogUniversalCase'').show(); hideGrowl()\">"+in.errorLink+"</a></span>"));
	}


CaseDataModel tdm = new CaseDataModel(in.cases);
tdm.filterByApps(in.selectedApps);
in.cases = tdm.getCases();' #txt
Cs0 f15 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f15 382 116 36 24 20 -2 #rect
Cs0 f15 @|StepIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 418 128 454 128 #arcP
Cs0 f47 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f47 processCall MultiPortal/CasesTasksFilter:findCases(String,java.util.Date,java.util.Date,List<String>) #txt
Cs0 f47 doCall true #txt
Cs0 f47 requestActionDecl '<java.lang.String user,java.util.Date createdFrom,java.util.Date createdTo,List<java.lang.String> priorities> param;
' #txt
Cs0 f47 requestMappingAction 'param.user=in.user;
param.createdFrom=in.createdFrom;
param.createdTo=in.createdTo;
param.priorities=in.selectedWorkflowPriorities;
' #txt
Cs0 f47 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData out;
' #txt
Cs0 f47 responseMappingAction 'out=in;
out.cases=result.cases;
out.errors=result.errors;
' #txt
Cs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCases(String,Date,Date,List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Cs0 f47 102 28 36 24 20 -2 #rect
Cs0 f47 @|CallSubIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 50 40 102 40 #arcP
Cs0 f4 expr out #txt
Cs0 f4 138 40 382 40 #arcP
Cs0 f6 type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
Cs0 f6 processCall MultiPortal/CasesTasksFilter:findCases(String,java.util.Date,java.util.Date,List<String>) #txt
Cs0 f6 doCall true #txt
Cs0 f6 requestActionDecl '<java.lang.String user,java.util.Date createdFrom,java.util.Date createdTo,List<java.lang.String> priorities> param;
' #txt
Cs0 f6 requestMappingAction 'param.user=in.user;
param.createdFrom=in.createdFrom;
param.createdTo=in.createdTo;
param.priorities=in.selectedWorkflowPriorities;
' #txt
Cs0 f6 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData out;
' #txt
Cs0 f6 responseMappingAction 'out=in;
out.cases=result.cases;
out.errors=result.errors;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCases(String,Date,Date,List&lt;String&gt;)</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 102 116 36 24 20 -2 #rect
Cs0 f6 @|CallSubIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 50 128 102 128 #arcP
Cs0 f11 expr out #txt
Cs0 f11 138 128 382 128 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.multiapp.cases.UniversalCaseList.UniversalCaseListData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f2 mainOut f5 tail #connect
Cs0 f5 head f1 mainIn #connect
Cs0 f15 mainOut f9 tail #connect
Cs0 f9 head f8 mainIn #connect
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f47 mainIn #connect
Cs0 f47 mainOut f4 tail #connect
Cs0 f4 head f2 mainIn #connect
Cs0 f7 mainOut f10 tail #connect
Cs0 f10 head f6 mainIn #connect
Cs0 f6 mainOut f11 tail #connect
Cs0 f11 head f15 mainIn #connect
