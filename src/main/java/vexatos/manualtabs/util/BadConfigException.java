package vexatos.manualtabs.util;

/**
 * @author Vexatos
 */
public class BadConfigException extends RuntimeException {
	public BadConfigException(String icon, Throwable t) {
		super(String.format("Invalid tab icon set in config: '%s'", icon), t);
	}

	public BadConfigException(String icon) {
		super(String.format("Invalid tab icon set in config: '%s'", icon));
	}
}
