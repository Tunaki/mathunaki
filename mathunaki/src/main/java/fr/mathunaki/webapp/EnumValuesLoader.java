package fr.mathunaki.webapp;

import java.util.Arrays;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.mathunaki.database.service.user.UserLevel;

/**
 * <code>EnumValuesLoader</code> stores values of enum classes and put them in
 * the application scope.
 */
public class EnumValuesLoader implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("userLevelList", Arrays.asList(UserLevel.values()));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("userLevelList");
	}

}
