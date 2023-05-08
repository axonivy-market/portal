package com.axonivy.portal.components.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExternalLink extends AbstractConfiguration {
    private static final String ROLE_EVERYBODY = "Everybody";

    private String name;
    private String link;
    private Long creatorId;
    private String icon;
    private String description;
    private List<String> permissions;
    @JsonIgnore
    private List<String> defaultPermissions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    @JsonIgnore
    public boolean isAbleToEdit() {
        return this.creatorId == null ? true : this.creatorId == Ivy.session().getSessionUser().getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("ExternalLink {creatorId=%s, name=%s, link=%s, isPublic=%s, rolePermission=[%s], icon=%s}", creatorId, name, link,
                getIsPublic(), String.join(", ", permissions), icon);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getPermissions() {
        return this.getIsPublic() ? (CollectionUtils.isEmpty(permissions) ? getDefaultPermissions() : permissions) : new ArrayList<>();
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    private List<String> getDefaultPermissions() {
        defaultPermissions.add(ROLE_EVERYBODY);
        return defaultPermissions;
    }
}
