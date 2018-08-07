package portal.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class TaskTemplatePage extends TemplatePage {
  
  @Override
  protected String getLoadedLocator() {
    return "id('document-tab')";
  }
  
  public void openDocumentTab() {
    String documentTabXpath = "//a[@href='#document-tab']";
    waitForElementDisplayed(By.xpath(documentTabXpath), true);
    click(findElementByXpath(documentTabXpath));
  }
  
  public boolean containsFileUploadComponent() {
    WebElement fileUpload = findElementByXpath("//input[@type='file']");
    return fileUpload.isEnabled();
  }
  
  public void openNoteTab() {
    String noteTabXpath = "//a[@href='#note-tab']";
    waitForElementDisplayed(By.xpath(noteTabXpath), true);
    click(findElementByXpath(noteTabXpath));
    waitForElementDisplayed(findElementById("case-notes:add-note-command"), true);
  }
  
  public boolean containsAddNoteButton() {
    WebElement addNote = findElementById("case-notes:add-note-command");
    return addNote.isDisplayed();
  }
  
  public void addNewNote(String content) {
    findElementById("case-notes:add-note-command").click();
    Sleeper.sleepTight(1000);
    WebElement addNoteDialog = findElementById("case-notes:add-new-note-dialog");
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")).click();
    waitAjaxIndicatorDisappear();
  }
  
  public int getNumberOfNotes(){
    return findListElementsByCssSelector("span[id*='note-message']").size();
  }
}
