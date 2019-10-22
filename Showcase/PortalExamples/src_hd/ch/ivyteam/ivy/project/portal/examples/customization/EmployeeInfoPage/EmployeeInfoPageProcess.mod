[Ivy]
16285274E701918B 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 EmployeeInfoPageProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @MessageFlowInP-0n messageIn messageIn #zField
Es0 @MessageFlowOutP-0n messageOut messageOut #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @UdEvent f3 '' #zField
Es0 @UdExitEnd f4 '' #zField
Es0 @UdEvent f6 '' #zField
Es0 @UdInit f9 '' #zField
Es0 @UdProcessEnd f10 '' #zField
Es0 @PushWFArc f11 '' #zField
Es0 @UdEvent f7 '' #zField
Es0 @UdProcessEnd f13 '' #zField
Es0 @GridStep f5 '' #zField
Es0 @PushWFArc f15 '' #zField
Es0 @Alternative f14 '' #zField
Es0 @GridStep f19 '' #zField
Es0 @PushWFArc f20 '' #zField
Es0 @UdExitEnd f21 '' #zField
Es0 @PushWFArc f22 '' #zField
Es0 @GridStep f8 '' #zField
Es0 @PushWFArc f23 '' #zField
Es0 @PushWFArc f17 '' #zField
Es0 @GridStep f24 '' #zField
Es0 @PushWFArc f26 '' #zField
Es0 @PushWFArc f25 '' #zField
Es0 @PushWFArc f16 '' #zField
Es0 @PushWFArc f12 '' #zField
>Proto Es0 Es0 EmployeeInfoPageProcess #zField
Es0 f0 guid 16285274E802BB78 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 inActionCode 'import ch.ivyteam.ivy.project.portal.examples.Employee;

out.employeeInfo = new Employee();' #txt
Es0 f0 outParameterDecl '<ch.ivyteam.ivy.project.portal.examples.enums.UserAction userAction,ch.ivyteam.ivy.project.portal.examples.Employee employeeInfo> result;' #txt
Es0 f0 outParameterMapAction 'result.userAction=in.userAction;
result.employeeInfo=in.employeeInfo;
' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f0 83 51 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 211 51 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f2 expr out #txt
Es0 f2 109 64 211 64 #arcP
Es0 f3 guid 16285274E9ED9987 #txt
Es0 f3 actionTable 'out=in;
out.userAction=ch.ivyteam.ivy.project.portal.examples.enums.UserAction.CANCEL;
' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f3 83 147 26 26 -18 15 #rect
Es0 f3 @|UdEventIcon #fIcon
Es0 f4 779 291 26 26 0 12 #rect
Es0 f4 @|UdExitEndIcon #fIcon
Es0 f6 guid 16285AB3E76C08B3 #txt
Es0 f6 actionTable 'out=in;
out.userAction=ch.ivyteam.ivy.project.portal.examples.enums.UserAction.SAVE;
' #txt
Es0 f6 actionCode 'import ch.ivyteam.ivy.project.portal.examples.Employee;
out.employeeInfo = ivy.persistence.PersistenceSample.merge(out.employeeInfo) as Employee; 

' #txt
Es0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f6 83 291 26 26 -13 15 #rect
Es0 f6 @|UdEventIcon #fIcon
Es0 f9 guid 16285AC67CD29971 #txt
Es0 f9 method start(ch.ivyteam.ivy.project.portal.examples.Employee) #txt
Es0 f9 inParameterDecl '<ch.ivyteam.ivy.project.portal.examples.Employee employeeInfo> param;' #txt
Es0 f9 inParameterMapAction 'out.employeeInfo=param.employeeInfo;
out.userAction=ch.ivyteam.ivy.project.portal.examples.enums.UserAction.CANCEL;
' #txt
Es0 f9 outParameterDecl '<ch.ivyteam.ivy.project.portal.examples.Employee employeeInfo,ch.ivyteam.ivy.project.portal.examples.enums.UserAction userAction> result;' #txt
Es0 f9 outParameterMapAction 'result.employeeInfo=in.employeeInfo;
result.userAction=in.userAction;
' #txt
Es0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(EmployeeInfo)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f9 371 51 26 26 -53 15 #rect
Es0 f9 @|UdInitIcon #fIcon
Es0 f10 499 51 26 26 0 12 #rect
Es0 f10 @|UdProcessEndIcon #fIcon
Es0 f11 expr out #txt
Es0 f11 397 64 499 64 #arcP
Es0 f7 guid 1628A65F3507A117 #txt
Es0 f7 actionTable 'out=in;
out.userAction=ch.ivyteam.ivy.project.portal.examples.enums.UserAction.OK;
' #txt
Es0 f7 actionCode '

' #txt
Es0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ok</name>
        <nameStyle>2,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f7 83 387 26 26 -6 15 #rect
