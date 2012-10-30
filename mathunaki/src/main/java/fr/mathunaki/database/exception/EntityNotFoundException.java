package fr.mathunaki.database.exception;

/**
 * EntityNotFoundException is thrown when an entity for a given id is searched
 * in database but none is found.
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3242192494212595467L;

	private Long id;

	/**
	 * Constructs a new EntityNotFoundException with the specified detail
	 * message and id.
	 * 
	 * @param message the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 * @param id Id if the entity that does not exist
	 */
	public EntityNotFoundException(String message, Long id) {
		super(message);
		this.id = id;
	}

	/**
	 * Constructs a new EntityNotFoundException with the specified detail
	 * message and id.
	 * 
	 * @param message the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 * @param id Id if the entity that does not exist
	 * @param cause the cause (which is saved for later retrieval by the
	 *            getCause() method). (A null value is permitted, and indicates
	 *            that the cause is nonexistent or unknown.)
	 */
	public EntityNotFoundException(String message, Long id, Throwable cause) {
		super(message, cause);
		this.id = id;
	}

	/**
	 * @return Get the searched id.
	 */
	public Long getId() {
		return id;
	}

}
