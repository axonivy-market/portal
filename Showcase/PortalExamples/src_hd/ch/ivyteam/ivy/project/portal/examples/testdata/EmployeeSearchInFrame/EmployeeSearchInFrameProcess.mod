[Ivy]
180B2268ACAB0796 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 EmployeeSearchInFrameProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @AnnotationInP-0n ai ai #zField
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @UdEvent f3 '' #zField
Es0 @UdExitEnd f4 '' #zField
Es0 @PushWFArc f5 '' #zField
Es0 @GridStep f9 '' #zField
Es0 @PushWFArc f10 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @UdMethod f11 '' #zField
Es0 @GridStep f12 '' #zField
Es0 @PushWFArc f13 '' #zField
>Proto Es0 Es0 EmployeeSearchInFrameProcess #zField
Es0 f0 guid 180B2268AD0E2F01 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 outParameterDecl '<Integer employeeId> result;' #txt
Es0 f0 outParameterMapAction 'result.employeeId=in.employeeId;
' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Es0 f0 83 51 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 339 51 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f3 guid 180B2268AD877061 #txt
Es0 f3 actionTable 'out=in;
' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Es0 f3 83 147 26 26 -15 15 #rect
Es0 f3 @|UdEventIcon #fIcon
Es0 f4 211 147 26 26 0 12 #rect
Es0 f4 @|UdExitEndIcon #fIcon
Es0 f5 109 160 211 160 #arcP
Es0 f9 actionTable 'out=in;
' #txt
Es0 f9 actionCode 'import ch.ivyteam.ivy.project.portal.examples.Employee;
Employee a = new ch.ivyteam.ivy.project.portal.examples.Employee();
a.id = 1;
a.firstName = "Peter";
a.lastName = "Parker";
a.city = "New York";

Employee b = new ch.ivyteam.ivy.project.portal.examples.Employee();
b.id = 2;
b.firstName = "Carol";
b.lastName = "Danver";
b.city = "Los Angeles";

in.employees.add(a);
in.employees.add(b);' #txt
Es0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init test data</name>
    </language>
</elementInfo>
' #txt
Es0 f9 168 42 112 44 -33 -8 #rect
Es0 f9 @|StepIcon #fIcon
Es0 f10 109 64 168 64 #arcP
Es0 f2 280 64 339 64 #arcP
Es0 f11 guid 180B71B26F8ECE1D #txt
Es0 f11 method detailsInFrame(Integer) #txt
Es0 f11 inParameterDecl '<Integer employeeId> param;' #txt
Es0 f11 inParameterMapAction 'out.employeeId=param.employeeId;
' #txt
Es0 f11 outParameterDecl '<> result;' #txt
Es0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>detailsInFrame(Integer)</name>
    </language>
</elementInfo>
' #txt
Es0 f11 83 243 26 26 -25 15 #rect
Es0 f11 @|UdMethodIcon #fIcon
Es0 f12 actionTable 'out=in;
' #txt
Es0 f12 actionCode 'import ch.ivy.addon.portalkit.util.RequestUtil;
import org.primefaces.PrimeFaces;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;

String requestPath = "Start Processes/CustomCaseInfomationForTaskTemplate/employeeDetailsInFrame.ivp";
String url = ProcessStartAPI.findLinkByFriendlyRequestPath(ivy.request.getApplication(), requestPath);
RequestUtil.redirect(String.format("%s?%s=%s", url, "employeeId", in.employeeId));

' #txt
Es0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect</name>
    </language>
</elementInfo>
' #txt
Es0 f12 168 234 112 44 -20 -8 #rect
Es0 f12 @|StepIcon #fIcon
Es0 f13 109 256 168 256 #arcP
>Proto Es0 .type ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeSearchInFrame.EmployeeSearchInFrameData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f3 mainOut f5 tail #connect
Es0 f5 head f4 mainIn #connect
Es0 f0 mainOut f10 tail #connect
Es0 f10 head f9 mainIn #connect
Es0 f9 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f11 mainOut f13 tail #connect
Es0 f13 head f12 mainIn #connect
