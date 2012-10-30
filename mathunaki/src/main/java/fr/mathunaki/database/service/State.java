package fr.mathunaki.database.service;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public enum State {

	DISABLED,

	ENABLED;

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages",
			LocaleContextHolder.getLocale());

	/**
	 * @return Localized label of this enum value.
	 */
	public String getLabel() {
		return BUNDLE.getString(name());
	}

}
