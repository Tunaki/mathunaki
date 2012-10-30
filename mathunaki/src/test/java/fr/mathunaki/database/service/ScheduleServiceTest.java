package fr.mathunaki.database.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fr.mathunaki.database.entity.Schedule;
import fr.mathunaki.database.service.schedule.ScheduleService;
import fr.mathunaki.database.service.tuition.TuitionService;

public class ScheduleServiceTest extends AbstractServiceTest {

	private static final Long TUITION_ID = 1L;
	private static final Date NOW = new Date();

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private TuitionService tuitionService;

	private Long scheduleId;

	@DataProvider
	public Object[][] scheduleList() {
		// @formatter:off
		return new Object[][] {
		{ 1L, 1L, getDate(2012, 9, 15, 11, 0), 60, null },
		{ 2L, 1L, getDate(2012, 9, 18, 13, 0), 60, null },
		{ 3L, 1L, getDate(2012, 9, 26, 14, 0), 60, null },
		{ 4L, 1L, getDate(2012, 9, 29, 11, 0), 60, null },
		{ 5L, 1L, getDate(2012, 10, 2, 11, 0), 60, null },
		{ 6L, 1L, getDate(2012, 10, 5, 11, 0), 60, null },
		{ 7L, 1L, getDate(2012, 10, 9, 14, 0), 60, null },
		{ 8L, 1L, getDate(2012, 10, 17, 11, 0), 60, null },
		{ 9L, 2L, getDate(2012, 10, 4, 16, 0), 120, null },
		{ 10L, 2L, getDate(2012, 10, 11, 14, 0), 120, null },
		{ 11L, 3L, getDate(2012, 10, 16, 17, 0), 60, null },
		{ 12L, 3L, getDate(2012, 10, 23, 17, 0), 90, null },
		{ 13L, 4L, getDate(2012, 10, 8, 18, 0), 60, null },
		{ 14L, 4L, getDate(2012, 10, 15, 18, 0), 60, null },
		{ 15L, 1L, getDate(2012, 10, 24, 11, 0), 60, null },
		{ 16L, 2L, getDate(2012, 10, 18, 14, 0), 120, null} };
		// @formatter:on
	}

	@Test(dataProvider = "scheduleList", groups = "get")
	public void testGetSchedule(Long id, Long tuitionId, Date startDate,
			int duration, String description) {
		Schedule schedule = scheduleService.getSchedule(id);
		assertEquals(schedule.getStartDate(), startDate);
		assertEquals(schedule.getTuition().getId(), tuitionId);
		assertEqualsOrBothNull(schedule.getDescription(), description);
		assertEquals(schedule.getDuration(), duration);
	}

	@Test(groups = "get")
	public void testGetScheduleList() {
		List<Schedule> scheduleList = scheduleService.getScheduleList();
		assertTrue(scheduleList.size() >= scheduleList().length);
	}

	@Test(groups = "get")
	public void testNewSchedule() {
		Schedule schedule = scheduleService.newSchedule();
		assertNull(schedule.getId());
		assertEquals(schedule.getDuration(), 60);
	}

	@Test(groups = "create", dependsOnGroups = "get")
	public void testCreateSchedule() {
		Schedule schedule = scheduleService.newSchedule();
		schedule.setStartDate(NOW);
		schedule.setDescription("Testéé");
		schedule.setTuition(tuitionService.getTuition(TUITION_ID));
		scheduleService.saveSchedule(schedule);
		scheduleId = schedule.getId();
		schedule = scheduleService.getSchedule(scheduleId);
		assertEquals(schedule.getStartDate(), getDateWithoutMilliseconds(NOW));
		assertEquals(schedule.getDescription(), "Testéé");
		assertEquals(schedule.getTuition().getId(), TUITION_ID);
	}

	@Test(groups = "update", dependsOnGroups = "create")
	public void testSaveExistingSchedule() {
		Schedule schedule = scheduleService.getSchedule(scheduleId);
		schedule.setDescription("Nouvelle description");
		scheduleService.saveSchedule(schedule);
		assertEquals(schedule.getId(), scheduleId);
		schedule = scheduleService.getSchedule(scheduleId);
		assertEquals(schedule.getDescription(), "Nouvelle description");
	}

	@Test(groups = "delete", dependsOnGroups = "update")
	public void testDeleteSchedule() {
		scheduleService.deleteSchedule(scheduleService.getSchedule(scheduleId));
		List<Schedule> scheduleList = scheduleService.getScheduleList();
		for (Schedule schedule : scheduleList) {
			assertNotSame(schedule.getDescription(), "Nouvelle description");
		}
	}

}
