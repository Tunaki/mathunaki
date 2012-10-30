package fr.mathunaki.database.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.mathunaki.database.entity.EntityInterface;
import fr.mathunaki.database.exception.EntityNotFoundException;

@Transactional(propagation = Propagation.MANDATORY)
public abstract class AbstractHibernateDAO<T extends EntityInterface> {

	@Autowired
	private SessionFactory sessionFactory;

	private final Class<T> clazz;

	/**
	 * Construct the DAO by giving the appropriate entity class that this DAO is
	 * managing.
	 * 
	 * @param clazz Entity class.
	 */
	protected AbstractHibernateDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Get entity with its id.
	 * 
	 * @param id Entity's id.
	 * @return Entity corresponding to this id.
	 */
	@SuppressWarnings("unchecked")
	public T get(Long id) {
		checkNotNull(id);
		T entity = (T) getCurrentSession().get(clazz, id);
		if (entity == null) {
			throw new EntityNotFoundException("exception.entity.notFound", id);
		}
		return entity;
	}

	/**
	 * Get list of all entities managed by this DAO.
	 * 
	 * @return List of all entities managed by this DAO.
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	/**
	 * Save or update the given entity.
	 * 
	 * @param entity Entity to save or update.
	 */
	public void save(T entity) {
		checkNotNull(entity);
		if (entity.getId() == null) {
			getCurrentSession().save(entity);
		} else {
			getCurrentSession().update(entity);
		}
	}

	/**
	 * Delete the given entity.
	 * 
	 * @param entity Entity to delete.
	 */
	public void delete(T entity) {
		checkNotNull(entity);
		getCurrentSession().delete(entity);
	}

	/**
	 * Get the current session.
	 * 
	 * @return Current session.
	 */
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	private void checkNotNull(Object argument) {
		if (argument == null) {
			throw new NullPointerException();
		}
	}

}