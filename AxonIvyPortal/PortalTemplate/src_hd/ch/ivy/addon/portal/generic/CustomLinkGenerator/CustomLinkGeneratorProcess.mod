[Ivy]
[>Created: Wed Apr 19 16:56:40 ICT 2017]
14BEEC80DFF38FB5 3.20 #module
>Proto >Proto Collection #zClass
Cs0 CustomLinkGeneratorProcess Big #zClass
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
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @CallSub f2 '' #zField
Cs0 @RichDialog f9 '' #zField
Cs0 @GridStep f17 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @RichDialog f23 '' #zField
Cs0 @Alternative f28 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f33 '' #zField
Cs0 @GridStep f5 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @RichDialogProcessStart f8 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @CallSub f15 '' #zField
Cs0 @Alternative f14 '' #zField
Cs0 @RichDialogInitStart f18 '' #zField
Cs0 @RichDialogMethodStart f0 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @Split f26 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @Join f32 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @CallSub f56 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f31 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @SJArc f34 '' #zField
Cs0 @CallSub f21 '' #zField
Cs0 @CallSub f16 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @SJArc f35 '' #zField
Cs0 @GridStep f36 '' #zField
Cs0 @PushWFArc f37 '' #zField
Cs0 @PushWFArc f41 '' #zField
Cs0 @CallSub f38 '' #zField
Cs0 @GridStep f13 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @GridStep f40 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @PushWFArc f39 '' #zField
>Proto Cs0 Cs0 CustomLinkGeneratorProcess #zField
Cs0 f1 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f1 819 275 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f2 processCall 'Functional Processes/GetPortalConfig:call()' #txt
Cs0 f2 doCall true #txt
Cs0 f2 requestActionDecl '<> param;
' #txt
Cs0 f2 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f2 responseMappingAction 'out=in;
out.linkList=result.portalConfig.customLinkList;
out.portalConfig=result.portalConfig;
' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GetPortalConfig</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f2 288 266 112 44 -43 -8 #rect
Cs0 f2 @|CallSubIcon #fIcon
Cs0 f9 targetWindow NEW:card: #txt
Cs0 f9 targetDisplay TOP #txt
Cs0 f9 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Cs0 f9 startMethod start() #txt
Cs0 f9 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f9 requestActionDecl '<> param;' #txt
Cs0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f9 responseMappingAction 'out=in;
' #txt
Cs0 f9 windowConfiguration '* ' #txt
Cs0 f9 isAsynch false #txt
Cs0 f9 isInnerRd true #txt
Cs0 f9 userContext '* ' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>dash board</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 420 440 120 48 -27 -8 #rect
Cs0 f9 @|RichDialogIcon #fIcon
Cs0 f17 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f17 actionTable 'out=in;
' #txt
Cs0 f17 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

in.currentUser = ivy.session.getSessionUserName();
in.hasReadAllTasksPermisson = TaskUtils.checkReadAllTasksPermission();
in.hasReadAllCasesPermission = TaskUtils.checkReadAllCasesPermission();' #txt
Cs0 f17 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check permission</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f17 176 272 80 32 -41 18 #rect
Cs0 f17 @|StepIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 256 288 288 288 #arcP
Cs0 f23 targetWindow NEW:card: #txt
Cs0 f23 targetDisplay TOP #txt
Cs0 f23 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Cs0 f23 startMethod start() #txt
Cs0 f23 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f23 requestActionDecl '<> param;' #txt
Cs0 f23 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f23 responseMappingAction 'out=in;
' #txt
Cs0 f23 windowConfiguration '* ' #txt
Cs0 f23 isAsynch false #txt
Cs0 f23 isInnerRd true #txt
Cs0 f23 userContext '* ' #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f23 420 728 120 48 -22 -8 #rect
Cs0 f23 @|RichDialogIcon #fIcon
Cs0 f28 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f28 274 602 28 28 14 0 #rect
Cs0 f28 @|AlternativeIcon #fIcon
Cs0 f27 expr in #txt
Cs0 f27 outCond ch.ivy.addon.portalkit.enums.MenuKind.DASHBOARD.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f27 302 616 420 464 #arcP
Cs0 f27 1 352 616 #addKink
Cs0 f27 2 352 464 #addKink
Cs0 f27 1 0.3793103448275862 0 0 #arcLabel
Cs0 f33 expr in #txt
Cs0 f33 outCond ch.ivy.addon.portalkit.enums.MenuKind.PROCESS.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f33 302 616 420 752 #arcP
Cs0 f33 1 352 616 #addKink
Cs0 f33 2 352 752 #addKink
Cs0 f33 2 0.22791790517406302 0 0 #arcLabel
Cs0 f5 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import javax.faces.context.FacesContext;

