package fr.mathunaki.database.service.user;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public enum UserLevel {

	ECOLE_INGENIEUR,

	PC, PCSI, MP, MPSI, PREPA_INTEGREE,

	TERMINALE_S, TERMINALE_ES, TERMINALE_L,

	PREMIERE_S, PREMIERE_ES, PREMIERE_L,

	SECONDE, TROISIEME, QUATRIEME, CINQUIEME, SIXIEME;

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages",
			LocaleContextHolder.getLocale());

	/**
	 * @return Localized label of this enum value.
	 */
	public String getLabel() {
		return BUNDLE.getString(name());
	}

}