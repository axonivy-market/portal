[Ivy]
14B33FEBF587FE2D 3.20 #module
>Proto >Proto Collection #zClass
Ss0 TaskFormProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ss0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ss0 @TextInP .resExport .resExport #zField
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @RichDialogInitStart f0 '' #zField
Ss0 @RichDialogProcessEnd f1 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @RichDialogProcessStart f3 '' #zField
Ss0 @RichDialogEnd f4 '' #zField
Ss0 @PushWFArc f5 '' #zField
Ss0 @RichDialogProcessStart f6 '' #zField
Ss0 @RichDialogProcessEnd f7 '' #zField
Ss0 @GridStep f11 '' #zField
Ss0 @PushWFArc f12 '' #zField
Ss0 @PushWFArc f9 '' #zField
>Proto Ss0 Ss0 TaskFormProcess #zField
Ss0 f0 guid 14B33FEBF8047203 #txt
Ss0 f0 type internaltest.TaskForm.TaskFormData #txt
Ss0 f0 method start() #txt
Ss0 f0 disableUIEvents true #txt
Ss0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ss0 f0 outParameterDecl '<> result;
' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 86 54 20 20 13 0 #rect
Ss0 f0 @|RichDialogInitStartIcon #fIcon
Ss0 f1 type internaltest.TaskForm.TaskFormData #txt
Ss0 f1 86 150 20 20 13 0 #rect
Ss0 f1 @|RichDialogProcessEndIcon #fIcon
Ss0 f2 expr out #txt
Ss0 f2 96 74 96 150 #arcP
Ss0 f3 guid 14B33FEFE9D85B84 #txt
Ss0 f3 type internaltest.TaskForm.TaskFormData #txt
Ss0 f3 actionDecl 'internaltest.TaskForm.TaskFormData out;
' #txt
Ss0 f3 actionTable 'out=in;
' #txt
Ss0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f3 214 54 20 20 13 0 #rect
Ss0 f3 @|RichDialogProcessStartIcon #fIcon
Ss0 f4 type internaltest.TaskForm.TaskFormData #txt
Ss0 f4 guid 14B33FF20755BB39 #txt
Ss0 f4 214 158 20 20 13 0 #rect
Ss0 f4 @|RichDialogEndIcon #fIcon
Ss0 f5 expr out #txt
Ss0 f5 224 74 224 158 #arcP
Ss0 f6 guid 160C5931F565508F #txt
Ss0 f6 type internaltest.TaskForm.TaskFormData #txt
Ss0 f6 actionDecl 'internaltest.TaskForm.TaskFormData out;
' #txt
Ss0 f6 actionTable 'out=in;
' #txt
Ss0 f6 actionCode '/*import javax.faces.context.FacesContext;
import ch.ivy.addon.portal.generic.bean.UserMenuBean;
String homePageUrl = new UserMenuBean().getHomePageURL();
FacesContext.getCurrentInstance().getExternalContext().redirect(homePageUrl);*/' #txt
Ss0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f6 339 51 26 26 -18 15 #rect
Ss0 f6 @|RichDialogProcessStartIcon #fIcon
Ss0 f7 type internaltest.TaskForm.TaskFormData #txt
Ss0 f7 339 243 26 26 0 12 #rect
Ss0 f7 @|RichDialogProcessEndIcon #fIcon
Ss0 f11 actionDecl 'internaltest.TaskForm.TaskFormData out;
' #txt
Ss0 f11 actionTable 'out=in;
' #txt
Ss0 f11 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();
' #txt
Ss0 f11 type internaltest.TaskForm.TaskFormData #txt
Ss0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>back to task list</name>
    </language>
</elementInfo>
' #txt
Ss0 f11 296 138 112 44 -42 -8 #rect
Ss0 f11 @|StepIcon #fIcon
Ss0 f12 expr out #txt
Ss0 f12 352 77 352 138 #arcP
Ss0 f9 expr out #txt
Ss0 f9 352 182 352 243 #arcP
>Proto Ss0 .type internaltest.TaskForm.TaskFormData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f3 mainOut f5 tail #connect
Ss0 f5 head f4 mainIn #connect
Ss0 f6 mainOut f12 tail #connect
Ss0 f12 head f11 mainIn #connect
Ss0 f11 mainOut f9 tail #connect
Ss0 f9 head f7 mainIn #connect
