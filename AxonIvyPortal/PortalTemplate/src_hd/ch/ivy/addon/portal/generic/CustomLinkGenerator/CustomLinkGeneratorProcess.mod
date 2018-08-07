[Ivy]
[>Created: Fri Mar 04 16:59:32 ICT 2016]
14BEEC80DFF38FB5 3.18 #module
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
Cs0 @RichDialog f13 '' #zField
Cs0 @CallSub f2 '' #zField
Cs0 @RichDialog f9 '' #zField
Cs0 @GridStep f17 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @RichDialog f20 '' #zField
Cs0 @RichDialog f23 '' #zField
Cs0 @Alternative f28 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @PushWFArc f31 '' #zField
Cs0 @PushWFArc f33 '' #zField
Cs0 @GridStep f5 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @RichDialogProcessStart f8 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @CallSub f15 '' #zField
Cs0 @Alternative f14 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @CallSub f21 '' #zField
Cs0 @RichDialogInitStart f18 '' #zField
Cs0 @RichDialogMethodStart f0 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @Split f26 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @Join f32 '' #zField
Cs0 @SJArc f34 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @Split f36 '' #zField
Cs0 @PushWFArc f37 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @Join f38 '' #zField
Cs0 @SJArc f39 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @CallSub f41 '' #zField
Cs0 @GridStep f42 '' #zField
Cs0 @PushWFArc f43 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @SJArc f40 '' #zField
Cs0 @CallSub f45 '' #zField
Cs0 @GridStep f46 '' #zField
Cs0 @PushWFArc f47 '' #zField
Cs0 @PushWFArc f48 '' #zField
Cs0 @SJArc f35 '' #zField
Cs0 @GridStep f49 '' #zField
Cs0 @CallSub f50 '' #zField
Cs0 @PushWFArc f51 '' #zField
Cs0 @PushWFArc f52 '' #zField
Cs0 @SJArc f53 '' #zField
>Proto Cs0 Cs0 CustomLinkGeneratorProcess #zField
Cs0 f1 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f1 819 243 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f13 targetWindow NEW:card: #txt
Cs0 f13 targetDisplay TOP #txt
Cs0 f13 richDialogId ch.ivy.addon.portal.generic.PortalTasks #txt
Cs0 f13 startMethod start(org.primefaces.model.TreeNode) #txt
Cs0 f13 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f13 requestActionDecl '<org.primefaces.model.TreeNode category> param;' #txt
Cs0 f13 requestMappingAction 'param.category=in.selectedNode;
' #txt
Cs0 f13 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f13 responseMappingAction 'out=in;
' #txt
Cs0 f13 windowConfiguration '* ' #txt
Cs0 f13 isAsynch false #txt
Cs0 f13 isInnerRd true #txt
Cs0 f13 userContext '* ' #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>tasks</name>
        <nameStyle>5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 420 592 120 48 -11 -8 #rect
Cs0 f13 @|RichDialogIcon #fIcon
Cs0 f2 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f2 processCall 'Functional Processes/GetPortalConfig:call()' #txt
Cs0 f2 doCall true #txt
Cs0 f2 requestActionDecl '<> param;
' #txt
Cs0 f2 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f2 responseMappingAction 'out=in;
out.homeUrl=result.portalConfig.homeLink;
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
Cs0 f2 288 234 112 44 -43 -8 #rect
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
Cs0 f9 420 496 120 48 -27 -8 #rect
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
Cs0 f17 176 240 80 32 -41 18 #rect
Cs0 f17 @|StepIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 256 256 288 256 #arcP
Cs0 f20 targetWindow NEW:card: #txt
Cs0 f20 targetDisplay TOP #txt
Cs0 f20 richDialogId ch.ivy.addon.portal.generic.PortalCaseList #txt
Cs0 f20 startMethod start(org.primefaces.model.TreeNode) #txt
Cs0 f20 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f20 requestActionDecl '<org.primefaces.model.TreeNode category> param;' #txt
Cs0 f20 requestMappingAction 'param.category=in.selectedNode;
' #txt
Cs0 f20 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f20 responseMappingAction 'out=in;
' #txt
Cs0 f20 windowConfiguration '* ' #txt
Cs0 f20 isAsynch false #txt
Cs0 f20 isInnerRd true #txt
Cs0 f20 userContext '* ' #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cases</name>
        <nameStyle>5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f20 416 688 128 48 -12 -8 #rect
