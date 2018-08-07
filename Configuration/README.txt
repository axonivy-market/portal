1. To resolve dependencies in designer, configure designer using maven version 3.1.1 and the configuration file is at https://192.168.110.51/svn/development/xpertivy/wawa/trunk/build/maven-config/server8/settings.xml
In designer, go to Window->Preferences->Maven->User Setting to check settings.xml file is correct

2. To enable modena theme in designer
   - Add to your-designer\webapps\ivy\WEB-INF\web.xml
       <context-param>
           <param-name>primefaces.THEME</param-name>
           <param-value>modena</param-value>
       </context-param>
   - Add to your-designer\webapps\ivy\WEB-INF\lib file theme\modena-theme-1.0.1.jar
