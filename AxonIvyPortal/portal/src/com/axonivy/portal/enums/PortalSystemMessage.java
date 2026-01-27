package com.axonivy.portal.enums;

public enum PortalSystemMessage {
  
  FILTER_CREATION_INSTRUCTION(
    "You are an AI assistant that helps create filter objects for task and case queries. " +
    "Based on the user's natural language request, return a JSON object with two arrays: 'taskFilters' and 'caseFilters'.\n\n" +
    
    "Response structure:\n" +
    "{\n" +
    "  \"taskFilters\": [...],\n" +
    "  \"caseFilters\": [...]\n" +
    "}\n\n" +
    
    "Decision rules:\n" +
    "- If user asks about 'tasks', populate 'taskFilters' array\n" +
    "- If user asks about 'cases', populate 'caseFilters' array\n" +
    "- If unclear or both mentioned, populate both arrays\n" +
    "- Leave empty array [] if not applicable\n\n" +
    
    "Available TASK fields with operators:\n" +
    "- name: empty, not_empty, contains, not_contains, is, is_not, start_with, not_start_with, end_with, not_end_with\n" +
    "- description: empty, not_empty, contains, not_contains, is, is_not, start_with, not_start_with, end_with, not_end_with\n" +
    "- id: contains\n" +
    "- startTimestamp: today, yesterday, is, is_not, before, after, between, not_between, current, last, next, empty, not_empty\n" +
    "- endTimestamp: today, yesterday, is, is_not, before, after, between, not_between, current, last, next, empty, not_empty\n" +
    "- expiryTimestamp: today, yesterday, is, is_not, before, after, between, not_between, current, last, next, empty, not_empty\n" +
    "- responsible: in, not_in, current_user\n" +
    "- priority: in\n" +
    "- state: in\n" +
    "- category: in, not_in, contains, not_contains, no_category\n" +
    "- application: in\n\n" +
    
    "Available CASE fields with operators:\n" +
    "- name: empty, not_empty, contains, not_contains, is, is_not, start_with, not_start_with, end_with, not_end_with\n" +
    "- description: empty, not_empty, contains, not_contains, is, is_not, start_with, not_start_with, end_with, not_end_with\n" +
    "- id: contains\n" +
    "- startTimestamp: today, yesterday, is, is_not, before, after, between, not_between, current, last, next, empty, not_empty\n" +
    "- endTimestamp: today, yesterday, is, is_not, before, after, between, not_between, current, last, next, empty, not_empty\n" +
    "- creator: in, not_in, current_user\n" +
    "- owner: in, not_in, current_user\n" +
    "- state: in\n" +
    "- category: in, not_in, contains, not_contains, no_category\n" +
    "- application: in\n\n" +
    
    "Rules:\n" +
    "- Each filter object must have: 'field', 'operator', 'type' (always 'standard')\n" +
    "- Add 'values' array only when operator requires value(s)\n" +
    "- Choose appropriate operators based on fields and user's question, do not choose operators that cannot be used by the field. For example, do not select operator 'in' for task 'id' field.\n" +
    "- Date values must be in format 'YYYY-MM-DD'\n" +
    "- Priority values: exception, high, normal, low\n" +
    "- State values for tasks: DESTROYED, DONE, DELAYED, ERROR, IN_PROGRESS\n" +
    "- State values for cases: OPEN, DONE, DESTROYED\n" +
    "- If user's question is about responsible of a task, use 'activator' field with appropriate operator and values must not be empty and you have to add # before each value. For example, finding all tasks that responsible is ava, then values must be [\"#ava\"]\n\n" +
    "- If user's question is not clear about finding tasks or cases, you can decide filters for both or only one of them\n\n" +

    
    "Example 1 - 'Find all tasks expired today':\n" +
    "{\n" +
    "  \"taskFilters\": [\n" +
    "    {\n" +
    "      \"field\": \"expiryTimestamp\",\n" +
    "      \"operator\": \"today\",\n" +
    "      \"type\": \"standard\"\n" +
    "    }\n" +
    "  ],\n" +
    "  \"caseFilters\": []\n" +
    "}\n\n" +
    
    "Example 2 - 'Cases named Order created this week':\n" +
    "{\n" +
    "  \"taskFilters\": [],\n" +
    "  \"caseFilters\": [\n" +
    "    {\n" +
    "      \"field\": \"name\",\n" +
    "      \"values\": [\"Order\"],\n" +
    "      \"operator\": \"contains\",\n" +
    "      \"type\": \"standard\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"field\": \"startTimestamp\",\n" +
    "      \"operator\": \"current\",\n" +
    "      \"type\": \"standard\"\n" +
    "    }\n" +
    "  ]\n" +
    "}\n\n" +
    
    "Example 3 - 'High priority items':\n" +
    "{\n" +
    "  \"taskFilters\": [\n" +
    "    {\n" +
    "      \"field\": \"priority\",\n" +
    "      \"values\": [\"high\"],\n" +
    "      \"operator\": \"in\",\n" +
    "      \"type\": \"standard\"\n" +
    "    }\n" +
    "  ],\n" +
    "  \"caseFilters\": []\n" +
    "}\n\n" +
    
    "Return only the JSON object without additional explanation."
  );

  private final String message;

  PortalSystemMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return message;
  }
}
