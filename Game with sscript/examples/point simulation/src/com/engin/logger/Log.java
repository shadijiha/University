/**
 *
 */
package com.engin.logger;

import java.io.*;
import java.text.*;
import java.util.*;

public class Log implements AutoCloseable {

	private final static Log singleton = new Log();

	public enum Level {
		ERROR, WARNING, INFO
	}

	private PrintWriter writer;
	private final String PATH = "logs/";
	private Level logLevel;
	private boolean printToConsole;
	private boolean hasInit;

	protected Log() {
		logLevel = Level.INFO;
		printToConsole = true;
	}

	/**
	 * Change the log level
	 * @param level The new log level
	 */
	public static void setLogLevel(Level level) {
		singleton.logLevel = level;
	}

	/**
	 * @return The current set log level
	 */
	public static Level getLogLevel() {
		return singleton.logLevel;
	}

	/**
	 * Logs message as info
	 * @param message
	 * @param args
	 */
	public static void info(String message, Object... args) {
		singleton.print(Level.INFO, String.format(message, args));
	}

	/**
	 * Logs message as info
	 * @param message
	 */
	public static void info(Object message) {
		singleton.print(Level.INFO, message.toString());
	}

	/**
	 * Logs message as warning
	 * @param message
	 * @param args
	 */
	public static void warn(String message, Object... args) {
		singleton.print(Level.WARNING, String.format(message, args));
	}

	/**
	 * Logs message as info
	 * @param message
	 */
	public static void warn(Object message) {
		singleton.print(Level.WARNING, message.toString());
	}

	/**
	 * Logs message as error
	 * @param message
	 * @param args
	 */
	public static void error(String message, Object... args) {
		singleton.print(Level.ERROR, String.format(message, args));
	}

	/**
	 * Logs message as error
	 * @param message
	 */
	public static void error(Object message) {
		singleton.print(Level.ERROR, message.toString());
	}

	/**
	 * Print to the file and console if applicable
	 * @param message The message to print
	 */
	private void print(Level level, String message) {
		if (level.ordinal() <= this.logLevel.ordinal()) {
			// Init if it is the first print
			if (!hasInit)
				init();

			String msg = getPrefix(level) + "\t" + message;

			writer.println(msg);
			writer.flush();
			if (printToConsole)
				System.out.println(msg);
		}
	}

	/**
	 * You can override this if you extend this class to have your costume prefix for logging
	 * @return The log prefix that goes before a message is printed
	 */
	protected String getPrefix(Level level) {
		return String.format("[%s]\t%s:", Calendar.getInstance().getTime(), level);
	}

	private void init() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String strDate = dateFormat.format(date);
		hasInit = true;

		try {
			File directory = new File(PATH);
			if (!directory.exists()) {
				directory.mkdir();
			}

			writer = new PrintWriter(new FileOutputStream(PATH + strDate + ".log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		writer.close();
	}
}
