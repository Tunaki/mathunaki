package fr.mathunaki.database.service.user;

import fr.mathunaki.database.service.LocalizedEnum;

public enum UserLevel implements LocalizedEnum {

	ECOLE_INGENIEUR,

	PC, PCSI, MP, MPSI, PREPA_INTEGREE,

	TERMINALE_S, TERMINALE_ES, TERMINALE_L,

	PREMIERE_S, PREMIERE_ES, PREMIERE_L,

	SECONDE, TROISIEME, QUATRIEME, CINQUIEME, SIXIEME;

	@Override
	public String toString() {
		return BUNDLE.getString(name());
	}

	@Override
	public String getLabel() {
		return toString();
	}

}