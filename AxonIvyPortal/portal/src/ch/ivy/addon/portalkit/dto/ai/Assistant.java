package ch.ivy.addon.portalkit.dto.ai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.components.service.exception.PortalException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.service.AiToolService;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Assistant extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 7885313923903511903L;

  @JsonIgnore
  private List<AiTool> toolkit;

  private String name;
  private List<String> tools;

  public Assistant() {
    this.setIsPublic(true);
  }

  public void init(String id, String name) {
    setId(id);
    this.name = name;
  }

  @JsonIgnore
  public List<AiTool> getToolkit() {
    return toolkit;
  }

  @JsonIgnore
  public void setToolkit(List<AiTool> toolkit) {
    this.toolkit = toolkit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toJson() {
    return BusinessEntityConverter.entityToJsonValue(this);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Assistant other = (Assistant) obj;
    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equals(other.getId()))
      return false;
    return true;
  }

  public List<String> getTools() {
    return tools;
  }

  public void setTools(List<String> tools) {
    this.tools = tools;
  }

  public void initToolkit() {
    if (CollectionUtils.isEmpty(tools)) {
      this.toolkit = new ArrayList<>();
      this.tools = new ArrayList<>();
    }

    List<AiTool> allTools = AiToolService.getInstance().getPublicConfig();
    this.toolkit = allTools.stream()
        .filter(tool -> this.tools.contains(tool.getId()))
        .collect(Collectors.toList());
  }

  public static Assistant addNewAssistant() {
    Assistant result = new Assistant();
    result.setId(UUID.randomUUID().toString());
    result.setName("");
    result.setTools(new ArrayList<>());
    result.setToolkit(new ArrayList<>());
    return result;
  }

  public String buildDetailsJson() {
    ObjectMapper mapper = BusinessEntityConverter.getObjectMapper();
    ObjectNode result;
    try {
      result = (ObjectNode) mapper
          .readTree(BusinessEntityConverter.entityToJsonValue(this));
    } catch (JsonMappingException e) {
      throw new PortalException(e);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }

    if (CollectionUtils.isNotEmpty(this.toolkit)) {
      ArrayNode toolkitNode = mapper.createArrayNode();
      for (AiTool tool : this.toolkit) {
        JsonNode node = tool.buildJsonNode();
        if (node != null) {
          toolkitNode.add(node);
        }
      }
      result.set("toolkit", toolkitNode);
    }

    result.remove("tools");

    try {
      return mapper.writeValueAsString(result);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public void addTool(AiTool tool) {
    this.toolkit.add(tool);
    this.tools.add(Optional.ofNullable(tool).map(AiTool::getId).orElse(""));
  }

  public void removeTool(AiTool toolToRemove) {
    if (Optional.ofNullable(toolToRemove).map(AiTool::getId).isEmpty()) {
      return;
    }

    for (AiTool tool : toolkit) {
      if (tool.getId().contentEquals(toolToRemove.getId())) {
        toolkit.remove(tool);
        break;
      }
    }

    for (String toolId : tools) {
      if (toolId.contentEquals(toolToRemove.getId())) {
        tools.remove(toolId);
        break;
      }
    }
  }
}
