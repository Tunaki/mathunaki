package fr.mathunaki.database.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.mathunaki.database.entity.User;

@Repository("userDAO")
public class UserDAO extends AbstractHibernateDAO<User> {

	private static final String DELETE_USER = "DELETE FROM User WHERE user_id = :user_id";
	private static final String UPDATE_ALIVE = "UPDATE User SET alive = :alive WHERE user_id = :user_id";
	private static final String GET_USER_NAME = "SELECT user FROM User AS user WHERE user.firstName LIKE '%' || :name || '%' OR user.lastName LIKE '%' || :name || '%'";

	/**
	 * Construct a UserDAO instance.
	 */
	public UserDAO() {
		super(User.class);
	}

	/**
	 * Execute the query that disables the given user by setting its alive
	 * attribute to <code>false</code>.
	 * 
	 * @param userId Id of the user to disable.
	 */
	public void disable(Long userId) {
		updateAlive(userId, false);
	}

	/**
	 * Execute the query that enables the given user by setting its alive
	 * attribute to <code>true</code>.
	 * 
	 * @param userId Id of the user to enable.
	 */
	public void enable(Long userId) {
		updateAlive(userId, true);
	}

	/**
	 * Execute the query that delete the given user from database.
	 * 
	 * @param userId Id of user to delete.
	 */
	public void delete(Long userId) {
		getCurrentSession().createQuery(DELETE_USER).setParameter("user_id", userId)
				.executeUpdate();
	}

	/**
	 * Execute the query that return all users whose first name or last name
	 * contains the given name.
	 * 
	 * @param name Name to test.
	 * @return Users whose first or last name contain the given name.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsersWithName(String name) {
		return getCurrentSession().createQuery(GET_USER_NAME).setParameter("name", name).list();
	}

	private void updateAlive(Long userId, boolean alive) {
		getCurrentSession().createQuery(UPDATE_ALIVE).setParameter("alive", alive)
				.setParameter("user_id", userId).executeUpdate();
	}

}