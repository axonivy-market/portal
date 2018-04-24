/**
 * 
 */
package ch.ivy.addon.portalkit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.vo.DocumentFile;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.document.IDocumentService;

public class DocumentFileUtils {
	private DocumentFileUtils(){}
  
	/**
	 * @param currentCase root folder 
	 * @param fileName file name to check exist
	 * @return boolean check file is exist or not
	 */
	public static boolean checkFileExist(ICase currentCase, String fileName){
		String originalFileName = FilenameUtils.getName(fileName);
		IDocumentService documentService = currentCase.documents();
		List<IDocument> documents = documentService.getAll();
		documents = documents.stream().filter(document -> document.getPath().asString().contains(CaseDocumentService.EXPRESS_UPLOAD_FOLDER) && document.getPath().asString().contains(originalFileName)).collect(Collectors.toList());
		return !documents.isEmpty();
	}
	/**
	 * @param filePath file Path to delete
	 * @param currentCase current case
	 * @return boolean delete success or not
	 */
	public static boolean deleteFile(String filePath, ICase currentCase){
		boolean kq = false;
		IDocumentService documentService = currentCase.documents();
		List<IDocument> documents = documentService.getAll().stream().filter(document -> document.getPath().asString().contains(filePath)).collect(Collectors.toList());
		if (documents != null && !documents.isEmpty()) {
			documentService.delete(documents.get(0));
			kq = true;
		}
		return kq;
	}
	
	/**
	 * @param currentCase list all file in the folder with current case
	 * @return List<DocumentFile> list of {@link DocumentFile}
	 */
	public static List<DocumentFile> listFileInDirectory(ICase currentCase){
		IDocumentService documentService = currentCase.documents();
		List<IDocument> documents = documentService.getAll();
		documents = documents.stream().filter(document -> document.getPath().asString().contains(CaseDocumentService.EXPRESS_UPLOAD_FOLDER)).collect(Collectors.toList());
		List<DocumentFile> listFile = new ArrayList<>();
		if (documents != null){
			for (IDocument document : documents){
				DocumentFile documentFile = new DocumentFile();
				documentFile.setFileName(document.getName());
				documentFile.setFileSize(document.getSize() < 1024 ? "1KB" : document.getSize()/1024 + "KB");
				documentFile.setModifiedDate(document.getLastModification().getTimestamp().toJavaDate());
				documentFile.setDownloadPath(document.getPath().asString());
				listFile.add(documentFile);
			}
		}
		return listFile;
	}
	
	/**
	 * Upload file and override the old one
	 * @param currentCase root folder by current case
	 * @param inputStream file to upload
	 * @param fileName file name of file upload
	 * @return upload success or not
	 */
	public static boolean uploadToDirectory(ICase currentCase, InputStream inputStream, String fileName){
		IDocumentService documentService = currentCase.documents();
		String originalFileName = FilenameUtils.getName(fileName);
		try {
			documentService.add(CaseDocumentService.EXPRESS_UPLOAD_FOLDER + "/" + originalFileName).write().withContentFrom(inputStream);
			return true;
		} catch (Exception e) {
		  Ivy.log().error(e);
			return false;
		}
	}
	
	 /**
	 * @param path file path to download
	 * @param fileName file name to return when download
	 * @param currentCase current case
	 * @return {@link StreamedContent}
	 */
	public static StreamedContent downloadFile(String path, String fileName, ICase currentCase) {
		 StreamedContent output = null;
		 String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
		 IDocumentService documentService = currentCase.documents();
		List<IDocument> documents = documentService.getAll().stream().filter(document -> document.getPath().asString().contains(path)).collect(Collectors.toList());
		if (documents != null && !documents.isEmpty()) {
			File fileInputStream = documents.get(0).read().asJavaFile();
			try{
				 output = new DefaultStreamedContent(new FileInputStream(fileInputStream), contentType, fileName);
			 }catch(IOException e){
				 Ivy.log().error(e);
			 }
		}
		 
		return output;
	 }
}
