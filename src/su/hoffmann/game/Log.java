package su.hoffmann.game;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

	private Logger logger;

	public Log() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		logger = Logger.getLogger(Logger.class.getName());
	}

	public void error(String message) {
		logger.log(Level.SEVERE, message);
	}

	public void warning(String message) {
		logger.log(Level.WARNING, message);
	}

	public void note(String message) {
		logger.log(Level.INFO, message);
	}
}
