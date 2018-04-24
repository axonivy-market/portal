[Ivy]
[>Created: Wed Sep 09 14:48:29 ICT 2015]
14C4FB0A03F4E19C 3.17 #module
>Proto >Proto Collection #zClass
Cs0 CaseTableProcess Big #zClass
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
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogMethodStart f9 '' #zField
Cs0 @RichDialogProcessEnd f10 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @RichDialogProcessEnd f15 '' #zField
Cs0 @RichDialogMethodStart f14 '' #zField
Cs0 @GridStep f17 '' #zField
Cs0 @PushWFArc f18 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @RichDialogInitStart f2 '' #zField
Cs0 @GridStep f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @RichDialogInitStart f19 '' #zField
Cs0 @RichDialogInitStart f20 '' #zField
Cs0 @GridStep f23 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @GridStep f25 '' #zField
Cs0 @PushWFArc f26 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @RichDialogInitStart f27 '' #zField
Cs0 @PushWFArc f31 '' #zField
Cs0 @RichDialogProcessStart f28 '' #zField
Cs0 @RichDialogProcessEnd f29 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @RichDialogInitStart f32 '' #zField
Cs0 @GridStep f33 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @PushWFArc f35 '' #zField
Cs0 @RichDialogInitStart f36 '' #zField
Cs0 @GridStep f37 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f39 '' #zField
Cs0 @RichDialogInitStart f40 '' #zField
Cs0 @GridStep f41 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @RichDialogInitStart f43 '' #zField
Cs0 @RichDialogInitStart f44 '' #zField
Cs0 @RichDialogInitStart f45 '' #zField
Cs0 @RichDialogInitStart f46 '' #zField
Cs0 @RichDialogInitStart f47 '' #zField
Cs0 @GridStep f48 '' #zField
Cs0 @RichDialogProcessEnd f49 '' #zField
Cs0 @PushWFArc f50 '' #zField
Cs0 @PushWFArc f51 '' #zField
Cs0 @PushWFArc f52 '' #zField
Cs0 @GridStep f53 '' #zField
Cs0 @PushWFArc f54 '' #zField
Cs0 @PushWFArc f55 '' #zField
Cs0 @GridStep f56 '' #zField
Cs0 @PushWFArc f57 '' #zField
Cs0 @PushWFArc f58 '' #zField
Cs0 @GridStep f59 '' #zField
Cs0 @PushWFArc f60 '' #zField
Cs0 @PushWFArc f61 '' #zField
Cs0 @GridStep f62 '' #zField
Cs0 @PushWFArc f63 '' #zField
Cs0 @PushWFArc f64 '' #zField
>Proto Cs0 Cs0 CaseTableProcess #zField
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f1 758 286 20 20 13 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f0 guid 14C4FDD62F115B73 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f0 method runningCases() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>runningCases()</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 38 62 20 20 13 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesRunning();' #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get running cases</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 30 132 36 24 20 -2 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 48 82 48 132 #arcP
Cs0 f5 expr out #txt
Cs0 f5 48 156 758 296 #arcP
Cs0 f5 1 48 296 #addKink
Cs0 f5 0 0.939769004372651 0 0 #arcLabel
Cs0 f9 guid 14C540158552DC40 #txt
Cs0 f9 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f9 method deleteCase(ch.ivy.addon.portalkit.vo.CaseVO) #txt
Cs0 f9 disableUIEvents false #txt
Cs0 f9 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.vo.CaseVO case> param = methodEvent.getInputArguments();
' #txt
Cs0 f9 inParameterMapAction 'out.selectedCase=param.case;
' #txt
Cs0 f9 outParameterDecl '<> result;
' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteCase(CaseVO)</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 1838 62 20 20 13 0 #rect
Cs0 f9 @|RichDialogMethodStartIcon #fIcon
Cs0 f10 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f10 1838 206 20 20 13 0 #rect
Cs0 f10 @|RichDialogProcessEndIcon #fIcon
Cs0 f11 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f11 actionTable 'out=in;
' #txt
Cs0 f11 actionCode 'import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivy.addon.portalkit.util.CaseUtils;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
if (in.#selectedCase != null){
	boolean deleteResult = CaseUtils.deleteCase(in.selectedCase);
	if (deleteResult){
			in.selectedCase.setStatus(CaseState.DESTROYED.toString());
			FacesMessage fm = new FacesMessage();
			fm.summary = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/information");;
			fm.detail = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/destroysucceed");
			fm.severity = FacesMessage.SEVERITY_INFO;
			FacesContext.getCurrentInstance().addMessage(null,fm);
	}else{
		FacesMessage fm = new FacesMessage();
			fm.summary = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/error");
			fm.detail = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/destroyfail");
			fm.severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().addMessage(null,fm);
  }
}