Cs0 f20 @|RichDialogIcon #fIcon
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
Cs0 f23 420 784 120 48 -22 -8 #rect
Cs0 f23 @|RichDialogIcon #fIcon
Cs0 f28 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f28 274 658 28 28 14 0 #rect
Cs0 f28 @|AlternativeIcon #fIcon
Cs0 f27 expr in #txt
Cs0 f27 outCond ch.ivy.addon.portalkit.enums.MenuKind.DASHBOARD.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f27 302 672 420 520 #arcP
Cs0 f27 1 352 672 #addKink
Cs0 f27 2 352 520 #addKink
Cs0 f27 1 0.3793103448275862 0 0 #arcLabel
Cs0 f29 expr in #txt
Cs0 f29 outCond ch.ivy.addon.portalkit.enums.MenuKind.TASK.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f29 302 672 420 616 #arcP
Cs0 f29 1 352 672 #addKink
Cs0 f29 2 352 616 #addKink
Cs0 f29 1 0.3451086956521739 0 0 #arcLabel
Cs0 f31 expr in #txt
Cs0 f31 outCond ch.ivy.addon.portalkit.enums.MenuKind.CASE.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f31 302 672 416 712 #arcP
Cs0 f31 1 352 672 #addKink
Cs0 f31 2 352 712 #addKink
Cs0 f31 2 0.41432955561407725 0 0 #arcLabel
Cs0 f33 expr in #txt
Cs0 f33 outCond ch.ivy.addon.portalkit.enums.MenuKind.PROCESS.equals(in.selectedMenuItem.menuKind) #txt
Cs0 f33 302 672 420 808 #arcP
Cs0 f33 1 352 672 #addKink
Cs0 f33 2 352 808 #addKink
Cs0 f33 2 0.22791790517406302 0 0 #arcLabel
Cs0 f5 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.selectedMenuItem.url);' #txt
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
Cs0 f5 408 876 144 56 -59 -8 #rect
Cs0 f5 @|StepIcon #fIcon
Cs0 f7 expr in #txt
Cs0 f7 302 672 408 904 #arcP
Cs0 f7 1 352 672 #addKink
Cs0 f7 2 352 904 #addKink
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
Cs0 f8 85 661 22 22 -28 18 #rect
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
Cs0 f11 190 660 36 24 -36 16 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 107 672 190 672 #arcP
Cs0 f10 expr out #txt
Cs0 f10 226 672 274 672 #arcP
Cs0 f15 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f15 processCall MultiPortal/TaskService:findActiveTasks(String) #txt
Cs0 f15 doCall true #txt
Cs0 f15 requestActionDecl '<java.lang.String user> param;
' #txt
Cs0 f15 requestMappingAction 'param.user=in.currentUser;
' #txt
Cs0 f15 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f15 responseMappingAction 'out=in;
out.errors=result.errors;
out.personalTasks=result.tasks;
' #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindPersonalTasks</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 590 308 36 24 -54 14 #rect
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
Cs0 f14 434 242 28 28 14 0 #rect
Cs0 f14 @|AlternativeIcon #fIcon
Cs0 f19 expr out #txt
Cs0 f19 400 256 434 256 #arcP
Cs0 f21 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f21 processCall MultiPortal/TaskService:findAllRunningTasks(String) #txt
Cs0 f21 doCall true #txt
Cs0 f21 requestActionDecl '<java.lang.String user> param;
' #txt
Cs0 f21 requestMappingAction 'param.user=in.currentUser;
' #txt
Cs0 f21 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f21 responseMappingAction 'out=in;
out.allTasks=result.allTasks;
out.errors=result.errors;
out.personalTasks=result.personalTasks;
' #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindAllTasks</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 590 84 36 24 -35 14 #rect
Cs0 f21 @|CallSubIcon #fIcon
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
Cs0 f0 85 245 22 22 -60 18 #rect
Cs0 f0 @|RichDialogMethodStartIcon #fIcon
Cs0 f24 expr out #txt
Cs0 f24 107 256 176 256 #arcP
Cs0 f25 expr out #txt
Cs0 f25 107 56 832 243 #arcP
Cs0 f25 1 832 56 #addKink
Cs0 f25 0 0.5351330989632179 0 0 #arcLabel
Cs0 f26 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out1;
ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out2;
ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out3;
' #txt
Cs0 f26 actionTable 'out1=in;
out2=in;
out3=in;
' #txt
Cs0 f26 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f26 466 114 28 28 14 0 #rect
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
Cs0 f30 448 242 466 128 #arcP
Cs0 f30 1 448 128 #addKink
Cs0 f30 0 0.5844880682361757 -9 0 #arcLabel
Cs0 f16 expr out1 #txt
Cs0 f16 480 114 590 96 #arcP
Cs0 f16 1 480 96 #addKink
Cs0 f16 1 0.20290693978229762 0 -10 #arcLabel
Cs0 f32 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f32 actionTable 'out=in1;
out.allCases=in3.allCases;
out.allTasks=in1.allTasks;
out.errors=in1.errors.addAll(in2.errors).addAll(in3.errors);
out.personalCases=in2.personalCases;
out.personalTasks=in1.personalTasks;
' #txt
Cs0 f32 754 114 28 28 14 0 #rect
Cs0 f32 @|JoinIcon #fIcon
Cs0 f34 expr out #txt
Cs0 f34 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f34 var in1 #txt
Cs0 f34 626 96 768 114 #arcP
Cs0 f34 1 768 96 #addKink
Cs0 f34 0 0.6966392519253586 0 0 #arcLabel
Cs0 f6 expr out #txt
Cs0 f6 782 128 832 243 #arcP
Cs0 f6 1 832 128 #addKink
Cs0 f6 0 0.9849038503045222 0 0 #arcLabel
Cs0 f36 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out1;
ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out2;
' #txt
Cs0 f36 actionTable 'out1=in;
out2=in;
' #txt
Cs0 f36 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f36 466 338 28 28 14 0 #rect
Cs0 f36 @|ThreadIcon #fIcon
Cs0 f37 expr in #txt
Cs0 f37 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f37 448 270 466 352 #arcP
Cs0 f37 1 448 352 #addKink
Cs0 f37 1 0.28169014084507044 0 -12 #arcLabel
Cs0 f22 expr out1 #txt
Cs0 f22 480 338 590 320 #arcP
Cs0 f22 1 480 320 #addKink
Cs0 f22 0 0.9804337750739077 -11 5 #arcLabel
Cs0 f38 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f38 actionTable 'out=in1;
out.allTasks=in1.allTasks;
out.errors=in1.errors.addAll(in2.errors);
out.personalCases=in2.personalCases;
out.personalTasks=in1.personalTasks;
' #txt
Cs0 f38 754 338 28 28 14 0 #rect
Cs0 f38 @|JoinIcon #fIcon
Cs0 f39 expr out #txt
Cs0 f39 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f39 var in1 #txt
Cs0 f39 626 320 768 338 #arcP
Cs0 f39 1 768 320 #addKink
Cs0 f39 1 0.7932554983320027 0 0 #arcLabel
Cs0 f4 expr out #txt
Cs0 f4 782 352 832 269 #arcP
Cs0 f4 1 832 352 #addKink
Cs0 f4 0 0.8415682711600175 0 0 #arcLabel
Cs0 f41 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f41 processCall MultiPortal/CaseService:findCasesWithDestroyPermissionByCriteria(ch.ivy.ws.addon.CaseSearchCriteria,String) #txt
Cs0 f41 doCall true #txt
Cs0 f41 requestActionDecl '<ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria,java.lang.String username> param;
' #txt
Cs0 f41 requestMappingAction 'param.caseSearchCriteria=in.personalCaseSearchCriteria;
param.username=in.currentUser;
' #txt
Cs0 f41 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f41 responseMappingAction 'out=in;
out.errors=result.errors;
out.personalCases=result.cases;
' #txt
Cs0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f41 686 372 36 24 20 -2 #rect
Cs0 f41 @|CallSubIcon #fIcon
Cs0 f42 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f42 actionTable 'out=in;
' #txt
Cs0 f42 actionCode 'import ch.ivy.ws.addon.CaseState;

