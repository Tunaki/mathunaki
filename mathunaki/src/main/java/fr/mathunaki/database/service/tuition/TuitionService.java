package fr.mathunaki.database.service.tuition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.mathunaki.database.dao.TuitionDAO;
import fr.mathunaki.database.dao.UserDAO;
import fr.mathunaki.database.entity.Tuition;
import fr.mathunaki.database.exception.DeleteEntityException;

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
	 * Save the given tuition in database.
	 * 
	 * @param tuition Tuition to create.
	 */
	public void saveTuition(Tuition tuition) {
		userDAO.save(tuition.getUser());
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
