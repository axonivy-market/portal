[Ivy]
169BDE2F368D6EC4 7.5.0 #module
>Proto >Proto Collection #zClass
Ss0 Showcases Big #zClass
Ss0 B #cInfo
Ss0 #process
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @MessageFlowInP-0n messageIn messageIn #zField
Ss0 @MessageFlowOutP-0n messageOut messageOut #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @StartRequest f0 '' #zField
Ss0 @EndTask f1 '' #zField
Ss0 @UserDialog f3 '' #zField
Ss0 @PushWFArc f4 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @EndTask f5 '' #zField
Ss0 @StartRequest f6 '' #zField
Ss0 @UserDialog f7 '' #zField
Ss0 @PushWFArc f8 '' #zField
Ss0 @PushWFArc f9 '' #zField
Ss0 @UserDialog f10 '' #zField
Ss0 @StartRequest f11 '' #zField
Ss0 @EndTask f12 '' #zField
Ss0 @PushWFArc f13 '' #zField
Ss0 @PushWFArc f14 '' #zField
Ss0 @StartRequest f15 '' #zField
Ss0 @EndTask f16 '' #zField
Ss0 @UserDialog f17 '' #zField
Ss0 @PushWFArc f18 '' #zField
Ss0 @PushWFArc f19 '' #zField
Ss0 @StartRequest f20 '' #zField
Ss0 @EndTask f21 '' #zField
Ss0 @GridStep f25 '' #zField
Ss0 @PushWFArc f26 '' #zField
Ss0 @UserDialog f23 '' #zField
Ss0 @PushWFArc f24 '' #zField
Ss0 @PushWFArc f22 '' #zField
>Proto Ss0 Ss0 Showcases #zField
Ss0 f0 outLink ApplicationShowcase.ivp #txt
Ss0 f0 inParamDecl '<> param;' #txt
Ss0 f0 inParamTable 'out.gdprRequest.caseId=ivy.case.getId();
out.gdprRequest.processState=ch.ivyteam.ivy.project.portal.examples.showcase.enums.ProcessState.APPLICATION;
' #txt
Ss0 f0 requestEnabled true #txt
Ss0 f0 triggerEnabled false #txt
Ss0 f0 callSignature ApplicationShowcase() #txt
Ss0 f0 persist false #txt
Ss0 f0 startName 'Showcase Application' #txt
Ss0 f0 startDescription 'Showcase Application' #txt
Ss0 f0 taskData 'TaskTriggered.NAM=Start Application Showcase' #txt
Ss0 f0 caseData 'businessCase.attach=true
case.name=Application Showcase' #txt
Ss0 f0 showInStartList 1 #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApplicationShowcase.ivp</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 @C|.responsibility Everybody #txt
Ss0 f0 81 49 30 30 -69 17 #rect
Ss0 f0 @|StartRequestIcon #fIcon
Ss0 f1 337 49 30 30 0 15 #rect
Ss0 f1 @|EndIcon #fIcon
Ss0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.showcase.ApplicationDialog #txt
Ss0 f3 startMethod start(ch.ivyteam.ivy.project.portal.examples.showcase.GdprRequest) #txt
Ss0 f3 requestActionDecl '<ch.ivyteam.ivy.project.portal.examples.showcase.GdprRequest gdprRequest> param;' #txt
Ss0 f3 requestMappingAction 'param.gdprRequest=in.gdprRequest;
' #txt
Ss0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.showcase.Data out;
' #txt
Ss0 f3 responseMappingAction 'out=in;
' #txt
Ss0 f3 168 42 112 44 0 -8 #rect
Ss0 f3 @|UserDialogIcon #fIcon
Ss0 f4 expr out #txt
Ss0 f4 111 64 168 64 #arcP
Ss0 f2 expr out #txt
Ss0 f2 280 64 337 64 #arcP
Ss0 f5 337 145 30 30 0 15 #rect
Ss0 f5 @|EndIcon #fIcon
Ss0 f6 outLink DataTableShowcase.ivp #txt
Ss0 f6 inParamDecl '<> param;' #txt
Ss0 f6 requestEnabled true #txt
Ss0 f6 triggerEnabled false #txt
Ss0 f6 callSignature DataTableShowcase() #txt
Ss0 f6 persist false #txt
Ss0 f6 startName 'Showcase Data Table' #txt
Ss0 f6 startDescription 'Showcase Data Table' #txt
Ss0 f6 taskData 'TaskTriggered.NAM=Start DataTable Showcase' #txt
Ss0 f6 caseData 'businessCase.attach=true
case.name=DataTable Showcase' #txt
Ss0 f6 showInStartList 1 #txt
Ss0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DataTableShowcase.ivp</name>
    </language>
