package ua.kiev.unicyb.tcct.ui.desktop.infrastructure;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author Denys Storozhenko.
 */
public final class Constants {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("app", Locale.getDefault(), new UTF8Control());

	public static final int MAIN_WIDTH = 800;

	public static final int MAIN_HEIGHT = 600;

	public static final int LOW_WIDTH = 400;

	public static final int LOW_HEIGHT = 200;

	public static final int MIDDLE_WIDTH = 600;

	public static final int MIDDLE_HEIGHT = 300;

	public static String getProperty(String name) {
//			return new String(RESOURCE_BUNDLE.getString(name).getBytes("ISO-8859-1"), "UTF-8");
		return RESOURCE_BUNDLE.getString(name);

	}
}
