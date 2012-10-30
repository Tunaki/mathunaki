package fr.mathunaki.database.dao;

import org.springframework.stereotype.Repository;

import fr.mathunaki.database.entity.Tuition;

@Repository("tuitionDAO")
public class TuitionDAO extends AbstractHibernateDAO<Tuition> {

	private static final String DELETE_TUITION = "DELETE FROM Tuition WHERE tuition_id = :tuition_id";

	/**
	 * Construct a TuitionDAO instance.
	 */
	public TuitionDAO() {
		super(Tuition.class);
	}

	/**
	 * Execute the query that delete the given tuition from database.
	 * 
	 * @param tuitionId Id of tuition to delete.
	 */
	public void delete(Long tuitionId) {
		getCurrentSession().createQuery(DELETE_TUITION).setParameter("tuition_id", tuitionId)
				.executeUpdate();
	}

}