PortalNavigator portalNavigator = new PortalNavigator();
portalNavigator.redirect(in.selectedMenuItem.url);' #txt
Cs0 f5 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to custom link</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 408 820 144 56 -59 -8 #rect
Cs0 f5 @|StepIcon #fIcon
Cs0 f7 expr in #txt
Cs0 f7 302 616 408 848 #arcP
Cs0 f7 1 352 616 #addKink
Cs0 f7 2 352 848 #addKink
Cs0 f7 1 0.5883396993301201 0 0 #arcLabel
Cs0 f8 guid 152115972A34713D #txt
Cs0 f8 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f8 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>selectMenu</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 85 605 22 22 -28 18 #rect
Cs0 f8 @|RichDialogProcessStartIcon #fIcon
Cs0 f11 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f11 actionTable 'out=in;
out.selectedMenuItem=in.selectedNode.getData() as ch.ivy.addon.portalkit.bo.MainMenuNode;
' #txt
Cs0 f11 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get data of node</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 190 604 36 24 -36 16 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 107 616 190 616 #arcP
Cs0 f10 expr out #txt
Cs0 f10 226 616 274 616 #arcP
Cs0 f15 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f15 processCall MultiPortal/TaskService:findCategories(String,String,List<String>,Long) #txt
Cs0 f15 doCall true #txt
Cs0 f15 requestActionDecl '<java.lang.String jsonQuery,java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Cs0 f15 requestMappingAction 'param.userName=in.currentUser;
param.apps=[ch.ivy.addon.portalkit.util.SecurityServiceUtils.getApplicationNameFromSession()];
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Cs0 f15 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f15 responseMappingAction 'out=in;
out.errors=result.errors;
out.myTaskCategories=result.categories;
' #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
my tasks</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 606 340 36 24 -49 14 #rect
Cs0 f15 @|CallSubIcon #fIcon
Cs0 f14 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>isAdmin?</name>
        <nameStyle>8
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f14 434 274 28 28 14 0 #rect
Cs0 f14 @|AlternativeIcon #fIcon
Cs0 f18 guid 1530867F9FF75447 #txt
Cs0 f18 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f18 method start() #txt
Cs0 f18 disableUIEvents true #txt
Cs0 f18 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f18 outParameterDecl '<> result;
' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f18 85 45 22 22 14 0 #rect
Cs0 f18 @|RichDialogInitStartIcon #fIcon
Cs0 f0 guid 1530879D1D06FD93 #txt
Cs0 f0 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f0 method findTasksAndCases() #txt
Cs0 f0 disableUIEvents false #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksAndCases()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 85 277 22 22 -60 18 #rect
Cs0 f0 @|RichDialogMethodStartIcon #fIcon
Cs0 f24 expr out #txt
Cs0 f24 107 288 176 288 #arcP
Cs0 f25 expr out #txt
Cs0 f25 107 56 832 275 #arcP
Cs0 f25 1 832 56 #addKink
Cs0 f25 0 0.5351330989632178 0 0 #arcLabel
Cs0 f26 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out1;
ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out2;
' #txt
Cs0 f26 actionTable 'out1=in;
out2=in;
' #txt
Cs0 f26 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f26 466 146 28 28 14 0 #rect
Cs0 f26 @|ThreadIcon #fIcon
Cs0 f30 expr in #txt
Cs0 f30 outCond in.hasReadAllTasksPermisson #txt
Cs0 f30 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f30 448 274 466 160 #arcP
Cs0 f30 1 448 160 #addKink
Cs0 f30 0 0.5844880682361757 -9 0 #arcLabel
Cs0 f32 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f32 actionTable 'out=in1;
out.allTaskCategories=in1.allTaskCategories;
out.errors=in1.errors.addAll(in2.errors);
out.myTaskCategories=in2.myTaskCategories;
' #txt
Cs0 f32 754 146 28 28 14 0 #rect
Cs0 f32 @|JoinIcon #fIcon
Cs0 f6 expr out #txt
Cs0 f6 782 160 832 275 #arcP
Cs0 f6 1 832 160 #addKink
Cs0 f6 0 0.9849038503045222 0 0 #arcLabel
Cs0 f56 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f56 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Cs0 f56 doCall true #txt
Cs0 f56 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Cs0 f56 requestMappingAction 'param.view=in.caseView;
' #txt
Cs0 f56 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f56 responseMappingAction 'out=in;
' #txt
Cs0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f56 472 616 112 48 -49 -12 #rect
Cs0 f56 @|CallSubIcon #fIcon
Cs0 f20 expr in #txt
Cs0 f20 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f20 448 302 606 352 #arcP
Cs0 f20 1 448 352 #addKink
Cs0 f20 1 0.48663816975842455 0 -12 #arcLabel
Cs0 f31 expr out #txt
Cs0 f31 642 352 832 301 #arcP
Cs0 f31 1 832 352 #addKink
Cs0 f31 1 0.7932554983320027 0 0 #arcLabel
Cs0 f22 expr out1 #txt
Cs0 f22 480 146 606 96 #arcP
Cs0 f22 1 480 96 #addKink
Cs0 f22 1 0.20290693978229762 0 -10 #arcLabel
Cs0 f34 expr out #txt
Cs0 f34 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f34 var in1 #txt
Cs0 f34 642 96 768 146 #arcP
Cs0 f34 1 768 96 #addKink
Cs0 f34 0 0.6966392519253586 0 0 #arcLabel
Cs0 f21 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f21 processCall MultiPortal/TaskService:findCategories(String,String,List<String>,Long) #txt
Cs0 f21 doCall true #txt
Cs0 f21 requestActionDecl '<java.lang.String jsonQuery,java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Cs0 f21 requestMappingAction 'param.apps=[ch.ivy.addon.portalkit.util.SecurityServiceUtils.getApplicationNameFromSession()];
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Cs0 f21 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f21 responseMappingAction 'out=in;
out.allTaskCategories=result.categories;
out.errors=result.errors;
' #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
all tasks</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 606 84 36 24 -49 14 #rect
Cs0 f21 @|CallSubIcon #fIcon
Cs0 f16 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f16 processCall MultiPortal/TaskService:findCategories(String,String,List<String>,Long) #txt
Cs0 f16 doCall true #txt
Cs0 f16 requestActionDecl '<java.lang.String jsonQuery,java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Cs0 f16 requestMappingAction 'param.userName=in.currentUser;
param.apps=[ch.ivy.addon.portalkit.util.SecurityServiceUtils.getApplicationNameFromSession()];
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Cs0 f16 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f16 responseMappingAction 'out=in;
out.errors=result.errors;
out.myTaskCategories=result.categories;
' #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
my tasks</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 606 196 36 24 -49 14 #rect
Cs0 f16 @|CallSubIcon #fIcon
Cs0 f4 expr out2 #txt
Cs0 f4 480 174 606 208 #arcP
Cs0 f4 1 480 208 #addKink
Cs0 f4 1 0.2987128197683225 0 0 #arcLabel
Cs0 f35 expr out #txt
Cs0 f35 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f35 var in2 #txt
Cs0 f35 642 208 768 174 #arcP
Cs0 f35 1 768 208 #addKink
Cs0 f35 0 0.6790557805187061 0 0 #arcLabel
Cs0 f36 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f36 actionTable 'out=in;
' #txt
Cs0 f36 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portal.generic.common.TreeNodeType;

