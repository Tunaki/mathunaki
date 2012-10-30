package fr.mathunaki.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fr.mathunaki.database.entity.User;
import fr.mathunaki.database.exception.DeleteEntityException;
import fr.mathunaki.database.service.user.UserService;

public class UserServiceTest extends AbstractServiceTest {

	@Autowired
	private UserService userService;

	private Long userId;

	@DataProvider
	public Object[][] userList() {
		// @formatter:off
		return new Object[][] {
		{ 1L, "Bernard", "Laporte", "22 Avenue du Colysée 59130 Lambersart",
			"trigalliere@gmail.com", "0681134594", null, "0320939873",
			"Cours de Maple. Passe par le CNED.", State.ENABLED, 25.00 },
		{ 2L, "Alexia", "Potdevin", "145 rue Gambetta, 3e étage, Appartement du fond",
			"alexia18@numericable.fr", "0607651918", "0672461872", "0320470578",
			"Cours Automatique. École d'ingénieur Polytech'Lille, département Génie Biologique Agroalimentaire.",
			State.ENABLED, 20.00 },
		{ 3L, "Thomas", "Nicolazic Zimmermann", "15 rue du Boulevard 59170 Croix",
			"nicolazic.jeannoel@wanadoo.fr", null, null, "0687584968",
			"Bon niveau.", State.ENABLED, 25.00 },
		{ 4L, "Valérie", "Alvares", "5 rue de la Grande Brasserie, Résidence Jardin des Sciences, Appartement 204",
			"vf.alva@orange.fr", "0683764126", "0688901753", "0466509839",
			"Prépa intégrée à l'ENSCL. Cours de physique.", State.ENABLED, 25.00 } };
		// @formatter:on
	}

	@Test(dataProvider = "userList", groups = "get")
	public void testGetUser(Long id, String firstName, String lastName, String address,
			String email, String phoneNumber, String phoneNumberParent, String phoneNumber2,
			String information, State state, double price) {
		User user = userService.getUser(id);
		assertEquals(user.getFirstName(), firstName);
		assertEquals(user.getLastName(), lastName);
		assertEquals(user.getAddress(), address);
		assertEqualsOrBothNull(user.getEmail(), email);
		assertEqualsOrBothNull(user.getPhoneNumber(), phoneNumber);
		assertEqualsOrBothNull(user.getPhoneNumber2(), phoneNumber2);
		assertEqualsOrBothNull(user.getPhoneNumberParent(), phoneNumberParent);
		assertEqualsOrBothNull(user.getInformation(), information);
		assertEquals(user.getState(), state);
		assertEquals(user.getPrice(), price);
	}

	@Test(groups = "get")
	public void testGetUserList() {
		List<User> userList = userService.getUserList();
		assertTrue(userList.size() == userList().length);
	}

	@Test(groups = "get")
	public void testNewUser() {
		User user = userService.newUser();
		assertNull(user.getId());
		assertEquals(user.getState(), State.ENABLED);
	}

	@Test(groups = "create", dependsOnGroups = "get")
	public void testCreateUser() {
		User user = userService.newUser();
		user.setFirstName("Guillaume");
		user.setLastName("Boué");
		user.setAddress("15 rue Eugène-Ténot 33800 Bordeaux");
		user.setPrice(20.00);
		userService.saveUser(user);
		userId = user.getId();
		user = userService.getUser(userId);
		assertEquals(user.getFirstName(), "Guillaume");
		assertEquals(user.getLastName(), "Boué");
		assertEquals(user.getAddress(), "15 rue Eugène-Ténot 33800 Bordeaux");
		assertEquals(user.getPrice(), 20.00);
		assertEquals(user.getState(), State.ENABLED);
	}

	@Test(groups = "update", dependsOnGroups = "create")
	public void testSaveExistingUser() {
		User user = userService.getUser(userId);
		user.setEmail("guillaume_boue@orange.fr");
		user.setInformation("Héhéhé");
		userService.saveUser(user);
		assertEquals(user.getId(), userId);
		user = userService.getUser(userId);
		assertEquals(user.getEmail(), "guillaume_boue@orange.fr");
		assertEquals(user.getInformation(), "Héhéhé");
	}

	@Test(groups = "delete", dependsOnGroups = "update")
	public void testDeleteUser() {
		userService.deleteUser(userService.getUser(userId));
		List<User> userList = userService.getUserList();
		assertTrue(userList.size() == userList().length);
		for (User user : userList) {
			assertNotSame(user.getLastName(), "Boué");
		}
	}

	@Test(groups = "delete", dependsOnGroups = "update", expectedExceptions = DeleteEntityException.class)
	public void testDeleteUserException() {
		userService.deleteUser(1L);
	}

}
