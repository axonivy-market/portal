[Ivy]
163AF3C65466F6F9 3.23 #module
>Proto >Proto Collection #zClass
Ps0 CustomizedSearchResultsProcess Big #zClass
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
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @RichDialog f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 CustomizedSearchResultsProcess #zField
Ps0 f0 guid 150D5B63FE18100D #txt
Ps0 f0 type ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults.CustomizedSearchResultsData #txt
Ps0 f0 method start(String) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.dataModel.caseDataModel.notKeepFilter=true;
out.dataModel.caseDataModel.queryCriteria.keyword=param.keyword;
out.dataModel.keyword=param.keyword;
out.dataModel.taskDataModel.compactMode=false;
out.dataModel.taskDataModel.notKeepFilter=true;
out.dataModel.taskDataModel.queryCriteria.keyword=param.keyword;
' #txt
Ps0 f0 inActionCode out.dataModel.search(); #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 53 85 22 22 -16 13 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults.CustomizedSearchResultsData #txt
Ps0 f1 333 85 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 75 96 333 96 #arcP
Ps0 f3 guid 163B050731AD1F6C #txt
Ps0 f3 type ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults.CustomizedSearchResultsData #txt
Ps0 f3 method openDetails(ch.ivyteam.ivy.project.portal.examples.Employee) #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.project.portal.examples.Employee employee> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 inParameterMapAction 'out.emp=param.employee;
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDetails(Employee)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 51 211 26 26 -65 15 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 targetWindow NEW #txt
Ps0 f4 targetDisplay TOP #txt
Ps0 f4 richDialogId ch.ivyteam.ivy.project.portal.examples.customization.EmployeeInfoPage #txt
Ps0 f4 startMethod start(ch.ivyteam.ivy.project.portal.examples.Employee) #txt
Ps0 f4 type ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults.CustomizedSearchResultsData #txt
Ps0 f4 requestActionDecl '<ch.ivyteam.ivy.project.portal.examples.Employee employeeInfo> param;' #txt
Ps0 f4 requestMappingAction 'param.employeeInfo=in.emp;
' #txt
Ps0 f4 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults.CustomizedSearchResultsData out;
' #txt
Ps0 f4 responseMappingAction 'out=in;
' #txt
Ps0 f4 isAsynch false #txt
Ps0 f4 isInnerRd true #txt
Ps0 f4 userContext '* ' #txt
Ps0 f4 152 202 112 44 0 -8 #rect
Ps0 f4 @|RichDialogIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 77 224 152 224 #arcP
>Proto Ps0 .type ch.ivyteam.ivy.project.portal.examples.customization.CustomizedSearchResults.CustomizedSearchResultsData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
