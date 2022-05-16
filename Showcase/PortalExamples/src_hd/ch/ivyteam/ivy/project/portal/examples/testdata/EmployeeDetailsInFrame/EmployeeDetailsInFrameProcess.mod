[Ivy]
180B70AFCA03F952 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 EmployeeDetailsInFrameProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @AnnotationInP-0n ai ai #zField
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdEvent f3 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @UdExitEnd f4 '' #zField
Es0 @UdInit f0 '' #zField
Es0 @GridStep f6 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @PushWFArc f5 '' #zField
Es0 @PushWFArc f2 '' #zField
>Proto Es0 Es0 EmployeeDetailsInFrameProcess #zField
Es0 f3 guid 180B70FF6576E654 #txt
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
Es0 f1 499 51 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f4 211 147 26 26 0 12 #rect
Es0 f4 @|UdExitEndIcon #fIcon
Es0 f0 guid 180B70FF657C7EBE #txt
Es0 f0 method start(Integer) #txt
Es0 f0 inParameterDecl '<Integer employeeId> param;' #txt
Es0 f0 inParameterMapAction 'out.employeeId=param.employeeId;
' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Integer)</name>
    </language>
</elementInfo>
' #txt
Es0 f0 83 51 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f6 actionTable 'out=in;
' #txt
Es0 f6 actionCode 'if (in.employeeId == 1) {
	in.employee.id = 1;
	in.employee.firstName = "Peter";
	in.employee.lastName = "Parker";
	in.employee.city = "New York";
} else {
  in.employee.id = 2;
	in.employee.firstName = "Carol";
	in.employee.lastName = "Danver";
	in.employee.city = "Los Angeles";
}' #txt
Es0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Es0 f6 168 42 112 44 -21 -8 #rect
Es0 f6 @|StepIcon #fIcon
Es0 f7 109 64 168 64 #arcP
Es0 f5 109 160 211 160 #arcP
Es0 f2 280 64 499 64 #arcP
>Proto Es0 .type ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeDetailsInFrame.EmployeeDetailsInFrameData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f3 mainOut f5 tail #connect
Es0 f5 head f4 mainIn #connect
Es0 f0 mainOut f7 tail #connect
Es0 f7 head f6 mainIn #connect
Es0 f6 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
