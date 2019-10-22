[Ivy]
15FA421D253C6746 7.5.0 #module
>Proto >Proto Collection #zClass
Sy0 SearchByCaseJsonQuery Big #zClass
Sy0 B #cInfo
Sy0 #process
Sy0 @TextInP .type .type #zField
Sy0 @TextInP .processKind .processKind #zField
Sy0 @AnnotationInP-0n ai ai #zField
Sy0 @MessageFlowInP-0n messageIn messageIn #zField
Sy0 @MessageFlowOutP-0n messageOut messageOut #zField
Sy0 @TextInP .xml .xml #zField
Sy0 @TextInP .responsibility .responsibility #zField
Sy0 @StartRequest f0 '' #zField
Sy0 @GridStep f3 '' #zField
Sy0 @PushWFArc f4 '' #zField
Sy0 @CallSub f23 '' #zField
Sy0 @PushWFArc f1 '' #zField
>Proto Sy0 Sy0 SearchByCaseJsonQuery #zField
Sy0 f0 outLink searchByCaseJsonQuery.ivp #txt
Sy0 f0 inParamDecl '<> param;' #txt
Sy0 f0 requestEnabled true #txt
Sy0 f0 triggerEnabled false #txt
Sy0 f0 callSignature searchByCaseJsonQuery() #txt
Sy0 f0 persist false #txt
Sy0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Sy0 f0 caseData businessCase.attach=true #txt
Sy0 f0 showInStartList 1 #txt
Sy0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>searchByCaseJsonQuery.ivp</name>
    </language>
</elementInfo>
' #txt
Sy0 f0 @C|.responsibility Everybody #txt
Sy0 f0 81 49 30 30 -79 17 #rect
Sy0 f0 @|StartRequestIcon #fIcon
Sy0 f3 actionTable 'out=in;
' #txt
Sy0 f3 actionCode 'import java.util.Arrays;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portal.generic.view.CaseView;

String title ="Case list with case name  = ''Leave Request''";
CaseLazyDataModel dataModel = new CaseLazyDataModel();
out.caseView = CaseView.create().dataModel(dataModel).withTitle(title).hideCaseFilter(true).buildNewView();' #txt
Sy0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sy0 f3 168 42 112 44 -50 -8 #rect
Sy0 f3 @|StepIcon #fIcon
Sy0 f4 expr out #txt
Sy0 f4 111 64 168 64 #arcP
Sy0 f23 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Sy0 f23 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;' #txt
Sy0 f23 requestMappingAction 'param.view=in.caseView;
' #txt
Sy0 f23 responseActionDecl 'internaltest.SearchByCaseJsonQueryData out;
' #txt
Sy0 f23 responseMappingAction 'out=in;
' #txt
Sy0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sy0 f23 358 40 112 48 -49 -12 #rect
Sy0 f23 @|CallSubIcon #fIcon
Sy0 f1 expr out #txt
Sy0 f1 280 64 358 64 #arcP
>Proto Sy0 .type internaltest.SearchByCaseJsonQueryData #txt
>Proto Sy0 .processKind NORMAL #txt
>Proto Sy0 0 0 32 24 18 0 #rect
>Proto Sy0 @|BIcon #fIcon
Sy0 f0 mainOut f4 tail #connect
Sy0 f4 head f3 mainIn #connect
Sy0 f3 mainOut f1 tail #connect
Sy0 f1 head f23 mainIn #connect
