package fr.mathunaki.database.service;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonSerialize(using = ToStringSerializer.class)
public interface LocalizedEnum {

	/**
	 * Resource bundle for messages.
	 */
	public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages",
			LocaleContextHolder.getLocale());

	/**
	 * @return Localized label of this enum value.
	 */
	@Override
	public String toString();

	/**
	 * @return Hack to make spring form options tag display a translated text.
	 */
	public String getLabel();

}