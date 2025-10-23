package com.axonivy.portal;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.core.engines.EmbeddedJmeterEngine;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.core.assertions.DslResponseAssertion.TargetField;

public class PerformancePortalTest {

  private String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

  @Test
  public void testPortalWalkthrough() throws IOException, InterruptedException, TimeoutException {
    // ====================Test one admin user====================
    System.out.println("=== Running test with 1 admin user ===");
    TestPlanStats stats1User = runPortalTest(1, "1_admin_user", "${__P(single_admin_user_server.csv)}");
    // Validate first test results
    validateTestResults(stats1User, "1 admin user test");


    // ====================Test for one normal user====================
    System.out.println("=== Running test with 1 normal user ===");
    TestPlanStats stats1NormalUser = runPortalTest(1, "1_normal_user", "${__P(single_normal_user_server.csv)}");
    // Validate first test results
    validateTestResults(stats1NormalUser, "1 normal user test");


    // ====================Test for 10 users====================
    System.out.println("=== Running test with 10 users ===");
    TestPlanStats stats10Users = runPortalTest(10, "10_normal_users", "${__P(users_server.csv)}");
    // Validate second test results
    validateTestResults(stats10Users, "10 users test");
  }

  private TestPlanStats runPortalTest(int numberOfUsers, String testName, String csvFilePath) throws IOException, InterruptedException, TimeoutException {
    String jtlDirName = String.format("target/jtls/%s", timestamp);

    return testPlan(
      threadGroup(testName)
        .rampTo(numberOfUsers,                 // Number of users
          Duration.ofSeconds(numberOfUsers))   // Ramp up time (1 second per user)
        .holdIterating(1)                      // portal.thread.loop
        .children(
          httpDefaults()
              .host("${__P(portal.server.host)}")  // portal.server.host
              .port(9000),                         // portal.server.port
          httpCookies(),

          httpHeaders().header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .header("Accept-Encoding", "gzip, deflate, br")
            .header("Accept-Language", "en-US,en;q=0.9")
            .header("Connection", "keep-alive")
            .header("Upgrade-Insecure-Requests", "1")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36"),

          csvDataSet(csvFilePath)
            .variableNames("username,password")
            .delimiter(",")
            .ignoreFirstLine(false),


          // UpdateSettingToAccessDetailWhenClickOnLineInTaskList ----------------------------
          httpSampler("UpdateSettingToAccessDetailWhenClickOnLineInTaskList"
            ,"/${__P(security.system.name)}/${__P(portal.application.name)}/pro/PortalKitTestHelper/17208192E0AF4185/updatePortalSetting.ivp")
            .method("GET")
            .param("settingName", "Portal.Tasks.BehaviourWhenClickingOnLineInTaskList")
            .param("settingValue", "ACCESS_TASK_DETAILS"),

          // PortalStart Transaction ----------------------------
          httpSampler("PortalStart",
            "/${__P(security.system.name)}/${__P(portal.application.name)}/pro/${__P(portal.portal)}/1549F58C18A6C562/DefaultApplicationHomePage.ivp")
            .method("GET")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") ")
            ),

          // Login
          httpSampler("Login"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "login-form:login-command")
            .param("javax.faces.partial.execute", "@all")
            .param("javax.faces.partial.render", "login:login-form")
            .param("login:login-form:login-command", "login:login-form:login-command")
            .param("login:login-form:username", "${username}")
            .param("login:login-form:password", "${password}")
            .param("login:login-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // PortalHome
          httpSampler("PortalHome"
            ,"/${__P(security.system.name)}/${__P(portal.application.name)}/pro/${__P(portal.portal)}/1549F58C18A6C562/DefaultApplicationHomePage.ivp")
            .method("GET")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") "),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToGlobalSearch
          httpSampler("NavigateToGlobalSearch"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "global-search-component:global-search-remote-cmd")
            .param("javax.faces.partial.execute", "global-search-component:global-search-data")
            .param("global-search-component:global-search-remote-cmd", "global-search-component:global-search-remote-cmd")
            .param("global-search-component:global-search-data", "leave")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // GlobalSearch
          httpSampler("GlobalSearch"
            ,"${redirectURL}")
            .method("POST")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\")"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToProcesses
          httpSampler("NavigateToProcesses"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.execute", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.render", "user-menu-required-login:main-navigator:main-menu")
            .param("user-menu-required-login:main-navigator:main-menu", "user-menu-required-login:main-navigator:main-menu")
            .param("taskId", "")
            .param("isWorkingOnATask", "false")
            .param("menuKind", "process")
            .param("menuUrl", "")
            .param("user-menu-required-login:main-navigator:main-menu_menuid", "process_1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // PortalProcesses
          httpSampler("PortalProcesses"
            ,"${redirectURL}")
            .method("GET")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") "),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // PortalProcesses_LazyLoadProcesses
          httpSampler("PortalProcesses_LazyLoadProcesses"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "process-widget:init-process-data-cmd ")
            .param("javax.faces.partial.execute", "process-widget:init-process-data-cmd")
            .param("javax.faces.partial.render", "process-widget:process-list process-widget:process-nav process-widget:process-view-mode")
            .param("process-widget:init-process-data-cmd", "process-widget:init-process-data-cmd")
            .param("portal-breadcrumb:breadcrumb-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") "),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToTaskList
          httpSampler("NavigateToTaskList"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.execute", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.render", "user-menu-required-login:main-navigator:main-menu")
            .param("user-menu-required-login:main-navigator:main-menu", "user-menu-required-login:main-navigator:main-menu")
            .param("taskId", "")
            .param("isWorkingOnATask", "false")
            .param("menuKind", "main_dashboard")
            .param("menuUrl", "")
            .param("user-menu-required-login:main-navigator:main-menu_menuid", "_js__default-task-list-dashboard-main-dashboard")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // PortalTaskList
          httpSampler("PortalTaskList"
            ,"${redirectURL}")
            .method("GET")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\")"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // LoadTaskListFirstTime
          httpSampler("LoadTaskListFirstTime"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "task-default_task_list_dashboard_task_1:task-component:rcLoadTaskFirstTime")
            .param("javax.faces.partial.execute", "task-default_task_list_dashboard_task_1:task-component:rcLoadTaskFirstTime")
            .param("javax.faces.partial.render", "task-default_task_list_dashboard_task_1:task-component:dashboard-tasks-container")
            .param("task-default_task_list_dashboard_task_1:task-component:rcLoadTaskFirstTime", "task-default_task_list_dashboard_task_1:task-component:rcLoadTaskFirstTime")
            .param("portal-breadcrumb:breadcrumb-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // TaskListSearch
          httpSampler("TaskListSearch"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "task-default_task_list_dashboard_task_1:quick-search-form:quick-search-input-0")
            .param("javax.faces.partial.execute", "task-default_task_list_dashboard_task_1:quick-search-form:quick-search-input-0")
            .param("javax.faces.partial.render", "task-default_task_list_dashboard_task_1:task-component:dashboard-tasks task-default_task_list_dashboard_task_1:task-component:empty-message-container")
            .param("javax.faces.behavior.event", "keydown")
            .param("javax.faces.partial.event", "keydown")
            .param("task-default_task_list_dashboard_task_1:quick-search-form:quick-search-input-0", "leave")
            .param("task-default_task_list_dashboard_task_1:quick-search-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToTaskDetail
          httpSampler("NavigateToTaskDetail"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source",
              "task-default_task_list_dashboard_task_1:task-component:dashboard-tasks:0:dashboard-tasks-columns:12:dashboard-actions-task-default_task_list_dashboard_task_1:task-open-detail-command")
            .param("javax.faces.partial.execute", "@all")
            .param("task-default_task_list_dashboard_task_1:task-component:dashboard-tasks:0:dashboard-tasks-columns:12:dashboard-actions-task-default_task_list_dashboard_task_1:task-open-detail-command",
              "task-default_task_list_dashboard_task_1:task-component:dashboard-tasks:0:dashboard-tasks-columns:12:dashboard-actions-task-default_task_list_dashboard_task_1:task-open-detail-command")
            .param("portal-breadcrumb:breadcrumb-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // PortalTaskDetail
          httpSampler("PortalTaskDetail"
            ,"${redirectURL}")
            .method("GET")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") "),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToCaseList
          httpSampler("NavigateToCaseList"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.execute", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.render", "user-menu-required-login:main-navigator:main-menu")
            .param("user-menu-required-login:main-navigator:main-menu", "user-menu-required-login:main-navigator:main-menu")
            .param("taskId", "")
            .param("isWorkingOnATask", "false")
            .param("menuKind", "main_dashboard")
            .param("menuUrl", "")
            .param("user-menu-required-login:main-navigator:main-menu_menuid", "_js__default-case-list-dashboard-main-dashboard")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // PortalCaseList
          httpSampler("PortalCaseList"
            ,"${redirectURL}")
            .method("GET")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\")"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // LoadCaseListFirstTime
          httpSampler("LoadCaseListFirstTime"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "case-default_case_list_dashboard_case_1:case-component:rcLoadCaseFirstTime")
            .param("javax.faces.partial.execute", "case-default_case_list_dashboard_case_1:case-component:rcLoadCaseFirstTime")
            .param("javax.faces.partial.render", "case-default_case_list_dashboard_case_1:case-component:dashboard-cases-container")
            .param("case-default_case_list_dashboard_case_1:case-component:rcLoadCaseFirstTime"
              , "case-default_case_list_dashboard_case_1:case-component:rcLoadCaseFirstTime")
            .param("portal-breadcrumb:breadcrumb-form_SUBMIT" , "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // CaseListSearch
          httpSampler("CaseListSearch"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "case-default_case_list_dashboard_case_1:quick-search-form:quick-search-input-0")
            .param("javax.faces.partial.execute", "case-default_case_list_dashboard_case_1:quick-search-form:quick-search-input-0")
            .param("javax.faces.partial.render", "case-default_case_list_dashboard_case_1:case-component:dashboard-cases case-default_case_list_dashboard_case_1:case-component:empty-message-container")
            .param("javax.faces.behavior.event", "keydown")
            .param("javax.faces.partial.event", "keydown")
            .param(
              "case-default_case_list_dashboard_case_1:quick-search-form:quick-search-input-0",
              "leave")
            .param("case-default_case_list_dashboard_case_1:quick-search-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // CaseListFilterLargeBusinessCase
          httpSampler("CaseListFilterLargeBusinessCase"
            ,"${url}")
            .method("POST")

            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source",
              "case-default_case_list_dashboard_case_1:quick-search-form:quick-search-input-0")
            .param("javax.faces.partial.execute",
              "case-default_case_list_dashboard_case_1:quick-search-form:quick-search-input-0")
            .param("javax.faces.partial.render",
              "case-default_case_list_dashboard_case_1:case-component:dashboard-cases case-default_case_list_dashboard_case_1:case-component:empty-message-container")
            .param("javax.faces.behavior.event", "keydown")
            .param("javax.faces.partial.event", "keydown")
            .param("case-default_case_list_dashboard_case_1:quick-search-form:quick-search-input-0",
              "Business case has lots of tasks cases")
            .param("case-default_case_list_dashboard_case_1:quick-search-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToCaseDetail
          httpSampler("NavigateToCaseDetail"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source",
              "case-default_case_list_dashboard_case_1:case-component:dashboard-cases:0:dashboard-cases-columns:9:dashboard-actions-case-default_case_list_dashboard_case_1:case-item-open-detail-link")
            .param("javax.faces.partial.execute", "@all")
            .param("case-default_case_list_dashboard_case_1:case-component:dashboard-cases:0:dashboard-cases-columns:9:dashboard-actions-case-default_case_list_dashboard_case_1:case-item-open-detail-link",
              "case-default_case_list_dashboard_case_1:case-component:dashboard-cases:0:dashboard-cases-columns:9:dashboard-actions-case-default_case_list_dashboard_case_1:case-item-open-detail-link")
            .param("portal-breadcrumb:breadcrumb-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // PortalCaseDetail
          httpSampler("PortalCaseDetail"
            ,"${redirectURL}")
            .method("GET")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToDashboard
          httpSampler("NavigateToDashboard"
            ,"/${__P(security.system.name)}/${__P(portal.application.name)}/pro/${__P(portal.portal)}/1549F58C18A6C562/DefaultApplicationHomePage.ivp")
            .method("GET")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200"),
              regexExtractor("url", "action=\"([^\"]+)\""),
              regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") ")
            ),

          // Dashboard_FirstTab_YourTasks
          httpSampler("Dashboard_FirstTab_YourTasks"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "task-task_1:task-component:rcLoadTaskFirstTime")
            .param("javax.faces.partial.execute", "task-task_1:task-component:rcLoadTaskFirstTime")
            .param("javax.faces.partial.render",
              "task-task_1:task-component:dashboard-tasks-container")
            .param("task-task_1:task-component:rcLoadTaskFirstTime",
              "task-task_1:task-component:rcLoadTaskFirstTime")
            .param("user-menu-required-login:global-search-component-mobile:global-search-data", "")
            .param("user-menu-required-login:global-search-component-mobile-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // Dashboard_FirstTab_YourCases
          httpSampler("Dashboard_FirstTab_YourCases"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "case-case_1:case-component:rcLoadCaseFirstTime")
            .param("javax.faces.partial.execute", "case-case_1:case-component:rcLoadCaseFirstTime")
            .param("javax.faces.partial.render",
              "case-case_1:case-component:dashboard-cases-container")
            .param("case-case_1:case-component:rcLoadCaseFirstTime",
              "case-case_1:case-component:rcLoadCaseFirstTime")
            .param("user-menu-required-login:global-search-component-mobile:global-search-data", "")
            .param("user-menu-required-login:global-search-component-mobile-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // Dashboard_FirstTab_ProcessWidget
          httpSampler("Dashboard_FirstTab_ProcessWidget"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "process-process_1:process-component:rcLoadProcessFirstTime")
            .param("javax.faces.partial.execute",
              "process-process_1:process-component:rcLoadProcessFirstTime")
            .param("javax.faces.partial.render",
              "process-process_1:process-component:dashboard-processes-container")
            .param("process-process_1:process-component:rcLoadProcessFirstTime",
              "process-process_1:process-component:rcLoadProcessFirstTime")
            .param("user-menu-required-login:global-search-component-mobile:global-search-data", "")
            .param("user-menu-required-login:global-search-component-mobile-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToDashboard_SecondTab_StoreSelectedMenuItem
          httpSampler("NavigateToDashboard_SecondTab_StoreSelectedMenuItem"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source",
              "user-menu-required-login:main-navigator:store-selected-menuitems-rc")
            .param("javax.faces.partial.execute",
              "user-menu-required-login:main-navigator:store-selected-menuitems-rc")
            .param("user-menu-required-login:main-navigator:store-selected-menuitems-rc",
              "user-menu-required-login:main-navigator:store-selected-menuitems-rc")
            .param("selectedMenuId",
              "user-menu-required-login:main-navigator:main-menu__js__customized-dashboard-sub-dashboard")
            .param("isWorkingOnATask", "false")
            .param("isOpenOnNewTab", "false")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // NavigateToDashboard_SecondTab_ClickOnSubMenuItem
          httpSampler("NavigateToDashboard_SecondTab_ClickOnSubMenuItem"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.execute", "user-menu-required-login:main-navigator:main-menu")
            .param("javax.faces.partial.render", "user-menu-required-login:main-navigator:main-menu")
            .param("user-menu-required-login:main-navigator:main-menu",
              "user-menu-required-login:main-navigator:main-menu")
            .param("taskId", "")
            .param("isWorkingOnATask", "false")
            .param("menuKind", "dashboard")
            .param("menuUrl", "")
            .param("user-menu-required-login:main-navigator:main-menu_menuid",
              "_js__customized-dashboard-sub-dashboard")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">"),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // Dashboard_SecondTab
          httpSampler("Dashboard_SecondTab"
            ,"${redirectURL}")
            .method("GET")
            .children(
              regexExtractor("url", "action=\"([^\"]+)\""),
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // Dashboard_SecondTab_YourTasks
          httpSampler("Dashboard_SecondTab_YourTasks"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "task-task_1:task-component:rcLoadTaskFirstTime")
            .param("javax.faces.partial.execute", "task-task_1:task-component:rcLoadTaskFirstTime")
            .param("javax.faces.partial.render",
              "task-task_1:task-component:dashboard-tasks-container")
            .param("task-task_1:task-component:rcLoadTaskFirstTime",
              "task-task_1:task-component:rcLoadTaskFirstTime")
            .param("user-menu-required-login:global-search-component-mobile:global-search-data", "")
            .param("user-menu-required-login:global-search-component-mobile-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),

          // Dashboard_SecondTab_YourCases
          httpSampler("Dashboard_SecondTab_YourCases"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "case-custom-case_1:case-component:rcLoadCaseFirstTime")
            .param("javax.faces.partial.execute",
              "case-custom-case_1:case-component:rcLoadCaseFirstTime")
            .param("javax.faces.partial.render",
              "case-custom-case_1:case-component:dashboard-cases-container")
            .param("case-custom-case_1:case-component:rcLoadCaseFirstTime",
              "case-custom-case_1:case-component:rcLoadCaseFirstTime")
            .param("user-menu-required-login:global-search-component-mobile:global-search-data", "")
            .param("user-menu-required-login:global-search-component-mobile-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),
          // Dashboard_SecondTab_ProcessWidget
          httpSampler("Dashboard_SecondTab_ProcessWidget"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source",
              "process-custom-compact-mode_1:process-component:rcLoadProcessFirstTime")
            .param("javax.faces.partial.execute",
              "process-custom-compact-mode_1:process-component:rcLoadProcessFirstTime")
            .param("javax.faces.partial.render",
              "process-custom-compact-mode_1:process-component:dashboard-processes-container")
            .param("process-custom-compact-mode_1:process-component:rcLoadProcessFirstTime",
              "process-custom-compact-mode_1:process-component:rcLoadProcessFirstTime")
            .param("user-menu-required-login:global-search-component-mobile:global-search-data", "")
            .param("user-menu-required-login:global-search-component-mobile-form_SUBMIT", "1")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            ),
          // Logout
          httpSampler("Logout"
            ,"${url}")
            .method("POST")
            .param("javax.faces.partial.ajax", "true")
            .param("javax.faces.source", "logout-setting:logout-menu-item")
            .param("javax.faces.partial.execute", "@all")
            .param("logout-setting:logout-menu-item", "logout-setting:logout-menu-item")
            .param("javax.faces.ViewState", "${viewState}")
            .children(
              responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
            )
        ),

      // Listeners and writers:
      jtlWriter(jtlDirName, testName + ".jtl"),  // path to directory and jtl file name 
      // resultsTreeVisualizer(),                // Remove comment on local to debug, since server doesn't have the UI
      htmlReporter("target/html-report/" + testName)
      // portal-performance-test\jmeter\test.properties
    ).runIn(new EmbeddedJmeterEngine().propertiesFile("jmeter/test.properties"));
  }
  
  private void validateTestResults(TestPlanStats stats, String testDescription) {
    // Validate test results - fail the test if there are any errors
    if (stats.overall().errorsCount() > 0) {
      double errorPercentage = (double) stats.overall().errorsCount() / stats.overall().samplesCount() * 100;
      fail(String.format("%s failed with %d errors out of %d samples (%.2f%% error rate)", 
           testDescription,
           stats.overall().errorsCount(), 
           stats.overall().samplesCount(), 
           errorPercentage));
    } else {
      System.out.printf("%s completed successfully: %d samples, 0 errors%n", testDescription, stats.overall().samplesCount());
    }
  }

}