' #txt
Cs0 f11 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroy &amp; show message</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 1830 132 36 24 20 -2 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 1848 82 1848 132 #arcP
Cs0 f13 expr out #txt
Cs0 f13 1848 156 1848 206 #arcP
Cs0 f15 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f15 2046 206 20 20 13 0 #rect
Cs0 f15 @|RichDialogProcessEndIcon #fIcon
Cs0 f14 guid 14C540E80E2C16CF #txt
Cs0 f14 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f14 method confirmDeleteCase(ch.ivy.addon.portalkit.vo.CaseVO) #txt
Cs0 f14 disableUIEvents false #txt
Cs0 f14 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.vo.CaseVO case> param = methodEvent.getInputArguments();
' #txt
Cs0 f14 inParameterMapAction 'out.selectedCase=param.case;
' #txt
Cs0 f14 outParameterDecl '<> result;
' #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>confirmDeleteCase(CaseVO)</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f14 2046 62 20 20 13 0 #rect
Cs0 f14 @|RichDialogMethodStartIcon #fIcon
Cs0 f17 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f17 actionTable 'out=in;
' #txt
Cs0 f17 actionCode 'import org.primefaces.context.RequestContext;

RequestContext.getCurrentInstance().execute("PF(''confirmation'').show()");' #txt
Cs0 f17 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show confirm diaglog</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f17 2038 132 36 24 20 -2 #rect
Cs0 f17 @|StepIcon #fIcon
Cs0 f18 expr out #txt
Cs0 f18 2056 82 2056 132 #arcP
Cs0 f16 expr out #txt
Cs0 f16 2056 156 2056 206 #arcP
Cs0 f2 guid 14C687A4DAA70AE9 #txt
Cs0 f2 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f2 method finishedCases() #txt
Cs0 f2 disableUIEvents true #txt
Cs0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f2 outParameterDecl '<> result;
' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finishedCases()</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f2 470 62 20 20 13 0 #rect
Cs0 f2 @|RichDialogInitStartIcon #fIcon
Cs0 f7 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f7 actionTable 'out=in;
' #txt
Cs0 f7 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesFinished();' #txt
Cs0 f7 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get finished cases</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 462 132 36 24 20 -2 #rect
Cs0 f7 @|StepIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 480 82 480 132 #arcP
Cs0 f8 0 0.29018383772324674 0 0 #arcLabel
Cs0 f6 expr out #txt
Cs0 f6 480 156 758 296 #arcP
Cs0 f6 1 480 296 #addKink
Cs0 f6 1 0.3073466765784763 0 0 #arcLabel
Cs0 f19 guid 14CA23C9A9348E16 #txt
Cs0 f19 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f19 method runningCasesByCategory(String) #txt
Cs0 f19 disableUIEvents true #txt
Cs0 f19 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String processCategoryCode> param = methodEvent.getInputArguments();
' #txt
Cs0 f19 inParameterMapAction 'out.processCategoryCode=param.processCategoryCode;
' #txt
Cs0 f19 outParameterDecl '<> result;
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>runningCasesByCategory(String)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f19 694 62 20 20 13 0 #rect
Cs0 f19 @|RichDialogInitStartIcon #fIcon
Cs0 f20 guid 14CA23CC6A4BB568 #txt
Cs0 f20 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f20 method finishedCasesByCategory(String) #txt
Cs0 f20 disableUIEvents true #txt
Cs0 f20 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String processCategoryCode> param = methodEvent.getInputArguments();
' #txt
Cs0 f20 inParameterMapAction 'out.processCategoryCode=param.processCategoryCode;
' #txt
Cs0 f20 outParameterDecl '<> result;
' #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finishedCasesByCategory(String)</name>
        <nameStyle>31,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f20 982 62 20 20 13 0 #rect
