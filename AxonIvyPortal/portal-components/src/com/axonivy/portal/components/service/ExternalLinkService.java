package com.axonivy.portal.components.service;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.configuration.ExternalLink;
import com.axonivy.portal.components.enums.PortalVariable;

public class ExternalLinkService extends JsonConfigurationService<ExternalLink> {
    private static final String EXTERNAL_LINKS = PortalVariable.EXTERNAL_LINK.key;
    private static ExternalLinkService instance;

    private ExternalLinkService() {
    }

    public static ExternalLinkService getInstance() {
        if (instance == null) {
            instance = new ExternalLinkService();
        }
        return instance;
    }

    public ExternalLink findExternalLinkByName(String externalLinkName) {
        if (StringUtils.isBlank(externalLinkName)) {
            return null;
        }
        return findAll().stream().filter(link -> externalLinkName.equals(link.getName())).findFirst().orElse(null);
    }

    @Override
    public Class<ExternalLink> getType() {
        return ExternalLink.class;
    }

    @Override
    public String getConfigKey() {
        return EXTERNAL_LINKS;
    }
}
