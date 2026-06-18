package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TabChangeEvent;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class AuditTrailBean implements Serializable {
    private static final long serialVersionUID = -440646080216267529L;
    private List<ITask> previousTasks = new ArrayList<>();
    private Long taskId;
    private ICase currentCase;

    public void init(Long taskId, ICase currentCase) {
        this.taskId = taskId;
        this.currentCase = currentCase;
    }

    public void onTabChange(TabChangeEvent<?> event) {
        if ("Tasks".equals(event.getTab().getTitle())) {
            loadPreviousTasks();
            Ivy.log().info(this.previousTasks.size());
        }
    }

    private void loadPreviousTasks() {
        List<ITask> allTasks = currentCase.tasks().all();
        setPreviousTasks(getTasksBeforeCurrent(allTasks, taskId));
    }

    private List<ITask> getTasksBeforeCurrent(List<ITask> tasks, Long taskId) {
        return tasks.stream()
                .filter(t -> t.getId() < taskId)
                .collect(java.util.stream.Collectors.toList());
    }

    public List<ITask> getPreviousTasks() {
        return previousTasks;
    }

    public void setPreviousTasks(List<ITask> previousTasks) {
        this.previousTasks = previousTasks;
    }
}