Cs0 f20 @|RichDialogInitStartIcon #fIcon
Cs0 f23 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f23 actionTable 'out=in;
' #txt
Cs0 f23 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesRunningByCategory(in.processCategoryCode);' #txt
Cs0 f23 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get running 
cases by category</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f23 686 132 36 24 27 -9 #rect
Cs0 f23 @|StepIcon #fIcon
Cs0 f24 expr out #txt
Cs0 f24 704 82 704 132 #arcP
Cs0 f24 0 0.4976317647224063 0 0 #arcLabel
Cs0 f21 expr out #txt
Cs0 f21 704 156 758 296 #arcP
Cs0 f21 1 704 296 #addKink
Cs0 f21 1 0.31398530304255723 0 0 #arcLabel
Cs0 f25 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f25 actionTable 'out=in;
' #txt
Cs0 f25 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesFinishedByCategory(in.processCategoryCode);' #txt
Cs0 f25 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get finished
cases by category</name>
        <nameStyle>13,7
17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f25 974 132 36 24 20 -2 #rect
Cs0 f25 @|StepIcon #fIcon
Cs0 f26 expr out #txt
Cs0 f26 992 82 992 132 #arcP
Cs0 f26 0 0.49433572184394203 0 0 #arcLabel
Cs0 f22 expr out #txt
Cs0 f22 992 156 778 296 #arcP
Cs0 f22 1 992 296 #addKink
Cs0 f22 1 0.012804976430422813 0 0 #arcLabel
Cs0 f27 guid 14D702D4F89DF60A #txt
Cs0 f27 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f27 method startWithCaseList(java.util.List<ch.ivyteam.ivy.workflow.ICase>) #txt
Cs0 f27 disableUIEvents true #txt
Cs0 f27 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivyteam.ivy.workflow.ICase> iCases> param = methodEvent.getInputArguments();
' #txt
Cs0 f27 inParameterMapAction 'out.iCases=param.iCases;
' #txt
Cs0 f27 inActionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
if (param.iCases != null && param.iCases.size() > 0){
	for (ICase iCase : param.iCases){
		out.cases.add(CaseUtils.convertToCaseVO(iCase));
	}
}' #txt
Cs0 f27 outParameterDecl '<> result;
' #txt
Cs0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithCaseList(List&lt;ICase&gt;)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f27 1558 62 20 20 13 0 #rect
Cs0 f27 @|RichDialogInitStartIcon #fIcon
Cs0 f31 expr out #txt
Cs0 f31 1568 82 778 296 #arcP
Cs0 f31 1 1568 296 #addKink
Cs0 f31 1 0.37575631879349997 0 0 #arcLabel
Cs0 f28 guid 14E00BB2067A17BB #txt
Cs0 f28 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f28 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f28 actionTable 'out=in;
' #txt
Cs0 f28 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
out.cases.clear();
if (in.iCases != null && in.iCases.size() > 0){
	for (ICase iCase : in.iCases){
		out.cases.add(CaseUtils.convertToCaseVO(iCase));
	}
}' #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f28 2286 54 20 20 13 0 #rect
Cs0 f28 @|RichDialogProcessStartIcon #fIcon
Cs0 f29 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f29 2286 150 20 20 13 0 #rect
Cs0 f29 @|RichDialogProcessEndIcon #fIcon
Cs0 f30 2296 74 2296 150 #arcP
Cs0 f32 guid 14E53747D4D1C4DB #txt
Cs0 f32 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f32 method startWithIPropertyFilter(ch.ivyteam.ivy.workflow.IPropertyFilter) #txt
Cs0 f32 disableUIEvents true #txt
Cs0 f32 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.IPropertyFilter propertyFilter> param = methodEvent.getInputArguments();
' #txt
Cs0 f32 inParameterMapAction 'out.propertyFilter=param.propertyFilter;
' #txt
Cs0 f32 outParameterDecl '<> result;
' #txt
Cs0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithIPropertyFilter(IPropertyFilter)</name>
        <nameStyle>41,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f32 1269 61 22 22 14 0 #rect
