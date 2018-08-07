package ch.ivy.addon.portalkit.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lptchi
 *
 */
public class DocumentFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 205667893049947067L;
	private String downloadPath;
	private String fileName;
	private String fileSize;
	private Date modifiedDate;
	
	/**
	 * @return String fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName file name of file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return String file size
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize String size of file
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return Date modified of file
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	/**
	 * @param modifiedDate Date date of modified
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	/**
	 * @return String download path of file
	 */
	public String getDownloadPath() {
		return downloadPath;
	}
	/**
	 * @param downloadPath String path of file
	 */
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	
}
