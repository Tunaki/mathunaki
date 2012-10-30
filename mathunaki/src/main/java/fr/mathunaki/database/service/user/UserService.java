package fr.mathunaki.database.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.mathunaki.database.dao.UserDAO;
import fr.mathunaki.database.entity.User;
import fr.mathunaki.database.exception.DeleteEntityException;
import fr.mathunaki.database.service.Status;

@Service("userService")
@Transactional
public class UserService {

	@Autowired
	private UserDAO userDAO;

	/**
	 * Return the user of the given id.
	 * 
	 * @param id Id of the user.
	 * @return User corresponding to the given id.
	 */
	public User getUser(Long id) {
		return userDAO.get(id);
	}

	/**
	 * Return the list of all users.
	 * 
	 * @return List of all users.
	 */
	public List<User> getUserList() {
		return userDAO.getAll();
	}

	/**
	 * List all users whose first or last name contains the given name.
	 * 
	 * @param name Name to test.
	 * @return All users whose first or last name contains the given name.
	 */
	public List<User> getUserListWithName(String name) {
		return userDAO.getUsersWithName(name);
	}

	/**
	 * Save the given user in database.
	 * 
	 * @param user User to create.
	 */
	public void saveUser(User user) {
		userDAO.save(user);
	}

	/**
	 * Delete the given user from database.
	 * 
	 * @param user User to delete.
	 */
	public void deleteUser(User user) {
		deleteUser(user.getId());
	}

	/**
	 * Delete the given user from database.
	 * 
	 * @param user User to delete.
	 */
	public void deleteUser(Long userId) {
		try {
			userDAO.delete(userId);
		} catch (DataIntegrityViolationException e) {
			throw new DeleteEntityException("exception.entity.delete", e);
		}
	}

	/**
	 * Disable the given user.
	 * 
	 * @param userId User to disable.
	 */
	public void disableUser(Long userId) {
		userDAO.disable(userId);
	}

	/**
	 * Enable the given user.
	 * 
	 * @param userId User to enable.
	 */
	public void enableUser(Long userId) {
		userDAO.enable(userId);
	}

	/**
	 * Return a new instance of an alive <code>User</code>.
	 * 
	 * @return New instance of <code>User</code>.
	 */
	public User newUser() {
		User user = new User();
		user.setStatus(Status.ENABLED);
		return user;
	}

}
