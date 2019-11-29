package ch.ivyteam.ivy.project.portal.examples.showcase;


import ch.ivyteam.ivy.project.portal.examples.showcase.enums.GdprFileType;
import ch.ivyteam.ivy.scripting.objects.File;

public class GdprFile {
	private Long fileId;
	private Long caseId;
	private	File file;
	private	Boolean isNewAdded;
	private GdprFileType type;
	

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Boolean getIsNewAdded() {
		return isNewAdded;
	}

	public void setIsNewAdded(Boolean isNewAdded) {
		this.isNewAdded = isNewAdded;
	}

	public GdprFileType getType() {
		return type;
	}

	public void setType(GdprFileType type) {
		this.type = type;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
}

