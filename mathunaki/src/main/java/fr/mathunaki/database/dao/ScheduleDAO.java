package fr.mathunaki.database.dao;

import org.springframework.stereotype.Repository;

import fr.mathunaki.database.entity.Schedule;

@Repository("scheduleDAO")
public class ScheduleDAO extends AbstractHibernateDAO<Schedule> {

	/**
	 * Construct a ScheduleDAO instance.
	 */
	public ScheduleDAO() {
		super(Schedule.class);
	}

}