</elementInfo>
' #txt
Ss0 f6 @C|.responsibility Everybody #txt
Ss0 f6 81 145 30 30 -64 19 #rect
Ss0 f6 @|StartRequestIcon #fIcon
Ss0 f7 dialogId ch.ivyteam.ivy.project.portal.examples.showcase.PrimefacesDataTable #txt
Ss0 f7 startMethod start() #txt
Ss0 f7 requestActionDecl '<> param;' #txt
Ss0 f7 responseActionDecl 'portal.show.Data out;
' #txt
Ss0 f7 responseMappingAction 'out=in;
' #txt
Ss0 f7 168 138 112 44 0 -8 #rect
Ss0 f7 @|UserDialogIcon #fIcon
Ss0 f8 expr out #txt
Ss0 f8 280 160 337 160 #arcP
Ss0 f9 expr out #txt
Ss0 f9 111 160 168 160 #arcP
Ss0 f10 dialogId ch.ivyteam.ivy.project.portal.examples.showcase.CustomizedUI #txt
Ss0 f10 startMethod start() #txt
Ss0 f10 requestActionDecl '<> param;' #txt
Ss0 f10 responseActionDecl 'portal.show.Data out;
' #txt
Ss0 f10 responseMappingAction 'out=in;
' #txt
Ss0 f10 168 234 112 44 0 -8 #rect
Ss0 f10 @|UserDialogIcon #fIcon
Ss0 f11 outLink CustomizedUIShowcase.ivp #txt
Ss0 f11 inParamDecl '<> param;' #txt
Ss0 f11 requestEnabled true #txt
Ss0 f11 triggerEnabled false #txt
Ss0 f11 callSignature CustomizedUIShowcase() #txt
Ss0 f11 persist false #txt
Ss0 f11 startName 'Showcase Customized UI' #txt
Ss0 f11 startDescription 'Showcase Customized UI' #txt
Ss0 f11 taskData 'TaskTriggered.NAM=Start Customized UI' #txt
Ss0 f11 caseData 'businessCase.attach=true
case.name=Start Customized UI' #txt
Ss0 f11 showInStartList 1 #txt
Ss0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomizedUIShowcase.ivp</name>
    </language>
</elementInfo>
' #txt
Ss0 f11 @C|.responsibility Everybody #txt
Ss0 f11 81 241 30 30 -77 19 #rect
Ss0 f11 @|StartRequestIcon #fIcon
Ss0 f12 337 241 30 30 0 15 #rect
Ss0 f12 @|EndIcon #fIcon
Ss0 f13 expr out #txt
Ss0 f13 111 256 168 256 #arcP
Ss0 f14 expr out #txt
Ss0 f14 280 256 337 256 #arcP
Ss0 f15 outLink PrimefacesElements.ivp #txt
Ss0 f15 inParamDecl '<> param;' #txt
Ss0 f15 requestEnabled true #txt
Ss0 f15 triggerEnabled false #txt
Ss0 f15 callSignature PrimefacesElements() #txt
Ss0 f15 persist false #txt
Ss0 f15 startName 'Showcase Primefaces Elements' #txt
Ss0 f15 startDescription 'Showcase Primefaces Elements' #txt
Ss0 f15 taskData 'TaskTriggered.NAM=Start Primefaces Elements' #txt
Ss0 f15 caseData 'businessCase.attach=true
case.name=Primefaces Elements Showcase' #txt
Ss0 f15 showInStartList 1 #txt
Ss0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PrimefacesElements.ivp</name>
    </language>
