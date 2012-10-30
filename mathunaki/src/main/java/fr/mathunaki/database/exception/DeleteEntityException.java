package fr.mathunaki.database.exception;

/**
 * DeleteEntityException is thrown when a delete query in database can not be
 * made because it violates some constraints.
 */
public class DeleteEntityException extends RuntimeException {

	private static final long serialVersionUID = 4882100573721469305L;

	/**
	 * Constructs a new DeleteEntityException with the specified detail message.
	 * 
	 * @param message the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 */
	public DeleteEntityException(String message) {
		super(message);
	}

	/**
	 * Constructs a new DeleteEntityException with the specified detail message
	 * and cause.
	 * 
	 * @param message the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 * @param cause the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A null value is permitted, and
	 *            indicates that the cause is nonexistent or unknown.)
	 */
	public DeleteEntityException(String message, Throwable cause) {
		super(message, cause);
	}

}
