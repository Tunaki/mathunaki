package fr.mathunaki.database.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fr.mathunaki.database.entity.Tuition;
import fr.mathunaki.database.entity.User;
import fr.mathunaki.database.exception.DeleteEntityException;
import fr.mathunaki.database.service.tuition.TuitionService;
import fr.mathunaki.database.service.user.UserLevel;
import fr.mathunaki.database.service.user.UserService;

public class TuitionServiceTest extends AbstractServiceTest {

	private static final Long USER_ID = 2L;

	@Autowired
	private TuitionService tuitionService;

	@Autowired
	private UserService userService;

	private Long tuitionId;
	private Long tuitionIdWithUser;
	private Long newUserId;

	@DataProvider
	public Object[][] tuitionList() {
		// @formatter:off
		return new Object[][] {
		{ 1L, 1L, UserLevel.MPSI, "Cours de Maple.", null  },
		{ 2L, 2L, UserLevel.ECOLE_INGENIEUR, "Cours d'automatique.", null },
		{ 3L, 3L, UserLevel.MPSI, "Cours de Maths. Élève au lycée Faidherbe en MPSI.", null },
		{ 4L, 4L, UserLevel.PREPA_INTEGREE, "Prépa intégrée à l'ENSCL. Cours de physique.", null } };
		// @formatter:on
	}

	@Test(dataProvider = "tuitionList", groups = "get")
	public void testGetTuition(Long id, Long userId, UserLevel userLevel, String description,
			File resource) {
		Tuition tuition = tuitionService.getTuition(id);
		assertEquals(tuition.getUser().getId(), userId);
		assertTrue(tuition.getUserLevel() == userLevel);
		assertEqualsOrBothNull(tuition.getDescription(), description);
		assertEqualsOrBothNull(tuition.getResource(), resource);
	}

	@Test(groups = "get")
	public void testGetAllTuition() {
		List<Tuition> tuitionList = tuitionService.getTuitionList();
		assertTrue(tuitionList.size() == tuitionList().length);
	}

	@Test(groups = "get")
	public void testNewTuition() {
		Tuition tuition = tuitionService.newTuition();
		assertNull(tuition.getId());
	}

	@Test(groups = "save", dependsOnGroups = "get")
	public void testSaveTuitionWithExistingUser() {
		Tuition tuition = tuitionService.newTuition();
		tuition.setUserLevel(UserLevel.PREMIERE_ES);
		tuition.setDescription("C'était un bon élève...");
		tuitionService.saveTuition(tuition, USER_ID);
		tuitionId = tuition.getId();
		tuition = tuitionService.getTuition(tuitionId);
		assertEquals(tuition.getUser().getId(), USER_ID);
		assertEquals(tuition.getUserLevel(), UserLevel.PREMIERE_ES);
		assertEquals(tuition.getDescription(), "C'était un bon élève...");
	}

	@Test(groups = "save", dependsOnGroups = "get")
	public void testSaveTuitionWithNonExistingUser() {
		Tuition tuition = tuitionService.newTuition();
		User user = userService.newUser();
		user.setFirstName("Test tuition");
		user.setLastName("Tuition test");
		user.setAddress("Là");
		user.setPrice(1.00);
		tuition.setUserLevel(UserLevel.PCSI);
		tuition.setDescription("C'était un bon élève...");
		tuitionService.saveTuition(tuition, user);
		tuitionIdWithUser = tuition.getId();
		newUserId = tuition.getUser().getId();
		tuition = tuitionService.getTuition(tuitionIdWithUser);
		assertEquals(tuition.getUser().getId(), newUserId);
		assertEquals(tuition.getUserLevel(), UserLevel.PCSI);
		assertEquals(tuition.getDescription(), "C'était un bon élève...");
		// test that new user has correctly been added
		user = userService.getUser(newUserId);
		assertEquals(user.getAddress(), "Là");
		assertEquals(user.getFirstName(), "Test tuition");
		assertEquals(user.getLastName(), "Tuition test");
		assertEquals(user.getPrice(), 1.);
	}

	@Test(groups = "update", dependsOnGroups = "save")
	public void testUpdateTuition() {
		Tuition tuition = tuitionService.getTuition(tuitionId);
		tuition.setUserLevel(UserLevel.CINQUIEME);
		tuitionService.updateTuition(tuition);
		tuition = tuitionService.getTuition(tuitionId);
		assertTrue(tuition.getUserLevel() == UserLevel.CINQUIEME);
	}

	@Test(groups = "delete", dependsOnGroups = "update")
	public void testDeleteTuition() {
		tuitionService.deleteTuition(tuitionService.getTuition(tuitionId));
		List<Tuition> tuitionList = tuitionService.getTuitionList();
		for (Tuition tuition : tuitionList) {
			assertNotSame(tuition.getDescription(), "C'était un bon élève...");
		}
	}

	@Test(groups = "delete", dependsOnGroups = "update", expectedExceptions = DeleteEntityException.class)
	public void testDeleteTuitionException() {
		// Tuition 1L is linked to an user -> expected exception.
		tuitionService.deleteTuition(tuitionService.getTuition(1L));
	}

	@Test(dependsOnGroups = "delete")
	public void cleanup() {
		tuitionService.deleteTuition(tuitionService.getTuition(tuitionIdWithUser));
		userService.deleteUser(userService.getUser(newUserId));
	}

}