</elementInfo>
' #txt
Ss0 f15 @C|.responsibility Everybody #txt
Ss0 f15 81 337 30 30 -59 30 #rect
Ss0 f15 @|StartRequestIcon #fIcon
Ss0 f16 337 337 30 30 0 15 #rect
Ss0 f16 @|EndIcon #fIcon
Ss0 f17 dialogId ch.ivyteam.ivy.project.portal.examples.showcase.PrimefacesElements #txt
Ss0 f17 startMethod start() #txt
Ss0 f17 requestActionDecl '<> param;' #txt
Ss0 f17 responseActionDecl 'portal.show.Data out;
' #txt
Ss0 f17 responseMappingAction 'out=in;
' #txt
Ss0 f17 168 330 112 44 0 -8 #rect
Ss0 f17 @|UserDialogIcon #fIcon
Ss0 f18 expr out #txt
Ss0 f18 111 352 168 352 #arcP
Ss0 f19 expr out #txt
Ss0 f19 280 352 337 352 #arcP
Ss0 f20 outLink CustomizedTaskTemplate.ivp #txt
Ss0 f20 inParamDecl '<> param;' #txt
Ss0 f20 requestEnabled true #txt
Ss0 f20 triggerEnabled false #txt
Ss0 f20 callSignature CustomizedTaskTemplate() #txt
Ss0 f20 persist false #txt
Ss0 f20 startName 'Customization for TaskTemplate' #txt
Ss0 f20 startDescription 'Show example for how to use TaskTemplate' #txt
Ss0 f20 taskData 'TaskTriggered.CATEGORY=Tasktemplate
TaskTriggered.DESC=Test Tasktemplate task
TaskTriggered.NAM=Test Tasktemplate task' #txt
Ss0 f20 caseData 'businessCase.attach=true
case.category=Test
case.description=Testing
case.name=Case testing' #txt
Ss0 f20 showInStartList 1 #txt
Ss0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomizedTaskTemplate.ivp</name>
    </language>
</elementInfo>
' #txt
Ss0 f20 @C|.responsibility Everybody #txt
Ss0 f20 65 465 30 30 -70 29 #rect
Ss0 f20 @|StartRequestIcon #fIcon
Ss0 f21 561 465 30 30 0 15 #rect
Ss0 f21 @|EndIcon #fIcon
Ss0 f25 actionTable 'out=in;
' #txt
Ss0 f25 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate.TestTaskTemplateData;

in.actuaStepIndex = ivy.session.getAttribute("taskConfigExampleActualStepIndex") as Integer;
in.processChainDirection = ivy.session.getAttribute("taskConfigExampleProcessChainDirection") as String;
in.processChainShape = ivy.session.getAttribute("taskConfigExampleProcessChainShape") as String;
if (StringUtils.isEmpty(in.processChainDirection)) {
	in.processChainDirection = "HORIZONTAL";
}
if (StringUtils.isEmpty(in.processChainShape)) {
	in.processChainShape = "LINE";
}' #txt
Ss0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get param from session</name>
    </language>
</elementInfo>
' #txt
Ss0 f25 136 458 144 44 -67 -8 #rect
Ss0 f25 @|StepIcon #fIcon
Ss0 f26 expr out #txt
Ss0 f26 95 480 136 480 #arcP
Ss0 f23 dialogId ch.ivyteam.ivy.project.portal.examples.TestTaskTemplate #txt
Ss0 f23 startMethod start(ch.ivyteam.ivy.project.portal.examples.showcase.Data) #txt
Ss0 f23 requestActionDecl '<ch.ivyteam.ivy.project.portal.examples.showcase.Data data> param;' #txt
Ss0 f23 requestMappingAction 'param.data=in;
' #txt
Ss0 f23 responseMappingAction 'out=in;
' #txt
Ss0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>open task template</name>
    </language>
</elementInfo>
' #txt
Ss0 f23 344 458 112 44 -52 -8 #rect
Ss0 f23 @|UserDialogIcon #fIcon
Ss0 f24 280 480 344 480 #arcP
Ss0 f22 456 480 561 480 #arcP
>Proto Ss0 .type ch.ivyteam.ivy.project.portal.examples.showcase.Data #txt
>Proto Ss0 .processKind NORMAL #txt
>Proto Ss0 0 0 32 24 18 0 #rect
>Proto Ss0 @|BIcon #fIcon
Ss0 f0 mainOut f4 tail #connect
Ss0 f4 head f3 mainIn #connect
Ss0 f3 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f6 mainOut f9 tail #connect
Ss0 f9 head f7 mainIn #connect
Ss0 f7 mainOut f8 tail #connect
Ss0 f8 head f5 mainIn #connect
Ss0 f11 mainOut f13 tail #connect
Ss0 f13 head f10 mainIn #connect
Ss0 f10 mainOut f14 tail #connect
Ss0 f14 head f12 mainIn #connect
Ss0 f15 mainOut f18 tail #connect
Ss0 f18 head f17 mainIn #connect
Ss0 f17 mainOut f19 tail #connect
Ss0 f19 head f16 mainIn #connect
Ss0 f20 mainOut f26 tail #connect
Ss0 f26 head f25 mainIn #connect
Ss0 f25 mainOut f24 tail #connect
Ss0 f24 head f23 mainIn #connect
Ss0 f23 mainOut f22 tail #connect
Ss0 f22 head f21 mainIn #connect
