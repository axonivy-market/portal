package ch.ivy.addon.portalkit.test.util;

public class DataCreationHandler {
  private int numOfCats;
  private int numOfSubCats;
  private int numOfCasesPerSubCat;// each case create several tasks (e.g. 10 tasks)
  private int catCounter;
  private int subCatCounter;
  private int caseCounter;

  public DataCreationHandler(int numOfCats, int numOfSubCats, int numOfCasesPerSubCat) {
    super();
    this.numOfCats = numOfCats;
    this.numOfSubCats = numOfSubCats;
    this.numOfCasesPerSubCat = numOfCasesPerSubCat;
    catCounter = 1;
    subCatCounter = 1;
    caseCounter = 1;
  }

  public String getNextTaskStructureInfo() {
    String taskStructureInfo = "Leave Request " + catCounter + "/Holiday Leave " + subCatCounter;
    updateCounter();
    return taskStructureInfo;
  }

  private void updateCounter() {
    caseCounter++;
    if (caseCounter > numOfCasesPerSubCat) {
      caseCounter = 1;
      subCatCounter++;
      if (subCatCounter > numOfSubCats) {
        subCatCounter = 1;
        catCounter++;
      }
    }
  }

  public boolean createMoreTasks() {
    return numOfCats >= catCounter;
  }
}
