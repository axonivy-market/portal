package ch.ivy.ws.addon;

import java.util.List;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.DateTime;

/**
 * Custom OPC Exception
 * 
 */
public class WSException extends Exception {

	private static final long serialVersionUID = -8558165268719107465L;

	private static final int GENERAL_ERROR_CODE = 99999;
	
	private WSErrorType errorType;
	
	private int errorCode;
	private Exception exception;
	private String userText;
	private String helpText;
	private transient List<Object> userTextData;
	private transient List<Object> helpTextData;
	private DateTime errorDateTime = new DateTime();
	private String server;
	
	private boolean textInitialized = false;
	private boolean exceptionAvailable = false;
	
	public WSException() {
		this.errorCode = 0;
		this.errorType = WSErrorType.ERROR;
	}
	
	public WSException(int errorCode) {
		this.errorCode = errorCode;
		this.errorType = WSErrorType.ERROR;
	}
	
	public WSException(Exception exception) {
		this.errorCode = GENERAL_ERROR_CODE;
		this.errorType = WSErrorType.ERROR;
		this.exception = exception;
		if(exception != null) {
			exceptionAvailable = true;
		}
	}
	
	public WSException(int errorCode, Exception exception) {
		this.errorCode = errorCode;
		this.errorType = WSErrorType.ERROR;
		this.exception = exception;
		if(exception != null) {
			exceptionAvailable = true;
		}
		fillInStackTrace();
		generateText();
	}
	/**
	 * constructor setting error type, error code and exception
	 * @param errorType
	 * @param errorCode
	 * @param exception
	 */
	public WSException(WSErrorType errorType, int errorCode, Exception exception) {
		super();
		this.errorType = errorType;
		this.errorCode = errorCode;
		this.exception = exception;
		if(exception != null) {
			exceptionAvailable = true;
		}
		generateText();
	}
	
	/**
	 * constructor setting error type, error code, exception, list of user data and list of help data
	 * @param errorType
	 * @param errorCode
	 * @param exception
	 * @param userTextData
	 * @param helpTextData
	 */
	public WSException(WSErrorType errorType, int errorCode, Exception exception,
			List<Object> userTextData, List<Object> helpTextData) {
		super();
		this.errorType = errorType;
		this.errorCode = errorCode;
		this.exception = exception;
		if(exception != null) {
			exceptionAvailable = true;
		}
		this.userTextData = userTextData;
		this.helpTextData = helpTextData;
		generateText(userTextData, helpTextData);
	}
	
	public WSException(WSErrorType errorType, int errorCode, 
			List<Object> userTextData, List<Object> helpTextData) {
		super();
		this.errorType = errorType;
		this.errorCode = errorCode;
		this.userTextData = userTextData;
		this.helpTextData = helpTextData;
		fillInStackTrace();
		generateText(userTextData, helpTextData);
	}
	
	public WSException(int errorCode, List<Object> userTextData, List<Object> helpTextData) {
		super();
		this.errorType = WSErrorType.ERROR;
		this.errorCode = errorCode;
		if(exception != null) {
			exceptionAvailable = true;
		}
		this.userTextData = userTextData;
		this.helpTextData = helpTextData;
		generateText(userTextData, helpTextData);
	}
	
	public WSException(int errorCode, Exception exception, List<Object> userTextData, List<Object> helpTextData) {
		super();
		this.errorType = WSErrorType.ERROR;
		this.errorCode = errorCode;
		this.exception = exception;
		if(exception != null) {
			exceptionAvailable = true;
		}
		this.userTextData = userTextData;
		this.helpTextData = helpTextData;
		generateText(userTextData, helpTextData);
	}
	
	/**
	 * constructor setting error type and error code
	 * @param errorType
	 * @param errorCode
	 */
	public WSException(WSErrorType errorType, int errorCode) {
		super();
		this.errorType = errorType;
		this.errorCode = errorCode;
	}
	
	/**
	 * generates the user message and help text from the ivy content management.
	 * placeholeders will be replaced by the list values provided by the params
	 * @param userTextData - contains list of object to replace placeholders in the user message
	 * @param helpTextData - contains list of object to replace placeholders in the help text
	 */
	public void generateText(List<Object> userTextData, List<Object> helpTextData) {
		this.userTextData = userTextData;
		this.helpTextData = helpTextData;
		generateText();
	}
	
	
	/**
	 * generates the user message and help text from the ivy content management.
	 * placeholeders will be replaced by the list values initially set on object initialization
	 */ 
	public void generateText() {
		String cmsBaseURI = "/error/" + this.errorCode;
		if(this.userTextData == null || this.userTextData.size() == 0) {
			this.userText = Ivy.cms().co( cmsBaseURI + "/userText");
		} else {
			this.userText = Ivy.cms().co( cmsBaseURI + "/userText", this.userTextData);
		}
		if(this.helpTextData == null || this.helpTextData.size() == 0) {
			this.helpText = Ivy.cms().co( cmsBaseURI + "/helpText");
		} else {
			this.helpText = Ivy.cms().co( cmsBaseURI + "/helpText", this.helpTextData);
		}
		this.textInitialized = true;

	}
	
	@Override
	//remove stack trace
    public synchronized Throwable fillInStackTrace() {
        return null;
    } 
	
	/**
	 * gets the current error type
	 * @return RISErrorType
	 */
	public WSErrorType getErrorType() {
		return errorType;
	}
	/**
	 * gets the current error code
	 * @return int
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * gets the current execption
	 * @return Exception
	 */
	public Exception getException() {
		return exception;
	}
	/**
	 * gets the user message text. The user message needs to be initialized by calling the method generateText()
	 * @return String
	 */
	public String getUserText() {
		return userText;
	}
	/**
	 * gets the help text. The help needs to be initialized by calling the method generateText()
	 * @return String
	 */
	public String getHelpText() {
		return helpText;
	}
	/**
	 * gets the datetime of the error
	 * @return DateTime
	 */
	public DateTime getErrorDateTime() {
		return errorDateTime;
	}
	
	public List<Object> getHelpTextData() {
		return helpTextData;
	}

	public void setHelpTextData(List<Object> helpTextData) {
		this.helpTextData = helpTextData;
	}

	public List<Object> getUserTextData() {
		return userTextData;
	}

	public void setUserTextData(List<Object> userTextData) {
		this.userTextData = userTextData;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorDateTime(DateTime errorDateTime) {
		this.errorDateTime = errorDateTime;
	}

	public void setErrorType(WSErrorType errorType) {
		this.errorType = errorType;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public void setExceptionAvailable(boolean exceptionAvailable) {
		this.exceptionAvailable = exceptionAvailable;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public void setTextInitialized(boolean textInitialized) {
		this.textInitialized = textInitialized;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}
	
	@Override
  public synchronized Throwable getCause(){
		if(this.exception != null && this.exception.getCause() != null){
			return this.exception.getCause();
		}else{
			return this.exception;
		}
	}

	/**
	 * checks if user message and help text are already initialized
	 * @return Boolean
	 */
	public Boolean isTextInitialized() {
		return textInitialized;
	}
	/**
	 * checks if the object was generated with an original 
	 * @return Boolean
	 */
	public Boolean isExceptionAvailable() {
		return exceptionAvailable;
	}

	@Override
	public String toString() {
		if(this.errorCode == 0 && this.exception != null){
			return "Root Exception: " + this.exception.getCause();
		}
		
		return super.toString();
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}	
}
