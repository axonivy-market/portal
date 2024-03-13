package com.axonivy.portal.ai.assistant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.axonivy.portal.ai.tool.PortalSupport;

import ch.ivy.addon.portalkit.dto.ai.Assistant;
import ch.ivy.addon.portalkit.dto.ai.IvyCallableTool;
import ch.ivy.addon.portalkit.dto.ai.IvyToolAttribute;

public class PortalAssitant extends Assistant {

  private static final long serialVersionUID = 1775967998065140034L;

  public PortalAssitant() {
    super.init("portal-assistant", "Portal Assistant");

    // Retrieval QA tool
    PortalSupport supportTool = new PortalSupport();
    supportTool.setDefault(true);

    // Ivy callable tool: find task by some attributes
    IvyCallableTool findTasksTool = new IvyCallableTool();
    findTasksTool.setPermissions(Arrays.asList("Developer", "CostObject"));
    findTasksTool.setName("findTasks");
    findTasksTool.setSignature("findTasks(String,String,String,String)");
    findTasksTool.setDescription(
        "This tool is helpful when user want to find tasks by attributes.");
    List<IvyToolAttribute> findTaskAttributes = new ArrayList<>();
    findTaskAttributes
        .add(new IvyToolAttribute("taskName", "", "Name of the task"));
    findTaskAttributes.add(
        new IvyToolAttribute("taskDescription", "", "Description of the task"));
    findTaskAttributes.add(new IvyToolAttribute("taskPriority", "",
        "Priority of a task. Valid values for this attribute: low, normal, high, exception"));
    findTaskAttributes.add(new IvyToolAttribute("taskState", "",
        "State of the task. Valid values for this attribute: open, in progress, done"));
    findTasksTool.setAttributes(findTaskAttributes);

    // Ivy tool: find case by some attributes
    IvyCallableTool findCaseTool = new IvyCallableTool();
    findCaseTool.setName("findCases");
    findTasksTool.setPermissions(Arrays.asList("Developer", "#demo"));
    findCaseTool.setSignature("findCases(String,String,String)");
    findCaseTool.setDescription(
        "This tool is helpful when user want to find cases by attributes.");
    findCaseTool.setPermissions(Arrays.asList("Everybody"));
    List<IvyToolAttribute> findCaseAttributes = new ArrayList<>();
    findCaseAttributes
        .add(new IvyToolAttribute("caseName", "", "Name of the case"));
    findCaseAttributes.add(
        new IvyToolAttribute("caseDescription", "", "Description of the case"));
    findCaseAttributes.add(new IvyToolAttribute("caseState", "",
        "State of the case. Valid values for this attribute: open, done, destroyed"));
    findCaseTool.setAttributes(findCaseAttributes);

    // Ivy callable tool: find user information
    IvyCallableTool findUserTool = new IvyCallableTool();
    findUserTool.setName("findUser");
    findUserTool.setDescription(
        "This tool is helpful when user want to find users by name or role of users.");
    findUserTool.setSignature("findUsers(String,String)");
    findUserTool.setPermissions(Arrays.asList("Everybody"));

    List<IvyToolAttribute> findUserAttributes = new ArrayList<>();
    findUserAttributes
        .add(new IvyToolAttribute("username", "", "name of user"));
    findUserAttributes.add(new IvyToolAttribute("role", "", "role of user"));
    findUserTool.setAttributes(findUserAttributes);

    this.setToolkit(
        Arrays.asList(supportTool, findTasksTool, findUserTool, findCaseTool));
  }
}
