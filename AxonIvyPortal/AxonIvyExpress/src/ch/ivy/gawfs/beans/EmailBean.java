package ch.ivy.gawfs.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ch.ivy.addon.portalkit.dto.AttachmentDTO;
import ch.ivy.gawfs.UserEmail;
import ch.ivy.gawfs.enums.EmailAttachmentStatus;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class EmailBean {
  private UserEmail userEmail;
  
  public void init(UserEmail email){
	  this.userEmail = email;
	  if(userEmail.getAttachments() == null) {
		  userEmail.setAttachments(new ArrayList<>());
	  }
  }
  
  public void uploadAttachment(FileUploadEvent event) {
    UploadedFile uploadedFile = event.getFile();
    List<AttachmentDTO> attachments = userEmail.getAttachments();
    boolean isDuplicatedName =
       attachments.stream().anyMatch(attachment -> uploadedFile.getFileName().equals(attachment.getName()));
    if(isDuplicatedName){
      addFileDuplicationMessage(event);
      return;
    }
    uploadAttachmentTemporarily(event);
  }
  
  private void addFileDuplicationMessage(FileUploadEvent event) {
    String panelId = (String) event.getComponent().getAttributes().get("panelId");
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/Labels/DuplicateDocumentMessage"), null);
    FacesContext.getCurrentInstance().addMessage(panelId, message);
  }
  
  private void uploadAttachmentTemporarily(FileUploadEvent event) {
    UploadedFile uploadedFile = event.getFile();
    AttachmentDTO attachmentDTO = new AttachmentDTO();
    attachmentDTO.setContent(uploadedFile.getContents());
    attachmentDTO.setName(uploadedFile.getFileName());
    attachmentDTO.setSize(getFileSize(uploadedFile.getSize()));
    attachmentDTO.setStatus(EmailAttachmentStatus.ADDED);
    userEmail.getAttachments().add(attachmentDTO);
  }
  
  private String getFileSize(long size){
    return size < 1024 ? "1KB" : size/1024 + "KB";
  }
}