CaseLazyDataModel dataModel = new CaseLazyDataModel();

dataModel.setIgnoreInvolvedUser(in.hasReadAllCasesPermission);

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	dataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	dataModel.setInvolvedApplications(applicationName);
}

out.caseView = ch.ivy.addon.portal.generic.view.CaseView.create().dataModel(dataModel).buildNewView();' #txt
Cs0 f36 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
case view</name>
        <nameStyle>27
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f36 390 628 36 24 -42 12 #rect
Cs0 f36 @|StepIcon #fIcon
Cs0 f37 expr in #txt
Cs0 f37 outCond ch.ivy.addon.portalkit.enums.MenuKind.CASE.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f37 302 616 390 640 #arcP
Cs0 f37 1 352 616 #addKink
Cs0 f37 2 352 640 #addKink
Cs0 f37 2 0.41432955561407725 0 0 #arcLabel
Cs0 f41 expr out #txt
Cs0 f41 426 640 472 640 #arcP
Cs0 f41 0 0.41432955561407725 0 0 #arcLabel
Cs0 f38 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f38 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Cs0 f38 doCall true #txt
Cs0 f38 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Cs0 f38 requestMappingAction 'param.taskView=in.taskView;
' #txt
Cs0 f38 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f38 responseMappingAction 'out=in;
' #txt
Cs0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f38 664 526 128 48 -48 -12 #rect
Cs0 f38 @|CallSubIcon #fIcon
Cs0 f13 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f13 actionTable 'out=in;
' #txt
Cs0 f13 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portal.generic.common.TreeNodeType;