Cs0 f32 @|RichDialogInitStartIcon #fIcon
Cs0 f33 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f33 actionTable 'out=in;
' #txt
Cs0 f33 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.iCases = CaseUtils.findICases(in.propertyFilter);
out.cases=CaseUtils.convertICasesToCaseVOs(out.iCases);' #txt
Cs0 f33 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Gets Cases By IPropertyFiler</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f33 1262 132 36 24 24 -10 #rect
Cs0 f33 @|StepIcon #fIcon
Cs0 f34 expr out #txt
Cs0 f34 1280 83 1280 132 #arcP
Cs0 f34 0 0.18384861071884329 0 0 #arcLabel
Cs0 f35 expr out #txt
Cs0 f35 1280 156 778 296 #arcP
Cs0 f35 1 1280 296 #addKink
Cs0 f35 1 0.18384861071884329 0 0 #arcLabel
Cs0 f36 guid 14FB013F8494DD21 #txt
Cs0 f36 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f36 method notZombieCases() #txt
Cs0 f36 disableUIEvents true #txt
Cs0 f36 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f36 outParameterDecl '<> result;
' #txt
Cs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>notZombieCases()</name>
    </language>
</elementInfo>
' #txt
Cs0 f36 246 62 20 20 13 0 #rect
Cs0 f36 @|RichDialogInitStartIcon #fIcon
Cs0 f37 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f37 actionTable 'out=in;
' #txt
Cs0 f37 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesNotZombie();' #txt
Cs0 f37 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get not zombie cases</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f37 238 132 36 24 20 -2 #rect
Cs0 f37 @|StepIcon #fIcon
Cs0 f38 expr out #txt
Cs0 f38 256 82 256 132 #arcP
Cs0 f39 expr out #txt
Cs0 f39 256 156 758 296 #arcP
Cs0 f39 1 256 296 #addKink
Cs0 f39 1 0.35480845256396515 0 0 #arcLabel
Cs0 f40 guid 14FB06EE5B79994A #txt
Cs0 f40 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f40 method runningCasesOfSessionUser() #txt
Cs0 f40 disableUIEvents true #txt
Cs0 f40 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f40 outParameterDecl '<> result;
' #txt
Cs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>runningCasesOfSessionUser()</name>
    </language>
</elementInfo>
' #txt
Cs0 f40 38 342 20 20 11 2 #rect
Cs0 f40 @|RichDialogInitStartIcon #fIcon
Cs0 f41 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f41 actionTable 'out=in;
' #txt
Cs0 f41 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesRunningOfSessionUser();' #txt
Cs0 f41 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f41 30 404 36 24 20 -2 #rect
Cs0 f41 @|StepIcon #fIcon
Cs0 f42 expr out #txt
Cs0 f42 48 362 48 404 #arcP
Cs0 f43 guid 14FB06F8DD4EB48A #txt
Cs0 f43 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f43 method finishedCasesOfSessionUser() #txt
Cs0 f43 disableUIEvents true #txt
Cs0 f43 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f43 outParameterDecl '<> result;
' #txt
Cs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finishedCasesOfSessionUser()</name>
    </language>
</elementInfo>
' #txt
Cs0 f43 470 342 20 20 13 0 #rect
Cs0 f43 @|RichDialogInitStartIcon #fIcon
Cs0 f44 guid 14FB06FAA309B673 #txt
Cs0 f44 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f44 method runningCasesOfSessionUserByCategory(String) #txt
Cs0 f44 disableUIEvents true #txt
Cs0 f44 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String processCategoryCode> param = methodEvent.getInputArguments();
' #txt
Cs0 f44 inParameterMapAction 'out.processCategoryCode=param.processCategoryCode;
' #txt
Cs0 f44 outParameterDecl '<> result;
' #txt
Cs0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>runningCasesOfSessionUserByCategory(String)</name>
    </language>
</elementInfo>
' #txt
Cs0 f44 694 342 20 20 13 0 #rect
Cs0 f44 @|RichDialogInitStartIcon #fIcon
Cs0 f45 guid 14FB06FC748A0A55 #txt
Cs0 f45 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f45 method finishedCasesOfSessionUserByCategory(String) #txt
Cs0 f45 disableUIEvents true #txt
Cs0 f45 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String processCategoryCode> param = methodEvent.getInputArguments();
' #txt
Cs0 f45 inParameterMapAction 'out.processCategoryCode=param.processCategoryCode;
' #txt
Cs0 f45 outParameterDecl '<> result;
' #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finishedCasesOfSessionUserByCategory(String)</name>
    </language>
