package ch.ivy.gawfs.beans;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivy.addon.portalkit.dto.ExpressAttachment;
import ch.ivy.addon.portalkit.enums.ExpressEmailAttachmentStatus;
import ch.ivy.addon.portalkit.masterdata.MasterData;
import ch.ivy.gawfs.ExpressProcessUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

@ManagedBean
@ViewScoped
public class EmailBean {
  
  private String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private ExpressUserEmail userEmail;
	private StreamedContent file;
	@SuppressWarnings("unused")
	private List<ExpressAttachment> filteredAttachments;

	public void init(ExpressUserEmail email, List<String> responsibles) {
		this.userEmail = email;
		if (userEmail.getAttachments() == null) {
			userEmail.setAttachments(new ArrayList<>());
			setFilteredAttachments(new ArrayList<>());
		}
		ExpressProcessUtils utils = new ExpressProcessUtils();
		if(this.userEmail.getRecipients() == null || this.userEmail.getRecipients().isEmpty()) {
		  List<String> recipients = utils.getRecipientEmailAddresses(responsibles);
		  this.userEmail.setRecipients(String.join(",", recipients));
		}
	}

  public void uploadAttachment(FileUploadEvent event) throws IOException {
    UploadedFile uploadedFile = event.getFile();
    if (uploadedFile == null) {
      return;
    }
    String uploadDocumentCheckMessage = checkFileSize(uploadedFile);
    if (!StringUtils.isEmpty(uploadDocumentCheckMessage)) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, uploadDocumentCheckMessage, null));
      return;
    }
    
    List<ExpressAttachment> attachments = userEmail.getAttachments();
    boolean isDuplicatedName = attachments
        .stream()
        .anyMatch(
            attachment -> (attachment.getStatus() != ExpressEmailAttachmentStatus.DELETED && uploadedFile
                .getFileName().equals(attachment.getName())));
    if (isDuplicatedName) {
      addFileDuplicationMessage(event, uploadedFile.getFileName());
      return;
    }
    uploadAttachmentTemporarily(event);
  }

  private String checkFileSize(UploadedFile uploadedFile) {
    String uploadDocumentCheckMessage = "";
    if(uploadedFile == null) {
      uploadDocumentCheckMessage =  Ivy.cms().co("/Dialogs/components/CaseDocument/invalidFileMessage");
    } else if (uploadedFile.getSize() == 0) {
      uploadDocumentCheckMessage = Ivy.cms().co("/Dialogs/components/CaseDocument/emptyFileMessage");
    } else {
      Long maxFileUploadSize = MasterData.getFileUploadSizeLimit();
      if(uploadedFile.getSize() > maxFileUploadSize) {
        uploadDocumentCheckMessage = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize", Arrays.asList(FileUtils.byteCountToDisplaySize(maxFileUploadSize)));
      }
    }
    
    return uploadDocumentCheckMessage;
  }

	public void downloadAttachment(ExpressAttachment attachment)
			throws IOException {
		if (attachment.getContent() != null) {
			file = new DefaultStreamedContent(new ByteArrayInputStream(
					attachment.getContent()), attachment.getContentType(),
					attachment.getName());
		} else if (attachment.getPath() != null) {
			File attachmentFile = new File(attachment.getPath(), false);
			InputStream is = new FileInputStream(attachmentFile.getJavaFile());
			file = new DefaultStreamedContent(is, attachment.getContentType(),
					attachment.getName());
		}
	}

	public void deleteAttachment(ExpressAttachment attachment) {
		if (attachment.getPath() != null) {
			attachment.setStatus(ExpressEmailAttachmentStatus.DELETED);
		} else {
			userEmail.getAttachments().remove(attachment);
		}
	}

	private void addFileDuplicationMessage(FileUploadEvent event,
			String fileName) {
		String panelId = (String) event.getComponent().getAttributes()
				.get("panelId");
		FacesMessage message = new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				Ivy.cms()
						.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFileExists",
								Arrays.asList(fileName)), null);
		FacesContext.getCurrentInstance().addMessage(panelId, message);
	}

	private void uploadAttachmentTemporarily(FileUploadEvent event)
			throws IOException {
		UploadedFile uploadedFile = event.getFile();
		ExpressAttachment attachmentDTO = new ExpressAttachment();
		attachmentDTO.setContent(uploadedFile.getContents());
		attachmentDTO.setName(uploadedFile.getFileName());
		attachmentDTO.setSize(getFileSize(uploadedFile.getSize()));
		attachmentDTO.setStatus(ExpressEmailAttachmentStatus.ADDED);
		attachmentDTO.setContentType(Files.probeContentType(Paths
				.get(uploadedFile.getFileName())));
		userEmail.getAttachments().add(attachmentDTO);
	}

	private String getFileSize(long size) {
		return size < 1024 ? "1KB" : size / 1024 + "KB";
	}

	public StreamedContent getFile() {
		return file;
	}

	public List<ExpressAttachment> getFilteredAttachments() {
		if(this.userEmail == null || this.userEmail.getAttachments() == null) {
			return new ArrayList<>();
		}
		return this.userEmail.getAttachments().stream().filter(item -> item.getStatus() != ExpressEmailAttachmentStatus.DELETED).collect(Collectors.toList());
	}

	public void setFilteredAttachments(List<ExpressAttachment> filteredAttachments) {
		this.filteredAttachments = filteredAttachments;
	}

  public String getEmailRegex() {
    return emailRegex;
  }

}