in.personalCaseSearchCriteria.involvedUsername = ivy.session.getSessionUserName();

List<CaseState> states = new List<CaseState>();
states.add(CaseState.DESTROYED);
states.add(CaseState.ZOMBIE);

in.personalCaseSearchCriteria.excludedStates = states;
' #txt
Cs0 f42 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare personal search</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f42 526 372 36 24 -60 12 #rect
Cs0 f42 @|StepIcon #fIcon
Cs0 f43 expr out #txt
Cs0 f43 562 384 686 384 #arcP
Cs0 f44 expr out2 #txt
Cs0 f44 480 366 526 384 #arcP
Cs0 f44 1 480 384 #addKink
Cs0 f44 1 0.09755973067835934 0 0 #arcLabel
Cs0 f40 expr out #txt
Cs0 f40 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f40 var in2 #txt
Cs0 f40 722 384 768 366 #arcP
Cs0 f40 1 768 384 #addKink
Cs0 f40 1 0.04193077935016792 0 0 #arcLabel
Cs0 f45 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f45 processCall MultiPortal/CaseService:findCasesWithDestroyPermissionByCriteria(ch.ivy.ws.addon.CaseSearchCriteria,String) #txt
Cs0 f45 doCall true #txt
Cs0 f45 requestActionDecl '<ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria,java.lang.String username> param;
' #txt
Cs0 f45 requestMappingAction 'param.caseSearchCriteria=in.personalCaseSearchCriteria;
param.username=in.currentUser;
' #txt
Cs0 f45 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f45 responseMappingAction 'out=in;
out.errors=result.errors;
out.personalCases=result.cases;
' #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f45 686 148 36 24 20 -2 #rect
Cs0 f45 @|CallSubIcon #fIcon
Cs0 f46 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f46 actionTable 'out=in;
' #txt
Cs0 f46 actionCode 'import ch.ivy.ws.addon.CaseState;

