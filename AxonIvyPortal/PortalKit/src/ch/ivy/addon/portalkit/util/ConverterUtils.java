package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.Contact;
import ch.ivy.addon.portalkit.vo.CaseVO;
import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Utilities to convert some kinds of object.
 */
public class ConverterUtils {
	private static String fullNameFormat = "%s (%s)";

	private ConverterUtils() {}

	
	/**
	 * Convert {@link ICase} to {@link CaseVO}
	 *
	 * @param iCase {@link ICase} to convert
	 * @return case view object
	 */
	public static CaseVO convertICaseToCaseVO(ICase iCase){	
		CaseVO caseVO = new CaseVO();
		caseVO.setCreatedAt(iCase.getStartTimestamp());
		caseVO.setId(iCase.getId());
		caseVO.setDescription(iCase.getDescription());
		caseVO.setTitle(iCase.getName());
		caseVO.setStatus(iCase.getState().name());
		if (iCase.getCreatorUser() != null && iCase.getCreatorUser().getFullName() != null){
			caseVO.setCreator(String.format(fullNameFormat,iCase.getCreatorUser().getFullName(), iCase.getCreatorUser().getName()));
		}else{
			caseVO.setCreator(iCase.getCreatorUserName());
		}
	
		return caseVO;
	}
	
	/**
	 * Convert to List<{@link ICase}> to List<{@link CaseVO}> 
	 *
	 * @param iCases list of {@link ICase} to convert
	 * @return List<{@link CaseVO}>
	 */
	public static List<CaseVO> convertICasesToCaseVOs(List<ICase> iCases){	
		List<CaseVO> caseVOs = new ArrayList<>();
		for(ICase iCase:iCases){
			if(iCase!=null){
				caseVOs.add(convertICaseToCaseVO(iCase));
			}
		}
		return caseVOs;
	}
	
	/**
	 * Convert from {@link ITask} to {@link TaskVO}
	 *
	 * @param iTask to convert
	 * @return taskVO after convert
	 */
	public static TaskVO convertITaskToTaskVO(ITask iTask){
		TaskVO taskVO = new TaskVO();
		
		taskVO.setId(iTask.getId());
		taskVO.setDeadline(iTask.getExpiryTimestamp());
		taskVO.setEditor(iTask.getActivator());
		
		if(iTask.getActivator()!=null){
			String editorNameInformat =  CaseUtils.getDisplayNameInFormat(iTask.getActivator().getDisplayName(),iTask.getActivatorName());
			taskVO.setEditorName(editorNameInformat);
			Contact contact = new Contact(TaskUtils.getEmailAddress(iTask));
			String phone = TaskUtils.getPhone(iTask);
			if (phone != null){
				contact.setPhone(phone);
			}
			String mobilePhone = TaskUtils.getMobile(iTask);
			if (phone != null){
				contact.setMobilePhone(mobilePhone);
			}
			taskVO.setContact(contact);
		}
					
		taskVO.setName(iTask.getName());
		taskVO.setState(iTask.getState());
		taskVO.setPriority(iTask.getPriority());
		taskVO.setLastEdit(iTask.getEndTimestamp());		
		taskVO.setType(TaskVO.TYPE_TASK);
		
		
		taskVO.setEdited(iTask.getStartTimestamp());
		taskVO.setDescription(iTask.getDescription());
		taskVO.setCustomDecimalField1(iTask.getCustomDecimalField1());
		taskVO.setDelayTimestamp(iTask.getDelayTimestamp());
		
		return taskVO;
	}
	
	/**
	 * Convert List<{@link ITask}> to List<{@link TaskVO}>
	 *
	 * @param iTasks list of {@link ITask} to convert
	 * @return List<{@link TaskVO}>
	 */
	public static List<TaskVO> convertITasksToTaskVOs(List<ITask> iTasks){	
		List<TaskVO> taskVOs = new ArrayList<>();
		for(ITask iTask:iTasks){
			if(iTask!=null){
				taskVOs.add(convertITaskToTaskVO(iTask));
			}
		}
		return taskVOs;
	}

	/**
	 * Convert {@link INote} to {@link TaskVO}
	 * 
	 * @param iNote - a {@link INote}
	 * @return {@link TaskVO}
	 */
	public static TaskVO convertINoteToTaskVO(INote iNote){
		TaskVO taskVO = new TaskVO();
		
		taskVO.setId(iNote.getId());
		taskVO.setEditor(iNote.getWritter());
		
		if(iNote.getWritter()!=null){
			String editorName = CaseUtils.getDisplayNameInFormat(iNote.getWritter().getDisplayName(), iNote.getWritterName());
			taskVO.setEditorName(editorName);
			
			Contact contact = new Contact();
			String email = TaskUtils.getEmailAddress(iNote.getWritter());
			contact.setEmail(email);
			String phone = TaskUtils.getMobile(iNote.getWritter());
			if (phone != null){
				contact.setPhone(phone);
			}
			String mobilePhone = TaskUtils.getMobile(iNote.getWritter());
			if (phone != null){
				contact.setMobilePhone(mobilePhone);
			}
			taskVO.setContact(contact);
		}
		
		taskVO.setType(TaskVO.TYPE_NOTE);
		
		taskVO.setEdited(iNote.getCreationTimestamp());
		taskVO.setDescription(iNote.getMessage());
		return taskVO;
	}
	
	/**
	 * Convert list of {@link INote} to list of {@link TaskVO}
	 * 
	 * @param iNotes list of {@link INote}
	 * @return {@link TaskVO}
	 */
	public static List<TaskVO> convertINotesToTaskVOs(List<INote> iNotes){	
		List<TaskVO> taskVOs = new ArrayList<>();
		for(INote iNote : iNotes){
			if(iNote!=null){
				taskVOs.add(convertINoteToTaskVO(iNote));
			}
		}
		return taskVOs;
	}
}
