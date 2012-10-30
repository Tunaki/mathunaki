package fr.mathunaki.database.service.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.mathunaki.database.dao.ScheduleDAO;
import fr.mathunaki.database.entity.Schedule;

@Service("scheduleService")
@Transactional
public class ScheduleService {

	@Autowired
	private ScheduleDAO scheduleDAO;

	/**
	 * Return the schedule of the given id.
	 * 
	 * @param id Id of the schedule.
	 * @return Schedule corresponding to the given id.
	 */
	public Schedule getSchedule(Long id) {
		return scheduleDAO.get(id);
	}

	/**
	 * Return the list of all schedules.
	 * 
	 * @return List of all schedules.
	 */
	public List<Schedule> getScheduleList() {
		return scheduleDAO.getAll();
	}

	/**
	 * Save the given schedule in database. The tuition bound to the given
	 * schedule must exist in database.
	 * 
	 * @param schedule Schedule to create.
	 */
	public void saveSchedule(Schedule schedule) {
		scheduleDAO.save(schedule);
	}

	/**
	 * Delete the given schedule from database.
	 * 
	 * @param schedule Schedule to delete.
	 */
	public void deleteSchedule(Schedule schedule) {
		scheduleDAO.delete(schedule);
	}

	/**
	 * Return a new instance of a <code>Schedule</code> of 60 minutes.
	 * 
	 * @return New instance of <code>Schedule</code>.
	 */
	public Schedule newSchedule() {
		Schedule schedule = new Schedule();
		schedule.setDuration(60);
		return schedule;
	}

}
