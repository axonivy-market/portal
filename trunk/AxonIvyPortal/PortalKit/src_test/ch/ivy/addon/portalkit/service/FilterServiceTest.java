package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ch.ivy.addon.portalkit.bo.RemoteCase;

public class FilterServiceTest {

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
