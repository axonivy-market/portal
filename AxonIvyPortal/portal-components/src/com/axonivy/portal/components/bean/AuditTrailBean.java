package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.dto.AuditTrailDTO;
import com.axonivy.portal.components.predicate.CustomProcessPredicate;
import com.axonivy.portal.components.service.IvyAdapterService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.note.Note;

@ManagedBean(name = "auditTrailBean")
@ViewScoped
public class AuditTrailBean implements Serializable {
    private static final long serialVersionUID = -440646080216267529L;
    private Long taskId;
    private ICase currentCase;
    private List<ITask> previousTasks = new ArrayList<>();
    private List<AuditTrailDTO> auditTrailDTOs = new ArrayList<>();
    private List<AuditTrailDTO> additionalContentFromPreviousTasks = new ArrayList<>();
    private String noteText;
    private String additionalEntry;
    private String additionalInformationAuditTrail;

    public void init(Long taskId, ICase currentCase, String additionalEntry) {
        this.taskId = taskId;
        this.currentCase = currentCase;
        this.additionalEntry = additionalEntry;
        initAuditTrailDTOs();
        loadPreviousTasks();
    }

    private void initAuditTrailDTOs() {
        auditTrailDTOs = new ArrayList<>();
        if (currentCase == null) {
            return;
        }
        boolean canSeeSystemNotes = true;
        List<Note> notes = currentCase.notes().all().stream()
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

    public String getAdditionalEntry() {
        return additionalEntry;
    }

    public String getAdditionalInformationAuditTrail() {
        return additionalInformationAuditTrail;
    }

    public void setAdditionalInformationAuditTrail(String additionalInformationAuditTrail) {
        this.additionalInformationAuditTrail = additionalInformationAuditTrail;
    }

    public void addAdditionalInformation() {
        ITask currentTask = findCurrentTask();
        Ivy.log().info("note" + this.additionalInformationAuditTrail);
        if (currentTask == null || additionalInformationAuditTrail == null || additionalInformationAuditTrail.trim().isEmpty()) {
            return;
        }
        currentTask.notes().add().content(this.additionalInformationAuditTrail).execute();
        additionalInformationAuditTrail = null;
    }

    private void loadPreviousTasks() {
        ITask currentTask = findCurrentTask();
        List<ITask> tasks = new ArrayList<>();
        collectPreviousTasks(currentTask, tasks, new HashSet<>());
        tasks.sort(Comparator.comparing(ITask::getStartTimestamp,
                Comparator.nullsLast(Comparator.naturalOrder())));
        setPreviousTasks(tasks);
    }

    public List<AuditTrailDTO> getTaskNotes(ITask task) {
        if (task == null) {
            return new ArrayList<>();
        }
        return task.notes().all().stream()
                .sorted((a, b) -> b.createdAt().compareTo(a.createdAt()))
                .map(note -> new AuditTrailDTO(note.authorName(), note.createdAt(), note.content(), null, null))
                .collect(Collectors.toList());
    }

    private ITask findCurrentTask() {
        if (currentCase == null || taskId == null) {
            return null;
        }
        return currentCase.tasks().all().stream()
                .filter(t -> Objects.equals(t.getId(), taskId))
                .findFirst()
                .orElse(null);
    }

    private void collectPreviousTasks(ITask task, List<ITask> result, Set<Long> visited) {
        if (task == null || task.getStartSwitchEvent() == null) {
            return;
        }
        for (ITask previous : task.getStartSwitchEvent().getEndedTasks()) {
            if (previous != null && visited.add(previous.getId()) && !isSystemTask(previous) && !previous.responsibles().all().isEmpty()) {
                result.add(previous);
                collectPreviousTasks(previous, result, visited);
            }
        }
    }

    public ITask getPreviousTask() {
        return previousTasks.getLast();
    }

    public List<AuditTrailDTO> getNotesFromPreviousTask() {
        if (previousTasks.isEmpty()) {
            return new ArrayList<>();
        }
        Ivy.log().info(previousTasks.getLast().notes().all());
        return getTaskNotes(previousTasks.getLast());
    }

    public void collectAdditionalInformation(String caseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("businessCaseId", caseId);
        Map<String, Object> result = IvyAdapterService.startSubProcessesInSecurityContext(
                "getAdditionalInformationInAuditTrail", params, new CustomProcessPredicate());
        if (result == null || result.isEmpty()) {
            return;
        }
        Long resultTaskId = (Long) result.get("taskId");
        String content = (String) result.get("content");
        if (resultTaskId == null || currentCase == null) {
            return;
        }
        ITask task = currentCase.tasks().all().stream()
                .filter(t -> resultTaskId.equals(t.getId()))
                .findFirst()
                .orElse(null);
        if (task == null) {
            return;
        }
        additionalContentFromPreviousTasks.add(new AuditTrailDTO(
                task.getWorkerUser() != null ? task.getWorkerUser().getMemberName() : null,
                task.getEndTimestamp(),
                content,
                task.getName(),
                task.getState().toString()
        ));
    }

    public List<AuditTrailDTO> getAdditionalContentFromPreviousTasks() {
        return additionalContentFromPreviousTasks;
    }

    private boolean isSystemTask(ITask task) {
        ISecurityMember systemUser = Ivy.wf().getSecurityContext().getSystemUser();
        return systemUser.getSecurityMemberId().equals(task.getWorkerUser().getSecurityMemberId());
    }

    public List<ITask> getPreviousTasks() {
        return previousTasks.stream().sorted((a, b) -> b.getEndTimestamp().compareTo(a.getEndTimestamp())).collect(Collectors.toList());
    }

    public void setPreviousTasks(List<ITask> previousTasks) {
        this.previousTasks = previousTasks;
    }
}
