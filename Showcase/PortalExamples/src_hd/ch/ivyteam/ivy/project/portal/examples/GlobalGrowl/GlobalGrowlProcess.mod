[Ivy]
16A8BFFA46F320D8 3.20 #module
>Proto >Proto Collection #zClass
Gs0 GlobalGrowlProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Gs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Gs0 @TextInP .resExport .resExport #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @RichDialogInitStart f0 '' #zField
Gs0 @RichDialogProcessEnd f1 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @RichDialogProcessStart f3 '' #zField
Gs0 @RichDialogEnd f4 '' #zField
Gs0 @GridStep f6 '' #zField
Gs0 @PushWFArc f7 '' #zField
Gs0 @PushWFArc f5 '' #zField
Gs0 @RichDialogProcessStart f8 '' #zField
Gs0 @GridStep f10 '' #zField
Gs0 @PushWFArc f11 '' #zField
Gs0 @GridStep f9 '' #zField
Gs0 @PushWFArc f14 '' #zField
>Proto Gs0 Gs0 GlobalGrowlProcess #zField
Gs0 f0 guid 16A8BFFA48B394D4 #txt
Gs0 f0 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f0 method start() #txt
Gs0 f0 disableUIEvents true #txt
Gs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Gs0 f0 outParameterDecl '<> result;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -16 15 #rect
Gs0 f0 @|RichDialogInitStartIcon #fIcon
Gs0 f1 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f1 211 51 26 26 0 12 #rect
Gs0 f1 @|RichDialogProcessEndIcon #fIcon
Gs0 f2 expr out #txt
Gs0 f2 109 64 211 64 #arcP
Gs0 f3 guid 16A8BFFA49FD2F6C #txt
Gs0 f3 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f3 actionDecl 'ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData out;
' #txt
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 83 147 26 26 -15 12 #rect
Gs0 f3 @|RichDialogProcessStartIcon #fIcon
Gs0 f4 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f4 guid 16A8BFFA49F488C7 #txt
Gs0 f4 435 147 26 26 0 12 #rect
Gs0 f4 @|RichDialogEndIcon #fIcon
Gs0 f6 actionDecl 'ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData out;
' #txt
Gs0 f6 actionTable 'out=in;
' #txt
Gs0 f6 actionCode 'import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

FacesMessage message = new FacesMessage("Task is done successfully");
FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);

Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
flash.put("overridePortalGrowl", true);
flash.setRedirect(true);
flash.setKeepMessages(true);' #txt
Gs0 f6 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display custom growl message
after finish task</name>
        <nameStyle>46,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f6 168 138 208 44 -83 -16 #rect
Gs0 f6 @|StepIcon #fIcon
Gs0 f7 expr out #txt
Gs0 f7 109 160 168 160 #arcP
Gs0 f5 expr out #txt
Gs0 f5 376 160 435 160 #arcP
Gs0 f8 guid 16F1880E0F0C29E1 #txt
Gs0 f8 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f8 actionDecl 'ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData out;
' #txt
Gs0 f8 actionTable 'out=in;
' #txt
Gs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f8 83 251 26 26 -18 15 #rect
Gs0 f8 @|RichDialogProcessStartIcon #fIcon
Gs0 f10 actionDecl 'ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData out;
' #txt
Gs0 f10 actionTable 'out=in;
' #txt
Gs0 f10 actionCode 'import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

FacesMessage message = new FacesMessage("You have cancelled and left the task successfully");
FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);

Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
flash.put("overridePortalGrowl", true);
flash.setRedirect(true);
flash.setKeepMessages(true);' #txt
Gs0 f10 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display custom growl message
after cancel task</name>
        <nameStyle>46,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f10 168 242 208 44 -83 -16 #rect
Gs0 f10 @|StepIcon #fIcon
Gs0 f11 expr out #txt
Gs0 f11 109 264 168 264 #arcP
Gs0 f9 actionDecl 'ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData out;
' #txt
Gs0 f9 actionTable 'out=in;
' #txt
Gs0 f9 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();
' #txt
Gs0 f9 type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
Gs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Go to End Page</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f9 472 242 112 44 -43 -8 #rect
Gs0 f9 @|StepIcon #fIcon
Gs0 f14 expr out #txt
Gs0 f14 376 264 472 264 #arcP
>Proto Gs0 .type ch.ivyteam.ivy.project.portal.examples.GlobalGrowl.GlobalGrowlData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f3 mainOut f7 tail #connect
Gs0 f7 head f6 mainIn #connect
Gs0 f6 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
Gs0 f8 mainOut f11 tail #connect
Gs0 f11 head f10 mainIn #connect
Gs0 f10 mainOut f14 tail #connect
Gs0 f14 head f9 mainIn #connect