</elementInfo>
' #txt
Cs0 f45 982 342 20 20 13 0 #rect
Cs0 f45 @|RichDialogInitStartIcon #fIcon
Cs0 f46 guid 14FB06FDEF6BCBB5 #txt
Cs0 f46 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f46 method notZombieCasesOfSessionUser() #txt
Cs0 f46 disableUIEvents true #txt
Cs0 f46 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f46 outParameterDecl '<> result;
' #txt
Cs0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>notZombieCasesOfSessionUser()</name>
    </language>
</elementInfo>
' #txt
Cs0 f46 246 342 20 20 13 0 #rect
Cs0 f46 @|RichDialogInitStartIcon #fIcon
Cs0 f47 guid 14FB06FF2E4A1DDB #txt
Cs0 f47 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f47 method startWithPropertyFilterAndSessionUser(ch.ivyteam.ivy.workflow.IPropertyFilter) #txt
Cs0 f47 disableUIEvents true #txt
Cs0 f47 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.IPropertyFilter propertyFilter> param = methodEvent.getInputArguments();
' #txt
Cs0 f47 inParameterMapAction 'out.propertyFilter=param.propertyFilter;
' #txt
Cs0 f47 outParameterDecl '<> result;
' #txt
Cs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startWithPropertyFilterAndSessionUser(IPropertyFilter)</name>
    </language>
</elementInfo>
' #txt
Cs0 f47 1270 342 20 20 13 0 #rect
Cs0 f47 @|RichDialogInitStartIcon #fIcon
Cs0 f48 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f48 actionTable 'out=in;
' #txt
Cs0 f48 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesNotZombieOfSessionUser();' #txt
Cs0 f48 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f48 238 404 36 24 20 -2 #rect
Cs0 f48 @|StepIcon #fIcon
Cs0 f49 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f49 758 534 20 20 13 0 #rect
Cs0 f49 @|RichDialogProcessEndIcon #fIcon
Cs0 f50 expr out #txt
Cs0 f50 48 428 758 544 #arcP
Cs0 f50 1 48 544 #addKink
Cs0 f50 1 0.4110641906638285 0 0 #arcLabel
Cs0 f51 expr out #txt
Cs0 f51 256 362 256 404 #arcP
Cs0 f52 expr out #txt
Cs0 f52 256 428 758 544 #arcP
Cs0 f52 1 256 544 #addKink
Cs0 f52 1 0.3761873741014448 0 0 #arcLabel
Cs0 f53 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f53 actionTable 'out=in;
' #txt
Cs0 f53 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesFinishedOfSessionUser();' #txt
Cs0 f53 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f53 462 404 36 24 20 -2 #rect
Cs0 f53 @|StepIcon #fIcon
Cs0 f54 expr out #txt
Cs0 f54 480 362 480 404 #arcP
Cs0 f55 expr out #txt
Cs0 f55 480 428 758 544 #arcP
Cs0 f55 1 480 544 #addKink
Cs0 f55 1 0.28179340074104114 0 0 #arcLabel
Cs0 f56 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f56 actionTable 'out=in;
' #txt
Cs0 f56 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesRunningOfSessionUserByCategory(in.processCategoryCode);' #txt
Cs0 f56 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f56 686 404 36 24 20 -2 #rect
Cs0 f56 @|StepIcon #fIcon
Cs0 f57 expr out #txt
Cs0 f57 704 362 704 404 #arcP
Cs0 f58 expr out #txt
Cs0 f58 704 428 758 544 #arcP
Cs0 f58 1 704 544 #addKink
Cs0 f58 0 0.7569086905962579 0 0 #arcLabel
Cs0 f59 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f59 actionTable 'out=in;
' #txt
Cs0 f59 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.cases = CaseUtils.findCasesFinishedOfSessionUserByCategory(in.processCategoryCode);' #txt
Cs0 f59 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f59 974 404 36 24 20 -2 #rect
Cs0 f59 @|StepIcon #fIcon
Cs0 f60 expr out #txt
Cs0 f60 992 362 992 404 #arcP
Cs0 f61 expr out #txt
Cs0 f61 992 428 778 544 #arcP
Cs0 f61 1 992 544 #addKink
Cs0 f61 1 0.22376615911254016 0 0 #arcLabel
Cs0 f62 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData out;
' #txt
Cs0 f62 actionTable 'out=in;
' #txt
Cs0 f62 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.iCases = CaseUtils.findICasesOfSessionUser(in.propertyFilter);
out.cases=CaseUtils.convertICasesToCaseVOs(out.iCases);' #txt
Cs0 f62 type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
Cs0 f62 1262 404 36 24 20 -2 #rect
Cs0 f62 @|StepIcon #fIcon
Cs0 f63 expr out #txt
Cs0 f63 1280 362 1280 404 #arcP
Cs0 f64 expr out #txt
Cs0 f64 1280 428 778 544 #arcP
Cs0 f64 1 1280 544 #addKink
Cs0 f64 1 0.37643351369870887 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseTable.CaseTableData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start methods</swimlaneLabel>
        <swimlaneLabel>Methods</swimlaneLabel>
        <swimlaneLabel>Events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>1760</swimlaneSize>
    <swimlaneSize>472</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f1 mainIn #connect
