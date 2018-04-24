[Ivy]
162668C788FE23AD 3.23 #module
>Proto >Proto Collection #zClass
Ae0 LibraryService Big #zClass
Ae0 WS #cInfo
Ae0 #process
Ae0 @TextInP .webServiceName .webServiceName #zField
Ae0 @TextInP .implementationClassName .implementationClassName #zField
Ae0 @TextInP .authenticationType .authenticationType #zField
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartWS ws0 '' #zField
Ae0 @EndWS ws1 '' #zField
Ae0 @GridStep f1 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @CallSub f7 '' #zField
Ae0 @PushWFArc f8 '' #zField
Ae0 @PushWFArc f0 '' #zField
Ae0 @GridStep f3 '' #zField
Ae0 @StartWS f4 '' #zField
Ae0 @EndWS f5 '' #zField
Ae0 @CallSub f6 '' #zField
Ae0 @PushWFArc f9 '' #zField
Ae0 @PushWFArc f10 '' #zField
Ae0 @PushWFArc f11 '' #zField
>Proto Ae0 Ae0 LibraryService #zField
Ae0 ws0 inParamDecl '<java.util.List<java.lang.String> apps> param;' #txt
Ae0 ws0 inParamTable 'out.apps=param.apps;
' #txt
Ae0 ws0 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyLibrary> libraries> result;
' #txt
Ae0 ws0 outParamTable 'result.errors=in.errors;
result.libraries=in.libraries;
' #txt
Ae0 ws0 actionDecl 'ch.ivy.ws.addon.LibraryServiceData out;
' #txt
Ae0 ws0 callSignature getLibraries(List<String>) #txt
Ae0 ws0 useUserDefinedException false #txt
Ae0 ws0 taskData TaskTriggered.PRI=2 #txt
Ae0 ws0 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLibraries(List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Ae0 ws0 @C|.responsibility Everybody #txt
Ae0 ws0 203 99 26 26 14 0 #rect
Ae0 ws0 @|StartWSIcon #fIcon
Ae0 ws1 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 ws1 203 299 26 26 14 0 #rect
Ae0 ws1 @|EndWSIcon #fIcon
Ae0 f1 actionDecl 'ch.ivy.ws.addon.LibraryServiceData out;
' #txt
Ae0 f1 actionTable 'out=in;
' #txt
Ae0 f1 actionCode 'import ch.ivy.ws.addon.bo.LibraryServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;

LibraryServiceResult result = WsServiceFactory.getLibraryService().getLibraries(in.apps);
in.libraries = result.getLibraries();
in.errors = result.getErrors();
' #txt
Ae0 f1 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get project libraries&#xD;
except Portal</name>
        <nameStyle>36,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f1 152 170 128 44 -46 -16 #rect
Ae0 f1 @|StepIcon #fIcon
Ae0 f2 expr out #txt
Ae0 f2 216 125 216 170 #arcP
Ae0 f7 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 f7 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Ae0 f7 doCall true #txt
Ae0 f7 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Ae0 f7 requestMappingAction 'param.errors=in.errors;
' #txt
Ae0 f7 responseActionDecl 'ch.ivy.ws.addon.LibraryServiceData out;
' #txt
Ae0 f7 responseMappingAction 'out=in;
' #txt
Ae0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f7 198 246 36 24 20 -2 #rect
Ae0 f7 @|CallSubIcon #fIcon
Ae0 f8 expr out #txt
Ae0 f8 216 214 216 246 #arcP
Ae0 f0 expr out #txt
Ae0 f0 216 270 216 299 #arcP
Ae0 f3 actionDecl 'ch.ivy.ws.addon.LibraryServiceData out;
' #txt
Ae0 f3 actionTable 'out=in;
' #txt
Ae0 f3 actionCode 'import ch.ivy.ws.addon.bo.LibraryServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;

LibraryServiceResult result = WsServiceFactory.getLibraryService().getLibrary(in.libraryId, in.appName);
in.library = result.getLibrary();
in.errors = result.getErrors();
' #txt
Ae0 f3 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find library</name>
    </language>
</elementInfo>
' #txt
Ae0 f3 440 169 112 44 -28 -8 #rect
Ae0 f3 @|StepIcon #fIcon
Ae0 f4 inParamDecl '<java.lang.String libraryId,java.lang.String appName> param;' #txt
Ae0 f4 inParamTable 'out.appName=param.appName;
out.libraryId=param.libraryId;
' #txt
Ae0 f4 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyLibrary library> result;
' #txt
Ae0 f4 outParamTable 'result.errors=in.errors;
result.library=in.library;
' #txt
Ae0 f4 actionDecl 'ch.ivy.ws.addon.LibraryServiceData out;
' #txt
Ae0 f4 callSignature getLibrary(String,String) #txt
Ae0 f4 useUserDefinedException false #txt
Ae0 f4 taskData TaskTriggered.PRI=2 #txt
Ae0 f4 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLibrary(String,String)</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f4 @C|.responsibility Everybody #txt
Ae0 f4 483 98 26 26 14 0 #rect
Ae0 f4 @|StartWSIcon #fIcon
Ae0 f5 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 f5 483 298 26 26 14 0 #rect
Ae0 f5 @|EndWSIcon #fIcon
Ae0 f6 type ch.ivy.ws.addon.LibraryServiceData #txt
Ae0 f6 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Ae0 f6 doCall true #txt
Ae0 f6 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Ae0 f6 requestMappingAction 'param.errors=in.errors;
' #txt
Ae0 f6 responseActionDecl 'ch.ivy.ws.addon.LibraryServiceData out;
' #txt
Ae0 f6 responseMappingAction 'out=in;
' #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f6 478 245 36 24 20 -2 #rect
Ae0 f6 @|CallSubIcon #fIcon
Ae0 f9 expr out #txt
Ae0 f9 496 213 496 245 #arcP
Ae0 f10 expr out #txt
Ae0 f10 496 269 496 298 #arcP
Ae0 f11 expr out #txt
Ae0 f11 496 124 496 169 #arcP
>Proto Ae0 .webServiceName ch.ivy.ws.addon.LibraryService #txt
>Proto Ae0 .authenticationType 'HTTP Basic' #txt
>Proto Ae0 .type ch.ivy.ws.addon.LibraryServiceData #txt
>Proto Ae0 .processKind WEB_SERVICE #txt
>Proto Ae0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ae0 -8 -8 16 16 16 26 #rect
>Proto Ae0 '' #fIcon
Ae0 ws0 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f1 mainOut f8 tail #connect
Ae0 f8 head f7 mainIn #connect
Ae0 f7 mainOut f0 tail #connect
Ae0 f0 head ws1 mainIn #connect
Ae0 f4 mainOut f11 tail #connect
Ae0 f11 head f3 mainIn #connect
Ae0 f3 mainOut f9 tail #connect
Ae0 f9 head f6 mainIn #connect
Ae0 f6 mainOut f10 tail #connect
Ae0 f10 head f5 mainIn #connect
