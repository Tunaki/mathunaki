package fr.mathunaki.database.service;

public enum Status implements LocalizedEnum {

	DISABLED,

	ENABLED;

	@Override
	public String toString() {
		return BUNDLE.getString(name());
	}

	@Override
	public String getLabel() {
		return toString();
	}

}
