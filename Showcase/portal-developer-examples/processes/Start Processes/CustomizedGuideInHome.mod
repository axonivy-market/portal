[Ivy]
17279884C45FC0CE 9.2.0 #module
>Proto >Proto Collection #zClass
Ce0 CustomizedGuideInHome Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartRequest f0 '' #zField
Ce0 @GridStep f22 '' #zField
Ce0 @EndTask f1 '' #zField
Ce0 @UserDialog f3 '' #zField
Ce0 @CallSub f9 '' #zField
Ce0 @PushWFArc f5 '' #zField
Ce0 @PushWFArc f4 '' #zField
Ce0 @PushWFArc f6 '' #zField
Ce0 @PushWFArc f2 '' #zField
>Proto Ce0 Ce0 CustomizedGuideInHome #zField
Ce0 f0 outLink start.ivp #txt
Ce0 f0 inParamDecl '<> param;' #txt
Ce0 f0 requestEnabled true #txt
Ce0 f0 triggerEnabled false #txt
Ce0 f0 callSignature start() #txt
Ce0 f0 startName 'Customized Guide in Home' #txt
Ce0 f0 caseData businessCase.attach=true #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 @C|.responsibility Everybody #txt
Ce0 f0 81 49 30 30 -20 17 #rect
Ce0 f0 @|StartRequestIcon #fIcon
Ce0 f22 actionTable 'out=in;
' #txt
Ce0 f22 actionCode 'import ch.ivy.addon.portal.generic.view.TaskView;

in.taskView = TaskView.create().dataModel(in.dataModel).createNewTaskView();
' #txt
Ce0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build task view</name>
    </language>
</elementInfo>
' #txt
Ce0 f22 352 42 112 44 -38 -8 #rect
Ce0 f22 @|StepIcon #fIcon
Ce0 f1 689 49 30 30 0 15 #rect
Ce0 f1 @|EndIcon #fIcon
Ce0 f3 dialogId com.axonivy.portal.developerexamples.CustomizedGuide #txt
Ce0 f3 startMethod start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Ce0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Ce0 f3 requestMappingAction 'param.taskView=in.taskView;
' #txt
Ce0 f3 responseMappingAction 'out=in;
' #txt
Ce0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomizedGuideInHome</name>
    </language>
</elementInfo>
' #txt
Ce0 f3 504 42 144 44 -67 -8 #rect
Ce0 f3 @|UserDialogIcon #fIcon
Ce0 f9 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Ce0 f9 requestActionDecl '<> param;' #txt
Ce0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Ce0 f9 responseMappingAction 'out=in;
out.dataModel=result.dataModel;
out.dataModel.compactMode=true;
' #txt
Ce0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InitializeTaskDataModel</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f9 160 42 144 44 -65 -8 #rect
Ce0 f9 @|CallSubIcon #fIcon
Ce0 f5 304 64 352 64 #arcP
Ce0 f4 464 64 504 64 #arcP
Ce0 f6 111 64 160 64 #arcP
Ce0 f2 648 64 689 64 #arcP
>Proto Ce0 .type com.axonivy.portal.developerexamples.CustomizedGuideInHome #txt
>Proto Ce0 .processKind NORMAL #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f3 mainOut f2 tail #connect
Ce0 f2 head f1 mainIn #connect
Ce0 f22 mainOut f4 tail #connect
Ce0 f4 head f3 mainIn #connect
Ce0 f0 mainOut f6 tail #connect
Ce0 f6 head f9 mainIn #connect
Ce0 f9 mainOut f5 tail #connect
Ce0 f5 head f22 mainIn #connect
