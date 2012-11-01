package fr.mathunaki.database.service.tuition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.mathunaki.database.dao.TuitionDAO;
import fr.mathunaki.database.dao.UserDAO;
import fr.mathunaki.database.entity.Tuition;
import fr.mathunaki.database.entity.User;
import fr.mathunaki.database.exception.DeleteEntityException;
import fr.mathunaki.database.exception.EntityNotFoundException;

@Service("tuitionService")
@Transactional
public class TuitionService {

	@Autowired
	private TuitionDAO tuitionDAO;

	@Autowired
	private UserDAO userDAO;

	/**
	 * Return the tuition of the given id.
	 * 
	 * @param id Id of the tuition.
	 * @return Tuition corresponding to the given id.
	 */
	public Tuition getTuition(Long id) {
		return tuitionDAO.get(id);
	}

	/**
	 * Return the list of all tuitions.
	 * 
	 * @return List of all tuitions.
	 */
	public List<Tuition> getTuitionList() {
		return tuitionDAO.getAll();
	}

	/**
	 * Save the given tuition in database linked with the given user. This
	 * method is to be used when the user does not exist in database.
	 * 
	 * @param tuition Tuition to create.
	 * @param user User linked to this tuition.
	 */
	public void saveTuition(Tuition tuition, User user) {
		userDAO.save(user);
		tuition.setUser(user);
		tuitionDAO.save(tuition);
	}

	/**
	 * Save the given tuition in database linked with the given user id. This
	 * method is to be used when the user already exist in database.
	 * 
	 * @param tuition Tuition to create.
	 * @param userId Id of user to link to the tuition to save.
	 */
	public void saveTuition(Tuition tuition, Long userId) {
		User user = userDAO.get(userId);
		if (user == null) {
			throw new EntityNotFoundException("fr.mathunaki.validation.user.invalid");
		}
		tuition.setUser(user);
		tuitionDAO.save(tuition);
	}

	/**
	 * Update the given tuition in database.
	 * 
	 * @param tuition Tuition to update.
	 */
	public void updateTuition(Tuition tuition) {
		tuitionDAO.save(tuition);
	}

	/**
	 * Delete the given tuition from database.
	 * 
	 * @param tuition Tuition to delete.
	 */
	public void deleteTuition(Tuition tuition) {
		deleteTuition(tuition.getId());
	}

	public void deleteTuition(Long tuitionId) {
		try {
			tuitionDAO.delete(tuitionId);
		} catch (DataIntegrityViolationException e) {
			throw new DeleteEntityException("exception.entity.delete", e);
		}
	}

	/**
	 * Return a new instance of a <code>Tuition</code>.
	 * 
	 * @return New instance of <code>Tuition</code>.
	 */
	public Tuition newTuition() {
		return new Tuition();
	}

}
