/**
 * 
 */
package ch.ivy.addon.portalkit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.vo.DocumentFile;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * @author lptchi
 *
 */
public class DocumentFileUtils {
	
	/**
	 * @param caseId root folder 
	 * @param inputStream file to check exist
	 * @param fileName file name to check exist
	 * @return boolean check file is exist or not
	 */
	public static boolean checkFileExist(String caseId, InputStream inputStream, String fileName){
		String originalFileName = FilenameUtils.getName(fileName);
		String home = Ivy.var().get("de_portalkit_upload_folder");
		File newFile = new File(home + "\\" + caseId + "\\" + originalFileName);
		
		return newFile.exists();
	}
	/**
	 * @param filePath file Path to delete
	 * @return boolean delete success or not
	 */
	public static boolean deleteFile(String filePath){
		boolean kq = false;
		File fileDelete = new File(filePath);
		if (fileDelete.exists()){
			kq = fileDelete.delete();
		}
		return kq;
	}
	
	/**
	 * @param caseId root folder by case Id
	 */
	public static void makeDirectory(String caseId){
		
		String home = Ivy.var().get("de_portalkit_upload_folder");
		File caseFolder = new File(home + "\\" + caseId);
		if (!caseFolder.exists()){
			try {
				FileUtils.forceMkdir(caseFolder);
			} catch (IOException e) {
				Ivy.log().debug(e);
			}
		}
	}
	
	/**
	 * @param caseId list all file in the folder with case Id
	 * @return List<DocumentFile> list of {@link DocumentFile}
	 */
	public static List<DocumentFile> listFileInDirectory(String caseId){
		String home = Ivy.var().get("de_portalkit_upload_folder");
		File caseFolder = new File(home + "\\" + caseId);
		List<DocumentFile> listFile = new ArrayList<DocumentFile>();
		if (caseFolder.exists()){
			File[] allFiles = caseFolder.listFiles();
			if (allFiles != null && allFiles.length > 0){
				for (File file : allFiles){
					DocumentFile documentFile = new DocumentFile();
					documentFile.setFileName(file.getName());
					documentFile.setFileSize(file.length() < 1024 ? "1KB" : file.length()/1024 + "KB");
					documentFile.setModifiedDate(new Date(file.lastModified()));
					documentFile.setDownloadPath(file.getAbsolutePath());
					listFile.add(documentFile);
				}
			}
		}else{
			makeDirectory(caseId);
		}
		return listFile;
	}
	
	/**
	 * Upload file and override the old one
	 * @param caseId root folder by case Id
	 * @param inputStream file to upload
	 * @param fileName file name of file upload
	 * @return upload success or not
	 */
	public static boolean uploadToDirectory(String caseId, InputStream inputStream, String fileName){
		String home = Ivy.var().get("de_portalkit_upload_folder");
		String originalFileName = FilenameUtils.getName(fileName);
		File newFile = new File(home + "\\" + caseId + "\\" + originalFileName);
		try {
			FileUtils.copyInputStreamToFile(inputStream, newFile);
			return true;
		} catch (IOException e) {
			
			Ivy.log().debug(e);
			return false;
		}
	}
	
	 /**
	 * @param path file path to download
	 * @param fileName file name to return when download
	 * @return {@link StreamedContent}
	 */
	public static StreamedContent downloadFile(String path, String fileName) {
		 StreamedContent output = null;
		 String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
		 try{
			 output = new DefaultStreamedContent(new FileInputStream(path), contentType, fileName);
		 }catch(IOException e){
			 Ivy.log().debug(e);
		 }
		return output;
	 }
}
