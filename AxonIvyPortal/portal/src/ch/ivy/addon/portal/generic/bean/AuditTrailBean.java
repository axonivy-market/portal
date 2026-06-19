package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.dto.AuditTrailDTO;
import com.axonivy.portal.components.predicate.CustomProcessPredicate;
import com.axonivy.portal.components.service.IvyAdapterService;

import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.note.Note;

@ManagedBean
@ViewScoped
public class AuditTrailBean implements Serializable {
    private static final long serialVersionUID = -440646080216267529L;
    private Long taskId;
    private ICase currentCase;
    private List<ITask> previousTasks = new ArrayList<>();
    private List<AuditTrailDTO> auditTrailDTOs = new ArrayList<>();
    private String noteText;

    public void init(Long taskId, ICase currentCase) {
        this.taskId = taskId;
        this.currentCase = currentCase;
        initAuditTrailDTOs();
    }

    private void initAuditTrailDTOs() {
        auditTrailDTOs = new ArrayList<>();
        if (currentCase == null) {
            return;
        }
        boolean canSeeSystemNotes = PermissionUtils.checkNoteReadAllCaseTaskDetailsPermission();
        List<Note> notes = currentCase.notes().all().stream()
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
    }

    public List<AuditTrailDTO> getAuditTrailDTOs() {
        // AuditTrailDTO additionInfor = getAdditionalNotesFromTask();
        return auditTrailDTOs;
    }

    public void addNote() {
        if (currentCase == null || noteText == null || noteText.trim().isEmpty()) {
            return;
        }
        currentCase.notes().add().content(noteText.trim()).execute();
        noteText = null;
        initAuditTrailDTOs();
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    private AuditTrailDTO getAdditionalNotesFromTask() {
        if (taskId == null) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        Map<String, Object> subResult = IvyAdapterService.startSubProcessesInSecurityContext(
                "auditTrailAdditionalNotes(Long)", params, new CustomProcessPredicate());
        if (subResult != null && !subResult.isEmpty()) {
            Object notesObj = subResult.get("notes");
            String notes = notesObj != null ? notesObj.toString() : null;
            if (notes != null && !notes.isEmpty()) {
                return new AuditTrailDTO(null, null, notes, null, null);
            }
        }
        return null;
    }

    private void loadPreviousTasks() {
        List<ITask> allTasks = currentCase.tasks().all();
        setPreviousTasks(getTasksBeforeCurrent(allTasks, taskId));
    }

    private List<ITask> getTasksBeforeCurrent(List<ITask> tasks, Long taskId) {
        return tasks.stream()
                .filter(t -> t.getId() < taskId)
                .collect(Collectors.toList());
    }

    public List<ITask> getPreviousTasks() {
        return previousTasks;
    }

    public void setPreviousTasks(List<ITask> previousTasks) {
        this.previousTasks = previousTasks;
    }
}
