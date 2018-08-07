package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;

public class FilterServiceTest {

  @Test
  public void testFilterTasks() {
    FilterService filterService = new FilterService("matched");
    RemoteTask taskMatchedName = new RemoteTask();
    taskMatchedName.setName("name matched");
    RemoteTask taskMatchedDescription = new RemoteTask();
    taskMatchedDescription.setName("description matched");
    RemoteTask taskMatchedVarChar1 = new RemoteTask();
    taskMatchedVarChar1.setName("varChar1 matched");
    RemoteTask taskMatchedVarChar2 = new RemoteTask();
    taskMatchedVarChar2.setName("varChar2 matched");
    RemoteTask taskMatchedVarChar3 = new RemoteTask();
    taskMatchedVarChar3.setName("varChar3 matched");
    RemoteTask taskMatchedVarChar4 = new RemoteTask();
    taskMatchedVarChar4.setName("varChar4 matched");
    RemoteTask taskMatchedVarChar5 = new RemoteTask();
    taskMatchedVarChar5.setName("varChar5 matched");
    RemoteTask taskNotMatched = new RemoteTask();
    taskNotMatched.setName("filter unsuccessfully");

    RemoteTask[] allTasks =
        {taskMatchedName, taskMatchedDescription, taskMatchedVarChar1, taskMatchedVarChar2, taskMatchedVarChar3,
            taskMatchedVarChar4, taskMatchedVarChar5, taskNotMatched};
    RemoteTask[] matchedTasks =
        {taskMatchedName, taskMatchedDescription, taskMatchedVarChar1, taskMatchedVarChar2, taskMatchedVarChar3,
            taskMatchedVarChar4, taskMatchedVarChar5};

    List<RemoteTask> filteredTasks = filterService.filterTasks(Arrays.asList(allTasks));
    Assert.assertEquals(allTasks.length - 1, filteredTasks.size());
    Assert.assertTrue(filteredTasks.containsAll(Arrays.asList(matchedTasks)));
    Assert.assertFalse(filteredTasks.contains(taskNotMatched));
  }


  @Test
  public void testFilterCase() {
    FilterService filterService = new FilterService("matched");
    RemoteCase caseMatchedName = new RemoteCase();
    caseMatchedName.setName("name matched");
    RemoteCase caseMatchedDescription = new RemoteCase();
    caseMatchedDescription.setName("description matched");
    RemoteCase caseMatchedVarChar1 = new RemoteCase();
    caseMatchedVarChar1.setName("varChar1 matched");
    RemoteCase caseMatchedVarChar2 = new RemoteCase();
    caseMatchedVarChar2.setName("varChar2 matched");
    RemoteCase caseMatchedVarChar3 = new RemoteCase();
    caseMatchedVarChar3.setName("varChar3 matched");
    RemoteCase caseMatchedVarChar4 = new RemoteCase();
    caseMatchedVarChar4.setName("varChar4 matched");
    RemoteCase caseMatchedVarChar5 = new RemoteCase();
    caseMatchedVarChar5.setName("varChar5 matched");
    RemoteCase caseNotMatched = new RemoteCase();
    caseNotMatched.setName("filter unsuccessfully");

    RemoteCase[] allCases =
        {caseMatchedName, caseMatchedDescription, caseMatchedVarChar1, caseMatchedVarChar2, caseMatchedVarChar3,
            caseMatchedVarChar4, caseMatchedVarChar5, caseNotMatched};
    RemoteCase[] matchedCases =
        {caseMatchedName, caseMatchedDescription, caseMatchedVarChar1, caseMatchedVarChar2, caseMatchedVarChar3,
            caseMatchedVarChar4, caseMatchedVarChar5};

    List<RemoteCase> filteredCases = filterService.filterCases(Arrays.asList(allCases));
    Assert.assertEquals(allCases.length - 1, filteredCases.size());
    Assert.assertTrue(filteredCases.containsAll(Arrays.asList(matchedCases)));
    Assert.assertFalse(filteredCases.contains(caseNotMatched));
  }
}