in.personalCaseSearchCriteria.involvedUsername = ivy.session.getSessionUserName();
List<CaseState> states = new List<CaseState>();
states.add(CaseState.DESTROYED);
states.add(CaseState.ZOMBIE);

in.personalCaseSearchCriteria.excludedStates = states;
' #txt
Cs0 f46 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare personal
cases criteria</name>
        <nameStyle>31,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f46 526 148 36 24 -47 13 #rect
Cs0 f46 @|StepIcon #fIcon
Cs0 f47 expr out #txt
Cs0 f47 562 160 686 160 #arcP
Cs0 f48 expr out2 #txt
Cs0 f48 480 142 526 160 #arcP
Cs0 f48 1 480 160 #addKink
Cs0 f48 1 0.27532161773830544 0 0 #arcLabel
Cs0 f35 expr out #txt
Cs0 f35 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f35 var in2 #txt
Cs0 f35 722 160 768 142 #arcP
Cs0 f35 1 768 160 #addKink
Cs0 f35 1 0.050800249966379656 0 0 #arcLabel
Cs0 f49 actionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f49 actionTable 'out=in;
' #txt
Cs0 f49 actionCode 'import ch.ivy.ws.addon.CaseState;

List<CaseState> states = new List<CaseState>();
states.add(CaseState.DESTROYED);
states.add(CaseState.ZOMBIE);