Es0 f7 @|UdEventIcon #fIcon
Es0 f13 899 147 26 26 0 12 #rect
Es0 f13 @|UdProcessEndIcon #fIcon
Es0 f5 actionTable 'out=in;
' #txt
Es0 f5 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portal.generic.bean.UserMenuBean;
String homePageUrl = new UserMenuBean().getHomePageURL();

FacesContext.getCurrentInstance().getExternalContext().redirect(homePageUrl);
' #txt
Es0 f5 security system #txt
Es0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>go to homepage</name>
    </language>
</elementInfo>
' #txt
Es0 f5 456 138 112 44 -45 -8 #rect
Es0 f5 @|StepIcon #fIcon
Es0 f15 expr out #txt
Es0 f15 109 160 456 160 #arcP
Es0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>back to home  page?</name>
        <nameStyle>19
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f14 368 288 32 32 -57 18 #rect
Es0 f14 @|AlternativeIcon #fIcon
Es0 f19 actionTable 'out=in;
' #txt
Es0 f19 actionCode 'import ch.ivyteam.ivy.project.portal.examples.Employee;

out.employeeInfo = ivy.persistence.PersistenceSample.merge(out.employeeInfo) as Employee; 
' #txt
Es0 f19 security system #txt
Es0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save employee</name>
    </language>
</elementInfo>
' #txt
Es0 f19 177 378 112 44 -41 -8 #rect
Es0 f19 @|StepIcon #fIcon
Es0 f20 expr out #txt
Es0 f20 109 400 177 400 #arcP
Es0 f20 0 0.5046816618705158 0 0 #arcLabel
Es0 f21 499 387 26 26 0 12 #rect
Es0 f21 @|UdExitEndIcon #fIcon
Es0 f22 expr out #txt
Es0 f22 289 400 499 400 #arcP
Es0 f22 0 0.5046816618705158 0 0 #arcLabel
Es0 f8 actionTable 'out=in;
' #txt
Es0 f8 actionCode 'import ch.ivyteam.ivy.project.portal.examples.Employee;

out.employeeInfo = ivy.persistence.PersistenceSample.merge(out.employeeInfo) as Employee; 
' #txt
Es0 f8 security system #txt
Es0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save employee</name>
    </language>
</elementInfo>
' #txt
Es0 f8 177 282 112 44 -41 -8 #rect
Es0 f8 @|StepIcon #fIcon
Es0 f23 expr out #txt
Es0 f23 109 304 177 304 #arcP
Es0 f23 0 0.69246261000053 0 0 #arcLabel
Es0 f17 expr out #txt
Es0 f17 289 304 368 304 #arcP
Es0 f24 actionTable 'out=in;
' #txt
Es0 f24 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
TaskUtils.resetTask(ivy.task);' #txt
Es0 f24 security system #txt
Es0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
    </language>
</elementInfo>
' #txt
Es0 f24 672 138 112 44 -27 -8 #rect
Es0 f24 @|StepIcon #fIcon
Es0 f26 expr in #txt
Es0 f26 outCond 'ivy.task.getState() == ch.ivyteam.ivy.workflow.TaskState.CREATED' #txt
Es0 f26 400 304 779 304 #arcP
Es0 f26 0 0.31901784977223685 0 0 #arcLabel
Es0 f25 expr in #txt
Es0 f25 396 300 512 182 #arcP
Es0 f25 1 512 264 #addKink
Es0 f25 1 0.36603669130543987 0 0 #arcLabel
Es0 f16 expr out #txt
Es0 f16 568 160 672 160 #arcP
Es0 f12 expr out #txt
Es0 f12 784 160 899 160 #arcP
>Proto Es0 .type ch.ivyteam.ivy.project.portal.examples.customization.EmployeeInfoPage.EmployeeInfoPageData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f0 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f9 mainOut f11 tail #connect
Es0 f11 head f10 mainIn #connect
Es0 f3 mainOut f15 tail #connect
Es0 f15 head f5 mainIn #connect
Es0 f7 mainOut f20 tail #connect
Es0 f20 head f19 mainIn #connect
Es0 f19 mainOut f22 tail #connect
Es0 f22 head f21 mainIn #connect
Es0 f6 mainOut f23 tail #connect
Es0 f23 head f8 mainIn #connect
Es0 f8 mainOut f17 tail #connect
Es0 f17 head f14 in #connect
Es0 f14 out f26 tail #connect
Es0 f26 head f4 mainIn #connect
Es0 f14 out f25 tail #connect
Es0 f25 head f5 mainIn #connect
Es0 f5 mainOut f16 tail #connect
Es0 f16 head f24 mainIn #connect
Es0 f24 mainOut f12 tail #connect
Es0 f12 head f13 mainIn #connect
