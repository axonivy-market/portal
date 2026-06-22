package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.dto.AuditTrailDTO;

import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.note.Note;

public class AuditTrailService {
    private static AuditTrailService instance;
    private List<AuditTrailDTO> auditTrailDTOs = new ArrayList<>();

    public static AuditTrailService getInstance() {
        if (instance == null) {
            instance = new AuditTrailService();
        }
        return instance;
    }

    
    public List<AuditTrailDTO> getAuditTrailDTOs(ICase caze) {
        if (caze == null) {
            return new ArrayList<>();
        }
        boolean canSeeSystemNotes = true;

        List<Note> notes = caze.notes().all().stream()
                .filter(note -> canSeeSystemNotes || !Strings.CS.equals(note.authorName(), ISecurityConstants.SYSTEM_USER_NAME))
                .sorted((a, b) -> b.createdAt().compareTo(a.createdAt()))
                .collect(Collectors.toList());
        for (Note note : notes) {
            auditTrailDTOs.add(new AuditTrailDTO(
                note.authorName(),
                note.createdAt(),
                note.content(),
                null,
                null
            ));
        }
        return auditTrailDTOs;
    }

}
