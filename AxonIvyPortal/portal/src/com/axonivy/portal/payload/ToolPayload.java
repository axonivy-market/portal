package com.axonivy.portal.payload;

import java.util.Map;

import com.axonivy.portal.enums.Tool;


public class ToolPayload {
	private Tool tool;
	private Map<String, String> attributes;
	public Tool getTool() {
		return tool;
	}
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
}