TaskNode categoryMenu = in.selectedMenuItem as TaskNode;
TaskLazyDataModel dataModel = new TaskLazyDataModel();

if(in.selectedNode.type.equals(TreeNodeType.TASKS)) {
	categoryMenu.value = ivy.cms.co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/tasks");
	dataModel.setIgnoreInvolvedUser(categoryMenu.rootNodeAllTask);
} else if(in.selectedNode.type.equals(TreeNodeType.TASKS_MY_TASKS)){
	categoryMenu.value = ivy.cms.co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/myTasks");
	dataModel.setIgnoreInvolvedUser(false);
} else if(in.selectedNode.type.equals(TreeNodeType.TASKS_ALL_TASKS)){
	categoryMenu.value = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/AllTasks/title");
	dataModel.setIgnoreInvolvedUser(true);
} else {
	dataModel.setCategory(categoryMenu.category);
	dataModel.setIgnoreInvolvedUser(categoryMenu.rootNodeAllTask);
}

in.taskView = TaskView.create().category(categoryMenu).dataModel(dataModel).pageTitle(categoryMenu.value).createNewTaskView();' #txt
Cs0 f13 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
 task view</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 414 538 36 24 -47 14 #rect
Cs0 f13 @|StepIcon #fIcon
Cs0 f29 expr in #txt
Cs0 f29 outCond ch.ivy.addon.portalkit.enums.MenuKind.TASK.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f29 302 616 414 550 #arcP
Cs0 f29 1 352 616 #addKink
Cs0 f29 2 352 550 #addKink
Cs0 f29 1 0.3451086956521739 0 0 #arcLabel
Cs0 f19 expr out #txt
Cs0 f19 400 288 434 288 #arcP
Cs0 f40 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f40 actionTable 'out=in;
' #txt
Cs0 f40 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), PortalPage.LINK_TO_TASK);' #txt
Cs0 f40 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store the last page
to session</name>
        <nameStyle>30
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f40 498 528 128 44 -45 -16 #rect
Cs0 f40 @|StepIcon #fIcon
Cs0 f42 expr out #txt
Cs0 f42 450 550 498 550 #arcP
Cs0 f42 0 0.3451086956521739 0 0 #arcLabel
Cs0 f39 expr out #txt
Cs0 f39 626 550 664 550 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f17 mainOut f3 tail #connect
Cs0 f3 head f2 mainIn #connect
Cs0 f28 out f27 tail #connect
Cs0 f27 head f9 mainIn #connect
Cs0 f33 head f23 mainIn #connect
Cs0 f7 head f5 mainIn #connect
Cs0 f8 mainOut f12 tail #connect
Cs0 f12 head f11 mainIn #connect
Cs0 f11 mainOut f10 tail #connect
Cs0 f10 head f28 in #connect
Cs0 f2 mainOut f19 tail #connect
Cs0 f19 head f14 in #connect
Cs0 f0 mainOut f24 tail #connect
Cs0 f24 head f17 mainIn #connect
Cs0 f18 mainOut f25 tail #connect
Cs0 f25 head f1 mainIn #connect
Cs0 f14 out f30 tail #connect
Cs0 f30 head f26 in #connect
Cs0 f21 mainOut f34 tail #connect
Cs0 f34 head f32 in #connect
Cs0 f32 mainOut f6 tail #connect
Cs0 f6 head f1 mainIn #connect
Cs0 f26 out f22 tail #connect
Cs0 f22 head f21 mainIn #connect
Cs0 f14 out f20 tail #connect
Cs0 f20 head f15 mainIn #connect
Cs0 f15 mainOut f31 tail #connect
Cs0 f31 head f1 mainIn #connect
Cs0 f26 out f4 tail #connect
Cs0 f4 head f16 mainIn #connect
Cs0 f16 mainOut f35 tail #connect
Cs0 f35 head f32 in #connect
Cs0 f37 head f36 mainIn #connect
Cs0 f36 mainOut f41 tail #connect
Cs0 f41 head f56 mainIn #connect
Cs0 f28 out f29 tail #connect
Cs0 f29 head f13 mainIn #connect
Cs0 f28 out f37 tail #connect
Cs0 f28 out f33 tail #connect
Cs0 f28 out f7 tail #connect
Cs0 f13 mainOut f42 tail #connect
Cs0 f42 head f40 mainIn #connect
Cs0 f40 mainOut f39 tail #connect
Cs0 f39 head f38 mainIn #connect
