package fr.mathunaki.database.exception;

/**
 * Exception thrown when an uploaded file size is too large and exceeds the
 * upper limit.
 */
public class FileTooLargeException extends RuntimeException {

	private static final long serialVersionUID = -8177883001440220375L;

	/**
	 * Construct a new FileTooLargeException instance with the given message.
	 * 
	 * @param message Exception message.
	 */
	public FileTooLargeException(String message) {
		super(message);
	}

	/**
	 * Construct a new FileTooLargeException instance with the given message and
	 * cause.
	 * 
	 * @param message Exception message.
	 * @param cause Cause of this exception.
	 */
	public FileTooLargeException(String message, Throwable cause) {
		super(message, cause);
	}

}
