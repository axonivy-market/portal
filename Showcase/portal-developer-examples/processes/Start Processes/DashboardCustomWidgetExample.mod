[Ivy]
17B2F35B7DC5B800 9.3.1 #module
>Proto >Proto Collection #zClass
De0 DashboardCustomWidgetExample Big #zClass
De0 B #cInfo
De0 #process
De0 @AnnotationInP-0n ai ai #zField
De0 @TextInP .type .type #zField
De0 @TextInP .processKind .processKind #zField
De0 @TextInP .xml .xml #zField
De0 @TextInP .responsibility .responsibility #zField
De0 @EndTask f1 '' #zField
De0 @PushWFArc f2 '' #zField
De0 @UserDialog f3 '' #zField
De0 @PushWFArc f4 '' #zField
De0 @StartRequest f0 '' #zField
>Proto De0 De0 DashboardCustomWidgetExample #zField
De0 f1 337 49 30 30 0 15 #rect
De0 f2 280 64 337 64 #arcP
De0 f3 dialogId com.axonivy.portal.developerexamples.testdata.InvestmentList #txt
De0 f3 startMethod start(String,String,String) #txt
De0 f3 requestActionDecl '<String startDate,String note,String user> param;' #txt
De0 f3 requestMappingAction 'param.startDate=in.startDate;
param.note=in.note;
param.user=in.customer;
' #txt
De0 f3 responseMappingAction 'out=in;
' #txt
De0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InvestmentList</name>
    </language>
</elementInfo>
' #txt
De0 f3 168 42 112 44 -40 -8 #rect
De0 f4 111 64 168 64 #arcP
De0 f0 outLink investmentList.ivp #txt
De0 f0 inParamDecl '<String date__startDate,String user__customer,String string__note> param;' #txt
De0 f0 inParamTable 'out.customer=param.user__customer;
out.note=param.string__note;
out.startDate=param.date__startDate;
' #txt
De0 f0 requestEnabled true #txt
De0 f0 triggerEnabled false #txt
De0 f0 callSignature investmentList(String,String,String) #txt
De0 f0 startName 'Investment List (Example for Custom Widget on Dashboard)' #txt
De0 f0 startCustomFields isDashboardProcess=true #txt
De0 f0 caseData businessCase.attach=true #txt
De0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>investmentList.ivp</name>
    </language>
</elementInfo>
' #txt
De0 f0 @C|.responsibility Everybody #txt
De0 f0 81 49 30 30 -21 17 #rect
>Proto De0 .type com.axonivy.portal.developerexamples.showcase.CustomWidgetExampleData #txt
>Proto De0 .processKind NORMAL #txt
>Proto De0 0 0 32 24 18 0 #rect
>Proto De0 @|BIcon #fIcon
De0 f0 mainOut f4 tail #connect
De0 f4 head f3 mainIn #connect
De0 f3 mainOut f2 tail #connect
De0 f2 head f1 mainIn #connect
