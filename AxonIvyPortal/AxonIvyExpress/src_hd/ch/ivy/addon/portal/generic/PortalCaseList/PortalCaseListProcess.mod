[Ivy]
[>Created: Fri Mar 17 15:42:23 CET 2017]
14C4FF3FCD291EF5 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalCaseListProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f6 '' #zField
Ps0 @RichDialogProcessEnd f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @RichDialogProcessEnd f10 '' #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogMethodStart f9 '' #zField
Ps0 @GridStep f33 '' #zField
Ps0 @CallSub f39 '' #zField
Ps0 @PushWFArc f40 '' #zField
Ps0 @GridStep f43 '' #zField
Ps0 @CallSub f45 '' #zField
Ps0 @PushWFArc f46 '' #zField
Ps0 @GridStep f48 '' #zField
Ps0 @PushWFArc f49 '' #zField
Ps0 @RichDialogProcessEnd f51 '' #zField
Ps0 @PushWFArc f52 '' #zField
Ps0 @PushWFArc f53 '' #zField
Ps0 @GridStep f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @GridStep f12 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @CallSub f14 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 PortalCaseListProcess #zField
Ps0 f6 guid 14FB0E758E2264A8 #txt
Ps0 f6 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f6 method start(Boolean) #txt
Ps0 f6 disableUIEvents true #txt
Ps0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Boolean loadAllCases> param = methodEvent.getInputArguments();
' #txt
Ps0 f6 inParameterMapAction 'out.loadAllCases=param.loadAllCases;
' #txt
Ps0 f6 outParameterDecl '<> result;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Boolean)</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 326 54 20 20 21 -10 #rect
Ps0 f6 @|RichDialogInitStartIcon #fIcon
Ps0 f7 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f7 326 214 20 20 13 0 #rect
Ps0 f7 @|RichDialogProcessEndIcon #fIcon
Ps0 f8 expr out #txt
Ps0 f8 336 74 336 214 #arcP
Ps0 f10 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f10 534 222 20 20 13 0 #rect
Ps0 f10 @|RichDialogProcessEndIcon #fIcon
Ps0 f0 guid 1506F94E55FDC414 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 118 54 20 20 13 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f9 guid 15897DCF36F92405 #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f9 method onCaseNodeSelect(org.primefaces.event.NodeSelectEvent) #txt
Ps0 f9 disableUIEvents false #txt
Ps0 f9 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.NodeSelectEvent event> param = methodEvent.getInputArguments();
' #txt
Ps0 f9 outParameterDecl '<> result;
' #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onCaseNodeSelect(NodeSelectEvent)</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 534 54 20 20 13 0 #rect
Ps0 f9 @|RichDialogMethodStartIcon #fIcon
Ps0 f33 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f33 actionTable 'out=in;
' #txt
Ps0 f33 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;

in.isAdmin = CaseUtils.checkReadAllCasesPermission();' #txt
Ps0 f33 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check is Admin ?</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f33 110 116 36 24 20 -2 #rect
Ps0 f33 @|StepIcon #fIcon
Ps0 f39 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f39 processCall 'Functional Processes/CustomPortalListsConfig:call()' #txt
Ps0 f39 doCall true #txt
Ps0 f39 requestActionDecl '<> param;
' #txt
Ps0 f39 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f39 responseMappingAction 'out=in;
out.portalListsConfig=result.portalListsConfig;
' #txt
Ps0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomPortalListsConfig</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f39 110 180 36 24 20 -2 #rect
Ps0 f39 @|CallSubIcon #fIcon
Ps0 f40 expr out #txt
Ps0 f40 128 140 128 180 #arcP
Ps0 f43 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f43 actionTable 'out=in;
' #txt
Ps0 f43 actionCode 'import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CaseUtils;

// Find all personal cases
in.userCasesTemp = CaseUtils.findICasesNotZombieOfSessionUser();

//Find all cases if Admin
if (in.isAdmin) {
	in.adminCasesTemp = CaseUtils.findICasesNotZombie();
	
}






' #txt
Ps0 f43 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find cases</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f43 110 244 36 24 20 -2 #rect
Ps0 f43 @|StepIcon #fIcon
Ps0 f45 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f45 processCall 'Functional Processes/GetPortalConfig:call()' #txt
Ps0 f45 doCall true #txt
Ps0 f45 requestActionDecl '<> param;
' #txt
Ps0 f45 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f45 responseMappingAction 'out=in;
out.homeLink=result.portalConfig.homeLink;
' #txt
Ps0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getPortalConfig</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f45 110 436 36 24 20 -2 #rect
Ps0 f45 @|CallSubIcon #fIcon
Ps0 f46 expr out #txt
Ps0 f46 128 204 128 244 #arcP
Ps0 f48 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f48 actionTable 'out=in;
' #txt
Ps0 f48 actionCode 'import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;