Cs0 f9 mainOut f12 tail #connect
Cs0 f12 head f11 mainIn #connect
Cs0 f11 mainOut f13 tail #connect
Cs0 f13 head f10 mainIn #connect
Cs0 f14 mainOut f18 tail #connect
Cs0 f18 head f17 mainIn #connect
Cs0 f17 mainOut f16 tail #connect
Cs0 f16 head f15 mainIn #connect
Cs0 f2 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f7 mainOut f6 tail #connect
Cs0 f6 head f1 mainIn #connect
Cs0 f19 mainOut f24 tail #connect
Cs0 f24 head f23 mainIn #connect
Cs0 f23 mainOut f21 tail #connect
Cs0 f21 head f1 mainIn #connect
Cs0 f20 mainOut f26 tail #connect
Cs0 f26 head f25 mainIn #connect
Cs0 f25 mainOut f22 tail #connect
Cs0 f22 head f1 mainIn #connect
Cs0 f27 mainOut f31 tail #connect
Cs0 f31 head f1 mainIn #connect
Cs0 f28 mainOut f30 tail #connect
Cs0 f30 head f29 mainIn #connect
Cs0 f32 mainOut f34 tail #connect
Cs0 f34 head f33 mainIn #connect
Cs0 f33 mainOut f35 tail #connect
Cs0 f35 head f1 mainIn #connect
Cs0 f36 mainOut f38 tail #connect
Cs0 f38 head f37 mainIn #connect
Cs0 f37 mainOut f39 tail #connect
Cs0 f39 head f1 mainIn #connect
Cs0 f40 mainOut f42 tail #connect
Cs0 f42 head f41 mainIn #connect
Cs0 f41 mainOut f50 tail #connect
Cs0 f50 head f49 mainIn #connect
Cs0 f46 mainOut f51 tail #connect
Cs0 f51 head f48 mainIn #connect
Cs0 f48 mainOut f52 tail #connect
Cs0 f52 head f49 mainIn #connect
Cs0 f43 mainOut f54 tail #connect
Cs0 f54 head f53 mainIn #connect
Cs0 f53 mainOut f55 tail #connect
Cs0 f55 head f49 mainIn #connect
Cs0 f44 mainOut f57 tail #connect
Cs0 f57 head f56 mainIn #connect
Cs0 f56 mainOut f58 tail #connect
Cs0 f58 head f49 mainIn #connect
Cs0 f45 mainOut f60 tail #connect
Cs0 f60 head f59 mainIn #connect
Cs0 f59 mainOut f61 tail #connect
Cs0 f61 head f49 mainIn #connect
Cs0 f47 mainOut f63 tail #connect
Cs0 f63 head f62 mainIn #connect
Cs0 f62 mainOut f64 tail #connect
Cs0 f64 head f49 mainIn #connect
