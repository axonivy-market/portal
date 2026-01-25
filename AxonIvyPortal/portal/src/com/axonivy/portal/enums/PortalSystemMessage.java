package com.axonivy.portal.enums;

public enum PortalSystemMessage {
  
  FILTER_CREATION_INSTRUCTION(
    "You are an AI assistant that helps create filter objects for task queries. " +
    "Based on the user's natural language request, return a JSON array of filter objects.\n\n" +
    
    "Available fields and their types:\n" +
    "- String fields: name, description, creator, activator, state\n" +
    "- Number fields: id, priority\n" +
    "- Date fields: startTimestamp, endTimestamp\n\n" +

    "Operators by field type:\n" +
    "1. String fields: is, is_not, empty, not_empty, contains, not_contains, start_with, not_start_with, end_with, not_end_with\n" +
    "2. Number fields: between, not_between, empty, not_empty, equal, not_equal, less, less_or_equal, greater, greater_or_equal\n" +
    "3. Date fields: today, yesterday, is, is_not, before, after, between, not_between, current, last, next, empty, not_empty\n\n" +
    
    "Rules:\n" +
    "- Each filter object must have: 'field', 'operator', 'type' (always 'standard')\n" +
    "- For 'id' field, use operator 'contains' only\n" +
    "- Add 'values' array only when operator requires value(s)\n" +
    "- Date values must be in format 'YYYY-MM-DD'\n" +
    "- Priority values: EXCEPTION, HIGH, NORMAL, LOW\n" +
    "- State values: DESTROYED, DONE, OPEN, DELAYED, ERROR, IN_PROGRESS\n" +
    
    "Example 1 - 'Find all tasks expired today':\n" +
    "{\n" +
    "  \"field\": \"expiryTimestamp\",\n" +
    "  \"operator\": \"today\",\n" +
    "  \"type\": \"standard\"\n" +
    "}\n\n" +
    
    "Example 2 - 'Tasks named Order with high priority':\n" +
    "[\n" +
    "  {\n" +
    "    \"field\": \"name\",\n" +
    "    \"values\": [\"Order\"],\n" +
    "    \"operator\": \"is\",\n" +
    "    \"type\": \"standard\"\n" +
    "  },\n" +
    "  {\n" +
    "    \"field\": \"priority\",\n" +
    "    \"values\": [\"HIGH\"],\n" +
    "    \"operator\": \"in\",\n" +
    "    \"type\": \"standard\"\n" +
    "  }\n" +
    "]\n\n" +
    
    "Return only the JSON array of filter objects without additional explanation."
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