in.allCaseSearchCriteria.excludedStates = states;
' #txt
Cs0 f49 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare all 
cases criteria</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f49 526 212 36 24 -32 12 #rect
Cs0 f49 @|StepIcon #fIcon
Cs0 f50 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f50 processCall MultiPortal/CaseService:findCasesWithDestroyPermissionByCriteria(ch.ivy.ws.addon.CaseSearchCriteria,String) #txt
Cs0 f50 doCall true #txt
Cs0 f50 requestActionDecl '<ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria,java.lang.String username> param;
' #txt
Cs0 f50 requestMappingAction 'param.caseSearchCriteria=in.allCaseSearchCriteria;
param.username=in.currentUser;
' #txt
Cs0 f50 responseActionDecl 'ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData out;
' #txt
Cs0 f50 responseMappingAction 'out=in;
out.allCases=result.cases;
out.errors=result.errors;
' #txt
Cs0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f50 686 212 36 24 20 -2 #rect
Cs0 f50 @|CallSubIcon #fIcon
Cs0 f51 expr out #txt
Cs0 f51 562 224 686 224 #arcP
Cs0 f52 expr out3 #txt
Cs0 f52 480 142 526 224 #arcP
Cs0 f52 1 480 224 #addKink
Cs0 f52 0 0.8691162237458423 0 0 #arcLabel
Cs0 f53 expr out #txt
Cs0 f53 type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
Cs0 f53 var in3 #txt
Cs0 f53 722 224 768 142 #arcP
Cs0 f53 1 768 224 #addKink
Cs0 f53 1 0.13088377625415756 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f17 mainOut f3 tail #connect
Cs0 f3 head f2 mainIn #connect
Cs0 f28 out f27 tail #connect
Cs0 f27 head f9 mainIn #connect
Cs0 f28 out f29 tail #connect
Cs0 f29 head f13 mainIn #connect
Cs0 f28 out f31 tail #connect
Cs0 f31 head f20 mainIn #connect
Cs0 f28 out f33 tail #connect
Cs0 f33 head f23 mainIn #connect
Cs0 f28 out f7 tail #connect
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
Cs0 f26 out f16 tail #connect
Cs0 f16 head f21 mainIn #connect
Cs0 f21 mainOut f34 tail #connect
Cs0 f34 head f32 in #connect
Cs0 f32 mainOut f6 tail #connect
Cs0 f6 head f1 mainIn #connect
Cs0 f14 out f37 tail #connect
Cs0 f37 head f36 in #connect
Cs0 f36 out f22 tail #connect
Cs0 f22 head f15 mainIn #connect
Cs0 f15 mainOut f39 tail #connect
Cs0 f39 head f38 in #connect
Cs0 f38 mainOut f4 tail #connect
Cs0 f4 head f1 mainIn #connect
Cs0 f42 mainOut f43 tail #connect
Cs0 f43 head f41 mainIn #connect
Cs0 f36 out f44 tail #connect
Cs0 f44 head f42 mainIn #connect
Cs0 f41 mainOut f40 tail #connect
Cs0 f40 head f38 in #connect
Cs0 f46 mainOut f47 tail #connect
Cs0 f47 head f45 mainIn #connect
Cs0 f26 out f48 tail #connect
Cs0 f48 head f46 mainIn #connect
Cs0 f45 mainOut f35 tail #connect
Cs0 f35 head f32 in #connect
Cs0 f49 mainOut f51 tail #connect
Cs0 f51 head f50 mainIn #connect
Cs0 f26 out f52 tail #connect
Cs0 f52 head f49 mainIn #connect
Cs0 f50 mainOut f53 tail #connect
Cs0 f53 head f32 in #connect
