package com.axonivy.portal.dashboardmigration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;

/**
 * One-shot migration of every user's private dashboards: case custom fields were always stored as
 * {@code custom_case}, even when they belong to the business case, which sends the related case
 * subquery to the wrong scope. Re-types them to {@code custom_business_case}.
 * <p>
 * Reads and writes the user property directly instead of going through {@code DashboardService}:
 * {@code JsonConfigurationService} resolves every read and write against {@code Ivy.session().getSessionUser()},
 * so it can only ever touch the logged-in user - no good for a migration over all users.
 * <p>
 * Only columns are re-typed. Filters are left to the dashboard converter, which derives a filter's type
 * from its matching column on every read - and still runs, because this migration leaves the JSON version
 * untouched.
 * <p>
 * Idempotent: a second run finds no {@code custom_case} left and writes nothing.
 */
public final class DashboardColumnTypeMigration {

  /** Value of {@code PortalVariable.DASHBOARD}, inlined to keep this class off Portal's Java API. */
  private static final String DASHBOARD_PROPERTY = "Portal.Dashboard";
  private static final String WIDGETS = "widgets";
  private static final String COLUMNS = "columns";
  private static final String FIELD = "field";
  private static final String TYPE = "type";
  private static final String CUSTOM_CASE = "custom_case";
  private static final String CUSTOM_BUSINESS_CASE = "custom_business_case";

  private DashboardColumnTypeMigration() {}

  /**
   * Migrates the private dashboards of every user. One failing user never aborts the run.
   *
   * @return a summary line, also written to the log
   */
  public static String migrateAllUsers() {
    List<IUser> users = allUsers();
    Ivy.log().warn("Dashboard migration: starting for {0} user(s), property {1}.", users.size(), DASHBOARD_PROPERTY);

    int rewritten = 0;
    int untouched = 0;
    int failed = 0;
    for (IUser user : users) {
      try {
        if (migrateUser(user)) {
          rewritten++;
        } else {
          untouched++;
        }
      } catch (RuntimeException ex) {
        failed++;
        Ivy.log().error("Dashboard migration: FAILED for user {0}, their dashboards are left untouched.", ex,
            user.getName());
      }
    }

    String summary = String.format(
        "Dashboard migration finished: %d user(s) rewritten, %d already correct or empty, %d failed (of %d).",
        rewritten, untouched, failed, users.size());
    Ivy.log().warn(summary);
    return summary;
  }

  /**
   * Migrates the private dashboards of one user.
   *
   * @return true if the stored JSON was rewritten
   */
  public static boolean migrateUser(IUser user) {
    String storedJson = user.getProperty(DASHBOARD_PROPERTY);
    if (StringUtils.isBlank(storedJson)) {
      Ivy.log().warn("Dashboard migration: user {0} has nothing stored, skipped.", user.getName());
      return false;
    }
    Ivy.log().warn("Dashboard migration: user {0} original value of {1}: {2}", user.getName(), DASHBOARD_PROPERTY,
        storedJson);

    JsonNode dashboards;
    try {
      dashboards = new ObjectMapper().readTree(storedJson);
    } catch (JsonProcessingException ex) {
      Ivy.log().error("Dashboard migration: cannot read the dashboards of user {0}, they are left untouched.", ex,
          user.getName());
      return false;
    }

    if (!dashboards.isArray()) {
      // Typically the JSON literal null - nothing to migrate, and not ours to repair
      Ivy.log().warn("Dashboard migration: the dashboards of user {0} are no JSON array, skipped.", user.getName());
      return false;
    }

    List<String> retyped = new ArrayList<>();
    retypeDashboards(dashboards, retyped);
    if (retyped.isEmpty()) {
      Ivy.log().warn("Dashboard migration: user {0} has no {1} left, nothing written.", user.getName(), CUSTOM_CASE);
      return false;
    }

    String migratedJson = dashboards.toString();
    user.setProperty(DASHBOARD_PROPERTY, migratedJson);
    Ivy.log().warn("Dashboard migration: user {0} - re-typed {1} entr(ies) to {2}: {3}", user.getName(),
        retyped.size(), CUSTOM_BUSINESS_CASE, String.join(", ", retyped));
    Ivy.log().warn("Dashboard migration: user {0} migrated value of {1}: {2}", user.getName(), DASHBOARD_PROPERTY,
        migratedJson);
    return true;
  }

  private static List<IUser> allUsers() {
    return ISecurityContext.current().users().paged().stream()
        .filter(user -> !ISecurityConstants.SYSTEM_USER_NAME.equals(user.getName()))
        .collect(Collectors.toList());
  }

  private static void retypeDashboards(JsonNode dashboards, List<String> retyped) {
    for (JsonNode dashboard : dashboards) {
      String dashboardId = dashboard.path("id").asText("?");
      for (JsonNode widget : dashboard.path(WIDGETS)) {
        retypeColumns(widget, dashboardId, retyped);
      }
    }
  }

  private static void retypeColumns(JsonNode widget, String dashboardId, List<String> retyped) {
    String widgetId = widget.path("id").asText("?");
    for (JsonNode column : widget.path(COLUMNS)) {
      if (column instanceof ObjectNode columnNode && CUSTOM_CASE.equals(columnNode.path(TYPE).asText())) {
        columnNode.put(TYPE, CUSTOM_BUSINESS_CASE);
        retyped.add(String.format("%s/%s[%s]", dashboardId, widgetId, columnNode.path(FIELD).asText("?")));
      }
    }
  }
}