in.caseLazyDataModel = new CaseLazyDataModel(in.selectedCases);
' #txt
Ps0 f48 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>lazy cases</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f48 110 500 36 24 20 -2 #rect
Ps0 f48 @|StepIcon #fIcon
Ps0 f49 expr out #txt
Ps0 f49 128 460 128 500 #arcP
Ps0 f51 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f51 117 565 22 22 14 0 #rect
Ps0 f51 @|RichDialogProcessEndIcon #fIcon
Ps0 f52 expr out #txt
Ps0 f52 128 524 128 565 #arcP
Ps0 f53 expr out #txt
Ps0 f53 128 74 128 116 #arcP
Ps0 f1 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f1 actionTable 'out=in;
' #txt
Ps0 f1 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;

if(in.selectedCaseNode != null && in.selectedCaseNode.getData() != null) {
	CaseNode nodeData = in.selectedCaseNode.getData() as CaseNode;
	out.selectedCases = CaseUtils.convertICasesToCaseVOs(nodeData.cases);
}

in.caseLazyDataModel = new CaseLazyDataModel(in.selectedCases);

' #txt
Ps0 f1 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update case table</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f1 526 132 36 24 20 -2 #rect
Ps0 f1 @|StepIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 544 74 544 132 #arcP
Ps0 f3 expr out #txt
Ps0 f3 544 156 544 222 #arcP
Ps0 f12 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 actionCode 'import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CaseUtils;



// Build case trees
	in.userCaseTree = CaseTreeUtils.convertToTreeNode(in.userCasesTemp, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseTree/myCases"));

if (in.isAdmin) {
	
	// Build case trees.
	in.adminCaseTree = CaseTreeUtils.convertToTreeNode(in.adminCasesTemp, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/allCases"));
	
	//Initialize for CaseTable
	in.selectedCases = CaseUtils.convertICasesToCaseVOs(in.userCasesTemp);
	
} else {
	//Initialize for CaseTable
	in.selectedCases = CaseUtils.convertICasesToCaseVOs(in.userCasesTemp);

	
}






' #txt
Ps0 f12 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build case trees</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 110 372 36 24 20 -2 #rect
Ps0 f12 @|StepIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 128 396 128 436 #arcP
Ps0 f14 type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
Ps0 f14 processCall 'Functional Processes/CustomPortalStandardCaseDetailsConfig:call(ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData)' #txt
Ps0 f14 doCall true #txt
Ps0 f14 requestActionDecl '<ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData portalCaseListData> param;
' #txt
Ps0 f14 requestMappingAction 'param.portalCaseListData=in;
' #txt
Ps0 f14 responseActionDecl 'ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData out;
' #txt
Ps0 f14 responseMappingAction 'out=in;
' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomCaseDetailsConfig</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f14 110 308 36 24 20 -2 #rect
Ps0 f14 @|CallSubIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 128 268 128 308 #arcP
Ps0 f5 expr out #txt
Ps0 f5 128 332 128 372 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalCaseList.PortalCaseListData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
        <swimlaneLabel>Start Methods</swimlaneLabel>
        <swimlaneLabel>Events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>448</swimlaneSize>
    <swimlaneSize>336</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f6 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
Ps0 f33 mainOut f40 tail #connect
Ps0 f40 head f39 mainIn #connect
Ps0 f39 mainOut f46 tail #connect
Ps0 f46 head f43 mainIn #connect
Ps0 f45 mainOut f49 tail #connect
Ps0 f49 head f48 mainIn #connect
Ps0 f48 mainOut f52 tail #connect
Ps0 f52 head f51 mainIn #connect
Ps0 f0 mainOut f53 tail #connect
Ps0 f53 head f33 mainIn #connect
Ps0 f9 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f1 mainOut f3 tail #connect
Ps0 f3 head f10 mainIn #connect
Ps0 f12 mainOut f4 tail #connect
Ps0 f4 head f45 mainIn #connect
Ps0 f43 mainOut f15 tail #connect
Ps0 f15 head f14 mainIn #connect
Ps0 f14 mainOut f5 tail #connect
Ps0 f5 head f12 mainIn #connect
