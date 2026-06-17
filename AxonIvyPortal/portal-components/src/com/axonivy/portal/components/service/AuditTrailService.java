package com.axonivy.portal.components.service;

import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.components.dto.AuditTrailNoteDTO;

import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.ICase;

public class AuditTrailService {
    private static AuditTrailService instance;

    public static AuditTrailService getInstance() {
        if (instance == null) {
            instance = new AuditTrailService();
        }
        return instance;
    }

    public List<AuditTrailNoteDTO> getSortedCaseNotes(ICase caze) {
        return caze.notes().all().stream()
                .filter(item -> !ISecurityConstants.SYSTEM_USER_NAME.equals(item.authorName()))
                .map(AuditTrailNoteDTO::new)
                .collect(Collectors.toList());
    }
}
